package jw3m.client.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;

import jw3m.beans.Hobby;
import jw3m.beans.Rating;
import jw3m.beans.Skill;
import jw3m.beans.User;
import jw3m.beans.UserHobby;
import jw3m.beans.UserSkill;
import jw3m.dao.DAO;

import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.JComboBox;
import java.awt.Dimension;

public class PanelProfile extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private SkillsClient baseFrame;
	private JPanel panel;
	private JTable table;
	private DefaultTableModel model = null;
	private JLabel lblMySkills;
	private JButton btnRemoveSelectedSkill;
	private JComboBox comboBoxSkills;
	private JButton btnAddSelectedSkill;
	private Vector<Skill> skillList = new Vector<Skill>();
	private Vector<String> comboSkillNames = new Vector<String>();
	private Vector<Skill> tempSkillNames = new Vector<Skill>();
	private Vector<Skill> tempASkill = new Vector<Skill>();
	private Vector<UserSkill> userSkills = new Vector<UserSkill>();
	private Vector<UserSkill> tmpUserSkills = new Vector<UserSkill>();
	private Vector<String> allSkillVect = new Vector<String>();
	private JScrollPane scrollPane;
	private JLabel lblAddSkills;
	private JLabel lblRemoveSkills;
	private JLabel lblSkillSummary;
	private User tempUser = new User();
	private JButton btnCaptureSkill;
	private JLabel lblSkillName;
	private JLabel lblVendor;
	private JLabel lblSkillDescription;
	private JTextField textFieldSkillName;
	private JTextField textFieldVendor;
	private JTextField textFieldSkillDesc;
	private JButton btnAddSkill;
	private int skill = 0;
	private int skillIDAdd = 0;
	private int knowledge = 0;
	private int workStandard = 0;
	private int autonomy = 0;
	private int complexityCoping = 0;
	private int contextPerception = 0;
	private int capabilityGrowing = 0;
	private int collaboration = 0;
	private int aveSelfRating = 0;
	private int aveColleagueRating = 0;
	private String skillName = null;
	private Skill newSkill = null;
	private Skill selectedSkill = null;
	private Vector<Rating> ratingVect = new Vector<Rating>();
	private Vector<Rating> allRatingVect = new Vector<Rating>();

	public PanelProfile(SkillsClient frame)
	{
		setPreferredSize(new Dimension(1700, 650));
		baseFrame = frame;

		setLayout(null);

		panel = new JPanel();
		panel.setBounds(0, 13, 1688, 641);
		add(panel);
		panel.setLayout(null);

		lblMySkills = new JLabel("My Skills");
		lblMySkills.setFont(new Font("Calibri", Font.BOLD, 22));
		// lblMySkills.setFont(baseFrame.getFont());
		lblMySkills.setBounds(468, 0, 88, 30);
		panel.add(lblMySkills);

		btnRemoveSelectedSkill = new JButton("Remove selected skill from table below");
		btnRemoveSelectedSkill.setBounds(22, 66, 308, 25);
		panel.add(btnRemoveSelectedSkill);
		btnRemoveSelectedSkill.addActionListener(this);

		tempUser = baseFrame.authenticatedUser;

		tempSkillNames = baseFrame.data_skillList;

		tmpUserSkills = baseFrame.getNetUserSkills(tempUser);

		for (int i = 0; i < tempSkillNames.size(); i++)
		{
			for (int j = 0; j < tmpUserSkills.size(); j++)
			{
				if (tempSkillNames.elementAt(i).getSkillID().equals(tmpUserSkills.elementAt(j).getSkillID()))
				{
					tempSkillNames.removeElementAt(i);
					i = 0;
				}
			}
		}

		for (int k = 0; k < tempSkillNames.size(); k++)
		{

			comboSkillNames.add(tempSkillNames.get(k).getSkillName());

		}

		comboBoxSkills = new JComboBox(comboSkillNames);
		comboBoxSkills.setBounds(546, 67, 308, 22);
		panel.add(comboBoxSkills);

		btnAddSelectedSkill = new JButton("Add selected skill from dropdown");
		btnAddSelectedSkill.setBounds(546, 102, 308, 25);
		panel.add(btnAddSelectedSkill);
		btnAddSelectedSkill.addActionListener(this);

		// JScrollPane scrollpane = new JScrollPane(table);
		// scrollpane.setPreferredSize(new Dimension(480, 300));
		scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 344, 1654, 284);
		// panel.add(scrollPane);
		// add(scrollPane);
		// scrollPane.setViewportView(table);

		setupSkillsTable();
		setLayout(null);

		// Set up the columns of the Jtable to be sortable
		table = new JTable(model);

		panel.add(scrollPane);
		scrollPane.setViewportView(table);
		// add(scrollPane);

		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
		table.setRowSorter(sorter);

		lblAddSkills = new JLabel("Add Skills");
		lblAddSkills.setFont(new Font("Calibri", Font.BOLD, 18));
		lblAddSkills.setBounds(701, 37, 112, 16);
		panel.add(lblAddSkills);

		lblRemoveSkills = new JLabel("Remove Skills");
		lblRemoveSkills.setFont(new Font("Calibri", Font.BOLD, 18));
		lblRemoveSkills.setBounds(121, 37, 120, 16);
		panel.add(lblRemoveSkills);

		lblSkillSummary = new JLabel("Skill Summary");
		lblSkillSummary.setFont(new Font("Calibri", Font.BOLD, 22));
		lblSkillSummary.setBounds(401, 288, 141, 30);
		panel.add(lblSkillSummary);

		btnCaptureSkill = new JButton("Click to capture a skill not on the dropdown");
		btnCaptureSkill.setBounds(546, 140, 308, 25);
		panel.add(btnCaptureSkill);
		btnCaptureSkill.addActionListener(this);

	}

	public void setupSkillsTable()
	{

		// Here we set up the model

		String str[] =
		{ "User ID", "Skill ID", "Skill Name", "Knowledgeable", "Standard of Work", "Autonomy",
				"Coping with Complexity", "Perception of Context", "Growing Capability", "Purposeful Collaboration",
				"Average Self-Rating", "Ave. Colleague Rating" };

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
				// make read only field columns 0, 1, 2, 10, 11 - other columns
				// directly editable
				if (column == 0 || column == 1 || column == 2 || column == 10 || column == 11)
				{
					return false;
				} else
				{
					return true;
				}

			}

		};

		// populate the model for the table
		tempUser = baseFrame.authenticatedUser;
		model.setRowCount(0);
		try
		{
			baseFrame.getNetSkillList();
			skillList = baseFrame.data_skillList;

			userSkills = baseFrame.getNetUserSkills(tempUser);

			for (int t = 0; t < userSkills.size(); t++)
			{
				skill = userSkills.get(t).getSkillID();

				for (int j = 0; j < skillList.size(); j++)
				{
					if (skill == skillList.get(j).getSkillID())
					{
						skillName = skillList.get(j).getSkillName();
						// *******************************************************************

						newSkill = new Skill();
						newSkill.setSkillID(skillList.get(j).getSkillID());
						newSkill.setSkillName(skillList.get(j).getSkillName());
						newSkill.setSkillDescription(skillList.get(j).getSkillDescription());
						newSkill.setSkillVendor(skillList.get(j).getSkillVendor());
						ratingVect = baseFrame.getNetSkillRating(newSkill);

						// *******************************************************************
						if (ratingVect.size() > 0)
						{
							knowledge = ratingVect.firstElement().getKnowledge();
							workStandard = ratingVect.firstElement().getWorkStandard();
							autonomy = ratingVect.firstElement().getAutonomy();
							complexityCoping = ratingVect.firstElement().getComplexityCoping();
							contextPerception = ratingVect.firstElement().getContextPerception();
							capabilityGrowing = ratingVect.firstElement().getCapabilityGrowing();
							collaboration = ratingVect.firstElement().getCollaboration();
							aveSelfRating = (int) ((knowledge + workStandard + autonomy + complexityCoping
									+ contextPerception + capabilityGrowing + collaboration) / 7 + 0.5);
							aveColleagueRating = 0;
						} else
						{
							knowledge = 0;
							workStandard = 0;
							autonomy = 0;
							complexityCoping = 0;
							contextPerception = 0;
							capabilityGrowing = 0;
							collaboration = 0;
							aveSelfRating = 0;
							aveColleagueRating = 0;
						}

						Object obj[] =
						{ tempUser.getUserName(), skill, skillName, knowledge, workStandard, autonomy, complexityCoping,
								contextPerception, capabilityGrowing, collaboration, aveSelfRating,
								aveColleagueRating };
						model.addRow(obj);

					}

				}
			}

		} catch (Exception e1)
		{
			e1.printStackTrace();
		}

	}

	public void setCustomTableElement(Object aValue, int row, int column)
	{
		
		row = table.getSelectedRow();
		
		selectedSkill = new Skill();
		selectedSkill.setSkillID(skillList.get(row).getSkillID());
		selectedSkill.setSkillName(skillList.get(row).getSkillName());
		selectedSkill.setSkillDescription(skillList.get(row).getSkillDescription());
		selectedSkill.setSkillVendor(skillList.get(row).getSkillVendor());
		allRatingVect = baseFrame.getNetSkillRating(selectedSkill);
		
		
		Rating rt1 = allRatingVect.get(row);

		switch (column)
		{
		case 3:
			try
			{
				rt1.setKnowledge(Integer.parseInt(aValue.toString()));
			} catch (NumberFormatException e)
			{
				model.setValueAt(rt1.getKnowledge(), row, column);
				
			}
			break;
		case 4:
			try
			{
				rt1.setWorkStandard(column);
			} catch (NumberFormatException e)
			{
				model.setValueAt(rt1.getWorkStandard(), row, column);

			}
			break;	
		case 5:
			try
			{
				rt1.setAutonomy(column);
			} catch (NumberFormatException e)
			{
				model.setValueAt(rt1.getAutonomy(), row, column);

			}
			break;
		case 6:
			try
			{
				rt1.setComplexityCoping(column);
			} catch (NumberFormatException e)
			{
				model.setValueAt(rt1.getComplexityCoping(), row, column);

			}
			break;
		case 7:
			try
			{
				rt1.setContextPerception(column);
			} catch (NumberFormatException e)
			{
				model.setValueAt(rt1.getContextPerception(), row, column);

			}
			break;
		case 8:
			try
			{
				rt1.setCapabilityGrowing(column);
			} catch (NumberFormatException e)
			{
				model.setValueAt(rt1.getCapabilityGrowing(), row, column);

			}
			break;
		case 9:
			try
			{
				rt1.setCollaboration(column);;
			} catch (NumberFormatException e)
			{
				model.setValueAt(rt1.getCollaboration(), row, column);

			}
			break;

		}
		
	}

	public void populateComboBox()
	{
		comboSkillNames.removeAllElements();

		tempSkillNames = baseFrame.data_skillList;

		tmpUserSkills = baseFrame.getNetUserSkills(tempUser);

		for (int i = 0; i < tempSkillNames.size(); i++)
		{
			for (int j = 0; j < tmpUserSkills.size(); j++)
			{
				if (tempSkillNames.elementAt(i).getSkillID().equals(tmpUserSkills.elementAt(j).getSkillID()))
				{
					tempSkillNames.removeElementAt(i);
					i = 0;
				}
			}
		}

		for (int k = 0; k < tempSkillNames.size(); k++)
		{

			comboSkillNames.add(tempSkillNames.get(k).getSkillName());

		}

		DefaultComboBoxModel cboNewModel = new DefaultComboBoxModel(comboSkillNames);
		comboBoxSkills.setModel(cboNewModel);

	}

	@Override
	public void actionPerformed(ActionEvent ae)
	{
		Object source = ae.getSource();
		int skill, rating = 0;
		String skillName = null, skillDesc = null;
		User tempUser = new User();
		String tmpUser = null;
		tmpUser = baseFrame.authenticatedUser.getUserName();
		tempUser = baseFrame.authenticatedUser;
		
		int i = table.getSelectedRow();    // set index for selected row
		int j = table.getSelectedColumn(); // set index for selected column
		
		//*****************************************************************************
		
			// Add prompt text where ratings are not populated
		
			// get current value
		
			/*int k = table.getHeight();
			int l = table.getWidth();
			
		      String value = table.getValueAt(8, 3).toString();
		      
		      System.out.println("selected value ----- >>>> " + value);

		      // append new value
		      value = "1 to 5";  // set prompt text
		      table.setValueAt(value, 9, 3);
		   */
		
		
		//*****************************************************************************

		

		if (source == btnRemoveSelectedSkill && i >= 0) // if nothing selected
														// its -1
		{

			try
			{
				DAO getSkill = new DAO();

				userSkills = baseFrame.getNetUserSkills(tempUser);
				skill = userSkills.get(i).getSkillID();

				getSkill.removeUserSkills(tmpUser, skill);

				setupSkillsTable();

				table = new JTable(model);
				// Set up the columns of the Jtable to be sortable
				table = new JTable(model);
				RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
				table.setRowSorter(sorter);

				add(scrollPane);

				scrollPane.setViewportView(table);

				// this.panel.validate();
				// this.panel.repaint();

				populateComboBox();

			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} // end remove button

		if (source == btnAddSelectedSkill) // top skill selected by default unless changed
		{
			try
			{
				DAO getSkill = new DAO();
				Vector<Skill> tempASkill = new Vector<Skill>();

				baseFrame.getNetSkillList();
				tempASkill = baseFrame.data_skillList;
				
				for (int m = 0; m < tempASkill.size(); m++)
				{
					allSkillVect.add(tempASkill.elementAt(m).getSkillName());
				}
				int index = allSkillVect.indexOf(comboBoxSkills.getSelectedItem().toString());
				
				skillIDAdd = tempASkill.get(index).getSkillID();

				getSkill.addUserSkills(tmpUser, skillIDAdd);
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
		} // end add button

		if (source == btnCaptureSkill)
		{
			lblSkillName = new JLabel("Skill Name");
			lblSkillName.setBounds(546, 194, 80, 16);
			panel.add(lblSkillName);

			lblVendor = new JLabel("Vendor");
			lblVendor.setBounds(546, 252, 56, 16);
			panel.add(lblVendor);

			lblSkillDescription = new JLabel("Skill Description");
			lblSkillDescription.setBounds(546, 223, 101, 16);
			panel.add(lblSkillDescription);

			textFieldSkillName = new JTextField();
			textFieldSkillName.setBounds(669, 191, 326, 22);
			panel.add(textFieldSkillName);
			textFieldSkillName.setColumns(10);

			textFieldVendor = new JTextField();
			textFieldVendor.setBounds(669, 255, 326, 22);
			panel.add(textFieldVendor);
			textFieldVendor.setColumns(10);

			textFieldSkillDesc = new JTextField();
			textFieldSkillDesc.setBounds(669, 223, 326, 22);
			panel.add(textFieldSkillDesc);
			textFieldSkillDesc.setColumns(10);

			btnAddSkill = new JButton("Add Skill");
			btnAddSkill.setBounds(669, 292, 97, 25);
			panel.add(btnAddSkill);
			btnAddSkill.addActionListener(this);

			this.panel.validate();
			this.panel.repaint();
		} // end capture skill button

		if (source == btnAddSkill) // add a skill not on the dropdown (new
									// skill)
		{
			try
			{
				DAO getSkill = new DAO();

				newSkill = new Skill();
				newSkill.setSkillID(100);
				newSkill.setSkillName(textFieldSkillName.getText());
				newSkill.setSkillDescription(textFieldSkillDesc.getText());
				newSkill.setSkillVendor(textFieldVendor.getText());

				if (textFieldSkillName.getText().equals("") || textFieldSkillDesc.getText().equals("")
						|| textFieldVendor.getText().equals(""))
				{
					JOptionPane.showMessageDialog(this, "Fill in all the fields!");
				} else
				{
					getSkill.addSkillList(newSkill);

					Vector<Skill> tempASkill = new Vector<Skill>();

					baseFrame.getNetSkillList();
					tempASkill = baseFrame.data_skillList;

					for (int m = 0; m < tempASkill.size(); m++)
					{
						allSkillVect.add(tempASkill.elementAt(m).getSkillName());
					}

					int index = allSkillVect.indexOf(textFieldSkillName.getText());

					skillIDAdd = tempASkill.get(index).getSkillID();

					getSkill.addUserSkills(tmpUser, skillIDAdd);
					setupSkillsTable();

					table = new JTable(model);

					add(scrollPane);

					scrollPane.setViewportView(table);

					populateComboBox();

					lblSkillName.setVisible(false);
					lblVendor.setVisible(false);
					lblSkillDescription.setVisible(false);
					textFieldSkillName.setVisible(false);
					textFieldSkillDesc.setVisible(false);
					textFieldVendor.setVisible(false);
				}
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
