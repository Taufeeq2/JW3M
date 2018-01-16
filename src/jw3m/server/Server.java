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
	
	    public ServerThread(Socket threadsoc)
	    {      
	    	this.threadsoc = threadsoc;       
	    }
	
		@Override
		public void run()
		{
		        try
		        {
		            pw = new PrintWriter(threadsoc.getOutputStream(), true);
		            serverbr = new BufferedReader(new InputStreamReader(threadsoc.getInputStream()));
		            boolean running = true;
		            String in = serverbr.readLine();
		            while (running)
		            {
		            	if (in.equals("request logon"))
		            	{
		            		logger.info("receieved : request logon");
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
		new Server();
	}
	
	private void logonServer(PrintWriter pw, BufferedReader serverbr)
	{
		
		try
		{
			logger.info("server logon method started");
			pw.println("Send Username");
			pw.flush();
			String clientUserName = serverbr.readLine();
			logger.info("server got "+ clientUserName);
			pw.println("Send Password");
			pw.flush();
			String clientUserPassword = serverbr.readLine();
			logger.info("server got "+ clientUserPassword);
			
			System.out.println(clientUserName +  " " + clientUserPassword);
			
			if (clientUserName.equals("Bob") && clientUserPassword.equals("123") )
			{
				pw.println("Good logon");
				pw.flush();
				logger.info("good logon");
			}
			else
			{
				pw.println("bad logon");
				pw.flush();
				logger.info("bad logon");
			}
			
			
			// send back vector for testing
			
	//		dao.getUserList();
			
			
			pw.flush();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
		// assumes the basic comms extablished lets pass objects
		
		
	}
	

}
