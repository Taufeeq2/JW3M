package jw3m.client.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import jw3m.beans.Hobby;
import jw3m.beans.User;
import jw3m.beans.UserHobby;

public class PanelMyProfileWest extends JPanel implements ActionListener, ListSelectionListener
{
	final static Logger logger = Logger.getLogger(PanelMyProfile.class);
	private SkillsClient baseFrame;
	private JLabel label_1;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_7;
	private JTextField name;
	private JTextField surname;
	private JTextField email;
	private JTextField alias;
	private JTextField mobile;
	private JLabel lblAlias;
	private JLabel lblMentor;
	private JRadioButton rdbtnYes;
	private JRadioButton rdbtnNo;
	private JButton btnUpdateProfile;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnAddHobby;
	private JButton btnRemove;
	private JScrollPane scrollPane;
	private JList list;
	Vector<UserHobby> dobby = new Vector<UserHobby>();
	Vector<String> dobby1 = new Vector<String>();
	Vector<Hobby> hobbyList = new Vector<Hobby>();
	User temp = new User();
	String returnedValue;
	private JButton btnNewButton;
//	private JPanel this;
	private JLabel lblAddNewHobby_1;
	private JTextField newField;
	private JButton btnAdd_1;
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
	private Font primaryFont, secondaryFont;
	
	public PanelMyProfileWest(SkillsClient frame) {
		baseFrame = frame;
		primaryFont = baseFrame.getPrimaryFont();
        secondaryFont = baseFrame.getSecondaryFont();
        PropertyConfigurator.configure("log4j.properties");
        
	
	
	
	setBackground(UIManager.getColor("Button.background"));
	setForeground(Color.LIGHT_GRAY);
//	this = new JPanel();
	setLayout(new BorderLayout(0, 0));
	this.setLayout(null);
	
	label_1 = new JLabel("Email");
	label_1.setBounds(30, 185, 45, 20);
	label_1.setFont(primaryFont);
	this.add(label_1);
	
	label_3 = new JLabel("Mobile");
	label_3.setBounds(32, 242, 54, 20);
	label_3.setFont(primaryFont);
	this.add(label_3);
	
	label_7 = new JLabel("Name");
	label_7.setBounds(28, 30, 47, 20);
	label_7.setFont(primaryFont);
	this.add(label_7);
	
	name = new JTextField();
	name.setBounds(155, 27, 267, 26);
	name.setFont(primaryFont);
	this.add(name);
	name.setColumns(10);
	
	surname = new JTextField();
	surname.setBounds(155, 75, 267, 26);
	surname.setFont(primaryFont);
	this.add(surname);
	surname.setColumns(10);
	
	mobile = new JTextField();
	mobile.setBounds(155, 239, 267, 26);
	mobile.setFont(primaryFont);
	this.add(mobile);
	mobile.setColumns(10);
	
	email = new JTextField();
	email.setBounds(155, 182, 267, 26);
	email.setFont(primaryFont);
	this.add(email);
	email.setColumns(10);
	
	btnUpdateProfile = new JButton("Update Profile");
	btnUpdateProfile.setBounds(155, 354, 129, 25);
	btnUpdateProfile.setFont(primaryFont);
	this.add(btnUpdateProfile);
	btnUpdateProfile.addActionListener(this);
	
	alias = new JTextField();
	alias.setBounds(155, 124, 267, 26);
	alias.setFont(primaryFont);
	alias.setColumns(10);
	this.add(alias);
	
	lblAlias = new JLabel("Alias");
	lblAlias.setBounds(33, 127, 40, 20);
	lblAlias.setFont(primaryFont);
	this.add(lblAlias);
	
	lblMentor = new JLabel("Mentor");
	lblMentor.setBounds(30, 302, 58, 20);
	lblMentor.setFont(primaryFont);
	this.add(lblMentor);
	
	rdbtnYes = new JRadioButton("Yes");
	rdbtnYes.setBounds(155, 300, 53, 25);
	rdbtnYes.setFont(primaryFont);
	buttonGroup.add(rdbtnYes);
	this.add(rdbtnYes);
	
	rdbtnNo = new JRadioButton("No");
	rdbtnNo.setBounds(239, 300, 49, 25);
	rdbtnNo.setFont(primaryFont);
	buttonGroup.add(rdbtnNo);
	this.add(rdbtnNo);
	
	// Mo seriously why not add the current values in edit???? Love Warren
	name.setText(baseFrame.authenticatedUser.getFirstName());
	surname.setText(baseFrame.authenticatedUser.getSurname());
	email.setText(baseFrame.authenticatedUser.getEmailAddress());
	alias.setText(baseFrame.authenticatedUser.getAlias());
	
	
	if(baseFrame.authenticatedUser.isMentor())
	{
		rdbtnYes.setSelected(true);
	}
	else
	{
		rdbtnNo.setSelected(true);
	}
	
	btnAddHobby = new JButton("Add Hobby");
	btnAddHobby.setBounds(865, 365, 121, 29);
	btnAddHobby.setFont(primaryFont);
	this.add(btnAddHobby);
	btnAddHobby.addActionListener(this);
	
	
	hobbyList = baseFrame.data_hobbyList;
	
	label_4 = new JLabel("Surname");
	label_4.setBounds(28, 78, 72, 20);
	label_4.setFont(primaryFont);
	this.add(label_4);
	this.add(this, BorderLayout.CENTER);

	
	
	


	
	
	
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
