package jw3m.client.gui;

import javax.swing.JPanel;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Vector;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import jw3m.beans.Notification;
import jw3m.beans.User;
import jw3m.beans.Rating;
import jw3m.dao.DAO;

import javax.swing.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PanelNotifications extends JPanel implements ActionListener, ListSelectionListener
{
	
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
	
	public PanelNotifications(SkillsClient frame) {
		baseFrame = frame;
		
		setLayout(null);
		
		lblNotifications = new JLabel("Notifications");
		lblNotifications.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 22));
		lblNotifications.setBounds(345, 23, 155, 16);
		add(lblNotifications);
		
		setupNotificationsTable();;

		table = new JTable(model);
	
		scrollPaneT = new JScrollPane(table);
		scrollPaneT.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneT.setBounds(100, 120, 600, 200);
		add(scrollPaneT);

		scrollPaneT.setViewportView(table);
		
		btnRateUser = new JButton("RATE USER");
		btnRateUser.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnRateUser.setBounds(100, 364, 164, 23);
		add(btnRateUser);
		btnRateUser.addActionListener(this);
		
		btnCancelNotification = new JButton("CANCEL NOTIFICATION");
		btnCancelNotification.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnCancelNotification.setBounds(330, 364, 249, 23);
		add(btnCancelNotification);
		
		btnInv = new JButton("Invite rating");
		btnInv.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnInv.setBounds(100, 415, 164, 23);
		btnInv.addActionListener(this);
		add(btnInv);
		
		comboBox = new JComboBox(baseFrame.data_userList);
		comboBox.setBounds(330, 416, 249, 22);
		add(comboBox);
		
	} 
	
	public void setupNotificationsTable()
	{

		// Here we set up the model

		String str[] =
			{ "noticeID", "requesterID", "raterID", "date" };
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

		for (int i = 0; i < notificationList.size(); i++)
		{

			Notification notification = notificationList.get(i);

			Object obj[] =
				{ notification.getNoticeID(), notification.getRequestorID(), notification.getRatorID(), notification.getDate() };
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
			System.out.println("Requester = " + userName);
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
	}
}
