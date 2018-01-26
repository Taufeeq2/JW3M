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
	
	public PanelEdit(SkillsClient frame) {
		baseFrame = frame;
		
		setBackground(UIManager.getColor("Button.background"));
		setForeground(Color.LIGHT_GRAY);
		setLayout(null);
		
		lblEditProfile = new JLabel("My Profile");
		lblEditProfile.setBounds(335, 13, 114, 16);
		lblEditProfile.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 22));
		add(lblEditProfile);
		
		label_1 = new JLabel("Email");
		label_1.setBounds(39, 237, 56, 16);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(label_1);
		
		label_3 = new JLabel("Mobile");
		label_3.setBounds(39, 288, 56, 16);
		label_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(label_3);
		
		label_4 = new JLabel("Surname");
		label_4.setBounds(39, 132, 92, 16);
		label_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(label_4);
		
		label_7 = new JLabel("Name");
		label_7.setBounds(39, 79, 56, 16);
		label_7.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(label_7);
		
		name = new JTextField();
		name.setBounds(166, 77, 260, 22);
		name.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(name);
		name.setColumns(10);
		
		surname = new JTextField();
		surname.setBounds(166, 130, 260, 22);
		surname.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(surname);
		surname.setColumns(10);
		
		email = new JTextField();
		email.setBounds(166, 235, 260, 22);
		email.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(email);
		email.setColumns(10);
		
		mobile = new JTextField();
		mobile.setBounds(166, 286, 260, 22);
		mobile.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(mobile);
		mobile.setColumns(10);
		
		btnUpdateProfile = new JButton("Update Profile");
		btnUpdateProfile.setBounds(166, 377, 147, 25);
		btnUpdateProfile.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(btnUpdateProfile);
		btnUpdateProfile.addActionListener(this);
		
		alias = new JTextField();
		alias.setBounds(166, 187, 260, 22);
		alias.setFont(new Font("Tahoma", Font.BOLD, 16));
		alias.setColumns(10);
		add(alias);
		
		lblAlias = new JLabel("Alias");
		lblAlias.setBounds(39, 189, 56, 16);
		lblAlias.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(lblAlias);
		
		lblMentor = new JLabel("Mentor");
		lblMentor.setBounds(39, 333, 92, 16);
		lblMentor.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(lblMentor);
		
		rdbtnYes = new JRadioButton("Yes");
		rdbtnYes.setBounds(166, 330, 56, 25);
		rdbtnYes.setFont(new Font("Tahoma", Font.BOLD, 14));
		buttonGroup.add(rdbtnYes);
		add(rdbtnYes);
		
		rdbtnNo = new JRadioButton("No");
		rdbtnNo.setBounds(226, 330, 56, 25);
		rdbtnNo.setFont(new Font("Tahoma", Font.BOLD, 14));
		buttonGroup.add(rdbtnNo);
		add(rdbtnNo);
		
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
		
		lblMyHobbies = new JLabel("My Hobbies");
		lblMyHobbies.setBounds(650, 71, 101, 16);
		lblMyHobbies.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(lblMyHobbies);
		
		label = new JLabel("Hobby");
		label.setBounds(495, 418, 72, 25);
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(label);
		
		btnAddHobby = new JButton("Add Hobby");
		btnAddHobby.setBounds(766, 418, 121, 25);
		btnAddHobby.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(btnAddHobby);
		btnAddHobby.addActionListener(this);
		
		
		hobbyList = baseFrame.data_hobbyList;
//		
//		for(int i = 0; i < hobbyList.size(); i++)
//		{
//			dobby1.addElement(hobbyList.get(i).to);
//		}
		
		comboBox = new JComboBox(hobbyList);
		comboBox.setBounds(582, 419, 147, 22);
		comboBox.setToolTipText("Select Hobby and click Add Hobby");
		
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(comboBox);
		comboBox.addActionListener(this);
		comboBox.setEditable(true);
		
		btnRemove = new JButton("Remove Hobby");
		btnRemove.setBounds(621, 366, 147, 25);
		btnRemove.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(btnRemove);
		btnRemove.addActionListener(this);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(582, 100, 245, 246);
		add(scrollPane);
		
		
		
		
		// sort out mentor
		
		
		
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
		
		list = new JList(dobby1);
		list.addListSelectionListener(this);
		scrollPane.setViewportView(list);
		
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
//			Vector<Hobby> hobby = new Vector<Hobby>();
//			hobby.add((Hobby)comboBox.getSelectedItem());
//			hobbyArea.append("\n" + hobby.toString());
//			
//			Hobby tempHobby = new Hobby();
//			
//			tempHobby = (Hobby) comboBox.getSelectedItem();
//			System.out.println(tempHobby + " Test");
//			dobby1.add(tempHobby);
//			list.add
//
//			baseFrame.setNetUserHobby(baseFrame.authenticatedUser, hobby);
			
//			String hobbyName = (String) comboBox.getSelectedItem();
//			list.add(hobbyName);

			Vector<Hobby> dobs = new Vector<Hobby>();
			Hobby hobs = new Hobby();
			Vector<UserHobby> uhobby = new Vector<UserHobby>();

			
			System.out.println("Size of hobby list = " + hobbyList.size());
			for (int i = 0; i < hobbyList.size(); i++)
			{

				
				if((comboBox.getSelectedItem()).equals(hobbyList.get(i).getHobbyName()))
				{
					hobs.setHobbyID(hobbyList.get(i).getHobbyID());
				
					hobs.setHobbyName(comboBox.getSelectedItem().toString());
					
					hobs.add(d);
				}
				
				
			}
			
			temp = baseFrame.authenticatedUser;
			
			

			list.removeListSelectionListener(this);
			dobby1.add(comboBox.getSelectedItem().toString());
			list.setListData(dobby1);
			list.addListSelectionListener(this);
			
			baseFrame.setNetUserHobby(temp, uhobby);
//			
//			if((baseFrame.setNetUserHobby(temp, dobby)).equals(true))
//			{
//				JOptionPane.showMessageDialog(this, "Hobby added");
//			}
//			else
//			{
//				JOptionPane.showMessageDialog(this, "You already have this hobby added");
//			}
			
				
			
		
		}
		
		if(source == btnRemove)
		{
			DAO dao;
			try
			{
				dao = new DAO();
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
				
				dao.removeUserHobby(uHob);
			} 
			catch (Exception e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
//			Vector<Hobby> hobby1 = new Vector<Hobby>();
//			hobbyArea.remove(hobbyArea.getText().replace('', ''));
//			baseFrame.setNetUserHobby(baseFrame.authenticatedUser, hobby1);
			
			
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
