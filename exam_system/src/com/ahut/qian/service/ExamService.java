package com.ahut.qian.service;

import com.ahut.qian.entity.ExamInfo;
import com.ahut.qian.entity.QuestionInfo;
import com.ahut.qian.entity.User;
import com.ahut.qian.util.IdOrPwdException;

import java.util.List;

/**
 * 考试服务接口
 * 标记了当前应用的核心功能
 */
public interface ExamService {

	// 根据id　实现登录
	User login(int id, String spwd) throws IdOrPwdException;

	// 开始考试的方法， 得到当前考试窗口中的所有信息
	ExamInfo start();

	// 对外提供获取试卷上考试题目的方法
	QuestionInfo getQuestion(int index);

	// 保存当前用户答案
	void saveUserAnswers(int index, List<Integer> userAnswers);

	// 考生交卷, 包含改卷的功能, 同时返回考生的分数
	int send();

}
