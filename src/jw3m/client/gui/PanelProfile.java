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

import jw3m.beans.User;
import jw3m.beans.UserSkill;
import jw3m.dao.DAO;

import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;

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
	private JPanel panel;
	private JPanel panel_1;
	private JLabel lblMySkills;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JButton btnShowSkills;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private User user = new User();
	
	
	public PanelProfile(SkillsClient frame) {

		baseFrame = frame;
		
		setLayout(null);
		
		lblMyProfile = new JLabel("My Profile");
		lblMyProfile.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 22));
		lblMyProfile.setBounds(303, 13, 116, 38);
		add(lblMyProfile);
		
		lblUserId = new JLabel("User ID");
		lblUserId.setBounds(39, 89, 56, 16);
		add(lblUserId);
		
		lblDisplayUserid = new JLabel("display userid");
		lblDisplayUserid.setBounds(126, 89, 146, 16);
		add(lblDisplayUserid);
		
		lblName = new JLabel("Name");
		lblName.setBounds(39, 137, 56, 16);
		add(lblName);
		
		lblSurname = new JLabel("Surname");
		lblSurname.setBounds(39, 188, 56, 16);
		add(lblSurname);
		
		lblDisplayName = new JLabel("display name");
		lblDisplayName.setBounds(126, 137, 146, 16);
		add(lblDisplayName);
		
		lblDisplaySurname = new JLabel("display surname");
		lblDisplaySurname.setBounds(126, 188, 146, 16);
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
		
		lblDisplayEmail = new JLabel("display email");
		lblDisplayEmail.setBounds(126, 229, 278, 16);
		add(lblDisplayEmail);
		
		lblDisplayMobile = new JLabel("display mobile");
		lblDisplayMobile.setBounds(126, 272, 146, 16);
		add(lblDisplayMobile);
		
		rdbtnYes = new JRadioButton("Yes");
		buttonGroup.add(rdbtnYes);
		rdbtnYes.setBounds(125, 311, 56, 25);
		add(rdbtnYes);
		
		rdbtnNo = new JRadioButton("No");
		buttonGroup.add(rdbtnNo);
		rdbtnNo.setBounds(194, 311, 56, 25);
		add(rdbtnNo);
		
		panel = new JPanel();
		panel.setBounds(0, 344, 882, 387);
		add(panel);
		panel.setLayout(null);
		
		lblMySkills = new JLabel("My Skills");
		lblMySkills.setBounds(309, 13, 94, 27);
		lblMySkills.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 22));
		panel.add(lblMySkills);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(48, 53, 796, 230);
		panel.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		btnShowSkills = new JButton("Show Skills");
		btnShowSkills.setBounds(371, 306, 127, 25);
		panel.add(btnShowSkills);
		btnShowSkills.addActionListener(this);
		
		panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 882, 346);
		add(panel_1);
		
//		baseFrame.authenticatedUser.getFirstName();
//		baseFrame.authenticatedUser.getSurname();

			
			lblDisplayUserid.setText(baseFrame.authenticatedUser.getUserName());
			lblDisplayName.setText(baseFrame.authenticatedUser.getFirstName());
			lblDisplaySurname.setText(baseFrame.authenticatedUser.getSurname());
			lblDisplayEmail.setText(baseFrame.authenticatedUser.getEmailAddress());
			lblDisplayMobile.setText(""+baseFrame.authenticatedUser.getMobile());
//			buttonGroup.setSelected(m, b);
						


			
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		DAO dao;
		Vector<UserSkill> skillVect = new Vector<UserSkill>();
		
		if(source == btnShowSkills)
		{
			try
			{
				dao = new DAO();
				skillVect = dao.getUserSkills(baseFrame.authenticatedUser);
				String skill = "";
				
				for(int i = 0; i < skillVect.size(); i++ )
				{
					textArea.append(skill.toString());
				}
			} 
			catch (Exception e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			
			
		}
		
	}
}
