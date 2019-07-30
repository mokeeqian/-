package com.ahut.qian.service;

/**
 * date: 2019-7-18
 */

import com.ahut.qian.entity.*;
import com.ahut.qian.ui.ExamFrame;
import com.ahut.qian.util.ConnectionUtil;
import com.ahut.qian.util.IdOrPwdException;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ExamServiceImpl implements ExamService{

	private EntityContent entityContent;		// 不允许外部使用者修改他
	private List<QuestionInfo> paper = new ArrayList<>();		// 存储考试卷子,含有考生的答案
	public User loginUser;

	public ExamFrame examFrame;			//ADD 保存最后一题答案
	public UserDaoImpl userDaoImpl;		//ADD

	public User login(int id, String spwd) throws IdOrPwdException {		// 谁调用他，谁就可以得到一个异常
		User user = new User();
		//user = entityContent.findUser(id);

		// 数据库版本
		user  = userDaoImpl.findUserById(id);

		loginUser = user;
		if ( user != null && spwd.equals(user.getPwd()) ) {
			return user;
		}
		else {
			throw new IdOrPwdException("用户名或密码错误!");
		}
	}

	@Override
	public ExamInfo start() {
		ExamInfo examInfo = new ExamInfo();
		// 创建考试卷子
		createPaper();

		// 组织考试信息
		examInfo.setQuestionCount(paper.size());
		examInfo.setTimeLimit(entityContent.getTimeLimit());
		examInfo.setTitle(entityContent.getTitle());
		examInfo.setUser(loginUser);		// global variable
		return examInfo;
	}

	@Override
	public QuestionInfo getQuestion(int index) {
		return paper.get(index);
	}

	@Override
	public void saveUserAnswers(int index, List<Integer> userAnswers) {
		QuestionInfo qi = paper.get(index);
		qi.getUserAnswers().clear();	// 答案全部清除
		qi.getUserAnswers().addAll(userAnswers);	// 答案存进去
	}

	@Override
	public int send() {
		//FIXME: 保存最后一题答案这个模块放在哪比较好?
		// 在最后提交方法里保存最后一题的答案

		// 保存最后一题答案
		saveUserAnswers(19, examFrame.getUserAnswers());

		int score = 0;
		for (int i = 0; i < paper.size() ; i++) {
			QuestionInfo qi = paper.get(i);
			Question q = qi.getQuestion();

			// 使用单选题的评分方式
			if ( Question.SINGLE_SELECTION == q.getType() ) {
				// 如果当期题目是单选题， 而用户选择了多个答案,不得分
				if ( qi.getUserAnswers().size() == 1 ) {
					if (qi.getUserAnswers().get(0).equals(q.getAnswers().get(0)))
						score += q.getScore();
				}
			}
			else if ( Question.MULTI_SELECTIOIN == q.getType() ) {
				// 答案个数不对，肯定不得分
				if ( qi.getUserAnswers().size() == q.getAnswers().size() ) {
					int k = 0;
					for ( int j = 0; j < q.getAnswers().size(); j++ ) {
						// 每个答案都正确才得分
						if (qi.getUserAnswers().get(j).equals(q.getAnswers().get(j)))
							k++;
					}
					if ( k == q.getAnswers().size() )
						score += q.getScore();
				}
			}
		}

		// 分数存入数据库
		Connection connection = ConnectionUtil.getConnection();
		String sql = "update tb_score set score = ? , examed = 1 where id = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, score);
			preparedStatement.setInt(2, loginUser.getId());

			boolean flag = preparedStatement.execute();
			if (flag) {
				System.out.println("ok");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.close(connection);
		}


		return score;
	}


	// 有参构造器
	public ExamServiceImpl(EntityContent entityContent) {
		this.entityContent = entityContent;
	}

	public EntityContent getEntityContent() {
		return entityContent;
	}

	public void setEntityContent(EntityContent entityContent) {
		this.entityContent = entityContent;
	}

	/**
	 * 创建一张考试卷,每个难度等级随机取两个题目
	 */
	private void createPaper() {
		Random random = new Random();
		int index = 1;		// 题号
		for ( int i =Question.LEVEL1; i <= Question.LEVEL10; i++ ) {
			// 获取当前难度等级下的所有题目
			List<Question> list = entityContent.findQuestion(i);
			Question q1 = list.remove(random.nextInt(list.size()));
			Question q2 = list.remove(random.nextInt(list.size()));
			paper.add(new QuestionInfo(index++, q1));
			paper.add(new QuestionInfo(index++, q2));
		}
	}
}
