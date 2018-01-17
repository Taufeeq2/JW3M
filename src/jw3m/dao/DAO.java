package jw3m.dao;
import java.util.*;
import jw3m.beans.*;
import java.sql.*;

public class DAO
{	
		private Connection con  = null;
		private Statement sqlstat = null;
		private PreparedStatement ps = null;
		private String url = "jdbc:mysql://localhost:3306/jw3m_project";
		private String user = "root";
		private String psw = "root";
		private ResultSet rs = null;
		private String msg = "";
		private Vector<User> userVect = null;
		private Vector<UserSkill> userSkillVect = null;
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
//			this.getUserList();


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
				}
			} 
			catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return userVect;
		}
		
		public boolean addUserList(User inUser)
		{
			String userName = inUser.getUserName();
			String password = inUser.getPassword();
			String firstName = inUser.getFirstName();
			String surname = inUser.getSurname();
			String alias = inUser.getAlias();
			String email = inUser.getEmailAddress();
			int mobile = inUser.getMobile();
			boolean mentor = inUser.isMentor();
			
			try
			{
				ps = con.prepareStatement("INSERT INTO users VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
				
				ps.setString(1, userName);
				ps.setString(2, password);
				ps.setString(3, firstName);
				ps.setString(4, surname);
				ps.setString(5, alias);
				ps.setString(6, email);
				ps.setInt(7, mobile);
				ps.setBoolean(8, mentor);
				
				ps.executeUpdate();
				
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return true;
		}
		
		public boolean removeUserList(String inUserID)
		{
			try
			{
				ps = con.prepareStatement("DELETE FROM users WHERE userID = ?");
				
				ps.setString(1, inUserID);
							
				ps.executeUpdate();
				
			} catch (SQLException e)
			{
				e.printStackTrace();
				return false;
			}
			
			return true;
		}
		
		public User getUser(String inUserName)
		{
			
	        try
	        {
	        	ps = con.prepareStatement("Select * from users where userID = ?");
	        	ps.setString(1, inUserName);
	        	rs = ps.executeQuery();
	        
	        	if(!rs.isBeforeFirst())
	        	{
	        		id = rs.getString("userID");
					psw1 = rs.getString("password");
					fn = rs.getString("firstName");
					s = rs.getString("surname");
					al = rs.getString("alias");
					em = rs.getString("email");
					mob = rs.getInt("mobile");
					men = rs.getBoolean("mentor");
					
					User tempUser = new User();
					tempUser.setUserName(id);
					tempUser.setPassword(psw1);
					tempUser.setFirstName(fn);
					tempUser.setSurname(s);
					tempUser.setAlias(al);
					tempUser.setEmailAddress(em);
					tempUser.setMobile(mob);
					tempUser.setMentor(men);
					
					return tempUser;
	        	}
	        	else
	        	{
	        		return null;
	        	}
	        	

	        	
	        } 
	        catch (SQLException e)
	        {
	        	e.printStackTrace();
	        	return null;
	        }
	       
		}
			  
		public Vector<UserSkill> getUserSkills(User inUser)
		{
			String userName = inUser.getUserName();
			String password = inUser.getPassword();
			String firstName = inUser.getFirstName();
			String surname = inUser.getSurname();
			String alias = inUser.getAlias();
			String email = inUser.getEmailAddress();
			int mobile = inUser.getMobile();
			boolean mentor = inUser.isMentor();
			
			try
			{
				ps = con.prepareStatement("SELECT * FROM userSkills where userID = ?");
				
				ps.setString(1, userName);
								
				rs = ps.executeQuery();
				
				while (rs.next())
				{
					int userSkillID = rs.getInt("userSkillID");
					String userID = rs.getString("userID");
					int skillID = rs.getInt("skillID");
					
					UserSkill tempUserSkill = new UserSkill();
					tempUserSkill.setSkillID(userSkillID);
					tempUserSkill.setUserID(userID);
					tempUserSkill.setSkillID(skillID);
					
					userSkillVect.add(tempUserSkill);
				}
							
								
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return userSkillVect;
		}
		
		public boolean addUserSkills(String inUserID, int inSkillID)
		{
			try
			{
				ps = con.prepareStatement("INSERT INTO userSkills VALUES(null, ?, ?)");
				ps.setString(1, inUserID);
				ps.setInt(1, inSkillID);
				
				ps.executeUpdate();
				
				
				
			} catch (SQLException e)
			{
				
				e.printStackTrace();
				return false;
			}
			return true;
		}
		
		public boolean removeUserSkills(String inUserID, int inSkillID)
		{
			try
			{
				ps = con.prepareStatement("DELETE FROM userSkills WHERE userID = ? AND skillID = ?");
				ps.setString(1, inUserID);
				ps.setInt(1, inSkillID);
				
				ps.executeUpdate();
				
				
				
			} catch (SQLException e)
			{
				
				e.printStackTrace();
				return false;
			}
			return true;
		}
		
		//HOBBY Methods
		
		public Vector<Hobby> getHobby()
		{
//			try
//			{
//				ps = con.prepareStatement("SELECT * FROM hobby");
//								
//				rs = ps.executeQuery();
//				
//				while (rs.next())
//				{
//					int userSkillID = rs.getInt("userSkillID");
//					String userID = rs.getString("userID");
//					int skillID = rs.getInt("skillID");
//					
//					UserSkill tempUserSkill = new UserSkill();
//					tempUserSkill.setSkillID(userSkillID);
//					tempUserSkill.setUserID(userID);
//					tempUserSkill.setSkillID(skillID);
//					
//					userSkillVect.add(tempUserSkill);
//				}
//							
//								
//			} catch (SQLException e)
//			{
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			return userSkillVect;
			return null;
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
