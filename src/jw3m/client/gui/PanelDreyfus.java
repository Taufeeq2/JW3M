package jw3m.client.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Dimension;

public class PanelDreyfus extends JPanel 
{
	final static Logger logger = Logger.getLogger(PanelLogin.class);
	private SkillsClient baseFrame;
	private Font primaryFont, secondaryFont;
	private JLabel lblDreyfusModel_1, pic;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private ImageIcon ii = null;

	public PanelDreyfus(SkillsClient frame)
	{
		PropertyConfigurator.configure("log4j.properties");
		this.baseFrame = frame;
		primaryFont = baseFrame.getPrimaryFont();
		secondaryFont = baseFrame.getSecondaryFont();
		
		
		pic = new JLabel(new ImageIcon("resources/DreyfusModel.jpg"));
		pic.setBounds(10, 10, 614, 143);
		
		scrollPane = new JScrollPane(pic,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		add(scrollPane, BorderLayout.SOUTH);
		
		
	}
}
