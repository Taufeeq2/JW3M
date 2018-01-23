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

import jw3m.beans.Rating;
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
	private Vector<Rating> ratingList = new Vector<Rating>();
	private Skill skill = null;
	private JLabel lblAvailableSkills;
	private JComboBox comboBoxSkills;
	private SkillsClient baseFrame = null;
	private JScrollPane scrollPane;
	private JTable table;

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
		
		scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(138, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblAvailableSkills)
							.addGap(387))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 645, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBoxSkills, GroupLayout.PREFERRED_SIZE, 645, GroupLayout.PREFERRED_SIZE))
							.addGap(123))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(38)
					.addComponent(lblAvailableSkills)
					.addGap(18)
					.addComponent(comboBoxSkills, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 291, GroupLayout.PREFERRED_SIZE)
					.addGap(71))
		);
		
		table = new JTable(model);
		scrollPane.setRowHeaderView(table);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		add(scrollPane);

		setupSkillsTable();

		setLayout(groupLayout);
		scrollPane.setViewportView(table);

	}

	public void setupSkillsTable()
	{

		// Here we set up the model

		String str[] =
		{ "User ID", "Level" };
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
					return false;
			}

		};

		// Setup the model

		try
		{
			DAO getRatings = new DAO();
			if (skill == null)
			{
				skill = (Skill) comboBoxSkills.getSelectedItem();
			}
			ratingList = getRatings.getRatings(skill);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < ratingList.size(); i++)
		{
			System.out.println("at line 153, ratinglist size = " + ratingList.size());
			Rating rating = ratingList.get(i);

			Object obj[] =
			{ rating.getUserID(), rating.getLevel()};
			model.addRow(obj);

		}
	}

	public void setCustomTableElement(Object aValue, int row, int column)
	{

		Rating rat = ratingList.get(row);

		switch (column)
		{
		case 0:
			try
			{
				rat.setUserID(aValue.toString());
			} catch (NumberFormatException e)
			{
				model.setValueAt(rat.getUserID(), row, column);

			}
			break;
		//case 1:
		//	rat.setLevel(level);;
		//	break;
		//case 2:
		//	skl.setSkillVendor(aValue.toString());
		//	break;
		//case 3:
		//	skl.setSkillDescription(aValue.toString());
		//	break;

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
		Object source = ae.getSource();
		if (source == comboBoxSkills)
		{
			
			skill = (Skill) comboBoxSkills.getSelectedItem();
			setupSkillsTable();
		}

	}
}
