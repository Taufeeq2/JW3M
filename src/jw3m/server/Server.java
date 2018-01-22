package jw3m.server;
import java.io.*;
import java.net.*;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import jw3m.dao.DAO;
import jw3m.beans.*;

public class Server
{
	final static Logger logger = Logger.getLogger(Server.class);
	
	private ServerSocket serversoc = null;
    private Socket clientsoc = null;
    private String msg = null;
    private DAO dao = null;
    private int userID = 0;
    private int threadCounter = 0;
    
    public Server()
	{
    	
    	PropertyConfigurator.configure("log4j.properties");
    	
    	try
        {
    	    serversoc = new ServerSocket(1337);
    	    dao = new DAO();

        }
        catch (IOException e)
        {
         //   System.err.println("could not listen on port 1337");
            logger.error("Could not listen on port 1337");
            System.exit(0);
        } 
    	catch (Exception e)
		{
    		// do we need to exit here?
//    		System.exit(0);
			e.printStackTrace();
		}
        
        while (true)
        {
            try
            {
            	// how do you get current threads
            	 
	            logger.info("  Listening for connections. Sessions processed [" + threadCounter + "]" );
	            clientsoc = serversoc.accept();
	            threadCounter ++;
	            ServerThread st = new ServerThread(clientsoc);
	            st.start();             
            }
            catch (IOException e)
            {
            	logger.error("Accept Failed");
	        //    System.err.println("Accept Failed");
	            System.exit(0);
            }
            
        }
	}

	

	class ServerThread extends Thread
	{
	    private Socket threadsoc;
	    private ObjectOutputStream oos = null;
	    private ObjectInputStream ois = null;
	    private Boolean running = true;
	    
	    public ServerThread(Socket threadsoc)
	    {      
	    	this.threadsoc = threadsoc;       
	    }
	
		@Override
		public void run()
		{
	
		        	long threadId = Thread.currentThread().getId();
		        	String strPrefixAuth = "  Thread ID:" + threadId + " | Authentication Message : ";
		        	String strPrefixEnd = "  Thread ID:" + threadId + " | Session information : ";
		        	
		        	try
					{
		        		logger.info(strPrefixEnd + " server thread started");
						oos = new ObjectOutputStream (threadsoc.getOutputStream());
						ois = new ObjectInputStream (threadsoc.getInputStream());
						
						Comms welcome = new Comms("Welcome", "Welcome");
		//				Comms comms_Creds = null;
						oos.writeObject(welcome);
						Comms comms_Username = (Comms)ois.readObject();
						Comms comms_Password = (Comms)ois.readObject();
						
						User userObj = dao.getUser((String)comms_Username.getObj());
						
						if (userObj != null)
						{
							if (userObj.getPassword().equals((String)comms_Password.getObj()))
							{
								// good logon
								
								logger.info(strPrefixAuth + " good logon");
								
								// we set the object we send back to client with no password
								// as client should have it
								userObj.setPassword(null);
								
								oos.writeObject(new Comms("authenticated", userObj));
								running = true;
								
							    while (running)
							    {
									Comms comms = (Comms)ois.readObject();
									listenForTransaction(threadId,ois, oos,  comms);
							    }							
							}
							else
							{
								// bad password
								running = false;
								logger.info(strPrefixAuth + " bad password");
							}
						}
						else
						{
							// no user
							running = false;
							logger.info(strPrefixAuth + " user does not exist");
							oos.writeObject(new Comms("auth_fail", "User does not exist and/or bad password!"));
						}
						
						
					} catch (IOException e)
					{
						// TODO Auto-generated catch block
						logger.info(strPrefixEnd + " Sesson dropped");
						running = false;
						//e.printStackTrace();
					} catch (ClassNotFoundException e)
					{
						// TODO Auto-generated catch block
						logger.error(strPrefixEnd + " Server class not found");
						running = false;
						// e.printStackTrace();
					}    
		            
		//logger.info(strPrefixEnd+ " Sesson dropped");
		}
	}
	
	public static void main(String[] args)
	{
		logger.info("Skills server starting...");
		new Server();
	}
	
	//listenForTransaction(threadId,ois, oos,  comms);
	public void listenForTransaction(long threadId, ObjectInputStream ois, ObjectOutputStream oos, Comms comms) // throws Exception
	{
		
	//logger.info("Thread ID: " + threadId + " trancation listener method executed (case switching instructions) ");l
		String strPrefix = "  Thread ID:" + threadId + " | Transaction message : ";
		
		try
		{
			switch (comms.getText())
			{
				
				case "Expect UserVector" : 
				{
				
						logger.info(strPrefix + " trying to send userVector back");
						oos.writeObject(new Comms("Returning UserVector", dao.getUserList()));

					break;
				}
			
				case "123" : 
				{
					logger.info(strPrefix + "123 case triggered"); 	
					oos.writeObject(comms);
					break;
				}
				case "test" : logger.info("test"); break;
				
				default : 
				{
							logger.info(strPrefix + " undefined comm packet!!!");
							logger.info("  ------>"	+ comms.getText() + " " + comms.getObj().toString() );
							
					
								oos.writeObject(comms);
			
				} // end default
			
			} // end switch 
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
