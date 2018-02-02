package jw3m.client.gui;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import jw3m.beans.Notification;
import jw3m.beans.Rating;
import jw3m.beans.Skill;
import jw3m.beans.User;
import jw3m.widgets.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridLayout;

public class PanelSearch extends JPanel implements ActionListener
{
	final static Logger logger = Logger.getLogger(PanelSearch.class);
	private SkillsClient baseFrame;
	

	private JLabel northTitleLabel, southTitleLabel, eastTitleLabel, westTitleLabel;
	private JButton centerButtonSearch;

	private JPanel nPanel, cPanel;

	private JLabel lblRateSomeone, lblBanner;
	private JButton btnSubmit;
	private DefaultTableModel model = null;
	private JScrollPane scrollPane;
	private JTable table;
	private Vector<Skill> skillList = new Vector<Skill>();
	private Vector<Skill> skillNames = new Vector<Skill>();
	private JLabel lblSearch;
	private Rating ratee;
	private JComboBox separatorComboBox ;
	
	private Vector itemsText = new Vector();
	private Vector<String> itemsName = new Vector<String>();
	private Font primaryFont, secondaryFont;
	private JButton btnClear;
	private JLabel lblImgLabel;

	public PanelSearch(SkillsClient frame)
	{
		baseFrame = frame;
		primaryFont = baseFrame.getPrimaryFont();
        secondaryFont = baseFrame.getSecondaryFont();
        PropertyConfigurator.configure("log4j.properties");
        
        nPanel = new JPanel();
		cPanel = new JPanel();
        
		lblImgLabel = new JLabel(new ImageIcon("resources/RateSomeone_Full.jpg"));
		nPanel.add(lblImgLabel);
	
//		btnSubmit = new JButton("Submit");
//		btnSubmit.setBounds(690, 484, 110, 25);
//		btnSubmit.setFont(primaryFont);
//		btnSubmit.addActionListener(this);
//		btnSubmit.setEnabled(false);
		
		
		Vector<Skill> skillNms = new Vector<Skill>();
		baseFrame.getNetSkillList();
		skillNms = baseFrame.data_skillList;
		
		
		//System.out.println(skillNms);
		
		for (int i = 0 ; i < skillNms.size() ; i++)
		{
			skillNames.add(skillNms.get(i));
		}
		
		lblSearch = new JLabel("Search ");
		lblSearch.setBounds(513, 71, 73, 16);
		lblSearch.setFont(primaryFont);
		
	
		setLayout(new BorderLayout(0, 0));
		cPanel.setLayout(null);

		table = new JTable(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFont(primaryFont);
		JTableHeader header = table.getTableHeader();
	    header.setFont(primaryFont);

	    JTextArea txtArea = new JTextArea("test");
		

		

		
		

		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(12, 143, 1668, 293);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		cPanel.add(scrollPane);
		scrollPane.setViewportView(txtArea);
//		scrollPane.setViewportView(table);
		cPanel.add(scrollPane);
//		nPanel.add(lblRateSomeone);
		cPanel.add(lblSearch);
//		cPanel.add(btnSubmit);
		
		
		this.add(nPanel, BorderLayout.NORTH);
		this.add(cPanel, BorderLayout.CENTER);
		
		// moved to global variable
//		Vector itemsText = new Vector();
//	    Vector itemsName = new Vector();
		
		
        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
      
        
        Vector<Notification> notify = baseFrame.getNetUserNotifications(baseFrame.authenticatedUser);
        		
        String str = new String();
        User requestUser = new User();
        
   
        
//        System.out.println("size of notify" + notify.size());


       

        itemsText.addElement(separator );
        itemsName.addElement("seperator"); // as this index will be unselectable it does not matter what we put here
        
        
        
        Vector<User> allUsers = baseFrame.data_userList;
        
        



		// Changed this to a SeperatorComboBox (which is a custom class jw3m.widgets.SeparatorComboBox
        separatorComboBox  = new SeparatorComboBox(itemsText);
        separatorComboBox.setBounds(630, 68, 367, 22);
        separatorComboBox.setFont(primaryFont);
		cPanel.add(separatorComboBox );
		
		separatorComboBox.setSelectedIndex(-1);
		

		
		separatorComboBox.addActionListener(this);

		
	}
	
	public void setupCenterPanel()
	{
		
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
	}
}
