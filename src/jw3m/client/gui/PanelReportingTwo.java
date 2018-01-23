package jw3m.client.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import jw3m.beans.Rating;
import jw3m.beans.Skill;
import jw3m.dao.DAO;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

public class PanelReportingTwo extends JPanel implements ActionListener, ListSelectionListener
{
	private JPanel panel;
	MyTableModel a;
	JTable table;
	private JComboBox comboBox;
	private Skill skillSelected = null;
	private Vector data = null;
	private Vector<Rating> ratingsData = null;
	private MyTableModel myModel;

	public PanelReportingTwo()
	{

		Object[] values =
		{ "", "" , "" };
		data = new Vector();
		panel = new JPanel();

		JPanel panel_1 = new JPanel();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE))
					.addContainerGap(137, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(44)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
					.addGap(138)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(97, Short.MAX_VALUE))
		);
		panel_1.setLayout(new GridLayout(2, 5, 0, 0));
		
		Vector<Skill> skillNames = new Vector<Skill>();
		try 
		{
			DAO getSkills = new DAO();
			skillNames = getSkills.getSkillList();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		
		
		comboBox = new JComboBox(skillNames);
		comboBox.addActionListener(this);
		panel_1.add(comboBox);
		panel.setLayout(new GridLayout(1, 1, 0, 0));
		
		myModel = new MyTableModel();
		table = new JTable(myModel);
		table.setFont(new Font("Arial", Font.PLAIN, 24));
		table.setRowHeight(28);
		table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 24));
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);
//		a = (MyTableModel) table.getModel();
//		a.insertData(values);

		// Create the scroll pane and add the table to it.
		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane);
		setLayout(groupLayout);
	}

	class MyTableModel extends AbstractTableModel
	{
		private String[] columnNames =
		{ "User ID", "Average Rating", "Number of Ratings" };

	//	private Vector data = new Vector();
	

		public final Object[] longValues =
		{ "", ""};

		@Override
		public int getColumnCount()
		{
			return columnNames.length;
		}

		@Override
		public int getRowCount()
		{
			return data.size();
		}

		@Override
		public Object getValueAt(int row, int col)
		{
			return ((Vector) data.get(row)).get(col);
		}

		public String getColumnName(int col)
		{
			return columnNames[col];
		}

		public Class getColumnClass(int c)
		{
			return getValueAt(0, c).getClass();
		}

		public void setValueAt(Object value, int row, int col)
		{
			((Vector) data.get(row)).setElementAt(value, col);
			fireTableCellUpdated(row, col);
		}

		public boolean isCellEditable(int row, int col)
		{
			return false;
		}

		public void insertData(Object[] values)
		{
			data.add(new Vector());
			for (int i = 0; i < values.length; i++)
			{
				((Vector) data.get(data.size() - 1)).add(values[i]);
			}
			fireTableDataChanged();
		}

		public void removeRow(int row)
		{
			data.removeElementAt(row);
			fireTableDataChanged();
		}
	}

	private static void createAndShowGUI()
	{
		JFrame frame = new JFrame("Reporting");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		PanelReportingTwo newContentPane = new PanelReportingTwo();
		frame.setContentPane(newContentPane);

		// Display the window.
		frame.setLocation(650, 300);
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args)
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				createAndShowGUI();
			}
		});
	}

	@Override
	public void valueChanged(ListSelectionEvent e)
	{

	}	
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		Object source = ae.getSource();
		if (source == comboBox)
		{
			int numberOfRowsInTable = table.getRowCount();
			System.out.println("Number of Rows in Table = " + numberOfRowsInTable );
			for (int x=0; x<numberOfRowsInTable; x++)
			{
				a.removeRow(0);
			}
			
			skillSelected = (Skill) comboBox.getSelectedItem();
			DAO skillUsers;
			try
			{
				skillUsers = new DAO();
				ratingsData = skillUsers.getRatings(skillSelected);
				for (int x=0; x<ratingsData.size(); x++)
				{
					Rating thisRating = ratingsData.get(x);
					
					
					String saveUserId = thisRating.getUserID();	
					Object[] data = new Object[3];
					double aveLevel = 0.00;
					int numberOfRatings = 0;
					
						aveLevel = aveLevel + thisRating.getLevel();
						numberOfRatings = numberOfRatings + 1;
						
						data[0] = saveUserId;
						data[1] = aveLevel/numberOfRatings;
						data[2] = numberOfRatings;
					
					a = (MyTableModel) table.getModel();
					a.insertData(data);
				}	
				System.out.println("number of skillUsers = " + ratingsData.size());
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

}
