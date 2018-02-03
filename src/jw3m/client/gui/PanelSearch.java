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
	private Vector<Rating> rating1List = new Vector<Rating>();
	private Vector<Rating> rating2List = new Vector<Rating>();
	private Vector<Rating> rating3List = new Vector<Rating>();
	private Vector<String> skillElement = new Vector<String>();
	private Vector<String> conditionElement = new Vector<String>();
	private Vector<Integer> valueElement = new Vector<Integer>();
	
	private Vector<Skill> skillNames = new Vector<Skill>();
	private JLabel lblSkill;
	private JLabel lblAttribute;
	private JLabel lblOperator;
	private JLabel lblValue;
	private JLabel label1Text;
	private JLabel label2Text;
	private JLabel label3Text;
	
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
		lblSearchBy3.setBounds(53, 166, 133, 29);
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
		
//		label1Text = new JLabel("Row 1 search");
//		label1Text.setFont(primaryFont);
//		label1Text.setBounds(170, 85, 635, 16);
//		cPanel.add(label1Text);
//		
//		label2Text = new JLabel("Row 2 search");
//		label2Text.setFont(primaryFont);
//		label2Text.setBounds(170, 140, 714, 16);
//		cPanel.add(label2Text);
//		
//		label3Text = new JLabel("Row 3 search");
//		label3Text.setFont(primaryFont);
//		label3Text.setBounds(170, 199, 714, 16);
//		cPanel.add(label3Text);
		

		
		
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
		skillElement.add("Coping with Complexity");		
		skillElement.add("Perception of Context");
		skillElement.add("Growing Capablity");
		skillElement.add("Purposeful Collaboration");

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
		comboBoxRow2Skill.setSelectedIndex(1);
		comboBoxRow3Skill.setSelectedIndex(2);
		
		// Default is 0 and then unselected , unselected
		comboBoxRow1SkillAttrib.setSelectedIndex(0);
		comboBoxRow2SkillAttrib.setSelectedIndex(0);
		comboBoxRow3SkillAttrib.setSelectedIndex(0);
		
		// Default selection is >=
		comboBoxRow1Condition.setSelectedIndex(2);
		comboBoxRow2Condition.setSelectedIndex(2);
		comboBoxRow3Condition.setSelectedIndex(2);
		
		
		// Default skill as 3
		comboBoxRow1Value.setSelectedIndex(2);
		comboBoxRow2Value.setSelectedIndex(2);
		comboBoxRow3Value.setSelectedIndex(2);
		
		checkBoxRow1.setSelected(true);
		checkBoxRow2.setSelected(false);
//		checkBoxRow3.setSelected(false);
		
		
//		label1Text.setText("");
//		label2Text.setText("");
//		label3Text.setText("");
		
		
		
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
	
	public String getSkillName(int skillID)
	{
		for (int i = 0; i < baseFrame.data_skillList.size();  i++)
		{
			if (baseFrame.data_skillList.get(i).getSkillID().equals(skillID) )
			{
				return baseFrame.data_skillList.get(i).getSkillName();
			}
				
		}
		
		return null;
	}
	
//	public maybe get userObj from UserName?
//	{
//		for (int i = 0; i < baseFrame.data_skillList.size();  i++)
//		{
//			if (baseFrame.data_skillList.get(i).getSkillID().equals(skillID) )
//			{
//				return baseFrame.data_skillList.get(i).getSkillName();
//			}
//				
//		}
//		
//		return null;
//	}
	
	
	public Boolean filterRating(Rating rating, int attribIn, int conditionIn, int valueIn)
	{
		int valueToTest = -1;
		valueIn = valueIn+1; // cos vector makes it off by 1
		
		switch (attribIn)
		{
			case 0:  valueToTest = rating.getKnowledge(); 			logger.info("Knowledge"); break;
			case 1:  valueToTest = rating.getWorkStandard();		logger.info("WorkStandard"); break;
			case 2:  valueToTest = rating.getAutonomy();			logger.info("Autonomy"); break;
			case 3:  valueToTest = rating.getComplexityCoping();	logger.info("ComplexityCoping"); break;	
			case 4:  valueToTest = rating.getContextPerception();	logger.info("ContextPerceptio"); break;
			case 5:  valueToTest = rating.getCapabilityGrowing();	logger.info("CapabilityGrowing"); break;
			case 6:  valueToTest = rating.getCollaboration();		logger.info("Collaboration"); break;
			//	skillElement.add("Knowledge");
			//	skillElement.add("Work Standard");
			//	skillElement.add("Autonomy");
			//	skillElement.add("Coping with Complexity");		
			//	skillElement.add("Perception of Context");
			//	skillElement.add("Growing Capablity");
			//	skillElement.add("Purposeful Collaboration");
			
			
		}
		logger.info("size of list 1 " + rating1List.size() + " list 2 " + rating2List.size() + " list 3 " +  rating3List.size());
		
		logger.info("condition case " + conditionIn);
		logger.info("value to test " + valueToTest);
		logger.info("value in from field  " + valueIn);
		switch (conditionIn)
		{
			case 0 : if (valueToTest == valueIn) 
						{
							logger.info(valueToTest + " == " + valueIn);
							return true;
						}
			break;
			
			case 1 : if (valueToTest <= valueIn) 
						{
							logger.info(valueToTest + " <= " + valueIn);
							return true;
						}
			break;
		
			case 2 : if (valueToTest >= valueIn) 
						{
							logger.info(valueToTest + " >= " + valueIn);
							return true;
						}
			break;
			
			case 3 : if (valueToTest < valueIn) 
						{
							logger.info(valueToTest + " < " + valueIn);
							return true;
						}
			break;
			
			case 4 : if (valueToTest > valueIn) 
						{
							logger.info(valueToTest + " > " + valueIn);
							return true;
						}
			break;
		}	
			
			
			
			//		conditionElement.add(" = " );
			//		conditionElement.add(" < and = " );
			//		conditionElement.add(" > and = " );
			//		conditionElement.add(" < " );
			//		conditionElement.add(" > " );
		
		
		// So basically we find what to test then how to test it and if we fail all that we return false
		
		return false;
	}
	

	
	public String searchRow(int ratingRow, Skill skillIn, int attribIn, int conditionIn, int value)
	{
		
		Vector<Rating> tempRatingList = new Vector<Rating>();
		String rowStr = "";
		

		
		String skillNameStr = "Skill " + skillIn.getSkillName() + "\n";
		
			
		Vector<User> userList = baseFrame.getNetSkillsUser(skillIn);
		Vector<Skill> skillList = baseFrame.data_skillList;
		
		
		String ratingsStr = "xxx";
//		String userNameStr = "";
		
		
		for (int i = 0; i < userList.size() ; i ++)
		{
			// now i have a vector of rating
			Vector<Rating> ratingVect = baseFrame.getNetUserRating(userList.get(i));
			for (int j = 0; j < ratingVect.size() ; j ++)
			{
				Rating rating = ratingVect.get(j);
				
				String s1 = skillIn.getSkillName() ;
				String s2 = getSkillName( rating.getSkillID() );
				// if the skill matches the filter
				if ( s1.equals(s2)     ) 
				{
					
				
				// so now we just get the self rating
					if (     rating.getRaterID().equals(   rating.getUserID()  )    )
					{

						
						// now switch
						//Rating tempRatingFromSwitch = switchCases( attribIn,  conditionIn,  value);
						if ( filterRating( rating, attribIn,  conditionIn,  value) )
						{
						
							tempRatingList.add(rating);
						}		
								
					}
				} // end of is rating matching skill filter
			}
			
			// now i want to get the self ratings only
			
			
			
		//	ratingsStr = ratingsStr +  ) + "\n";
			
		}
		
		if (ratingRow == 1)
		{
			rating1List = tempRatingList;
			rowStr = "Row 1 (matched " + rating1List.size() + ") :";
		}
		
		if (ratingRow == 2)
		{
			rating2List = tempRatingList;
			rowStr = "Row 2 (matched " + rating2List.size() + ") :";
		}
		
		if (ratingRow == 3)
		{
			rating3List = tempRatingList;
			rowStr = "Row 3 (matched " + rating3List.size() + ") :";
		}
		
		// now make a vector of ratings by taking the vector of this skill ?
		
		
//		
//		skillList.get(1).getSkillName() 
//		
//		
//		Vector<User> userList = baseFrame.getNetSkillsUser(   skillIn    );
		
//		0) Skill name				
//		1) Vector of users
// 	    2) Now filter by atribute where
//		3) =
//		4) skill attribute rating of 3
//		5) return String of users


//		skillIn
//		attribIn
//		conditionIn
//		value
		
		
//		( comboBoxRow2Skill.getSelectedItem() != null ) &&
//		( comboBoxRow2SkillAttrib.getSelectedItem() != null ) && 
//		( comboBoxRow2Condition.getSelectedItem() != null ) && 
//		( comboBoxRow2Value.getSelectedItem() != null )  )
		
		
		return rowStr + skillNameStr; 
		
	}
	
	public String ratingListToText(Vector<Rating> ratingListIn)
	{
		String result = ""; 
		
		User tempUser;
		
		
		for (int i = 0; i < ratingListIn.size();i++)
		{
			Rating rating = ratingListIn.get(i);
			
			tempUser = baseFrame.getNetUser(rating.getUserID());
			
			
			result = result + "  " + tempUser.getFirstName() + " " + tempUser.getSurname()  + " | ";
		
//			result = result + "  UserID " +	rating.getUserID() + ",";
			result = result + "   Knowledge:" + rating.getKnowledge() + ", ";
			result = result + "   WorkStandard:" + rating.getWorkStandard() + ", ";
			result = result + "   Autonomy:" + rating.getAutonomy() + ", ";
			result = result + "   Coping with Complexity:" + rating.getComplexityCoping() + ", ";
			result = result + "   Perception of Context:" + rating.getContextPerception() + ", ";
			result = result + "   Growing Capablity:" + rating.getCapabilityGrowing() + ", ";
			result = result + "   Purposeful Collaboration:" + rating.getCollaboration() + "\n";

		}
		
		return result;
	}
	
	public void updateSearchData()
	{
		String currentText = txtArea.getText();
		
		String result1 = "Row 1 result : ";
		String result2 = "Row 2 result :";
		String result3 = "Row 3 result :";
	
		
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
		
	
		lblSearchBy1.setToolTipText(row1Str);
		lblSearchBy2.setToolTipText(row2Str);
		lblSearchBy3.setToolTipText(row3Str);
		
		
//		+ "Next row enabled (" + checkBoxRow3.isSelected() + ").";
		
		
//		Skill temp = (Skill)comboBoxRow1Skill.getSelectedItem();
		
		// If row 1 not null
		if (  ( comboBoxRow1Skill.getSelectedItem() != null ) &&
				( comboBoxRow1SkillAttrib.getSelectedItem() != null ) && 
				( comboBoxRow1Condition.getSelectedItem() != null ) && 
				( comboBoxRow1Value.getSelectedItem() != null )  )
		{
			// row 1 good to search
			result1 = searchRow(1, (Skill)comboBoxRow1Skill.getSelectedItem(), 
						comboBoxRow1SkillAttrib.getSelectedIndex() , 
						comboBoxRow1Condition.getSelectedIndex() ,
						comboBoxRow1Value.getSelectedIndex() );
		}
			else
		{
			// row 2 not good to search
		}
 
		// If row 1 checked and rest not null from 2 !!!!!!!!!!!!!!!!!!!!!!!!!!!!
		if (  checkBoxRow1.isSelected() &&
				( comboBoxRow2Skill.getSelectedItem() != null ) &&
				( comboBoxRow2SkillAttrib.getSelectedItem() != null ) && 
				( comboBoxRow2Condition.getSelectedItem() != null ) && 
				( comboBoxRow2Value.getSelectedItem() != null )  )
		{
			// row 2 good to search
			result2 = searchRow(2, (Skill)comboBoxRow2Skill.getSelectedItem(), 
					comboBoxRow2SkillAttrib.getSelectedIndex() , 
					comboBoxRow2Condition.getSelectedIndex() ,
					comboBoxRow2Value.getSelectedIndex() );
			
		}
			else
		{
			// row 2 not good to search
		}
		
		// If row 2 checked and rest not null from 3   !!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		if (  checkBoxRow2.isSelected() &&
				( comboBoxRow3Skill.getSelectedItem() != null ) &&
				( comboBoxRow3SkillAttrib.getSelectedItem() != null ) && 
				( comboBoxRow3Condition.getSelectedItem() != null ) && 
				( comboBoxRow3Value.getSelectedItem() != null )  )
		{
			// row 3 good to search
			result3 = searchRow(3, (Skill)comboBoxRow3Skill.getSelectedItem(), 
					comboBoxRow3SkillAttrib.getSelectedIndex() , 
					comboBoxRow3Condition.getSelectedIndex() ,
					comboBoxRow3Value.getSelectedIndex() );
		}
			else
		{
			// row 3 not good to search
		}
		
		
		
	
		result1 = result1 + ratingListToText(rating1List);
		result2 = result2 + ratingListToText(rating2List);
		result3 = result3 + ratingListToText(rating3List);
		
		
		
		txtArea.setText(
				result1 + "\n" +
				result2 + "\n" +
				result3  );
		
//		txtArea.setText(
//				rating1List + "\n" +
//				rating2List + "\n" +
//				rating3List  );

		
	}
	
//	public String getStuff(Vector<User> userListIn)
//	{
//		String resultStr = "";
//		
//		for (int i = 0; i < userListIn.size() ; i ++ )
//		{
//			resultStr = resultStr + userListIn.get(i) + ":" + baseFrame.getnet;
//			
//		}
//		
//		return resultStr;
//	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		
		//By default i must clear result vectors 1 2 and 3
		rating1List.removeAllElements();
		rating2List.removeAllElements();
		rating3List.removeAllElements();
		
		Object source = e.getSource();
		
		if (source == comboBoxRow1SkillAttrib )
		{
			System.out.println("!!!!!!!!    index is   " + comboBoxRow1SkillAttrib.getSelectedIndex() );
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
		
		//logger.info("Search panel action taking place");
		this.updateSearchData();
		
		
	}
}
