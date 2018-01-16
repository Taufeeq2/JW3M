package jw3m.client.gui;
import java.io.*;
import java.net.*;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import jw3m.server.Server;

public class Client
{
	final static Logger logger = Logger.getLogger(Client.class);
	private Socket soc = null;
    private BufferedReader br = null;
    private PrintWriter pw = null;
    private boolean tf = true;

    
    public Client()
	{
    	PropertyConfigurator.configure("log4j.properties");
    	
    	try
	    {
	        soc = new Socket("localhost",1337);
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
    			logger.info("client requesting logon");
    			String mesg = "request logon" ;
    		    pw.println(mesg);
            	pw.flush();
            	String msg = "";
            	
 
 //           	while((msg = br.readLine()) != null)
            		
  //          	{
            		msg = br.readLine();
            		logger.info("client recieved " + msg);
            		if (msg == "Send Username");
            		{
            			pw.println("Bob");
            		}
            		
            		msg = br.readLine();
            		logger.info("client recieved " + msg);
               		if (msg == "Send Password");
            		{
            			pw.println("123");
            		}
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

    public static void main(String[] args)
	{
		new Client();
	}
}
