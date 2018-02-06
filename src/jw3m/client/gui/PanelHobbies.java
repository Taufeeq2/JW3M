package jw3m.client.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import jw3m.beans.Hobby;
import jw3m.beans.Skill;
import jw3m.beans.User;
import jw3m.beans.UserHobby;
import jw3m.dao.DAO;

import javax.swing.JComboBox;

public class PanelHobbies extends JPanel implements ActionListener
{
	final static Logger logger = Logger.getLogger(PanelMyProfile.class);
	private SkillsClient baseFrame;
	private JPanel nPanel, cPanel;
	private Font primaryFont, secondaryFont;
	private JComboBox comboBox;
	private JTable table;
	private DefaultTableModel model = null;
	Vector<Hobby> hobbyList = null;

	/**
	 * Create the panel.
	 */
	public PanelHobbies(SkillsClient frame)
	{
		
		baseFrame = frame;
		primaryFont = baseFrame.getPrimaryFont();
        secondaryFont = baseFrame.getSecondaryFont();
        PropertyConfigurator.configure("log4j.properties");
        

        nPanel = new JPanel();
		setLayout(new BorderLayout(0, 0));
		cPanel = new JPanel();
		
		table = new JTable(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFont(primaryFont);
		JTableHeader header = table.getTableHeader();
	    header.setFont(primaryFont);
		cPanel.add(table);
		
		hobbyList = new Vector<Hobby>();
		hobbyList = baseFrame.data_hobbyList;
		
		this.add(cPanel, BorderLayout.CENTER);
		
		comboBox = new JComboBox();
		cPanel.add(comboBox);
		this.add(nPanel, BorderLayout.NORTH);
		comboBox.addActionListener(this);
	}
	
	public void setupHobbyTable()
	{

		// Here we set up the model

		String str[] =
		{ "User ID", "Surname", "First Name" };
		model = new DefaultTableModel(str, 0)
		
		
		
		
		{
			public void setValueAt(Object aValue, int row, int column)
			{
				

				Vector rowVector = (Vector) dataVector.elementAt(row);
				rowVector.setElementAt(aValue, column);
				fireTableCellUpdated(row, column);

				setCustomTableElement(aValue, row, column);
				
				

				
			}
			
			

			public boolean isCellEditable(int row, int column)
			{
				// make read only field column 0 - other column is directly
				// editable
				if (column == 0 || column == 1 || column == 2)
				{
					return false;
				} 
				else
				{
					return true;
				}

			}

		};

		// Setup the model

		
	}
	
	public void setCustomTableElement(Object aValue, int row, int column)
	{

//		Skill skl = skillList.get(row);
//
//		switch (column)
//		{
//		case 0:
//			try
//			{
//				skl.setSkillID(Integer.parseInt(aValue.toString()));
//			} catch (NumberFormatException e)
//			{
//				model.setValueAt(skl.getSkillID(), row, column);
//
//			}
//			break;
//		case 1:
//			skl.setSkillName(aValue.toString());
//			break;
////		case 2:
//			skl.setSkillVendor(aValue.toString());
//			break;
//		case 3:
//			skl.setSkillDescription(aValue.toString());
//			break;
//
//		}
//		System.out.println(aValue + " " + row + " " + column);

		// skillList.SaveToDisk();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		
        if(source == comboBox)
        {
               try
               {
                      DAO dao = new DAO();
                      System.out.println("Hobby selected: " + comboBox.getSelectedItem());
                      Vector<UserHobby> userHob = new Vector<UserHobby>();
                      Vector<User> tempUser = new Vector<User>();
                      Hobby tempHobby = new Hobby();
                      User newUser = new User();
                      
                      for (int i = 0; i < hobbyList.size(); i++)
                      {
                            System.out.println("combobox: " +  comboBox.getSelectedItem() + "hobbyList: " + hobbyList.get(i).getHobbyName() );
                            
                      if((comboBox.getSelectedItem().toString()).equals(hobbyList.get(i).getHobbyName()))
                            {
                                   tempHobby.setHobbyID(hobbyList.get(i).getHobbyID());
                                   tempHobby.setHobbyName(hobbyList.get(i).getHobbyName());
                                   System.out.println("hobby id: " + tempHobby.getHobbyID() + "hobby name: " + tempHobby.getHobbyName());
                            }
                      }
                      
                      tempUser = dao.getUserHobby(tempHobby);
                      
                      for (int i = 0; i < tempUser.size(); i++)
                      {
//                          System.out.println("users: " +  tempUser);
                             newUser.setUserName(tempUser.get(i).getUserName());
                             newUser.setSurname(tempUser.get(i).getSurname());
                             newUser.setFirstName(tempUser.get(i).getFirstName());
                            
                            
                            Object obj[] =
                                   {newUser.getUserName(), newUser.getSurname(), newUser.getFirstName()};
                            model.addRow(obj);
                            
                            
                      }
                      
               } catch (Exception e1)
               {
                      // TODO Auto-generated catch block
                      e1.printStackTrace();
               }
               
               
               
        }

		
	}

}
