package jw3m.client.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;

public class PanelRateSomeone extends JPanel 
{
	private SkillsClient baseFrame;
	private JLabel lblRateSomeone;
	
	public PanelRateSomeone(SkillsClient frame) {
		
		lblRateSomeone = new JLabel("Rate Someone");
		lblRateSomeone.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 22));
		add(lblRateSomeone);
	}

}
