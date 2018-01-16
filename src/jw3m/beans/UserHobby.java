package jw3m.beans;

import java.io.Serializable;

public class UserHobby implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer userID;
	private Integer hobbyID;
	
	public UserHobby()
	{
		//BEAN
	}

	public Integer getUserID()
	{
		return userID;
	}

	public void setUserID(Integer userID)
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