package jw3m.beans;

import java.io.Serializable;
import java.sql.Date;

public class Notification implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int noticeID = 0;
	private String requestorID = null;
	private String ratorID = null;
	private Date date;
	
	public Notification()
	{
		//BEAN
	}

	public int getNoticeID()
	{
		return noticeID;
	}

	public void setNoticeID(int noticeID)
	{
		this.noticeID = noticeID;
	}

	public String getRequestorID()
	{
		return requestorID;
	}

	public void setRequestorID(String requestorID)
	{
		this.requestorID = requestorID;
	}

	public String getRatorID()
	{
		return ratorID;
	}

	public void setRatorID(String ratorID)
	{
		this.ratorID = ratorID;
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

	@Override
	public String toString()
	{
		return "Notification [noticeID=" + noticeID + ", requestorID=" + requestorID + ", ratorID=" + ratorID
				+ ", date=" + date + "]";
	}
	
}
