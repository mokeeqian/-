package com.ahut.qian.util;

import java.sql.*;

/**
 * 数据库连接工具
 */
public class ConnectionUtil {
	// 数据库配置
	private static String user = "root";
	private static String pwd = "990505";
	private static String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8";
	// 数据库驱动
	private static String driver = "com.mysql.jdbc.Driver";

	// 连接数据库静态方法
	public static Connection getConnection() {
		Connection connection = null;

		try {
			// 加载驱动
			//Class.forName(driver);	// 这里我装了mysql-connector.deb,　驱动装在系统底层
			// 获取连接通道
			connection = DriverManager.getConnection(url, user, pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

	// 关闭当期数据库连接通道
	public static void close( Connection connection ) {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 关闭通往数据库的陈述
	public static void close(Statement statement) {
		try {
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 结果集
	public static void close(ResultSet resultSet) {
		try {
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Connection connection = ConnectionUtil.getConnection();
		System.out.println(connection);
	}
}
