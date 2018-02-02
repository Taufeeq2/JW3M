package jw3m.client.gui;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import jw3m.beans.Notification;
import jw3m.beans.User;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;


public class PanelNotifications extends JPanel implements ActionListener, ListSelectionListener
{
	final static Logger logger = Logger.getLogger(PanelNotifications.class);
	private Font primaryFont, secondaryFont;
	private SkillsClient baseFrame;
	DefaultTableModel model = null;
	private Vector<Notification> notificationList = null;
	private JLabel lblNotifications;
	private JScrollPane scrollPane, scrollPaneT;
	private JTextArea textArea;
	private JTable table;
	private JButton btnRateUser;
	private JButton btnCancelNotification;
	private JButton btnInv;
	private JComboBox comboBox;
	private JPanel northP, centerP, southP, panel, dummyP1, dummyP2;
	private JLabel lblImgLabel = null;
	
	public PanelNotifications(SkillsClient frame) 
	{
		PropertyConfigurator.configure("log4j.properties");
		this.baseFrame = frame;
		primaryFont = baseFrame.getPrimaryFont();
		secondaryFont = baseFrame.getSecondaryFont();
		
		this.setupPanels();

	} 
	
	public void setupPanels()
	{
		setLayout(new BorderLayout(0, 0));
		
		northP = new JPanel();

		
		lblImgLabel = new JLabel(new ImageIcon("resources/Notifications_Full.jpg"));
		northP.add(lblImgLabel);
		
		
		centerP = new JPanel();		
		
		setupNotificationsTable();
		centerP.setLayout(new GridLayout(1, 1));
		
		
		table = new JTable(model);
		table.setFont(primaryFont);

		
		scrollPaneT = new JScrollPane(table);
		scrollPaneT.setBounds(20, 10, 900, 300);
		scrollPaneT.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		centerP.add(scrollPaneT);

		scrollPaneT.setViewportView(table);
		
		southP = new JPanel();
//		southP.setBorder(new LineBorder(Color.GREEN, 2));
		GridLayout gl_southP = new GridLayout(1, 3);
		southP.setLayout(gl_southP);
		
		
		panel = new JPanel();
//		panel.setBorder(new LineBorder(Color.BLUE, 2));
		

		
		btnRateUser = new JButton("RATE USER");
		btnRateUser.setFont(primaryFont);
		btnRateUser.setBounds(50, 100, 225, 30);
		btnRateUser.addActionListener(this);
		panel.setLayout(new GridLayout(4, 2, 20, 20));
		panel.add(btnRateUser);
		
		btnCancelNotification = new JButton("CANCEL NOTIFICATION");
		btnCancelNotification.setSize(225, 30);
		btnCancelNotification.setLocation(400, 100);
		btnCancelNotification.setFont(primaryFont);
		btnCancelNotification.addActionListener(this);
		panel.add(btnCancelNotification);
		
		btnInv = new JButton("INVITE RATING");
		btnInv.setSize(225, 30);
		btnInv.setLocation(50, 200);
		btnInv.setFont(primaryFont);
		btnInv.addActionListener(this);
		
		comboBox = new JComboBox(baseFrame.data_userList);
		comboBox.setSize(225, 30);
		comboBox.setLocation(400, 200);
		comboBox.setFont(primaryFont);
		panel.add(comboBox);
		panel.add(btnInv);
		
		
		dummyP1 = new JPanel();
		dummyP2 = new JPanel();
		
		southP.add(dummyP1);
		southP.add(panel);
		southP.add(dummyP2);
		
		this.add(northP, BorderLayout.NORTH);
		this.add(centerP, BorderLayout.CENTER);
		this.add(southP, BorderLayout.SOUTH);
		
	}
	
	public void setupNotificationsTable()
	{

		// Here we set up the model

		String str[] =
			{ "Date", "Notification"};
		model = new DefaultTableModel(str, 0)
		{
			public void setValueAt(Object aValue, int row, int column)
			{
				Vector rowVector = (Vector) dataVector.elementAt(row);
				rowVector.setElementAt(aValue, column);
				fireTableCellUpdated(row, column);

				setCustomTableElement(aValue, row, column);

			}

			public boolean isCellEditable(int row, int column)
			{
				// make read only field column 0 - other column is directly
				// editable
				if ((column == 0) || (column == 3))
				{
					return false;
				} else
				{
					return true;
				}

			}

		};

		// Setup the model

		try
		{
			notificationList = baseFrame.getNetUserNotifications(baseFrame.authenticatedUser);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Vector<User> userList = new Vector<User>();
		userList = baseFrame.data_userList;
		
		for (int i = 0; i < notificationList.size(); i++)
		{

			Notification notification = notificationList.get(i);
			String requester = "";
			String rater = "";

			for (int j = 0; j < userList.size(); j++)
			{
				if(notification.getRequestorID().equals(userList.get(j).getUserName()))
				{
					requester = userList.get(j).getFirstName() + " " + userList.get(j).getSurname() + " has requested you to rate them.";
				}
//				if(notification.getRatorID().equals(userList.get(j).getUserName()))
//				{
//					rater = userList.get(j).getFirstName() + " " + userList.get(j).getSurname();
//				}
				
			}
			Object obj[] =
				{ notification.getDate(), requester };
			model.addRow(obj);

		}
	}

	public void setCustomTableElement(Object aValue, int row, int column)
	{

		Notification notify = notificationList.get(row);

		switch (column)
		{
		case 0:
			try
			{
				notify.setNoticeID(Integer.parseInt(aValue.toString()));
			} catch (NumberFormatException e)
			{
				model.setValueAt(notify.getNoticeID(), row, column);

			}
			break;
		case 1:
			notify.setRequestorID(aValue.toString());
			break;
		case 2:
			notify.setRatorID(aValue.toString());
			break;
		case 3:
//			notify.setDate(aValue.toString());
			break;

		}
		System.out.println(aValue + " " + row + " " + column);

		// skillList.SaveToDisk();
	}
	
	public String getTabledetails(int row)
	{
		String userName = null;
		Object userObject []= new Object[model.getColumnCount()];
		
		for (int i = 0; i < model.getColumnCount(); i++)
		{
			userObject [i] = model.getValueAt(row, i);
		}
		
		for (int d = 0; d < userObject.length; d++)
		{
			System.out.println("Array " + d + " " + userObject[d] + "\n" );
		}
		
		
		return userName;
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent ae)
	{
		Object source = ae.getSource();
		
		if (source == btnRateUser)
		{
			String userName = (String) model.getValueAt(table.getSelectedRow(), 1);
			
			StringTokenizer tokenizer = new StringTokenizer(userName, " ");
			String token1;
			String token2;
			

			tokenizer.hasMoreTokens();
		    token1 = tokenizer.nextToken();
		    tokenizer.hasMoreTokens();
		    token2 = tokenizer.nextToken();
		    
		    for (int i = 0; i < baseFrame.data_userList.size(); i++)
			{
				if ((baseFrame.data_userList.get(i).getFirstName().equals(token1)) && (baseFrame.data_userList.get(i).getSurname().equals(token2)))
				{
					userName = baseFrame.data_userList.get(i).getUserName();
				}
			}

			baseFrame.rateSomeoneP.getRateUser(userName);
			baseFrame.getTabbedPane().setSelectedComponent(baseFrame.rateSomeoneP);
		}
		
		if (source ==btnInv)
		{
			
			User tempUser = (User)comboBox.getSelectedItem();
			tempUser.getUserName();
			
			
			
			if (baseFrame.getAuthenticatedUser().getUserName().equals(tempUser.getUserName()) )
			{
				JOptionPane.showMessageDialog(this, "You cannot invite yourself");
			}
			else
			{
				Notification notification = new Notification();
				
				notification.setRequestorID(  baseFrame.getAuthenticatedUser().getUserName()  );
				
				notification.setRatorID(   tempUser.getUserName()    );
			
				
				java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
				
				notification.setDate(date);
				
				baseFrame.setNetNotifcation(notification);
				
				User tempUserRator = baseFrame.getNetUser(notification.getRatorID());
				
				JOptionPane.showMessageDialog(this, "You have invited " + tempUserRator.getFirstName() + " " + tempUserRator.getSurname() + " ("+ tempUserRator.getUserName()+ ") to rate you.");
				
			}
		}
			
			if (source == btnCancelNotification)
			{
				System.out.println("Cancel Notification Botton pressed");
				Notification notify = new Notification();
				String userName = (String) model.getValueAt(table.getSelectedRow(), 1);
				
				StringTokenizer tokenizer = new StringTokenizer(userName, " ");
				String token1;
				String token2;
				

				tokenizer.hasMoreTokens();
			    token1 = tokenizer.nextToken();
			    System.out.println("NAME " + token1);
			    tokenizer.hasMoreTokens();
			    token2 = tokenizer.nextToken();
			    System.out.println("SURNAME " + token2);
			    
			    for (int i = 0; i < baseFrame.data_userList.size(); i++)
				{
					if ((baseFrame.data_userList.get(i).getFirstName().equals(token1)) && (baseFrame.data_userList.get(i).getSurname().equals(token2)))
					{
						userName = baseFrame.data_userList.get(i).getUserName();
					}
				}
			    
			    notify.setRatorID(baseFrame.authenticatedUser.getUserName());
			    notify.setRequestorID(userName);
			    baseFrame.setNetRemoveUserNotification(notify);
			    model.removeRow(table.getSelectedRow());
			    // create the server/client call to remove notification
			    
			}
			

			
			
		
	}
}
