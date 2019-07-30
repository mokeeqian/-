package com.ahut.qian.ui;

import com.ahut.qian.entity.EntityContent;
import com.ahut.qian.service.ExamService;
import com.ahut.qian.service.ExamServiceImpl;
import com.ahut.qian.service.UserDaoImpl;
import com.ahut.qian.util.Config;

/**
 * @brife main method!
 */
public class Main {
	public static void main(String[] args) {
		Config config = new Config("client.properties");
		EntityContent entityContent = new EntityContent(config);
		ExamServiceImpl examServiceImpl = new ExamServiceImpl(entityContent);
		LoginFrame loginFrame =  new LoginFrame();
		MenuFrame menuFrame = new MenuFrame();
		ExamFrame examFrame = new ExamFrame();
		ClientContent clientContent = new ClientContent();
		UserDaoImpl userDaoImpl = new UserDaoImpl();
		SignupFrame signupFrame = new SignupFrame();

		// 一次完整的握手, 将这里已经初始化的对象赋值给各自类里面的只声明的对象
		loginFrame.client = clientContent;
		clientContent.loginFrame = loginFrame;
		clientContent.menuFrame = menuFrame;
		clientContent.examService = examServiceImpl;
		examServiceImpl.userDaoImpl = userDaoImpl;		// ADD 数据库版本

		menuFrame.clientContent = clientContent;
		clientContent.examFrame = examFrame;

		examFrame.clientContent = clientContent;		//ADD
		examServiceImpl.examFrame = examFrame;			//ADD 保存最后一题的答案用
		clientContent.entityContent = entityContent;

		clientContent.examServiceImpl = examServiceImpl;


		// 注册
		clientContent.signupFrame = signupFrame;
		clientContent.userDaoImpl = userDaoImpl;
		userDaoImpl.signupFrame = signupFrame;

	}
}

/**
 * TODO:
 *  1. 题目从数据库读取
 *  2. 窗口叉叉按钮bug 修复
 *  3. 数据库操作性能优化
 */
