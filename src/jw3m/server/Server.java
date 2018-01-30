package jw3m.server;
import java.io.*;
import java.net.*;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import jw3m.dao.DAO;
import jw3m.beans.*;

// import jw3m.server.ServerProtocol;

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
					User authenticatedUser = null;
					
		        	long threadId = Thread.currentThread().getId();
		        	
		        	String strPrefixAuth = "  Thread ID:" + threadId + " | Authentication Message : ";
		        	String strPrefixEnd = "  Thread ID:" + threadId + " | Session information : ";
		        
		        	
		        	// could maybe move this whole try into authenticate method in this class
		        	try
					{
		        		logger.info(strPrefixEnd + " server thread started");
						oos = new ObjectOutputStream (threadsoc.getOutputStream());
						ois = new ObjectInputStream (threadsoc.getInputStream());
						
						Comms welcome = new Comms("Welcome", "Welcome");
						oos.writeObject(welcome);
						
						Comms commsFirstPacket = (Comms)ois.readObject();

					//	logger.info("server got " + );
						listenForFirstTransaction(threadId,ois, oos,  commsFirstPacket, authenticatedUser, running);
						
					    while (running)
					    {
							Comms comms = (Comms)ois.readObject();
							listenForTransaction(threadId,ois, oos,  comms);
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
		            
		logger.info(strPrefixEnd+ " Sesson dropped");
		}
	}
	
	public static void main(String[] args)
	{
		logger.info("Skills server starting...");
		new Server();
	}
	
	public void listenForFirstTransaction(long threadId, ObjectInputStream ois, ObjectOutputStream oos, Comms comms, User authenticatedUser, Boolean running)
	{
		String strPrefix = "  Thread ID:" + threadId + " | Known Transaction message : ";
		String strPrefixDefault = "  Thread ID:" + threadId + " | *** Unknown Transaction message : "; 
		String strPrefixAdd = "  Thread ID:" + threadId + " |  : ";		
		
		String strPrefixAuth = "  Thread ID:" + threadId + " | Authentication Message : ";
    	String strPrefixEnd = "  Thread ID:" + threadId + " | Session information : ";
    
		
		try
		{
			switch (comms.getText())
			{
			
				case "request auth" : 
				{
					logger.info(strPrefix + " request auth");
	
		
					MultiBean multiBean = (MultiBean)comms.getObj();
					
					
					String userName =(String)multiBean.getObj();
					String password =(String)multiBean.getMulti().get(0);

				
					User userObj = dao.getUser( userName );
					
					if (userObj != null)
					{
						if (userObj.getPassword().equals(    password    ))
						{
							// good logon
							
							logger.info(strPrefixAuth + " good logon");
							
							// we set the object we send back to client with no password
							// as client should have it
  						   //	userObj.setPassword(null);
 							
							oos.writeObject(new Comms("authenticated", userObj));

							authenticatedUser = userObj;
							
							// running tells the server to now process normal transactions when logged in.
							running = true;
							
						}
						else
						{
							// bad password
							running = false;
							logger.info(strPrefixAuth + " bad password");
							oos.writeObject(new Comms("auth_fail", "Bad password!!!"));
							//return false;
						}
					}
					else
					{
						// no user
						running = false;
						logger.info(strPrefixAuth + " user does not exist");
						oos.writeObject(new Comms("auth_fail", "User does not exist and/or bad password!"));
						//return false;
					}

					break;
				}
				
				case "add userList" : 
				{
					logger.info(strPrefix + " add userList");

					// Actually process the add
					if (  dao.addUserList( (User)comms.getObj()  )      )
					{ 
						logger.info(strPrefixAdd + " added " + (User)(comms.getObj()) );
					}
					else
					{
						logger.error(strPrefixAdd + " Critical failure - could be duplicate primary key");
					}

					// seems i am not sending the new bean back - How did justin fixed
					
					oos.writeObject(new Comms("added userList",   dao.getUserList()  )  )   ;
					break;
				}

				case "send user" : 
				{
					logger.info(strPrefix + " send user");
					
					String tempStr = (String)comms.getObj() ;
					User tempUser = dao.getUser(   tempStr      );
					oos.writeObject(new Comms("reply user",   tempUser      )  );
					break;
				}
				
				
				
	
				default : 
				{
							logger.error(strPrefixDefault + " unexpected packet. At this time packet must be authenticate or add user only");
							logger.error("  ------> Txt part was: "	+ comms.getText() );
							logger.error("  ------> Obj part was: " + comms.getObj().toString() );
							
							Comms tempComms = new Comms();
							
							tempComms.setText("Unknown Packet... sending you back your whole comms object");
							tempComms.setObj(comms);
							
							oos.writeObject(tempComms);
			
				} // end default
			
			
			}
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//listenForTransaction(threadId,ois, oos,  comms);
	public void listenForTransaction(long threadId, ObjectInputStream ois, ObjectOutputStream oos, Comms comms) // throws Exception
	{
		String strPrefix = "  Thread ID:" + threadId + " | Known Transaction message : ";
		String strPrefixDefault = "  Thread ID:" + threadId + " | *** Unknown Transaction message : "; 
		String strPrefixAdd = "  Thread ID:" + threadId + " |  : ";			
	//logger.info("Thread ID: " + threadId + " trancation listener method executed (case switching instructions) ");l

		
		try
		{
			// can make a protocol class
			switch (comms.getText())
			{

			// The sends
				
						
				case "send user" : 
				{
					logger.info(strPrefix + " send user");
					
					String tempStr = (String)comms.getObj() ;
					oos.writeObject(new Comms("reply user", dao.getUser(   tempStr      )         )  );
					break;
				}
					
			
				case "send userList" : 
				{
					logger.info(strPrefix + " send userList");
					oos.writeObject(new Comms("reply userList", dao.getUserList())   );
					break;
				}
							
				case "send skillList" : 
				{
					logger.info(strPrefix + " send skillList");
					oos.writeObject(new Comms("reply skillList", dao.getSkillList())   );
					break;
				}
								
				
				case "send hobbyList" : 
				{
					logger.info(strPrefix + " send hobbyList");
					oos.writeObject(new Comms("reply hobbyList", dao.getHobbyList())   );
					break;
				}
				
				
				case "send levels" : 
				{
					logger.info(strPrefix + " send levels");
					oos.writeObject(new Comms("reply levels", dao.getLevel())   );
					break;
				}
				
				case "send userSkills" : 
				{
					logger.info(strPrefix + " send userSkills");
					oos.writeObject(new Comms("reply userSkills", dao.getUserSkills( (User)comms.getObj()   )       )   );
					break;
				}
				
				case "send userHobby" : 
				{
					logger.info(strPrefix + " send userHobby");
					oos.writeObject(new Comms("reply userHobby", dao.getUserHobby( (User)comms.getObj()   )       )   );
					break;
				}
				
				
				case "send userRating" : 
				{
					logger.info(strPrefix + " send userRating");
					oos.writeObject(new Comms("reply userRating", dao.getRatings( (User)comms.getObj()   )       )   );
					break;
				}
				
				case "send skillRating" : 
				{
					logger.info(strPrefix + " send skillRating");
					oos.writeObject(new Comms("reply skillRating", dao.getRatings( (Skill)comms.getObj()   )       )   );
					break;
				}
				
				case "send userNotifications" : 
				{
					logger.info(strPrefix + " send userNotifications");
					oos.writeObject(new Comms("reply userNotifications", dao.getNotification( (User)comms.getObj()   )       )   );
					break;
				}
				
				// the adds
				
// moved to first packets switcher
//				case "add userList" : 
//				{
//					logger.info(strPrefix + " add userList");
//
//					// Actually process the add
//					if (  dao.addUserList( (User)comms.getObj()  )      )
//					{ 
//						logger.info(strPrefixAdd + " added " + (User)(comms.getObj()) );
//					}
//					else
//					{
//						logger.error(strPrefixAdd + " Critical failure - could be duplicate primary key");
//					}
//
//					oos.writeObject(new Comms("added userList",   dao.getUserList()  )  )   ;
//					break;
//				}

				case "add skillList" : 
				{
					logger.info(strPrefix + " add skillList");

					// Actually process the add
					if (  dao.addSkillList( (Skill)comms.getObj()  )      )
					{ 
						logger.info(strPrefixAdd + " added " + (Skill)(comms.getObj()) );
					}
					else
					{
						logger.error(strPrefixAdd + " Critical failure");
					}

					oos.writeObject(new Comms("added skillList",   dao.getSkillList()  )  )   ;
					break;
				}
				
				case "add hobbyList" : 
				{
					logger.info(strPrefix + " add hobbyList");

					// Actually process the add
					if (  dao.addHobbyList( (Hobby)comms.getObj()  )      )
					{ 
						logger.info(strPrefixAdd + " added " + (Hobby)(comms.getObj()) );
					}
					else
					{
						logger.error(strPrefixAdd + " Critical failure");
					}

					oos.writeObject(new Comms("added hobbyList",   dao.getHobbyList()  )  )   ;
					break;
				}
				
					// add levels is never a thing
				

				case "add userSkills" : 
				{
					logger.info(strPrefix + " add userSkills");
					
					MultiBean mb = (MultiBean)comms.getObj() ;
					User tempUser = (User)mb.getObj();
					Vector<Object> vo = (Vector<Object>)mb.getMulti();
					Integer skillInt = 0;
				//	Skill tempSkill = null;
					
					// check for off by 1
					for (int i = 0; i < vo.size() ; i++)
					{
						
						skillInt  = (Integer)vo.get(i);
						if (dao.addUserSkills(tempUser.getUserName(), skillInt ) )
						{
							 logger.info(strPrefixAdd + " added skillID " + skillInt + " to " + tempUser.getUserName() );
	
						}
						else
						{
							logger.error(strPrefixAdd + " Critical failure");
						}			
					}
					oos.writeObject(new Comms("added userSkills",   dao.getUserSkills(  tempUser   )  )  )   ;
					break;
				}
				
				
				case "add userHobby" : 
				{
					logger.info(strPrefix + " add userHobby");
					
					MultiBean mb = (MultiBean)comms.getObj() ;
					User tempUser = (User)mb.getObj();
					Vector<Object> vo = (Vector<Object>)mb.getMulti();
					Integer hobbyInt = 0;
				//	Skill tempSkill = null;
					
					// check for off by 1
					for (int i = 0; i < vo.size() ; i++)
					{
						
						hobbyInt  = (Integer)vo.get(i);
						if (dao.addUserHobby(tempUser.getUserName(), hobbyInt ) )
						{
							 logger.info(strPrefixAdd + " added hobbyID " + hobbyInt + " to " + tempUser.getUserName() );
	
						}
						else
						{
							logger.error(strPrefixAdd + " Critical failure");
						}			
					}
					oos.writeObject(new Comms("added userHobby",   dao.getUserHobby(  tempUser   )  )  )   ;
					break;
				}
				
				case "add userRating" : 
				{
					// the rating bean has everything
					// but inconsistant with multibean way like skills and hobbies???
					// the adding works but the send back???
				
					Rating tempRating = (Rating)(comms.getObj()) ;
					logger.info(strPrefix + " add userRating");
					
					// Actually process the add
					if (  dao.setRating( tempRating  )      )
					{ 
						logger.info(strPrefixAdd + " added " + tempRating );
					}
					else
					{
						logger.error(strPrefixAdd + " Critical failure");
					}
							
					// return the rator or rateee? or rating?

					oos.writeObject(new Comms("added userRating",   "sending nothing"  )  )   ;
					break;
				}
				
				case "add userNotifications" : 
				{
					// the rating bean has everything
					// but inconsistant with multibean way like skills and hobbies???
					// the adding works but the send back???
				
					
					Notification tempNotification = (Notification)(comms.getObj()) ;
					
					logger.info(strPrefix + "add userNotifications");
					
					// Actually process the add
					if (  dao.setNotification( tempNotification  )      )
					{ 
						logger.info(strPrefixAdd + " added " + tempNotification);
					}
					else
					{
						logger.error(strPrefixAdd + " Critical failure");
					}
							
					// return the rator or rateee? or rating?

					oos.writeObject(new Comms("added userNotifications",   "sending nothing"  )  )   ;
					break;
				}
				
				// Some edits
				
				case "edit user" : 
				{
					logger.info(strPrefix + "edit user");
					
					User tempUser;
					//lies
					// Actually process the add
					if ( (  tempUser = dao.editUser( (User)comms.getObj()  ) ) != null     )
					{ 
						logger.info(strPrefixAdd + " eddited " + (User)(comms.getObj()) );
					}
					else
					{
						logger.error(strPrefixAdd + " Critical failure - ???");
					}
				
//					User tempUser = (User)(comms.getObj());
//					tempUser.getUserName();
					
					oos.writeObject(new Comms("editted user",  tempUser ) )   ;
					break;
				}
				
				case "remove user" : 
				{
					logger.info(strPrefix + " remove user");
					
					if (  dao.removeUserList( (String)comms.getObj()  )      )
					{ 
						logger.info(strPrefixAdd + " removed " + (String)comms.getObj()  );
					}
					else
					{
						logger.error(strPrefixAdd + " Critical failure");
					}
					
					oos.writeObject(new Comms("removed User",   dao.getUserList()  )  )   ;
					break;
					
				}
				
				case "remove skillList" : 
				{
					logger.info(strPrefix + " remove skillList");

					// Actually process the add
					if (  dao.removeSkillList( (Skill)comms.getObj()  )      )
					{ 
						logger.info(strPrefixAdd + " removed " + (Skill)(comms.getObj()) );
					}
					else
					{
						logger.error(strPrefixAdd + " Critical failure");
					}

					oos.writeObject(new Comms("remove skillList",   dao.getSkillList()  )  )   ;
					break;
				}
				
				case "remove userSkills" : 
				{
					logger.info(strPrefix + " remove userSkills");
					
					MultiBean mb = (MultiBean)comms.getObj() ;
					User tempUser = (User)mb.getObj();
					Vector<Object> vo = (Vector<Object>)mb.getMulti();
					Integer skillInt = 0;

					for (int i = 0; i < vo.size() ; i++)
					{
						
						skillInt  = (Integer)vo.get(i);
						if (dao.removeUserSkills(tempUser.getUserName(), skillInt ) )
						{
							 logger.info(strPrefixAdd + " removed skillID " + skillInt + " to " + tempUser.getUserName() );
	
						}
						else
						{
							logger.error(strPrefixAdd + " Critical failure");
						}			
					}
					oos.writeObject(new Comms("removed userSkills",   dao.getUserSkills(  tempUser   )  )  )   ;
					break;
				}
				
				case "remove hobbyList" : 
				{
					logger.info(strPrefix + " remove hobbyList");

					// Actually process the add
					if (  dao.removeHobby( (Hobby)comms.getObj()  )      )
					{ 
						logger.info(strPrefixAdd + " removed " + (Hobby)(comms.getObj()) );
					}
					else
					{
						logger.error(strPrefixAdd + " Critical failure");
					}

					oos.writeObject(new Comms("removed hobbyList",   dao.getHobbyList()  )  )   ;
					break;
				}
				
				case "remove userHobby" : 
				{
					logger.info(strPrefix + " remove userHobby");
					
					UserHobby tempUserHobby = (UserHobby)comms.getObj();
					
					if (  dao.removeUserHobby( (UserHobby)comms.getObj()  )      )
					{ 
						logger.info(strPrefixAdd + " removed " + (UserHobby)comms.getObj()  );
					}
					else
					{
						logger.error(strPrefixAdd + " Critical failure");
					}
					
					User tempUser = new User();
					tempUser = dao.getUser(tempUserHobby.getUserID());

					oos.writeObject(new Comms("removed UserHobby",   dao.getUserHobby(tempUser)  )  )   ;
					break;
					
				}
				
				case "remove userNotification" : 
				{
					logger.info(strPrefix + " remove userNotification");
					
					Notification tempNotification = (Notification)comms.getObj();
					
					if (  dao.removeNotification( (Notification)comms.getObj()   )   )
					{ 
						logger.info(strPrefixAdd + " removed " + (Notification)comms.getObj()  );
					}
					else
					{
						logger.error(strPrefixAdd + " Critical failure");
					}
					
					User tempUser = new User();
					tempUser = dao.getUser(tempNotification.getRatorID());

					oos.writeObject(new Comms("removed UserHobby",   dao.getNotification(tempUser)  )  )   ;
					break;
					
				}
				
				
//				case ""request auth"" : 
//				{
//					logger.info(strPrefix + " "request auth"");
//					
				
				
//					oos.writeObject(new Comms("reply userList", dao.getUser((String)comms.getObj() )  )  );
//					break;
//				}
//				
//				
				
				// we keep default to help us code client
				default : 
				{
							logger.error(strPrefixDefault + " undefined comm packet!!! Sending whole comm object back to client!");
							logger.error("  ------> Txt part was: "	+ comms.getText() );
							logger.error("  ------> Obj part was: " + comms.getObj().toString() );
							
							Comms tempComms = new Comms();
							
							tempComms.setText("Unknown Packet... sending you back your whole comms object");
							tempComms.setObj(comms);
							
							oos.writeObject(tempComms);
			
				} // end default
			
			} // end switch 
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public DAO getDao()
	{
		return dao;
	}
	
	

}
