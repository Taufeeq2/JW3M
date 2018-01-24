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
	private JLabel lblHobby;
	private JTextField hobbyField;
	private JTextArea textArea_1;
	private JScrollPane scrollPane_1;
	private JTextArea textArea_2;
	private JTextArea hobbyArea;
	private JLabel lblHobbyList;
	private JButton btnAddHobby;
	
	
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
		
		panel = new JPanel();
		panel.setBounds(0, 447, 882, 385);
		add(panel);
		panel.setLayout(null);
		
		lblMySkills = new JLabel("My Skills");
		lblMySkills.setBounds(354, 57, 94, 27);
		lblMySkills.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 22));
		panel.add(lblMySkills);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(46, 97, 796, 230);
		panel.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		btnShowSkills = new JButton("Show Skills");
		btnShowSkills.setBounds(370, 347, 127, 25);
		btnShowSkills.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel.add(btnShowSkills);
		btnShowSkills.addActionListener(this); 
		
		panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 882, 447);
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
				
				lblHobby = new JLabel("Hobby");
				lblHobby.setBounds(38, 371, 72, 25);
				lblHobby.setFont(new Font("Tahoma", Font.BOLD, 16));
				panel_1.add(lblHobby);
				
				hobbyField = new JTextField();
				hobbyField.setBounds(143, 373, 161, 22);
				panel_1.add(hobbyField);
				hobbyField.setColumns(10);
				
				textArea_1 = new JTextArea();
				textArea_1.setBounds(783, 87, -100, 286);
				panel_1.add(textArea_1);
				
				scrollPane_1 = new JScrollPane();
				scrollPane_1.setBounds(629, 386, 167, -295);
				panel_1.add(scrollPane_1);
				
				textArea_2 = new JTextArea();
				textArea_2.setBounds(763, 89, -177, 254);
				panel_1.add(textArea_2);
				
				hobbyArea = new JTextArea();
				hobbyArea.setBounds(616, 142, 213, 254);
				panel_1.add(hobbyArea);
				
				lblHobbyList = new JLabel("Hobby List");
				lblHobbyList.setFont(new Font("Tahoma", Font.BOLD, 16));
				lblHobbyList.setBounds(667, 113, 101, 16);
				panel_1.add(lblHobbyList);
				
				btnAddHobby = new JButton("Add Hobby");
				btnAddHobby.setFont(new Font("Tahoma", Font.BOLD, 16));
				btnAddHobby.setBounds(317, 372, 121, 25);
				panel_1.add(btnAddHobby);
				btnAddHobby.addActionListener(this);
				
			if(baseFrame.authenticatedUser.isMentor())
			{
				rdbtnYes.setSelected(true);
			}
			else
			{
				rdbtnNo.setSelected(true);
			}
			
			Vector<UserHobby> dobby = new Vector<UserHobby>();
			baseFrame.getNetHobbyList();
			
			dobby = (baseFrame.getNetUserHobby(baseFrame.authenticatedUser));
			
			for(int i = 0; i < dobby.size(); i++ )
			{
				int hobID = (dobby.get(i).getHobbyID());
				
				for(int j = 0; j < baseFrame.data_hobbyList.size(); j++)
				{
					if (baseFrame.data_hobbyList.get(j).getHobbyID() == hobID)
					{
						hobbyArea.setEditable(false);
						hobbyArea.setText(baseFrame.data_hobbyList.get(j).getHobbyName());
					}
				}

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
		
		if(source == btnAddHobby)
		{
		
		}
		
	}
}
