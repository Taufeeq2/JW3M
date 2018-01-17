package jw3m.client.gui;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JRadioButton;



import javax.swing.JButton;

public class PanelProfile extends JPanel 
{
	private SkillsClient baseFrame;
	private JLabel lblMyProfile;
	private JLabel lblUserId;
	private JLabel lblDisplayUserid;
	private JLabel lblName;
	private JLabel lblSurname;
	private JLabel lblDisplayName;
	private JLabel lblDisplaySurname;
	private JLabel lblEmail;
	private JLabel lblMobile;
	private JLabel lblMentor;
	private JLabel label;
	private JLabel label_1;
	private JRadioButton rdbtnYes;
	private JRadioButton rdbtnNo;
	private JPanel panel;
	private JPanel panel_1;
	
	public PanelProfile(SkillsClient frame) {
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
		
		lblDisplayName = new JLabel("display name");
		lblDisplayName.setBounds(126, 137, 102, 16);
		add(lblDisplayName);
		
		lblDisplaySurname = new JLabel("display surname");
		lblDisplaySurname.setBounds(126, 188, 102, 16);
		add(lblDisplaySurname);
		
		lblEmail = new JLabel("Email");
		lblEmail.setBounds(39, 229, 56, 16);
		add(lblEmail);
		
		lblMobile = new JLabel("Mobile");
		lblMobile.setBounds(39, 272, 56, 16);
		add(lblMobile);
		
		lblMentor = new JLabel("Mentor");
		lblMentor.setBounds(39, 315, 56, 16);
		add(lblMentor);
		
		label = new JLabel("display surname");
		label.setBounds(126, 229, 102, 16);
		add(label);
		
		label_1 = new JLabel("display surname");
		label_1.setBounds(126, 272, 102, 16);
		add(label_1);
		
		rdbtnYes = new JRadioButton("Yes");
		rdbtnYes.setBounds(125, 311, 56, 25);
		add(rdbtnYes);
		
		rdbtnNo = new JRadioButton("No");
		rdbtnNo.setBounds(194, 311, 56, 25);
		add(rdbtnNo);
		
		panel = new JPanel();
		panel.setBounds(0, 344, 882, 315);
		add(panel);
		
		panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 882, 346);
		add(panel_1);
	}
}
