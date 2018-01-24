package jw3m.client.gui;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JRadioButton;

import javax.swing.JButton;
import javax.swing.JTextArea;

import jw3m.beans.Hobby;
import jw3m.beans.Skill;
import jw3m.beans.User;
import jw3m.beans.UserHobby;
import jw3m.beans.UserSkill;
import jw3m.dao.DAO;

import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JComboBox;

public class PanelProfile extends JPanel implements ActionListener
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
	private JLabel lblDisplayEmail;
	private JLabel lblDisplayMobile;
	private JRadioButton rdbtnYes;
	private JRadioButton rdbtnNo;
	private JPanel panel_1;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private User user = new User();
	private JLabel lblAlias;
	private JLabel lblDisplayalias;
	private JTextArea textArea_1;
	private JScrollPane scrollPane_1;
	private JTextArea textArea_2;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JTable table;
	private JLabel lblMySkills;
	private JButton btnRemoveSelectedSkill;
	private JComboBox comboBoxSkills;
	private JButton btnAddSelectedSkill;
	private Vector<String> skillNames = new Vector<String>();
	
	
	public PanelProfile(SkillsClient frame) {

		baseFrame = frame;
		
		setLayout(null);
		
		lblMyProfile = new JLabel("My Profile");
		lblMyProfile.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 22));
		lblMyProfile.setBounds(303, 13, 116, 38);
		add(lblMyProfile);
		
		lblUserId = new JLabel("User ID");
		lblUserId.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUserId.setBounds(39, 89, 68, 16);
		add(lblUserId);
		
		panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 882, 387);
		add(panel_1);
		panel_1.setLayout(null);
		
		lblName = new JLabel("Name");
		lblName.setBounds(38, 124, 56, 16);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_1.add(lblName);
		
		lblDisplayName = new JLabel("display name");
		lblDisplayName.setBounds(143, 125, 146, 16);
		lblDisplayName.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_1.add(lblDisplayName);
		lblDisplayName.setText(baseFrame.authenticatedUser.getFirstName());
		
		lblAlias = new JLabel("Alias");
		lblAlias.setBounds(38, 160, 56, 16);
		lblAlias.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_1.add(lblAlias);
		
		lblDisplayalias = new JLabel("displayAlias");
		lblDisplayalias.setBounds(143, 161, 146, 16);
		lblDisplayalias.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_1.add(lblDisplayalias);
			lblDisplayalias.setText(baseFrame.authenticatedUser.getAlias());
			
			lblDisplayUserid = new JLabel("display userid");
			lblDisplayUserid.setBounds(143, 89, 146, 16);
			lblDisplayUserid.setFont(new Font("Tahoma", Font.BOLD, 16));
			panel_1.add(lblDisplayUserid);
			
//		baseFrame.authenticatedUser.getFirstName();
//		baseFrame.authenticatedUser.getSurname();

				
				lblDisplayUserid.setText(baseFrame.authenticatedUser.getUserName());
				
				lblMentor = new JLabel("Mentor");
				lblMentor.setBounds(38, 327, 72, 16);
				panel_1.add(lblMentor);
				lblMentor.setFont(new Font("Tahoma", Font.BOLD, 16));
				
				lblMobile = new JLabel("Mobile");
				lblMobile.setBounds(38, 283, 56, 16);
				panel_1.add(lblMobile);
				lblMobile.setFont(new Font("Tahoma", Font.BOLD, 16));
				
				lblEmail = new JLabel("Email");
				lblEmail.setBounds(38, 241, 56, 16);
				panel_1.add(lblEmail);
				lblEmail.setFont(new Font("Tahoma", Font.BOLD, 16));
				
				lblSurname = new JLabel("Surname");
				lblSurname.setBounds(38, 201, 72, 16);
				panel_1.add(lblSurname);
				lblSurname.setFont(new Font("Tahoma", Font.BOLD, 16));
				
				lblDisplaySurname = new JLabel("display surname");
				lblDisplaySurname.setBounds(143, 202, 146, 16);
				lblDisplaySurname.setFont(new Font("Tahoma", Font.BOLD, 16));
				panel_1.add(lblDisplaySurname);
				lblDisplaySurname.setText(baseFrame.authenticatedUser.getSurname());
				
				lblDisplayEmail = new JLabel("display email");
				lblDisplayEmail.setBounds(143, 242, 329, 16);
				lblDisplayEmail.setFont(new Font("Tahoma", Font.BOLD, 16));
				panel_1.add(lblDisplayEmail);
				lblDisplayEmail.setText(baseFrame.authenticatedUser.getEmailAddress());
				
				lblDisplayMobile = new JLabel("display mobile");
				lblDisplayMobile.setBounds(143, 284, 146, 16);
				lblDisplayMobile.setFont(new Font("Tahoma", Font.BOLD, 16));
				panel_1.add(lblDisplayMobile);
				lblDisplayMobile.setText("0" + baseFrame.authenticatedUser.getMobile());
				
				rdbtnYes = new JRadioButton("Yes");
				rdbtnYes.setBounds(143, 324, 56, 25);
				rdbtnYes.setFont(new Font("Tahoma", Font.BOLD, 14));
				panel_1.add(rdbtnYes);
				buttonGroup.add(rdbtnYes);
				
				rdbtnNo = new JRadioButton("No");
				rdbtnNo.setBounds(208, 324, 56, 25);
				rdbtnNo.setFont(new Font("Tahoma", Font.BOLD, 14));
				panel_1.add(rdbtnNo);
				buttonGroup.add(rdbtnNo);
				
				textArea_1 = new JTextArea();
				textArea_1.setBounds(783, 87, -100, 286);
				panel_1.add(textArea_1);
				
				scrollPane_1 = new JScrollPane();
				scrollPane_1.setBounds(629, 386, 167, -295);
				panel_1.add(scrollPane_1);
				
				textArea_2 = new JTextArea();
				textArea_2.setBounds(763, 89, -177, 254);
				panel_1.add(textArea_2);
				
				panel = new JPanel();
				panel.setBounds(0, 400, 1031, 267);
				add(panel);
				panel.setLayout(null);
				
				scrollPane = new JScrollPane();
				scrollPane.setBounds(1028, 13, -1029, 241);
				panel.add(scrollPane);
				
				table = new JTable();
				table.setBounds(1012, 151, -980, -108);
				panel.add(table);
				
				lblMySkills = new JLabel("My Skills");
				lblMySkills.setFont(new Font("Calibri", Font.BOLD, 22));
				lblMySkills.setBounds(468, 0, 88, 30);
				panel.add(lblMySkills);
				
				btnRemoveSelectedSkill = new JButton("Remove selected skill from above table");
				btnRemoveSelectedSkill.setBounds(22, 158, 308, 25);
				panel.add(btnRemoveSelectedSkill);
				
				
				baseFrame.getNetSkillList();
								
				comboBoxSkills = new JComboBox();
				comboBoxSkills.setBounds(22, 207, 308, 22);
				panel.add(comboBoxSkills);
				
				btnAddSelectedSkill = new JButton("Add selected skill from dropdown");
				btnAddSelectedSkill.setBounds(22, 242, 308, 25);
				panel.add(btnAddSelectedSkill);
				
			if(baseFrame.authenticatedUser.isMentor())
			{
				rdbtnYes.setSelected(true);
			}
			else
			{
				rdbtnNo.setSelected(true);
			}
			

			
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		int skill, rating = 0;
		String skillName = null, skillDesc = null;
		
		
		
	}
}
