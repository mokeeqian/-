package com.ahut.qian.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 考试题目类(仅含有题目)
 */
public class Question {
	// 试题等级
	public static final int LEVEL1 = 1;
	public static final int LEVEL2 = 2;
	public static final int LEVEL3 = 3;
	public static final int LEVEL4 = 4;
	public static final int LEVEL5 = 5;
	public static final int LEVEL6 = 6;
	public static final int LEVEL7 = 7;
	public static final int LEVEL8 = 8;
	public static final int LEVEL9 = 9;
	public static final int LEVEL10 = 10;

	// 用来标记当前考试是单选题还是多选题
	public static final int SINGLE_SELECTION = 0;
	public static final int MULTI_SELECTIOIN = 1;


	private int id;		// 题号
	private String title;	// 题目
	private List<String> options = new ArrayList<String>();		// 题目的选项
	private List<Integer> answers = new ArrayList<Integer>();	// 答案
	private int score;		// 分数
	private int levell;		// 难度等级
	private int type;		// 题目类型

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}

	public List<Integer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Integer> answers) {
		this.answers = answers;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getLevell() {
		return levell;
	}

	public void setLevell(int levell) {
		this.levell = levell;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Question() {

	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(title + "\n");
		for ( int i = 0; i < options.size(); i ++ ) {
			sb.append((char) ('A' + i)).append(".").append(options.get(i)).append("\n");		//ASCII
			sb.append("\n");
		}
		//System.out.println("131`3123123");
		return sb.toString();
	}
}
