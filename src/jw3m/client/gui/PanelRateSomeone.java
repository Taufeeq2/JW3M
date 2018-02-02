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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.w3c.dom.events.EventTarget;
import org.w3c.dom.events.MouseEvent;
import org.w3c.dom.views.AbstractView;
import jw3m.beans.*;
import jw3m.dao.DAO;
import jw3m.widgets.SeparatorComboBox;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JComboBox;
import jw3m.widgets.SeparatorComboBox;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class PanelRateSomeone extends JPanel implements ActionListener
{
	final static Logger logger = Logger.getLogger(PanelMyProfile.class);
	private SkillsClient baseFrame;
	private JLabel lblRateSomeone, lblBanner;
	private JButton btnSubmit;
	private DefaultTableModel model = null;
	private JScrollPane scrollPane;
	private JTable table;
	private Vector<Skill> skillList = new Vector<Skill>();
	private Vector<Skill> skillNames = new Vector<Skill>();
	private JLabel lblSearch;
	private Rating ratee;
	private JComboBox separatorComboBox ;
	private JPanel nPanel, cPanel;
	private Vector itemsText = new Vector();
	private Vector<String> itemsName = new Vector<String>();
	private Font primaryFont, secondaryFont;
	private JButton btnClear;
	
	@SuppressWarnings("unchecked")
	public PanelRateSomeone(SkillsClient frame) {
		
		baseFrame = frame;
		primaryFont = baseFrame.getPrimaryFont();
        secondaryFont = baseFrame.getSecondaryFont();
        PropertyConfigurator.configure("log4j.properties");
        
        nPanel = new JPanel();
		cPanel = new JPanel();
        
//		lblRateSomeone = new JLabel("Rate Someone");
//		lblRateSomeone.setFont(secondaryFont);

		lblBanner = new JLabel(new ImageIcon("resources/"));
		nPanel.add(lblBanner);
		
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(690, 484, 110, 25);
		btnSubmit.setFont(primaryFont);
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
		lblSearch.setBounds(513, 71, 73, 16);
		lblSearch.setFont(primaryFont);
		
		setupSkillsTable();
		setLayout(new BorderLayout(0, 0));
		cPanel.setLayout(null);

		table = new JTable(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFont(primaryFont);

		
		
		for (int col = 5; col < table.getColumnCount(); col++)
		{
			TableColumn cm = table.getColumnModel().getColumn(col);
			Vector<Integer> rateVect = new Vector<Integer>();
			rateVect.addElement(1);
			rateVect.addElement(2);
			rateVect.addElement(3);
			rateVect.addElement(4);
			rateVect.addElement(5);
			
			JComboBox ratings = new JComboBox(rateVect);

			cm.setCellEditor(new DefaultCellEditor(ratings));
		}
		

		
		

		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(12, 143, 1668, 293);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		cPanel.add(scrollPane);
		scrollPane.setViewportView(table);
		cPanel.add(scrollPane);
//		nPanel.add(lblRateSomeone);
		cPanel.add(lblSearch);
		cPanel.add(btnSubmit);
		
		
		this.add(nPanel, BorderLayout.NORTH);
		this.add(cPanel, BorderLayout.CENTER);
		
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
        separatorComboBox.setBounds(630, 68, 367, 22);
        separatorComboBox.setFont(primaryFont);
		cPanel.add(separatorComboBox );
		
		btnClear = new JButton("Clear");
		btnClear.setBounds(847, 484, 110, 25);
		btnClear.setFont(primaryFont);
		cPanel.add(btnClear);
		
		separatorComboBox.addActionListener(this);
		btnClear.addActionListener(this);
        
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
					
					ratee.setKnowledge((int)rating[5]);	
					ratee.setWorkStandard((int)rating[6]);
					ratee.setAutonomy((int)rating[7]);
					ratee.setComplexityCoping((int)rating[8]);
					ratee.setContextPerception((int)rating[9]);
					ratee.setCapabilityGrowing((int)rating[10]);
					ratee.setCollaboration((int)rating[11]);

					ratee.setRaterID(baseFrame.authenticatedUser.getUserName());
					ratee.setSkillID((int)rating[3]); 
					ratee.setUserID((String)rating[0]);

					int level = 0; 

					level = ((int)rating[5] + (int)rating[6] + (int)rating[7] + (int)rating[8] + 
							(int)rating[9] + (int)rating[10] + (int)rating[11]) / 7 ;
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
			Vector<Rating> userRatings = new Vector<Rating>();

			// get me a user from get me the correct selection
			tempUser = baseFrame.getNetUser (   itemsName.get(   separatorComboBox.getSelectedIndex() )   );
			Rating tempRating = new Rating();
			userRatings = baseFrame.getNetUserRating(tempUser);
			
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
							
							for (int j2 = 0; j2 < userRatings.size(); j2++)
							{
								if((skill == userRatings.get(j2).getSkillID()) && (baseFrame.authenticatedUser.getUserName().equals(userRatings.get(j2).getRaterID())))
								{
									tempRating.setKnowledge(userRatings.get(j2).getKnowledge());
									tempRating.setWorkStandard(userRatings.get(j2).getWorkStandard());
									tempRating.setAutonomy(userRatings.get(j2).getAutonomy());
									tempRating.setComplexityCoping(userRatings.get(j2).getComplexityCoping());
									tempRating.setContextPerception(userRatings.get(j2).getContextPerception());
									tempRating.setCapabilityGrowing(userRatings.get(j2).getCapabilityGrowing());
									tempRating.setCollaboration(userRatings.get(j2).getCollaboration());
									tempRating.setLevel(userRatings.get(j2).getLevel());
									
								}
							}
							
							Object obj[] =
								{tempUser.getUserName(), tempUser.getFirstName(), tempUser.getSurname(), skill, skillName, tempRating.getKnowledge(), 
										tempRating.getWorkStandard(), tempRating.getAutonomy(), tempRating.getComplexityCoping(), tempRating.getContextPerception(), 
										tempRating.getCapabilityGrowing(), tempRating.getCollaboration(), tempRating.getLevel()};
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
		
		if(source == btnClear)
		{
			model.setRowCount(0);
			
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
