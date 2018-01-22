package jw3m.beans;

import java.io.Serializable;

public class Comms implements Serializable
{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String text;
	private Object obj;
	
	public Comms()
	{
		
	}
	
	// not true bean
	public Comms(String text, Object obj)
	{
		this.text = text;
		this.obj = obj;
	}

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public Object getObj()
	{
		return obj;
	}

	public void setObj(Object obj)
	{
		this.obj = obj;
	}

	@Override
	public String toString()
	{
		//return "Comms [text=" + text + ", obj=" + obj + "]";
		return "Comms [text=" + text + ", obj=" + obj.getClass() + "]";
		
	}


	
	

}
