package com.ahut.qian.service;

import com.ahut.qian.entity.Question;
import com.ahut.qian.util.ConnectionUtil;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;

import java.util.List;

public class QuestionDaoImpl implements QuestionDao {

	public List<Question> questions = new ArrayList<>();


	@Override
	public List<Question> findAllQuestions() {
		String sql = "select * from tb_question";
		List<Question> qs = new ArrayList<>();

		Connection connection = ConnectionUtil.getConnection();

		// sql 语句中没有问号, 故只要陈述语句即可, 不需要预编译语句
		Statement statement = null;
		try {
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while ( resultSet.next() ) {
				Question q = new Question();
				String sa = resultSet.getString("answers");

				//FIXME: 单选题截取可能会有问题
				String[] data = sa.split("/");


				for ( int i = 0; i < data.length; i++ ) {
					q.getAnswers().add(Integer.parseInt(data[i]));
				}

				q.setScore(resultSet.getInt("score"));
				q.setLevell(resultSet.getInt("level"));
				q.setTitle(resultSet.getString("title"));

				String o1 = resultSet.getString("option1");
				String o2 = resultSet.getString("option2");
				String o3 = resultSet.getString("option3");
				String o4 = resultSet.getString("option4");

				q.getOptions().add(o1);
				q.getOptions().add(o2);
				q.getOptions().add(o3);
				q.getOptions().add(o4);

				qs.add(q);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.close(connection);
		}


		return qs;
	}

	@Override
	public Question findQuestionById(int id) {

		//TODO

		return null;
	}

	/**
	 * @brife 将考试题库导入数据库, 注意数据库连接时要设置utf8 编码
	 * @param id
	 * @param answers
	 * @param score
	 * @param level
	 * @param title
	 * @param o1
	 * @param o2
	 * @param o3
	 * @param o4
	 */
	public void insertIntoDatabase(int id, String answers, int score ,int level, String title,
							String o1, String o2, String o3, String o4) {
		String sql =
				"insert into tb_question(id,answers,score,level,title," +
						"option1,option2,option3,option4) values ("
				+ "?," + "?," + "?," + "?," + "?," + "?," + "?," + "?," + "?)";
		Connection connection = ConnectionUtil.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, answers);
			preparedStatement.setInt(3, score);
			preparedStatement.setInt(4, level);
			preparedStatement.setString(5, title);
			preparedStatement.setString(6, o1);
			preparedStatement.setString(7, o2);
			preparedStatement.setString(8, o3);
			preparedStatement.setString(9, o4);
			boolean res = preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.close(connection);
		}
	}

	private void loadQuestion( String filename) {
		try {
			BufferedReader in = new BufferedReader(
					new InputStreamReader(
							new BufferedInputStream(new FileInputStream(filename)), StandardCharsets.UTF_8
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

				// 向容器中加入一个问题对象
				questions.add(one);
			}
			in.close();		// 释放资源
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @brife 先把问题信息用对象存起来
	 * @param string
	 * @param in
	 * @return
	 * @throws IOException
	 */
	private  Question parseQuestion(String string, BufferedReader in) throws IOException {	// 往调用他的函数里抛出异常
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

	private  List<Integer> parseAnswers(String string) {
		List<Integer> list = new ArrayList<Integer>();
		String[] data = string.split("/");
		for (String datum : data) {
			list.add(Integer.parseInt(datum));
		}
		return list;
	}



	// 向数据库导入题库
	public  static void main(String[] args) {

		QuestionDaoImpl questionDao = new QuestionDaoImpl();

		for ( int i = 0; i < 61; i++ ) {
			questionDao.loadQuestion("corejava.txt");
			Question q = questionDao.questions.get(i);

			List<Integer> ans = q.getAnswers();
			StringBuilder answer = new StringBuilder();

			// 答案特殊处理
			for (int j = 0; j < ans.size(); j++) {
				answer.append(ans.get(j));
				answer.append("/");
			}
			// 去除最后的 "/"
			answer.replace(answer.length() - 1, answer.length(), "");

			// 插入数据库
			questionDao.insertIntoDatabase(i+1, answer.toString(), q.getScore(), q.getLevell(), q.getTitle(),
					q.getOptions().get(0), q.getOptions().get(1),
					q.getOptions().get(2), q.getOptions().get(3)  );
		}

	}

}
