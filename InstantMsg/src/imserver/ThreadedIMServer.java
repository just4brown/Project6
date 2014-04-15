package imserver;

import java.io.*;
import java.net.Socket;


public class ThreadedIMServer
    extends BasicServer implements Runnable {


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
        
        // Get Username & Password (should be sent in succession)
        String username = bin.readLine();
        String password = bin.readLine();
        
        // Check hashmap for username password combo
        
        // Send back 
        
        
        
    } catch (IOException e) {
        
    }   
           
           
    String user = null;
    
     
    

     /**********************
	a bunch of code deleted; this is where
	you handle the handshake with the client, and then
	put your readline busy wait
	**************************/
  }
  
}

