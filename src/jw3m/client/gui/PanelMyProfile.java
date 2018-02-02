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
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import jw3m.beans.Hobby;
import jw3m.beans.User;
import jw3m.beans.UserHobby;
import jw3m.dao.DAO;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.GridLayout;
import java.awt.FlowLayout;

public class PanelMyProfile extends JPanel implements ActionListener, ListSelectionListener
{
	final static Logger logger = Logger.getLogger(PanelMyProfile.class);
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
	private Font primaryFont, secondaryFont;
	private JLabel lblImgLabel;
	
	
	public PanelMyProfile(SkillsClient frame) {
		baseFrame = frame;
		primaryFont = baseFrame.getPrimaryFont();
        secondaryFont = baseFrame.getSecondaryFont();
        PropertyConfigurator.configure("log4j.properties");
        
        
		setBackground(UIManager.getColor("Button.background"));
		setForeground(Color.LIGHT_GRAY);
		
		nPanel = new JPanel();
		cPanel = new JPanel();
		
		lblImgLabel = new JLabel(new ImageIcon("resources/MyProfile_Full.jpg"));
		nPanel.add(lblImgLabel);
		
		setLayout(new BorderLayout(0, 0));
		cPanel.setLayout(null);
		
		label_1 = new JLabel("Email");
		label_1.setBounds(12, 197, 45, 20);
		label_1.setFont(primaryFont);
		cPanel.add(label_1);
		
		label_3 = new JLabel("Mobile");
		label_3.setBounds(12, 248, 54, 20);
		label_3.setFont(primaryFont);
		cPanel.add(label_3);
		
		label_7 = new JLabel("Name");
		label_7.setBounds(12, 33, 47, 20);
		label_7.setFont(primaryFont);
		cPanel.add(label_7);
		
		name = new JTextField();
		name.setBounds(122, 30, 298, 26);
		name.setFont(primaryFont);
		cPanel.add(name);
		name.setColumns(10);
		
		surname = new JTextField();
		surname.setBounds(122, 84, 298, 26);
		surname.setFont(primaryFont);
		cPanel.add(surname);
		surname.setColumns(10);
		
		email = new JTextField();
		email.setBounds(122, 194, 298, 26);
		email.setFont(primaryFont);
		cPanel.add(email);
		email.setColumns(10);
		
		mobile = new JTextField();
		mobile.setBounds(122, 245, 298, 26);
		mobile.setFont(primaryFont);
		cPanel.add(mobile);
		mobile.setColumns(10);
		
		btnUpdateProfile = new JButton("Update Profile");
		btnUpdateProfile.setBounds(170, 354, 167, 25);
		btnUpdateProfile.setFont(primaryFont);
		cPanel.add(btnUpdateProfile);
		btnUpdateProfile.addActionListener(this);
		
		alias = new JTextField();
		alias.setBounds(122, 137, 298, 26);
		alias.setFont(primaryFont);
		alias.setColumns(10);
		cPanel.add(alias);
		
		lblAlias = new JLabel("Alias");
		lblAlias.setBounds(12, 140, 40, 20);
		lblAlias.setFont(primaryFont);
		cPanel.add(lblAlias);
		
		lblMentor = new JLabel("Mentor");
		lblMentor.setBounds(12, 302, 58, 20);
		lblMentor.setFont(primaryFont);
		cPanel.add(lblMentor);
		
		rdbtnYes = new JRadioButton("Yes");
		rdbtnYes.setBounds(122, 300, 53, 25);
		rdbtnYes.setFont(primaryFont);
		buttonGroup.add(rdbtnYes);
		cPanel.add(rdbtnYes);
		
		rdbtnNo = new JRadioButton("No");
		rdbtnNo.setBounds(193, 300, 49, 25);
		rdbtnNo.setFont(primaryFont);
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
		
		lblMyHobbies = new JLabel("Hobbies and Interests");
		lblMyHobbies.setBounds(711, 30, 179, 20);
		lblMyHobbies.setFont(primaryFont);
		cPanel.add(lblMyHobbies);
		
		label = new JLabel("Hobby");
		label.setBounds(570, 356, 51, 20);
		label.setFont(primaryFont);
		cPanel.add(label);
		
		btnAddHobby = new JButton("Add Hobby");
		btnAddHobby.setBounds(830, 352, 121, 29);
		btnAddHobby.setFont(primaryFont);
		cPanel.add(btnAddHobby);
		btnAddHobby.addActionListener(this);
		
		
		hobbyList = baseFrame.data_hobbyList;
		
		comboBox = new JComboBox(hobbyList);
		comboBox.setBounds(651, 353, 167, 26);
		
		comboBox.setFont(primaryFont);
		cPanel.add(comboBox);
		comboBox.addActionListener(this);
		comboBox.setEditable(true);
		
		label_4 = new JLabel("Surname");
		label_4.setBounds(12, 87, 72, 20);
		label_4.setFont(primaryFont);
		cPanel.add(label_4);
		
		btnRemove = new JButton("Remove Hobby");
		btnRemove.setBounds(711, 300, 153, 25);
		btnRemove.setFont(primaryFont);
		cPanel.add(btnRemove);
		btnRemove.addActionListener(this);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(651, 56, 257, 225);
		cPanel.add(scrollPane);
		
		list = new JList(dobby1);
		scrollPane.setViewportView(list);
		list.setFont(primaryFont);
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
		
		btnNewButton = new JButton("Create new Hobby/Interest");
		btnNewButton.setBounds(651, 415, 257, 29);
		btnNewButton.setFont(primaryFont);
		cPanel.add(btnNewButton);
		btnNewButton.addActionListener(this);
		
		this.add(nPanel, BorderLayout.NORTH);
		this.add(cPanel, BorderLayout.CENTER);
		
		panel_1 = new JPanel();
		cPanel.add(panel_1);
		panel_1.setBounds(553, 457, 462, 225);
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
			lblAddNewHobby_1.setFont(primaryFont);
			lblAddNewHobby_1.setBounds(28, 31, 262, 26);
			panel_1.add(lblAddNewHobby_1);
			
			newField = new JTextField();
			newField.setFont(primaryFont);
			newField.setBounds(245, 29, 200, 30);
			panel_1.add(newField);
			newField.setColumns(10);
			
			btnAdd_1 = new JButton("Add");
			btnAdd_1.setFont(primaryFont);
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
