package jw3m.client.gui;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class PanelLogin extends JPanel implements ActionListener
{
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

	/**
	 * Create the panel.
	 */
	public PanelLogin(SkillsClient frame)
	{
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
		labelUserID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
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
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(175)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
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
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(buttonChangePassword, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(260)
							.addComponent(lblStandardBank)))
					.addContainerGap(306, Short.MAX_VALUE))
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
					.addGap(79)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(buttonChangePassword)
							.addComponent(buttonCancel))
						.addComponent(buttonSubmit))
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
			
//			Boolean logonSuccess= false;
//			// add logic to validate user
//			
//			String passwordString = new String (passwordField.getPassword() );
//			
//			if ( mainFrame.getUserCatalog().userExists(textFieldUserID.getText())  )
//			{
//				 logonSuccess = mainFrame.getUserCatalog().logonUser(textFieldUserID.getText(), passwordString);
//				 
//			}
//	
//			
//			
//			if (logonSuccess)
//			{
//				if ( mainFrame.getUserCatalog().findUser(textFieldUserID.getText()).isAdmin() )
//				{
					baseFrame.setupMenuBar();
					baseFrame.setupSouthPanel();
					baseFrame.setupTabs();
					baseFrame.changeToTabbedPane();
//				}
//				else
//				{
//					mainFrame.setupMenuBar();
//					mainFrame.setupSouthPanel();
//					mainFrame.setupTabs();
//					mainFrame.changeToTabbedPane();
//				}
//			}
//			else
//			{
//				// For security we don't want to show if the user name exists only but the combination
//				JOptionPane.showMessageDialog (this, "Incorrect user name or password!");
//				
//			}

			
			
			
		}
		
		if (source == buttonCancel)
		{
			textFieldUserID.setText("");
			passwordField.setText("");
			
		}
		
		if (source == buttonChangePassword)
		{
			String username = JOptionPane.showInputDialog(this,"Username");
			
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
