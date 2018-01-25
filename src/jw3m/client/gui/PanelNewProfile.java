package jw3m.client.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import jw3m.beans.User;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JFrame;

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
		
//		System.out.println("new profile panel started");
		
		setLayout(null);
		
		lblCreateMyProfile = new JLabel("Create My Profile");
		lblCreateMyProfile.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 22));
		lblCreateMyProfile.setBounds(356, 27, 187, 34);
		add(lblCreateMyProfile);
		
		label = new JLabel("User ID");
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		label.setBounds(47, 80, 77, 16);
		add(label);
		
		label_1 = new JLabel("Name");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_1.setBounds(47, 122, 56, 16);
		add(label_1);
		
		label_2 = new JLabel("Surname");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_2.setBounds(47, 167, 77, 16);
		add(label_2);
		
		label_3 = new JLabel("Email");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_3.setBounds(47, 309, 56, 16);
		add(label_3);
		
		label_4 = new JLabel("Mobile");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_4.setBounds(47, 355, 56, 16);
		add(label_4);
		
		lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPassword.setBounds(47, 256, 90, 16);
		add(lblPassword);
		
		userID = new JTextField();
		userID.setFont(new Font("Tahoma", Font.BOLD, 16));
		userID.setBounds(173, 77, 161, 22);
		add(userID);
		userID.setColumns(10);
		
		name = new JTextField();
		name.setFont(new Font("Tahoma", Font.BOLD, 16));
		name.setBounds(173, 119, 161, 22);
		add(name);
		name.setColumns(10);
		
		surname = new JTextField();
		surname.setFont(new Font("Tahoma", Font.BOLD, 16));
		surname.setBounds(173, 164, 161, 22);
		add(surname);
		surname.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.BOLD, 16));
		passwordField.setBounds(173, 256, 161, 22);
		add(passwordField);
		
		email = new JTextField();
		email.setFont(new Font("Tahoma", Font.BOLD, 16));
		email.setBounds(173, 306, 161, 22);
		add(email);
		email.setColumns(10);
		
		mobile = new JTextField();
		mobile.setFont(new Font("Tahoma", Font.BOLD, 16));
		mobile.setBounds(173, 352, 161, 22);
		add(mobile);
		mobile.setColumns(10);
		
		btnRegister = new JButton("Register");
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnRegister.setBounds(193, 406, 126, 25);
		add(btnRegister);
		btnRegister.addActionListener(this);
		
		lblAlias = new JLabel("Alias");
		lblAlias.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAlias.setBounds(47, 210, 56, 16);
		add(lblAlias);
		
		alias = new JTextField();
		alias.setFont(new Font("Tahoma", Font.BOLD, 16));
		alias.setColumns(10);
		alias.setBounds(173, 207, 161, 22);
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
			
			JOptionPane.showMessageDialog(this, "New user " + name.getText() + " created" );
			
			
			
		
			baseFrame.setNetAddUser(newUser);
			
		//	baseFrame.dao.addUserList(newUser);
			
			SwingUtilities.windowForComponent(this).dispose();
			
			
		//	this.getParent();
			
			
//			WindowEvent winEvent = new WindowEvent(parent, WindowEvent.WINDOW_CLOSING);
//		    Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winEvent );
			
//			PanelLogin logonP = new PanelLogin(baseFrame);
			
				
//				baseFrame.getData();
				
//				JFrame tempFrame = new JFrame();
//				
//				tempFrame.add(logonP);
//				tempFrame.setSize(800,600);
//				tempFrame.setVisible(true);
//				tempFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			
		}
		
	}
}
