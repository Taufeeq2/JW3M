package jw3m.beans;

import java.io.Serializable;
import java.util.Vector;

public class MultiBean implements Serializable
{
	private Object obj = null;
	private Vector<Object> multi= null;
	
	
	
	public MultiBean()
	{
		this.multi = new Vector<Object>();
	}

	public Object getObj()
	{
		return obj;
	}
	
	public void setObj(Object obj)
	{
		this.obj = obj;
	}
	
	public Vector<Object> getMulti()
	{
		return multi;
	}
	
	public void setMulti(Vector<Object> multi)
	{
		this.multi = multi;
	}
	
	public void addMulti(Object obj)
	{
		this.multi.add(obj);
		
	}

	@Override
	public String toString()
	{
		return "MutliBean [obj=" + obj + ", multi=" + multi + "]";
	}
	
	
	

}
