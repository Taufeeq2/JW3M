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
    private ObjectInputStream ois = null;
    private ObjectOutputStream oos = null;
    private SkillsClient baseFrame;
    private Boolean isConnected = false;
    
    // global scope for defaults : not sure if we need this
    private String serverAddress;
    private int serverPort = 0;
    
    public NetworkClient(SkillsClient frame, String serverAddress, int serverPort)
	{
    	PropertyConfigurator.configure("log4j.properties");
    	
    	this.baseFrame = frame;
  	
    	logger.info("Object of NetworkClient created");
    	//set the server address and port
    	setServerAddress(serverAddress);
    	setServerPort(serverPort);
    	// get the socket connected
    	setupSocket();
    	// get the object streams established
    	setupObjectStreams();
    	
    	//client will need to process logon - done by PanelLogin
    	
  
	}
    
    public void networkTransaction(Comms comms)
    {

    	try
		{
			oos.writeObject(comms);
			Comms replyComms = (Comms) ois.readObject();
			
			logger.info("...send to server " + comms.getText() + "--- " + comms.getObj());
			logger.info("reply from server " + replyComms.getText() + "--- " + replyComms.getObj());
			
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error("Network client - classnot found");
			
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("Network client - IO error : probably session dropped");
		}
    	
    	
    	
    }
    
    public User passCredentials(String userName, String password)
    {
    	Comms creds = null;
    	User user = null;
		try
		{
			Comms welcome = (Comms) ois.readObject();
			
			// should check that the object part is a string with welcome
			if (welcome.getText().equals("Welcome")  )
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
					// drop the network sesssion
					logger.info("authentication failed '" + credentialsReply.getObj() + "'");
					//credentialsReply.getText();
					this.dropSession();
					baseFrame.connectToServer();
					return null;
				}
				
			}
			else
			{
				// drop the network sesssion
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
    
    public void dropSession()
    {

    	try
		{
    		logger.info("Trying to close client server session - dropping oos, ois and socket" );
    		
    		// sometimes we try closing even if they dont exist to we check for null first
    		
    		if (oos!=null)
    		{
    			oos.close();
    		}
    		if (ois!=null)
    		{
    			ois.close();
			}
    		if (soc!=null)
			{
    			soc.close();
			}
    		
    	//	 if (baseFrame.getNetworkClient() != null )
    			 
    		this.setIsConnected(false);
			
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			logger.error("client trying to drop network session... server can deal it");
			//e.printStackTrace();
		} catch (NullPointerException ne)
    	{
			// this is bad!!!
    		ne.printStackTrace();
    	}
    	
    }
    
    public void setServerAddress(String serverAddress)
    {
    	this.serverAddress = serverAddress;
    }
    
    public void setServerPort(int serverPort)
    {
    	this.serverPort = serverPort;
    }
    
    public String getServerAddress()
    {
    	return this.serverAddress;
    }
    
    public int getServerPort()
    {
    	return this.getServerPort();
    }
    
    
    public Socket getSocket()
    {
    	if (soc == null)
    	{
    		logger.error("network client class says socket is null");
    	}
    	return this.soc;
    }
    
	public Boolean getSocketStatus()
	{
		if (soc!=null)
		{
			logger.info("Socket exists");
			// this is closed may not work
			if (soc.isClosed() )
			{
				logger.info("Socket is open");
				return true;
			}
			else
			{
				logger.error("Socket is closed");
				return false;
			}
		}
		else
		{
			logger.info("Socket does not exist");
			return false;
		}
	
	}
    
    public void setupSocket()
    {
    	try
	    {
	        this.soc = new Socket(serverAddress,serverPort);
	        this.setIsConnected(true);
	        logger.info("Socket established");
	    } 
    	catch (UnknownHostException e)
	    {
    		logger.error("unknown host");
	        //e.printStackTrace();
	    } 
    	catch (IOException e)
	    {
//    		if (this.getServerAddress()!=null && this.getServerPort()!=0 )
//    		{
//    			logger.error("Network class says : Server not responding! Unable to create a socket for  " + this.getServerAddress() + ":" + this.getServerPort() + "!!!");
//    		}
    		logger.error("Network class says : Server not responding!");
	        //e.printStackTrace();
	    }
    }
    
    public void setIsConnected(Boolean bool)
    {
    	this.isConnected = bool;
    }
    
    public Boolean isConnected()
    {
    	return this.isConnected;
    }

    public void setupObjectStreams()
    {
		try
		{
			oos = new ObjectOutputStream (soc.getOutputStream());
			ois = new ObjectInputStream (soc.getInputStream());
			logger.info("Network Class has setup oos and ois");

				
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.info("Network Class has failed to setup oos and ois");
		}
    }
    
//    public static void main(String[] args)
//	{
//		new Client();
//	}
}
