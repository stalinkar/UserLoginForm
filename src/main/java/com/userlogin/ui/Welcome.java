package com.userlogin.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

class Welcome {

	private JFrame frame;
	private JTextField txtLoginToken;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Welcome window = new Welcome();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Welcome() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel welcomePanel = new JPanel();
		frame.getContentPane().add(welcomePanel, BorderLayout.CENTER);
		welcomePanel.setLayout(null);

		JLabel lblLoginToken = new JLabel("Token");
		lblLoginToken.setBounds(94, 172, 56, 16);

		txtLoginToken = new JTextField();
		txtLoginToken.setColumns(10);
		txtLoginToken.setBounds(216, 169, 116, 22);

		JButton btnLoginLogin = new JButton("Login");
		btnLoginLogin.setBounds(167, 215, 97, 25);


		JButton btnResister = new JButton("Resister");
		btnResister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//frame.setVisible(false);
				new Resister().getFrame().setVisible(true);
			}
		});
		btnResister.setBounds(167, 47, 97, 25);
		welcomePanel.add(btnResister);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//frame.setVisible(false);
				new Login().getFrame().setVisible(true);
			}
		});
		btnLogin.setBounds(167, 94, 97, 25);
		welcomePanel.add(btnLogin);
		welcomePanel.setVisible(true);
	}
}
