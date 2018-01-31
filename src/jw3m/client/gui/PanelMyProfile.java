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
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import jw3m.beans.Hobby;
import jw3m.beans.User;
import jw3m.beans.UserHobby;
import jw3m.dao.DAO;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
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
	private JScrollPane scrollPane;
	private JList list;
	Vector<UserHobby> dobby = new Vector<UserHobby>();
	Vector<String> dobby1 = new Vector<String>();
	Vector<Hobby> hobbyList = new Vector<Hobby>();
	User temp = new User();
	String returnedValue;
	private JButton btnNewButton;
	private JPanel panel_1, panelNorth;
	private JLabel lblAddNewHobby_1;
	private JTextField newField;
	private JButton btnAdd_1;


	private JLabel label_50;
	
	JPanel panelWest, panelEast , panelCenter;
	private Font primaryFont, secondaryFont;
	private JScrollPane scrollPane_1;
	
	
	public PanelMyProfile(SkillsClient frame) 
	{
		PropertyConfigurator.configure("log4j.properties");
		baseFrame = frame;
		primaryFont = baseFrame.getPrimaryFont();
        secondaryFont = baseFrame.getSecondaryFont();
        
        
        
		//Create the panels (comment ones you don't need)
		
		JPanel panelNorth = new JPanel();
		JPanel panelSouth = new JPanel();
		panelEast = new JPanel();
//		panelWest = new JPanel();
//		panelCenter = new JPanel();
        
	
        
		panelNorth.setBorder(new LineBorder(Color.red, 2));
		panelSouth.setBorder(new LineBorder(Color.green, 2));
		panelEast.setBorder(new LineBorder(Color.yellow, 2));
//		panelWest.setBorder(new LineBorder(Color.blue, 2));
//		panelCenter.setBorder(new LineBorder(Color.black, 2));
        
		setBackground(UIManager.getColor("Button.background"));
		setForeground(Color.LIGHT_GRAY);
		
		
		//North Panel (all of it)
		
		lblEditProfile = new JLabel("My Profile");
//		lblEditProfile.setBounds(335, 13, 114, 16);
		lblEditProfile.setFont(secondaryFont);
		panelNorth.add(lblEditProfile);
		
		// West Panel (all of it)
		
//		setupWestPanel();
		
		
		// East Panel (all of it)
		
		setupEastPanel();
		
//		panelCenter.setLayout(null);
		
		

//		panelWest.setBounds(0, 0, 300, 300);
//		
//		panelEast.setBounds(0, 0, 300, 300);
		
//		panelCenter.setBounds(0,0 , 1, 1);
		
		
		// Setup layout and add all panels (comment out ones not used)
		setLayout(new BorderLayout(0, 0));
//		this.add(panelCenter, BorderLayout.CENTER);
		this.add(panelNorth, BorderLayout.NORTH);
//		this.add(panelSouth, BorderLayout.SOUTH);
		this.add(panelEast, BorderLayout.EAST);
//		this.add(panelWest, BorderLayout.WEST);
		this.setSize(800,600);
		setLayout(new BorderLayout(0, 0));
		add(panelNorth);
		add(panelEast);
		

		this.validate();
		this.setVisible(true);
		
		
		
	}

	public void setupWestPanel()
	{
	//	panelWest.setLayout(null);
		
		GroupLayout groupLayout = new GroupLayout(this);
		panelWest.setLayout(null);
		
//		panelWest.setLayout(      new GridLayout(7,2, 50, 30)      );
		
		label_7 = new JLabel("Name");
		label_7.setBounds(7, 11, 33, 16);
		label_7.setFont(primaryFont);
		panelWest.add(label_7);
		
		
			name = new JTextField();
			name.setBounds(45, 8, 116, 22);
			name.setFont(primaryFont);
			panelWest.add(name);
			name.setColumns(10);
		
		label_50 = new JLabel("Surname");
		label_50.setBounds(166, 11, 52, 16);
//			label_50.setBounds(28, 30, 47, 20);
		label_50.setFont(primaryFont);
		panelWest.add(label_50);
		
			surname = new JTextField();
			surname.setBounds(223, 8, 116, 22);
	//		surname.setBounds(155, 75, 267, 26);
			surname.setFont(primaryFont);
			panelWest.add(surname);
			surname.setColumns(10);
		
			
		lblAlias = new JLabel("Alias");
		lblAlias.setBounds(344, 11, 27, 16);
//			lblAlias.setBounds(33, 127, 40, 20);
		lblAlias.setFont(primaryFont);
		panelWest.add(lblAlias);
			
			alias = new JTextField();
			alias.setBounds(376, 8, 116, 22);
	//			alias.setBounds(155, 124, 267, 26);
			alias.setFont(primaryFont);
			alias.setColumns(10);
			panelWest.add(alias);
			

			
		
		label_1 = new JLabel("Email");
		label_1.setBounds(497, 11, 31, 16);
//		label_1.setBounds(30, 185, 45, 20);
		label_1.setFont(primaryFont);
		panelWest.add(label_1);
		
			email = new JTextField();
			email.setBounds(533, 8, 116, 22);
	//		email.setBounds(155, 182, 267, 26);
			email.setFont(primaryFont);
			panelWest.add(email);
			email.setColumns(10);
		
		label_3 = new JLabel("Mobile");
		label_3.setBounds(654, 11, 37, 16);
//		label_3.setBounds(32, 242, 54, 20);
		label_3.setFont(primaryFont);
		panelWest.add(label_3);
		
		
			mobile = new JTextField();
			mobile.setBounds(696, 8, 116, 22);
	//		mobile.setBounds(155, 239, 267, 26);
			mobile.setFont(primaryFont);
			panelWest.add(mobile);
			mobile.setColumns(10);
		
			
			lblMentor = new JLabel("Mentor");
			lblMentor.setBounds(817, 11, 40, 16);
//			lblMentor.setBounds(30, 302, 58, 20);
			lblMentor.setFont(primaryFont);
			panelWest.add(lblMentor);
			
				rdbtnYes = new JRadioButton("Yes");
				rdbtnYes.setBounds(862, 7, 49, 25);
	//			rdbtnYes.setBounds(155, 300, 53, 25);
				rdbtnYes.setFont(primaryFont);
				buttonGroup.add(rdbtnYes);
				panelWest.add(rdbtnYes);
			

			
			rdbtnNo = new JRadioButton("No");
			rdbtnNo.setBounds(916, 7, 43, 25);
//			rdbtnNo.setBounds(239, 300, 49, 25);
			rdbtnNo.setFont(primaryFont);
			buttonGroup.add(rdbtnNo);
			panelWest.add(rdbtnNo);

		
		btnUpdateProfile = new JButton("Update Profile");
		btnUpdateProfile.setBounds(964, 7, 113, 25);
//		btnUpdateProfile.setBounds(155, 354, 129, 25);
		btnUpdateProfile.setFont(primaryFont);
		panelWest.add(btnUpdateProfile);
		btnUpdateProfile.addActionListener(this);
		

		

		

		

		
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
	}
	
	public void setupEastPanel()
	{
	
		
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
		panelEast.setLayout(null);
//		panelEast.setLayout(      new GridLayout(5,2, 50, 30)      );
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(17, 40, 286, 160);
		panelEast.add(scrollPane_1);
		
		
		
		lblMyHobbies = new JLabel("Hobbies and Interests");
		lblMyHobbies.setBounds(7, 11, 124, 16);
//		lblMyHobbies.setBounds(711, 30, 179, 20);
		lblMyHobbies.setFont(primaryFont);
		panelEast.add(lblMyHobbies);
		
		
		
		list = new JList(dobby1);
		list.setBounds(141, 77, 139, -58);
		panelEast.add(list);
		scrollPane_1.setRowHeaderView(list);
		list.setFont(primaryFont);
		list.addListSelectionListener(this);

		
			
//			label = new JLabel("Hobby");
//			label.setBounds(571, 369, 51, 20);
//			label.setFont(primaryFont);
//			panelEast.add(label);
			
			
		hobbyList = baseFrame.data_hobbyList;
		comboBox = new JComboBox(hobbyList);
		comboBox.setBounds(157, 213, 127, 22);
//		comboBox.setBounds(675, 366, 167, 26);
		comboBox.setFont(primaryFont);
		panelEast.add(comboBox);
		comboBox.addActionListener(this);
		comboBox.setEditable(true);
		

			
			btnAddHobby = new JButton("Add Hobby");
			btnAddHobby.setBounds(27, 212, 93, 25);
	//		btnAddHobby.setBounds(865, 365, 121, 29);
			btnAddHobby.setFont(primaryFont);
			panelEast.add(btnAddHobby);
			btnAddHobby.addActionListener(this);
		
		
		
		btnNewButton = new JButton("Create new Hobby/Interest");
		btnNewButton.setBounds(12, 368, 187, 25);
//		btnNewButton.setBounds(651, 417, 257, 29);
		btnNewButton.setFont(primaryFont);
		panelEast.add(btnNewButton);
		btnNewButton.addActionListener(this);
	
		
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
		
		if(source == btnNewButton)
		{
			lblAddNewHobby_1 = new JLabel("Add new Hobby or Interest");
			lblAddNewHobby_1.setFont(primaryFont);
			lblAddNewHobby_1.setBounds(28, 31, 262, 16);
			panel_1.add(lblAddNewHobby_1);
			
			newField = new JTextField();
			newField.setFont(primaryFont);
			newField.setBounds(321, 29, 226, 22);
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
