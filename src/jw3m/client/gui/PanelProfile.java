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
	private Vector<Skill> comboSkillNames = new Vector<Skill>();
	private Vector<Skill> tempSkillNames = new Vector<Skill>();
	private Vector<UserSkill> userSkills = new Vector<UserSkill>();
	private Vector<UserSkill> tmpUserSkills = new Vector<UserSkill>();
	private Vector<UserSkill> tmpUserSkillIds = new Vector<UserSkill>();
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

	public PanelProfile(SkillsClient frame)
	{
		baseFrame = frame;

		setLayout(null);

		panel = new JPanel();
		panel.setBounds(0, 13, 1031, 641);
		add(panel);
		panel.setLayout(null);

		lblMySkills = new JLabel("My Skills");
		lblMySkills.setFont(new Font("Calibri", Font.BOLD, 22));
		lblMySkills.setBounds(468, 0, 88, 30);
		panel.add(lblMySkills);

		btnRemoveSelectedSkill = new JButton("Remove selected skill from table below");
		btnRemoveSelectedSkill.setBounds(22, 66, 308, 25);
		panel.add(btnRemoveSelectedSkill);
		btnRemoveSelectedSkill.addActionListener(this);

		tempSkillNames = baseFrame.data_skillList;
		tmpUserSkills = baseFrame.getNetUserSkills(tempUser);

		for (int j = 0; j < tmpUserSkills.size(); j++)
		{
			tmpUserSkillIds.add(tmpUserSkills.get(j));
		}

		for (int i = 0; i < tempSkillNames.size(); i++)
		{
			if (!(tempSkillNames.contains(tmpUserSkills)))
			{
				comboSkillNames.add(tempSkillNames.get(i));
			}
		}

		comboBoxSkills = new JComboBox(comboSkillNames);
		comboBoxSkills.setBounds(546, 67, 308, 22);
		panel.add(comboBoxSkills);

		btnAddSelectedSkill = new JButton("Add selected skill from dropdown");
		btnAddSelectedSkill.setBounds(546, 102, 308, 25);
		panel.add(btnAddSelectedSkill);

		setupSkillsTable();
		setLayout(null);

		table = new JTable(model);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 344, 973, 284);
		panel.add(scrollPane);
		scrollPane.setViewportView(table);

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

		// populate the model for the table

		// User tempUser = new User();
		int skill;
		String skillName;

		tempUser = baseFrame.authenticatedUser;
		model.setRowCount(0);
		try
		{
			baseFrame.getNetSkillList();
			skillList = baseFrame.data_skillList;
			// Vector<UserSkill> userSkills = new Vector<UserSkill>();
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
			tempSkillNames = baseFrame.data_skillList;

			for (int i = 0; i < tempSkillNames.size(); i++)
			{
				comboSkillNames.add(tempSkillNames.get(i));
			}

		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DefaultComboBoxModel cboNewModel = new DefaultComboBoxModel(comboSkillNames);
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
		tmpUser = baseFrame.authenticatedUser.getUserName();
		tempUser = baseFrame.authenticatedUser;

		int i = table.getSelectedRow(); // set index for selected row

		System.out.println("selected row index -------->>>>>>> " + i);

		if (source == btnRemoveSelectedSkill && i >= 0) // if nothing selected
														// its -1
		{

			try
			{
				DAO getSkill = new DAO();
				// skillList = getSkill.getSkillListByUser();

				userSkills = baseFrame.getNetUserSkills(tempUser);
				skill = userSkills.get(i + 1).getSkillID();

				System.out.println("all user skills : " + userSkills);
				System.out.println("selected skill : " + skill);

				getSkill.removeUserSkills(tmpUser, skill);

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
			
			panel.repaint();
		}
	}
}
