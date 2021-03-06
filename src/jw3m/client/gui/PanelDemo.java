package jw3m.client.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class PanelDemo extends JPanel implements ActionListener
{
	final static Logger logger = Logger.getLogger(PanelDemo.class);
	private SkillsClient baseFrame;
	
	private Font primaryFont, secondaryFont;
	
	private JLabel northTitleLabel, southTitleLabel, eastTitleLabel, westTitleLabel, centerTitleLabel;

	public PanelDemo(SkillsClient frame)
	{
		PropertyConfigurator.configure("log4j.properties");
		this.baseFrame = frame;
		primaryFont = baseFrame.getPrimaryFont();
		secondaryFont = baseFrame.getSecondaryFont();
		
		//Create the panels (comment ones you don't need)
		
		JPanel panelNorth = new JPanel();
		JPanel panelSouth = new JPanel();
		JPanel panelEast = new JPanel();
		JPanel panelWest = new JPanel();
		JPanel panelCenter = new JPanel();
		
		
		// these colors to help you get it setup - remove later
		panelNorth.setBorder(new LineBorder(Color.red, 2));
		panelSouth.setBorder(new LineBorder(Color.green, 2));
		panelEast.setBorder(new LineBorder(Color.yellow, 2));
		panelWest.setBorder(new LineBorder(Color.blue, 2));
		panelCenter.setBorder(new LineBorder(Color.black, 2));
		

		
		// Create all north elements
		northTitleLabel = new JLabel("Title Demo");
		northTitleLabel.setFont(baseFrame.getSecondaryFont());
				
		// Create all south elements
		southTitleLabel = new JLabel("south");
		southTitleLabel.setFont(baseFrame.getPrimaryFont());
		
		// Create all east elements
		eastTitleLabel = new JLabel("east");
		eastTitleLabel.setFont(baseFrame.getPrimaryFont());
		
		// Create all west elements
		westTitleLabel = new JLabel("west");
		westTitleLabel.setFont(baseFrame.getPrimaryFont());
		
		// Create all center elements
		centerTitleLabel = new JLabel("center");
		centerTitleLabel.setFont(baseFrame.getPrimaryFont());
		
		
		// Add north elements
		panelNorth.add(northTitleLabel);
		
		// Add south elements
		panelSouth.add(southTitleLabel);
		
		// Add east elements
		panelEast.add(eastTitleLabel);
		
		// Add west elements
		panelWest.add(westTitleLabel);
		
		// Add center elements
		panelCenter.add(centerTitleLabel);
		
		
		
		// Setup layout and add all panels (comment out ones not used)
		setLayout(new BorderLayout(0, 0));
		this.add(panelCenter, BorderLayout.CENTER);
		this.add(panelNorth, BorderLayout.NORTH);
		this.add(panelSouth, BorderLayout.SOUTH);
		this.add(panelEast, BorderLayout.EAST);
		this.add(panelWest, BorderLayout.WEST);
		this.setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
	}
	
	
}
