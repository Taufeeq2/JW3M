package jw3m.client.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

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
		scrollPane.setBounds(20, 20, 750, 200);
		cPanel.add(scrollPane);
		
		
		table = new JTable();
		
		
		table.setFont(primaryFont);
		
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"1. Novice (10%)", "Minimal, or \"textbook\" knowledge without connecting it to practrice", "Unlikely to be satisfactory unless closely supervised", "Needs close supervision or instruction", "Little or no conception of dealing with complexity", "Tends to see actions in isolation", "Has a grasp on the basics of people development", "Is consultative in approach, Engages staheholders according to a prescribed engagement model."},
				{"2. Beginner (20%)", "Working knowledge of key aspects of practice", "Straightforward tasks likely to be completed to an acceptable standard", "Able to achieve some steps using own judgement, but supervision needed for overall standard", "Appreciates complex situations but only able to achieve partial resolution", "Sees actions as a series of steps", "Makes suggestions/recommendations to assist others to improve", "Identifies functions to be engaged. Effectively connects stakeholders."},
				{"3. Competent (40%)", "Good working and background knowledge of area of practice.", "Fit for purpose, though may lack refinement.", "Able to achieve most tasks using own judgement.", "Copes with complex situations through deliberate analysis and planning.", "Sees actions at least partly in terms of longer-term goals.", "Builds capability within a team using various avenues.", "Actively engages other functions driving new ideas, plans and ensuring alignment."},
				{"4. Proficient (25%)", "Depth of understanding of discipine and area of practice.", "Fully acceptable standard achieved routinely.", "Able to take full responsibility for own work(and that of others where applicable).", "Deals with complex situations more holistically, decision-making more confident.", "Sees overall \"picture\" and how individual actions fit within it.", "Deliberately grows and builds capability with a view of the future.", "Drives decisions, leads across structures, ensures cross functional alignment."},
				{"5. Expert (5%)", "Authoritative knowledge of discipline and deep tacit understanding across area of practice.", "Excellence achieved with relative ease.", "Able to take responsibility for going beyond existing standards and creating own interpretations.", "Holistic grasp of complex situations, moves between intuitive and analytical approaches with ease.", "Sees overall \"picture\" and alternative approaches; vision of what may be possible.", "Sought after to grow & build capability - uses networks, influence and innovative approaches.", "Drives organisational collaboration and has the ability to redirect/re-prioritise focus for the group."},
			},
			new String[] {
				"Proficiency Level", "Knowledgable", "Standard of Work", "Autonomy", "Coping with Complexity", "Perception of Context", "Growing Capability", "Purposeful Collaboration"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				
				return columnEditables[column];
				
			}
		});

		
		
		scrollPane.setViewportView(table);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
	}
}
