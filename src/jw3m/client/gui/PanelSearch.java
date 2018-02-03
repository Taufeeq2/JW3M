package jw3m.client.gui;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import jw3m.beans.Notification;
import jw3m.beans.Rating;
import jw3m.beans.Skill;
import jw3m.beans.User;
import jw3m.widgets.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridLayout;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class PanelSearch extends JPanel implements ActionListener
{
	final static Logger logger = Logger.getLogger(PanelSearch.class);

	private SkillsClient baseFrame;
	

	private JLabel northTitleLabel, southTitleLabel, eastTitleLabel, westTitleLabel;
	private JButton centerButtonSearch;

	private JPanel nPanel, cPanel;

	private JLabel lblRateSomeone, lblBanner;
	private JButton btnSubmit;
	private DefaultTableModel model = null;
	private JScrollPane scrollPane;
	private JTable table;

	
	private Rating ratee;
	private JComboBox separatorComboBox ;
	
	private Vector itemsText = new Vector();
	private Vector<String> itemsName = new Vector<String>();
	private Font primaryFont, secondaryFont;
	private JButton btnClear;
	private JLabel lblImgLabel;
	private JLabel lblSearchBy1;
	private JLabel lblFilterBy2;
	private JLabel lblFilterBy3;
	private JComboBox comboBoxRow1SkillAttrib;
	private JCheckBox chckbxRow1;
	private JComboBox comboBoxRow2SkillAttrib;
	private JCheckBox checkBoxRow2;
	private JComboBox comboBoxRow3SkillAttrib;
	private JCheckBox checkBoxRow3;
	private JComboBox comboBoxRow1Skill;
	private JComboBox comboBoxRow2Skill;
	private JComboBox comboBoxRow3Skill;
	private JLabel lblWhere1;
	private JLabel lblWhere2;
	private JLabel lblWhere3;
	private JComboBox comboBoxRow1Condition;
	private JComboBox comboBoxRow2Condition;
	private JComboBox comboBoxRow3Condition;
	private JLabel lblIs1;
	private JLabel lblIs2;
	private JLabel lblIs3;
	private JComboBox comboBoxRow1Value;
	private JComboBox comboBoxRow2Value;
	private JComboBox comboBoxRow3Value;
	
	// data vars start
	private Vector<Skill> skillList1 = new Vector<Skill>();
	private Vector<Skill> skillList2 = new Vector<Skill>();
	private Vector<Skill> skillList3 = new Vector<Skill>();
	private Vector<String> skillElement = new Vector<String>();
	private Vector<String> conditionElement = new Vector<String>();
	private Vector<Integer> valueElement = new Vector<Integer>();
	
	private Vector<Skill> skillNames = new Vector<Skill>();
	
	// data vars end

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PanelSearch(SkillsClient frame)
	{
		baseFrame = frame;
		primaryFont = baseFrame.getPrimaryFont();
        secondaryFont = baseFrame.getSecondaryFont();
        PropertyConfigurator.configure("log4j.properties");
        
        this.setupData();
        
        nPanel = new JPanel();
		cPanel = new JPanel();
        
		lblImgLabel = new JLabel(new ImageIcon("resources/RateSomeone_Full.jpg"));
		nPanel.add(lblImgLabel);
	
		setLayout(new BorderLayout(0, 0));
		cPanel.setLayout(null);

		table = new JTable(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFont(primaryFont);
		JTableHeader header = table.getTableHeader();
	    header.setFont(primaryFont);

	    JTextArea txtArea = new JTextArea("test");
		
		

		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(12, 301, 1668, 293);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		cPanel.add(scrollPane);
		scrollPane.setColumnHeaderView(txtArea);
//		scrollPane.setViewportView(table);
		cPanel.add(scrollPane);
//		cPanel.add(btnSubmit);
		
		
		this.add(nPanel, BorderLayout.NORTH);
		this.add(cPanel, BorderLayout.CENTER);
		
		lblSearchBy1 = new JLabel("Search for");
		lblSearchBy1.setFont(primaryFont);
		lblSearchBy1.setBounds(53, 56, 133, 29);
		cPanel.add(lblSearchBy1);
		
		lblFilterBy2 = new JLabel("Filter by");
		lblFilterBy2.setFont(primaryFont);
		lblFilterBy2.setBounds(53, 110, 133, 29);
		cPanel.add(lblFilterBy2);
		
		lblFilterBy3 = new JLabel("Filter by");
		lblFilterBy3.setFont(primaryFont);
		lblFilterBy3.setBounds(53, 165, 133, 29);
		cPanel.add(lblFilterBy3);
		
		comboBoxRow1SkillAttrib = new JComboBox(skillElement);
		comboBoxRow1SkillAttrib.setFont(primaryFont);
		comboBoxRow1SkillAttrib.setBounds(420, 60, 175, 22);
		cPanel.add(comboBoxRow1SkillAttrib);
		
		chckbxRow1 = new JCheckBox("New check box");
		chckbxRow1.setFont(primaryFont);
		chckbxRow1.setBounds(879, 59, 113, 25);
		cPanel.add(chckbxRow1);
		
		comboBoxRow2SkillAttrib = new JComboBox(skillElement);
		comboBoxRow2SkillAttrib.setFont(primaryFont);
		comboBoxRow2SkillAttrib.setBounds(420, 114, 175, 22);
		cPanel.add(comboBoxRow2SkillAttrib);
		
		checkBoxRow2 = new JCheckBox("New check box");
		checkBoxRow2.setFont(primaryFont);
		checkBoxRow2.setBounds(879, 113, 113, 25);
		cPanel.add(checkBoxRow2);
		
		comboBoxRow3SkillAttrib = new JComboBox(skillElement);
		comboBoxRow3SkillAttrib.setFont(primaryFont);
		comboBoxRow3SkillAttrib.setBounds(420, 169, 175, 22);
		cPanel.add(comboBoxRow3SkillAttrib);
		
		checkBoxRow3 = new JCheckBox("New check box");
		checkBoxRow3.setFont(primaryFont);
		checkBoxRow3.setBounds(879, 168, 113, 25);
		cPanel.add(checkBoxRow3);
		
		comboBoxRow1Skill = new JComboBox(skillList1);
		comboBoxRow1Skill.setFont(primaryFont);
		comboBoxRow1Skill.setBounds(170, 60, 133, 22);
		cPanel.add(comboBoxRow1Skill);
		
		comboBoxRow2Skill = new JComboBox(skillList2);
		comboBoxRow2Skill.setFont(primaryFont);
		comboBoxRow2Skill.setBounds(170, 114, 133, 22);
		cPanel.add(comboBoxRow2Skill);
		
		comboBoxRow3Skill = new JComboBox(skillList3);
		comboBoxRow3Skill.setFont(primaryFont);
		comboBoxRow3Skill.setBounds(170, 169, 133, 22);
		cPanel.add(comboBoxRow3Skill);
		
		lblWhere1 = new JLabel("where");
		lblWhere1.setFont(primaryFont);
		lblWhere1.setBounds(321, 63, 56, 16);
		cPanel.add(lblWhere1);
		
		lblWhere2 = new JLabel("where");
		lblWhere2.setFont(primaryFont);
		lblWhere2.setBounds(321, 117, 56, 16);
		cPanel.add(lblWhere2);
		
		lblWhere3 = new JLabel("where");
		lblWhere3.setFont(primaryFont);
		lblWhere3.setBounds(321, 172, 56, 16);
		cPanel.add(lblWhere3);
		
		comboBoxRow1Condition = new JComboBox(conditionElement);
		comboBoxRow1Condition.setFont(primaryFont);
		comboBoxRow1Condition.setBounds(701, 60, 66, 22);
		cPanel.add(comboBoxRow1Condition);
		
		comboBoxRow2Condition = new JComboBox(conditionElement);
		comboBoxRow2Condition.setFont(primaryFont);
		comboBoxRow2Condition.setBounds(701, 114, 66, 22);
		cPanel.add(comboBoxRow2Condition);
		
		comboBoxRow3Condition = new JComboBox(conditionElement);
		comboBoxRow3Condition.setFont(primaryFont);
		comboBoxRow3Condition.setBounds(701, 169, 66, 22);
		cPanel.add(comboBoxRow3Condition);
		
		lblIs1 = new JLabel("is");
		lblIs1.setFont(primaryFont);
		lblIs1.setBounds(637, 63, 34, 16);
		cPanel.add(lblIs1);
		
		lblIs2 = new JLabel("is");
		lblIs2.setFont(primaryFont);
		lblIs2.setBounds(637, 117, 34, 16);
		cPanel.add(lblIs2);
		
		lblIs3 = new JLabel("is");
		lblIs3.setFont(primaryFont);
		lblIs3.setBounds(637, 172, 34, 16);
		cPanel.add(lblIs3);
		
		comboBoxRow1Value = new JComboBox(valueElement);
		comboBoxRow1Value.setFont(primaryFont);
		comboBoxRow1Value.setBounds(802, 59, 56, 22);
		cPanel.add(comboBoxRow1Value);
		
		comboBoxRow2Value = new JComboBox(valueElement);
		comboBoxRow1Value.setFont(primaryFont);
		comboBoxRow2Value.setBounds(802, 113, 56, 22);
		cPanel.add(comboBoxRow2Value);
		
		comboBoxRow3Value = new JComboBox(valueElement);
		comboBoxRow1Value.setFont(primaryFont);
		comboBoxRow3Value.setBounds(802, 168, 56, 22);
		cPanel.add(comboBoxRow3Value);
		
		
	
 

		
	}
	
	public void setupData()
	{
		logger.info("Searches panel setting up data *******************************");
		
		skillList1 = baseFrame.data_skillList;
		skillList2 = baseFrame.data_skillList;
		skillList3 = baseFrame.data_skillList;
		
		skillElement.add("Knowledge");
		skillElement.add("Work Standard");
		skillElement.add("Autonomy");
		skillElement.add("Complexity Coping");		
		skillElement.add("Context Perception");
		skillElement.add("Capablity Growing");
		skillElement.add("Collaboration");

		conditionElement.add(" = " );
		conditionElement.add(" =< " );
		conditionElement.add(" => " );
		conditionElement.add(" < " );
		conditionElement.add(" > " );
		
		valueElement.add(1);
		valueElement.add(2);
		valueElement.add(3);
		valueElement.add(4);
		valueElement.add(5);
	
		
	}
	
	public void setupCenterPanel()
	{
		
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
	}
}
