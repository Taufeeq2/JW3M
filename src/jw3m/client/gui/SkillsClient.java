package jw3m.client.gui;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Date;
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
	private PanelNewProfile newProfile;
	private PanelReporting panelReporting;
	private PanelTests testPanel;
	
	
	// DIRECT DAO ACCESS - MUST BE CHANGED LATER!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	public DAO dao;
	public User authenticatedUser; // mo lester - bleh
	
	
	// Access varaiables 
	public Vector<User> data_userList;
	public Vector<UserSkill> data_userSkills; // this is probably garbage we SHOULD NOT USE!!!!!!!! - Warren + Justin
	public Vector<Skill> data_skillList;
	public Vector<Hobby> data_hobbyList;
	public Vector<UserHobby> data_userHobby;  // this is probably garbage we SHOULD NOT USE!!!!!!!! - Warren + Justin
	public Vector<User> data_hobbyUsers;
	public Vector<Level> data_levels;
	public Vector<Rating> data_userRatings; // this is probably garbage we SHOULD NOT USE!!!!!!!! - Warren + Justin 
	public Vector<Notification> data_notifications; // this is probably garbage we SHOULD NOT USE!!!!!!!! - Warren + Justin
	
	// Style objects
	
	public Font standardFont;

	// Networking stuff
	
    private NetworkClient networkClient = null;
	private String serverAddress = null;
	private int serverPort = 0;
	private Boolean networkSession = false;
	
	
	public SkillsClient()
	{
		PropertyConfigurator.configure("log4j.properties");
		
		standardFont = new Font ("THAHOMA",Font.PLAIN, 16);
		
		
	//	Font.ITALIC|Font.BOLD
		
		// Lets get the client making network comms to server
		
		// these are the default values - could use a DNS name and properties file
		this.setServerAddress("localhost");
		this.setServerPort(1337);
		this.connectToServer();
		
		
		
		
		// We need to connect to the server but maybe only on login?
//		 connectToServer();
		
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

		data_skillList = dao.getSkillList();
		data_hobbyList = dao.getHobbyList();
		
	//	data_hobbyUsers = dao.getUserHobby(  object of hobby   );
		data_levels = dao.getLevel();
		if (authenticatedUser!= null)
		{
			data_userSkills = dao.getUserSkills(authenticatedUser);
			data_userHobby = dao.getUserHobby(authenticatedUser);
			data_userRatings = dao.getRatings(authenticatedUser);
			data_notifications = dao.getNotification(authenticatedUser);
		}
		
		
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
//		newProfile = new PanelNewProfile(this);
		panelReporting = new PanelReporting(this);
		testPanel = new PanelTests(this);
		
		
		//wtf
	//	userMaintenanceP = new PanelUserMaintenance(this);
		
		tabbedPane = new JTabbedPane();
		tabbedPane.add("My Profile", profileP);
		tabbedPane.add("Edit", editP);
		tabbedPane.add("Notification", notificationP);
		tabbedPane.add("Rate Someone", rateSomeoneP);
//		tabbedPane.add("Create New Profile", newProfile);
		tabbedPane.add("Reporting", panelReporting);
		tabbedPane.addTab("Test Panel", testPanel);

		
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
		sPanelMessagesBut = new JButton("Messages : *3");
		sPanelMessagesBut.addActionListener(this);
		
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
			// remove authenticated user
			this.setAuthenticatedUser(null);
			disconnectToServer();
			
			// establish new clean session
		//	this.connectToServer();
			
			
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
		
			disconnectToServer();
			
	
			
			logger.info("Graceful exit - requested");
			logger.info("Program halted!!!! ");

			System.exit(0);
		
		}

		if (source == sPanelMessagesBut)
		{
			logger.info("messages butt pressed");
			tabbedPane.setSelectedComponent(notificationP);

			// Tests to come out
			this.getNetUserList();
			this.getNetSkill();
			this.getNetHobbyList();
			this.getNetLevels();
			
			System.out.println("!!!!!!!!!!!!!!!!!" + this.getNetUserSkills(authenticatedUser) );
			System.out.println();
			System.out.println("!!!!!!!!!!!!!!!!!" + this.getNetUserHobby(authenticatedUser) );
			System.out.println();
			System.out.println("!!!!!!!!!!!!!!!!!" + this.getNetUserRating(authenticatedUser) );
			System.out.println();
			System.out.println("!!!!!!!!!!!!!!!!!" + this.getNetUserNotifications(authenticatedUser) );
			System.out.println();
			
		
			
			
		}
			
		
	}

	// Getters and setters for server ports

	public String getServerAddress()
	{
		return serverAddress;
	}

	public void setServerAddress(String serverAddress)
	{
		this.serverAddress = serverAddress;
	}

	public int getServerPort()
	{
		return serverPort;
	}

	public void setServerPort(int serverPort)
	{
		this.serverPort = serverPort;
	}



	public NetworkClient getNetworkClient()
	{
		return networkClient;
	}




	public void setNetworkClient(NetworkClient networkClient)
	{
		this.networkClient = networkClient;
	}



	public Boolean getNetworkSession()
	{
		return networkSession;
	}



	public void setNetworkSession(Boolean networkSession)
	{
		this.networkSession = networkSession;
	}

	public void setAuthenticatedUser(User user)
	{
		this.authenticatedUser = user;
	}
	
	public User getAuthenticatedUser()
	{
		return this.authenticatedUser;
	}
	
	public void disconnectToServer()
	{
		try
		{
			networkClient.dropSession();
		}
		catch (NullPointerException ne)
		{
			logger.error("Unable to drop non-existant session. Nothing to worry about!!!");
			// ne.printStackTrace();
		}
		
	}
	
	public void connectToServer()
	{
		try
		{
			networkClient = new NetworkClient(this, serverAddress,serverPort );
			networkSession = true;
		} catch (Exception e1)
		{
			// TODO Auto-generated catch block
			logger.error("Unable to create client/server session. Perhaps the server is not avaliable?");
			networkClient = null;
			networkSession = false;
			
			//e1.printStackTrace();
		}
	}
	
	public Font getFont()
	{
		return this.standardFont;
	}

	// Network client calls
	
	public void getNetUserList()
	{
		Comms commsSend = new Comms();
			commsSend.setText("send userList");
			commsSend.setObj("");

		Comms commsRec = getNetworkClient().networkTransaction( commsSend);
			
		this.data_userList = (Vector<User>)commsRec.getObj();
		
	//	logger.debug(" getNetUserList() call invoked");	
	}
	
	public void getNetSkill()
	{
		Comms commsSend = new Comms();
			commsSend.setText("send skillList");
			commsSend.setObj("");

		Comms commsRec = getNetworkClient().networkTransaction( commsSend);
			
		this.data_skillList = (Vector<Skill>)commsRec.getObj();
		
	//	logger.debug(" getNetUserList() call invoked");

	}
	
	public void getNetHobbyList()
	{
		Comms commsSend = new Comms();
			commsSend.setText("send hobbyList");
			commsSend.setObj("");

		Comms commsRec = getNetworkClient().networkTransaction( commsSend);
			
		this.data_hobbyList = (Vector<Hobby>)commsRec.getObj();
		
	//	logger.debug(" getNetUserList() call invoked");

	}
	
	public void getNetLevels()
	{
		Comms commsSend = new Comms();
			commsSend.setText("send levels");
			commsSend.setObj("");

		Comms commsRec = getNetworkClient().networkTransaction( commsSend);
			
		this.data_levels = (Vector<Level>)commsRec.getObj();
		
	//	logger.debug(" getNetUserList() call invoked");

	}

	public Vector<UserSkill> getNetUserSkills(User userIn)
	{
		Comms commsSend = new Comms();
			commsSend.setText("send userSkills");
			commsSend.setObj(userIn);

		Comms commsRec = getNetworkClient().networkTransaction( commsSend);
			
		return (Vector<UserSkill>)commsRec.getObj();
		
	//	logger.debug(" getNetUserList() call invoked");

	}
	
	public Vector<UserHobby> getNetUserHobby(User userIn)
	{
		Comms commsSend = new Comms();
			commsSend.setText("send userHobby");
			commsSend.setObj(userIn);

		Comms commsRec = getNetworkClient().networkTransaction( commsSend);
			
		return (Vector<UserHobby>)commsRec.getObj();
		
	//	logger.debug(" getNetUserList() call invoked");

	}
	
	public Vector<Rating> getNetUserRating(User userIn)
	{
		Comms commsSend = new Comms();
			commsSend.setText("send userRating");
			commsSend.setObj(userIn);

		Comms commsRec = getNetworkClient().networkTransaction( commsSend);
			
		return (Vector<Rating>)commsRec.getObj();
		
	//	logger.debug(" getNetUserList() call invoked");

	}
	
	public Vector<Notification> getNetUserNotifications(User userIn)
	{
		Comms commsSend = new Comms();
			commsSend.setText("send userNotifications");
			commsSend.setObj(userIn);

		Comms commsRec = getNetworkClient().networkTransaction( commsSend);
			
		return (Vector<Notification>)commsRec.getObj();
		
	//	logger.debug(" getNetUserList() call invoked");

	}
	



//	
//	getNetworkClient().networkTransaction(new Comms("add userList", newUser ));
//	getNetworkClient().networkTransaction(new Comms("add userSkills", multibean )           );	
//	getNetworkClient().networkTransaction(new Comms("add userHobby", multibeanHob )           );
//	getNetworkClient().networkTransaction(new Comms("add userRating", newRating )           );
//	getNetworkClient().networkTransaction(new Comms("add userNotifications", newNotification )    );
//	
//	
	
	

}
