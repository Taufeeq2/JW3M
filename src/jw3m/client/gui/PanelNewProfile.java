package jw3m.client.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;
import javax.swing.JTextField;

import jw3m.beans.User;

import javax.swing.JPasswordField;
import javax.swing.JButton;

public class PanelNewProfile extends JPanel implements ActionListener
{

	/**
	 * Create the panel.
	 */
	private SkillsClient baseFrame;
	private JLabel lblCreateMyProfile;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel lblPassword;
	private JTextField userID;
	private JTextField name;
	private JTextField surname;
	private JPasswordField passwordField;
	private JTextField email;
	private JTextField mobile;
	private JButton btnRegister;
	private JLabel lblAlias;
	private JTextField alias;
	
	public PanelNewProfile(SkillsClient frame)
	{
		this.baseFrame = frame;
		
		System.out.println("new profile panel started");
		
		setLayout(null);
		
		lblCreateMyProfile = new JLabel("Create My Profile");
		lblCreateMyProfile.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 22));
		lblCreateMyProfile.setBounds(356, 27, 187, 34);
		add(lblCreateMyProfile);
		
		label = new JLabel("User ID");
		label.setBounds(47, 80, 56, 16);
		add(label);
		
		label_1 = new JLabel("Name");
		label_1.setBounds(47, 122, 56, 16);
		add(label_1);
		
		label_2 = new JLabel("Surname");
		label_2.setBounds(47, 157, 56, 16);
		add(label_2);
		
		label_3 = new JLabel("Email");
		label_3.setBounds(47, 269, 56, 16);
		add(label_3);
		
		label_4 = new JLabel("Mobile");
		label_4.setBounds(47, 313, 56, 16);
		add(label_4);
		
		lblPassword = new JLabel("Password");
		lblPassword.setBounds(47, 229, 56, 16);
		add(lblPassword);
		
		userID = new JTextField();
		userID.setBounds(128, 77, 161, 22);
		add(userID);
		userID.setColumns(10);
		
		name = new JTextField();
		name.setBounds(128, 119, 161, 22);
		add(name);
		name.setColumns(10);
		
		surname = new JTextField();
		surname.setBounds(128, 154, 161, 22);
		add(surname);
		surname.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(128, 226, 161, 22);
		add(passwordField);
		
		email = new JTextField();
		email.setBounds(128, 266, 161, 22);
		add(email);
		email.setColumns(10);
		
		mobile = new JTextField();
		mobile.setBounds(128, 307, 161, 22);
		add(mobile);
		mobile.setColumns(10);
		
		btnRegister = new JButton("Register");
		btnRegister.setBounds(153, 351, 97, 25);
		add(btnRegister);
		btnRegister.addActionListener(this);
		
		lblAlias = new JLabel("Alias");
		lblAlias.setBounds(47, 199, 56, 16);
		add(lblAlias);
		
		alias = new JTextField();
		alias.setColumns(10);
		alias.setBounds(128, 189, 161, 22);
		add(alias);
	//	baseFrame = frame;
		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		User newUser = new User();
				
		if(source == btnRegister)
		{
			newUser.setUserName(userID.getText());	
			newUser.setFirstName(name.getText());
			newUser.setSurname(surname.getText());
			newUser.setAlias(alias.getText());
			newUser.setPassword(passwordField.getText());
			newUser.setEmailAddress(email.getText());
			newUser.setMobile(Integer.parseInt(mobile.getText()));
			
			
//			baseFrame.dao.addUserList(inUser);
		}
		
	}
}
