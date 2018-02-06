package jw3m.client.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;

import jw3m.beans.Hobby;
import jw3m.beans.Rating;
import jw3m.beans.Skill;
import jw3m.beans.User;
import jw3m.beans.UserHobby;
import jw3m.beans.UserSkill;
import jw3m.widgets.BarChart;
import jw3m.widgets.BarChart2;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import java.awt.BorderLayout;

public class PanelReporting extends JPanel implements ActionListener, ListSelectionListener
{
	final static Logger logger = Logger.getLogger(PanelReporting.class);
	static SkillsClient baseFrame;
	private JPanel centerP = null, southP = null, northP = null;
	private JPanel panel;
	MyTableModel a;
	JTable table;
	private JComboBox comboBox;
	private JComboBox comboBoxHobbies;
	private Skill skillSelected = null;
	private Vector data = null;
	private Vector<Rating> ratingsData = null;
	private User thisUser = null;
	private MyTableModel myModel;
	private JLabel titleLabel;
	private JButton buttonHobbies;
	private Vector<User> userList = null;
	private JScrollPane scrollPane = null;
	private JButton buttonSkillsUp;
	private JButton buttonSkillsDown;
	private JButton buttonUsers;
	
	private Font primaryFont, secondaryFont;
	private int skillArrayStart = 0;
	private int skillArrayEnd = 0;
	private int skillArrayDisplay = 7;
	private JLabel lblImgLabel;
	

	public PanelReporting(SkillsClient frame)
	{
		
		baseFrame = frame;
		centerP = new JPanel();
		northP = new JPanel();
		southP = new JPanel();

		primaryFont = baseFrame.getPrimaryFont();
		secondaryFont = baseFrame.getSecondaryFont();
		
		
		
		Object[] values = { "", "" , "" , "" , "" , "" , "" , "" };
		data = new Vector();
		panel = new JPanel();
		panel.setLayout(new GridLayout(1, 1, 0, 0));
		//centerP.add(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(new GridLayout(2, 5, 0, 0));
		centerP.add(panel_1);
		centerP.add(panel);
		
		lblImgLabel = new JLabel(new ImageIcon("resources/PeoplePerSkill_Full.jpg"));
		northP.add(lblImgLabel);
		
		buttonHobbies = new JButton("Hobbies");
		buttonHobbies.setFont(primaryFont);
		buttonHobbies.addActionListener(this);
		
		buttonSkillsDown = new JButton("<-Skills");
		buttonSkillsDown.setFont(primaryFont);
		buttonSkillsDown.addActionListener(this);
		
		buttonSkillsUp = new JButton("Skills->");
		buttonSkillsUp.setFont(primaryFont);
		buttonSkillsUp.addActionListener(this);
		
		buttonUsers = new JButton("Users");
		buttonUsers.setFont(primaryFont);
		buttonUsers.addActionListener(this);
		
		panel_1.setLayout(new GridLayout(4, 1, 0, 0));
		centerP.setLayout(new GridLayout(2,1,0,0 ));
		
		Vector<Skill> skillNames = new Vector<Skill>();
		try 
		{
			baseFrame.getNetSkillList();
		
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		
		
		comboBox = new JComboBox(baseFrame.data_skillList);
		comboBox.setSelectedIndex(-1);
		comboBox.setFont(primaryFont);
		comboBox.addActionListener(this);
		panel_1.add(comboBox);
		
		comboBoxHobbies = new JComboBox(baseFrame.data_hobbyList);
		comboBoxHobbies.setSelectedIndex(-1);
		comboBoxHobbies.setFont(primaryFont);
		comboBoxHobbies.addActionListener(this);
		panel_1.add(comboBoxHobbies);
		
		panel.setLayout(new GridLayout(2, 1, 0, 0));
		
		
		
		
		myModel = new MyTableModel();
		table = new JTable(myModel);
		table.setFont(primaryFont);
		table.setRowHeight(28);
		table.getTableHeader().setFont(primaryFont);
		table.setPreferredScrollableViewportSize(new Dimension(1200, 70));
		table.setFillsViewportHeight(true);
//		a = (MyTableModel) table.getModel();
//		a.insertData(values);

		// Create the scroll pane and add the table to it.
		scrollPane = new JScrollPane(table);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		panel.add(scrollPane);
		setLayout(new BorderLayout(0, 0));
		//add(panel_1);
		//add(panel);
		southP.add(buttonHobbies);
		southP.add(buttonSkillsDown);
		southP.add(buttonSkillsUp);
		southP.add(buttonUsers);
		//add(titleLabel);
		
		this.add(centerP, BorderLayout.CENTER);
		this.add(southP, BorderLayout.SOUTH);
		this.add(northP, BorderLayout.NORTH);
	}

	class MyTableModel extends AbstractTableModel
	{
		private String[] columnNames =
		{ "User ID", "Surname" , "Name" , "Average", "Ratings" , "Mentor" , "Knowledgeable" , 
				"Standard of Work" , "Autonomy" , "Complexity Coping" , "Context Perception" ,
				"Capability Growing" , "Purposeful Collaboration"};

	//	private Vector data = new Vector();
	

		public final Object[] longValues =
		{ "", ""};

		@Override
		public int getColumnCount()
		{
			return columnNames.length;
		}

		@Override
		public int getRowCount()
		{
			return data.size();
		}

		@Override
		public Object getValueAt(int row, int col)
		{
			return ((Vector) data.get(row)).get(col);
		}

		public String getColumnName(int col)
		{
			return columnNames[col];
		}

		public Class getColumnClass(int c)
		{
			return getValueAt(0, c).getClass();
		}

		public void setValueAt(Object value, int row, int col)
		{
			((Vector) data.get(row)).setElementAt(value, col);
			fireTableCellUpdated(row, col);
		}

		public boolean isCellEditable(int row, int col)
		{
			return false;
		}

		public void insertData(Object[] values)
		{
			data.add(new Vector());
			for (int i = 0; i < values.length; i++)
			{
				((Vector) data.get(data.size() - 1)).add(values[i]);
			}
			fireTableDataChanged();
		}

		public void removeRow(int row)
		{
			data.removeElementAt(row);
			fireTableDataChanged();
		}
	}

	private static void createAndShowGUI()
	{
		JFrame frame = new JFrame("Reporting");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		PanelReporting newContentPane = new PanelReporting(baseFrame);
		frame.setContentPane(newContentPane);

		// Display the window.
		frame.setLocation(650, 300);
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args)
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				createAndShowGUI();
			}
		});
	}

	@Override
	public void valueChanged(ListSelectionEvent e)
	{

	}	
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		Object source = ae.getSource();
		
		if (source == buttonHobbies)
		{
			skillArrayEnd = 0;
			skillArrayStart = 0;
			userList = baseFrame.data_userList;
			double[] values = new double [userList.size()];
			String[] labels = new String[userList.size()];
			Color[] colors = new Color[userList.size()];
			String barChartTitle = "Hobbies";
			for (int i=0; i<userList.size(); i++)
			{
				thisUser = userList.get(i);
				//labels[i] = thisUser.getSurname();
				Vector<UserHobby> userHobbyList = baseFrame.getNetUserHobby(thisUser);
				values[i] = userHobbyList.size();
				labels[i] = thisUser.getSurname() + " [" + (int)values[i] + "]";
				int rem = i%5;
				if (rem == 0)
				{
					colors[i] = Color.red;
				}
				if (rem == 1)
				{
					colors[i] = Color.orange; 
				}
				if (rem == 2)
				{
					colors[i] = Color.yellow;
				}
				if (rem == 3)
				{
					colors[i] = Color.green;
				}
				if (rem == 4)
				{
					colors[i] = Color.blue;
				}
			}
			BarChart barChart = new BarChart(values, labels, colors, barChartTitle);
			//BarChart2 barChart = new BarChart2(values, labels, barChartTitle);
			panel.removeAll();
			panel.validate();
			panel.repaint();
			panel.add(barChart);
			panel.validate();
			panel.repaint();
		}
		
		if (source == buttonUsers)
		{
			skillArrayEnd = 0;
			skillArrayStart = 0;
			userList = baseFrame.data_userList;
			double[] values = new double [userList.size()];
			String[] labels = new String[userList.size()];
			Color[] colors = new Color[userList.size()];
			String barChartTitle = "Skills";
			for (int i=0; i<userList.size(); i++)
			{
				thisUser = userList.get(i);
				//labels[i] = thisUser.getSurname();
				Vector<UserSkill> userSkillsList = baseFrame.getNetUserSkills(thisUser);
				values[i] = userSkillsList.size();
				labels[i] = thisUser.getSurname() + " [" + (int)values[i] + "]";
				int rem = i%5;
				if (rem == 0)
				{
					colors[i] = Color.red;
				}
				if (rem == 1)
				{
					colors[i] = Color.orange; 
				}
				if (rem == 2)
				{
					colors[i] = Color.yellow;
				}
				if (rem == 3)
				{
					colors[i] = Color.green;
				}
				if (rem == 4)
				{
					colors[i] = Color.blue;
				}
			}
			BarChart barChart = new BarChart(values, labels, colors, barChartTitle);
			panel.removeAll();
			panel.validate();
			panel.repaint();
			panel.add(barChart);
			panel.validate();
			panel.repaint();
		}
		
		if (source == buttonSkillsUp)
		{
			Vector<Skill> skillList = baseFrame.data_skillList;
			double[] values = new double [skillList.size()];
			String[] labels = new String[skillList.size()];
			Color[] colors = new Color[skillList.size()];
			String barChartTitle = "Users";
			for (int i=0; i<skillList.size(); i++)
			{
				Skill thisSkill = skillList.get(i);
				//labels[i] = thisSkill.getSkillName();
				Vector<User> userSkillList = baseFrame.getNetSkillsUser(thisSkill);
				values[i] = userSkillList.size();
				labels[i] = thisSkill.getSkillName() + " [" + (int)values[i] + "]";
				
			}
			//the graph cant handle too many skills at once, so going to sort by size
			//and only display a few
			//Taking out the sort so the order of skills is the same as the table
			//char sorted = 'n';
			//while (sorted == 'n')
			//{
			//	sorted = 'y';
			//	for (int i=1; i<skillList.size(); i++)
			//	{
			//		if(values[i]>values[i-1])
			//		{
			//			sorted = 'n';
			//			int tempValue = (int)(values[i-1]);
			//			String tempLabel = labels[i-1];
			//			values[i-1] = values[i];
			//			labels[i-1] = labels[i];
			//			values[i] = tempValue;
			//			labels[i] = tempLabel;
			//		}
			//	}
			//}
			int sizeOfArray = skillList.size();
			int sizeOfDisplay = skillArrayDisplay;
			double[] valuesTop6 = new double [sizeOfDisplay];
			String[] labelsTop6 = new String[sizeOfDisplay];
			Color[] colorsTop6 = new Color[sizeOfDisplay];
			
			if(skillArrayEnd == 0)
			{
				skillArrayEnd = skillArrayDisplay;
				skillArrayStart = 0;
				if (skillArrayEnd > sizeOfArray)
				{
					skillArrayEnd = sizeOfArray;
				}
			}
			else
			{
				skillArrayEnd = skillArrayEnd + skillArrayDisplay;
				skillArrayStart = skillArrayEnd - skillArrayDisplay;
				if (skillArrayEnd > sizeOfArray)
				{
					skillArrayEnd = sizeOfArray;
					skillArrayStart = skillArrayEnd - skillArrayDisplay;
				}	
				if (skillArrayStart < 0)
				{
					skillArrayStart = 0;
					skillArrayEnd = skillArrayDisplay;
				}	
			}
		//	System.out.println("Array Start = " + skillArrayStart + " Array End = " + skillArrayEnd +
		//			 " Array Size " + sizeOfArray);
			
			for (int i=0; i<skillArrayDisplay; i++)
			{
				valuesTop6[i] = values[skillArrayStart + i];
				labelsTop6[i] = labels[skillArrayStart + i];
				int rem = i%5;
				if (rem == 0)
				{
					colorsTop6[i] = Color.red;
				}
				if (rem == 1)
				{
					colorsTop6[i] = Color.orange; 
				}
				if (rem == 2)
				{
					colorsTop6[i] = Color.yellow;
				}
				if (rem == 3)
				{
					colorsTop6[i] = Color.green;
				}
				if (rem == 4)
				{
					colorsTop6[i] = Color.blue;
				}
				
			}
			BarChart barChart = new BarChart(valuesTop6, labelsTop6, colorsTop6, barChartTitle);
			panel.removeAll();
			panel.validate();
			panel.repaint();
			panel.add(barChart);
			panel.validate();
			panel.repaint();
		}
		
		if (source == buttonSkillsDown)
		{
			Vector<Skill> skillList = baseFrame.data_skillList;
			double[] values = new double [skillList.size()];
			String[] labels = new String[skillList.size()];
			Color[] colors = new Color[skillList.size()];
			String barChartTitle = "Users";
			for (int i=0; i<skillList.size(); i++)
			{
				Skill thisSkill = skillList.get(i);
				//labels[i] = thisSkill.getSkillName();
				Vector<User> userSkillList = baseFrame.getNetSkillsUser(thisSkill);
				values[i] = userSkillList.size();
				labels[i] = thisSkill.getSkillName() + " [" + (int)values[i] + "]";
				
			}
			//the graph cant handle too many skills at once, so going to sort by size
			//and only display a few
			//taking out the sort so the skills will be in the same order as the table
			//char sorted = 'n';
			//while (sorted == 'n')
			//{
			//	sorted = 'y';
			//	for (int i=1; i<skillList.size(); i++)
			//	{
			//		if(values[i]>values[i-1])
			//		{
			//			sorted = 'n';
			//			int tempValue = (int)(values[i-1]);
			//			String tempLabel = labels[i-1];
			//			values[i-1] = values[i];
			//			labels[i-1] = labels[i];
			//			values[i] = tempValue;
			//			labels[i] = tempLabel;
			//		}
			//	}
			//}
			int sizeOfArray = skillList.size();
			int sizeOfDisplay = skillArrayDisplay;
			double[] valuesTop6 = new double [sizeOfDisplay];
			String[] labelsTop6 = new String[sizeOfDisplay];
			Color[] colorsTop6 = new Color[sizeOfDisplay];
			
			if(skillArrayEnd == 0)
			{
				skillArrayEnd = skillArrayDisplay;
				skillArrayStart = 0;
				if (skillArrayEnd > sizeOfArray)
				{
					skillArrayEnd = sizeOfArray;
				}
			}
			else
			{
				skillArrayEnd = skillArrayEnd - skillArrayDisplay;
				skillArrayStart = skillArrayEnd - skillArrayDisplay;
				if (skillArrayEnd > sizeOfArray)
				{
					skillArrayEnd = sizeOfArray;
					skillArrayStart = skillArrayEnd - skillArrayDisplay;
				}	
				if (skillArrayStart < 0)
				{
					skillArrayStart = 0;
					skillArrayEnd = skillArrayDisplay;
				}	
			}
		//	System.out.println("Array Start = " + skillArrayStart + " Array End = " + skillArrayEnd +
		//			 " Array Size " + sizeOfArray);
			
			for (int i=0; i<skillArrayDisplay; i++)
			{
				valuesTop6[i] = values[skillArrayStart + i];
				labelsTop6[i] = labels[skillArrayStart + i];
				int rem = i%5;
				if (rem == 0)
				{
					colorsTop6[i] = Color.red;
				}
				if (rem == 1)
				{
					colorsTop6[i] = Color.orange; 
				}
				if (rem == 2)
				{
					colorsTop6[i] = Color.yellow;
				}
				if (rem == 3)
				{
					colorsTop6[i] = Color.green;
				}
				if (rem == 4)
				{
					colorsTop6[i] = Color.blue;
				}
				
			}
			BarChart barChart = new BarChart(valuesTop6, labelsTop6, colorsTop6, barChartTitle);
			panel.removeAll();
			panel.validate();
			panel.repaint();
			panel.add(barChart);
			panel.validate();
			panel.repaint();
		}
		
		if (source == comboBox)
		{
			skillArrayEnd = 0;
			skillArrayStart = 0;
			panel.removeAll();
			panel.validate();
			panel.repaint();
			panel.add(scrollPane);
			panel.validate();
			panel.repaint();
			int numberOfRowsInTable = table.getRowCount();
			
			for (int x=0; x<numberOfRowsInTable; x++)
			{
				a.removeRow(0);
			}
			
			skillSelected = (Skill) comboBox.getSelectedItem();
		  
			try
			{
		  		
		   		ratingsData = baseFrame.getNetSkillRating(skillSelected);
				Vector<String> vectUserId = new Vector<String>();
				Vector<Double> vectSum = new Vector<Double>();
				Vector<Integer> vectCount = new Vector<Integer>();
				Vector<Double> vectKnowledgeable = new Vector<Double>();
				Vector<Double> vectStandard = new Vector<Double>();
				Vector<Double> vectAutonomy = new Vector<Double>();
				Vector<Double> vectComplexity = new Vector<Double>();
				Vector<Double> vectContext = new Vector<Double>();
				Vector<Double> vectCapability = new Vector<Double>();
				Vector<Double> vectCollaboration = new Vector<Double>();
				
				for (int x=0; x<ratingsData.size(); x++)
				{
					Rating thisRating = ratingsData.get(x);	
					String saveUserId = thisRating.getUserID();
					char matched = 'n';
				
					for (int y=0; y<vectUserId.size(); y++ )	
					{
					
						if (saveUserId.equals(vectUserId.get(y)))
						{
							
							matched = 'y';
							double sum = vectSum.get(y) + thisRating.getLevel();
							vectSum.set(y, sum);
							int count = vectCount.get(y) + 1;
							vectCount.set(y, count);
							double knowledgeable = vectKnowledgeable.get(y) + thisRating.getKnowledge();
							vectKnowledgeable.set(y, knowledgeable);
							double standard = vectStandard.get(y) + thisRating.getWorkStandard();
							vectStandard.set(y, standard);
							double autonomy = vectAutonomy.get(y) + thisRating.getAutonomy();
							vectAutonomy.set(y, autonomy);
							double complexity = vectComplexity.get(y) + thisRating.getComplexityCoping();
							vectComplexity.set(y, complexity);
							double context = vectContext.get(y) + thisRating.getContextPerception();
							vectContext.set(y, context);
							double capability = vectCapability.get(y) + thisRating.getCapabilityGrowing();
							vectCapability.set(y, capability);
							double collaboration = vectCollaboration.get(y) + thisRating.getCollaboration();
							vectCollaboration.set(y, collaboration);
						}
					}
					if (matched == 'n')
					{
						
						vectUserId.addElement(saveUserId);
						vectSum.addElement((double)thisRating.getLevel());
						vectCount.addElement(1);
						vectKnowledgeable.addElement((double)thisRating.getKnowledge());
						vectStandard.addElement((double)thisRating.getWorkStandard());
						vectAutonomy.addElement((double)thisRating.getAutonomy());
						vectComplexity.addElement((double)thisRating.getComplexityCoping());
						vectContext.addElement((double)thisRating.getContextPerception());
						vectCapability.addElement((double)thisRating.getCapabilityGrowing());
						vectCollaboration.addElement((double)thisRating.getCollaboration());
					}
				}	
				
	 			//sort the vector by average rating
				char sorted = 'n';
	  			while (sorted == 'n')
	  			{
	  				sorted = 'y';
	  				for (int ind=1; ind<vectUserId.size(); ind++)
	  				{
	  					if((vectSum.get(ind-1)/vectCount.get(ind-1)) < (vectSum.get(ind)/vectCount.get(ind)))
	  					{
	  						sorted = 'n';
	  						String tempUserId = vectUserId.get(ind-1);
	  						Double tempSum = vectSum.get(ind-1);
	  						Integer tempCount = vectCount.get(ind-1);
	  						Double tempKnowledgeable = vectKnowledgeable.get(ind-1);
	  						Double tempStandard = vectStandard.get(ind-1);
	  						Double tempAutonomy = vectAutonomy.get(ind-1);
	  						Double tempComplexity = vectComplexity.get(ind-1);
	  						Double tempContext = vectContext.get(ind-1);
	  						Double tempCapability = vectContext.get(ind-1);
	  						Double tempCollaboration = vectContext.get(ind-1);
	  						
	  						vectUserId.set(ind-1, vectUserId.get(ind));
	  						vectSum.set(ind-1, vectSum.get(ind));
	  						vectCount.set(ind-1,  vectCount.get(ind));
	  						vectKnowledgeable.set(ind-1, vectKnowledgeable.get(ind));
	  						vectStandard.set(ind-1, vectStandard.get(ind));
	  						vectAutonomy.set(ind-1, vectAutonomy.get(ind));
	  						vectComplexity.set(ind-1, vectComplexity.get(ind));
	  						vectContext.set(ind-1, vectContext.get(ind));
	  						vectCapability.set(ind-1, vectCapability.get(ind));
	  						vectCollaboration.set(ind-1, vectCollaboration.get(ind));
	  						
	  						vectUserId.set(ind,  tempUserId);
	  						vectSum.set(ind, tempSum);
	  						vectCount.set(ind,  tempCount);
	  						vectKnowledgeable.set(ind, tempKnowledgeable);
	  						vectStandard.set(ind, tempStandard);
	  						vectAutonomy.set(ind, tempAutonomy);
	  						vectComplexity.set(ind, tempComplexity);
	  						vectContext.set(ind, tempContext);
	  						vectCapability.set(ind, tempCapability);
	  						vectCollaboration.set(ind, tempCollaboration);
	  						
	  					}
	  				}
	  			}
				
				
				for (int z=0; z<vectUserId.size(); z++ )
				{	
					Object[] data = new Object[13];
					double aveLevel = vectSum.get(z)/vectCount.get(z);
					int aveLevelRounded = (int)(aveLevel + 0.5);
					double aveKnowledgeable = vectKnowledgeable.get(z)/vectCount.get(z);
					int aveKnowledgeableRounded = (int)(aveKnowledgeable + 0.5);
					double aveStandard = vectStandard.get(z)/vectCount.get(z);
					int aveStandardRounded = (int)(aveStandard + 0.5);
					double aveAutonomy = vectAutonomy.get(z)/vectCount.get(z);
					int aveAutonomyRounded = (int)(aveAutonomy + 0.5);
					double aveComplexity = vectComplexity.get(z)/vectCount.get(z);
					int aveComplexityRounded = (int)(aveComplexity + 0.5);
					double aveContext = vectContext.get(z)/vectCount.get(z);
					int aveContextRounded = (int)(aveContext + 0.5);
					double aveCapability = vectCapability.get(z)/vectCount.get(z);
					int aveCapabilityRounded = (int)(aveCapability + 0.5);
					double aveCollaboration = vectCollaboration.get(z)/vectCount.get(z);
					int aveCollaborationRounded = (int)(aveCollaboration + 0.5);
					int numberOfRatings = vectCount.get(z);
					String thisUserId = vectUserId.get(z);
						
					User thisUser = new User();
					thisUser = baseFrame.getNetUser(thisUserId);
					
						
						data[0] = thisUserId;
						data[1] = thisUser.getSurname();
						data[2] = thisUser.getFirstName();
						data[3] = aveLevelRounded;
						data[4] = numberOfRatings;
						data[5] = thisUser.isMentor();
						data[6] = aveKnowledgeableRounded;
						data[7] = aveStandardRounded;
						data[8] = aveAutonomyRounded;
						data[9] = aveComplexityRounded;
						data[10] = aveContextRounded;
						data[11] = aveCapabilityRounded;
						data[12] = aveCollaborationRounded;
					
					a = (MyTableModel) table.getModel();
					a.insertData(data);
					
				}
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		
		if (source == comboBoxHobbies)
		{
			skillArrayEnd = 0;
			skillArrayStart = 0;
			panel.removeAll();
			panel.validate();
			panel.repaint();
			panel.add(scrollPane);
			panel.validate();
			panel.repaint();
			int numberOfRowsInTable = table.getRowCount();
			
			for (int x=0; x<numberOfRowsInTable; x++)
			{
				a.removeRow(0);
			}
			
			Hobby hobbySelected = (Hobby) comboBoxHobbies.getSelectedItem();
			
		//	System.out.println("On line 792 hobbySelected = " + hobbySelected);
		  
			try
			{
		  		
		   		Vector<User> hobbiesData = baseFrame.getNetUserHobby(hobbySelected);
				Vector<String> vectUserId = new Vector<String>();
				Vector<Double> vectSum = new Vector<Double>();
				Vector<Integer> vectCount = new Vector<Integer>();
				Vector<Double> vectKnowledgeable = new Vector<Double>();
				Vector<Double> vectStandard = new Vector<Double>();
				Vector<Double> vectAutonomy = new Vector<Double>();
				Vector<Double> vectComplexity = new Vector<Double>();
				Vector<Double> vectContext = new Vector<Double>();
				Vector<Double> vectCapability = new Vector<Double>();
				Vector<Double> vectCollaboration = new Vector<Double>();
				
			//	System.out.println("On line 809 hobbiesData.size() = " + hobbiesData.size());
				
				for (int x=0; x<hobbiesData.size(); x++)
				{
					User thisUser = hobbiesData.get(x);	
					String saveUserId = thisUser.getUserName();
					
	 			
				
				
				
					thisUser = baseFrame.getNetUser(saveUserId);
					
					Object[] data = new Object[13];
						data[0] = thisUser.getUserName();
						data[1] = thisUser.getSurname();
						data[2] = thisUser.getFirstName();
						data[3] = 0;
						data[4] = 0;
						data[5] = 0;
						data[6] = 0;
						data[7] = 0;
						data[8] = 0;
						data[9] = 0;
						data[10] = 0;
						data[11] = 0;
						data[12] = 0;
					
					a = (MyTableModel) table.getModel();
					a.insertData(data);
					
				//	System.out.println("added " + thisUser + " to the data array");
					
				}
				
			}
			
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
