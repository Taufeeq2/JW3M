package jw3m.beans;

import java.io.Serializable;

public class SkillName implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String skillName = null;
		
	public SkillName()
	{
		// BEAN
	}
	
	public String getSkillName()
	{
		return skillName;
	}
	public void setSkillName(String skillName)
	{
		this.skillName = skillName;
	}
	
	@Override
	public String toString()
	{
		return skillName;
	}
	
}