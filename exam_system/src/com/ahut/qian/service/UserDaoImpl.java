package com.ahut.qian.service;

import com.ahut.qian.entity.User;
import com.ahut.qian.ui.SignupFrame;
import com.ahut.qian.util.ConnectionUtil;
import com.ahut.qian.util.IdOrPwdException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;



public class UserDaoImpl implements UserDao {

	public SignupFrame signupFrame;

	@Override
	public User findUserById(int id) {
		User user = new User();
		// 数据库查询语句
		String sql = "select * from tb_user where id=?";
		// 创建连接数据库的通道
		Connection connection = ConnectionUtil.getConnection();
		ResultSet resultSet = null;
		// 陈述语句
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(sql);

			// 对通道中的问号设置值
			// 1 表示第一个问号
			preparedStatement.setInt(1, id);

			// 将完整的sql　语句提交数据库
			resultSet = preparedStatement.executeQuery();		// resultSet 得到的是数据库　表的表头

			// 如果没查询到结果, 即不存在当前id 的用户
			if ( !resultSet.next()) {
				return null;
			}

			// 从数据库中取出id 列的数据, 设置为user对象相应属性的值
			user.setId( resultSet.getInt("id") );
			user.setName( resultSet.getString("name") );
			user.setPwd( resultSet.getString("pwd") );
			user.setPhone( resultSet.getString("phone") );
			user.setEmail( resultSet.getString("email") );

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭数据库连接
			ConnectionUtil.close(connection);
		}

		return user;
	}

	@Override
	public void praseSignupInfo() {
		//TODO: 一个手机号码只能注册一个

		String name = signupFrame.nameField.getText();
		String pwd = new String(signupFrame.pwdField.getPassword());
		String phone = signupFrame.phoneField.getText();
		String eamil = signupFrame.emailField.getText();

		Connection connection = ConnectionUtil.getConnection();
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		String sql = "insert into tb_user values(?)";

		String string = "";



	}


	//TODO:  自动生成考生ID
	private int getRandomNumber() {
		int id = 1000;

		return id;
	}


}
