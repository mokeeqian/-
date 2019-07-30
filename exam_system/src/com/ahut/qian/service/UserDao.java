package com.ahut.qian.service;

import com.ahut.qian.entity.User;

/**
 * 用户控制的核心类(数据库)
 */
public interface UserDao {
	User findUserById(int id);

	// 解析注册界面的输入框信息
	void praseSignupInfo();

}
