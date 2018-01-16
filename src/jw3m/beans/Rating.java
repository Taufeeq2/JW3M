package jw3m.beans;

import java.io.Serializable;
import java.sql.Date;


public class Rating implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int ratingID = 0;
	private String raterID = null;
	private String userID = null;
	private int skillID = 0;
	private int level = 0;
	private Date date = null;
	
	public Rating()
	{
		//BEAN
	}

	public int getRatingID()
	{
		return ratingID;
	}

	public void setRatingID(int ratingID)
	{
		this.ratingID = ratingID;
	}

	public String getRaterID()
	{
		return raterID;
	}

	public void setRaterID(String raterID)
	{
		this.raterID = raterID;
	}

	public String getUserID()
	{
		return userID;
	}

	public void setUserID(String userID)
	{
		this.userID = userID;
	}

	public int getSkillID()
	{
		return skillID;
	}

	public void setSkillID(int skillID)
	{
		this.skillID = skillID;
	}

	public int getLevel()
	{
		return level;
	}

	public void setLevel(int level)
	{
		this.level = level;
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}
	

	
}
