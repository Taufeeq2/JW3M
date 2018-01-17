package jw3m.client.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class PanelNotifications extends JPanel 
{
	
	private SkillsClient baseFrame;
	private JLabel lblNotifications;
	
	public PanelNotifications(SkillsClient frame) {
		setBackground(new Color(0, 153, 255));
		setLayout(null);
		
		lblNotifications = new JLabel("Notifications");
		lblNotifications.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 22));
		lblNotifications.setBounds(345, 23, 155, 16);
		add(lblNotifications);
	} 
	
	
}
