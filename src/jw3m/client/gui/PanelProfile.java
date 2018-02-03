package jw3m.client.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import jw3m.beans.Hobby;
import jw3m.beans.Rating;
import jw3m.beans.Skill;
import jw3m.beans.User;
import jw3m.beans.UserSkill;
import jw3m.client.gui.SkillsClient;
import jw3m.dao.DAO;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import javax.swing.ScrollPaneConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;

public class PanelProfile extends JPanel implements ActionListener
{
	final static Logger logger = Logger.getLogger(PanelProfile.class);

	private Font primaryFont, secondaryFont;

	private static final long serialVersionUID = 1L;
	private SkillsClient baseFrame;
	private JPanel centrePanel, northPanel, westPanel;
	private JTable table;
	private DefaultTableModel model = null;
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
	private User tempUser = new User();
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
	private Vector<Rating> ratingVect = new Vector<Rating>();
	private JButton btnAddNewSkill;
	private JLabel lblSkillName;
	private JTextField textFieldSkillName;
	private JLabel lblVendor;
	private JTextField textFieldVendor;
	private JLabel lblSkillDescription;
	private JTextField textFieldSkillDesc;
	private JButton btnAddSkill;
	private Rating ratee;
	private JLabel lblImgLabel;

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
		setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		setupSkillsTable();

		table = new JTable(model);

		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFont(primaryFont);
		;
		centrePanel.add(scrollPane);
		scrollPane.setViewportView(table);

		JTableHeader header = table.getTableHeader();
		header.setFont(primaryFont);

		// ***************************************************************************************
		// Set up the table to have a level (1 to 5) combobox on each field to
		// enter the ratings
		// ***************************************************************************************
		for (int col = 3; col < 10; col++)
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

		// ***************************************************************************************
		// Set up the buttons (rating update and skill delete) on the Jtable for
		// each row
		// ***************************************************************************************

		table.getColumn("Rating").setCellRenderer(new ButtonRenderer());
		table.getColumn("Rating").setCellEditor(new ButtonEditor1(new JCheckBox()));
		table.getColumn("Skill").setCellRenderer(new ButtonRenderer());
		table.getColumn("Skill").setCellEditor(new ButtonEditor2(new JCheckBox()));

		setVisible(true);

		// ***************************************************************************************
		// Set up the table sorter functionality to allow each column to be
		// sorted
		// ***************************************************************************************

		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
		table.setRowSorter(sorter);

		this.add(northPanel, BorderLayout.NORTH);

		lblImgLabel = new JLabel(new ImageIcon("resources/MySkills_Full.jpg"));
		northPanel.add(lblImgLabel);

		this.add(centrePanel, BorderLayout.CENTER);
		GroupLayout gl_centrePanel = new GroupLayout(centrePanel);
		gl_centrePanel.setHorizontalGroup(gl_centrePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_centrePanel.createSequentialGroup().addContainerGap()
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 960, Short.MAX_VALUE).addContainerGap()));
		gl_centrePanel
				.setVerticalGroup(gl_centrePanel.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
						gl_centrePanel.createSequentialGroup().addContainerGap()
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 465, GroupLayout.PREFERRED_SIZE)
								.addGap(176)));
		centrePanel.setLayout(gl_centrePanel);
		this.add(westPanel, BorderLayout.WEST);

		btnAddSelectedSkill = new JButton("Add Selected Skill from DropDown");
		btnAddSelectedSkill.setFont(primaryFont);
		btnAddSelectedSkill.addActionListener(this);

		// ***************************************************************************************
		// Set up the initial population of the skill names combobox
		// ***************************************************************************************

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

		Collections.sort(comboSkillNames);
		comboBoxSkills = new JComboBox(comboSkillNames);
		comboBoxSkills.setFont(primaryFont);

		btnAddNewSkill = new JButton("Add Skill NOT on the DropDown");
		btnAddNewSkill.setFont(primaryFont);
		btnAddNewSkill.addActionListener(this);

		lblSkillName = new JLabel("Skill Name");
		lblSkillName.setFont(primaryFont);

		textFieldSkillName = new JTextField();
		textFieldSkillName.setFont(primaryFont);
		textFieldSkillName.setColumns(10);

		lblVendor = new JLabel("Vendor");
		lblVendor.setFont(primaryFont);

		textFieldVendor = new JTextField();
		textFieldVendor.setFont(primaryFont);
		textFieldVendor.setColumns(10);

		lblSkillDescription = new JLabel("Skill Description");
		lblSkillDescription.setFont(primaryFont);

		textFieldSkillDesc = new JTextField();
		textFieldSkillDesc.setFont(primaryFont);
		textFieldSkillDesc.setColumns(10);

		btnAddSkill = new JButton("Add Skill");
		btnAddSkill.setFont(primaryFont);
		btnAddSkill.addActionListener(this);

		GroupLayout gl_westPanel = new GroupLayout(westPanel);
		gl_westPanel.setHorizontalGroup(
			gl_westPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_westPanel.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_westPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnAddSkill)
						.addComponent(textFieldVendor, 241, 241, Short.MAX_VALUE)
						.addComponent(lblVendor)
						.addComponent(textFieldSkillDesc, 241, 241, Short.MAX_VALUE)
						.addComponent(lblSkillDescription)
						.addComponent(lblSkillName)
						.addComponent(btnAddNewSkill, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_westPanel.createSequentialGroup()
							.addComponent(comboBoxSkills, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(12))
						.addComponent(btnAddSelectedSkill, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(textFieldSkillName))
					.addContainerGap())
		);
		gl_westPanel.setVerticalGroup(
			gl_westPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_westPanel.createSequentialGroup()
					.addGap(6)
					.addComponent(comboBoxSkills, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(41)
					.addComponent(btnAddSelectedSkill)
					.addGap(40)
					.addComponent(btnAddNewSkill)
					.addGap(52)
					.addComponent(lblSkillName)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textFieldSkillName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblSkillDescription)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textFieldSkillDesc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblVendor)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textFieldVendor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnAddSkill)
					.addContainerGap(245, Short.MAX_VALUE))
		);
		westPanel.setLayout(gl_westPanel);

		lblSkillName.setVisible(false);
		lblVendor.setVisible(false);
		lblSkillDescription.setVisible(false);
		textFieldSkillName.setVisible(false);
		textFieldSkillDesc.setVisible(false);
		textFieldVendor.setVisible(false);
		btnAddSkill.setVisible(false);
	}

	public void setupSkillsTable()
	{

		// ***************************************************************************************
		// Set up the table model
		// ***************************************************************************************

		String str[] =
		{ "User ID", "Skill ID", "Skill Name", "Knowledgeable", "Standard of Work", "Autonomy",
				"Coping with Complexity", "Perception of Context", "Growing Capability", "Purposeful Collaboration",
				"Average Self-Rating", "Ave. Colleague Rating", "Rating", "Skill" };

		model = new DefaultTableModel(str, 0)
		{
			public void setValueAt(Object aValue, int row, int column)
			{
				Vector rowVector = (Vector) dataVector.elementAt(row);
				rowVector.setElementAt(aValue, column);
				fireTableCellUpdated(row, column);

				// setCustomTableElement(aValue, row, column);

			}

			public boolean isCellEditable(int row, int column)
			{
				// make read only field columns 0, 1, 9, 10, 11 - other columns
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

		// ***************************************************************************************
		// Populate the table model
		// ***************************************************************************************
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
								contextPerception, capabilityGrowing, collaboration, aveSelfRating, aveColleagueRating,
								"Update", "Delete" };
						model.addRow(obj);

					}

				}
			}

		} catch (Exception e1)
		{
			e1.printStackTrace();
		}

	}

	// ************************************************************************************************************
	// Method to repopulate the skill names combobox to only contain skills not
	// already added to the user profile
	// ************************************************************************************************************
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

		Collections.sort(comboSkillNames);

		DefaultComboBoxModel cboNewModel = new DefaultComboBoxModel(comboSkillNames);
		comboBoxSkills.setModel(cboNewModel);

	}

	// **********************************************************
	// method to redraw table and repopulate the skills combobox
	// **********************************************************

	public void tableRedraw()
	{
		setupSkillsTable();

		centrePanel.remove(table);
		table = new JTable(model);
		table.setFont(primaryFont);
		;

		centrePanel.add(scrollPane);
		scrollPane.setViewportView(table);

		// ***************************************************************************************
		// Set up the table sorter functionality to allow each column to be
		// sorted
		// ***************************************************************************************
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
		table.setRowSorter(sorter);

		// ***************************************************************************************
		// Set up the table to have a level (1 to 5) combobox on each field to
		// enter the ratings
		// ***************************************************************************************
		for (int col = 3; col < 10; col++)
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

		// ***************************************************************************************
		// Set up the buttons (rating update and skill delete) on the Jtable for
		// each row
		// ***************************************************************************************

		table.getColumn("Rating").setCellRenderer(new ButtonRenderer());
		table.getColumn("Rating").setCellEditor(new ButtonEditor1(new JCheckBox()));
		table.getColumn("Skill").setCellRenderer(new ButtonRenderer());
		table.getColumn("Skill").setCellEditor(new ButtonEditor2(new JCheckBox()));

		populateComboBox();
	}

	// ************************************************************************************************************
	// Method to render the JTable buttons
	// ************************************************************************************************************
	class ButtonRenderer extends JButton implements TableCellRenderer
	{

		/**
		* 
		*/
		private static final long serialVersionUID = 1L;

		public ButtonRenderer()
		{
			setOpaque(true);
			setFont(primaryFont);
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column)
		{
			if (isSelected)
			{
				setForeground(table.getSelectionForeground());
				setBackground(table.getSelectionBackground());
			} else
			{
				setForeground(table.getForeground());
				setBackground(UIManager.getColor("Button.background"));
			}
			setText((value == null) ? "" : value.toString());
			return this;
		}
	}

	// ************************************************************************************************************
	// Method to set up the Update Skill Rating JTable button
	// ************************************************************************************************************
	class ButtonEditor1 extends DefaultCellEditor
	{
		/**
		* 
		*/
		private static final long serialVersionUID = 1L;

		protected JButton button1;

		private String label;

		private boolean isPushed1;

		public ButtonEditor1(JCheckBox checkBox)
		{
			super(checkBox);
			button1 = new JButton();
			button1.setOpaque(true);
			button1.setFont(primaryFont);
			button1.addActionListener(new ActionListener()

			{
				public void actionPerformed(ActionEvent e)
				{
					fireEditingStopped();
				}
			});
		}

		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column)
		{
			if (isSelected)
			{
				button1.setForeground(table.getSelectionForeground());
				button1.setBackground(table.getSelectionBackground());
			} else
			{
				button1.setForeground(table.getForeground());
				button1.setBackground(table.getBackground());
			}
			label = (value == null) ? "" : value.toString();
			button1.setText(label);
			button1.setFont(primaryFont);
			isPushed1 = true;
			return button1;
		}

		public Object getCellEditorValue()
		{
			if (isPushed1)
			{
				Object rating[] = new Object[model.getColumnCount()];
				ratee = new Rating();

				for (int i = 0; i < model.getRowCount(); i++)
				{
					// System.out.println("number of rows: " +
					// model.getRowCount());

					if (model.getValueAt(i, 2) != null)
					{
						for (int count = 0; count < model.getColumnCount(); count++)
						{
							rating[count] = model.getValueAt(i, count);
						}

						ratee.setKnowledge((int) rating[3]);
						ratee.setWorkStandard((int) rating[4]);
						ratee.setAutonomy((int) rating[5]);
						ratee.setComplexityCoping((int) rating[6]);
						ratee.setContextPerception((int) rating[7]);
						ratee.setCapabilityGrowing((int) rating[8]);
						ratee.setCollaboration((int) rating[9]);

						ratee.setRaterID(baseFrame.authenticatedUser.getUserName());
						ratee.setSkillID((int) rating[1]);
						ratee.setUserID(baseFrame.authenticatedUser.getUserName());

						int level = 0;

						level = ((int) rating[3] + (int) rating[4] + (int) rating[5] + (int) rating[6] + (int) rating[7]
								+ (int) rating[8] + (int) rating[9]) / 7;
						ratee.setLevel(level);
						System.out.println("Level is: " + (ratee.getKnowledge() + ratee.getWorkStandard()
								+ ratee.getAutonomy() + ratee.getComplexityCoping() + ratee.getContextPerception()
								+ ratee.getCapabilityGrowing() + ratee.getCollaboration()) / 7);
						System.out.println("level: " + level + " skill id: " + ratee.getSkillID());
						table.setValueAt(level, i, 10);

						baseFrame.setNetAddRating(ratee);

					}

				}
				JOptionPane.showMessageDialog(null, "Skill updated");
			}

			isPushed1 = false;
			return new String(label);
		}

		public boolean stopCellEditing()
		{
			isPushed1 = false;
			return super.stopCellEditing();
		}

		protected void fireEditingStopped()
		{
			super.fireEditingStopped();
		}
	}

	// ************************************************************************************************************
	// Method to set up the Delete Skill JTable button
	// ************************************************************************************************************
	class ButtonEditor2 extends DefaultCellEditor
	{
		/**
		* 
		*/
		private static final long serialVersionUID = 1L;

		protected JButton button2;

		private String label;

		private boolean isPushed2;

		public ButtonEditor2(JCheckBox checkBox)
		{
			super(checkBox);
			button2 = new JButton();
			button2.setOpaque(true);
			button2.setFont(primaryFont);
			button2.addActionListener(new ActionListener()

			{
				public void actionPerformed(ActionEvent e)
				{
					fireEditingStopped();
				}
			});
		}

		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column)
		{
			if (isSelected)
			{
				button2.setForeground(table.getSelectionForeground());
				button2.setBackground(table.getSelectionBackground());
			} else
			{
				button2.setForeground(table.getForeground());
				button2.setBackground(table.getBackground());
			}
			label = (value == null) ? "" : value.toString();
			button2.setText(label);
			button2.setFont(primaryFont);
			isPushed2 = true;
			return button2;
		}

		public Object getCellEditorValue()
		{
			if (isPushed2)
			{
				int skill, rating = 0;
				String skillName = null, skillDesc = null;
				User tempUser = new User();
				String tmpUser = null;
				tmpUser = baseFrame.authenticatedUser.getUserName();
				tempUser = baseFrame.authenticatedUser;

				int i = table.getSelectedRow(); // set index for selected row
				int j = table.getSelectedColumn(); // set index for selected
													// column
				Skill tempSkill = new Skill();
				Vector<Skill> removeUserSkill = new Vector<Skill>();

				baseFrame.getNetSkillList();
				skillList = baseFrame.data_skillList;

				userSkills = baseFrame.getNetUserSkills(tempUser);
				Object obj2 = model.getValueAt(table.getSelectedRow(), 1);

				for (int k = 0; k < skillList.size(); k++)
				{
					if (skillList.get(k).getSkillID().equals(obj2))
					{
						tempSkill.setSkillID(skillList.get(k).getSkillID());
						tempSkill.setSkillName(skillList.get(k).getSkillName());
						tempSkill.setSkillVendor(skillList.get(k).getSkillVendor());
						tempSkill.setSkillDescription(skillList.get(k).getSkillDescription());
					}
				}

				removeUserSkill.add(tempSkill);

				baseFrame.setNetRemoveUserSkills(tempUser, removeUserSkill);

				tableRedraw();

				JOptionPane.showMessageDialog(null, "Skill removed");

			}
			isPushed2 = false;
			return new String(label);
		}

		public boolean stopCellEditing()
		{
			isPushed2 = false;
			return super.stopCellEditing();
		}

		protected void fireEditingStopped()
		{
			super.fireEditingStopped();
		}
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

		// *********************************************************************************************************************************
		// Action to take when the add selected skill from Combobox button is
		// clicked. Skill at the top selected by default until changed
		// *********************************************************************************************************************************
		if (source == btnAddSelectedSkill)
		{
			Vector<Skill> skillAdd = new Vector<Skill>();
			Vector<Skill> newSkill = new Vector<Skill>();

			Skill tempSkill = new Skill();
			skillAdd = baseFrame.data_skillList;
			String checkSkill = (comboBoxSkills.getSelectedItem().toString());

			for (int k = 0; k < skillAdd.size(); k++)
			{
				if (skillAdd.get(k).getSkillName().equals(checkSkill))
				{
					tempSkill.setSkillID(skillAdd.get(k).getSkillID());
					tempSkill.setSkillName(skillAdd.get(k).getSkillName());
				}
			}

			newSkill.add(tempSkill);
			boolean alreadyAdded = true;

			for (int m = 0; m < comboSkillNames.size(); m++)
			{
				if (comboSkillNames.get(m).equals(checkSkill))
				{
					alreadyAdded = false;
					break;
				} else
				{
					alreadyAdded = true;
				}
			}
			if (!alreadyAdded)
			{
				baseFrame.setNetUserSkills(baseFrame.authenticatedUser, newSkill);
				JOptionPane.showMessageDialog(this, "Skill added");
			} else
			{
				JOptionPane.showMessageDialog(this, "You already have this skill added");
			}

			tableRedraw();

		} // end add button

		// ************************************************************************************************************
		// Action to take when the add new skill not on Combobox button is
		// clicked
		// ************************************************************************************************************
		if (source == btnAddNewSkill)
		{
			lblSkillName.setVisible(true);
			lblVendor.setVisible(true);
			lblSkillDescription.setVisible(true);
			textFieldSkillName.setVisible(true);
			textFieldSkillDesc.setVisible(true);
			textFieldVendor.setVisible(true);
			btnAddSkill.setVisible(true);

		} // end add new skill button

		// ************************************************************************************************************
		// Action to take when the add skill button is clicked to add a
		// completely new skill
		// ************************************************************************************************************
		if (source == btnAddSkill)
		{
			Vector<Skill> skillAdd = new Vector<Skill>();
			Vector<Skill> skillAdd2 = new Vector<Skill>();
			Vector<Skill> newSkill = new Vector<Skill>();

			Skill tempSkill = new Skill();
			baseFrame.getNetSkillList();
			skillAdd = baseFrame.data_skillList;

			String checkSkill = (textFieldSkillName.getText().toString());
			
			//**************************************************
			boolean alreadyAdded = true;

			for (int n = 0; n < skillAdd.size(); n++)
			{
				if (skillAdd.get(n).equals(checkSkill))
				{
					alreadyAdded = false;
					break;
				} else
				{
					alreadyAdded = true;
				}
			}
			if (alreadyAdded)
			//*************************************************************	
			//if (skillAdd.contains(checkSkill))
			{
				JOptionPane.showMessageDialog(this,
						"This skill already exists on the register.  Select from the dropdown to add");
			} else
			{
				tempSkill.setSkillID(0);
				tempSkill.setSkillName(textFieldSkillName.getText());
				tempSkill.setSkillDescription(textFieldSkillDesc.getText());
				tempSkill.setSkillVendor(textFieldVendor.getText());

				baseFrame.setNetAddSkillList(tempSkill);

				Skill tempSkill2 = new Skill();
				baseFrame.getNetSkillList();
				skillAdd2 = baseFrame.data_skillList;
				String checkSkill2 = (textFieldSkillName.getText());

				for (int k = 0; k < skillAdd2.size(); k++)
				{
					if (skillAdd2.get(k).getSkillName().equals(checkSkill2))
					{
						tempSkill2.setSkillID(skillAdd2.get(k).getSkillID());
						tempSkill2.setSkillName(skillAdd2.get(k).getSkillName());
					}
				}

				newSkill.add(tempSkill2);

				baseFrame.setNetUserSkills(baseFrame.authenticatedUser, newSkill);
				JOptionPane.showMessageDialog(this, "Skill added");

				tableRedraw();

				textFieldSkillName.setText(null);
				textFieldSkillDesc.setText(null);
				textFieldVendor.setText(null);

				lblSkillName.setVisible(false);
				lblVendor.setVisible(false);
				lblSkillDescription.setVisible(false);
				textFieldSkillName.setVisible(false);
				textFieldSkillDesc.setVisible(false);
				textFieldVendor.setVisible(false);
				btnAddSkill.setVisible(false);
			}
		}

	}
}
