package jw3m.server;
import java.io.*;
import java.net.*;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import jw3m.dao.DAO;

public class Server
{
	final static Logger logger = Logger.getLogger(Server.class);
	
	private ServerSocket serversoc = null;
    private Socket clientsoc = null;
    private BufferedReader br = null;
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
	      //      System.out.println("Skills server: Welcome");
	            logger.info("Skills server: Welcome");
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
	    private PrintWriter pw = null;
	    private BufferedReader serverbr = null;
	    private ObjectOutputStream oos = null;
	
	    public ServerThread(Socket threadsoc)
	    {      
	    	this.threadsoc = threadsoc;       
	    }
	
		@Override
		public void run()
		{
		        try
		        {
		        	long threadId = Thread.currentThread().getId();
		        	
		        //	this.threadsoc.
		            pw = new PrintWriter(threadsoc.getOutputStream(), true);
		            serverbr = new BufferedReader(new InputStreamReader(threadsoc.getInputStream()));
		            boolean running = true;
		            String in = serverbr.readLine();
		            while (running)
		            {
		            	if (in.equals("request logon"))
		            	{
		            		logger.info(threadId + " :receieved : request logon");
		            		
		            		oos = new ObjectOutputStream(threadsoc.getOutputStream());
		            		logonServer(pw, serverbr);
		            		
		            		// pass back objets
//		            		msg = userDB.getUser(userID);
//				            pw.println(msg);
		            		
				            pw.flush();
		            	}   
		            	

		            	running = false;
		             }      
		        } 
		        catch (IOException e)
		        {
		        	try
					{
						serverbr.close();
						pw.close();
						threadsoc.close();
//						br.close();
					} 
		        	catch (IOException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		            e.printStackTrace();
		        }  
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
			

			
			
		//	System.out.println(  dao.getUser(clientUserName).getFirstName() );
			
			// send back vector for testing
			
	//		dao.getUserList();
			
			
			pw.flush();
			
		//	logger.info(threadId + " : Thread ended...");
			
			
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
		// assumes the basic comms extablished lets pass objects
		
		
	}
	

}
