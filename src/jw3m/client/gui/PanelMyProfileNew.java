package jw3m.client.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import jw3m.beans.Hobby;
import jw3m.beans.UserHobby;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class PanelMyProfileNew extends JPanel implements ActionListener
{
	final static Logger logger = Logger.getLogger(PanelMyProfileNew.class);
	private SkillsClient baseFrame;
	
	private Font primaryFont, secondaryFont;
	
	private JLabel northTitleLabel, southTitleLabel, eastTitleLabel, westTitleLabel, centerTitleLabel;
	private JLabel label;
	private JTextField textField;
	private JLabel label_1;
	private JTextField textField_1;
	private JLabel label_2;
	private JTextField textField_2;
	private JLabel label_3;
	private JTextField textField_3;
	private JLabel label_4;
	private JTextField textField_4;
	private JLabel label_5;
	private JRadioButton radioButton;
	private JRadioButton radioButton_1;
	private JButton button;
	private JLabel lblEditProfile;
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
	
	private JButton btnAddHobby;
	private JComboBox comboBox;
	private JButton btnRemove;
	private JScrollPane scrollPane;
	private JButton btnNewButton;
	private JPanel panel_1, nPanel;
	private JLabel lblAddNewHobby_1;
	private JTextField newField;
	private JButton btnAdd_1;
	private JLabel label_39;
	
	private JList list;
	
	Vector<UserHobby> dobby = new Vector<UserHobby>();
	Vector<String> dobby1 = new Vector<String>();
	Vector<Hobby> hobbyList = new Vector<Hobby>();

	public PanelMyProfileNew(SkillsClient frame)
	{
		PropertyConfigurator.configure("log4j.properties");
		this.baseFrame = frame;
		primaryFont = baseFrame.getPrimaryFont();
		secondaryFont = baseFrame.getSecondaryFont();
		
		//Create the panels (comment ones you don't need)
		
		JPanel panelNorth = new JPanel();
//		JPanel panelSouth = new JPanel();
		JPanel panelEast = new JPanel();
		JPanel panelWest = new JPanel();
		JPanel panelCenter = new JPanel();
		
	//	panelCenter.
		
		
		// these colors to help you get it setup - remove later
		panelNorth.setBorder(new LineBorder(Color.red, 2));
//		panelSouth.setBorder(new LineBorder(Color.green, 2));
		panelEast.setBorder(new LineBorder(Color.yellow, 2));
		panelWest.setBorder(new LineBorder(Color.blue, 2));
		panelCenter.setBorder(new LineBorder(Color.black, 2));
		

		
		// Create all north elements
		northTitleLabel = new JLabel("Title Demo");
		northTitleLabel.setFont(baseFrame.getSecondaryFont());
				
		// Create all south elements
		southTitleLabel = new JLabel("south");
		southTitleLabel.setFont(baseFrame.getPrimaryFont());
		
		// Create all east elements
		eastTitleLabel = new JLabel("east");
//		eastTitleLabel.setBounds(7, 11, 24, 16);
		eastTitleLabel.setFont(baseFrame.getPrimaryFont());
		
		// Create all west elements
//		westTitleLabel = new JLabel("west");
//		westTitleLabel.setBounds(7, 7, 27, 16);
//		westTitleLabel.setFont(baseFrame.getPrimaryFont());
//		
		// Create all center elements
		centerTitleLabel = new JLabel("center");
		centerTitleLabel.setFont(baseFrame.getPrimaryFont());
		
		
		// Add north elements
		panelNorth.add(northTitleLabel);
		panelEast.setLayout(null);
		
		// Add south elements
//		panelSouth.add(southTitleLabel);
		
		// Add east elements
		panelEast.add(eastTitleLabel);
		panelWest.setLayout(null);
		
		// Add west elements
//		panelWest.add(westTitleLabel);
		
		// Add center elements
//		panelCenter.add(centerTitleLabel);
		
		
		


		
		label_1 = new JLabel("Email");
		label_1.setBounds(30, 185, 45, 20);
		label_1.setFont(primaryFont);
		panelWest.add(label_1);
		
		label_3 = new JLabel("Mobile");
		label_3.setBounds(32, 242, 54, 20);
		label_3.setFont(primaryFont);
		panelWest.add(label_3);
		
		label_7 = new JLabel("Name");
		label_7.setBounds(28, 30, 47, 20);
		label_7.setFont(primaryFont);
		panelWest.add(label_7);
		
		name = new JTextField();
		name.setBounds(155, 27, 267, 26);
		name.setFont(primaryFont);
		panelWest.add(name);
		name.setColumns(10);
		
		surname = new JTextField();
		surname.setBounds(155, 75, 267, 26);
		surname.setFont(primaryFont);
		panelWest.add(surname);
		surname.setColumns(10);
		
		email = new JTextField();
		email.setBounds(155, 182, 267, 26);
		email.setFont(primaryFont);
		panelWest.add(email);
		email.setColumns(10);
		
		mobile = new JTextField();
		mobile.setBounds(155, 239, 267, 26);
		mobile.setFont(primaryFont);
		panelWest.add(mobile);
		mobile.setColumns(10);
		
		btnUpdateProfile = new JButton("Update Profile");
		btnUpdateProfile.setBounds(155, 354, 129, 25);
		btnUpdateProfile.setFont(primaryFont);
		panelWest.add(btnUpdateProfile);
		btnUpdateProfile.addActionListener(this);
		
		alias = new JTextField();
		alias.setBounds(155, 124, 267, 26);
		alias.setFont(primaryFont);
		alias.setColumns(10);
		panelWest.add(alias);
		
		lblAlias = new JLabel("Alias");
		lblAlias.setBounds(33, 127, 40, 20);
		lblAlias.setFont(primaryFont);
		panelWest.add(lblAlias);
		
		lblMentor = new JLabel("Mentor");
		lblMentor.setBounds(30, 302, 58, 20);
		lblMentor.setFont(primaryFont);
		panelWest.add(lblMentor);
		
		rdbtnYes = new JRadioButton("Yes");
		rdbtnYes.setBounds(155, 300, 53, 25);
		rdbtnYes.setFont(primaryFont);
		buttonGroup.add(rdbtnYes);
		panelWest.add(rdbtnYes);
		
		rdbtnNo = new JRadioButton("No");
		rdbtnNo.setBounds(239, 300, 49, 25);
		rdbtnNo.setFont(primaryFont);
		buttonGroup.add(rdbtnNo);
		panelWest.add(rdbtnNo);
		
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
		
		// now the east
		
		lblMyHobbies = new JLabel("Hobbies and Interests");
		lblMyHobbies.setBounds(711, 30, 179, 20);
		lblMyHobbies.setFont(primaryFont);
		panelEast.add(lblMyHobbies);
		
		label = new JLabel("Hobby");
		label.setBounds(571, 369, 51, 20);
		label.setFont(primaryFont);
		panelEast.add(label);
		
		btnAddHobby = new JButton("Add Hobby");
		btnAddHobby.setBounds(865, 365, 121, 29);
		btnAddHobby.setFont(primaryFont);
		panelEast.add(btnAddHobby);
		btnAddHobby.addActionListener(this);
		
		
		hobbyList = baseFrame.data_hobbyList;
		
		comboBox = new JComboBox(hobbyList);
		comboBox.setBounds(675, 366, 167, 26);
		
		comboBox.setFont(primaryFont);
		panelEast.add(comboBox);
		comboBox.addActionListener(this);
		comboBox.setEditable(true);
		
		label_4 = new JLabel("Surname");
		label_4.setBounds(28, 78, 72, 20);
		label_4.setFont(primaryFont);
		panelEast.add(label_4);
		
		btnRemove = new JButton("Remove Hobby");
		btnRemove.setBounds(739, 313, 139, 25);
		btnRemove.setFont(primaryFont);
		panelEast.add(btnRemove);
		btnRemove.addActionListener(this);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(691, 75, 257, 225);
		panelEast.add(scrollPane);
		
		list = new JList(dobby1);
		scrollPane.setRowHeaderView(list);
		list.setFont(primaryFont);
//		list.addListSelectionListener(this);
		
		
		
		
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
		btnNewButton.setBounds(651, 417, 257, 29);
		btnNewButton.setFont(primaryFont);
		panelEast.add(btnNewButton);
		btnNewButton.addActionListener(this);
		
	
		
		label_39 = new JLabel("");
		label_39.setBounds(922, 135, 0, 0);
		panelEast.add(label_39);
		
//		panel_1 = new JPanel();
//		panelEast.add(panel_1);
//		panel_1.setBounds(488, 472, 508, 225);
//		panel_1.setLayout(null);
//		
		

		// Setup layout and add all panels (comment out ones not used)
		setLayout(new BorderLayout(0, 0));
		this.add(panelCenter, BorderLayout.CENTER);
		this.add(panelNorth, BorderLayout.NORTH);
//		this.add(panelSouth, BorderLayout.SOUTH);
		this.add(panelEast, BorderLayout.EAST);
		this.add(panelWest, BorderLayout.CENTER);
		this.setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
	}
}
