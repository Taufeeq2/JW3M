package jw3m.dao;
import java.util.*;

import org.apache.log4j.Logger;

import java.sql.Date;

import jw3m.beans.*;
import jw3m.client.gui.SkillsClient;

import java.sql.*;

public class DAO
{	
		private Connection con  = null;
		private Statement sqlstat = null;
		private PreparedStatement ps = null;
		private String url = "jdbc:mysql://localhost:3306/jw3m_project";
		private String user = "root";
		private String psw = "root";
		final static Logger logger = Logger.getLogger(SkillsClient.class);
		private String strPrefix = "DAO call to the Database for ";
		
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
						
			sqlstat = con.createStatement();
			
		}	
		
		
		// USERLIST Methods
		
		public Vector<User> getUserList()
		{
			logger.info(strPrefix + " getUserList()");
			Vector<User> userVect = new Vector<User>();
			try
			{
				ResultSet rs = sqlstat.executeQuery("Select * from users");
				
				while(rs.next())
				{
								
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
			logger.info(strPrefix + " addUserList()");
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
			logger.info(strPrefix + " removeUserList()");
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
			logger.info(strPrefix + " getUser()");
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
			logger.info(strPrefix + " editUser()");
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
			logger.info(strPrefix + " getSkillList()");
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
			logger.info(strPrefix + " addSkillList()");
			String skillName = inSkill.getSkillName();
			
			try
			{
				ResultSet rsCheck = sqlstat.executeQuery("SELECT * FROM skills");
				while (rsCheck.next())
				{
					if (skillName.equals(rsCheck.getString("skillName")))
					{
						return false;
					}
				}
				
				ps = con.prepareStatement("INSERT INTO skills VALUES (null, ?, ?, ?)");
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
			logger.info(strPrefix + " removeSkillList()");
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
		
			
		//USER SKILLS METHODS
		
		public Vector<UserSkill> getUserSkills(User inUser)
		{
			logger.info(strPrefix + " getUserSkill()");
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
			logger.info(strPrefix + " addUserSkill()");
			try
			{
				ResultSet rsCheck = sqlstat.executeQuery("SELECT * FROM UserSkills");
				while (rsCheck.next())
				{
					if ((inUserID.equals(rsCheck.getString("userID"))) && (inSkillID == rsCheck.getInt("skillID")))
					{
						return false;
					}
				}
				
				ps = con.prepareStatement("INSERT INTO userSkills VALUES(null, ?, ?)");
				ps.setString(1, inUserID);
				ps.setInt(2, inSkillID);
				
				ps.executeUpdate();
				
				return true;
				
			} catch (SQLException e)
			{
				
				e.printStackTrace();
				return false;
			}
			
		}
		
		public boolean removeUserSkills(String inUserID, int inSkillID)
		{
			logger.info(strPrefix + " removeUserSkills()");
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
			logger.info(strPrefix + " getHobbyList()");
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
			logger.info(strPrefix + " addHobbyList()");
			String hobbyName = inHobby.getHobbyName();
						
			try
			{
				ResultSet rsCheck = sqlstat.executeQuery("SELECT * FROM hobby");
				while (rsCheck.next())
				{
					if (hobbyName.equals(rsCheck.getString("hobbyName")))
					{
						return false;
					}
				}
				
				ps = con.prepareStatement("INSERT INTO hobby VALUES(null, ?)");
				
				ps.setString(1, hobbyName);
								
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
			logger.info(strPrefix + " removeHobby()");
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
			logger.info(strPrefix + " getUserHobby()");
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
			logger.info(strPrefix + " getUserHobby()");
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
			logger.info(strPrefix + " addUserHobby()");
			try
			{
				ResultSet rsCheck = sqlstat.executeQuery("SELECT * FROM Userhobbies");
				while (rsCheck.next())
				{
					if ((inUserID.equals(rsCheck.getString("userID"))) && (inHobbyID == rsCheck.getInt("hobbyID")))
					{
						System.out.println("Hobby for user exists");
						return false;
					}
				}
				
				ps = con.prepareStatement("INSERT INTO userHobbies VALUES(?, ?)");
				
				ps.setString(1, inUserID);
				ps.setInt(2, inHobbyID);
				
				ps.executeUpdate();
				System.out.println("Hobby added");
				return true;
			} catch (SQLException e)
			{
				e.printStackTrace();
				return false;
			}
			
		}
		
		public boolean removeUserHobby(UserHobby inUserHobby)
		{						
			logger.info(strPrefix + " removeUserHobby()");
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
			logger.info(strPrefix + " getLevel()");
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
			logger.info(strPrefix + " getLevel()");
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
			logger.info(strPrefix + " getRatings()");
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
			logger.info(strPrefix + " getRatings()");
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
			logger.info(strPrefix + " setRatings()");
			try
			{
				ResultSet rsCheck = sqlstat.executeQuery("SELECT * FROM ratings");
				while (rsCheck.next())
				{
					if ((inRating.getRaterID().equals(rsCheck.getString("raterID"))) && (inRating.getUserID().equals(rsCheck.getString("userID"))) && (inRating.getSkillID() == rsCheck.getInt("skillID")))	
					{
						ps = con.prepareStatement("UPDATE ratings SET level = ?, knowledge = ?, workStandard = ?, autonomy = ?, complexityCoping = ?, "
								+ "contextPerception = ?, capabilityGrowing = ?, collaboration = ?, date = now() WHERE ratingID = ?");
						
						ps.setInt(1, inRating.getLevel());
						ps.setInt(2, inRating.getKnowledge());
						ps.setInt(3, inRating.getWorkStandard());
						ps.setInt(4, inRating.getAutonomy());
						ps.setInt(5, inRating.getComplexityCoping());
						ps.setInt(6, inRating.getContextPerception());
						ps.setInt(7, inRating.getCapabilityGrowing());
						ps.setInt(8, inRating.getCollaboration());
						ps.setInt(9, rsCheck.getInt("ratingID"));
						
						ps.executeUpdate();					
						
						return true;
					}	
				}
				
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
			logger.info(strPrefix + " removeRatings()");
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
			logger.info(strPrefix + " getNotification()");
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
			logger.info(strPrefix + " setNotification()");
			String requesterID = inNotice.getRequestorID();
			String raterID = inNotice.getRatorID();
			Date date = inNotice.getDate();
			
			try
			{
				ResultSet rsCheck = sqlstat.executeQuery("SELECT * FROM notifications");
				while (rsCheck.next())
				{
					if ((requesterID.equals(rsCheck.getString("requesterID"))) && (raterID.equals(rsCheck.getString("raterID"))))
					{
						System.out.println("notification for user exists");
						return false;
					}
				}
				
				
				
				
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
			logger.info(strPrefix + " removeNotification()");
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
		

		
//		public static void main(String[] args)
//		{
//			try
//			{
//				new DAO();
//				
//			} catch (Exception e)
//			{
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
}
