package jw3m.client.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import jw3m.beans.Hobby;
import jw3m.beans.Rating;
import jw3m.beans.Skill;
import jw3m.beans.User;
import jw3m.beans.UserHobby;
import jw3m.beans.UserSkill;
import jw3m.client.gui.SkillsClient;
import jw3m.dao.DAO;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import javax.swing.ScrollPaneConstants;

public class PanelProfile extends JPanel implements ActionListener
{
	final static Logger logger = Logger.getLogger(PanelProfile.class);

	private Font primaryFont, secondaryFont;

	private static final long serialVersionUID = 1L;
	private SkillsClient baseFrame;
	private JPanel centrePanel, northPanel, westPanel;
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
		baseFrame = frame;

		primaryFont = baseFrame.getPrimaryFont();
		secondaryFont = baseFrame.getSecondaryFont();

		setBackground(UIManager.getColor("Button.background"));
		setForeground(Color.LIGHT_GRAY);

		northPanel = new JPanel();
		centrePanel = new JPanel();
		westPanel = new JPanel();

		lblMySkills = new JLabel("My Skills");
		lblMySkills.setBounds(335, 13, 114, 16);
		lblMySkills.setFont(primaryFont);
		northPanel.add(lblMySkills);
		setLayout(new BorderLayout(0, 0));

		lblSkillSummary = new JLabel("Skill Summary");
		lblSkillSummary.setFont(primaryFont);

		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		setupSkillsTable();
		
		table = new JTable(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		centrePanel.add(scrollPane);
		scrollPane.setViewportView(table);
		centrePanel.add(scrollPane);
		
		//****************************************************************

		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
		table.setRowSorter(sorter);
		
		//****************************************************************

		this.add(northPanel, BorderLayout.NORTH);
		this.add(centrePanel, BorderLayout.CENTER);
		GroupLayout gl_centrePanel = new GroupLayout(centrePanel);
		gl_centrePanel.setHorizontalGroup(gl_centrePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_centrePanel.createSequentialGroup().addGap(385).addComponent(lblSkillSummary,
						GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_centrePanel.createSequentialGroup().addGap(12).addComponent(scrollPane,
						GroupLayout.PREFERRED_SIZE, 816, GroupLayout.PREFERRED_SIZE)));
		gl_centrePanel.setVerticalGroup(gl_centrePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_centrePanel.createSequentialGroup().addGap(13)
						.addComponent(lblSkillSummary, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addGap(23)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 422, GroupLayout.PREFERRED_SIZE)));
		centrePanel.setLayout(gl_centrePanel);
		this.add(westPanel, BorderLayout.WEST);

		btnAddSelectedSkill = new JButton("Add Selected Skill from DropDown");
		btnAddSelectedSkill.setFont(new Font("Calibri", Font.ITALIC, 15));
		//btnAddSelectedSkill.setFont(secondaryFont);
		btnAddSelectedSkill.addActionListener(this);

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

		GroupLayout gl_westPanel = new GroupLayout(westPanel);
		gl_westPanel.setHorizontalGroup(gl_westPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_westPanel
				.createSequentialGroup().addGap(21)
				.addGroup(gl_westPanel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_westPanel.createSequentialGroup()
								.addComponent(comboBoxSkills, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGap(12))
						.addComponent(btnAddSelectedSkill))));
		gl_westPanel.setVerticalGroup(gl_westPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_westPanel.createSequentialGroup().addGap(6)
						.addComponent(comboBoxSkills, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(41).addComponent(btnAddSelectedSkill).addContainerGap(623, Short.MAX_VALUE)));
		westPanel.setLayout(gl_westPanel);

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
				rt1.setCollaboration(column);
				;
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

		int i = table.getSelectedRow(); // set index for selected row
		int j = table.getSelectedColumn(); // set index for selected column

		// *****************************************************************************

		// Add prompt text where ratings are not populated

		// get current value

		/*
		 * int k = table.getHeight(); int l = table.getWidth();
		 * 
		 * String value = table.getValueAt(8, 3).toString();
		 * 
		 * System.out.println("selected value ----- >>>> " + value);
		 * 
		 * // append new value value = "1 to 5"; // set prompt text
		 * table.setValueAt(value, 9, 3);
		 */

		// *****************************************************************************

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

				// this.centrePanel.validate();
				// this.centrePanel.repaint();

				populateComboBox();

			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} // end remove button

		if (source == btnAddSelectedSkill) // top skill selected by default
											// unless changed
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
			centrePanel.add(lblSkillName);

			lblVendor = new JLabel("Vendor");
			lblVendor.setBounds(546, 252, 56, 16);
			centrePanel.add(lblVendor);

			lblSkillDescription = new JLabel("Skill Description");
			lblSkillDescription.setBounds(546, 223, 101, 16);
			centrePanel.add(lblSkillDescription);

			textFieldSkillName = new JTextField();
			textFieldSkillName.setBounds(669, 191, 326, 22);
			centrePanel.add(textFieldSkillName);
			textFieldSkillName.setColumns(10);

			textFieldVendor = new JTextField();
			textFieldVendor.setBounds(669, 255, 326, 22);
			centrePanel.add(textFieldVendor);
			textFieldVendor.setColumns(10);

			textFieldSkillDesc = new JTextField();
			textFieldSkillDesc.setBounds(669, 223, 326, 22);
			centrePanel.add(textFieldSkillDesc);
			textFieldSkillDesc.setColumns(10);

			btnAddSkill = new JButton("Add");
			btnAddSkill.setBounds(669, 292, 97, 25);
			centrePanel.add(btnAddSkill);
			btnAddSkill.addActionListener(this);

			this.centrePanel.validate();
			this.centrePanel.repaint();
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
