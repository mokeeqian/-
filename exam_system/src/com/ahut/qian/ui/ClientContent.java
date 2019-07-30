package com.ahut.qian.ui;


import com.ahut.qian.entity.EntityContent;
import com.ahut.qian.entity.ExamInfo;
import com.ahut.qian.entity.QuestionInfo;
import com.ahut.qian.entity.User;
import com.ahut.qian.service.ExamService;
import com.ahut.qian.service.ExamServiceImpl;
import com.ahut.qian.service.UserDaoImpl;
import com.ahut.qian.util.ConnectionUtil;
import com.ahut.qian.util.IdOrPwdException;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * 控制器
 * 用于数据交换
 */
public class ClientContent {

	// 在这里先声明，赋值放在Main 里面
	public LoginFrame loginFrame;// = new LoginFrame();
	public ExamService examService;
	public MenuFrame menuFrame;// = new MenuFrame();
	public SignupFrame signupFrame;
	public ExamInfo examInfo;
	public QuestionInfo currentQuestionInfo;		// 当前题目信息
	public ExamFrame examFrame;
	public Timer timer = new Timer();
	public EntityContent entityContent;		//ADD

	public ExamServiceImpl examServiceImpl;

	public UserDaoImpl userDaoImpl;			//ADD

	private boolean isSelfSubmited;		// 标志位变量, 标志是否是考生自己点击的交卷按钮, if (true), 结束计时器线程

	// 用来标记考试成绩的类
	class MyScore {
		int score;		// 分数
		int flag = 0;		// 标志位变量, 开始flag = 0, 每考试一次 flag 就加一

		MyScore() {
		}
	}

	private MyScore stuScore = new MyScore();

	/**
	 * 用户登录
	 */
	public void login() {

		try {
			int id = Integer.parseInt(loginFrame.idField.getText());
			String sPwd = new String(loginFrame.pwdField.getPassword());
			User user = examService.login(id, sPwd);
			menuFrame.info.setText("欢迎: " + user.getName() + " 同学");	//ADD
			menuFrame.setVisible(true);
			//loginFrame.setVisible(false);
			loginFrame.dispose();

		} catch (IdOrPwdException e) {
			loginFrame.mess.setText(e.getMessage());
		} catch (NumberFormatException e) {
			loginFrame.mess.setText("非法输入!");
		}
	}

	/**
	 * 用户注册
	 */
	public void signup() {
		//this.signupFrame = new SignupFrame();
		userDaoImpl.praseSignupInfo();
	}

	/**
	 * 注册行为的再一次确认
	 */
	public void signupConfirm() {

	}


	/**
	 * 点击退出按钮退出前的再一次确认
	 */
	public void exitConfirm() {
		int val = JOptionPane.showConfirmDialog(
				this.loginFrame, "确认退出?","友情提示",
				JOptionPane.YES_NO_OPTION );

		if ( val == JOptionPane.YES_OPTION ) {
			System.exit(0);
		}
		if ( val == JOptionPane.NO_OPTION ) {
			return;
		}
	}

	/**
	 * 访问业务层,开始考试
	 */
	public void startExam() {

		// // 禁止重复考试 V1.0
		//if ( stuScore.flag > 0) {

		// V2.0
		Connection connection = ConnectionUtil.getConnection();
		String sql = "select * from tb_score where id= ?";
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, examServiceImpl.loginUser.getId());

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				if (resultSet.getInt("examed") == 1) {
					JOptionPane.showMessageDialog(examFrame, "你已经考过了,禁止重复考试!");
					return;
				}

				if ( resultSet.getInt("examed") == 0 ) {
					continue;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.close(connection);
		}

		//FIXME: 点击关闭窗口，这个对话框就退出了
		// 显示考试规则, 只有点击同意才能继续考试
		int flag = this.showRules(true);

		// 表示点击了取消按钮,就不进行就考试
		if ( flag == 1)
			return;
		else {

			// 考试次数加一
			stuScore.flag++;

			// 生成一张考试卷
			examInfo = examService.start();

			// 取得第一道题目，显示在menuFrame中
			currentQuestionInfo = examService.getQuestion(0);
			// 考试信息和考试题目更新到考试界面
			examFrame.updateView(examInfo, currentQuestionInfo);
			menuFrame.setVisible(false);
			examFrame.setVisible(true);

			// 点击开始考试时，就应该开始计时
			startTime();
		}
	}

	/**
	 * 查询考试成绩
	 */
	public void queryScore() {

		Connection connection = ConnectionUtil.getConnection();
		String sql = "select * from tb_score where id= ?";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, examServiceImpl.loginUser.getId());
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				int score = resultSet.getInt("score");

				// 0 表示还没考试
				if (resultSet.getInt("examed") == 0) {
					//if ( stuScore.flag == 0 ) {
					JOptionPane.showMessageDialog(menuFrame, "你还没考试, 请点击开始考试按钮");
					return;
				}

				//JOptionPane.showMessageDialog(menuFrame, "你的成绩是: " + stuScore.score);
				JOptionPane.showMessageDialog(menuFrame, "你的成绩是: " + score);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.close(connection);
		}


	}

	/**
	 * @brife 考试规则, 弹出一个界面, 递归函数
	 *  		直至考生点击同意按钮时才退出此函数
	 * @param isStartExam: 是否是点击开始考试按钮的时候显示,
	 *                 		如果是(true)，则额外显示一个取消按钮
	 * @return 1 表示确认消息框包含取消按钮 并且 点击了取消按钮
	 * 			0 表示剩余的其他所有情况
	 */
	public int showRules(boolean isStartExam) {
		StringBuilder str = new StringBuilder();
		List<String> list = entityContent.getRules();

		for (String s : list) {
			str.append(s).append("\n");
		}

		// 防止不小心点击到开始考试按钮, 如果在点击考试按钮时弹出此窗口，就多显示一个取消选项
		int option = isStartExam ? JOptionPane.YES_NO_CANCEL_OPTION: JOptionPane.YES_NO_OPTION;

		int val = JOptionPane.showConfirmDialog(menuFrame, str.toString(), "考试规则",option);
		if ( val == JOptionPane.NO_OPTION ) {
			JOptionPane.showMessageDialog(menuFrame, "请点击同意按钮进行考试");
			showRules(isStartExam);
		}
		if ( val == JOptionPane.YES_OPTION )
			return 0;

		if ( val == JOptionPane.NO_OPTION )
			return 0;

		// 有一个取消按钮
		if (option == JOptionPane.YES_NO_CANCEL_OPTION && val == JOptionPane.CANCEL_OPTION )
			return 1;

		return 0;
	}

	/**
	 * 考试倒计时方法
	 * FIXME: 主动点击交卷按钮后，计时器仍然在运行
	 * 			已解决!!!
	 */
	private void startTime() {

		final long end = System.currentTimeMillis() + examInfo.getTimeLimit() * 60 * 1000;	// 系统当前时间的毫秒

		// 开始考试计时
		timer.schedule(new TimerTask() {
			@Override
			public void run() {

				long now = System.currentTimeMillis();
				long show = end - now;
				long h = show / 1000 / 60 / 60;		// 强制取整
				long m = show / 1000 / 60 % 60;
				long s = show / 1000 % 60;

				examFrame.timer.setText("剩余时间: " + h + ":" + m + ":" + s);
				if ( show <= 5000 ) {
					examFrame.timer.setForeground(Color.red);
				}
			}
		}, 0, 1000);		// 每间隔一秒对task 进行调用一次

		// 时间到了，结束进程
		timer.schedule(new TimerTask() {
			@Override
			public void run() {

				//ADD
				// 如果考生自己点击了交卷按钮, 则停止计时
				if (isSelfSubmited)
					return;

				examService.send();
				JOptionPane.showMessageDialog(examFrame, "考试时间到，已经为你自动交卷");
				examFrame.dispose();
				menuFrame.setVisible(true);
			}
		}, new Date(end));
	}

	/**
	 * 点击一次下一题按钮，界面显示出下一题的题目, 这里的index 存的是当前题目的index, 而题目的index
	 * 我是设置从1 开始的
	 */
	public void next() {

		int index = currentQuestionInfo.getQuestionIndex();		// 当前的index ,刚开始是 1

		currentQuestionInfo = examService.getQuestion(index);		// List 容器要从0 开始索引

		// 1. 取得用户当前题目的答案
		List<Integer> userAnswers = examFrame.getUserAnswers();
		// 2. 保存到模型层
		examService.saveUserAnswers(index-1, userAnswers);		// 索引要从0 开始
		examFrame.updateView(examInfo, currentQuestionInfo);
	}

	public void pre() {
		int index = currentQuestionInfo.getQuestionIndex();    // 刚开始是 1
		currentQuestionInfo = examService.getQuestion(index-2);

		// 保存当期用户答案到模型层
		// 1. 取得用户当前题目的答案
		List<Integer> userAnswers = examFrame.getUserAnswers();
		// 2. 保存到模型层
		examService.saveUserAnswers(index-1, userAnswers);
		examFrame.updateView(examInfo, currentQuestionInfo);
	}

	/**
	 * 交卷
	 */
	public void submit() {
		int val = JOptionPane.showConfirmDialog(examFrame, "确认交卷吗?",
				"友情提示", JOptionPane.YES_NO_OPTION);
		// 交卷
		if ( val == JOptionPane.YES_OPTION ) {
			// 更新标志位变量
			this.isSelfSubmited = true;
			stuScore.score = examService.send();
		}
		if ( val == JOptionPane.NO_OPTION) {
			return;
		}
		// 交卷后不能重复交卷，该功能放在考试开始函数中
		//this.queryScore();
		examFrame.dispose();
		menuFrame.setVisible(true);
	}

}
