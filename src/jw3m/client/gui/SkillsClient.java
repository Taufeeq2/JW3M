package jw3m.client.gui;

import java.awt.*;
import java.awt.event.*;
import java.util.Set;
import java.util.Vector;

import jw3m.beans.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

//DIRECT DAO ACCESS - MUST BE CHANGED LATER!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
import jw3m.dao.DAO;

//import jw3m.client.gui.PanelLogin;
//import jw3m.client.gui.PanelMediaCatalog;
//import jw3m.client.gui.PanelMediaMaintenance;
//import jw3m.client.gui.PanelUserMaintenance;


public class SkillsClient extends JFrame implements ActionListener
{
	final static Logger logger = Logger.getLogger(SkillsClient.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel basePanel;
	// Main "screens"
	private PanelLogin logonP;


	private JTabbedPane tabbedPane = null;
	
	
	/// All the menu items
	private JMenuBar menuBar = null;
	private JMenu fileMenu = null, mediaMenu = null, loanMenu  = null, userMenu = null,  helpMenu = null, subMenu = null;
	private JMenuItem exitMenu_exitItem = null, fileMenu_openItem = null, fileMenu_logoutItem = null, helpMenu_aboutItem = null;
	private JMenuItem mediaMenu_Open = null, loanMenu_Open = null, userMenu_Open = null;
	
	//South Panel
	
	private JPanel sPanel ;
	private JLabel sPanelLoggedOnAs;
	private JLabel sPanelConnectionStatus;
	private JLabel sPanelMessagesLabel;
	private JButton sPanelMessagesBut;
	
	
	
	private PanelProfile profileP;
	private	PanelEdit editP;
	private PanelNotifications notificationP;
	private PanelRateSomeone rateSomeoneP;
	
	
	// DIRECT DAO ACCESS - MUST BE CHANGED LATER!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	public DAO dao;
	public User authenticatedUser; // mo lester - bleh
	
	
	// Access varaiables 
	public Vector<User> data_userList;
	public Vector<UserSkill> data_userSkills;
	public Vector<Skill> data_skillList;
	public Vector<Hobby> data_hobbyList;
	public Vector<UserHobby> data_userHobby;
	public Vector<User> data_hobbyUsers;
	public Vector<Level> data_levels;
	public Vector<Rating> data_userRatings; 
	public Vector<Notification> data_notifications;
	
	
	
	
	public SkillsClient()
	{
		PropertyConfigurator.configure("log4j.properties");
		
		// DIRECT DAO ACCESS - MUST BE CHANGED LATER!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		
		try
		{
			dao = new DAO();
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//Now lets do the graphics
		this.setTitle("Skills Client");
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100,100,1000,700);
		MyWindowListner mwl = new MyWindowListner();
		this.addWindowListener(mwl);
		
		basePanel = new JPanel();
	//	basePanel.setBackground(new Color(255,200,200));
		basePanel.setBorder(new EmptyBorder(15,15,15,15));
		basePanel.setLayout(new GridLayout(1,1));
	
		
		// The logon panel goes into the base panel
		logonP = new PanelLogin(this);
		basePanel.add(logonP);
		
		
		// At first we have a JFrame with a base panel with the logon panel in the middle
		this.add(basePanel, BorderLayout.CENTER);
		this.setVisible(true);
		
	}
	
	// Getters and setters for data - must be changed later
	// get data or refresh data?
	public void getData()
	{
		data_userList = dao.getUserList();
		data_userSkills = dao.getUserSkills(authenticatedUser);
	//	skillList = dao.getSkillList();
	//	hobbyList = dao.getHobbyList();
		data_userHobby = dao.getUserHobby(authenticatedUser);
	//	hobbyUsers = dao.getUserHobby(  object of hobby   );
		data_levels = dao.getLevel();
		data_userRatings = dao.getRatings(authenticatedUser);
		data_notifications = dao.getNotification(authenticatedUser);
		
	}
	

	
	
	// All the graphics below
	
	public JTabbedPane getTabbedPane()
	{
		return this.tabbedPane;
	}
	
	public JPanel getBasePanel()
	{
		return this.basePanel;
		
	}
	
	public void changeToTabbedPane()
	{
		
		
		basePanel.removeAll();
		basePanel.validate();
		basePanel.repaint();
		
		basePanel.add(this.tabbedPane);
		
		
		basePanel.validate();
		basePanel.repaint();
		
		// gets the Menu Bar to show
		this.validate();
		this.repaint();

		
	}
	
	public void setupTabs()
	{
		// This creates everything we need for the tabs
		
		profileP = new PanelProfile(this);
		rateSomeoneP = new PanelRateSomeone(this);
		notificationP = new PanelNotifications(this);
		editP = new PanelEdit(this);
		
		//wtf
	//	userMaintenanceP = new PanelUserMaintenance(this);
		
		tabbedPane = new JTabbedPane();
		tabbedPane.add("My Profile", profileP);
		tabbedPane.add("Edit", editP);
		tabbedPane.add("Notification", notificationP);
		tabbedPane.add("Rate Someone", rateSomeoneP);
		

		
		
	}
	
	public void setupMenuBar()
	{
		menuBar= new JMenuBar();
			
		fileMenu = new JMenu("File");
		fileMenu.setMnemonic('F');
		fileMenu_logoutItem = new JMenuItem("Log out");
		fileMenu_logoutItem.addActionListener(this);
		
		
		helpMenu = new JMenu("Help");
		helpMenu_aboutItem = new JMenuItem("About");
		helpMenu_aboutItem.addActionListener(this);
		helpMenu.add(helpMenu_aboutItem);
		
		exitMenu_exitItem = new JMenuItem("Exit");
		exitMenu_exitItem.addActionListener(this);
		
//		subMenu = new JMenu("Submenu");

		fileMenu.add(fileMenu_logoutItem);
		fileMenu.addSeparator();
		fileMenu.add(exitMenu_exitItem);
		
		
		// Add all the menus to the menu bar
		menuBar.add(fileMenu);
		menuBar.add(helpMenu);

		this.setJMenuBar(menuBar);

	}
	
	public void removeMenuBar()
	{
		menuBar.removeAll();
		this.remove(menuBar);
		this.validate();
		this.repaint();
	}
	
	public void setupSouthPanel()
	{
		sPanelConnectionStatus = new JLabel("Connected to localhost:1337");
		sPanelLoggedOnAs = new JLabel( "Logged on as '" + this.authenticatedUser.getUserName() + "' " );
	//	sPanelMessagesLabel = new JLabel ("Messages:");
		sPanelMessagesBut = new JButton("Messages : 3");
	//	sPanelMessagesBut.setSize(200,50);
		//sPanelMessagesBut.setText("3");
		
		// South Panel setup
		sPanel = new JPanel();
		sPanel.setLayout(new GridLayout(1,3));
		
	//	sPanel.setAlignmentX(CENTER_ALIGNMENT);
		
		sPanel.add(sPanelConnectionStatus);
		sPanel.add(sPanelLoggedOnAs);
	//	sPanel.add(sPanelMessagesLabel);
		sPanel.add(sPanelMessagesBut);
		
		
		//checkout?
		sPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		this.add(sPanel, BorderLayout.SOUTH);
		
	}
	
	public void hideSouthPanel()
	{
		this.remove(sPanel);
		this.validate();
		this.repaint();
	}

	public static void main(String[] args)
	{
		SkillsClient sk = new SkillsClient();
		sk.setVisible(true);

	}
	
	class MyWindowListner extends WindowAdapter
	{
		public void windowClosing(WindowEvent we)
		{
			
			// If we are logged in then exitMenu on the JMenu would exist and we can try graceful exist while DAO closes
			// If not logged in then we can halt right away.
			
			try
			{
				exitMenu_exitItem.doClick();
			}
			catch (NullPointerException e)
			{
				System.out.print("Exiting...");
				System.exit(0);
			}
		}
	}


	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		
		if (source == helpMenu_aboutItem)
		{
			JOptionPane.showMessageDialog (this, "Skills Client \n Author : \n   Justin Fielding \n   Warren Nieuwoudt \n   Mohammed Oldey \n   Mark Erasmus\n   Michael Findlay \n 2017/18 VZAP Project");
		}
		
		if (source == fileMenu_logoutItem)
		{
	//		System.out.println("loggin out " + this.userCatalog.getAuthenticatedUser().getUserName() );
			this.removeMenuBar();
			this.hideSouthPanel();
		//	this.userCatalog.logoutUser(userCatalog.getAuthenticatedUser());
			
			basePanel.removeAll();
			basePanel.validate();
			basePanel.repaint();
			
			logonP = new PanelLogin(this); // If we dont remake the panel the old data is still present
			basePanel.add(logonP, BorderLayout.CENTER);
			
			
			basePanel.validate();
			basePanel.repaint();
			
			// gets the Menu Bar to show
			this.validate();
			this.repaint();
			
		}

		if (source == exitMenu_exitItem)
		{
			System.out.println("Trying to exit...");
			
			Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
			Thread[] threadArray = threadSet.toArray(new Thread[threadSet.size()]);
			System.out.println(threadSet.toString());
			System.out.println(); 
		
			boolean DAO_Open = true;
			
	
			
			logger.info("Graceful exit - requested");
			logger.info("Program halted!!!! ");

			System.exit(0);
		
		}

			
		
	}
	
	
	

}
