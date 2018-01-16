package jw3m.dao;
import java.util.*;
import jw3m.beans.*;
import java.sql.*;

public class DAO
{	
		private Connection con  = null;
		private Statement sqlstat = null;
		private String url = "jdbc:mysql://localhost:3306/jw3m_project";
		private String user = "root";
		private String psw = "root";
		private ResultSet rs = null;
		private String msg = "";
		private Vector<User> userVect = null;
	//	private User user1 = null;
		private String id, fn, s, psw1, al, em;
		private int mob = 0;
		private boolean men = true;
		
		
		public DAO() throws Exception 
		{
			
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(url, user, psw);	
			} 
			catch (ClassNotFoundException e)
			{
				System.out.println("No driver found");
				return;
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			
			
			//System.out.println("Connected to DB");
			
			sqlstat = con.createStatement();
			rs = sqlstat.executeQuery("Select * from users");
			
			if (!rs.next())
		    {
			    sqlstat.executeUpdate("insert into users values('', '', '', '', '', '', '')");
		    }
			this.getUserList();
			//this.getUser(fn);
			
		}	
		
		public boolean saveUser(String sql)
		{
			try
			{
				sqlstat.executeUpdate(sql);
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			return true;
		}
		
		public Vector<User> getUserList()
		{
			try
			{
				rs = sqlstat.executeQuery("Select * from users");
				
				while(rs.next())
				{
					id = rs.getString("userID");
					psw1 = rs.getString("password");
					fn = rs.getString("firstName");
					s = rs.getString("surname");
					al = rs.getString("alias");
					em = rs.getString("email");
					mob = rs.getInt("mobile");
					men = rs.getBoolean("mentor");
					
					userVect = new Vector<User>();			
					User tempUser = new User();
					
					tempUser.setUserName(id);
					tempUser.setPassword(psw1);
					tempUser.setFirstName(fn);
					tempUser.setSurname(s);
					tempUser.setAlias(al);
					tempUser.setEmailAddress(em);
					tempUser.setMobile(mob);
					tempUser.setMentor(men);
					
					userVect.add(tempUser);
					
					System.out.println("Name: " + userVect.get(0).getFirstName());
				}
			} 
			catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return userVect;
			
		}
		
		public String getUser(String firstName)
		{
			
	        try
	        {
	        	rs = sqlstat.executeQuery("Select * from users where firstName = " + firstName);
	        
	        	while(rs.next())
	        	{
	        		id = rs.getString("userID");
					psw1 = rs.getString("password");
					fn = rs.getString("firstName");
					s = rs.getString("surname");
					al = rs.getString("alias");
					em = rs.getString("email");
					mob = rs.getInt("mobile");
					men = rs.getBoolean("mentor");
					
					msg += (id + " " + psw1 + " " + fn + " " + s + " " + al + " " + em + " " + mob + " " + men + "\n");
	        	}
	        	

	        	
	        } 
	        catch (SQLException e)
	        {
	        	e.printStackTrace();
	        }
	        return msg;
		}
			  
				
		public void closeDB()
		{
			  try
				{
					con.close();
				} 
				catch (SQLException e)
				{
					e.printStackTrace();
				}		
		}
		

		
		public static void main(String[] args)
		{
			try
			{
				new DAO();
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
