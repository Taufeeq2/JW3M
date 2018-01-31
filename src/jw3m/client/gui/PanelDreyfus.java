package jw3m.client.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class PanelDreyfus extends JPanel implements ActionListener
{
	final static Logger logger = Logger.getLogger(PanelLogin.class);
	private SkillsClient baseFrame;
	private JPanel nPanel, cPanel;
	
	private Font primaryFont, secondaryFont;
	private JLabel lblDreyfusModel_1;
	private JTable table;
	private JScrollPane scrollPane;

	public PanelDreyfus(SkillsClient frame)
	{
		PropertyConfigurator.configure("log4j.properties");
		this.baseFrame = frame;
		primaryFont = baseFrame.getPrimaryFont();
		secondaryFont = baseFrame.getSecondaryFont();
		
		nPanel = new JPanel();
		cPanel = new JPanel();
		setLayout(new BorderLayout(0, 0));
		
		
		
		this.add(nPanel, BorderLayout.NORTH);
		
		lblDreyfusModel_1 = new JLabel("Dreyfus Model");
		lblDreyfusModel_1.setFont(secondaryFont);
		nPanel.add(lblDreyfusModel_1);
		this.add(cPanel, BorderLayout.CENTER);
		cPanel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(103, 275, 658, -205);
		cPanel.add(scrollPane);
		
		
		table = new JTable();
		
		scrollPane.setViewportView(table);
		table.setFont(primaryFont);
		table.setBounds(124, 131, 600, 128);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Proficiency Level", "Knowledgable", "Standard of Work", "Autonomy", "New column", "Coping with Complexity", "Perception of Context", "Growing Capability", "Purposeful Collaboration"
			}
		));

	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
	}
}
