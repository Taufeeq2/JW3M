package jw3m.client.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import java.awt.Color;

public class PanelRateSomeone extends JPanel implements ActionListener
{
	private SkillsClient baseFrame;
	private JLabel lblRateSomeone;
	private JButton btnSubmit;
	
	public PanelRateSomeone(SkillsClient frame) {
		setBackground(new Color(0, 153, 255));
		setLayout(null);
		
		lblRateSomeone = new JLabel("Rate Someone");
		lblRateSomeone.setBounds(311, 5, 134, 27);
		lblRateSomeone.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 22));
		add(lblRateSomeone);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(334, 484, 97, 25);
		add(btnSubmit);
		btnSubmit.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		
		if(source == btnSubmit)
		{
			JOptionPane.showMessageDialog(this, "You have been chosen to rate me!");
			
		}
		
	}

}
