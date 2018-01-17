package jw3m.client.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;

public class PanelNotifications extends JPanel 
{
	
	private SkillsClient baseFrame;
	private JLabel lblNotifications;
	
	public PanelNotifications(SkillsClient frame) {
		setLayout(null);
		
		lblNotifications = new JLabel("Notifications");
		lblNotifications.setBounds(345, 23, 56, 16);
		add(lblNotifications);
	} 
	
	
}
