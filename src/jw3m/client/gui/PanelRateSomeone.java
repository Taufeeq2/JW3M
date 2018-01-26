package jw3m.client.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import jw3m.beans.Rating;
import jw3m.beans.Skill;
import jw3m.beans.User;
import jw3m.beans.UserSkill;
import jw3m.dao.DAO;

import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

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
	private JTextField searchField;
	private JLabel lblSearch;
	private JButton btnSearch;
	private Rating ratee;
	
	public PanelRateSomeone(SkillsClient frame) {
		
		baseFrame = frame;
		lblRateSomeone = new JLabel("Rate Someone");
		lblRateSomeone.setBounds(409, 26, 134, 27);
		lblRateSomeone.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 22));
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(485, 173, 97, 29);
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
		
		searchField = new JTextField();
		searchField.setBounds(415, 107, 153, 26);
		searchField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		searchField.setColumns(10);
		
		lblSearch = new JLabel("Search ");
		lblSearch.setBounds(326, 110, 60, 20);
		lblSearch.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		btnSearch = new JButton("Search");
		btnSearch.setBounds(353, 173, 102, 29);
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSearch.addActionListener(this);
		
		setupSkillsTable();
		setLayout(null);

		table = new JTable(model);
	
		scrollPane = new JScrollPane(table);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(112, 273, 800, 300);
		add(scrollPane);
		scrollPane.setViewportView(table);
		add(scrollPane);
		add(lblRateSomeone);
		add(lblSearch);
		add(btnSubmit);
		add(searchField);
		add(btnSearch);
		
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
			for(int count = 0; count < model.getColumnCount(); count++)
			{
				rating[count] = model.getValueAt(table.getSelectedRow(), count);	
				//System.out.println(model.getValueAt(count, 0).toString());
			}

			ratee.setKnowledge(Integer.parseInt((String)rating[3]));
			ratee.setWorkStandard(Integer.parseInt((String)rating[4]));
			ratee.setAutonomy(Integer.parseInt((String)rating[5]));
			ratee.setComplexityCoping(Integer.parseInt((String)rating[6]));
			ratee.setContextPerception(Integer.parseInt((String)rating[7]));
			ratee.setCapabilityGrowing(Integer.parseInt((String)rating[8]));
			ratee.setCollaboration(Integer.parseInt((String)rating[9]));
			
			
//			System.out.println("2nd test " + ratee.getAutonomy());
			ratee.setRaterID(baseFrame.authenticatedUser.getUserName());
//			int skill1 = (Integer.parseInt((String)rating[1]));
			ratee.setSkillID((int)rating[1]); 
			ratee.setUserID((String)rating[0]);

			int level = 0; 
			level = (ratee.getKnowledge() + ratee.getWorkStandard() + ratee.getAutonomy() + ratee.getComplexityCoping() + ratee.getContextPerception() 
					+ ratee.getCapabilityGrowing() + ratee.getCollaboration()) / 7;
			ratee.setLevel(level);
			table.setValueAt(level, table.getSelectedRow(), 10);
			
			
			baseFrame.setNetAddRating(ratee);
			JOptionPane.showMessageDialog(this, "Rating submitted");
		}
		
		if(source == btnSearch)
		{
			Vector<Rating> userRatings1 = new Vector<Rating>();
			tempUser = baseFrame.getNetUser(searchField.getText());
			userRatings1 = baseFrame.getNetUserRating(tempUser);
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
				searchField.setText("");
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
			Vector<UserSkill> userSkills = new Vector<UserSkill>();
			userSkills = baseFrame.getNetUserSkills(tempUser1);
			
			for (int t = 0; t < userSkills.size(); t++)
			{
				skill1 = userSkills.get(t).getSkillID();
				
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
		} 
		catch (Exception e1)
		{
			e1.printStackTrace();
		}
	}
}
