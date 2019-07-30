package com.ahut.qian.ui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * 用户注册界面
 */
public class SignupFrame extends JFrame {

	public ClientContent clientContent;

	//JTextField idFiled;		// TODO: ID 如何由系统自动生成?
	public JTextField nameField;
	public JPasswordField pwdField;
	public JTextField phoneField;
	public JTextField emailField;

	public static void main(String[] args) {

	}

	public SignupFrame() {
		init();
	}

	private void init() {
		this.setContentPane( createContentPane() );
		this.setTitle("用户注册");
		this.setSize(300,200);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(false);
	}

	/**
	 * @brife 创建一张画布
	 * @return JPanel 对象
	 */
	private JPanel createContentPane() {
		JPanel panel = new JPanel( new BorderLayout());
		panel.setBorder(new EmptyBorder(8, 8, 8, 8));
		panel.add(BorderLayout.NORTH, new JLabel("考生注册", JLabel.CENTER));
		panel.add(BorderLayout.CENTER, createCenterPane());		// 中央组件
		panel.add(BorderLayout.SOUTH, createBtmPane());		// 按钮组件
		return panel;
	}

	// 放输入框用
	private JPanel createCenterPane() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder( new EmptyBorder(8, 0, 0, 0) );
		panel.add( BorderLayout.NORTH, createInputBoxPane() );		// 输入区
		return panel;
	}

	private JPanel createBtmPane() {
		JPanel panel = new JPanel(new FlowLayout());
		JButton confirm = new JButton("确认");
		JButton cancle = new JButton("取消");

		confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO: 注册相关处理


			}
		});

		cancle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO: 关闭注册页面

			}
		});

		panel.add(confirm);
		panel.add(cancle);

		return panel;
	}

	private JPanel createInputBoxPane() {
		JPanel panel = new JPanel(new GridLayout(4,1,0,6));

		this.nameField = new JTextField();
		this.pwdField = new JPasswordField();
		this.phoneField = new JTextField();
		this.emailField = new JTextField();

		panel.add(BorderLayout.WEST, new JLabel("姓名: "));
		panel.add(BorderLayout.CENTER, nameField);
		panel.add(BorderLayout.WEST, new JLabel("密码: "));
		panel.add(BorderLayout.CENTER, pwdField);
		panel.add(BorderLayout.WEST, new JLabel("电话: "));
		panel.add(BorderLayout.CENTER, phoneField);
		panel.add(BorderLayout.WEST, new JLabel("邮箱: "));
		panel.add(BorderLayout.CENTER, emailField);

		return panel;
	}



}
