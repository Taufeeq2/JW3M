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
	private JLabel lblAlias;
	private JLabel lblDisplayalias;
	
	
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
		
		lblSurname = new JLabel("Surname");
		lblSurname.setBounds(39, 188, 56, 16);
		add(lblSurname);
		
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
		panel.setBounds(0, 388, 882, 361);
		add(panel);
		panel.setLayout(null);
		
		lblMySkills = new JLabel("My Skills");
		lblMySkills.setBounds(356, 34, 94, 27);
		lblMySkills.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 22));
		panel.add(lblMySkills);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(49, 86, 796, 230);
		panel.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		btnShowSkills = new JButton("Show Skills");
		btnShowSkills.setBounds(371, 329, 127, 25);
		panel.add(btnShowSkills);
		btnShowSkills.addActionListener(this);
		
		panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 882, 389);
		add(panel_1);
		panel_1.setLayout(null);
		
		lblName = new JLabel("Name");
		lblName.setBounds(38, 124, 56, 16);
		panel_1.add(lblName);
		
		lblDisplayName = new JLabel("display name");
		lblDisplayName.setBounds(126, 124, 146, 16);
		panel_1.add(lblDisplayName);
		lblDisplayName.setText(baseFrame.authenticatedUser.getFirstName());
		
		lblAlias = new JLabel("Alias");
		lblAlias.setBounds(38, 160, 56, 16);
		panel_1.add(lblAlias);
		
		lblDisplayalias = new JLabel("displayAlias");
		lblDisplayalias.setBounds(126, 160, 146, 16);
		panel_1.add(lblDisplayalias);
		
//		baseFrame.authenticatedUser.getFirstName();
//		baseFrame.authenticatedUser.getSurname();

			
			lblDisplayUserid.setText(baseFrame.authenticatedUser.getUserName());
			lblDisplaySurname.setText(baseFrame.authenticatedUser.getSurname());
			lblDisplayEmail.setText(baseFrame.authenticatedUser.getEmailAddress());
			lblDisplayMobile.setText("0" + baseFrame.authenticatedUser.getMobile());
			lblDisplayalias.setText(baseFrame.authenticatedUser.getAlias());
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
		
		if(source == btnShowSkills)
		{
			textArea.setText("");
			for (int i = 0; i < baseFrame.data_userSkills.size(); i++)
			{
				skill = baseFrame.data_userSkills.get(i).getSkillID();
				
				for(int j = 0; j < baseFrame.data_skillList.size(); j++)
				{
					if(skill == baseFrame.data_skillList.get(j).getSkillID())
					{
						skillName = baseFrame.data_skillList.get(j).getSkillName();
						skillDesc = baseFrame.data_skillList.get(j).getSkillDescription();
					}

				}
				
				for(int k = 0; k < baseFrame.data_userRatings.size(); k++)
				{
					
					
					if((baseFrame.authenticatedUser.getUserName().equals(baseFrame.data_userRatings.get(k).getUserID())) 
							&& (skill == baseFrame.data_userRatings.get(k).getSkillID()) && 
							(baseFrame.authenticatedUser.getUserName().equals(baseFrame.data_userRatings.get(k).getRaterID())))
					{
						rating = baseFrame.data_userRatings.get(k).getLevel();
					}
				}
				textArea.append(skill + "\t" + skillName + "\t" + skillDesc + "\t" + rating + "\n" );
	
			}
			
			
			
			
		}
		
	}
}
