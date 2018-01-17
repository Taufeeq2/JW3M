package jw3m.junit;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Test;

import jw3m.beans.User;
import jw3m.dao.DAO;

public class JunitDAO
{
	
	 private DAO dao = null;
	 private User user = null;
	 private Vector<User> userList = null;

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
			e.printStackTrace();
		 }
		 
		 user = dao.getUser("a126317");
		 String email = user.getEmailAddress();
		 System.out.println("Email = " + email);
		 
		 
		 userList = dao.getUserList();
		 int listSize = userList.size();
		 System.out.println("the userList has " + listSize + " entries");
	}

}
