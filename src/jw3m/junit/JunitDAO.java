package jw3m.junit;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.Vector;

import org.junit.Test;

import jw3m.beans.Hobby;
import jw3m.beans.Level;
import jw3m.beans.Notification;
import jw3m.beans.Rating;
import jw3m.beans.Skill;
import jw3m.beans.User;
import jw3m.beans.UserHobby;
import jw3m.beans.UserSkill;
import jw3m.dao.DAO;

public class JunitDAO
{
	
	 private DAO dao = null;
	 private User user = null;
	 private User newUser = null;
	 private Level level = null;
	 private Rating rating = null;
	 private Rating newRating = null;
	 private UserSkill userSkill = null;
	 private Hobby hobby = null;
	 private Hobby newHobby = null;
	 private Notification notification = null;
	 private UserHobby userHobby = null;
	 private Skill skill = null;
	 private Vector<UserSkill> userSkillList = null;
	 private Vector<User> userList = null;
	 private Vector<Hobby> hobbyList = null;
	 private Vector<UserHobby> userHobbyList = null;
	 private Vector<Level> levelList = null;
	 private Vector<Rating> ratingList = null;
	 private Vector<Notification> notificationList = null;

	@SuppressWarnings("deprecation")
	@Test
	public void test()
	{
		 try
		 {
			dao = new DAO();
		 }
		 catch (Exception e)
		 {
			// TODO Auto-generated catch block
			 System.out.println("Problem instantiating the DAO");
			e.printStackTrace();
		 }
		 System.out.println("Testing method getUser");
		 //positive test
		 user = dao.getUser("a126317");
		 if (user == null)
		 {
			 System.out.println("User a126317 = null");
		 }
		 else
		 {
			 String email = user.getEmailAddress();
			 System.out.println("Email a126317 = " + email);
			 assertTrue(email.equals("michaelf@dinersclub.co.za"));
			 
		  }
		 
		 //negative test
		 user = dao.getUser("a126318");
		 if (user == null)
		 {
			 System.out.println("User a126318 = null");
		 }
		 else
		 {
			 String email = user.getEmailAddress();
			 System.out.println("Email a126318= " + email);
			 
		  }
		 
		 System.out.println("Testing method getUserList");
		 userList = dao.getUserList();
		 int listSize = userList.size();
		 System.out.println("the userList has " + listSize + " entries");
		 assertTrue(listSize == 5);
		 
		 System.out.println("Testing the method addUserList");
		 newUser = new User();
		 newUser.setUserName("a1234567");
		 newUser.setPassword("1234567");
		 newUser.setFirstName("firstName");
		 newUser.setSurname("Surname");
		 newUser.setAlias("Alias");
		 newUser.setEmailAddress("emailAddress");
		 newUser.setMobile(113588517);
		 newUser.setMentor(true);
		 dao.addUserList(newUser);
		 System.out.println("User a1234567 has been added");
		 userList = dao.getUserList();
		 listSize = userList.size();
		 System.out.println("the userList has " + listSize + " entries");
		 assertTrue(listSize == 6);
		 
		 System.out.println("Testing the method removeUserList");
		 dao.removeUserList("a1234567");
		 System.out.println("User a1234567 has been removed");
		 userList = dao.getUserList();
		 listSize = userList.size();
		 System.out.println("the userList has " + listSize + " entries");
		 assertTrue(listSize == 5);
		 
		 System.out.println("Testing the method getUserSkills");
		 user = dao.getUser("a126317");
		 userSkillList = dao.getUserSkills(user);
		 int numberOfSkills = userSkillList.size();
		 System.out.println("User a126317 has " + numberOfSkills + " skills");
		 userSkill = new UserSkill();
		 assertTrue(numberOfSkills == 3);
		 for (int x=0; x<numberOfSkills; x++)
		 {
			 userSkill = userSkillList.get(x);
			 System.out.println("Skill number = " + userSkill.getSkillID());	 
		 }
		 
		 System.out.println("Testing method addUserSkills");
		 dao.addUserSkills("a126317", 999);
		 userSkillList = dao.getUserSkills(user);
		 numberOfSkills = userSkillList.size();
		 System.out.println("User a126317 has " + numberOfSkills + " skills");
		 userSkill = new UserSkill();
		 for (int x=0; x<numberOfSkills; x++)
		 {
			 userSkill = userSkillList.get(x);
			 System.out.println("Skill number = " + userSkill.getSkillID());	 
		 }
		 
		 System.out.println("Testing method removeUserSkills");
		 dao.removeUserSkills("a126317", 999);
		 userSkillList = dao.getUserSkills(user);
		 numberOfSkills = userSkillList.size();
		 System.out.println("User a126317 has " + numberOfSkills + " skills");
		 userSkill = new UserSkill();
		 for (int x=0; x<numberOfSkills; x++)
		 {
			 userSkill = userSkillList.get(x);
			 System.out.println("Skill number = " + userSkill.getSkillID());	 
		 }
		 
		 
		 System.out.println("Testing method getHobby");
		 hobbyList = dao.getHobbyList();
		 int numberOfHobbies = hobbyList.size();
		 System.out.println("Number of hobbies = " + numberOfHobbies);
		 hobby = new Hobby();
		 for (int x=0; x<numberOfHobbies; x++)
		 {
			 hobby = hobbyList.get(x);
			 System.out.println("Hobby ID = " + hobby.getHobbyID() + " Hobby Name = " + hobby.getHobbyName());
		 }
		 
		// System.out.println("Testing method addHobby");
		// newHobby = new Hobby();
		// newHobby.setHobbyID(999);
		// newHobby.setHobbyName("Testing adding a hobby");
		// dao.addHobby(newHobby);
		// hobbyList = dao.getHobbyList();
		// numberOfHobbies = hobbyList.size();
		// System.out.println("Number of hobbies = " + numberOfHobbies);
		 //hobby = new Hobby();
		 //for (int x=0; x<numberOfHobbies; x++)
		// {
		//	 hobby = hobbyList.get(x);
		//	 System.out.println("Hobby ID = " + hobby.getHobbyID() + " Hobby Name = " + hobby.getHobbyName());
		 //}
		 
		 System.out.println("Testing method removeHobby");
		 newHobby = new Hobby();
		 newHobby.setHobbyID(999);
		 newHobby.setHobbyName("Testing adding a hobby");
		 dao.removeHobby(newHobby);
		 hobbyList = dao.getHobbyList();
		 numberOfHobbies = hobbyList.size();
		 System.out.println("Number of hobbies = " + numberOfHobbies);
		 hobby = new Hobby();
		 for (int x=0; x<numberOfHobbies; x++)
		 {
			 hobby = hobbyList.get(x);
			 System.out.println("Hobby ID = " + hobby.getHobbyID() + " Hobby Name = " + hobby.getHobbyName());
		 }
		 
		 System.out.println("Testing method getUserHobby");
		 user = dao.getUser("a126317");
		 userHobbyList = dao.getUserHobby(user);
		 numberOfHobbies = userHobbyList.size();
		 System.out.println("Number of hobbies for user a126317 = " + numberOfHobbies);
		 userHobby = new UserHobby();
		 for (int x=0; x<numberOfHobbies; x++)
		 {
			 userHobby = userHobbyList.get(x);
			 System.out.println("User ID = " + userHobby.getUserID() + " Hobby ID = " + userHobby.getHobbyID());
		 }
		 
		 System.out.println("Testing method getUserHobby passing Hobby to get Vector<User> back");
		 newHobby = new Hobby();
		 newHobby.setHobbyID(1);
		 newHobby.setHobbyName("Chess");
		 userList = dao.getUserHobby(newHobby);
		 int numberOfUsers = userList.size();
		 System.out.println("Number of users with hobby Chess = " + numberOfUsers);
		 for (int x=0; x<numberOfUsers; x++)
		 {
			 user = userList.get(x);
			 System.out.println("User who has chess as a hobby = " + user.getFirstName());
		 }
		 
		 System.out.println("Testing method addUserHobby");
		 userHobby = new UserHobby();
		 userHobby.setUserID("a124788");
		 userHobby.setHobbyID(1);
		
		 // this may need to be checked again
		 dao.addUserHobby("a124788", 1);
	//	 dao.addUserHobby(userHobby);
		 
		 
		 System.out.println("After adding chess as a hobby for a124788");
		 userList = dao.getUserHobby(newHobby);
		 numberOfUsers = userList.size();
		 System.out.println("Number of users with hobby Chess = " + numberOfUsers);
		 for (int x=0; x<numberOfUsers; x++)
		 {
			 user = userList.get(x);
			 System.out.println("User who has chess as a hobby = " + user.getFirstName());
		 }
		 
		 System.out.println("Testing method removeUserHobby");
		 userHobby = new UserHobby();
		 userHobby.setUserID("a124788");
		 userHobby.setHobbyID(1);
		 dao.removeUserHobby(userHobby);
		 System.out.println("After removing chess as a hobby for a124788");
		 userList = dao.getUserHobby(newHobby);
		 numberOfUsers = userList.size();
		 System.out.println("Number of users with hobby Chess = " + numberOfUsers);
		 for (int x=0; x<numberOfUsers; x++)
		 {
			 user = userList.get(x);
			 System.out.println("User who has chess as a hobby = " + user.getFirstName());
		 }
		 
		 System.out.println("Testing method getLevel");
		 levelList = new Vector<Level>();
		 levelList = dao.getLevel();
		 int numberOfLevels = levelList.size();
		 System.out.println("number of levels = " + numberOfLevels);
		 level = new Level();
		 for (int x=0; x< numberOfLevels; x++)
		 {
			 level = levelList.get(x);
			 System.out.println("Level = " + level.getLevel() + " Description = " + level.getDescription());
		 }
		 
		 System.out.println("Testing the second getLevel method");
		 for (int x=1; x<6; x++)
		 {
			 System.out.println("level = " + x + " description = " + dao.getLevel(x));
		 }
		 
		 System.out.println("Testing method getRatings");
		 ratingList = new Vector<Rating>();
		 user = dao.getUser("a126317");
		 ratingList = dao.getRatings(user);
		 rating = new Rating();
		 for (int x=0; x<ratingList.size(); x++)
		 {
			 rating = ratingList.get(x);
			 System.out.println("Rating number " + (x+1));
			 System.out.println("Rating for a126317, user ID = " + rating.getUserID() );
			 System.out.println("Rating for a126317, rater ID = " + rating.getRaterID());
			 System.out.println("Rating for a126317, skill ID = " + rating.getSkillID());
			 System.out.println("Rating for a126317, autonomy  = " + rating.getAutonomy());
			 System.out.println("Rating for a126317, capability growing = " + rating.getCapabilityGrowing());
			 System.out.println("Rating for a126317, collaboration = " + rating.getCollaboration());
			 System.out.println("Rating for a126317, complexity coping = " + rating.getComplexityCoping());
			 System.out.println("Rating for a126317, context perception = " + rating.getContextPerception());
			 System.out.println("Rating for a126317, knowledge = " + rating.getKnowledge());
			 System.out.println("Rating for a126317, level = " + rating.getLevel());
			 System.out.println("Rating for a126317, rating ID = " + rating.getRatingID());
			 System.out.println("Rating for a126317, work standard = " + rating.getWorkStandard());
			 System.out.println("Rating for a126317, date = " + rating.getDate());
		 }
		 
		 
		 
		
		 
		 System.out.println("Testing method setRating");
		 newRating = new Rating();
		 newRating.setAutonomy(9);
		 newRating.setCapabilityGrowing(9);
		 newRating.setCollaboration(9);
		 newRating.setComplexityCoping(9);
		 newRating.setContextPerception(9);
		 newRating.setDate(null);
		 newRating.setKnowledge(9);
		 newRating.setKnowledge(9);
		 newRating.setLevel(9);
		 newRating.setRaterID("a1234567");
		 newRating.setRatingID(9);
		 newRating.setSkillID(9);
		 newRating.setUserID("a126317");
		 newRating.setWorkStandard(9);
		 dao.setRating(newRating);
		 
		 System.out.println("testing method removeRating");
		 skill = new Skill();
		 skill.setSkillID(9);
		 user = new User();
		 user.setUserName("a126317");
		 User rator = new User();
		 rator.setUserName("a1234567");
		 dao.removeRating(user, rator, skill);
		 
		 System.out.println("Testing method getNotification");
		 notificationList = new Vector<Notification>();
		 user = dao.getUser("a126317");
		 notificationList = dao.getNotification(user);
		 System.out.println("Number of notifications for a126317 = " + notificationList.size());
		 notification = new Notification();
		 for (int x=0; x<notificationList.size(); x++)	 
		 {
			 notification = notificationList.get(x);
			 System.out.println("Notification " + (x+1));
			 System.out.println("Rator ID = " + notification.getRatorID());
			 System.out.println("Requestor ID = " + notification.getRequestorID());
			 System.out.println("Notice ID = " + notification.getNoticeID());
			 System.out.println("Date = " + notification.getDate());
		 }
		 
		 System.out.println("Testing method setNotification");
		 Notification addNotification = new Notification();
		 addNotification.setRequestorID("a999999");
		 addNotification.setRatorID("a111111");
		 
		 java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
		 
		 //Date date = new Date(20180101); //this date is still wonky
		 
		 addNotification.setDate(date); 
		 dao.setNotification(addNotification);
		 
		 
		 System.out.println("Testing method removeNotification");
		 Notification removeNotification = new Notification();
		 removeNotification.setRequestorID("a999999");
		 removeNotification.setRatorID("a111111");
		 Date rmvDate = new Date(20180101);
		 removeNotification.setDate(rmvDate);
		 
		 // this should remove notification by ID
		 dao.removeNotification(removeNotification);
		 
	}

}
