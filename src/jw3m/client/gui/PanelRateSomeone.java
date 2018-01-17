package jw3m.client.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class PanelRateSomeone extends JPanel 
{
	private SkillsClient baseFrame;
	private JLabel lblRateSomeone;
	private JButton btnSubmit;
	private JPanel panel;
	private JPanel panel_1;
	
	public PanelRateSomeone(SkillsClient frame) {
		setLayout(null);
		
		lblRateSomeone = new JLabel("Rate Someone");
		lblRateSomeone.setBounds(311, 5, 134, 27);
		lblRateSomeone.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 22));
		add(lblRateSomeone);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(334, 541, 97, 25);
		add(btnSubmit);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 820, 283);
		add(panel);
		
		panel_1 = new JPanel();
		panel_1.setBounds(0, 281, 820, 256);
		add(panel_1);
	}

}
