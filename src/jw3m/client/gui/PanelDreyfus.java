package jw3m.client.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class PanelDreyfus extends JPanel implements ActionListener
{
	final static Logger logger = Logger.getLogger(PanelLogin.class);
	private SkillsClient baseFrame;
	
	private Font primaryFont, secondaryFont;

	public PanelDreyfus(SkillsClient frame)
	{
		PropertyConfigurator.configure("log4j.properties");
		this.baseFrame = frame;
		primaryFont = baseFrame.getPrimaryFont();
		secondaryFont = baseFrame.getSecondaryFont();
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
	}
	
	
}
