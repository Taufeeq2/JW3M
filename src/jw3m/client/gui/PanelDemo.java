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
		
		// this.setBorder(border);
		
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
		

		
		
		northTitleLabel = new JLabel("Title Demo");
		northTitleLabel.setFont(baseFrame.getSecondaryFont());
		
		southTitleLabel = new JLabel("south");
		southTitleLabel.setFont(baseFrame.getPrimaryFont());
		
		
		eastTitleLabel = new JLabel("east");
		eastTitleLabel.setFont(baseFrame.getPrimaryFont());
		
		westTitleLabel = new JLabel("west");
		westTitleLabel.setFont(baseFrame.getPrimaryFont());
		
		centerTitleLabel = new JLabel("center");
		centerTitleLabel.setFont(baseFrame.getPrimaryFont());
		
		
		panelNorth.add(northTitleLabel);
		panelSouth.add(southTitleLabel);
		panelEast.add(eastTitleLabel);
		panelWest.add(westTitleLabel);
		panelCenter.add(centerTitleLabel);
		
		
	//	this.setLayout(layoutMgr);
		
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
