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
		//super();
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


	
	

}
