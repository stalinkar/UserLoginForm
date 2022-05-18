package com.userlogin.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.userlogin.service.ReadWriteJSONService;
import com.userlogin.service.TokenService;

public class Login {

	private JFrame frame;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JTextField txtLoginToken;
	
	private String strToken;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setFrame(new JFrame());
		getFrame().setBounds(100, 100, 450, 300);
		getFrame().setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		JPanel loginPanel = new JPanel();
		loginPanel.setLayout(null);
		getFrame().getContentPane().add(loginPanel, BorderLayout.CENTER);
		
		JLabel lblLoginUsername = new JLabel("Username: ");
		lblLoginUsername.setBounds(94, 38, 110, 16);
		loginPanel.add(lblLoginUsername);
		
		JLabel lblLoginPassword = new JLabel("Password:");
		lblLoginPassword.setBounds(94, 82, 86, 16);
		loginPanel.add(lblLoginPassword);
		
		txtUsername = new JTextField();
		txtUsername.setColumns(10);
		txtUsername.setBounds(216, 35, 116, 22);
		loginPanel.add(txtUsername);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(216, 79, 116, 22);
		loginPanel.add(txtPassword);
		
		JButton btnLoginGetToken = new JButton("Get Token");
		btnLoginGetToken.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtPassword.getText().equals(new ReadWriteJSONService().getPasswordFromFile(txtUsername.getText()))) {
					strToken = new TokenService().getToken();
					txtLoginToken.setText(strToken);
				} else {
					JOptionPane.showMessageDialog(frame, "Invalid Username or Password");
				};
			}
		});
		btnLoginGetToken.setBounds(166, 111, 97, 25);
		loginPanel.add(btnLoginGetToken);
		
		JLabel lblLoginToken = new JLabel("Token: ");
		lblLoginToken.setBounds(94, 152, 110, 16);
		loginPanel.add(lblLoginToken);
		
		txtLoginToken = new JTextField();
		txtLoginToken.setColumns(10);
		txtLoginToken.setBounds(216, 152, 116, 22);
		loginPanel.add(txtLoginToken);
		
		JButton btnLoginLogin = new JButton("Login");
		btnLoginLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtLoginToken.getText().equals(strToken)) {
					JOptionPane.showMessageDialog(frame, "Logged In successfully");
				} else {
					JOptionPane.showMessageDialog(frame, "Invlid Token");
				}
			}
		});
		btnLoginLogin.setBounds(167, 199, 97, 25);
		loginPanel.add(btnLoginLogin);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

}
