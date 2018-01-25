package jw3m.client.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JRadioButton;

import javax.swing.JButton;
import javax.swing.JTextArea;

import jw3m.beans.Hobby;
import jw3m.beans.Skill;
import jw3m.beans.User;
import jw3m.beans.UserHobby;
import jw3m.beans.UserSkill;
import jw3m.dao.DAO;

import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JComboBox;

public class PanelProfile extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private SkillsClient baseFrame;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private User user = new User();
	private JPanel panel;
	private JTable table;
	private DefaultTableModel model = null;
	private JLabel lblMySkills;
	private JButton btnRemoveSelectedSkill;
	private JComboBox comboBoxSkills;
	private JButton btnAddSelectedSkill;
	private Vector<Skill> skillList = new Vector<Skill>();
	private Vector<Skill> skillNames = new Vector<Skill>();
	Vector<Skill> skillNms = new Vector<Skill>();
	private JScrollPane scrollPane;

	public PanelProfile(SkillsClient frame)
	{
		baseFrame = frame;

		setLayout(null);

		panel = new JPanel();
		panel.setBounds(0, 13, 1031, 496);
		add(panel);
		panel.setLayout(null);

		lblMySkills = new JLabel("My Skills");
		lblMySkills.setFont(new Font("Calibri", Font.BOLD, 22));
		lblMySkills.setBounds(468, 0, 88, 30);
		panel.add(lblMySkills);

		btnRemoveSelectedSkill = new JButton("Remove selected skill from above table");
		btnRemoveSelectedSkill.setBounds(22, 158, 308, 25);
		panel.add(btnRemoveSelectedSkill);

		//Vector<Skill> skillNms = new Vector<Skill>();
		baseFrame.getNetSkillList();
		skillNms = baseFrame.data_skillList;

		for (int i = 0; i < skillNms.size(); i++)
		{
			skillNames.add(skillNms.get(i));
		}

		comboBoxSkills = new JComboBox(skillNames);
		comboBoxSkills.setBounds(22, 207, 308, 22);
		panel.add(comboBoxSkills);

		btnAddSelectedSkill = new JButton("Add selected skill from dropdown");
		btnAddSelectedSkill.setBounds(22, 242, 308, 25);
		panel.add(btnAddSelectedSkill);

		// table = new JTable(model);
		// scrollPane.setViewportView(table);

	
		setupSkillsTable();
		setLayout(null);

		table = new JTable(model);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 33, 973, 112);
		panel.add(scrollPane);
		scrollPane.setViewportView(table);

		User tempUser = new User();
		int skill;
		String skillName;

		tempUser = baseFrame.authenticatedUser;
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

				for (int j = 0; j < skillList.size(); j++)
				{
					if (skill == skillList.get(j).getSkillID())
					{
						skillName = skillList.get(j).getSkillName();

						Object obj[] =
						{ tempUser.getUserName(), skill, skillName };
						model.addRow(obj);

					}

				}
			}

		} catch (Exception e1)
		{
			e1.printStackTrace();
		}

	}

	public void setupSkillsTable()
	{

		// Here we set up the model

		String str[] =
		{ "User ID", "Skill ID", "Skill Name", "Knowledgeable", "Standard of Work", "Autonomy",
				"Coping with Complexity", "Perception of Context", "Growing Capability", "Purposeful Collaboration",
				"Overall Rating(Level)" };
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
				} else
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
		// case 2:
		// skl.setSkillVendor(aValue.toString());
		// break;
		// case 3:
		// skl.setSkillDescription(aValue.toString());
		// break;

		}
		System.out.println(aValue + " " + row + " " + column);

	}

	public void populateComboBox()
	{
		try
		{
			baseFrame.getNetSkillList();
			skillNms = baseFrame.data_skillList;

			for (int i = 0; i < skillNms.size(); i++)
			{
				skillNames.add(skillNms.get(i));
			}
			
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DefaultComboBoxModel cboNewModel = new DefaultComboBoxModel(skillNames);
		comboBoxSkills.setModel(cboNewModel);
		// comboBoxSkills.showPopup();

	}

	@Override
	public void actionPerformed(ActionEvent ae)
	{
		Object source = ae.getSource();
		int skill, rating = 0;
		String skillName = null, skillDesc = null;
		User tempUser = new User();
		String tmpUser = null;
		tmpUser = baseFrame.authenticatedUser.toString();
		tempUser = baseFrame.authenticatedUser;
		
		int i = table.getSelectedRow(); // set index for selected row

		if (source == btnRemoveSelectedSkill && i >= 0) // if nothing selected
														// its -1
		{

			try
			{
				DAO getSkill = new DAO();
				skillList = baseFrame.data_skillList;
				
				getSkill.removeUserSkills(tmpUser, skillList.get(i).getSkillID());
				
				skillList = baseFrame.data_skillList;
				Vector<UserSkill> userSkills = new Vector<UserSkill>();
				userSkills = baseFrame.getNetUserSkills(tempUser);
				

				for (int t = 0; t < userSkills.size(); t++)
				{
					skill = userSkills.get(t).getSkillID();

					for (int j = 0; j < skillList.size(); j++)
					{
						if (skill == skillList.get(j).getSkillID())
						{
							skillName = skillList.get(j).getSkillName();

							Object obj[] =
							{ tempUser.getUserName(), skill, skillName };
							model.addRow(obj);

						}

					}
				}
				
				setupSkillsTable();

				table = new JTable(model);

				add(scrollPane);

				scrollPane.setViewportView(table);

				populateComboBox();
				

			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} // end remove button

	}
}
