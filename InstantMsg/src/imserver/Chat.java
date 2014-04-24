package imserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

/**
 *
 * @author Lindsey
 */
public class Chat extends javax.swing.JFrame {

    public String inbox;
    public String outbox;
    ObjectInputStream is;
    Socket IMServer;
    ObjectOutputStream os; 
    public static final Hashtable buddiesOpen = new Hashtable();
    public String msg;
    DefaultListModel buddiesOnline = new DefaultListModel();
    String currentUser;
//    public ThreadedIMServer server;
//    Socket serverSocket;
//    ObjectInputStream readServer;
//    ObjectOutputStream writeServer;
    
    /**
     * Creates new form ChatBox
     */
    public Chat() {
        initComponents();
        buddylistpanel.hide();
        jList1.setModel(buddiesOnline);
        
        try {
            IMServer = new Socket("163.11.32.13", 4225);
            is  = new ObjectInputStream(IMServer.getInputStream());
            os  = new ObjectOutputStream(IMServer.getOutputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
   }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        DialogBox = new javax.swing.JDialog();
        messageFeed = new javax.swing.JScrollPane();
        textBox = new javax.swing.JTextField();
        sendMsgButton = new javax.swing.JButton();
        createNewUserBox = new javax.swing.JFrame();
        usernameLabel1 = new javax.swing.JLabel();
        newUsername = new javax.swing.JTextField();
        passwordLabel1 = new javax.swing.JLabel();
        newPassword1 = new javax.swing.JPasswordField();
        newPassword2 = new javax.swing.JPasswordField();
        passwordLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        closeChat = new javax.swing.JButton();
        loginPanel = new javax.swing.JLayeredPane();
        usernameLabel = new javax.swing.JLabel();
        username = new javax.swing.JTextField();
        passwordLabel = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        createNewUser = new javax.swing.JButton();
        login = new javax.swing.JButton();
        buddylistpanel = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        logout = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        messageFeed.setBackground(new java.awt.Color(204, 255, 204));

        textBox.setText("Enter Text");

        sendMsgButton.setText("SEND");
        sendMsgButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendMsgButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout DialogBoxLayout = new javax.swing.GroupLayout(DialogBox.getContentPane());
        DialogBox.getContentPane().setLayout(DialogBoxLayout);
        DialogBoxLayout.setHorizontalGroup(
            DialogBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DialogBoxLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DialogBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(messageFeed)
                    .addGroup(DialogBoxLayout.createSequentialGroup()
                        .addComponent(textBox, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sendMsgButton)))
                .addContainerGap())
        );
        DialogBoxLayout.setVerticalGroup(
            DialogBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DialogBoxLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(messageFeed, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(DialogBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textBox)
                    .addGroup(DialogBoxLayout.createSequentialGroup()
                        .addGap(0, 21, Short.MAX_VALUE)
                        .addComponent(sendMsgButton, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        createNewUserBox.setMinimumSize(new java.awt.Dimension(300, 350));

        usernameLabel1.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        usernameLabel1.setText(" Username");

        newUsername.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        newUsername.setText("New Username");

        passwordLabel1.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        passwordLabel1.setText(" Password");

        newPassword1.setText("jPasswordField1");

        newPassword2.setText("jPasswordField1");

        passwordLabel2.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        passwordLabel2.setText(" Re-Enter Password");

        jButton1.setText("Create New Account");
        jButton1.setName("createNewAccount"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createNewAccountAction(evt);
            }
        });

        javax.swing.GroupLayout createNewUserBoxLayout = new javax.swing.GroupLayout(createNewUserBox.getContentPane());
        createNewUserBox.getContentPane().setLayout(createNewUserBoxLayout);
        createNewUserBoxLayout.setHorizontalGroup(
            createNewUserBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createNewUserBoxLayout.createSequentialGroup()
                .addGroup(createNewUserBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(createNewUserBoxLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(createNewUserBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(newPassword2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(passwordLabel1)
                            .addComponent(usernameLabel1)
                            .addComponent(newUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(passwordLabel2)
                            .addComponent(newPassword1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(createNewUserBoxLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jButton1)))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        createNewUserBoxLayout.setVerticalGroup(
            createNewUserBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createNewUserBoxLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(usernameLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(newUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(passwordLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(newPassword1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(passwordLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(newPassword2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jButton1)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JL-Talk");
        setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        setName("chatWindow"); // NOI18N

        closeChat.setText("X");
        closeChat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeChat(evt);
            }
        });

        usernameLabel.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        usernameLabel.setText(" Username");

        username.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        username.setText("Bob");

        passwordLabel.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        passwordLabel.setText(" Password");

        password.setText("abcd");

        createNewUser.setFont(new java.awt.Font("Calibri", 2, 12)); // NOI18N
        createNewUser.setText("Create New User");
        createNewUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                createNewUserMouseClicked(evt);
            }
        });

        login.setFont(new java.awt.Font("Calibri", 2, 12)); // NOI18N
        login.setText("Login");
        login.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginMouseClicked(evt);
            }
        });
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout loginPanelLayout = new javax.swing.GroupLayout(loginPanel);
        loginPanel.setLayout(loginPanelLayout);
        loginPanelLayout.setHorizontalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(login)
                    .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(usernameLabel)
                        .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(passwordLabel)
                        .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(createNewUser)))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        loginPanelLayout.setVerticalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPanelLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(usernameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(passwordLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(login)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addComponent(createNewUser)
                .addGap(48, 48, 48))
        );
        loginPanel.setLayer(usernameLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        loginPanel.setLayer(username, javax.swing.JLayeredPane.DEFAULT_LAYER);
        loginPanel.setLayer(passwordLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        loginPanel.setLayer(password, javax.swing.JLayeredPane.DEFAULT_LAYER);
        loginPanel.setLayer(createNewUser, javax.swing.JLayeredPane.DEFAULT_LAYER);
        loginPanel.setLayer(login, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Buddy 1", "Buddy 2", "Buddy 3", "Buddy 4", "Buddy 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        logout.setText("Logout");
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });

        jLabel1.setText("Buddies");

        javax.swing.GroupLayout buddylistpanelLayout = new javax.swing.GroupLayout(buddylistpanel);
        buddylistpanel.setLayout(buddylistpanelLayout);
        buddylistpanelLayout.setHorizontalGroup(
            buddylistpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buddylistpanelLayout.createSequentialGroup()
                .addGroup(buddylistpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(buddylistpanelLayout.createSequentialGroup()
                        .addGroup(buddylistpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(buddylistpanelLayout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addComponent(logout))
                            .addGroup(buddylistpanelLayout.createSequentialGroup()
                                .addGap(84, 84, 84)
                                .addComponent(jLabel1)))
                        .addGap(0, 60, Short.MAX_VALUE))
                    .addGroup(buddylistpanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        buddylistpanelLayout.setVerticalGroup(
            buddylistpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buddylistpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(logout)
                .addContainerGap())
        );
        buddylistpanel.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        buddylistpanel.setLayer(logout, javax.swing.JLayeredPane.DEFAULT_LAYER);
        buddylistpanel.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(closeChat))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(loginPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 71, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(71, Short.MAX_VALUE)
                    .addComponent(buddylistpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(closeChat)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(loginPanel)
                .addGap(43, 43, 43))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(51, 51, 51)
                    .addComponent(buddylistpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(63, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginMouseClicked
        // TODO add your handling code here:
        //status.setText("Button pressed");
    }//GEN-LAST:event_loginMouseClicked

    private void createNewUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_createNewUserMouseClicked
        //Close login screen
        loginPanel.hide();
        //Open window to create new user
        createNewUserBox.show();

    }//GEN-LAST:event_createNewUserMouseClicked

    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
        currentUser = username.getText();
        String pw = password.getText();
        outbox = "1 "+ currentUser + " " + pw;
        new sendToServer().start();
       // new ListenFromServer().start();
        //Debug:
        
        buddiesOnline.addElement("Jane Doe");
        //
        
        
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException ex) {
//            ex.printStackTrace();
//        }
//             
//       //Format:  1 USERNAME PASSWORD
//        try {
//            //Username logged in
//            if (inbox.equals("6 " + un)) {
//                JOptionPane.showMessageDialog(this, "You're logged in!",
//                        "Logged In", JOptionPane.INFORMATION_MESSAGE);
//                loginPanel.hide();
//                buddylistpanel.show();
//            } //Username exists
//            else {
//                //Password is wrong or username doesn't exist
//                    JOptionPane.showMessageDialog(this, "Incorrect Username or Password.",
//                            "Error", JOptionPane.ERROR_MESSAGE);
//            }
//        } catch (Exception ex) {
//            //Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
//            ex.printStackTrace();
//        }
    }//GEN-LAST:event_loginActionPerformed

    private void closeChat(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeChat
        int result = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to exit the application?",
                "Exit Application",
                JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            this.dispose();
            try {
                logout();
                IMServer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_closeChat

    private void createNewAccountAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createNewAccountAction
        //Username already exists
        currentUser = newUsername.getText();
        String un = newUsername.getText();
        String password1  = newPassword1.getText();
        String password2 = newPassword2.getText();
        
        //try {
            //Password mismatch
            if (!(password1.equals(password2))) {
                JOptionPane.showMessageDialog(createNewUserBox, "Passwords must match.",
                        "Password error", JOptionPane.ERROR_MESSAGE);
            } //Username and passwords are good to be created
            else {
                outbox = "0 " + un + " " + password1;
                new sendToServer().start();
                
                
                //if(logNewUser(un,password1)){
                    //JOptionPane.showMessageDialog(createNewUserBox, "This username already exists.",
                    //"Username error", JOptionPane.ERROR_MESSAGE);
                //}
                //else{
                
//                JOptionPane.showMessageDialog(this, "You're logged in!",
//                    "Logged In", JOptionPane.INFORMATION_MESSAGE);
//                createNewUserBox.hide();
//                loginPanel.hide();
//                //buddiesOnline.addElement("John Doe");
//                buddylistpanel.show();
                //}
            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
    }//GEN-LAST:event_createNewAccountAction

    private void sendMsgButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendMsgButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sendMsgButtonActionPerformed

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        // TODO add your handling code here:
        // ChatBox CB = new ChatBox("Pete");
        //DialogBox.show();
        //new chatThread().start();
        String buddyName = (String)jList1.getSelectedValue();
        if(!buddiesOpen.containsKey(buddyName)) {
            //BuddyChat bc = new BuddyChat();
            BuddyChat bc = new BuddyChat(os, currentUser);
            bc.setTitle("Chat with " + buddyName);
            bc.setVisible(true);
            buddiesOpen.put(buddyName, bc);
        }
//        ChatBox dialog = new ChatBox(new javax.swing.JFrame(), true);
//        dialog.updateD("yes");
//        dialog.setVisible(true);
        //bc.updateD("yes");
        
    }//GEN-LAST:event_jList1ValueChanged

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
        // TODO add your handling code here:
        logout();
    }//GEN-LAST:event_logoutActionPerformed

    public void startClient() {
        this.setVisible(true);        
    }
    
    public void initializeConnection() {
        new ListenFromServer().start();
    }
    
    public String send(String message) {
        new sendToServer().start();
        return "";
    }
    
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Chat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Chat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Chat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Chat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        Chat c = new Chat();
        c.startClient();
        c.initializeConnection();

    }
    
    class ListenFromServer extends Thread {
        
        public void run() {
            while(true) {
                String received = null;
                try {
                    //inbox = (String) is.readObject();
                    received = (String)is.readObject();
                    //status.setText(received);
                } catch (Exception ioe) {
                    System.err.println(ioe);
                }
                
                String[] s = received.split(" ");
                int type = Integer.parseInt(s[0]);
                String un = s[1];
                switch(type) {
                    case 3: // Outgoing/Incoming message: "3 SENDER RECIPIENT MESSAGE"
                        String sender = un;
                        String text = s[3];
                        BuddyChat thisBuddy = null;
                        if(buddiesOpen.containsKey(sender)) {
                            thisBuddy = (BuddyChat)buddiesOpen.get(sender);
                            thisBuddy.updateDisplay(sender, text);
                        }
                        else {
                            BuddyChat bc = new BuddyChat(os, currentUser);
                            bc.setTitle("Chat with " + sender);
                            bc.setVisible(true);
                            bc.updateDisplay(sender, text);
                            buddiesOpen.put(sender, bc);
                        }
                        break;
                        
                    case 4: // Buddy ON notify: "4 Username"
                        buddiesOnline.addElement(un);
                        break;
                    case 5: // Buddy OFF notify: "5 Username"
                        buddiesOnline.removeElement(un);
                        break;
                    case 6: // User logon success: : "6 Username"
                        loginSuccess(un);
                        break;                        
                    case 7: // User logon fail: "7 Username"
                        loginFail(un);
                        break;
                  }
            }
                
        }
    }
    
    private void loginSuccess(String un) {
        createNewUserBox.hide();       
        JOptionPane.showMessageDialog(this, "You're logged in!",
                "Logged In", JOptionPane.INFORMATION_MESSAGE);
        loginPanel.hide();
        //buddiesOnline.addElement("John Doe");
        buddylistpanel.show();
        
    }
    
    private void loginFail(String un) {
        JOptionPane.showMessageDialog(this, "Incorrect Username or Password.",
            "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    private void logout() {
        String logout = "2 "+ currentUser;
        //new sendToServer().start();
        try {                    
            os.writeObject(logout);
        } catch (Exception ioe) {
            System.err.println(ioe);
        }
    }
    
    class sendToServer extends Thread {
        
        public void run() {
                try {                    
                    os.writeObject(outbox);
                } catch (Exception ioe) {
                    System.err.println(ioe);
                }
        }
    }
    
    class chatThread extends Thread {
        
        public void run() {
            //ChatBox dialog = new ChatBox(new javax.swing.JFrame(), true);
            //dialog.setVisible(true);
            //dialog.update(msg);
        }
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog DialogBox;
    private javax.swing.JLayeredPane buddylistpanel;
    private javax.swing.JButton closeChat;
    private javax.swing.JButton createNewUser;
    private javax.swing.JFrame createNewUserBox;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton login;
    private javax.swing.JLayeredPane loginPanel;
    private javax.swing.JButton logout;
    private javax.swing.JScrollPane messageFeed;
    private javax.swing.JPasswordField newPassword1;
    private javax.swing.JPasswordField newPassword2;
    private javax.swing.JTextField newUsername;
    private javax.swing.JPasswordField password;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JLabel passwordLabel1;
    private javax.swing.JLabel passwordLabel2;
    private javax.swing.JButton sendMsgButton;
    private javax.swing.JTextField textBox;
    private javax.swing.JTextField username;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JLabel usernameLabel1;
    // End of variables declaration//GEN-END:variables
}
