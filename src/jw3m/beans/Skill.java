package jw3m.beans;

import java.io.Serializable;

public class Skill implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int skillID = 0;
	private String skillName = null;
	private String skillVendor = null;
	private String skillDescription = null;
	
	public Skill()
	{
		// BEAN
	}
	
	public Integer getSkillID()
	{
		return skillID;
	}
	public void setSkillID(Integer skillID)
	{
		this.skillID = skillID;
	}
	public String getSkillName()
	{
		return skillName;
	}
	public void setSkillName(String skillName)
	{
		this.skillName = skillName;
	}
	public String getSkillVendor()
	{
		return skillVendor;
	}
	public void setSkillVendor(String skillVendor)
	{
		this.skillVendor = skillVendor;
	}
	public String getSkillDescription()
	{
		return skillDescription;
	}
	public void setSkillDescription(String skillDescription)
	{
		this.skillDescription = skillDescription;
	}
	
	
	
}
