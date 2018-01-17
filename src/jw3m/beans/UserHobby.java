package jw3m.beans;

import java.io.Serializable;

public class UserHobby implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userID;
	private Integer hobbyID;
	
	public UserHobby()
	{
		//BEAN
	}

	public String getUserID()
	{
		return userID;
	}

	public void setUserID(String userID)
	{
		this.userID = userID;
	}

	public Integer getHobbyID()
	{
		return hobbyID;
	}

	public void setHobbyID(Integer hobbyID)
	{
		this.hobbyID = hobbyID;
	}
	
}