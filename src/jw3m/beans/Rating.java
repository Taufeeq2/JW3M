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
	private int knowledge = 0;
	private int workStandard = 0;
	private int autonomy = 0;
	private int complexityCoping = 0;
	private int contextPerception = 0;
	private int capabilityGrowing = 0;
	private int collaboration = 0;
	private Date date = null;

	public Rating()
	{
		// BEAN
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

	public int getKnowledge()
	{
		return knowledge;
	}

	public void setKnowledge(int knowledge)
	{
		this.knowledge = knowledge;
	}

	public int getWorkStandard()
	{
		return workStandard;
	}

	public void setWorkStandard(int workStandard)
	{
		this.workStandard = workStandard;
	}

	public int getAutonomy()
	{
		return autonomy;
	}

	public void setAutonomy(int autonomy)
	{
		this.autonomy = autonomy;
	}

	public int getComplexityCoping()
	{
		return complexityCoping;
	}

	public void setComplexityCoping(int complexityCoping)
	{
		this.complexityCoping = complexityCoping;
	}

	public int getContextPerception()
	{
		return contextPerception;
	}

	public void setContextPerception(int contextPerception)
	{
		this.contextPerception = contextPerception;
	}

	public int getCapabilityGrowing()
	{
		return capabilityGrowing;
	}

	public void setCapabilityGrowing(int capabilityGrowing)
	{
		this.capabilityGrowing = capabilityGrowing;
	}

	public int getCollaboration()
	{
		return collaboration;
	}

	public void setCollaboration(int collaboration)
	{
		this.collaboration = collaboration;
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
