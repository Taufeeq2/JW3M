package jw3m.beans;

import java.io.Serializable;

public class Hobby implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer hobbyID = null;
	private String hobbyName = null;
	
	public Hobby()
	{
		//BEAN
	}

	public Integer getHobbyID()
	{
		return hobbyID;
	}

	public void setHobbyID(Integer hobbyID)
	{
		this.hobbyID = hobbyID;
	}

	public String getHobbyName()
	{
		return hobbyName;
	}

	public void setHobbyName(String hobbyName)
	{
		this.hobbyName = hobbyName;
	} 
	
	@Override
	public String toString()
	{
		return "\n[Hobby ID = " + this.getHobbyID() + "]" + "[Hobby Name = " + this.getHobbyName() + "]";
	}
}
