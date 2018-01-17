package jw3m.client.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;

public class PanelNotifications extends JPanel 
{
	
	private SkillsClient baseFrame;
	private JLabel lblNotifications;
	
	public PanelNotifications(SkillsClient frame) {
		setLayout(null);
		
		lblNotifications = new JLabel("Notifications");
		lblNotifications.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 22));
		lblNotifications.setBounds(345, 23, 155, 16);
		add(lblNotifications);
	} 
	
	
}
