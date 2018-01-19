package jw3m.client.gui;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.LayoutStyle.ComponentPlacement;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import java.awt.Panel;
import java.awt.Label;

public class PanelLogin extends JPanel implements ActionListener
{
	final static Logger logger = Logger.getLogger(PanelLogin.class);
	
	private JLabel labelUserID;
	private JTextField textFieldUserID;
	private JPasswordField passwordField;
	private JLabel labelPassword;
	private JButton buttonSubmit;
	private JButton buttonCancel;
	private JButton buttonChangePassword;
	private JPanel basePanel;
	private SkillsClient baseFrame;
	
	private JPanel sPanel ;
	private JLabel sPanelText;
	private JLabel lblStandardBank;
	private JLabel labelServer;
	private JTextField textFieldServer;
	private JLabel labelPort;
	private JTextField textFieldPort;
	private JButton buttonConnect;
	private JLabel labelConnectStatus;
	private JTextField textFieldConnectStatus;
	private JButton btnRegister;
	
	private PanelNewProfile newProfile;

	/**
	 * Create the panel.
	 */
	public PanelLogin(SkillsClient frame)
	{
		PropertyConfigurator.configure("log4j.properties");
		this.baseFrame = frame;
		
		try
		{
			baseFrame.removeMenuBar();
			baseFrame.hideSouthPanel();
		}
		catch (NullPointerException e)
		{
			// Do nothing 
			
		}
		
		
		labelUserID = new JLabel("User ID");
		labelUserID.setFont(baseFrame.standardFont);
		
		textFieldUserID = new JTextField();
		textFieldUserID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldUserID.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		labelPassword = new JLabel("Password");
		labelPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		buttonSubmit = new JButton("Submit");
		buttonSubmit.addActionListener(this);
		
		buttonCancel = new JButton("Cancel");
		buttonCancel.addActionListener(this);
		
		buttonChangePassword = new JButton("Change Password");
		buttonChangePassword.addActionListener(this);
		
		lblStandardBank = new JLabel("Standard Bank Skills Matrix");
		lblStandardBank.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 22));
		
		labelServer = new JLabel("Server");
		labelServer.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		textFieldServer = new JTextField();
		textFieldServer.setText("local");
		textFieldServer.setColumns(10);
		
		labelPort = new JLabel("Port");
		labelPort.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		textFieldPort = new JTextField();
		textFieldPort.setText("1337");
		textFieldPort.setColumns(10);
		
		buttonConnect = new JButton("Connect");
		buttonConnect.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		labelConnectStatus = new JLabel("Connect Status");
		labelConnectStatus.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		textFieldConnectStatus = new JTextField();
		textFieldConnectStatus.setColumns(10);
		
		btnRegister = new JButton("Register");
		btnRegister.addActionListener(this);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(260)
							.addComponent(lblStandardBank))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(175)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(labelUserID, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
									.addGap(78)
									.addComponent(textFieldUserID, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(labelPassword, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
									.addGap(56)
									.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(buttonSubmit, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(buttonCancel, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(buttonChangePassword, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(134)
									.addComponent(textFieldConnectStatus)))
							.addGap(18)
							.addComponent(btnRegister))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(170)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(199)
											.addComponent(labelPort)
											.addGap(18)
											.addComponent(textFieldPort, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(labelServer)
											.addGap(18)
											.addComponent(textFieldServer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
									.addGap(12)
									.addComponent(buttonConnect))
								.addComponent(labelConnectStatus))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblStandardBank)
					.addGap(101)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(labelUserID, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
						.addComponent(textFieldUserID, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(32)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(labelPassword, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(39)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(buttonCancel)
							.addComponent(buttonChangePassword)
							.addComponent(btnRegister))
						.addComponent(buttonSubmit))
					.addGap(97)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelServer)
						.addComponent(textFieldServer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelPort)
						.addComponent(textFieldPort, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonConnect))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelConnectStatus)
						.addComponent(textFieldConnectStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		
		if (source == buttonSubmit)
		{
			logger.info("loggon submit clicked");
			Boolean logonSuccess= false;
			// add logic to validate user
			
			String passwordString = new String (passwordField.getPassword() );
			
			String userName = new String (textFieldUserID.getText());
			
			if (baseFrame.dao.getUser(userName) != null)
			{
				logger.info("Found user " + userName);
				
				if (baseFrame.dao.getUser(userName).getPassword().equals(passwordString))
				{
					// clearly bad logic here were the client has the user object with password but ya for now.
					logger.info("Passwords match " + userName);
					
					// also bad to accesss the public object directly should be using business logic access methods
					baseFrame.authenticatedUser = baseFrame.dao.getUser(userName);
					baseFrame.getData(); // updates the local data variables;
					
					// example of how to get the data
					//baseFrame.data_hobbyList[1];
					
					
					
					baseFrame.setupMenuBar();
					baseFrame.setupSouthPanel();
					baseFrame.setupTabs();
					baseFrame.changeToTabbedPane();
					
				}
				else
				{
					JOptionPane.showMessageDialog(this, "Bad password");
				//	textFieldUserID.setText("");
					passwordField.setText("");
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this, "No such user!");
			}

			
			
		}
		
		if (source == buttonCancel)
		{
			textFieldUserID.setText("");
			passwordField.setText("");
			
			logger.debug("Login cancelled");
			
		}
		
		if(source == btnRegister)
		{
			newProfile = new PanelNewProfile(baseFrame);
			
		//	baseFrame.add(newProfile);
			
			baseFrame.getData();
			
			JFrame tempFrame = new JFrame();
			
			tempFrame.add(newProfile);
			tempFrame.setSize(800,600);
			tempFrame.setVisible(true);
			tempFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			
			
			
//			this.add(basePanel, BorderLayout.CENTER);
//			this.baseFrame.setVisible(true);
//			
//			
//			this.baseFrame.removeAll();
//			this.baseFrame.validate();
//			this.baseFrame.repaint();
//			this.baseFrame.add(newProfile,BorderLayout.CENTER);
//			this.baseFrame.validate();
//			this.baseFrame.repaint();
			
		//	this.baseFrame.setVisible(true);
            
            
		}
		if (source == buttonChangePassword)
		{
//			String userName = JOptionPane.showInputDialog(this,"Username");
//			
//			if (baseFrame.dao.getUser(userName) != null)
//			{
//				logger.info("Found user " + userName);
//			}
//			else
//			{
//				JOptionPane.showConfirmDialog(this, "No such user!");
//			}
			
//			if (mainFrame.getUserCatalog().userExists(username) )
//			{
//			//	User user = mainFrame.getUserCatalog().findUser(username);
//				
//				String oldpassword = JOptionPane.showInputDialog(this,"Old password");
//				
//				if (oldpassword.equals (mainFrame.getUserCatalog().findUser(username).getUserPassword() ))
//				{
//					// passwords match
//					
//					String newpw1 = JOptionPane.showInputDialog(this,"New password");
//					String newpw2 = JOptionPane.showInputDialog(this,"New password (confirm)");
//					
//					if (newpw1.equals(newpw2))
//					{
//
//							try
//							{
//								mainFrame.getUserCatalog().findUser(username).setUserPassword(newpw1);
//							} catch (UserException e1)
//							{
//								// TODO Auto-generated catch block
//								JOptionPane.showConfirmDialog(this, "New password does not meet complexity requirement");
//								e1.printStackTrace();
//							}
//	
//					}
//					else
//					{
//						JOptionPane.showConfirmDialog(this, "New password does not match");
//					}
//					
//				}
//				else
//				{
//					// invalid password
//					JOptionPane.showConfirmDialog(this, "New password does not match");
//				}
//				
//			
//				
//				
//			}
//			else
//			{
//				JOptionPane.showConfirmDialog(this, "No such user!");
//			}
//			
//			
		
			
		}
		
		
		
		// TODO Auto-generated method stub
		
	}
}
