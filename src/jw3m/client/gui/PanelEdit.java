package jw3m.client.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.ButtonGroup;

public class PanelEdit extends JPanel implements ActionListener
{
	
	private SkillsClient baseFrame;
	private JLabel lblEditProfile;
	private JLabel label_1;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_7;
	private JTextField name;
	private JTextField surname;
	private JTextField email;
	private JTextField mobile;
	private JButton btnUpdateProfile;
	private JTextField alias;
	private JLabel lblAlias;
	private JLabel lblMentor;
	private JRadioButton rdbtnYes;
	private JRadioButton rdbtnNo;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	public PanelEdit(SkillsClient frame) {
		baseFrame = frame;
		
		setBackground(UIManager.getColor("Button.background"));
		setForeground(Color.LIGHT_GRAY);
		setLayout(null);
		
		lblEditProfile = new JLabel("Edit Profile");
		lblEditProfile.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 22));
		lblEditProfile.setBounds(335, 13, 114, 16);
		add(lblEditProfile);
		
		label_1 = new JLabel("Email");
		label_1.setBounds(39, 237, 56, 16);
		add(label_1);
		
		label_3 = new JLabel("Mobile");
		label_3.setBounds(39, 288, 56, 16);
		add(label_3);
		
		label_4 = new JLabel("Surname");
		label_4.setBounds(39, 132, 56, 16);
		add(label_4);
		
		label_7 = new JLabel("Name");
		label_7.setBounds(39, 79, 56, 16);
		add(label_7);
		
		name = new JTextField();
		name.setBounds(117, 76, 116, 22);
		add(name);
		name.setColumns(10);
		
		surname = new JTextField();
		surname.setBounds(117, 129, 116, 22);
		add(surname);
		surname.setColumns(10);
		
		email = new JTextField();
		email.setBounds(117, 234, 116, 22);
		add(email);
		email.setColumns(10);
		
		mobile = new JTextField();
		mobile.setBounds(117, 285, 116, 22);
		add(mobile);
		mobile.setColumns(10);
		
		btnUpdateProfile = new JButton("Update Profile");
		btnUpdateProfile.setBounds(119, 372, 114, 25);
		add(btnUpdateProfile);
		btnUpdateProfile.addActionListener(this);
		
		alias = new JTextField();
		alias.setColumns(10);
		alias.setBounds(117, 186, 116, 22);
		add(alias);
		
		lblAlias = new JLabel("Alias");
		lblAlias.setBounds(39, 189, 56, 16);
		add(lblAlias);
		
		lblMentor = new JLabel("Mentor");
		lblMentor.setBounds(39, 333, 56, 16);
		add(lblMentor);
		
		rdbtnYes = new JRadioButton("Yes");
		buttonGroup.add(rdbtnYes);
		rdbtnYes.setBounds(118, 329, 56, 25);
		add(rdbtnYes);
		
		rdbtnNo = new JRadioButton("No");
		buttonGroup.add(rdbtnNo);
		rdbtnNo.setBounds(191, 329, 56, 25);
		add(rdbtnNo);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		boolean mentor;
		
		if(source == btnUpdateProfile)
		{
			baseFrame.authenticatedUser.setFirstName(name.getText());
			baseFrame.authenticatedUser.setSurname(surname.getText());
			baseFrame.authenticatedUser.setAlias(alias.getText());
			baseFrame.authenticatedUser.setEmailAddress(email.getText());
			baseFrame.authenticatedUser.setMobile(Integer.parseInt(mobile.getText()));
			
			if(rdbtnYes.isSelected())
			{
				mentor = true;
			}
			else
			{
				mentor = false;
			}

			baseFrame.authenticatedUser.setMentor(mentor);
			
			baseFrame.dao.editUser(baseFrame.authenticatedUser);
			JOptionPane.showMessageDialog(this, "Well done on updating your profile"); 
		}
		
	}
}
