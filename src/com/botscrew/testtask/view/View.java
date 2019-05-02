package com.botscrew.testtask.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import com.botscrew.testtask.model.Model;
import com.botscrew.testtask.util.Observer;
import com.botscrew.testtask.util.Updatable;

public class View extends JPanel implements Observer {
	static final int WIDTH = 600;
	static final int HEIGHT = 450;
	private Model model;
	private JPanel authenticationPanel;
	private WorkPanel workPanel;
	public View(Model model) {
		this.model = model;
		this.model.registerObserver(this);
		try {
			EventQueue.invokeAndWait(
				() -> initSwing()
			);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
	}
	@Override
	public void update(ResultSet resultSet) {
		((Updatable) this.workPanel.getTabbedPane().getSelectedComponent()).post(resultSet);
	}
	private void initSwing() {
		this.workPanel = new WorkPanel(this.model);
		this.add(workPanel);
		JFrame jf = new JFrame("TestTask");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add(this, BorderLayout.CENTER);
		jf.pack();
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
	}
	static class MyFormField extends JPanel {
		JTextField textField;
		MyFormField(String labelText, boolean isPassword) {
			JLabel label = new JLabel();
			label.setText(labelText);
			this.textField = isPassword ? new JPasswordField(20) : new JTextField(20);
			super.add(label);
			super.add(this.textField);
		}
		String getEnteredText() {
			return textField.getClass() != JPasswordField.class
				? textField.getText() 
				: new String(
					((JPasswordField) textField).getPassword()
			);
		}
		void flush() {
			this.textField.setText("");
		}
	}
	private static class WorkPanel extends JPanel {
		private JTabbedPane tabbedPane;
		WorkPanel(Model model) {
			this.setLayout(new GridLayout(1, 1));
        	this.tabbedPane = new JTabbedPane();
        	JPanel panel1 = makeQuestion1Panel(model);
        	tabbedPane.addTab("Question 1", panel1);
        	tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        	JPanel panel2 = makeQuestion2Panel(model);
        	tabbedPane.addTab("Question 2", panel2);
        	tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
        	JPanel panel3 = makeQuestion3Panel(model);
        	tabbedPane.addTab("Question 3", panel3);
        	tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
        	JPanel panel4 = makeQuestion4Panel(model);
        	tabbedPane.addTab("Question 4", panel4);
        	tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);
        	JPanel panel5 = makeQuestion5Panel(model);
        	tabbedPane.addTab("Question 5", panel5);
        	tabbedPane.setMnemonicAt(4, KeyEvent.VK_5);
        	this.add(tabbedPane);  
        	tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		}
		JTabbedPane getTabbedPane() {
			return this.tabbedPane;
		}
    	private JPanel makeQuestion1Panel(Model model) {
    		return new Question1Panel(model);
    	}
    	private JPanel makeQuestion2Panel(Model model) {
    		return new Question2Panel(model);
    	}
    	private JPanel makeQuestion3Panel(Model model) {
    		return new Question3Panel(model);
    	}
    	private JPanel makeQuestion4Panel(Model model) {
    		return new Question4Panel(model);
    	}
    	private JPanel makeQuestion5Panel(Model model) {
    		return new Question5Panel(model);
    	}
		@Override
		public Dimension getPreferredSize() {
			return new Dimension(View.WIDTH, View.HEIGHT);
		}
	}
}