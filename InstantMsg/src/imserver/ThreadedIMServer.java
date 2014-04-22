package imserver;

import java.io.*;
import java.net.Socket;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ThreadedIMServer
    extends BasicServer implements Runnable {
    //key = username, value = int port
    public static final Hashtable users = new Hashtable();
    int userPort = 4221;


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
        os.writeObject("Connected 1to Server!!");
        ObjectInputStream sInput  = new ObjectInputStream(thisSocket.getInputStream());
        // Get Username & Password (should be sent in succession)
        String msg = null;
        while(true) {
            try {
                msg = (String) sInput.readObject();
                String response = parseMessage();
                os.writeObject(response);
            } catch (Exception ex) {
                Logger.getLogger(ThreadedIMServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }        
        
    } catch (IOException e) {
        e.printStackTrace();
    }   
           
           
    String user = null;
    
     
    

     /**********************
	a bunch of code deleted; this is where
	you handle the handshake with the client, and then
	put your readline busy wait
	**************************/
  }
  
  public String parseMessage(String msg) {
      int type = Integer.parseInt(msg.substring(0, 1));
      switch(type) {
          case 0:
              break;
          case 1:
              break;
          
          
          
      }
      
      return "null";
  }
  
}

