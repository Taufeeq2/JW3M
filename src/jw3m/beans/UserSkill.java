package jw3m.beans;

import java.io.Serializable;

public class UserSkill implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer userSkillID;
	private String userID;
	private Integer skillID;
	
	public UserSkill()
	{
		//BEAN
	}

	public Integer getUserSkillID()
	{
		return userSkillID;
	}

	public void setUserSkillID(Integer userSkillID)
	{
		this.userSkillID = userSkillID;
	}

	public String getUserID()
	{
		return userID;
	}

	public void setUserID(String userID)
	{
		this.userID = userID;
	}

	public Integer getSkillID()
	{
		return skillID;
	}

	public void setSkillID(Integer skillID)
	{
		this.skillID = skillID;
	}
	

	
	
	
}
