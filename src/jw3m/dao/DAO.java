package jw3m.dao;
import java.util.*;
import java.sql.Date;

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
//		private ResultSet rs = null;
//		private String msg = "";
//		private Vector<User> userVect = null;
//		private Vector<UserSkill> userSkillVect = null;
//		private Vector<Hobby> hobbyVect = null;
//		private Vector<UserHobby> userHobbyVect = null;
	//	private User user1 = null;
//		private String id, fn, s, psw1, al, em;
//		private int mob = 0;
//		private boolean men = true;
		
		
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
			
			// Testing methods area
			
//			Vector<Hobby> j = this.getHobbyList();
//			Vector<User> j = this.getUserHobby(1);
//			Vector<UserHobby> j = this.getUserHobby("a126317");
//			Vector<Rating> j = this.getRatings("a205128");
//			System.out.println(j.size());
//			for (int i = 0; i < j.size(); i++)
//			{
//			System.out.println(this.getLevel(3));
//			}
//			for (int i = 0; i < j.size(); i++)
//			{
//				System.out.println(j.get(i).getHobbyName());
//			}

		}	
		
		
		// USERLIST Methods
		
		public Vector<User> getUserList()
		{
			Vector<User> userVect = new Vector<User>();
			try
			{
				ResultSet rs = sqlstat.executeQuery("Select * from users");
				
				while(rs.next())
				{
//					id = rs.getString("userID");
//					psw1 = rs.getString("password");
//					fn = rs.getString("firstName");
//					s = rs.getString("surname");
//					al = rs.getString("alias");
//					em = rs.getString("email");
//					mob = rs.getInt("mobile");
//					men = rs.getBoolean("mentor");
					
								
					User tempUser = new User();
					
					tempUser.setUserName(rs.getString("userID"));
					tempUser.setPassword(rs.getString("password"));
					tempUser.setFirstName(rs.getString("firstName"));
					tempUser.setSurname(rs.getString("surname"));
					tempUser.setAlias(rs.getString("alias"));
					tempUser.setEmailAddress(rs.getString("email"));
					tempUser.setMobile(rs.getInt("mobile"));
					tempUser.setMentor(rs.getBoolean("mentor"));
					
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
			User tempUser = new User();
			try
			{
				ps = con.prepareStatement("Select * from users where userID = ?");
				ps.setString(1, inUserName);
				ResultSet rs = ps.executeQuery();

				if(rs.isBeforeFirst())
				{
					while(rs.next())
					{

//						id = rs.getString("userID");
//						psw1 = rs.getString("password");
//						fn = rs.getString("firstName");
//						s = rs.getString("surname");
//						al = rs.getString("alias");
//						em = rs.getString("email");
//						mob = rs.getInt("mobile");
//						men = rs.getBoolean("mentor");


						tempUser.setUserName(rs.getString("userID"));
						tempUser.setPassword(rs.getString("password"));
						tempUser.setFirstName(rs.getString("firstName"));
						tempUser.setSurname(rs.getString("surname"));
						tempUser.setAlias(rs.getString("alias"));
						tempUser.setEmailAddress(rs.getString("email"));
						tempUser.setMobile(rs.getInt("mobile"));
						tempUser.setMentor(rs.getBoolean("mentor"));

		        	}
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
		
		public User editUser(User inUser)
		{
			try
			{

				ps = con.prepareStatement("UPDATE users SET password = ?, firstName = ?, surname = ?, alias = ?, email = ?, mobile = ?, mentor = ? WHERE userID = ?");

				ps.setString(1, inUser.getPassword());
				ps.setString(2, inUser.getFirstName());
				ps.setString(3, inUser.getSurname());
				ps.setString(4, inUser.getAlias());
				ps.setString(5, inUser.getEmailAddress());
				ps.setInt(6, inUser.getMobile());
				ps.setBoolean(7, inUser.isMentor());
				ps.setString(8, inUser.getUserName());
				
				ps.executeUpdate();
				
				return getUser(inUser.getUserName());
				
				
			} catch (SQLException e)
			{
				e.printStackTrace();
				return null;
			}
			
			
			
			
		}
		
		//SKILLS METHODS
		public Vector<Skill> getSkillList()
		{
			Vector<Skill> skillVect = new Vector<Skill>();
			
			
			try
			{
				ps = con.prepareStatement("SELECT * FROM skills");
				ResultSet rs = ps.executeQuery();
				
				while (rs.next())
				{
					Skill tempSkill = new Skill();
					tempSkill.setSkillID(rs.getInt("skillID"));
					tempSkill.setSkillName(rs.getString("skillName"));
					tempSkill.setSkillVendor(rs.getString("skillVendor"));
					tempSkill.setSkillDescription(rs.getString("skillDescription"));
					
					skillVect.add(tempSkill);					
				}
				
				return skillVect;
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			
			
		}
		
		public boolean addSkillList(Skill inSkill)
		{
			
			try
			{
				ps = con.prepareStatement("INSERT INTO skills VALUES (null, ?', ?, ?)");
				ps.setString(1, inSkill.getSkillName());
				ps.setString(2, inSkill.getSkillVendor());
				ps.setString(3, inSkill.getSkillDescription());
				
				ps.executeUpdate();
				
				return true;
			} catch (SQLException e)
			{
				e.printStackTrace();
				return false;
			}
		}
		
		public boolean removeSkillList(Skill inSkill)
		{
			try
			{
				ps = con.prepareStatement("DELETE FROM skills WHERE skillID = ?");
				ps.setInt(1, inSkill.getSkillID());
				ps.executeUpdate();
				
				return true;
				
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}			
		}
		
		public Vector<SkillName> getSkillName(int skillID)
		{
			Vector<SkillName> skillVect = new Vector<SkillName>();

			try
			{
				ps = con.prepareStatement(
						"SELECT skillName FROM skills where skillID = ?");
				ResultSet rs = ps.executeQuery();
				rs = ps.executeQuery();

				while (rs.next())
				{
					SkillName tempSkill = new SkillName();
					tempSkill.setSkillName(rs.getString("skillName"));
					skillVect.addElement(tempSkill);
				}

				return skillVect;
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}

		}
		
		//USER SKILLS METHODS
		
		public Vector<UserSkill> getUserSkills(User inUser)
		{
			Vector<UserSkill> userSkillVect = new Vector<UserSkill>();
			String userName = inUser.getUserName();
			
			try
			{
				ps = con.prepareStatement("SELECT * FROM userSkills where userID = ?");
				
				ps.setString(1, userName);
								
				ResultSet rs = ps.executeQuery();
				
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
				ps.setInt(2, inSkillID);
				
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
				ps.setInt(2, inSkillID);
				
				ps.executeUpdate();
				
				
				
			} catch (SQLException e)
			{
				
				e.printStackTrace();
				return false;
			}
			return true;
		}
		
		//HOBBY Methods
		
		public Vector<Hobby> getHobbyList()
		{
			Vector<Hobby> hobbyVect = new Vector<Hobby>();
			
			try
			{
				ps = con.prepareStatement("SELECT * FROM hobby");
								
				ResultSet rs = ps.executeQuery();
				
				while (rs.next())
				{
					Hobby tempHobby = new Hobby();
					int hobbyID = rs.getInt("hobbyID");
					String hobbyName = rs.getString("hobbyName");
										
					tempHobby.setHobbyID(hobbyID);
					tempHobby.setHobbyName(hobbyName);
					
					hobbyVect.add(tempHobby);
				}
							
								
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return hobbyVect;
		}
		
		public boolean addHobbyList(Hobby inHobby)
		{
			int hobbyID = inHobby.getHobbyID();
			String hobbyName = inHobby.getHobbyName();
						
			try
			{
				ps = con.prepareStatement("INSERT INTO hobby VALUES(?, ?)");
				
				ps.setInt(1, hobbyID);
				ps.setString(2, hobbyName);
								
				ps.executeUpdate();
				
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
		
		public boolean removeHobby(Hobby inHobby)
		{
			int hobbyID = inHobby.getHobbyID();
						
			try
			{
				ps = con.prepareStatement("DELETE FROM hobby WHERE hobbyID = ?");
				
				ps.setInt(1, hobbyID);
							
				ps.executeUpdate();
				
			} catch (SQLException e)
			{
				e.printStackTrace();
				return false;
			}
			
			return true;
		}
		
		//USER HOBBY Methods
		
		public Vector<UserHobby> getUserHobby(User inUser)
		{
			Vector<UserHobby> userHobbyVect = new Vector<UserHobby>();
			
			String userName = inUser.getUserName();
			try
			{
				ps = con.prepareStatement("SELECT * FROM userHobbies WHERE userID = ?");
				ps.setString(1, userName);
				
				ResultSet rs = ps.executeQuery();
				
				while (rs.next())
				{
					UserHobby tempUserHobby = new UserHobby();
					String userID = rs.getString("userID");
					int hobbyID = rs.getInt("hobbyID");
										
					tempUserHobby.setUserID(userID);
					tempUserHobby.setHobbyID(hobbyID);
					
					userHobbyVect.add(tempUserHobby);
				}
							
								
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return userHobbyVect;
		}
		
		public Vector<User> getUserHobby(Hobby inHobby)
		{
			Vector<User> userVect = new Vector<User>();
			ResultSet rs1 = null;
			int inHobbyID = inHobby.getHobbyID();
			try
			{
				ps = con.prepareStatement("SELECT * FROM userHobbies WHERE hobbyID = ?");
				ps.setInt(1, inHobbyID);
				
				rs1 = ps.executeQuery();
				
				User tempUser = null;
				
		
				while (rs1.next())
				{
					
					String userID = rs1.getString("userID");
					tempUser = this.getUser(userID);
					userVect.add(tempUser);
									
				}
							
								
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return userVect;
		}
		
		public boolean addUserHobby(String inUserID , int inHobbyID)
		{
//			String userID = inUserHobby.getUserID();
//			int hobbyID = inUserHobby.getHobbyID();
			
			try
			{
				ps = con.prepareStatement("INSERT INTO userHobbies VALUES(?, ?)");
				
				ps.setString(1, inUserID);
				ps.setInt(2, inHobbyID);
				
				ps.executeUpdate();
				return true;
			} catch (SQLException e)
			{
				e.printStackTrace();
				return false;
			}
			
		}
		
		public boolean removeUserHobby(UserHobby inUserHobby)
		{						
			try
			{
				String userID = inUserHobby.getUserID();
				int hobbyID = inUserHobby.getHobbyID();
				
				ps = con.prepareStatement("DELETE FROM userHobbies WHERE userID = ? AND hobbyID = ?");
				
				ps.setString(1, userID);
				ps.setInt(2, hobbyID);
							
				ps.executeUpdate();
				
				return true;
			} catch (SQLException e)
			{
				e.printStackTrace();
				return false;
			}
			
			
		}
		
		
		
		public Vector<Level> getLevel()
		{
			
			Vector<Level> levelVect = new Vector<Level>();
			try
			{
				ps = con.prepareStatement("SELECT * FROM level");
				ResultSet rs = ps.executeQuery();
				
				while (rs.next())
				{
					Level tempLevel = new Level();
					int levelID = rs.getInt("level");
					String levelDesc = rs.getString("description");
					
					tempLevel.setLevel(levelID);
					tempLevel.setDescription(levelDesc);
					
					levelVect.add(tempLevel);
				}
				return levelVect;
				
			} catch (SQLException e)
			{
				
				e.printStackTrace();
				return null;
			}
		}
		
		public String getLevel(int inLevel)
		{
			try
			{
				ps = con.prepareStatement("SELECT description FROM level WHERE level = ?");
				ps.setInt(1, inLevel);
				
				ResultSet rs = ps.executeQuery();
				rs.next();
				String desc = rs.getString("description");
				
				return desc;
			} catch (SQLException e)
			{
				e.printStackTrace();
				return null;
			}
		}
		
		//RATING METHODS
		public Rating getRating(User inUser, Skill inSkill)
		{
			Rating tempRating = new Rating();
			
			
			
			
			
			return tempRating;
		}
		
		public Vector<Rating> getRatings(User inUser)
		{
			Vector<Rating> ratingVect = new Vector<Rating>();
			
			String userName = inUser.getUserName();
		//	System.out.println("UserName: " + userName);
			try
			{
				ps = con.prepareStatement("SELECT * FROM ratings WHERE userID = ?");
				ps.setString(1, userName);
				
				ResultSet rs = ps.executeQuery();
				while (rs.next())
				{
					Rating tempRating = new Rating();
					tempRating.setRatingID(rs.getInt("ratingID"));
					tempRating.setRaterID(rs.getString("raterID"));
					tempRating.setUserID(rs.getString("userID"));
					tempRating.setSkillID(rs.getInt("skillID"));
					tempRating.setLevel(rs.getInt("level"));
					tempRating.setKnowledge(rs.getInt("knowledge"));
					tempRating.setWorkStandard(rs.getInt("workStandard"));
					tempRating.setAutonomy(rs.getInt("autonomy"));
					tempRating.setComplexityCoping(rs.getInt("complexityCoping"));
					tempRating.setContextPerception(rs.getInt("contextPerception"));
					tempRating.setCapabilityGrowing(rs.getInt("capabilityGrowing"));
					tempRating.setCollaboration(rs.getInt("collaboration"));
					tempRating.setDate(rs.getDate("date"));
					
					ratingVect.add(tempRating);
				}
				return ratingVect;
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		
		//added by Mike to use in PanelReporting
		public Vector<Rating> getRatings(Skill inSkill)
		{
			Vector<Rating> ratingVect = new Vector<Rating>();
			
			int skillID = inSkill.getSkillID();
			
			try
			{
				ps = con.prepareStatement("SELECT * FROM ratings WHERE skillID = ?");
				ps.setInt(1, skillID);
				
				ResultSet rs = ps.executeQuery();
				while (rs.next())
				{
					Rating tempRating = new Rating();
					tempRating.setRatingID(rs.getInt("ratingID"));
					tempRating.setRaterID(rs.getString("raterID"));
					tempRating.setUserID(rs.getString("userID"));
					tempRating.setSkillID(rs.getInt("skillID"));
					tempRating.setLevel(rs.getInt("level"));
					tempRating.setKnowledge(rs.getInt("knowledge"));
					tempRating.setWorkStandard(rs.getInt("workStandard"));
					tempRating.setAutonomy(rs.getInt("autonomy"));
					tempRating.setComplexityCoping(rs.getInt("complexityCoping"));
					tempRating.setContextPerception(rs.getInt("contextPerception"));
					tempRating.setCapabilityGrowing(rs.getInt("capabilityGrowing"));
					tempRating.setCollaboration(rs.getInt("collaboration"));
					tempRating.setDate(rs.getDate("date"));
					
					ratingVect.add(tempRating);
				}
				return ratingVect;
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		
		public boolean setRating(Rating inRating)
		{
			try
			{
				ps = con.prepareStatement("INSERT INTO ratings VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW() )");
				ps.setString(1, inRating.getRaterID());
				ps.setString(2, inRating.getUserID());
				ps.setInt(3, inRating.getSkillID());
				ps.setInt(4, inRating.getLevel());
				ps.setInt(5, inRating.getKnowledge());
				ps.setInt(6, inRating.getWorkStandard());
				ps.setInt(7, inRating.getAutonomy());
				ps.setInt(8, inRating.getComplexityCoping());
				ps.setInt(9, inRating.getContextPerception());
				ps.setInt(10, inRating.getCapabilityGrowing());
				ps.setInt(11, inRating.getCollaboration());
				
				ps.executeUpdate();
				
				return true;
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		
		public boolean removeRating(User inUser, User inRator, Skill inSkill)
		{
			String userName = inUser.getUserName();
			String ratorID = inRator.getUserName();
			int SkillID = inSkill.getSkillID();
			
			try
			{
				ps = con.prepareStatement("DELETE FROM ratings WHERE raterID = ? AND userID = ? AND skillID = ?");
				ps.setString(1, ratorID);
				ps.setString(2, userName);
				ps.setInt(3, SkillID);
				
				ps.executeUpdate();
				
				return true;
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		
		
		
		//Notification Methods
		public Vector<Notification> getNotification(User inUser)
		{
			String userName = inUser.getUserName();
			
			Vector<Notification> notificationVect = new Vector<Notification>();
			try
			{
				ps = con.prepareStatement("SELECT * FROM notifications WHERE raterID = ?");
				
				ps.setString(1, userName);
				
				ResultSet rs = ps.executeQuery();
				while(rs.next())
				{
					Notification tempNotification = new Notification();
					int noticeID = rs.getInt("noticeID");
					String requestID = rs.getString("requesterID");
					String raterID = rs.getString("raterID");
					Date date = rs.getDate("date");
					
					tempNotification.setNoticeID(noticeID);
					tempNotification.setRequestorID(requestID);
					tempNotification.setRatorID(raterID);
					tempNotification.setDate(date);
					
					notificationVect.add(tempNotification);
				}
				return notificationVect;
				
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			
		}
		
		public boolean setNotification(Notification inNotice)
		{
			String requesterID = inNotice.getRequestorID();
			String raterID = inNotice.getRatorID();
			Date date = inNotice.getDate();
			
			try
			{
				ps = con.prepareStatement("INSERT INTO notifications VALUES(null, ?, ?, ?)");
				
				ps.setString(1, requesterID);
				ps.setString(2, raterID);
				ps.setDate(3, date);
				
				ps.executeUpdate();
				
				return true;
				
				
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}

		public boolean removeNotification(Notification inNotice)
		{
			int noticeID = inNotice.getNoticeID();
			String requesterID = inNotice.getRequestorID();
			String raterID = inNotice.getRatorID();
			Date date = inNotice.getDate();
			
			try
			{
				ps = con.prepareStatement("DELETE FROM notifications WHERE requesterID = ? AND raterID = ?");
				
				ps.setString(1, requesterID);
				ps.setString(2, raterID);
//				ps.setDate(3, date);
				
				ps.executeUpdate();
				
				return true;
				
				
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
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
