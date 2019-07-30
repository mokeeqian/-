package com.ahut.qian.db;

import java.sql.*;


/**
 * 使用mysql 官方提供的 jdbc driverManager 来连接数据库
 */
public class dbConnect {

	// TODO 如何匹配某一行的对应信息?
	public static void main(String[] args) {
		dbConnect db = new dbConnect();
		String user = "admin";
		//String sql = "select * from stuManagerDB.tb_user where userName='" + user + "';" ;
		String sql = "select * from stuManagerDB.tb_user;";
		ResultSet res = db.queryDatabase(sql);
		try {
			while ( res.next() ) {
				System.out.println(res.getString("userPwd"));
			}
			db.disConnect();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private Connection M_conn;		// connection 对象
	//private ResultSet M_result;		// 结果集


	/**
	 * @brife 构造器,建立连接
	 */
	public dbConnect() {
		this.connection();
	}

	private Connection connection() {
		//Connection conn = null;
		try {
			M_conn = DriverManager.getConnection("jdbc:mysql://localhost/stuManagerDB?"
								+ "user=这里写你的数据库用户名&password=这里写你的数据库密码");
		} catch(SQLException e) {
			System.out.println( "SqlException: " + e.getMessage() );
			System.out.println( "SqlState: " + e.getSQLState() );
		}
		return M_conn;
	}

	/**
	 * @param sql, 查询语句
	 * @return 结果集
	 */
	public ResultSet queryDatabase(String sql) {
		try {
			ResultSet resultSet = null;
			Statement statement = this.connection().createStatement();

			// 获取结果集
				try {
					//M_result = statement.executeQuery( sql );
					resultSet = statement.executeQuery(sql);
				} catch (SQLException e) {
					System.out.println("SqlException: " + e.getMessage());
				}

			//return M_result;
			return resultSet;
		} catch (SQLException a) {
			System.out.print( "SqlException: " + a.getMessage() );
			System.out.print( "SqlState: " + a.getSQLState() );
		}
		return null;
	}

	/**
	 * @brife 修改数据库信息
	 * @param sql, 数据库语句
	 */
	public void updateDatabase(String sql) {
		try {
			Statement statement = this.connection().createStatement();
			if ( statement.executeUpdate(sql) == 0 )
				System.out.println("修改失败");
			System.out.println("修改成功");
		} catch (SQLException e) {
			System.out.println( "SqlException: " + e.getMessage() );
		}
	}

	/**
	 * @brife 断开连接
	 */
	public void disConnect() {
		try {
			M_conn.close();
			M_conn = null;
			//M_result = null;
		} catch (SQLException e) {
			System.out.print( "SqlException: " + e.getMessage() );
		}
	}


}
