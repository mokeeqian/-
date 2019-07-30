package com.ahut.qian.entity;

import com.ahut.qian.util.Config;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 实体化控制器,用来读取数据文件，放入内存中
 */
public class EntityContent {
	// member variable
	private Config config;		// Config　对象
	private HashMap<Integer, User> users = new HashMap<>();		// 一个ID 对应一个User 对象
	private HashMap<Integer, List<Question>> questions = new HashMap<>();	// 一个难度等级对应一个容器
	private List<String> rules = new ArrayList<>();		// 考试规则
	/*
	存储映像:
		< level, list<Question> >	一个难度等级对应当前难度等级的所有question 对象
	 */

	public EntityContent( Config conf ) {
		// 根据client.properties中的UserFile 来找到user.txt的路径　
		this.config = conf;

		// 从user.txt中加载用户信息
		//loadUser( this.config.getString("UserFile") );		//DEL

		loadQuestion( this.config.getString("QuestionFile") );
		loadRules(this.config.getString("ExamRule"));
	}

	// 加载用户 not used
	private void loadUser(String fileName) {
		try {
			BufferedReader in = new BufferedReader(
					new InputStreamReader(
							new FileInputStream(fileName), StandardCharsets.UTF_8 )
			);
			String line;
			// 按行读取
			while ( (line = in.readLine()) != null ) {		// 先赋值，再执行比较判断
				line = line.trim();		// 去除每行的空格
				if ( "".equals(line) || line.startsWith("#") )		// 跳过空行, 和注释行
					continue;
				// 否则, 开始解析数据
				User one = parseUser(line);
				users.put(one.getId(), one);	// 用户信息存入hashmap中
			}
			// 关闭文件流，防止内存占用
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 解析用户, 读入一行数据,返回一个User对象 not used
	// 1000:宁丽娟:1234:13810381038:ninglj@tarena.com.cn
	private User parseUser(String str) {
		User user = new User();
		String[] data = str.split(":");

		user.setId( Integer.parseInt(data[0]) );
		user.setName(data[1]);
		user.setPwd(data[2]);
		user.setPhone(data[3]);
		user.setEmail(data[4]);

		return user;
	}


	/**
	 * @brife 根据id 来获取User对象
	 * @param id, User 的id
	 * @return User 对象
	 */
	public User findUser(int id) {
		return users.get(id);
	}

	/**
	 * 加载问题
	 * @param string, 文件路径
	 */
	private void loadQuestion(String string) {
		try {
			BufferedReader in = new BufferedReader(
					new InputStreamReader(
							new BufferedInputStream(new FileInputStream(string)), StandardCharsets.UTF_8
					)
			);
			String str;
			while (null != (str = in.readLine())) {
				str = str.trim();
				// 跳过空行和注释行
				if ( "".equals(str) || str.startsWith("#") )
					continue;
				// 否则，开始解析题目信息
				Question one = parseQuestion(str, in);
				addQuestion(one);
			}
			in.close();		// 释放资源
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 从文件中获取信息，创建一个Question 对象，并返回
	 * @param string, 题目文件每道题目的第一行信息
	 * @param in, 文件流
	 * @return Question 对象
	 */
	private Question parseQuestion(String string, BufferedReader in) throws IOException {	// 往调用他的函数里抛出异常
		Question question = new Question();
		String[] data = string.split("[@,][a-z]+=");		// + 表示一个或者多个, . 表示0或者多个
		/*
		@answer=2/3,score=5,level=5
		指出下面语句没有编译错误的是:
		long n = 999999999999;
		int n = 999999999999L;
		long n = 999999999999L;
		double n = 999999999999;
		 */
		// data 的内容是: "", "2/3", "5", "5"
		question.setAnswers(parseAnswers(data[1]));
		question.setScore(Integer.parseInt(data[2]));
		question.setLevell(Integer.parseInt(data[3]));

		// 从题目头再往下读一行，就是题干信息
		question.setTitle(in.readLine());

		// 连续读取四行选项信息
		List<String> options = new ArrayList<>();
		options.add(in.readLine());
		options.add(in.readLine());
		options.add(in.readLine());
		options.add(in.readLine());

		question.setOptions(options);		// 设置选项

		// 确定当前问题的类型，单选题还是多选题
		question.setType(
				question.getAnswers().size() == 1 ? Question.SINGLE_SELECTION : Question.MULTI_SELECTIOIN );

		return question;
	}

	/**
	 * 向容器questions中按照 *难度等级* 加入一个question 对象
	 * @param question, Question 对象
	 */
	private void addQuestion(Question question) {

		/*
		// 取出当前问题难度等级的对象
		List<Question> list = questions.get( question.getLevell() );
		// 如果当前容器中没有等于当前难度等级的Question 对象
		if ( list == null ) {
			list = new ArrayList<Question>();		// 创建一个对象容器
			questions.put(question.getLevell(), list);		// 将当前list 容器以难度等级为key, 放入hashmap 容器中
		}
		*/
		List<Question> list = questions.computeIfAbsent(question.getLevell(), k -> new ArrayList<>());
		list.add(question);
	}

	/**
	 * 解析答案 // answer=1/2
	 * @param string, 文件中解析出来的答案字符串
	 * @return 存放答案的List 容器
	 */
	private List<Integer> parseAnswers(String string) {
		List<Integer> list = new ArrayList<Integer>();
		String[] data = string.split("/");
		for (String datum : data) {
			list.add(Integer.parseInt(datum));
		}
		return list;
	}

	/**
	 * @brife 根据题目难度等级获取题目的List 容器
	 * @param level 难度等级
	 * @return List 容器
	 */
	public List<Question> findQuestion(int level) {
		return new ArrayList<Question>(questions.get(level));
	}

	public int getTimeLimit() {
		return this.config.getInt("TimeLimit");
	}

	public String getTitle() {
		return this.config.getString("PaperTitle");
	}

	/**
	 * 加载配置文件中的考试规则
	 */
	private void loadRules(String string) {
		String str;
		//List<String> rules = new ArrayList<>();
		try {
			BufferedReader in = new BufferedReader(
					new InputStreamReader(
							new BufferedInputStream(new FileInputStream(string)), StandardCharsets.UTF_8
					)
			);
			while (null != (str = in.readLine())) {
				str = str.trim();
				// 跳过空行
				if ( "".equals(str) || str.startsWith("#"))
					continue;
				// 否则，开始解析规则信息
				this.rules.add(str);
			}
			in.close();		// 释放资源
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<String> getRules() {
		return this.rules;
	}


}
