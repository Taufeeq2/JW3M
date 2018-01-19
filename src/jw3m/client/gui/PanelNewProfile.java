package jw3m.client.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;
import javax.swing.JTextField;
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
	private JLabel label_5;
	private JLabel lblPassword;
	private JRadioButton radioButton;
	private JRadioButton radioButton_1;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField;
	private JTextField textField_3;
	private JTextField textField_4;
	private JButton btnRegister;
	
	public PanelNewProfile(SkillsClient frame)
	{
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
		label_3.setBounds(47, 237, 56, 16);
		add(label_3);
		
		label_4 = new JLabel("Mobile");
		label_4.setBounds(47, 276, 56, 16);
		add(label_4);
		
		label_5 = new JLabel("Mentor");
		label_5.setBounds(47, 323, 56, 16);
		add(label_5);
		
		lblPassword = new JLabel("Password");
		lblPassword.setBounds(47, 197, 56, 16);
		add(lblPassword);
		
		radioButton = new JRadioButton("Yes");
		radioButton.setBounds(128, 319, 56, 25);
		add(radioButton);
		
		radioButton_1 = new JRadioButton("No");
		radioButton_1.setBounds(197, 319, 56, 25);
		add(radioButton_1);
		
		textField = new JTextField();
		textField.setBounds(128, 77, 161, 22);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(128, 119, 161, 22);
		add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(128, 154, 161, 22);
		add(textField_2);
		textField_2.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(128, 194, 161, 22);
		add(passwordField);
		
		textField_3 = new JTextField();
		textField_3.setBounds(128, 234, 161, 22);
		add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(128, 273, 161, 22);
		add(textField_4);
		textField_4.setColumns(10);
		
		btnRegister = new JButton("Register");
		btnRegister.setBounds(147, 364, 97, 25);
		add(btnRegister);
		baseFrame = frame;

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
				
		
	}
}
