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
    private BufferedReader br = null;
    private PrintWriter pw = null;
    private boolean tf = true;
    private String msg; 
    private ObjectInputStream ois;
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
	        pw = new PrintWriter(soc.getOutputStream(), true);
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
    		
    			br = new BufferedReader(new InputStreamReader(soc.getInputStream()));
    			logger.info("client attempting to logon");
    			String mesg = "request logon" ;
    		    pw.println(mesg);
            	pw.flush();
            	msg = "";
            	
//            	passCredentials();
 //           	while((msg = br.readLine()) != null)
            		
  //          	{
//            		msg = br.readLine();
//            		logger.info("client recieved " + msg);
//            		if (msg == "Send Username");
//            		{
//            			pw.println("Bob");
//            		}
//            		
//            		msg = br.readLine();
//            		logger.info("client recieved " + msg);
//               		if (msg == "Send Password");
//            		{
//            			pw.println("123");
//            		}
    //        	}
        		
            		// read vector for testing
        		

	    } 
    	
	    catch (IOException e1)
	    {
	    	try
			{
				br.close();
				soc.close();
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
	        e1.printStackTrace();
	    }
	}
    
    public void passCredentials(String userName, String password)
    {
    	
		try
		{
			
			msg = br.readLine();
			
			
			logger.info("1st client recieved " + msg);
			if (msg.equals("Send Username"))
			{
				pw.println(userName);
			}
			else
			{
				System.out.println("did not get what we expected from server USERNAME");
			}
			
			msg = br.readLine();
			logger.info("2nd client recieved " + msg);
			if (msg.equals("Send Password"))
			{
				pw.println(password);
			}
			else
			{
				System.out.println("did not get what we expected from server PASSWORD");
			}
			
			
			msg = br.readLine();
			
			if (msg == "Good logon")
			{
				logger.info("client recieved " + msg);
			}
			
			// ask for user bean
//			System.out.println("expecting user object now");
//			  ois = new ObjectInputStream(soc.getInputStream());
//			  
//			  User user = null;
//			  
//			 try
//			{
//				user = (User)(ois.readObject() );
//				System.out.println("got object");
//				baseFrame.authenticatedUser = user;
//				
//			} catch (ClassNotFoundException e)
//			{
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			
			
//		     FileInputStream fis = new FileInputStream("t.tmp");
//		     ObjectInputStream ois = new ObjectInputStream(fis);
//			ois = new ObjectInputStream();
			
			
			
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
    }

//    public static void main(String[] args)
//	{
//		new Client();
//	}
}
