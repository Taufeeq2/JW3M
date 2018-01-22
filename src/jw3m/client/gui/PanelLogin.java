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
	private JButton btnRegister;
	
	public JFrame tempFrame;
	private PanelNewProfile newProfile;
	private JLabel lblUserDetails;
	private JLabel lblConnectionStatus;

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
		textFieldServer.setText(baseFrame.getServerAddress());
		textFieldServer.setColumns(10);
		
		labelPort = new JLabel("Port");
		labelPort.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		textFieldPort = new JTextField();
		textFieldPort.setText(""+baseFrame.getServerPort());
		textFieldPort.setColumns(10);
		
		buttonConnect = new JButton("Connect");
		buttonConnect.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		labelConnectStatus = new JLabel("No connection");
		labelConnectStatus.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		btnRegister = new JButton("Register");
		btnRegister.addActionListener(this);
		
		lblUserDetails = new JLabel("User details");
		lblUserDetails.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		lblConnectionStatus = new JLabel("Connection status");
		lblConnectionStatus.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
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
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(labelUserID, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
									.addGap(78)
									.addComponent(textFieldUserID, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblUserDetails)
								.addComponent(lblConnectionStatus, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(labelConnectStatus, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
									.addGap(84))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(labelPassword, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
										.addComponent(labelServer))
									.addGap(53)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(passwordField, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)
										.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addComponent(buttonSubmit, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(buttonChangePassword))
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(btnRegister, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
												.addComponent(buttonCancel, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)))
										.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
											.addComponent(textFieldServer, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(buttonConnect, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
												.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
													.addComponent(labelPort)
													.addGap(18)
													.addComponent(textFieldPort, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)))))))))
					.addContainerGap(258, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblStandardBank)
					.addGap(58)
					.addComponent(lblUserDetails)
					.addGap(27)
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
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(buttonSubmit)
						.addComponent(buttonCancel))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRegister)
						.addComponent(buttonChangePassword))
					.addGap(64)
					.addComponent(lblConnectionStatus, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelServer)
						.addComponent(textFieldServer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelPort)
						.addComponent(textFieldPort, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelConnectStatus)
						.addComponent(buttonConnect))
					.addContainerGap(112, Short.MAX_VALUE))
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
			
			// testing client server comms
			
			baseFrame.setAuthenticatedUser( baseFrame.getNetworkClient().passCredentials(userName, passwordString)  );
			
			if (baseFrame.authenticatedUser!=null)
			{
				logger.info("User now authenticated");
				
				baseFrame.getData(); // updates the local data variables;
				baseFrame.setupMenuBar();
				baseFrame.setupSouthPanel();
				baseFrame.setupTabs();
				baseFrame.changeToTabbedPane();
			}
			else
			{
				logger.info("No user authenticated");
			}
			
			
//			if (baseFrame.dao.getUser(userName) != null)
//			{
//				logger.info("Found user " + userName);
//				
//				if (baseFrame.dao.getUser(userName).getPassword().equals(passwordString))
//				{
//					// clearly bad logic here were the client has the user object with password but ya for now.
//					logger.info("Passwords match " + userName);
//					
//					// also bad to accesss the public object directly should be using business logic access methods
//					baseFrame.authenticatedUser = baseFrame.dao.getUser(userName);
//					baseFrame.getData(); // updates the local data variables;
//					
//					// example of how to get the data
//					//baseFrame.data_hobbyList[1];
//					
//					
//					
//					baseFrame.setupMenuBar();
//					baseFrame.setupSouthPanel();
//					baseFrame.setupTabs();
//					baseFrame.changeToTabbedPane();
//					
//				}
//				else
//				{
//					JOptionPane.showMessageDialog(this, "Bad password");
//				//	textFieldUserID.setText("");
//					passwordField.setText("");
//				}
//			}
//			else
//			{
//				JOptionPane.showMessageDialog(this, "No such user!");
//			}

			
			
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
			
			 tempFrame = new JFrame();
			
			tempFrame.getContentPane().add(newProfile);
			tempFrame.setSize(800,600);
			tempFrame.setVisible(true);
			tempFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
	//		tempFrame.dispose();
			
			
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
