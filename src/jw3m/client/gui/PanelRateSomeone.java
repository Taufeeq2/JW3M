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

public class PanelRateSomeone extends JPanel implements ActionListener
{
	private SkillsClient baseFrame;
	private JLabel lblRateSomeone;
	private JButton btnSubmit;
	private JLabel lblDisplayRatee;
	private JLabel lblIAmRating;
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
		lblRateSomeone.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 22));
		
		btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(this);
		
		lblDisplayRatee = new JLabel("Display ratee");
		lblDisplayRatee.setFont(new Font("Tahoma", Font.BOLD, 16));
		
//		lblDisplayRatee.setText(baseFrame.authenticatedUser.getFirstName());
		
		lblIAmRating = new JLabel("I am rating:");
		lblIAmRating.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		Vector<Skill> skillNms = new Vector<Skill>();
		
		 
		
		try
		{
			DAO getSkill = new DAO();
			skillNms = getSkill.getSkillList();
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.out.println(skillNms);
		
		for (int i = 0 ; i < skillNms.size() ; i++)
		{
			skillNames.add(skillNms.get(i));
		}
		
		searchField = new JTextField();
		searchField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		searchField.setColumns(10);
		
		lblSearch = new JLabel("Search ");
		lblSearch.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(this);
		
		

		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(407)
					.addComponent(lblRateSomeone))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(65)
					.addComponent(lblIAmRating, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
					.addGap(8)
					.addComponent(lblDisplayRatee, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblSearch))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addGap(334)
							.addComponent(btnSubmit, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)))
					.addGap(32)
					.addComponent(searchField, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
					.addGap(332))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(512, Short.MAX_VALUE)
					.addComponent(btnSearch)
					.addGap(363))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(13)
					.addComponent(lblRateSomeone)
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblIAmRating, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDisplayRatee, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(searchField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSearch))
					.addGap(18)
					.addComponent(btnSearch)
					.addGap(369)
					.addComponent(btnSubmit))
		);
		
		setupSkillsTable();

		table = new JTable(model);
	
		scrollPane = new JScrollPane(table);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(200, 200, 800, 300);
		add(scrollPane);
		
		setLayout(groupLayout);
		scrollPane.setViewportView(table);
		
	}
	
	public void setupSkillsTable()
	{

		// Here we set up the model

		String str[] =
		{ "User ID", "Skill ID", "Skill Name", "Knowledgeable", "Standard of Work", "Autonomy", "Coping with Complexity", "Perception of Context",
				"Growing Capability", "Purposeful Collaboration", "Overall Rating(Level)" };
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
				if (column == 0 || column == 1 || column == 2)
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
			
//			System.out.println("Test " + ratee.getAutonomy());
			
			ratee.setCapabilityGrowing(Integer.parseInt((String)rating[8]));
			ratee.setCollaboration(Integer.parseInt((String)rating[9]));
			ratee.setLevel(Integer.parseInt((String)rating[10]));
			
//			System.out.println("2nd test " + ratee.getAutonomy());
			ratee.setRaterID((String)rating[0]);
//			int skill1 = (Integer.parseInt((String)rating[1]));
			ratee.setSkillID((int)rating[1]); 
			ratee.setUserID(baseFrame.authenticatedUser.getUserName());
			baseFrame.dao.setRating(ratee);
			JOptionPane.showMessageDialog(this, "Rating submitted");
		}
		
		if(source == btnSearch)
		{
			tempUser = baseFrame.dao.getUser(searchField.getText());
			model = (DefaultTableModel) table.getModel();
			model.setRowCount(0);
			try
			{
				DAO getSkill = new DAO();
				skillList = getSkill.getSkillList();
				Vector<UserSkill> userSkills = new Vector<UserSkill>();
				userSkills = getSkill.getUserSkills(tempUser);
				
				for (int t = 0; t < userSkills.size(); t++)
				{
					skill = userSkills.get(t).getSkillID();
					
					for(int j = 0; j < skillList.size(); j++)
					{
						if(skill == skillList.get(j).getSkillID())
						{
							skillName = skillList.get(j).getSkillName();
							
							Object obj[] =
								{tempUser.getUserName(), skill, skillName};
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
}
