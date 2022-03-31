package com.login.System;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.Avnish_Sanket.EMS.Employee;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JPasswordField;

public class LoginSystem {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginSystem window = new LoginSystem();
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
	public LoginSystem() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.setBounds(200, 200, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblLoginSystem = new JLabel("Login System");
		lblLoginSystem.setBackground(Color.LIGHT_GRAY);
		lblLoginSystem.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblLoginSystem.setBounds(183, 0, 159, 33);
		frame.getContentPane().add(lblLoginSystem);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUsername.setBounds(35, 79, 112, 13);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPassword.setBounds(35, 130, 102, 13);
		frame.getContentPane().add(lblPassword);
		
		textField = new JTextField();
		textField.setBackground(SystemColor.inactiveCaptionBorder);
		textField.setBounds(187, 76, 227, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setText("");
		passwordField.setBounds(187, 129, 227, 19);
		frame.getContentPane().add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBackground(Color.LIGHT_GRAY);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			String password = passwordField.getText();
				String username=textField.getText();
				
				if(password.equals("1234") && username.equals("avnish")) {
				JOptionPane.showMessageDialog(null, "Login Successful");
				
				Employee.main(null);
				}
				else {
					JOptionPane.showMessageDialog(null, "Invalid username & password");
					
				}	
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnLogin.setBounds(198, 202, 85, 33);
		frame.getContentPane().add(btnLogin);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(20, 179, 456, 13);
		frame.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(20, 35, 456, 13);
		frame.getContentPane().add(separator_1);
		
		
	}
}
