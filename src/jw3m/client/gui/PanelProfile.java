package jw3m.client.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;

public class PanelProfile extends JPanel 
{
	private JLabel lblMyProfile;
	private JLabel lblUserId;
	private JLabel lblDisplayUserid;
	private JLabel lblName;
	private JLabel lblSurname;
	public PanelProfile() {
		setLayout(null);
		
		lblMyProfile = new JLabel("My Profile");
		lblMyProfile.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 22));
		lblMyProfile.setBounds(303, 13, 116, 38);
		add(lblMyProfile);
		
		lblUserId = new JLabel("User ID");
		lblUserId.setBounds(39, 89, 56, 16);
		add(lblUserId);
		
		lblDisplayUserid = new JLabel("display userid");
		lblDisplayUserid.setBounds(126, 89, 102, 16);
		add(lblDisplayUserid);
		
		lblName = new JLabel("Name");
		lblName.setBounds(39, 137, 56, 16);
		add(lblName);
		
		lblSurname = new JLabel("Surname");
		lblSurname.setBounds(39, 188, 56, 16);
		add(lblSurname);
	}
}
