package jw3m.client.gui;

import jw3m.beans.*;
import java.io.*;
import java.net.*;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import jw3m.server.Server;

public class NetworkClient
{
	final static Logger logger = Logger.getLogger(NetworkClient.class);
	private Socket soc = null;
    private boolean tf = true;
    private String msg; 
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private SkillsClient baseFrame;
    
    public NetworkClient(SkillsClient frame, String serverAddress, int serverPort)
	{
    	PropertyConfigurator.configure("log4j.properties");
    	
    	this.baseFrame = frame;
    	
//    	private String serverAddress = null;
//    	private int serverPort = 0;
    	
    	
    	try
	    {
	        soc = new Socket(serverAddress,serverPort);
	       
	    } 
    	catch (UnknownHostException e)
	    {
	        e.printStackTrace();
	    } 
    	catch (IOException e)
	    {
	        e.printStackTrace();
	    }
    	
    	
		try
		{
			oos = new ObjectOutputStream (soc.getOutputStream());
			ois = new ObjectInputStream (soc.getInputStream());
			
			
			
			// 	read first from client
			
			
			
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	
  
	}
    
    public User passCredentials(String userName, String password)
    {
    	Comms creds = null;
    	User user = null;
		try
		{
			Comms welcome = (Comms) ois.readObject();
			
			if (welcome.getText().equals("Welcome"))
			{
				logger.info("sending username");
				oos.writeObject(new Comms("userName", userName));
				logger.info("sending password");
				oos.writeObject(new Comms("password", password));
				
				logger.info("getting server response to credentials");
				Comms credentialsReply = (Comms) ois.readObject();
				
				
				
				if (credentialsReply.getText().equals("authenticated"))
				{
					user = (User)credentialsReply.getObj();
					logger.info("authenticated " + user.getUserName());
					return user;
				}
				else
				{
					logger.info("authentication failed");
					return null;
				}
				
			}
			else
			{
				logger.info("bad server response");
				return null;
			}
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return user;
		
    }

//    public static void main(String[] args)
//	{
//		new Client();
//	}
}
