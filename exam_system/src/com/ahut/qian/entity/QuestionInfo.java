package com.ahut.qian.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目和用户选择的答案
 */
public class QuestionInfo {
	private Question question;		// 题目问题
	private List<Integer> userAnswers = new ArrayList<Integer>();		// 用户答案
	private int questionIndex;		// 题目序号

	public QuestionInfo() {

	}

	public QuestionInfo(int questionIndex, Question question) {
		this.questionIndex = questionIndex;
		this.question = question;
	}

	public QuestionInfo(int questionIndex, Question question, List<Integer> userAnswers) {
		this.questionIndex = questionIndex;
		this.question = question;
		this.userAnswers = userAnswers;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public List<Integer> getUserAnswers() {
		return userAnswers;
	}

	public void setUserAnswers(List<Integer> userAnswers) {
		this.userAnswers = userAnswers;
	}

	public int getQuestionIndex() {
		return questionIndex;
	}

	public void setQuestionIndex(int questionIndex) {
		this.questionIndex = questionIndex;
	}

	@Override
	public String toString() {
		return question.toString();
	}
}
