package jw3m.client.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

public class PanelEdit extends JPanel implements ActionListener
{
	
	private SkillsClient baseFrame;
	private JLabel lblEditProfile;
	private JLabel label_1;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_7;
	private JLabel label_8;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JButton btnUpdateProfile;
	
	public PanelEdit(SkillsClient frame) {
		setLayout(null);
		
		lblEditProfile = new JLabel("Edit Profile");
		lblEditProfile.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 22));
		lblEditProfile.setBounds(335, 13, 114, 16);
		add(lblEditProfile);
		
		label_1 = new JLabel("Email");
		label_1.setBounds(39, 228, 56, 16);
		add(label_1);
		
		label_3 = new JLabel("Mobile");
		label_3.setBounds(39, 275, 56, 16);
		add(label_3);
		
		label_4 = new JLabel("Surname");
		label_4.setBounds(39, 176, 56, 16);
		add(label_4);
		
		label_7 = new JLabel("Name");
		label_7.setBounds(39, 123, 56, 16);
		add(label_7);
		
		label_8 = new JLabel("User ID");
		label_8.setBounds(39, 72, 56, 16);
		add(label_8);
		
		textField = new JTextField();
		textField.setBounds(117, 69, 116, 22);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(117, 120, 116, 22);
		add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(117, 173, 116, 22);
		add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(117, 225, 116, 22);
		add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(117, 272, 116, 22);
		add(textField_4);
		textField_4.setColumns(10);
		
		btnUpdateProfile = new JButton("Update Profile");
		btnUpdateProfile.setBounds(119, 330, 114, 25);
		add(btnUpdateProfile);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		
		if(source == btnUpdateProfile)
		{
			JOptionPane.showMessageDialog(this, "Well done on updating your profile");
		}
		
	}
}
