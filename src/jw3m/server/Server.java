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
//    private BufferedReader br = null;
    private String msg = null;
    private DAO dao = null;
    private int userID = 0;

    
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
			e.printStackTrace();
		}
        
        while (true)
        {
            try
            {
	            logger.info("Skills server: Listening for connections....");
	            clientsoc = serversoc.accept();
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
//	    private PrintWriter pw = null;
//	    private BufferedReader serverbr = null;
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
		        	
		        	try
					{
		        		logger.info("Thread ID:" + threadId + " server thread started");
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
								
								logger.info("Thread ID:" + threadId + " good logon");
								
								// we set the object we send back to client with no password
								// as client should have it
								userObj.setPassword(null);
								
								oos.writeObject(new Comms("authenticated", userObj));
								running = true;
							}
							else
							{
								// bad password
								running = false;
								logger.info("Thread ID:" + threadId + " bad password");
							}
						}
						else
						{
							// no user
							running = false;
							logger.info("Thread ID:" + threadId + " user does not exist");
						}
						
						
					} catch (IOException e)
					{
						// TODO Auto-generated catch block
						logger.info("Thread ID:" + threadId + " Sesson dropped");
						running = false;
						//e.printStackTrace();
					} catch (ClassNotFoundException e)
					{
						// TODO Auto-generated catch block
						logger.error("Thread ID:" + threadId + " Server class not found");
						running = false;
						// e.printStackTrace();
					}
		        	

		            while (running)
		            {
		            	
		            	logger.info("waiting for object");
		            	try
						{
			            		
							Comms comms = (Comms)ois.readObject();
					
							listenForTransaction(comms);
							
						} catch (ClassNotFoundException e)
						{
							// TODO Auto-generated catch block
							//e.printStackTrace();
							logger.error("Thread ID:" + threadId + " Server class not found");
							running = false;
							
							
						} catch (IOException e)
						{
							// TODO Auto-generated catch block
							logger.error("Thread ID:" + threadId + " Server class not found");
							running = false;
							// e.printStackTrace();
						}

		            }      
		            
		logger.info("Thread ID:" + threadId + " Sesson dropped");

		}
	
	}
	
	public static void main(String[] args)
	{

	//	dao = new DAO();
		
		new Server();
	}
	
	private void logonServer(PrintWriter pw, BufferedReader serverbr)
	{
		
		try
		{
			long threadId = Thread.currentThread().getId();
			logger.info(threadId + " : Server thread awaiting credentials");
			pw.flush();
			pw.println("Send Username");
			pw.flush();
			String clientUserName = serverbr.readLine();
			logger.info(threadId + " : Server got "+ clientUserName);
			pw.println("Send Password");
			pw.flush();
			String clientUserPassword = serverbr.readLine();
			logger.info(threadId + " : server got "+ clientUserPassword);
			
		//	System.out.println(clientUserName +  " " + clientUserPassword);
			
			if (dao.getUser(clientUserName).getUserName().equals(clientUserName) )
			{
				logger.info("username found...");
			}
			else
			{
				logger.info("no such user...");
			}
			
			if ( dao.getUser(clientUserName).getPassword().equals(clientUserPassword) )
			{
				logger.info("passwords match...");
			}
			else
			{
				logger.info("bas password and or no user");
			}
			
	//		oos = new ObjectOutStream(soc.getInputStream());
			
			if (dao.getUser(clientUserName).getUserName().equals(clientUserName) && dao.getUser(clientUserName).getPassword().equals(clientUserPassword) )
			{
				pw.println("Good logon");
				pw.flush();
				logger.info(threadId + " : good logon");
				
//				oos.flush();
//				oos.writeObject(dao.getUser(clientUserName));
//				oos.flush();
//				
				logger.info(threadId + " : user object sent down the line");
				
			}
			else
			{
				pw.println("Bad logon");
				pw.flush();
				logger.info(threadId + " :Bad logon");
			}
			
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		
		
	}
	
	public void listenForTransaction(Comms comms)
	{
		
		logger.info("trancation listener method executed (case switching instructions) ");
		switch (comms.getText())
		{
		
			case "123" : logger.info("123"); 	break;
			case "test" : logger.info("test"); break;
		
		}
		
		
	}

}
