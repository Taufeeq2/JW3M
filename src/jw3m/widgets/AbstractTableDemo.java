package jw3m.widgets;

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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class AbstractTableDemo extends JPanel
{
	private JPanel panel;
	MyTableModel a;
	JTable table;
	private JTextField nameJTF;
	private JTextField surnameJTF;
	private JTextField titleJTF;
	private JTextField salaryJTF;
	private JButton btnAddRow;
	private JButton btnDeleteRow;
	private JCheckBox managerChkBox;

	public AbstractTableDemo()
	{

		Object[] values =
		{ "Greg", "Guy", "Mr", 2000.89, new Boolean(false) };

		panel = new JPanel();

		JPanel panel_1 = new JPanel();

		btnAddRow = new JButton("Add Row");
		btnAddRow.addActionListener(new BtnAddRowActionListener());
		btnAddRow.setFont(new Font("Tahoma", Font.BOLD, 18));

		btnDeleteRow = new JButton("Delete Row");
		btnDeleteRow.addActionListener(new BtnDeleteRowActionListener());
		btnDeleteRow.setFont(new Font("Tahoma", Font.BOLD, 18));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout
						.createSequentialGroup().addGap(34).addGroup(groupLayout
								.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE))
						.addContainerGap(25, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup().addGap(170).addComponent(btnAddRow)
						.addPreferredGap(ComponentPlacement.RELATED, 293, Short.MAX_VALUE).addComponent(btnDeleteRow)
						.addGap(204)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(44)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE).addGap(57)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(btnAddRow)
								.addComponent(btnDeleteRow))
						.addGap(50).addComponent(panel, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(21, Short.MAX_VALUE)));
		panel_1.setLayout(new GridLayout(2, 5, 0, 0));

		JLabel nameLBL = new JLabel("Name");
		nameLBL.setFont(new Font("Tahoma", Font.BOLD, 18));
		nameLBL.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(nameLBL);

		JLabel surnameLBL = new JLabel("Surname");
		surnameLBL.setFont(new Font("Tahoma", Font.BOLD, 18));
		surnameLBL.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(surnameLBL);

		JLabel titleLBL = new JLabel("Title");
		titleLBL.setFont(new Font("Tahoma", Font.BOLD, 18));
		titleLBL.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(titleLBL);

		JLabel salaryLBL = new JLabel("Salary");
		salaryLBL.setFont(new Font("Tahoma", Font.BOLD, 18));
		salaryLBL.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(salaryLBL);

		JLabel managerLBL = new JLabel("Manager");
		managerLBL.setFont(new Font("Tahoma", Font.BOLD, 18));
		managerLBL.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(managerLBL);

		nameJTF = new JTextField();
		nameJTF.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel_1.add(nameJTF);
		nameJTF.setColumns(10);

		surnameJTF = new JTextField();
		surnameJTF.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel_1.add(surnameJTF);
		surnameJTF.setColumns(10);

		titleJTF = new JTextField();
		titleJTF.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel_1.add(titleJTF);
		titleJTF.setColumns(10);

		salaryJTF = new JTextField();
		salaryJTF.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel_1.add(salaryJTF);
		salaryJTF.setColumns(10);

		managerChkBox = new JCheckBox("");
		managerChkBox.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(managerChkBox);
		panel.setLayout(new GridLayout(1, 1, 0, 0));

		table = new JTable(new MyTableModel());
		table.setFont(new Font("Arial", Font.PLAIN, 24));
		table.setRowHeight(28);
		table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 24));
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);
		a = (MyTableModel) table.getModel();
		a.insertData(values);

		// Create the scroll pane and add the table to it.
		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane);
		setLayout(groupLayout);
	}

	class MyTableModel extends AbstractTableModel
	{
		private String[] columnNames =
		{ "Name", "Surname", "Title", "Salary", "Manager" };

		private Vector data = new Vector();

		public final Object[] longValues =
		{ "", "", "", new Float(20), Boolean.TRUE };

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
			if (col > 0)
			{
				return true;
			}
			else
			{
				return false;
			}
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
		JFrame frame = new JFrame("Abstract Table Demo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		AbstractTableDemo newContentPane = new AbstractTableDemo();
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

	private class BtnAddRowActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			Object[] data = new Object[5];
			data[0] = nameJTF.getText();
			data[1] = surnameJTF.getText();
			data[2] = titleJTF.getText();
			data[3] = Float.parseFloat(salaryJTF.getText());

			if (managerChkBox.isSelected())
			{
				data[4] = new Boolean(true);
			}
			else
			{
				data[4] = new Boolean(false);
			}
			a.insertData(data);
			nameJTF.setText("");
			surnameJTF.setText("");
			titleJTF.setText("");
			salaryJTF.setText("");
			managerChkBox.setSelected(false);
		}
	}

	private class BtnDeleteRowActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			int selectedRow = table.getSelectedRow();
			if (selectedRow < 0)
			{
				return;
			}
			System.out.println("Selected row = " + selectedRow);
			a.removeRow(selectedRow);
		}
	}
}
