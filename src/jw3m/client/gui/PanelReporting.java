package jw3m.client.gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import jw3m.beans.Skill;
import jw3m.dao.*;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JComboBox;

public class PanelReporting extends JPanel implements ActionListener, ListSelectionListener
{
	private static final long serialVersionUID = 1L;
	DefaultTableModel model = null;
	private Vector<Skill> skillList = new Vector<Skill>();
	private Vector<Skill> skillNames = new Vector<Skill>();
	private JLabel lblAvailableSkills;
	private JComboBox comboBoxSkills;
	private SkillsClient baseFrame = null;

	/**
	 * Create the panel.
	 */
	public PanelReporting(SkillsClient frame)
	{
		this.baseFrame = frame;
		lblAvailableSkills = new JLabel("Available Skills");
		lblAvailableSkills.setFont(new Font("Tahoma", Font.BOLD, 15));
		
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
		
		comboBoxSkills = new JComboBox(skillNames);
		comboBoxSkills.addActionListener(this);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(138, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(lblAvailableSkills)
							.addGap(387))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(comboBoxSkills, GroupLayout.PREFERRED_SIZE, 645, GroupLayout.PREFERRED_SIZE)
							.addGap(123))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(38)
					.addComponent(lblAvailableSkills)
					.addGap(18)
					.addComponent(comboBoxSkills, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(451, Short.MAX_VALUE))
		);

		setupSkillsTable();

		setLayout(groupLayout);

	}

	public void setupSkillsTable()
	{

		// Here we set up the model

		String str[] =
		{ "Skill ID", "Skill Name", "Skill Vendor", "Skill Description" };
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
				if (column == 0)
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
			DAO getSkill = new DAO();
			skillList = getSkill.getSkillList();
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < skillList.size(); i++)
		{

			Skill skill = skillList.get(i);

			Object obj[] =
			{ skill.getSkillID(), skill.getSkillName(), skill.getSkillVendor(), skill.getSkillDescription() };
			model.addRow(obj);

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
		case 2:
			skl.setSkillVendor(aValue.toString());
			break;
		case 3:
			skl.setSkillDescription(aValue.toString());
			break;

		}
		System.out.println(aValue + " " + row + " " + column);

		// skillList.SaveToDisk();
	}

	@Override
	public void valueChanged(ListSelectionEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent ae)
	{
		// TODO Auto-generated method stub

	}
}
