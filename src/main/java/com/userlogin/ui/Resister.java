package com.userlogin.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.userlogin.service.ReadWriteJSONService;

import javax.swing.JPasswordField;
import javax.swing.JButton;

public class Resister {

	private JFrame frame;
	private JTextField txtName;
	private JTextField txtUsername;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Resister window = new Resister();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Resister() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setFrame(new JFrame());
		getFrame().setBounds(100, 100, 450, 300);
		getFrame().setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		JPanel resisterPanel = new JPanel();
		resisterPanel.setLayout(null);
		getFrame().getContentPane().add(resisterPanel, BorderLayout.CENTER);
		
		JLabel lblUsername = new JLabel("Username: ");
		lblUsername.setBounds(94, 88, 110, 16);
		resisterPanel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(94, 138, 86, 16);
		resisterPanel.add(lblPassword);
		
		JLabel lblToken = new JLabel("Name");
		lblToken.setBounds(94, 39, 56, 16);
		resisterPanel.add(lblToken);
		
		txtUsername = new JTextField();
		txtUsername.setColumns(10);
		txtUsername.setBounds(216, 85, 116, 22);
		resisterPanel.add(txtUsername);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(216, 36, 116, 22);
		resisterPanel.add(txtName);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(216, 135, 116, 22);
		resisterPanel.add(passwordField);
		
		JButton btnResisterUser = new JButton("Resister");
		btnResisterUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ReadWriteJSONService().WriteJSONToFile(txtName.getText(), txtUsername.getText(), passwordField.getText());
				frame.setVisible(false);
			}
		});
		btnResisterUser.setBounds(161, 214, 97, 25);
		resisterPanel.add(btnResisterUser);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

}
