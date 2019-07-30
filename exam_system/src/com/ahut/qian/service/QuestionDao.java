package com.ahut.qian.service;

import com.ahut.qian.entity.Question;

import java.util.List;

/**
 * 数据库版的考试题目接口
 */
public interface QuestionDao {



	/**
	 * @brife 从数据库中查找所有的题目
	 * @return 包括所有的对象的一个容器
	 */
	List<Question> findAllQuestions();



	/**
	 * @brife 根据题号查找题目
	 * @param id, 用户id
	 * @return 一个id 为 id 的对象
	 */
	Question findQuestionById(int id);
}
