package jw3m.client.gui;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class PanelTests extends JPanel implements ActionListener
{

	private SkillsClient baseFrame;
	private JTextArea textArea;
	private JButton btnTest;
	private JButton btnTest_1;
	private JButton btnTest_2;
	private JButton btnTest_3;
	private JButton btnTest_4;
	private JButton btnTest_5;
	private JButton btnTest_6;
	private JButton btnTest_7;
	private JButton btnNewButton;
	
	/**
	 * Create the panel.
	 */
	public PanelTests(SkillsClient frame)
	{
		setLayout(null);
		
		textArea = new JTextArea();
		textArea.setBounds(155, 97, 619, 271);
		add(textArea);
		
		btnTest = new JButton("Test 1");
		btnTest.setBounds(93, 430, 97, 25);
		add(btnTest);
		btnTest.addActionListener(this);
		
		btnTest_1 = new JButton("Test 2");
		btnTest_1.setBounds(234, 430, 97, 25);
		add(btnTest_1);
		btnTest_1.addActionListener(this);
		
		btnTest_2 = new JButton("Test 3");
		btnTest_2.setBounds(376, 430, 97, 25);
		add(btnTest_2);
		btnTest_2.addActionListener(this);
		
		btnTest_3 = new JButton("Test 4");
		btnTest_3.setBounds(519, 430, 97, 25);
		add(btnTest_3);
		btnTest_3.addActionListener(this);
		
		btnTest_4 = new JButton("Test 5");
		btnTest_4.setBounds(673, 430, 97, 25);
		add(btnTest_4);
		btnTest_4.addActionListener(this);
		
		btnTest_5 = new JButton("Test 6");
		btnTest_5.setBounds(93, 504, 97, 25);
		add(btnTest_5);
		btnTest_5.addActionListener(this);
		
		btnTest_6 = new JButton("Test 7");
		btnTest_6.setBounds(234, 504, 97, 25);
		add(btnTest_6);
		btnTest_6.addActionListener(this);
		
		btnTest_7 = new JButton("Test 8");
		btnTest_7.setBounds(376, 504, 97, 25);
		add(btnTest_7);
		btnTest_7.addActionListener(this);
		
		btnNewButton = new JButton("Test 9");
		btnNewButton.setBounds(519, 504, 97, 25);
		add(btnNewButton);
		btnNewButton.addActionListener(this);

		baseFrame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		
		if(source == btnTest)
		{
			textArea.append(baseFrame.data_hobbyList.toString() + "\n");
		}
		
//		if(source == btnTest_1)
//		{
//			textArea.append(baseFrame.data_hobbyUsers.toString() + "\n");
//		}
		if(source == btnTest_2)
		{
			textArea.append(baseFrame.data_levels.toString() + "\n");
		}
		if(source == btnTest_3)
		{
			textArea.append(baseFrame.data_notifications.toString() + "\n");
		}
		if(source == btnTest_4)
		{
			textArea.append(baseFrame.data_skillList.toString() + "\n");
		}
		if(source == btnTest_5)
		{
			textArea.append(baseFrame.data_userHobby.toString() + "\n");
		}
		if(source == btnTest_6)
		{
			textArea.append(baseFrame.data_userList.toString() + "\n");
		}
		if(source == btnTest_7)
		{
			textArea.append(baseFrame.data_userRatings.toString() + "\n");
		}
		if(source == btnNewButton)
		{
			textArea.append(baseFrame.data_userSkills.toString() + "\n");
		}
		
	}
}
