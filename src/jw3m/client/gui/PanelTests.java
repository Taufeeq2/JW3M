package jw3m.client.gui;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PanelTests extends JPanel
{

	private SkillsClient baseFrame;
	private JTextArea textArea;
	
	/**
	 * Create the panel.
	 */
	public PanelTests(SkillsClient frame)
	{
		setLayout(null);
		
		textArea = new JTextArea();
		textArea.setBounds(155, 97, 619, 271);
		add(textArea);

		baseFrame = frame;
	}
}
