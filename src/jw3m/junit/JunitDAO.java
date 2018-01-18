package jw3m.junit;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Test;

import jw3m.beans.Hobby;
import jw3m.beans.User;
import jw3m.beans.UserSkill;
import jw3m.dao.DAO;

public class JunitDAO
{
	
	 private DAO dao = null;
	 private User user = null;
	 private User newUser = null;
	 private UserSkill userSkill = null;
	 private Hobby hobby = null;
	 private Hobby newHobby = null;
	 private Vector<UserSkill> userSkillList = null;
	 private Vector<User> userList = null;
	 private Vector<Hobby> hobbyList = null;

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
		 
		 System.out.println("Testing the method removeUserList");
		 dao.removeUserList("a1234567");
		 System.out.println("User a1234567 has been removed");
		 userList = dao.getUserList();
		 listSize = userList.size();
		 System.out.println("the userList has " + listSize + " entries");
		 
		 System.out.println("Testing the method getUserSkills");
		 user = dao.getUser("a126317");
		 userSkillList = dao.getUserSkills(user);
		 int numberOfSkills = userSkillList.size();
		 System.out.println("User a126317 has " + numberOfSkills + " skills");
		 userSkill = new UserSkill();
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
		 hobbyList = dao.getHobby();
		 int numberOfHobbies = hobbyList.size();
		 System.out.println("Number of hobbies = " + numberOfHobbies);
		 hobby = new Hobby();
		 for (int x=0; x<numberOfHobbies; x++)
		 {
			 hobby = hobbyList.get(x);
			 System.out.println("Hobby ID = " + hobby.getHobbyID() + " Hobby Name = " + hobby.getHobbyName());
		 }
		 
		 System.out.println("Testing method addHobby");
		 newHobby = new Hobby();
		 newHobby.setHobbyID(999);
		 newHobby.setHobbyName("Testing adding a hobby");
		 dao.addHobby(newHobby);
		 hobbyList = dao.getHobby();
		 numberOfHobbies = hobbyList.size();
		 System.out.println("Number of hobbies = " + numberOfHobbies);
		 hobby = new Hobby();
		 for (int x=0; x<numberOfHobbies; x++)
		 {
			 hobby = hobbyList.get(x);
			 System.out.println("Hobby ID = " + hobby.getHobbyID() + " Hobby Name = " + hobby.getHobbyName());
		 }
		 
		 System.out.println("Testing method removeHobby");
		 newHobby = new Hobby();
		 newHobby.setHobbyID(999);
		 newHobby.setHobbyName("Testing adding a hobby");
		 dao.removeHobby(newHobby);
		 hobbyList = dao.getHobby();
		 numberOfHobbies = hobbyList.size();
		 System.out.println("Number of hobbies = " + numberOfHobbies);
		 hobby = new Hobby();
		 for (int x=0; x<numberOfHobbies; x++)
		 {
			 hobby = hobbyList.get(x);
			 System.out.println("Hobby ID = " + hobby.getHobbyID() + " Hobby Name = " + hobby.getHobbyName());
		 }
		 
		 
	}

}
