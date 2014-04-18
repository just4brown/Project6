/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imserver;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import javax.swing.SwingWorker;

/**
 *
 * @author pioni_000
 */
public class ClientConnectionWorker extends SwingWorker<String, String> {
    
    Socket sock;
    int port;
    ClientGUI cg;
    
    public ClientConnectionWorker (int p) {
        port = p;
    }

    protected String doInBackground() throws Exception {
       
        InputStream in = null;
        BufferedReader bin = null;
        Socket IMServer = null;
        String line = null;
        try {
            IMServer = new Socket(InetAddress.getByName("localhost"), port);
            in = IMServer.getInputStream();
            bin = new BufferedReader(new InputStreamReader(in));
            line = bin.readLine();
            
//            Send login info to server from here
//            Recall:
//            1  - LOGON 
//            From client to server
//            Format:  1 USERNAME PASSWORD
//            Example: 1 mzimmerm qaz123
            
        }
        catch (IOException ioe) {
            System.err.println(ioe);
        }
        return line;
    }
    
    protected void process(List<String> chunks){
        for(String message : chunks){
            System.out.println(message);
        }
    }
    
    
    
}
