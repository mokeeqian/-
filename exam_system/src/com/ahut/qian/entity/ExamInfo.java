package com.ahut.qian.entity;

/**
 * 当前考试窗口中所有与考试相关的信息 的类
 */
public class ExamInfo {

	private String title;			// 考试科目
	private User user;			// 考生
	private int timeLimit;			// 时间限制
	private int questionCount;		// 考试题目个数

	public ExamInfo() {

	}

	public ExamInfo(String title, String user, int timeLimit, int questionCount) {

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(int timeLimit) {
		this.timeLimit = timeLimit;
	}

	public int getQuestionCount() {
		return questionCount;
	}

	public void setQuestionCount(int questionCount) {
		this.questionCount = questionCount;
	}

	public String toString() {
		if (null == user)	// 不会执行
			return "无信息";
		return "姓名: " + user.getName() + "		编号: " + user.getId() + "	考试时间: " + this.getTimeLimit()
				+ " 分钟" + "	考试科目: " + this.getTitle() + "		题目数量: " + this.getQuestionCount();
	}
}
