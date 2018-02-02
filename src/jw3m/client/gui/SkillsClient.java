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
	public JFrame tempFrame; // for dreyfus pop out  page
	// Main "screens"
	private PanelLogin logonP;
 

	private JTabbedPane tabbedPane = null;
	
	
	/// All the menu items
	private JMenuBar menuBar = null;
	private JMenu fileMenu = null, mediaMenu = null, loanMenu  = null, userMenu = null,  helpMenu = null, subMenu = null;
	private JMenuItem helpMenu_dreyfusItem = null, exitMenu_exitItem = null, fileMenu_openItem = null, fileMenu_logoutItem = null, helpMenu_aboutItem = null;
	private JMenuItem mediaMenu_Open = null, loanMenu_Open = null, userMenu_Open = null;
	
	//South Panel
	
	private JPanel sPanel ;
	private JLabel sPanelLoggedOnAs;
	private JLabel sPanelConnectionStatus;
	private JLabel sPanelMessagesLabel;
	private JButton sPanelMessagesBut;
	
	
	
	private PanelProfile profileP;
	private	PanelMyProfile myProfile;
	private PanelMyProfileNew myProfile_new;
	private PanelNotifications notificationP;
	public PanelRateSomeone rateSomeoneP;
	private PanelNewProfile newProfile;
	private PanelReporting panelReporting;
	private PanelSearch searchPanel;
	private PanelDemo demoPanel;
	private PanelTests testPanel;
	
	
	
	// DIRECT DAO ACCESS - MUST BE CHANGED LATER!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	//public DAO dao;
	public User authenticatedUser; // mo lester - bleh
	
	
	// Access varaiables 
	public Vector<User> data_userList;   // use getNetUserList(); can be done onces and client has it
	public Vector<Skill> data_skillList;  // use getNetSkillList(); can be done onces and client has it
	public Vector<Hobby> data_hobbyList;  // use getNetHobbyList(); can be done onces and client has it
	public Vector<Level> data_levels;  // getNetLevels() - should be called once and never again

	public Vector<User> data_hobbyUsers;  // This is a list of users who have hobby
	
	//	public Vector<UserSkill> data_userSkills; // this is probably garbage we SHOULD NOT USE!!!!!!!! - Warren + Justin
//	public Vector<UserHobby> data_userHobby;  // this is probably garbage we SHOULD NOT USE!!!!!!!! - Warren + Justin
//	public Vector<Rating> data_userRatings; // this is probably garbage we SHOULD NOT USE!!!!!!!! - Warren + Justin 
//	public Vector<Notification> data_notifications; // this is probably garbage we SHOULD NOT USE!!!!!!!! - Warren + Justin
	
	// Style objects
	
	private Font primaryFont, secondaryFont;

	// Networking stuff
	
    private NetworkClient networkClient = null;
	private String serverAddress = null;
	private int serverPort = 0;
	private Boolean networkSession = false;
	
	
	public SkillsClient()
	{
		PropertyConfigurator.configure("log4j.properties");
		
		primaryFont = new Font ("THAHOMA",Font.ITALIC, 16); // Normal Use
		secondaryFont = new Font ("THAHOMA",Font.ITALIC, 20); // Headings ??
		
		
	//	Font.ITALIC|Font.BOLD
		
		// Lets get the client making network comms to server
		
		// these are the default values - could use a DNS name and properties file
		this.setServerAddress("localhost");
		this.setServerPort(1337);
		this.connectToServer();
		
		
		
		
		// We need to connect to the server but maybe only on login?
//		 connectToServer();
		
		// DIRECT DAO ACCESS - MUST BE CHANGED LATER!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		
//		try
//		{
//			dao = new DAO();
//		} catch (Exception e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		//Now lets do the graphics
		this.setTitle("Skills Client");
		this.setFont(primaryFont);
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
		this.getNetUserList();
	//	data_userList = dao.getUserList();

		this.getNetSkillList();
	//	data_skillList = dao.getSkillList();
		
		this.getNetHobbyList();
	//	data_hobbyList = dao.getHobbyList();
		
		this.getNetLevels();
	// data_levels = dao.getLevel();
		
		if (authenticatedUser!= null)
		{
	//		data_userSkills = this.getNetUserSkills(authenticatedUser);
		//	data_userSkills = dao.getUserSkills(authenticatedUser);
			
	//		data_userHobby= this.getNetUserHobby(authenticatedUser);
		//	data_userHobby = dao.getUserHobby(authenticatedUser);
			
	//		data_userRatings = this.getNetUserRating(authenticatedUser);
		//	data_userRatings = dao.getRatings(authenticatedUser);
			
	//		data_notifications = this.getNetUserNotifications(authenticatedUser);
		//	data_notifications = dao.getNotification(authenticatedUser);
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
		logger.info("Tabbed panels initializing");
		// This creates everything we need for the tabs
		
		profileP = new PanelProfile(this);
		rateSomeoneP = new PanelRateSomeone(this);
		notificationP = new PanelNotifications(this);
		myProfile = new PanelMyProfile(this);
//		myProfile_new = new PanelMyProfileNew(this);
//		newProfile = new PanelNewProfile(this);
		panelReporting = new PanelReporting(this);
	//	demoPanel = new PanelDemo(this);
		searchPanel = new PanelSearch(this);
	//	testPanel = new PanelTests(this);
		
		
		tabbedPane = new JTabbedPane();
		tabbedPane.setFont(primaryFont);
		tabbedPane.add("My Profile", myProfile); // this is my basic details which are edditable by default
//		tabbedPane.add("My Profile (new)", myProfile_new);
		
		tabbedPane.add("My Skills", profileP); // this should change to my skills
		
		tabbedPane.add("Notifications", notificationP);
		tabbedPane.add("Rate Someone", rateSomeoneP);
//		tabbedPane.add("Create New Profile", newProfile);
		tabbedPane.add("People-Skill finder", panelReporting);
		tabbedPane.add("Searches", searchPanel);
//		tabbedPane.add("Example (GUI layout)", examplePanel);
//		tabbedPane.addTab("Demo Panel", demoPanel);

		
	}
	
	public void setupMenuBar()
	{
		//indentation here shows the menu flow
		menuBar= new JMenuBar();
			
			fileMenu = new JMenu("File");
			fileMenu.setFont(primaryFont);
			fileMenu.setMnemonic('F');
				fileMenu_logoutItem = new JMenuItem("Log out");
				fileMenu_logoutItem.setFont(primaryFont);
				fileMenu_logoutItem.addActionListener(this);
			
			
			helpMenu = new JMenu("Help");
			helpMenu.setFont(primaryFont);
			
				helpMenu_dreyfusItem = new JMenuItem("Drefus Model");
				helpMenu_dreyfusItem.setFont(primaryFont);
				helpMenu_dreyfusItem.addActionListener(this);
			
				helpMenu_aboutItem = new JMenuItem("About");
				helpMenu_aboutItem.setFont(primaryFont);
				helpMenu_aboutItem.addActionListener(this);
				
				
				exitMenu_exitItem = new JMenuItem("Exit");
				exitMenu_exitItem.setFont(primaryFont);
				exitMenu_exitItem.addActionListener(this);
		
		
		// adding everything to the right place

		fileMenu.add(fileMenu_logoutItem);
		fileMenu.addSeparator();
		fileMenu.add(exitMenu_exitItem);
		
		helpMenu.add(helpMenu_dreyfusItem);
		helpMenu.addSeparator();
		helpMenu.add(helpMenu_aboutItem);
		
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
		
		// South Panel setup
		sPanelConnectionStatus = new JLabel("Connected to " + this.getNetworkClient().getServerAddress() + ":" +this.getNetworkClient().getServerPort() );
		sPanelConnectionStatus.setFont(primaryFont);
		
		sPanelLoggedOnAs = new JLabel( "Logged on as '" + this.authenticatedUser.getUserName() + "' " );
		sPanelLoggedOnAs.setFont(primaryFont);
				
		sPanelMessagesBut = new JButton("Messages : " + this.getNetUserNotifications(this.authenticatedUser).size());
		sPanelMessagesBut.setFont(primaryFont);
		sPanelMessagesBut.addActionListener(this);

		sPanel = new JPanel();
		sPanel.setLayout(new GridLayout(1,3));
		
		sPanel.add(sPanelConnectionStatus);
		sPanel.add(sPanelLoggedOnAs);
		sPanel.add(sPanelMessagesBut);
		
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
			
			logger.debug(threadSet.toString());
			
		
			disconnectToServer();
			
	
			
			logger.info("Graceful exit - requested");
			logger.info("Program halted!!!! ");

			System.exit(0);
		
		}

		if (source == helpMenu_dreyfusItem)
		{
			// show the drefus model diagrams or something
			logger.info("dreyfus model menu item clicked");
			
			JPanel dreyfusPanel = new PanelDreyfus(this);
			
			tempFrame = new JFrame();
			tempFrame.getContentPane().add(dreyfusPanel);
			tempFrame.setSize(800,600);
			tempFrame.setVisible(true);
			tempFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			
		}
		
		if (source == sPanelMessagesBut)
		{
			//logger.info("messages butt pressed");
			tabbedPane.setSelectedComponent(notificationP);

			
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
	
	public Font getPrimaryFont()
	{
		return this.primaryFont;
	}
	
	public Font getSecondaryFont()
	{
		return this.secondaryFont;
	}
	

	// Network client calls
	
	public User getNetUser(String userID)
	{
		Comms commsSend = new Comms();
			commsSend.setText("send user");
			commsSend.setObj(userID);

		Comms commsRec = getNetworkClient().networkTransaction( commsSend);
		
		
		return (User)commsRec.getObj();
		
	//	logger.debug(" getNetUserList() call invoked");	
	}
	
	public void getNetUserList()
	{
		Comms commsSend = new Comms();
			commsSend.setText("send userList");
			commsSend.setObj("");

		Comms commsRec = getNetworkClient().networkTransaction( commsSend);
			
		this.data_userList = (Vector<User>)commsRec.getObj();
		
	//	logger.debug(" getNetUserList() call invoked");	
	}
	
	public void getNetSkillList()
	{
		Comms commsSend = new Comms();
			commsSend.setText("send skillList");
			commsSend.setObj("");

		Comms commsRec = getNetworkClient().networkTransaction( commsSend);
			
		this.data_skillList = (Vector<Skill>)commsRec.getObj();
		
	//	logger.debug(" getNetUserList() call invoked");

	}
	
	public Vector<User> getNetSkillsUser(Skill SkillIn)
	{
		Comms commsSend = new Comms();
			commsSend.setText("send skillReturnUser");
			commsSend.setObj(SkillIn);

		Comms commsRec = getNetworkClient().networkTransaction( commsSend);
			
		return (Vector<User>)commsRec.getObj();
		
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
	
	public Vector<Rating> getNetSkillRating(Skill skillIn)
	{
		Comms commsSend = new Comms();
		commsSend.setText("send skillRating");
		commsSend.setObj(skillIn);

	Comms commsRec = getNetworkClient().networkTransaction( commsSend);
		
	return (Vector<Rating>)commsRec.getObj();
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
	
	// Do we return boolean or the user or the whole list???
	public void setNetAddUser(User userIn)
	{
		Comms commsSend = new Comms();
			commsSend.setText("add userList");
			commsSend.setObj(userIn);

		Comms commsRec = getNetworkClient().networkTransaction( commsSend);
		
		logger.info(commsRec.getText());
		this.data_userList = (Vector<User>)commsRec.getObj();
		
	//	logger.debug(" getNetUserList() call invoked");	
	}

	public Vector<UserSkill> setNetUserSkills(User userIn, Vector<Skill> skillsSet)
	{
		// This is what we do
		// We make a MultiBean with User as the first Object and create a vector of Skills
		// The Server wants [ User , <1,2,3,4> ] 
		
		MultiBean multiBean = new MultiBean();
		
		multiBean.setObj(userIn);
		
		for (int i = 0; i < skillsSet.size()   ;  i++   )
		{
			multiBean.addMulti(skillsSet.get(i).getSkillID());
		}
		
		Comms commsSend = new Comms();
			commsSend.setText("add userSkills");
			commsSend.setObj(multiBean);

		Comms commsRec = getNetworkClient().networkTransaction( commsSend);
			
		return (Vector<UserSkill>)commsRec.getObj();
		
	//	logger.debug(" getNetUserList() call invoked");

	}

	public Vector<UserHobby> setNetUserHobby(User userIn, Vector<Hobby> hobbySet)
	{
		// This is what we do
		// We make a MultiBean with User as the first Object and create a vector of Hobbies
		// The Server wants [ User , <1,2,3,4> ] 
		
		MultiBean multiBean = new MultiBean();
		
		multiBean.setObj(userIn);
		
		for (int i = 0; i < hobbySet.size()   ;  i++   )
		{
			multiBean.addMulti(hobbySet.get(i).getHobbyID());
		}
		
		Comms commsSend = new Comms();
			commsSend.setText("add userHobby");
			commsSend.setObj(multiBean);

		Comms commsRec = getNetworkClient().networkTransaction( commsSend);
			
		return (Vector<UserHobby>)commsRec.getObj();
		
	//	logger.debug(" getNetUserList() call invoked");

	}

	// probably a boolean to say coool we did it
	public void setNetAddRating(Rating ratingIn)
	{
		Comms commsSend = new Comms();
			commsSend.setText("add userRating");
			commsSend.setObj(ratingIn);

		Comms commsRec = getNetworkClient().networkTransaction( commsSend);
		
		// Do nothing.. the server does not yet return anything !!!!!!!
		
		
	//	logger.debug(" getNetUserList() call invoked");	
	}

	
	// probably a boolean to say coool we did it
	public void setNetNotifcation(Notification notificationIn)
	{
		Comms commsSend = new Comms();
			commsSend.setText("add userNotifications");
			commsSend.setObj(notificationIn);

		Comms commsRec = getNetworkClient().networkTransaction( commsSend);
		
		// Do nothing.. the server does not yet return anything !!!!!!!
		
		
	//	logger.debug(" getNetUserList() call invoked");	
	}

	public User editNetUser(User userIn)
	{
		Comms commsSend = new Comms();
			commsSend.setText("edit user");
			commsSend.setObj(userIn);

		Comms commsRec = getNetworkClient().networkTransaction( commsSend);
		
		return  (User)commsRec.getObj();
	
//	logger.debug(" getNetUserList() call invoked");	
		
	}
	
	public Vector<UserHobby> setNetRemoveUserHobby(UserHobby userHobbyIn)
	{
//		logger.info("Transaction sent : Remove UserHobby " + userHobbyIn.getUserID() + " ID: " + userHobbyIn.getHobbyID());
		Comms commsSend = new Comms();
			commsSend.setText("remove userHobby");
			commsSend.setObj(userHobbyIn);

		Comms commsRec = getNetworkClient().networkTransaction( commsSend);
		
		logger.info(commsRec.getText());
		return (Vector<UserHobby>)commsRec.getObj();
		
	//	logger.debug(" getNetUserList() call invoked");	
	}

	public Vector<Notification> setNetRemoveUserNotification(Notification notificationIn)
	{
//		logger.info("Transaction sent : Remove UserHobby " + userHobbyIn.getUserID() + " ID: " + userHobbyIn.getHobbyID());
		Comms commsSend = new Comms();
			commsSend.setText("remove userNotification");
			commsSend.setObj(notificationIn);

		Comms commsRec = getNetworkClient().networkTransaction( commsSend);
		
		logger.info(commsRec.getText());
		return (Vector<Notification>)commsRec.getObj();
		
	//	logger.debug(" getNetUserList() call invoked");	
	}
	
	public Vector<User> setNetRemoveUser(String userID)
	{
		Comms commsSend = new Comms();
			commsSend.setText("remove user");
			commsSend.setObj(userID);

		Comms commsRec = getNetworkClient().networkTransaction( commsSend);
		this.data_userList = (Vector<User>)commsRec.getObj();
		return (Vector<User>) commsRec.getObj();
		
	//	logger.debug(" getNetUserList() call invoked");	
	}
	
	public Vector<Skill> setNetRemoveSkill(Skill skillIn)
	{
		Comms commsSend = new Comms();
			commsSend.setText("remove skillList");
			commsSend.setObj(skillIn);

		Comms commsRec = getNetworkClient().networkTransaction( commsSend);
		
		logger.info(commsRec.getText());
		this.data_skillList = (Vector<Skill>)commsRec.getObj();
		return (Vector<Skill>)commsRec.getObj();
	//	logger.debug(" getNetUserList() call invoked");	
	}

	public Vector<UserSkill> setNetRemoveUserSkills(User userIn, Vector<Skill> skillsSet)
	{
		// This is what we do
		// We make a MultiBean with User as the first Object and create a vector of Skills
		// The Server wants [ User , <1,2,3,4> ] 
		
		MultiBean multiBean = new MultiBean();
		
		multiBean.setObj(userIn);
		
		for (int i = 0; i < skillsSet.size()   ;  i++   )
		{
			multiBean.addMulti(skillsSet.get(i).getSkillID());
		}
		
		Comms commsSend = new Comms();
			commsSend.setText("remove userSkills");
			commsSend.setObj(multiBean);

		Comms commsRec = getNetworkClient().networkTransaction( commsSend);
			
		return (Vector<UserSkill>)commsRec.getObj();
		
	//	logger.debug(" getNetUserList() call invoked");

	}
	
	public void setNetRemoveHobby(Hobby hobbyIn)
	{
		Comms commsSend = new Comms();
			commsSend.setText("remove hobbyList");
			commsSend.setObj(hobbyIn);

		Comms commsRec = getNetworkClient().networkTransaction( commsSend);
		
		
	//	logger.debug(" getNetUserList() call invoked");	
	}
	
	public void setNetAddHobbyList(Hobby hobbyIn)
	{
		Comms commsSend = new Comms();
			commsSend.setText("add hobbyList");
			commsSend.setObj(hobbyIn);

		Comms commsRec = getNetworkClient().networkTransaction( commsSend);
		
		
	//	logger.debug(" getNetUserList() call invoked");	
	}
}
