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

		baseFrame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		
		
	}
}
