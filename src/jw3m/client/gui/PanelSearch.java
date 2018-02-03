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
	private JLabel lblSearchBy2;
	private JLabel lblSearchBy3;
	private JComboBox comboBoxRow1SkillAttrib;
	private JCheckBox checkBoxRow1;
	private JComboBox comboBoxRow2SkillAttrib;
	private JCheckBox checkBoxRow2;
	private JComboBox comboBoxRow3SkillAttrib;
//	private JCheckBox checkBoxRow3;
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
	
	private JTextArea txtArea; // will replace with JTable later?
	
	// data vars start
	private Vector<Skill> skillList1 = new Vector<Skill>();
	private Vector<Skill> skillList2 = new Vector<Skill>();
	private Vector<Skill> skillList3 = new Vector<Skill>();
	private Vector<String> skillElement = new Vector<String>();
	private Vector<String> conditionElement = new Vector<String>();
	private Vector<Integer> valueElement = new Vector<Integer>();
	
	private Vector<Skill> skillNames = new Vector<Skill>();
	private JLabel lblSkill;
	private JLabel lblAttribute;
	private JLabel lblOperator;
	private JLabel lblValue;
	
	// data vars end

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PanelSearch(SkillsClient frame)
	{
		baseFrame = frame;
		primaryFont = baseFrame.getPrimaryFont();
        secondaryFont = baseFrame.getSecondaryFont();
        PropertyConfigurator.configure("log4j.properties");
        
// 		Note to code reviewer 
// 		The pattern is
//		Label 			lblSearchBy2					"Search by"
//		ComboBox		comboBoxRow2Skill				"Java"
//		Label			lblWhere2						"Where"
//      ComboBox		comboBoxRow2SkillAttrib			"Knowlegable" 
//		Label 			lblIs2							"is"
//		ComboBox		comboBoxRow2Condition			" = " 
//		ComboBox		comboBoxRow2Value				" 3 " 
//		checkBoxRow2	checkBox						" <Tick> and then by " 
        
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

	    txtArea = new JTextArea("Start off blank");
	    txtArea.setFont(secondaryFont);
		
		

		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(12, 301, 1668, 293);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		cPanel.add(scrollPane);
		scrollPane.setViewportView(txtArea);
//		scrollPane.setViewportView(table);
		cPanel.add(scrollPane);
//		cPanel.add(btnSubmit);
		
		
		this.add(nPanel, BorderLayout.NORTH);
		this.add(cPanel, BorderLayout.CENTER);
		
		
		lblSkill = new JLabel("Skill");
		lblSkill.setFont(primaryFont);
		lblSkill.setBounds(170, 27, 56, 16);
		cPanel.add(lblSkill);
		
		lblAttribute = new JLabel("Attribute");
		lblAttribute.setFont(primaryFont);
		lblAttribute.setBounds(423, 27, 56, 16);
		cPanel.add(lblAttribute);
		
		lblOperator = new JLabel("Operator");
		lblOperator.setFont(primaryFont);
		lblOperator.setBounds(701, 27, 89, 16);
		cPanel.add(lblOperator);
		
		lblValue = new JLabel("Value");
		lblValue.setFont(primaryFont);
		lblValue.setBounds(828, 28, 104, 16);
		cPanel.add(lblValue);
		
		lblSearchBy1 = new JLabel("Search for");
		lblSearchBy1.setFont(primaryFont);
		lblSearchBy1.setBounds(53, 56, 133, 29);
		cPanel.add(lblSearchBy1);
		
		lblSearchBy2 = new JLabel("Filter by");
		lblSearchBy2.setFont(primaryFont);
		lblSearchBy2.setBounds(53, 110, 133, 29);
		cPanel.add(lblSearchBy2);
		
		lblSearchBy3 = new JLabel("Filter by");
		lblSearchBy3.setFont(primaryFont);
		lblSearchBy3.setBounds(53, 165, 133, 29);
		cPanel.add(lblSearchBy3);
		
		comboBoxRow1SkillAttrib = new JComboBox(skillElement);
		comboBoxRow1SkillAttrib.setFont(primaryFont);
		comboBoxRow1SkillAttrib.addActionListener(this);
		comboBoxRow1SkillAttrib.setBounds(420, 60, 175, 22);
		cPanel.add(comboBoxRow1SkillAttrib);
		
		comboBoxRow2SkillAttrib = new JComboBox(skillElement);
		comboBoxRow2SkillAttrib.setFont(primaryFont);
		comboBoxRow2SkillAttrib.addActionListener(this);
		comboBoxRow2SkillAttrib.setBounds(420, 114, 175, 22);
		cPanel.add(comboBoxRow2SkillAttrib);
		
		comboBoxRow3SkillAttrib = new JComboBox(skillElement);
		comboBoxRow3SkillAttrib.setFont(primaryFont);
		comboBoxRow3SkillAttrib.addActionListener(this);
		comboBoxRow3SkillAttrib.setBounds(420, 169, 175, 22);
		cPanel.add(comboBoxRow3SkillAttrib);
		
		
		checkBoxRow1 = new JCheckBox("and then  ...",false);// we want this unselected on start but dont want to trigger actions
		checkBoxRow1.setFont(primaryFont);
		checkBoxRow1.setBounds(905, 60, 113, 25);
		checkBoxRow1.addActionListener(this);
		cPanel.add(checkBoxRow1);
		
		checkBoxRow2 = new JCheckBox("and then  ...");
		checkBoxRow2.setFont(primaryFont);
		checkBoxRow2.setBounds(905, 114, 113, 25);
		checkBoxRow2.addActionListener(this);
		cPanel.add(checkBoxRow2);
		
//		checkBoxRow3 = new JCheckBox("");
//		checkBoxRow3.setFont(primaryFont);
//		checkBoxRow3.setBounds(905, 169, 113, 25);
//		checkBoxRow3.addActionListener(this);
//		cPanel.add(checkBoxRow3);
		
		comboBoxRow1Skill = new JComboBox(skillList1);
		comboBoxRow1Skill.setFont(primaryFont);
		comboBoxRow1Skill.setBounds(170, 60, 133, 22);
		comboBoxRow1Skill.addActionListener(this);
		cPanel.add(comboBoxRow1Skill);
		
		comboBoxRow2Skill = new JComboBox(skillList2);
		comboBoxRow2Skill.setFont(primaryFont);
		comboBoxRow2Skill.setBounds(170, 114, 133, 22);
		comboBoxRow2Skill.addActionListener(this);
		cPanel.add(comboBoxRow2Skill);
		
		comboBoxRow3Skill = new JComboBox(skillList3);
		comboBoxRow3Skill.setFont(primaryFont);
		comboBoxRow3Skill.setBounds(170, 169, 133, 22);
		comboBoxRow3Skill.addActionListener(this);
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
		comboBoxRow1Condition.setBounds(701, 60, 104, 22);
		comboBoxRow1Condition.addActionListener(this);
		cPanel.add(comboBoxRow1Condition);
		
		comboBoxRow2Condition = new JComboBox(conditionElement);
		comboBoxRow2Condition.setFont(primaryFont);
		comboBoxRow2Condition.setBounds(701, 114, 104, 22);
		comboBoxRow2Condition.addActionListener(this);
		cPanel.add(comboBoxRow2Condition);
		
		comboBoxRow3Condition = new JComboBox(conditionElement);
		comboBoxRow3Condition.setFont(primaryFont);
		comboBoxRow3Condition.setBounds(701, 169, 104, 22);
		comboBoxRow3Condition.addActionListener(this);
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
		comboBoxRow1Value.setBounds(828, 60, 56, 22);
		comboBoxRow1Value.addActionListener(this);
		cPanel.add(comboBoxRow1Value);
		
		comboBoxRow2Value = new JComboBox(valueElement);
		comboBoxRow2Value.setFont(primaryFont);		
		comboBoxRow2Value.setBounds(828, 114, 56, 22);
		comboBoxRow2Value.addActionListener(this);
		cPanel.add(comboBoxRow2Value);
		
		comboBoxRow3Value = new JComboBox(valueElement);
		comboBoxRow3Value.setFont(primaryFont);
		comboBoxRow3Value.setBounds(828, 169, 56, 22);
		comboBoxRow3Value.addActionListener(this);
		cPanel.add(comboBoxRow3Value);
		

		
		
		this.selectDefaultValues();
//		this.validate();
//		this.repaint();

	
		
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
		conditionElement.add(" < and = " );
		conditionElement.add(" > and = " );
		conditionElement.add(" < " );
		conditionElement.add(" > " );
		
		valueElement.add(1);
		valueElement.add(2);
		valueElement.add(3);
		valueElement.add(4);
		valueElement.add(5);
	
		
	}
	
	public void selectDefaultValues()
	{
		logger.info("Searches panel setting default values");
		// Default selection of skills at row 1 to 3
		comboBoxRow1Skill.setSelectedIndex(0);
		comboBoxRow2Skill.setSelectedIndex(-1);
		comboBoxRow3Skill.setSelectedIndex(-1);
		
		// Default is 1 and then unselected , unselected
		comboBoxRow1SkillAttrib.setSelectedIndex(0);
		comboBoxRow2SkillAttrib.setSelectedIndex(-1);
		comboBoxRow3SkillAttrib.setSelectedIndex(-1);
		
		// Default selection is >=
		comboBoxRow1Condition.setSelectedIndex(2);
		comboBoxRow2Condition.setSelectedIndex(-1);
		comboBoxRow3Condition.setSelectedIndex(-1);
		
		
		// Default skill as 3
		comboBoxRow1Value.setSelectedIndex(2);
		comboBoxRow2Value.setSelectedIndex(-1);
		comboBoxRow3Value.setSelectedIndex(-1);
		
		checkBoxRow1.setSelected(true);
		checkBoxRow2.setSelected(false);
//		checkBoxRow3.setSelected(false);
		
		
		// first row unticked on start up
		checkBoxRow1.setSelected(false);
		
		
		// disable Rows 2 and 3
		
		lblSearchBy2.setEnabled(false);
		comboBoxRow2Skill.setEnabled(false);
		comboBoxRow2SkillAttrib.setEnabled(false);
		lblWhere2.setEnabled(false);
		comboBoxRow2SkillAttrib.setEnabled(false);
		lblIs2.setEnabled(false);
		comboBoxRow2Condition.setEnabled(false);
		comboBoxRow2Value.setEnabled(false);
		checkBoxRow2.setEnabled(false);
		
		lblSearchBy3.setEnabled(false);
		comboBoxRow3Skill.setEnabled(false);
		comboBoxRow2SkillAttrib.setEnabled(false);
		lblWhere3.setEnabled(false);
		comboBoxRow3SkillAttrib.setEnabled(false);
		lblIs3.setEnabled(false);
		comboBoxRow3Condition.setEnabled(false);
		comboBoxRow3Value.setEnabled(false);
//		checkBoxRow3.setEnabled(false);
		
		
	}
	
	
	public void updateSearchData()
	{
		String currentText = txtArea.getText();
		
	
		
		String row1Str = lblSearchBy1.getText() + " " + 
				comboBoxRow1Skill.getSelectedItem() + " " + 
				lblWhere1.getText() + " " + 
				comboBoxRow1SkillAttrib.getSelectedItem() + " " + 
				lblIs1.getText() + " " + 
				comboBoxRow1Condition.getSelectedItem() + " " + 
				comboBoxRow1Value.getSelectedItem() + " " + 
				"Next row enabled (" + checkBoxRow1.isSelected() + ").";
		
		
		
		
		String row2Str = lblSearchBy2.getText() + " " + 
					comboBoxRow2Skill.getSelectedItem() + " " + 
					lblWhere2.getText() + " " + 
					comboBoxRow2SkillAttrib.getSelectedItem() + " " + 
					lblIs2.getText() + " " + 
					comboBoxRow2Condition.getSelectedItem() + " " + 
					comboBoxRow2Value.getSelectedItem() + " " + 
					"Next row enabled (" + checkBoxRow2.isSelected() + ").";
		
		String row3Str = lblSearchBy3.getText() + " " + 
				comboBoxRow3Skill.getSelectedItem() + " " + 
				lblWhere3.getText() + " " + 
				comboBoxRow3SkillAttrib.getSelectedItem() + " " + 
				lblIs3.getText() + " " + 
				comboBoxRow3Condition.getSelectedItem() + " " + 
				comboBoxRow3Value.getSelectedItem() + " ";
//		+ "Next row enabled (" + checkBoxRow3.isSelected() + ").";
		
		
		
		
		txtArea.setText(row1Str + "\n" + row2Str + "\n" + row3Str + "\n");
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		
		if (source == comboBoxRow1SkillAttrib )
		{
			
		}
		
		if (source == comboBoxRow2SkillAttrib )
		{
			
		}
		
		if (source == comboBoxRow3SkillAttrib )
		{
			
		}
		
		if (source == comboBoxRow1Skill )
		{
			
		}
		
		if (source == comboBoxRow2Skill )
		{
			
		}
		
		if (source == comboBoxRow3Skill )
		{
			
		}
		
		
		if (source == comboBoxRow1Condition )
		{
			
		}
		
		if (source == comboBoxRow2Condition )
		{
			
		}
		
		if (source == comboBoxRow3Condition )
		{
			
		}
		
		
		if (source == comboBoxRow1Value )
		{
			
		}
		
		if (source == comboBoxRow2Value )
		{
			
		}
		
		if (source == comboBoxRow3Value )
		{
			
		}
		
		
		
		
		
		if (source == checkBoxRow1)
		{
			// enable row 2
			
			Boolean isSelected = checkBoxRow1.isSelected();
			
			lblSearchBy2.setEnabled(isSelected);
			comboBoxRow2Skill.setEnabled(isSelected);
			comboBoxRow2SkillAttrib.setEnabled(isSelected);
			lblWhere2.setEnabled(isSelected);
			comboBoxRow2SkillAttrib.setEnabled(isSelected);
			lblIs2.setEnabled(isSelected);
			comboBoxRow2Condition.setEnabled(isSelected);
			comboBoxRow2Value.setEnabled(isSelected);
			checkBoxRow2.setEnabled(isSelected);
			
		}
		
		if (source == checkBoxRow2)
		{
			// enable row 3
			Boolean isSelected = checkBoxRow2.isSelected();
			
			lblSearchBy3.setEnabled(isSelected);
			comboBoxRow3Skill.setEnabled(isSelected);
			comboBoxRow3SkillAttrib.setEnabled(isSelected);
			lblWhere3.setEnabled(isSelected);
			comboBoxRow3SkillAttrib.setEnabled(isSelected);
			lblIs3.setEnabled(isSelected);
			comboBoxRow3Condition.setEnabled(isSelected);
			comboBoxRow3Value.setEnabled(isSelected);
//			checkBoxRow3.setEnabled(isSelected);
			
		}
		
//		if (source == checkBoxRow3)
//		{
//			// Do nothing for now
//		}
		
		
		// Default code on any action
		
		logger.info("Search panel action taking place");
		this.updateSearchData();
		
		
	}
}
