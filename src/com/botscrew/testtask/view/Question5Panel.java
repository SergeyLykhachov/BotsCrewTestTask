package com.botscrew.testtask.view;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;

import com.botscrew.testtask.model.Model;
import com.botscrew.testtask.util.Updatable;
import com.botscrew.testtask.util.SQLFormer;

public class Question5Panel extends JPanel implements Updatable {
	private Model model;
	private AnswerPanel answerPanel;
	private String template;
	Question5Panel(Model model) {
		this.answerPanel = new AnswerPanel();
		this.add(new InquiryPanel(model, this), BorderLayout.NORTH);
		this.add(answerPanel, BorderLayout.CENTER);
	}
	void setTemplate(String template) {
		this.template = template;
	}
	String getTemplate() {
		return this.template;
	}
	private static class InquiryPanel extends JPanel {
		static final String question;
		static final String sqlTemplate;
		static {
			question = "Global search by ";
			sqlTemplate = "SELECT first_name, last_name "
						+ "FROM lector "
						+ "WHERE first_name LIKE '%?%' OR last_name LIKE '%?%'";
		}
		private Model model;
		private JTextField textField;
		InquiryPanel(Model model, Question5Panel q5p) {
			this.model = model;
			JLabel label = new JLabel();
			label.setText(InquiryPanel.question);
			this.textField = new JTextField(20);
			JButton button = new JButton("Query");
			button.addActionListener(
				e -> {
					q5p.setTemplate(this.textField.getText());
					this.textField.setText("");
					String sql = SQLFormer.formSQL(
						this.sqlTemplate,
						q5p.getTemplate(),
						q5p.getTemplate()
					);
					System.out.println(sql);
					this.model.executeQuery(sql);		
			});
			this.add(label);
			this.add(textField);
			this.add(button);
		}
	}
	private static class AnswerPanel extends JPanel {
		private DefaultListModel<String> nameListModel;
		AnswerPanel() {
			JLabel label = new JLabel();
			label.setText("Answer:");
			this.add(label, BorderLayout.WEST);
			JPanel listContainer = new JPanel();
			JList<String> nameList = new JList<>();
			nameList.setFixedCellWidth(450);
			nameList.setFixedCellHeight(20);
			this.nameListModel = new DefaultListModel<>();
			nameList.setModel(nameListModel);
			listContainer.setLayout(new BorderLayout());
			listContainer.add(new JScrollPane(nameList), BorderLayout.CENTER);
			this.add(listContainer, BorderLayout.CENTER);
		}
		void setAnswer(List<String> list) {
			this.nameListModel.clear();
			list.forEach(this.nameListModel::addElement);
		}
	}
	@Override
	public void post(ResultSet resultSet) {
		List<String> list = new ArrayList<>();
		String name;
		try {
			while (resultSet.next()) {
				name = resultSet.getString(1);
				name += " ";
				name += resultSet.getString(2);
				list.add(name);
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
		this.answerPanel.setAnswer(list);
	}
}
