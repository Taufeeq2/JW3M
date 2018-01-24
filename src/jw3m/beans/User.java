package jw3m.beans;

import java.io.Serializable;

public class User implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName = null;
	private String password = null;
	private String firstName= null;
	private String surname= null;
	private String alias= null;
	private String emailAddress= null;
	private int mobile = 0;
	private boolean mentor = false;
	
	public User()
	{
		// BEAN
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getSurname()
	{
		return surname;
	}

	public void setSurname(String surname)
	{
		this.surname = surname;
	}

	public String getAlias()
	{
		return alias;
	}

	public void setAlias(String alias)
	{
		this.alias = alias;
	}

	public String getEmailAddress()
	{
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress)
	{
		this.emailAddress = emailAddress;
	}

	public int getMobile()
	{
		return mobile;
	}

	public void setMobile(int mobile)
	{
		this.mobile = mobile;
	}

	public boolean isMentor()
	{
		return mentor;
	}

	public void setMentor(boolean mentor)
	{
		this.mentor = mentor;
	}

	public String toStringFull()
	{
		return "User [userName=" + userName + ", password=" + password + ", firstName=" + firstName + ", surname="
				+ surname + ", alias=" + alias + ", emailAddress=" + emailAddress + ", mobile=" + mobile + ", mentor="
				+ mentor + "]";
	}
	
	@Override
	public String toString()
	{
		return firstName + " " + surname; 
	}
	
}
