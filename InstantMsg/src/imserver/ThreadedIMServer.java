package imserver;

import java.io.*;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ThreadedIMServer
    extends BasicServer implements Runnable {
    //key = username, value = int port
    public static final Hashtable usersOn = new Hashtable();
    int userPort = 4221;
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;


  public ThreadedIMServer() {
    super(4225, 0);
  }

  public static void main(String[] args) {
    ThreadedIMServer myServer = new ThreadedIMServer();

    // spins on ServerSocket
    myServer.start();
  }

  protected void serviceConnection(Socket connection) throws IOException {
    ServerConnectionThread connectThread = new ServerConnectionThread(this,
         connection);
    connectThread.start();
    
    
  }

  public void run() {
    ServerConnectionThread thisThread = (ServerConnectionThread) Thread.
        currentThread();
    Socket thisSocket = thisThread.getSocket();
    
    try {
        
        PrintWriter out = new PrintWriter
           (new PrintWriter(thisSocket.getOutputStream(), true));
        out.println("Hello from your server");
        InputStream clientIn = thisSocket.getInputStream();
        BufferedReader bin = new BufferedReader(new InputStreamReader(clientIn));
        ObjectOutputStream os = new ObjectOutputStream(thisSocket.getOutputStream());
        ObjectInputStream sInput  = new ObjectInputStream(thisSocket.getInputStream());

        os.writeObject("Connected 1to Server!!");        
        
        // Get Username & Password (should be sent in succession)
        String msg = null;
        
        while(true) {
            try {
                msg = (String) sInput.readObject();
                String response = parseMessage(msg, os);
                os.writeObject(response);
            } catch (Exception ex) {
                Logger.getLogger(ThreadedIMServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }        
        
    } catch (IOException e) {
        e.printStackTrace();
    }   
           
           
    String user = null;
    

  }
  
  public String parseMessage(String msg, ObjectOutputStream os) throws Exception {
      
      String[] s = msg.split(" ");
      int type = Integer.parseInt(s[0]);
      String un = s[1];
      switch(type) {
          case 0: // Create Account
              break;
          case 1: // Log on              
              String pw = s[2];
              if(logUserIn(un,pw)) {                  
                  notifyBuddies(un, "on");
                  usersOn.put(un, os);
                  return ("6 " + un);
              }
              else {
                  return ("7 " + un);
              }            
              
          case 2: // Log off
               // notifyBuddies(un, "off");
              // usersOn.remove(un);
              //
              break;
          case 3: // Outgoing/Incoming message
              String recipient = s[2];
              String text = s[3];
              break;
          case 4: // Buddy off notify
              break;
          
          
          
          
      }
      
      return "null";
  }
  
  public void notifyBuddies(String user, String status) {
      
        try {
            if(status.equals("on")) {
              for(Object o : usersOn.values()) {
                ObjectOutputStream temp = (ObjectOutputStream)o;
                temp.writeObject("4 " + user);
              }
            }
            if(status.equals("off")) {
              for(Object o : usersOn.values()) {
                ObjectOutputStream temp = (ObjectOutputStream)o;
                temp.writeObject("5 " + user);
              }
            }
        } catch (Exception e) {
              e.printStackTrace();
        }
  }
  
  public Boolean logUserIn(String name, String pw) throws Exception {
        Boolean userLoggedIn = false;
        try {
             
            // this will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // setup the connection with the DB.
            connect = DriverManager
                    .getConnection("jdbc:mysql://john.cedarville.edu:3306/cs4220?"
                            + "user=cs4220&password=");
            statement = connect.createStatement();
            // resultSet gets the result of the SQL query
            resultSet = statement
                    .executeQuery("select name,pw from JLChatUsers where name='"+name+"'");
            resultSet.next();
            String pwCheck = resultSet.getString("pw");
            if(pwCheck==null){
                userLoggedIn = false;
            }
            else{
                if(pwCheck.toString().equals(pw)){
                    userLoggedIn = true;
                    //writeServer.writeBytes(pw);
                }
                else{
                    userLoggedIn = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            return userLoggedIn;
        }
    }
    
    public Boolean logNewUser(String name, String pw) throws Exception {
        Boolean userExists = false;
        try {
             
            // this will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // setup the connection with the DB.
            connect = DriverManager
                    .getConnection("jdbc:mysql://john.cedarville.edu:3306/cs4220?"
                            + "user=cs4220&password=");
            statement = connect.createStatement();
            resultSet = statement
                    .executeQuery("select name from JLChatUsers where name='"+name+"'");
            if(resultSet.next()){
                userExists = true;
            }
            else{
                userExists = false;
                statement = connect.createStatement();
                statement.executeUpdate("insert into JLChatUsers values('"+name+"','"+pw+"')");
            }
                       
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            return userExists;
        }
    }
  
}


/*

MESSAGE CODES AND FORMATS (ALL MESSAGES MUST BE TERMINATED WITH A NEWLINE)
*******************************************

**** REQUIRED CODES FOR Cedar-Talk COMPLIANCE ****

0  - CREATE ACCOUNT
From client to server
Format:  0 USERNAME PASSWORD
Example: 0 mzimmerm qaz123


1  - LOGON 
From client to server
Format:  1 USERNAME PASSWORD
Example: 1 mzimmerm qaz123


2  - LOGOFF 
From client to server
Format:  2 USERNAME
Example: 2 mzimmerm


3  - OUTGOING/INCOMING MESSAGES
From client to server and server to client
Format:  3 SENDER RECIPIENT MESSAGE
Example: 3 mzimmerm esimon This is a sample message!


4  - BUDDY ON NOTIFY
From server to client
Format:  4 BUDDYNAME
Example: 4 esimon


5  - BUDDY OFF NOTIFY
From server to client
Format:  5 BUDDYNAME
Example: 5 esimon


6  - USER LOGGED ON SUCCESSFULLY
From server to client
Format:  6 USERNAME
Example: 6 mzimmerm


7  - USER LOGON FAILED
From server to client
Format:  7 USERNAME
Example: 7 mzimmerm

*/