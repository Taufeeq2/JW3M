package jw3m.client.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import jw3m.beans.Hobby;
import jw3m.beans.User;
import jw3m.beans.UserHobby;
import jw3m.dao.DAO;

import javax.swing.ButtonGroup;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.GridLayout;
import java.awt.FlowLayout;

public class PanelEdit extends JPanel implements ActionListener, ListSelectionListener
{
	
	private SkillsClient baseFrame;
	private JLabel lblEditProfile;
	private JLabel label_1;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_7;
	private JTextField name;
	private JTextField surname;
	private JTextField email;
	private JTextField mobile;
	private JTextField alias;
	private JLabel lblAlias;
	private JLabel lblMentor;
	private JRadioButton rdbtnYes;
	private JRadioButton rdbtnNo;
	private JButton btnUpdateProfile;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblMyHobbies;
	private JLabel label;
	private JButton btnAddHobby;
	private JComboBox comboBox;
	private JButton btnRemove;
	private JScrollPane scrollPane;
	private JList list;
	Vector<UserHobby> dobby = new Vector<UserHobby>();
	Vector<String> dobby1 = new Vector<String>();
	Vector<Hobby> hobbyList = new Vector<Hobby>();
	User temp = new User();
	String returnedValue;
	private JButton btnNewButton;
	private JPanel panel_1, nPanel, cPanel;
	private JLabel lblAddNewHobby_1;
	private JTextField newField;
	private JButton btnAdd_1;
	private JLabel label_2;
	private JLabel label_5;
	private JLabel label_6;
	private JLabel label_8;
	private JLabel label_9;
	private JLabel label_10;
	private JLabel label_11;
	private JLabel label_12;
	private JLabel label_13;
	private JLabel label_14;
	private JLabel label_15;
	private JLabel label_16;
	private JLabel label_17;
	private JLabel label_18;
	private JLabel label_19;
	private JLabel label_20;
	private JLabel label_21;
	private JLabel label_22;
	private JLabel label_23;
	private JLabel label_24;
	private JLabel label_25;
	private JLabel label_26;
	private JLabel label_27;
	private JLabel label_28;
	private JLabel label_29;
	private JLabel label_30;
	private JLabel label_31;
	private JLabel label_32;
	private JLabel label_33;
	private JLabel label_34;
	private JLabel label_35;
	private JLabel label_36;
	private JLabel label_37;
	private JLabel label_38;
	private JLabel label_39;
	
	public PanelEdit(SkillsClient frame) {
		baseFrame = frame;
		
		setBackground(UIManager.getColor("Button.background"));
		setForeground(Color.LIGHT_GRAY);
		
		nPanel = new JPanel();
		cPanel = new JPanel();
		
		lblEditProfile = new JLabel("My Profile");
		lblEditProfile.setBounds(335, 13, 114, 16);
		lblEditProfile.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 22));
		nPanel.add(lblEditProfile);
		setLayout(new BorderLayout(0, 0));
		cPanel.setLayout(null);
		
		label_1 = new JLabel("Email");
		label_1.setBounds(30, 185, 45, 20);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		cPanel.add(label_1);
		
		label_3 = new JLabel("Mobile");
		label_3.setBounds(32, 242, 54, 20);
		label_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		cPanel.add(label_3);
		
		label_7 = new JLabel("Name");
		label_7.setBounds(28, 30, 47, 20);
		label_7.setFont(new Font("Tahoma", Font.BOLD, 16));
		cPanel.add(label_7);
		
		label_2 = new JLabel("");
		label_2.setBounds(178, 18, 0, 0);
		cPanel.add(label_2);
		
		name = new JTextField();
		name.setBounds(155, 27, 267, 26);
		name.setFont(new Font("Tahoma", Font.BOLD, 16));
		cPanel.add(name);
		name.setColumns(10);
		
		label_5 = new JLabel("");
		label_5.setBounds(344, 18, 0, 0);
		cPanel.add(label_5);
		
		label_6 = new JLabel("");
		label_6.setBounds(349, 18, 0, 0);
		cPanel.add(label_6);
		
		label_8 = new JLabel("");
		label_8.setBounds(354, 18, 0, 0);
		cPanel.add(label_8);
		
		label_9 = new JLabel("");
		label_9.setBounds(359, 18, 0, 0);
		cPanel.add(label_9);
		
		surname = new JTextField();
		surname.setBounds(155, 75, 267, 26);
		surname.setFont(new Font("Tahoma", Font.BOLD, 16));
		cPanel.add(surname);
		surname.setColumns(10);
		
		label_10 = new JLabel("");
		label_10.setBounds(525, 18, 0, 0);
		cPanel.add(label_10);
		
		email = new JTextField();
		email.setBounds(155, 182, 267, 26);
		email.setFont(new Font("Tahoma", Font.BOLD, 16));
		cPanel.add(email);
		email.setColumns(10);
		
		label_11 = new JLabel("");
		label_11.setBounds(691, 18, 0, 0);
		cPanel.add(label_11);
		
		label_12 = new JLabel("");
		label_12.setBounds(696, 18, 0, 0);
		cPanel.add(label_12);
		
		mobile = new JTextField();
		mobile.setBounds(155, 239, 267, 26);
		mobile.setFont(new Font("Tahoma", Font.BOLD, 16));
		cPanel.add(mobile);
		mobile.setColumns(10);
		
		label_13 = new JLabel("");
		label_13.setBounds(862, 18, 0, 0);
		cPanel.add(label_13);
		
		label_14 = new JLabel("");
		label_14.setBounds(867, 18, 0, 0);
		cPanel.add(label_14);
		
		btnUpdateProfile = new JButton("Update Profile");
		btnUpdateProfile.setBounds(155, 354, 129, 25);
		btnUpdateProfile.setFont(new Font("Tahoma", Font.BOLD, 14));
		cPanel.add(btnUpdateProfile);
		btnUpdateProfile.addActionListener(this);
		
		label_15 = new JLabel("");
		label_15.setBounds(1006, 18, 0, 0);
		cPanel.add(label_15);
		
		alias = new JTextField();
		alias.setBounds(155, 124, 267, 26);
		alias.setFont(new Font("Tahoma", Font.BOLD, 16));
		alias.setColumns(10);
		cPanel.add(alias);
		
		label_16 = new JLabel("");
		label_16.setBounds(193, 50, 0, 0);
		cPanel.add(label_16);
		
		label_17 = new JLabel("");
		label_17.setBounds(198, 50, 0, 0);
		cPanel.add(label_17);
		
		lblAlias = new JLabel("Alias");
		lblAlias.setBounds(33, 127, 40, 20);
		lblAlias.setFont(new Font("Tahoma", Font.BOLD, 16));
		cPanel.add(lblAlias);
		
		label_18 = new JLabel("");
		label_18.setBounds(248, 50, 0, 0);
		cPanel.add(label_18);
		
		lblMentor = new JLabel("Mentor");
		lblMentor.setBounds(30, 302, 58, 20);
		lblMentor.setFont(new Font("Tahoma", Font.BOLD, 16));
		cPanel.add(lblMentor);
		
		rdbtnYes = new JRadioButton("Yes");
		rdbtnYes.setBounds(155, 300, 53, 25);
		rdbtnYes.setFont(new Font("Tahoma", Font.BOLD, 14));
		buttonGroup.add(rdbtnYes);
		cPanel.add(rdbtnYes);
		
		label_19 = new JLabel("");
		label_19.setBounds(374, 50, 0, 0);
		cPanel.add(label_19);
		
		rdbtnNo = new JRadioButton("No");
		rdbtnNo.setBounds(239, 300, 49, 25);
		rdbtnNo.setFont(new Font("Tahoma", Font.BOLD, 14));
		buttonGroup.add(rdbtnNo);
		cPanel.add(rdbtnNo);
		
		// Mo seriously why not add the current values in edit???? Love Warren
		name.setText(baseFrame.authenticatedUser.getFirstName());
		surname.setText(baseFrame.authenticatedUser.getSurname());
		email.setText(baseFrame.authenticatedUser.getEmailAddress());
	 	mobile.setText("0" + baseFrame.authenticatedUser.getMobile());    // leading ZERO does not work here
		alias.setText(baseFrame.authenticatedUser.getAlias());
		
		
		if(baseFrame.authenticatedUser.isMentor())
		{
			rdbtnYes.setSelected(true);
		}
		else
		{
			rdbtnNo.setSelected(true);
		}
		
		label_20 = new JLabel("");
		label_20.setBounds(433, 50, 0, 0);
		cPanel.add(label_20);
		
		lblMyHobbies = new JLabel("Hobbies and Interests");
		lblMyHobbies.setBounds(711, 30, 179, 20);
		lblMyHobbies.setFont(new Font("Tahoma", Font.BOLD, 16));
		cPanel.add(lblMyHobbies);
		
		label_21 = new JLabel("");
		label_21.setBounds(622, 50, 0, 0);
		cPanel.add(label_21);
		
		label = new JLabel("Hobby");
		label.setBounds(571, 369, 51, 20);
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		cPanel.add(label);
		
		btnAddHobby = new JButton("Add Hobby");
		btnAddHobby.setBounds(865, 365, 121, 29);
		btnAddHobby.setFont(new Font("Tahoma", Font.BOLD, 16));
		cPanel.add(btnAddHobby);
		btnAddHobby.addActionListener(this);
		
		
		hobbyList = baseFrame.data_hobbyList;
		
		label_22 = new JLabel("");
		label_22.setBounds(809, 50, 0, 0);
		cPanel.add(label_22);
		
		comboBox = new JComboBox(hobbyList);
		comboBox.setBounds(675, 366, 167, 26);
		comboBox.setToolTipText("Select Hobby and click Add Hobby");
		
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 16));
		cPanel.add(comboBox);
		comboBox.addActionListener(this);
		comboBox.setEditable(true);
		
		label_23 = new JLabel("");
		label_23.setBounds(986, 50, 0, 0);
		cPanel.add(label_23);
		
		label_24 = new JLabel("");
		label_24.setBounds(991, 50, 0, 0);
		cPanel.add(label_24);
		
		label_4 = new JLabel("Surname");
		label_4.setBounds(28, 78, 72, 20);
		label_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		cPanel.add(label_4);
		
		label_25 = new JLabel("");
		label_25.setBounds(177, 135, 0, 0);
		cPanel.add(label_25);
		
		label_26 = new JLabel("");
		label_26.setBounds(182, 135, 0, 0);
		cPanel.add(label_26);
		
		label_27 = new JLabel("");
		label_27.setBounds(187, 135, 0, 0);
		cPanel.add(label_27);
		
		btnRemove = new JButton("Remove Hobby");
		btnRemove.setBounds(739, 313, 139, 25);
		btnRemove.setFont(new Font("Tahoma", Font.BOLD, 14));
		cPanel.add(btnRemove);
		btnRemove.addActionListener(this);
		
		label_28 = new JLabel("");
		label_28.setBounds(336, 135, 0, 0);
		cPanel.add(label_28);
		
		label_29 = new JLabel("");
		label_29.setBounds(341, 135, 0, 0);
		cPanel.add(label_29);
		
		label_30 = new JLabel("");
		label_30.setBounds(346, 135, 0, 0);
		cPanel.add(label_30);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(691, 75, 258, 225);
		cPanel.add(scrollPane);
		
		list = new JList(dobby1);
		scrollPane.setRowHeaderView(list);
		list.addListSelectionListener(this);
		
		
		
		
		// sort out mentor
		
		
//		
//		for(int i = 0; i < hobbyList.size(); i++)
//		{
//			dobby1.addElement(hobbyList.get(i).to);
//		}
		
		
		baseFrame.getNetHobbyList();
		
		dobby = (baseFrame.getNetUserHobby(baseFrame.authenticatedUser));
		
		for(int i = 0; i < dobby.size(); i++ )
		{
			int hobID = (dobby.get(i).getHobbyID());
			
			for(int j = 0; j < baseFrame.data_hobbyList.size(); j++)
			{
				if (baseFrame.data_hobbyList.get(j).getHobbyID() == hobID)
				{
//					hobbyArea.append(baseFrame.data_hobbyList.get(j).getHobbyName() + "\n");
					dobby1.add(baseFrame.data_hobbyList.get(j).getHobbyName());
				}
			}

		}
		
		label_31 = new JLabel("");
		label_31.setBounds(614, 135, 0, 0);
		cPanel.add(label_31);
		
		label_32 = new JLabel("");
		label_32.setBounds(619, 135, 0, 0);
		cPanel.add(label_32);
		
		label_33 = new JLabel("");
		label_33.setBounds(624, 135, 0, 0);
		cPanel.add(label_33);
		
		label_34 = new JLabel("");
		label_34.setBounds(629, 135, 0, 0);
		cPanel.add(label_34);
		
		btnNewButton = new JButton("Create new Hobby/Interest");
		btnNewButton.setBounds(651, 417, 257, 29);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		cPanel.add(btnNewButton);
		btnNewButton.addActionListener(this);
		
		label_35 = new JLabel("");
		label_35.setBounds(896, 135, 0, 0);
		cPanel.add(label_35);
		
		label_36 = new JLabel("");
		label_36.setBounds(901, 135, 0, 0);
		cPanel.add(label_36);
		
		label_37 = new JLabel("");
		label_37.setBounds(906, 135, 0, 0);
		cPanel.add(label_37);
		
		this.add(nPanel, BorderLayout.NORTH);
		this.add(cPanel, BorderLayout.CENTER);
		
		label_38 = new JLabel("");
		label_38.setBounds(917, 135, 0, 0);
		cPanel.add(label_38);
		
		label_39 = new JLabel("");
		label_39.setBounds(922, 135, 0, 0);
		cPanel.add(label_39);
		
		panel_1 = new JPanel();
		cPanel.add(panel_1);
		panel_1.setBounds(553, 472, 443, 225);
		panel_1.setLayout(null);

		
		
		
	

		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		boolean mentor;
		
		if(source == btnUpdateProfile)
		{
			baseFrame.authenticatedUser.setFirstName(name.getText());
			baseFrame.authenticatedUser.setSurname(surname.getText());
			baseFrame.authenticatedUser.setAlias(alias.getText());
			baseFrame.authenticatedUser.setEmailAddress(email.getText());
			baseFrame.authenticatedUser.setMobile(Integer.parseInt(mobile.getText()));
			
			if(rdbtnYes.isSelected())
			{
				mentor = true;
			}
			else
			{
				mentor = false;
			}

			baseFrame.authenticatedUser.setMentor(mentor);
			
			// Need an edit user in the protocols

			baseFrame.authenticatedUser = baseFrame.editNetUser(baseFrame.authenticatedUser);
			
//			baseFrame.authenticatedUser = baseFrame.editUser(baseFrame.authenticatedUser);
		

			

			JOptionPane.showMessageDialog(this, "Well done on updating your profile"); 
		}
		
		if(source == btnAddHobby)
		{
			Vector<Hobby> hobby = new Vector<Hobby>();
            Vector<Hobby> newHobby = new Vector<Hobby>();

            Hobby tempHobby = new Hobby();
            hobby = baseFrame.data_hobbyList;                     
            String checkHobby = (comboBox.getSelectedItem().toString());
            
            for (int j = 0; j < hobby.size(); j++)
            {
                  if(hobby.get(j).getHobbyName().equals(checkHobby))
                  {
                         tempHobby.setHobbyID(hobby.get(j).getHobbyID());
                         tempHobby.setHobbyName(hobby.get(j).getHobbyName());
                  }
            }
            
            newHobby.add(tempHobby);
            boolean alreadyAdded = true;
            
            for (int i = 0; i < dobby1.size(); i++)
            {
                  System.out.println("Sting Hobby: " + dobby1.get(i) + "IF Check = " + dobby1.get(i).equals(checkHobby));
                  if (dobby1.get(i).equals(checkHobby))
                  {
                         alreadyAdded = false;
                         break;
                  }
                  else
                  {
                         alreadyAdded = true;
                  }
            }
            if (alreadyAdded)
            {
                  list.removeListSelectionListener(this);
                  dobby1.add(comboBox.getSelectedItem().toString());
                  list.setListData(dobby1);
                  list.addListSelectionListener(this);
                  baseFrame.setNetUserHobby(baseFrame.authenticatedUser, newHobby);
                  JOptionPane.showMessageDialog(this, "Hobby added");                       
            }
            else
            {
                  JOptionPane.showMessageDialog(this, "You already have this hobby added");
            }
		}
		
		if(source == btnRemove)
		{
			if(list.getSelectedValue() == null)
			{
				return;
			}
//			DAO dao;
			try
			{
//				dao = new DAO();
				UserHobby uHob = new UserHobby();
				list.removeListSelectionListener(this);
				dobby1.removeElement(list.getSelectedValue());
				list.setListData(dobby1);
				list.addListSelectionListener(this);
				
				
				
				uHob.setUserID(baseFrame.authenticatedUser.getUserName());
				for (int i = 0; i < hobbyList.size(); i++)
				{

					
					if((returnedValue).equals(hobbyList.get(i).getHobbyName()))
					{
						uHob.setHobbyID(hobbyList.get(i).getHobbyID());
					}
				}
				
//				dao.removeUserHobby(uHob);
				baseFrame.setNetRemoveUserHobby(uHob);
			} 
			catch (Exception e1)
			{
				e1.printStackTrace();
			}
			

			
			
		}
		
		if(source == btnNewButton)
		{
			lblAddNewHobby_1 = new JLabel("Add new Hobby or Interest");
			lblAddNewHobby_1.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblAddNewHobby_1.setBounds(28, 31, 262, 16);
			panel_1.add(lblAddNewHobby_1);
			
			newField = new JTextField();
			newField.setFont(new Font("Tahoma", Font.BOLD, 16));
			newField.setBounds(321, 29, 226, 22);
			panel_1.add(newField);
			newField.setColumns(10);
			
			btnAdd_1 = new JButton("Add");
			btnAdd_1.setFont(new Font("Tahoma", Font.BOLD, 16));
			btnAdd_1.setBounds(240, 89, 97, 25);
			panel_1.add(btnAdd_1);
			btnAdd_1.addActionListener(this);
			
			this.panel_1.validate();
			this.panel_1.repaint();
		}
		
		if(source == btnAdd_1)
		{
			Vector<Hobby> hobby = new Vector<Hobby>();
            Vector<Hobby> newHobby = new Vector<Hobby>();

            Hobby tempHobby = new Hobby();
            tempHobby.setHobbyID(0);
            tempHobby.setHobbyName(newField.getText());
            baseFrame.setNetAddHobbyList(tempHobby);
            hobby = baseFrame.data_hobbyList;                     
            String checkHobby = (newField.getText().toString());
            
            for (int j = 0; j < hobby.size(); j++)
            {
                  if(hobby.get(j).getHobbyName().equals(checkHobby))
                  {
                         tempHobby.setHobbyID(hobby.get(j).getHobbyID());
                         tempHobby.setHobbyName(hobby.get(j).getHobbyName());
                  }
            }
            
            newHobby.add(tempHobby);
            boolean alreadyAdded = true;
            
            for (int i = 0; i < dobby1.size(); i++)
            {
                  System.out.println("Sting Hobby: " + dobby1.get(i) + "IF Check = " + dobby1.get(i).equals(checkHobby));
                  if (dobby1.get(i).equals(checkHobby))
                  {
                         alreadyAdded = false;
                         break;
                  }
                  else
                  {
                         alreadyAdded = true;
                  }
            }
            if (alreadyAdded)
            {
                  list.removeListSelectionListener(this);
                  dobby1.add(newField.getText().toString());
                  list.setListData(dobby1);
                  list.addListSelectionListener(this);
                  baseFrame.setNetUserHobby(baseFrame.authenticatedUser, newHobby);
                  JOptionPane.showMessageDialog(this, "Hobby added");                       
            }
            else
            {
                  JOptionPane.showMessageDialog(this, "You already have this hobby added");
            }
		}
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e)
	{
		if(e.getValueIsAdjusting())
		{
			returnedValue = list.getSelectedValue().toString();
			System.out.println("Hobby Name " + returnedValue);
			return;
		}
		
		
	}
}
