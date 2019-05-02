package com.botscrew.testtask.view;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;

import com.botscrew.testtask.model.Model;
import com.botscrew.testtask.util.Updatable;
import com.botscrew.testtask.util.SQLFormer;

public class Question1Panel extends JPanel implements Updatable {
	private Model model;
	private AnswerPanel answerPanel;
	private String departmentName;
	Question1Panel(Model model) {
		this.setLayout(new GridLayout(2, 1));
		this.answerPanel = new AnswerPanel();
		this.add(new InquiryPanel(model, this));
		this.add(answerPanel);
	}
	void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	String getDepartmentName() {
		return this.departmentName;
	}
	private static class InquiryPanel extends JPanel {
		static final String question;
		static final String sqlTemplate;
		static {
			question = "Who is the head of department";
			sqlTemplate = "SELECT first_name, last_name "
						+ "FROM lector "
						+ "WHERE lector_id = ("
						   	+ "SELECT head_id "
						   	+ "FROM department "
						   	+ "WHERE department_name = '?'"
						+ ");";
		}
		private Model model;
		private JTextField textField;
		InquiryPanel(Model model, Question1Panel q1p) {
			this.model = model;
			JLabel label = new JLabel();
			label.setText(InquiryPanel.question);
			this.textField = new JTextField(20);
			JButton button = new JButton("Query");
			button.addActionListener(
				e -> {
					q1p.setDepartmentName(this.textField.getText());
					this.textField.setText("");
					String sql = SQLFormer.formSQL(
						this.sqlTemplate,
						q1p.getDepartmentName()
					);
					//System.out.println(sql);
					this.model.executeQuery(sql);		
			});
			this.add(label);
			this.add(textField);
			this.add(button);
			this.setBorder(
				new CompoundBorder(
					new BevelBorder(BevelBorder.RAISED),
					new EtchedBorder()
				)
			);
		}
	}
	private static class AnswerPanel extends JPanel {
		private JLabel label;
		AnswerPanel() {
			this.label = new JLabel();
			this.label.setText("Answer:");
			this.add(label);
			this.setBorder(
				new CompoundBorder(
					new BevelBorder(BevelBorder.RAISED),
					new EtchedBorder()
				)
			);
		}
		void setAnswer(String... params) {
			String answer = "<html>Answer:<br><br>Head of " 
				+ params[0] + " department is " 
				+ params[1] + " " + params[2] + "</html>";
			this.label.setText(answer);
		}
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
		}
	}
	@Override
	public void post(ResultSet resultSet) {
		String firstName = "UNDEFINED";
		String lastName = "UNDEFINED";
		try {
			if (resultSet.next()) {
				firstName = resultSet.getString(1);
				lastName = resultSet.getString(2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
			} catch (Exception e) {
				System.out.println(e.getCause());
			}
		}
		this.answerPanel.setAnswer(
			this.getDepartmentName(),
			firstName,
			lastName
		);
		this.answerPanel.repaint();
	}
}