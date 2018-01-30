package jw3m.client.gui;

import javax.swing.JPanel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.rmi.server.SocketSecurityException;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MouseInputListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import org.w3c.dom.events.EventTarget;
import org.w3c.dom.events.MouseEvent;
import org.w3c.dom.views.AbstractView;
import jw3m.beans.*;
import jw3m.dao.DAO;
import jw3m.widgets.SeparatorComboBox;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.DefaultCellEditor;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Component;
import javax.swing.JComboBox;
import jw3m.widgets.SeparatorComboBox;

public class PanelRateSomeone extends JPanel implements ActionListener
{
	private SkillsClient baseFrame;
	private JLabel lblRateSomeone;
	private JButton btnSubmit;
	private DefaultTableModel model = null;
	private JScrollPane scrollPane;
	private JTable table;
	private Vector<Skill> skillList = new Vector<Skill>();
	private Vector<Skill> skillNames = new Vector<Skill>();
	private JLabel lblSearch;
	private Rating ratee;
	private JComboBox separatorComboBox ;
	
	private Vector itemsText = new Vector();
	private Vector<String> itemsName = new Vector<String>();
	
	@SuppressWarnings("unchecked")
	public PanelRateSomeone(SkillsClient frame) {
		
		baseFrame = frame;
		lblRateSomeone = new JLabel("Rate Someone");
		lblRateSomeone.setBounds(409, 26, 134, 27);
		lblRateSomeone.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 22));
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(603, 173, 97, 29);
		btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSubmit.addActionListener(this);
		btnSubmit.setEnabled(false);
		Vector<Skill> skillNms = new Vector<Skill>();
		baseFrame.getNetSkillList();
		skillNms = baseFrame.data_skillList;
		
		
		//System.out.println(skillNms);
		
		for (int i = 0 ; i < skillNms.size() ; i++)
		{
			skillNames.add(skillNms.get(i));
		}
		
		lblSearch = new JLabel("Search ");
		lblSearch.setBounds(326, 110, 60, 20);
		lblSearch.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		setupSkillsTable();
		setLayout(null);

		table = new JTable(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		

		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(39, 273, 1653, 300);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		add(scrollPane);
		scrollPane.setViewportView(table);
		add(scrollPane);
		add(lblRateSomeone);
		add(lblSearch);
		add(btnSubmit);
		
		// moved to global variable
//		Vector itemsText = new Vector();
//	    Vector itemsName = new Vector();
		
		
        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
      
        
        Vector<Notification> notify = baseFrame.getNetUserNotifications(baseFrame.authenticatedUser);
        		
        String str = new String();
        User requestUser = new User();
        
        
        System.out.println("size of notify" + notify.size());
        
        for (int i = 0; i < notify.size(); i++)
		{
        	if(baseFrame.authenticatedUser.getUserName().equals(notify.get(i).getRatorID()))
        	{
        		
        		requestUser = baseFrame.getNetUser(notify.get(i).getRequestorID()); 
        		
        		if (requestUser!=null)
        		{
        			System.out.println("request user is : " + requestUser.toStringFull());
        		}
        		
        		str = requestUser.getFirstName() + " " + requestUser.getSurname() + " (" + requestUser.getUserName() + ")" ;
        		itemsText.addElement("Rate request - " + str );
        		itemsName.addElement(requestUser.getUserName());   // so we will fill this with UserNames only!!!
        		
        		// or other order
//        		str = requestUser.getFirstName() + " " + requestUser.getSurname() + " (" + requestUser.getUserName() + ")" ;
//        		items.addElement(str + "(Rate requested)" );
        		
        	}
			
		}
       

        itemsText.addElement(separator );
        itemsName.addElement("seperator"); // as this index will be unselectable it does not matter what we put here
        
        Vector<User> allUsers = baseFrame.data_userList;
        
        
        for (int i = 0; i < allUsers.size(); i++)
		{

       
        	
        	// for this to always work nicely do not rely on toStrings!!!!
    
        	itemsText.addElement(allUsers.get(i).getFirstName() + " " + allUsers.get(i).getSurname() + " (" +  allUsers.get(i).getUserName() + ")" );
			itemsName.addElement(allUsers.get(i).getUserName());
		}
        


		// Changed this to a SeperatorComboBox (which is a custom class jw3m.widgets.SeparatorComboBox
        separatorComboBox  = new SeparatorComboBox(itemsText);
        separatorComboBox.setFont(new Font("Tahoma", Font.BOLD, 16));
        separatorComboBox .setBounds(409, 109, 412, 22);
		add(separatorComboBox );
		separatorComboBox.addActionListener(this);
        
	}
	
	public void setupSkillsTable()
	{

		// Here we set up the model

		String str[] =
		{ "User ID", "Name", "Surname", "Skill ID", "Skill Name", "Knowledgeable", "Standard of Work", "Autonomy", "Coping with Complexity", "Perception of Context",
				"Growing Capability", "Purposeful Collaboration", "Overall Rating" };
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
				if (column == 0 || column == 1 || column == 2 || column == 3 || column == 4 ||  column == 12)
				{
					return false;
				} 
				else
				{
					return true;
				}

			}

		};

		// Setup the model

		
	}
	
	public void setCustomTableElement(Object aValue, int row, int column)
	{

		Skill skl = skillList.get(row);

		switch (column)
		{
		case 0:
			try
			{
				skl.setSkillID(Integer.parseInt(aValue.toString()));
			} catch (NumberFormatException e)
			{
				model.setValueAt(skl.getSkillID(), row, column);

			}
			break;
		case 1:
			skl.setSkillName(aValue.toString());
			break;
//		case 2:
//			skl.setSkillVendor(aValue.toString());
//			break;
//		case 3:
//			skl.setSkillDescription(aValue.toString());
//			break;

		}
		System.out.println(aValue + " " + row + " " + column);

		// skillList.SaveToDisk();
	}
	

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		int skill;
		User tempUser = new User();
		String skillName;
		Object rating [] = new Object[model.getColumnCount()];
		ratee = new Rating();
//		Vector<Rating> rateVect	= new Vector<Rating>();
		
		
		if(source == btnSubmit)
		{
			
			
			for (int i = 0; i < model.getRowCount(); i++)
			{
				System.out.println("number of rows: " + model.getRowCount());
				
				
				if(model.getValueAt(i, 5) != null)
				{
					for(int count = 0; count < model.getColumnCount(); count++)
					{
						rating[count] = model.getValueAt(i, count);	
					}
					
					 
					ratee.setKnowledge(Integer.parseInt((String)rating[5]));	
					ratee.setWorkStandard(Integer.parseInt((String)rating[6]));
					ratee.setAutonomy(Integer.parseInt((String)rating[7]));
					ratee.setComplexityCoping(Integer.parseInt((String)rating[8]));
					ratee.setContextPerception(Integer.parseInt((String)rating[9]));
					ratee.setCapabilityGrowing(Integer.parseInt((String)rating[10]));
					ratee.setCollaboration(Integer.parseInt((String)rating[11]));
					
//					System.out.println("value of knowledge: " + (String)rating[5]);

					ratee.setRaterID(baseFrame.authenticatedUser.getUserName());
					ratee.setSkillID((int)rating[3]); 
					ratee.setUserID((String)rating[0]);

					int level = 0; 
//					level = (ratee.getKnowledge() + ratee.getWorkStandard() + ratee.getAutonomy() + ratee.getComplexityCoping() + ratee.getContextPerception() 
//							+ ratee.getCapabilityGrowing() + ratee.getCollaboration()) / 7;
					level = (Integer.parseInt((String)rating[5]) + Integer.parseInt((String)rating[6]) + Integer.parseInt((String)rating[7]) + Integer.parseInt((String)rating[8]) + 
							Integer.parseInt((String)rating[9]) + Integer.parseInt((String)rating[10]) + Integer.parseInt((String)rating[11])) / 7 ;
					ratee.setLevel(level);
					System.out.println("Level is: " + (ratee.getKnowledge() + ratee.getWorkStandard() + ratee.getAutonomy() + ratee.getComplexityCoping() + ratee.getContextPerception() 
							+ ratee.getCapabilityGrowing() + ratee.getCollaboration()) / 7);
					System.out.println("level: " + level + " skill id: " + ratee.getSkillID());
					table.setValueAt(level, i, 12);
					baseFrame.setNetAddRating(ratee);
					
				}
				
				
		
			}	 
			JOptionPane.showMessageDialog(this, "Rating submitted");
		}
		
		if(source == separatorComboBox)
		{
			Vector<Rating> userRatings1 = new Vector<Rating>();
//			tempUser = baseFrame.getNetUser(searchField.getText());
 
// this between stars replaced by....
//			************************************************************
//			String userName = (String)separatorComboBox.getSelectedItem();
//
//			StringTokenizer tokenizer = new StringTokenizer(userName, " ");
//			String token1;
//			String token2; 
//			tokenizer.hasMoreTokens();
//			token1 = tokenizer.nextToken();
//			System.out.println("NAME " + token1);
//			tokenizer.hasMoreTokens();
//			token2 = tokenizer.nextToken();
//			System.out.println("SURNAME " + token2); 
//			for (int i = 0; i < baseFrame.data_userList.size(); i++)
//			{
//				if ((baseFrame.data_userList.get(i).getFirstName().equals(token1)) && (baseFrame.data_userList.get(i).getSurname().equals(token2)))
//				{
//					userName = baseFrame.data_userList.get(i).getUserName();
//				}
//			} 
//			tempUser = baseFrame.getNetUser(userName);
//  ************************************************************
			
			// get me a user from get me the correct selection
			tempUser = baseFrame.getNetUser (   itemsName.get(   separatorComboBox.getSelectedIndex() )   );
			
//			userRatings1 = baseFrame.getNetUserRating(tempUser);
//			System.out.println("ratings " + userRatings1);
			model = (DefaultTableModel) table.getModel();
			model.setRowCount(0);
			try
			{
				baseFrame.getNetSkillList();
				skillList = baseFrame.data_skillList;
				Vector<UserSkill> userSkills = new Vector<UserSkill>();
				userSkills = baseFrame.getNetUserSkills(tempUser);
				
				for (int t = 0; t < userSkills.size(); t++)
				{
					skill = userSkills.get(t).getSkillID();
					
					for(int j = 0; j < skillList.size(); j++)
					{
						if(skill == skillList.get(j).getSkillID())
						{
							skillName = skillList.get(j).getSkillName();
							
							Object obj[] =
								{tempUser.getUserName(), tempUser.getFirstName(), tempUser.getSurname(), skill, skillName};
								model.addRow(obj);

						}
			

					}
				}
		
		
				btnSubmit.setEnabled(true);
			} 
			catch (Exception e1)
			{
				e1.printStackTrace();
			}
			
			
		}
		
	}
	
	public void getRateUser(String user)
	{
		User tempUser1 = new User();
		int skill1;
		String skillName1;
		Vector<Rating> userRatings = new Vector<Rating>();
		
		tempUser1 = baseFrame.getNetUser(user);
		model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		try
		{
			skillList = baseFrame.data_skillList;
			Vector<UserSkill> userSkills1 = new Vector<UserSkill>();
			userSkills1 = baseFrame.getNetUserSkills(tempUser1);
			
			for (int t = 0; t < userSkills1.size(); t++)
			{
				skill1 = userSkills1.get(t).getSkillID();
				
				for(int j = 0; j < skillList.size(); j++)
				{
					if(skill1 == skillList.get(j).getSkillID())
					{
						skillName1 = skillList.get(j).getSkillName();
						
						Object obj[] =
							{tempUser1.getUserName(), tempUser1.getFirstName(), tempUser1.getSurname(), skill1, skillName1};
							model.addRow(obj);

					}
				}
			}
			
			btnSubmit.setEnabled(true);
		} 
		catch (Exception e1)
		{
			e1.printStackTrace();
		}
	}
}
