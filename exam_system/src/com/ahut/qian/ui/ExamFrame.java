/**
 * date: 2019-7-16
 */

package com.ahut.qian.ui;

import com.ahut.qian.entity.ExamInfo;
import com.ahut.qian.entity.QuestionInfo;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * 考试界面
 */
public class ExamFrame extends JFrame {

	private JLabel examInfo;
	private JLabel questionCount;		//MOD
	private JTextArea questionArea;
	//private JLabel errorMess;	//ADD

	public JLabel timer;	//MOD
	public JButton next;
	public JButton pre;
	public ClientContent clientContent;

	public ExamFrame() {
		init();
	}

	private void init() {
		setTitle("在线测评系统");
		setSize(600, 380);
		setContentPane( createContentPane() );		// 方法的调用
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		//setVisible(true);
	}

	private JPanel createContentPane() {
		JPanel panel = new JPanel( new BorderLayout());
		panel.setBorder( new EmptyBorder(6,6,6,6));
		ImageIcon icon = new ImageIcon("exam_title.png");

		panel.add( BorderLayout.NORTH, new JLabel(icon) );
		panel.add( BorderLayout.CENTER ,createCenterPane() );		// 调用

		// 添加工具(计数器)控件
		panel.add( BorderLayout.SOUTH, createToolsPane() );


		return panel;
	}

	private JPanel createToolsPane() {
		JPanel panel = new JPanel( new BorderLayout() );

		questionCount = new JLabel("");
		timer = new JLabel("剩余时间: X分钟");
		panel.add( BorderLayout.WEST, questionCount );
		panel.add( BorderLayout.CENTER, createBtnPane() );		// 3个按钮
		panel.add( BorderLayout.EAST, timer );

		return panel;
	}

	// 左中右三个控件(其实五个)
	private JPanel createCenterPane() {
		JPanel panel = new JPanel( new BorderLayout() );
		examInfo = new JLabel( "姓名: xxx 考试: xxx ", JLabel.CENTER );

		panel.add( BorderLayout.NORTH, examInfo );

		panel.add( BorderLayout.CENTER, createQuestionPane() );		// 显示题目的文本狂

		// 添加选项
		panel.add( BorderLayout.SOUTH, createOptionPane() );

		return panel;
	}

	// 创建三个按钮
	private JPanel createBtnPane() {
		JPanel panel = new JPanel( new FlowLayout() );
		pre = new JButton("<上一题");
		next = new JButton("下一题>");
		JButton submit = new JButton("提交");

		pre.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clientContent.pre();
			}
		});

		next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clientContent.next();
			}
		});

		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clientContent.submit();
			}
		});
		panel.add(pre);
		panel.add(next);
		panel.add(submit);

		return panel;
	}

	private Option[] options = new Option[4];		// 用来存取选项的一个数组


	private JPanel createOptionPane() {
		JPanel panel = new JPanel();
		Option a = new Option(0, "A");
		Option b = new Option(1, "B");
		Option c = new Option(2, "C");
		Option d = new Option(3, "D");
		// 存入数组，备用
		options[0] = a;
		options[1] = b;
		options[2] = c;
		options[3] = d;
		// 添加到画布中
		panel.add(a);
		panel.add(b);
		panel.add(c);
		panel.add(d);

		return panel;
	}


	// 得到当前用户的选项
	public List<Integer> getUserAnswers() {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < options.length ; i++) {
			Option option = options[i];
			if ( option.isSelected() )
				list.add(option.value);
		}
		return list;
	}

	// 内部类, 用于a b c d 选项
	class Option extends JCheckBox {
		int value;		// 记录当前选项的值
		public Option( int val, String txt ) {
			super(txt);		// 调用JCheckBox 类的构造器
			this.value = val;
		}
	}

	// 创建一个带有滚动条的画布, 因为题目可能显示不下
	private JScrollPane createQuestionPane() {
		JScrollPane jsp = new JScrollPane();
		jsp.setBorder( new TitledBorder("题目") );		// 标题框, 区别与文本输入框
		questionArea = new JTextArea();
		//questionArea.setText("问题: \nA. \nB.");	// 为文本输入区域设置内容
		questionArea.setLineWrap(true);			// 允许换行显示, 这样会出现上下的滚动条
		questionArea.setEditable(false);		// 设置文本不可编辑

		// 把当前文本输入区域添加到滚动条所在的视图中
		jsp.getViewport().add( questionArea );

		return jsp;
	}

	/**
	 * 对外提供更新考试信息和考试题目到当前考试界面的方法
	 */
	public void updateView(ExamInfo ei, QuestionInfo qi) {
		// 更新考试窗口中的考试信息
		examInfo.setText(ei.toString());

		// 更新考试窗口中的考试题目, 加上一个题号
		questionArea.setText( qi.getQuestionIndex() + ". "+ qi.toString() );		//MOD

		updateQuestionNumber(ei.getQuestionCount(), qi.getQuestionIndex());
		updateButtons(ei.getQuestionCount(), qi.getQuestionIndex());

		// 更新选项, 点击上下一题时，清空选项框
		updateOptions(qi.getUserAnswers());
	}

	// 更新按钮状态
	private void updateButtons(int questioncount, int questionindex) {	// 老师的版本
		// 到最后一题时，设置下一题不可按
		boolean usable1 = questioncount != questionindex;
		next.setEnabled(usable1);
		// 第一题时，设置上一题不可用
		boolean usable2 = questioncount - 19 != questionindex;
		pre.setEnabled(usable2);
	}

	// 更新考试进度
	private void updateQuestionNumber(int questioncount, int questionindex) {
		String str = "进度: " + questionindex + " / " + questioncount;
		this.questionCount.setText(str);
	}

	private void updateOptions(List<Integer> userAnswers) {
		for (int i = 0; i < options.length; i++) {
			Option option = options[i];
			option.setSelected( userAnswers.contains(option.value) );
		}
	}

}
