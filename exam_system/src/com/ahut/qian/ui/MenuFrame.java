/**
 * date: 2019-7-16
 */


package com.ahut.qian.ui;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * 菜单界面
 */
public class MenuFrame extends JFrame {

	//public static void main(String[] args) {
	//	new MenuFrame();
	//}

	public JLabel info;		//ADD
	public ClientContent clientContent;

	public MenuFrame() {
		init();		// 调用init() 方法
	}

	private void init() {

		//System.out.println("MenuFrame中产生了窗口");
		setTitle("在线测评系统");
		setSize(600, 400);
		setLocationRelativeTo(null);

		// 设置默认关闭操作为关闭窗口，即退出
		//setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		// 为窗口添加内容
		setContentPane( createContentPane() );		// 合并上面两行代码, 对createContentPane() 的调用

		setResizable(false);
		//setVisible(true);

		addWindowListener(new WindowListener() {
			@Override
			public void windowOpened(WindowEvent e) {

			}

			@Override
			public void windowClosing(WindowEvent e) {
				int val = JOptionPane.showConfirmDialog(MenuFrame.this, "确认退出吗?",
						"友情提示", JOptionPane.YES_NO_OPTION);
				if ( val == JOptionPane.YES_OPTION )
					System.exit(0);
				if ( val == JOptionPane.NO_OPTION )
					return;
			}

			@Override
			public void windowClosed(WindowEvent e) {

			}

			@Override
			public void windowIconified(WindowEvent e) {

			}

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

	}

	private JPanel createContentPane() {
		JPanel panel = new JPanel( new BorderLayout());
		ImageIcon icon = new ImageIcon(
				//this.getClass().getResource("title.png")
				"title.png"
				);	// 创建图片控件

		// 北边添加一张图片
		panel.add( BorderLayout.NORTH, new JLabel(icon) );

		panel.add( BorderLayout.CENTER, createMenuPane() );
		panel.add( BorderLayout.SOUTH, new JLabel("版权所有，盗版必究", JLabel.RIGHT) );

		return panel;
	}

	private JPanel createMenuPane() {
		JPanel panel = new JPanel( new BorderLayout() );
		this.info = new JLabel("" , JLabel.CENTER);		//MOD
		panel.add( BorderLayout.NORTH, info );

		// 添加一个按钮控件
		panel.add( BorderLayout.CENTER, createBtnPane() );

		return panel;
	}

	private JPanel createBtnPane() {
		JPanel panel = new JPanel( new FlowLayout() );
		JButton start = createImgBtn("exam.png", "开始考试");
		JButton result = createImgBtn("result.png", "查询成绩");
		JButton msg = createImgBtn("message.png", "考试规则");
		JButton exit = createImgBtn("exit.png", "退出系统");

		// 开始考试
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clientContent.startExam();		// 开始考试
			}
		});

		// 查询功能
		result.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clientContent.queryScore();
			}
		});

		// 查看考试规则
		msg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clientContent.showRules(false);
			}
		});

		// 退出系统
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clientContent.exitConfirm();
			}
		});

		panel.add(start);
		panel.add(result);
		panel.add(msg);
		panel.add(exit);

		return panel;
	}

	// 创建带有图片和文字的按钮, 封装一个方法
	private JButton createImgBtn(String img, String text) {
		ImageIcon icon = new ImageIcon(img);
		JButton button = new JButton( text, icon );

		// 设置图片和文字之间的位置关系
		// 设置文本相对于图片的垂直位置
		button.setVerticalTextPosition( JButton.BOTTOM );
		// 设置文本相对于图片的水平位置
		button.setHorizontalTextPosition( JButton.CENTER );

		return button;
	}


}
