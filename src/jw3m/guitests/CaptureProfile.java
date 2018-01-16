package jw3m.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import jw3m.dao.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.ButtonGroup;

public class CaptureProfile extends JFrame implements ActionListener
{

	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblCaptureUserProfile;
	private JLabel lblUserId;
	private JTextField textField;
	private JLabel lblFirstName;
	private JTextField textField_1;
	private JLabel lblSurname;
	private JTextField textField_2;
	private JLabel lblAlias;
	private JTextField textField_3;
	private JLabel lblEmail;
	private JTextField textField_4;
	private JLabel lblMobile;
	private JTextField textField_5;
	private JLabel lblMentor;
	private JRadioButton rdbtnYes;
	private JRadioButton rdbtnNo;
	private JButton btnSubmit;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					CaptureProfile frame = new CaptureProfile();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CaptureProfile()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 849, 686);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 102, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		lblCaptureUserProfile = new JLabel("Capture User Profile");
		lblCaptureUserProfile.setBounds(320, 5, 181, 26);
		lblCaptureUserProfile.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 18));
		panel.add(lblCaptureUserProfile);
		
		lblUserId = new JLabel("User ID");
		lblUserId.setBounds(42, 71, 56, 16);
		panel.add(lblUserId);
		
		textField = new JTextField();
		textField.setBounds(137, 68, 116, 22);
		panel.add(textField);
		textField.setColumns(10);
		
		lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(42, 130, 72, 16);
		panel.add(lblFirstName);
		
		textField_1 = new JTextField();
		textField_1.setBounds(137, 127, 116, 22);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		lblSurname = new JLabel("Surname");
		lblSurname.setBounds(42, 191, 56, 16);
		panel.add(lblSurname);
		
		textField_2 = new JTextField();
		textField_2.setBounds(137, 188, 116, 22);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		lblAlias = new JLabel("Alias");
		lblAlias.setBounds(42, 256, 56, 16);
		panel.add(lblAlias);
		
		textField_3 = new JTextField();
		textField_3.setBounds(137, 253, 116, 22);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		lblEmail = new JLabel("Email");
		lblEmail.setBounds(42, 329, 56, 16);
		panel.add(lblEmail);
		
		textField_4 = new JTextField();
		textField_4.setBounds(137, 326, 116, 22);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		lblMobile = new JLabel("Mobile ");
		lblMobile.setBounds(42, 401, 56, 16);
		panel.add(lblMobile);
		
		textField_5 = new JTextField();
		textField_5.setBounds(137, 398, 116, 22);
		panel.add(textField_5);
		textField_5.setColumns(10);
		
		lblMentor = new JLabel("Mentor");
		lblMentor.setBounds(42, 465, 56, 16);
		panel.add(lblMentor);
		
		rdbtnYes = new JRadioButton("Yes");
		buttonGroup.add(rdbtnYes);
		rdbtnYes.setBounds(137, 461, 56, 25);
		panel.add(rdbtnYes);
		
		rdbtnNo = new JRadioButton("No");
		buttonGroup.add(rdbtnNo);
		rdbtnNo.setBounds(207, 461, 56, 25);
		panel.add(rdbtnNo);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(137, 520, 97, 25);
		panel.add(btnSubmit);
		btnSubmit.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		String sql = "test";
		
		DAO userDAO;
			
		if(source == btnSubmit)
		{
			try
			{
				userDAO = new DAO();
				userDAO.saveUser(sql);
			} 
			catch (Exception e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(this, "You just submitted your profile");
		}
		
	}
}
