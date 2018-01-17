package jw3m.widgets;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

public class PanelDynamicButtonAdd extends JPanel implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblSkillFirst;
	private JTextField textFieldSkill1;
	private JButton btnAddSkill;
	private JLabel lblSkillSecond;
	private JTextField textFieldSkill2;
	private JButton btnAddSkill2;

	/**
	 * Create the panel.
	 */
	public PanelDynamicButtonAdd(JPanel basePanel)
	{
		
		lblSkillFirst = new JLabel("Skill 1");
		
		textFieldSkill1 = new JTextField();
		textFieldSkill1.setColumns(10);
		
		btnAddSkill = new JButton("Add");
		btnAddSkill.addActionListener(this);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(83)
					.addComponent(lblSkillFirst)
					.addGap(31)
					.addComponent(textFieldSkill1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(45)
					.addComponent(btnAddSkill)
					.addContainerGap(86, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(69)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSkillFirst)
						.addComponent(textFieldSkill1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAddSkill))
					.addContainerGap(209, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}

	@Override
	public void actionPerformed(ActionEvent ae)
	{
		Object source = ae.getSource();
		
		if (source == btnAddSkill)
		{
			lblSkillSecond = new JLabel("Skill 2");
			
			textFieldSkill2 = new JTextField();
			textFieldSkill2.setColumns(10);
			
			btnAddSkill2 = new JButton("Add");
			btnAddSkill2.addActionListener(this);
			
			GroupLayout groupLayout = new GroupLayout(this);
			groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(83)
						.addComponent(lblSkillSecond)
						.addGap(31)
						.addComponent(textFieldSkill2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(45)
						.addComponent(btnAddSkill2)
						.addContainerGap(86, Short.MAX_VALUE))
			);
			groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(169)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblSkillSecond)
							.addComponent(textFieldSkill2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnAddSkill2))
						.addContainerGap(309, Short.MAX_VALUE))
			);
			setLayout(groupLayout);
		}
		
	}
}