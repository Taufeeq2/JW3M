package jw3m.client.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;

public class PanelEdit extends JPanel 
{
	
	private SkillsClient baseFrame;
	private JLabel lblEditProfile;
	
	public PanelEdit(SkillsClient frame) {
		setLayout(null);
		
		lblEditProfile = new JLabel("Edit Profile");
		lblEditProfile.setBounds(335, 13, 56, 16);
		add(lblEditProfile);
	}
	
	

}
