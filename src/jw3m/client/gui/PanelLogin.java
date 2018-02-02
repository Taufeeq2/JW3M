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
import java.util.Vector;
import java.awt.event.ActionEvent;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import java.awt.Panel;
import java.awt.Label;
import jw3m.beans.Comms;
import jw3m.beans.Hobby;
import jw3m.beans.Level;
import jw3m.beans.Notification;
import jw3m.beans.Rating;
import jw3m.beans.Skill;
import jw3m.beans.User;
import jw3m.beans.UserHobby;
import jw3m.beans.UserSkill;

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
	private JLabel labelConnectionStatusHeading;
	private Font primaryFont, secondaryFont;
	
	

	/**
	 * Create the panel.
	 */
	public PanelLogin(SkillsClient frame)
	{
		PropertyConfigurator.configure("log4j.properties");
		this.baseFrame = frame;
		primaryFont = baseFrame.getPrimaryFont();
		secondaryFont = baseFrame.getSecondaryFont();
		
		frame.setBounds(100,100,800,600);
		
		try
		{
			baseFrame.removeMenuBar();
			baseFrame.hideSouthPanel();
		}
		catch (NullPointerException e)
		{
			// Do nothing 
			
		}
		
		
		lblStandardBank = new JLabel("Standard Bank Skills Matrix");
		lblStandardBank.setFont(secondaryFont);
		
		labelUserID = new JLabel("User ID");
		labelUserID.setFont(primaryFont);
		
		textFieldUserID = new JTextField();
		textFieldUserID.setFont(primaryFont);
		textFieldUserID.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(primaryFont);
		passwordField.addActionListener(this);
		
		labelPassword = new JLabel("Password");
		labelPassword.setFont(primaryFont);
		labelPassword.setFont(primaryFont);
		
		buttonSubmit = new JButton("Submit");
		buttonSubmit.setFont(primaryFont);
		buttonSubmit.addActionListener(this);
		
		buttonCancel = new JButton("Cancel");
		buttonCancel.setFont(primaryFont);
		buttonCancel.addActionListener(this);
		
		buttonChangePassword = new JButton("Change Password");
		buttonChangePassword.setFont(primaryFont);
		buttonChangePassword.addActionListener(this);
		
		
		
		labelServer = new JLabel("Server");
		labelServer.setFont(primaryFont);
		
		
		textFieldServer = new JTextField();
		textFieldServer.setFont(primaryFont);
		textFieldServer.setText(baseFrame.getServerAddress());
		textFieldServer.setColumns(10);
		
		labelPort = new JLabel("Port");
		labelPort.setFont(primaryFont);
		labelPort.setFont(primaryFont);
		
		textFieldPort = new JTextField();
		textFieldPort.setFont(primaryFont);
		textFieldPort.setText(""+baseFrame.getServerPort());
		textFieldPort.setColumns(10);
		
		buttonConnect = new JButton("Connect");
		buttonConnect.setFont(primaryFont);
		buttonConnect.addActionListener(this);
		
		labelConnectStatus = new JLabel("No connection");
		labelConnectStatus.setFont(primaryFont);
		
		btnRegister = new JButton("Register");
		btnRegister.setFont(primaryFont);
		btnRegister.addActionListener(this);
		
		lblUserDetails = new JLabel("User details");
		lblUserDetails.setFont(primaryFont);
		
		labelConnectionStatusHeading = new JLabel("Connection status");
		labelConnectionStatusHeading.setFont(primaryFont);
		
		
		// this should try connect on start up
		buttonConnect.doClick();
		
	//	baseFrame.getNetworkClient().reconnect();
		
		
	
		
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
								.addComponent(lblUserDetails)
								.addComponent(labelConnectionStatusHeading, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(labelConnectStatus, GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
									.addGap(84))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(labelUserID, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(labelServer, Alignment.LEADING)
										.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
											.addComponent(labelPassword, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)))
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(53)
											.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)
												.addGroup(groupLayout.createSequentialGroup()
													.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
														.addComponent(buttonSubmit, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(buttonChangePassword))
													.addPreferredGap(ComponentPlacement.RELATED)
													.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(btnRegister, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
														.addComponent(buttonCancel, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)))
												.addGroup(groupLayout.createSequentialGroup()
													.addComponent(textFieldServer, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
													.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
														.addComponent(buttonConnect, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
														.addGroup(groupLayout.createSequentialGroup()
															.addComponent(labelPort)
															.addGap(18)
															.addComponent(textFieldPort, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))))))
										.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
											.addGap(56)
											.addComponent(textFieldUserID, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)))))))
					.addContainerGap(298, Short.MAX_VALUE))
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
						.addComponent(textFieldUserID, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(labelUserID, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)))
					.addGap(32)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addGap(28)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(buttonSubmit)
								.addComponent(buttonCancel))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnRegister)
								.addComponent(buttonChangePassword)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(labelPassword, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)))
					.addGap(64)
					.addComponent(labelConnectionStatusHeading, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
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
					.addContainerGap(150, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		Boolean okToAuth = false;
		
		if(source == passwordField)
		{
			buttonSubmit.doClick();
		}
		
		
		if (source == buttonSubmit)
		{
			logger.info("loggon submit clicked");
			
			// check if networkClass is avaliable
			
			//check if Network Class has been established
			
			baseFrame.getNetworkClient().reconnect();
			
			if (baseFrame.getNetworkClient() == null)
			{
				baseFrame.connectToServer();
				if (baseFrame.getNetworkSession())
				{
					logger.info("seems we had no comms but now we do");
					okToAuth = true;

				}
				else
				{
					logger.info("seems we had no comms and still have no comms");
					okToAuth = false;
				}
			}
			else
			{
				if (baseFrame.getNetworkClient().isConnected() )
				{
					logger.info("Right away we have comms so proceed to auth phase");
					okToAuth = true;
				}
				else
				{
					logger.info("no comms");
				}
				
			}
			
			
				
			if (okToAuth)
			{
				// ok we can go ahead
				
				String passwordString = new String (passwordField.getPassword() );
				String userName = new String (textFieldUserID.getText());
				
				// testing client server comms
				
				baseFrame.setAuthenticatedUser( baseFrame.getNetworkClient().passCredentials(userName, passwordString)  );
				
//				baseFrame.getData();
//				
//				// 3 temp comms packets for testing

				
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
					JOptionPane.showMessageDialog(this,"Password incorrect!");
					
					// we have to drop the session because the server will drop a bad auth and expect a new?
//					baseFrame.getNetworkClient().dropSession();
//					
					baseFrame.connectToServer();
					// probably need to drop the network client session
					
					
					
				}
				
				
			} // end of ok to auth	
			
		
			
		} // end of source logon button
		
		if (source == buttonCancel)
		{
			textFieldUserID.setText("");
			passwordField.setText("");
			
			logger.debug("Login cancelled");
			
		}
		
		if(source == btnRegister)
		{
			newProfile = new PanelNewProfile(baseFrame);
	//		baseFrame.getData();
			
			tempFrame = new JFrame();
			tempFrame.getContentPane().add(newProfile);
			tempFrame.setSize(800,600);
			tempFrame.setVisible(true);
			tempFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		}
		
		if (source == buttonChangePassword)
		{
			
			baseFrame.getNetworkClient().reconnect();
			
			String userName = JOptionPane.showInputDialog(this,"Username");
			
			// we have to soak up the welcome message first 
			baseFrame.getNetworkClient().soakWelcomeMessage();
			
			User tempUser = baseFrame.getNetUser(userName);
			
			if ( tempUser!= null)
			{
				logger.info("Found user " + userName);
				String oldPassword = JOptionPane.showInputDialog(this,"Enter old password");
				
				if (tempUser.getPassword().equals(oldPassword))
				{
					logger.info("old password matches");
					
					String newPassword = JOptionPane.showInputDialog(this,"Enter new password");
					tempUser.setPassword(newPassword);
					baseFrame.editNetUser(tempUser);
					
				}
				else
				{
					logger.info("old password does not match");
				}
				
			}
			else
			{
				//baseFrame.getNetworkClient().dropSession();
				JOptionPane.showMessageDialog(this, "No such user!");
				
			}
			
//			
			// we should always drop the session after good or bad reset because server will go wonky
			logger.info("Panel Login - Reset Password button trying to drop session...");
			baseFrame.getNetworkClient().dropSession();
		}
			
		
	
		if (source == buttonConnect)
		{
			logger.info("button connect pressed");
			
			// just in case we have a connection we drop it here
			baseFrame.disconnectToServer();
			labelConnectStatus.setText("Disconnected");
			
			baseFrame.setServerAddress(textFieldServer.getText());
			baseFrame.setServerPort(  Integer.parseInt(textFieldPort.getText() ) );
			baseFrame.connectToServer();
			
			// should try get an actual status
			if (baseFrame.getNetworkClient()!=null)
			{
				if (baseFrame.getNetworkClient().isConnected())
				{
					labelConnectStatus.setText("Connected to " + textFieldServer.getText() + ":" + textFieldPort.getText());
				}
				else
				{
					labelConnectStatus.setText("Not connected");
				}
			}
			else
			{
				labelConnectStatus.setText("Networking not established");
			}
			
			
		}
		
		
		
		// TODO Auto-generated method stub
		
	}
}
