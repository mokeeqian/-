/**
 * date: 2019-7-15
 */


package com.ahut.qian.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


public class LoginFrame extends JFrame {

	//public static void main(String[] args) {
	//	new LoginFrame();
	//}

	/**
	 * 	java 强制规定任何变量使用之前都必须要初始化!!!
	 * 	初始化 = 声明 + 赋值
	 * 	声明 = 数据类型 + 变量名
	 * 	赋值: "=" 就是赋值
	 */

	public ClientContent client;// = new ClientContent();


	// 先声明为全局变量
	JTextField idField;
	JPasswordField pwdField;
	JLabel mess;

	public LoginFrame() {
		init();
	}

	private void init() {
		// 为当前窗口设置标题
		this.setTitle("登录系统");
		// 设置当前窗口的内容
		// 创建一个名为 contentPane 的空白画布
		//JPanel contentPane = new JPanel();
		JPanel contentPane = createContentPane();
		// 把画布添加　到窗口上
		this.setContentPane(contentPane);

		// 设置窗口的大小
		//this.setSize(300, 200);
		this.setSize(400,280);

		//设置当期窗口居中　
		this.setLocationRelativeTo(null);

		// 设置当期窗口大小不可变
		this.setResizable(false);

		// 设置 当点击关闭窗口时，就退出程序
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 给当前窗口添加监听器
		addWindowListener(new WindowListener() {
			@Override
			public void windowOpened(WindowEvent e) {

			}

			// 窗口正在关闭时带调用
			// 用户视图从窗口的系统菜单中关闭窗口时
			@Override
			public void windowClosing( WindowEvent e ) {
				client.exitConfirm();
			}

			// 窗口调用某个方法导致其关闭时　
			// dispose() 关闭　彻底关闭窗口，并释放窗口占用的内存
			// setVisiable() 是隐藏窗口，其在内存中仍然在运行
			@Override
			public void windowClosed(WindowEvent e) {

			}

			// 窗口最小化时调用
			@Override
			public void windowIconified(WindowEvent e) {

			}

			// 在最小化变成正常状态时调用
			@Override
			public void windowDeiconified(WindowEvent e) {

			}

			@Override
			public void windowActivated(WindowEvent e) {

			}

			@Override
			public void windowDeactivated(WindowEvent e) {

			}
		});

		// 设置当前窗口可见
		this.setVisible(true);
	}

	/**
	 * @return an object to JPanel
	 * @brife create
	 */
	private JPanel createContentPane() {
		/*
		 * new BorderLayout(): 布局管理器
		 * 特点:
		 * 		当东西南北方向没有组建的时候，中间部分会自动充满整个画布
		 */
		JPanel panel = new JPanel(new BorderLayout());        // 布局管理器,将画布分为５个部分

		// 为当前画布添加边框
		panel.setBorder(new EmptyBorder(8, 8, 8, 8));    //单位是pixel

		// 添加内容, 设置位置：北中央
		panel.add(BorderLayout.NORTH, new JLabel("登录考试系统", JLabel.CENTER));        // JLabel 是标签组建

		// 在当前画布的中间部分添加一个居中的内容, 由createCenterPane() 来完成
		panel.add(BorderLayout.CENTER, createCenterPane());

		// 创建一个登录 按钮
		panel.add( BorderLayout.SOUTH, createBtnPane() );

		return panel;
	}

	private JPanel createBtnPane() {
		/*
		FlowLayout() 是一种流线布局()
		特点:
			空间按照从左向右，从上乡下
		 */
		JPanel panel = new JPanel( new FlowLayout() );


		JButton login = new JButton("登录");
		// 为登录按钮添加行为监听器,　当login　按钮发生某种操作后，会检测到
		login.addActionListener(new ActionListener() {		// 这里的一对花括弧是属于实现其接口的匿名类的　
			@Override
			public void actionPerformed(ActionEvent e) {
				client.login();		// 调用我写的login() 方法
			}
		});

		JButton cancel = new JButton("退出");
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				client.exitConfirm();
			}
		});

		JButton signup = new JButton("注册");
		signup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				client.signup();
			}
		});

		panel.add(login);
		panel.add(signup);
		panel.add(cancel);

		return panel;
	}

	/**
	 * @return JPanel object
	 * @brife create
	 */
	private JPanel createCenterPane() {
		JPanel panel = new JPanel( new BorderLayout() );
		panel.setBorder( new EmptyBorder(8, 0, 0, 0) );

		// 添加ＩＤ和密码输入框
		panel.add( BorderLayout.NORTH, createIdPwdPane() );

		// 创建一个错误提示狂的标签
		this.mess = new JLabel( "", JLabel.CENTER );		// 赋值
		mess.setForeground(Color.red);		// 文字颜色为红色
		panel.add( BorderLayout.CENTER, mess );


		return panel;
	}

	/**
	 * @brife 对createIdPane()和createPwdPane()的封装
	 * @return JPanel 对象
	 */
	private JPanel createIdPwdPane() {
		JPanel panel = new JPanel( new GridLayout(2,1,0,6) ); // 可以划分画布为几行几列de布局

		// 这里的添加没有指定位置，按顺序，依次来添加
		panel.add( createIdPane() );		// 在第一行
		panel.add( createPwdPane() );		// 在第二行

		return panel;
	}

	private JPanel createIdPane() {
		JPanel panel = new JPanel( new BorderLayout(6,0) );
		panel.add( BorderLayout.WEST, new JLabel("编号：　") );
		// 创建文本输入框
		this.idField = new JTextField();	// 赋值

		panel.add( BorderLayout.CENTER, idField );		// id

		return panel;
	}

	private JPanel createPwdPane() {
		JPanel panel = new JPanel( new BorderLayout(6,0) );
		panel.add( BorderLayout.WEST, new JLabel("密码：　") );

		this.pwdField = new JPasswordField();	// 赋值

		panel.add( BorderLayout.CENTER, pwdField );		// password


		return panel;
	}





}