package imserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Lindsey
 */
public class Chat extends javax.swing.JFrame {

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    public boolean succesfulConn;
    public String msg;
    public ThreadedIMServer server;
    Socket serverSocket;
    ObjectInputStream readServer;
    ObjectOutputStream writeServer;
    
    /**
     * Creates new form ChatBox
     */
    public Chat() {
        initComponents();
        server = new ThreadedIMServer();
        try {
            serverSocket = new Socket(InetAddress.getByName("163.11.2.127"), 4225);
            readServer  = new ObjectInputStream(serverSocket.getInputStream());
            writeServer  = new ObjectOutputStream(serverSocket.getOutputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
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
                    writeServer.writeBytes(pw);
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
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
        status = new javax.swing.JLabel();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
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
        setTitle("chatWindow");
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
        username.setText("Enter Username");

        passwordLabel.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        passwordLabel.setText(" Password");

        password.setText("jPasswordField1");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(createNewUser)
                .addGap(48, 48, 48))
        );
        loginPanel.setLayer(usernameLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        loginPanel.setLayer(username, javax.swing.JLayeredPane.DEFAULT_LAYER);
        loginPanel.setLayer(passwordLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        loginPanel.setLayer(password, javax.swing.JLayeredPane.DEFAULT_LAYER);
        loginPanel.setLayer(createNewUser, javax.swing.JLayeredPane.DEFAULT_LAYER);
        loginPanel.setLayer(login, javax.swing.JLayeredPane.DEFAULT_LAYER);

        status.setText("Welcome");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(closeChat, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(loginPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))))
            .addGroup(layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(status)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(closeChat)
                .addGap(18, 18, 18)
                .addComponent(loginPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(status)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_loginMouseClicked

    private void createNewUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_createNewUserMouseClicked
        //Close login screen
        loginPanel.hide();
        //Open window to create new user
        createNewUserBox.show();

    }//GEN-LAST:event_createNewUserMouseClicked

    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
        String un = username.getText();
        String pw = password.getText();
        try {
            //Username logged in
            if (logUserIn(un,pw)) {
                JOptionPane.showMessageDialog(this, "You're logged in!",
                        "Logged In", JOptionPane.INFORMATION_MESSAGE);
                loginPanel.hide();
            } //Username exists
            else {
                //Password is wrong or username doesn't exist
                    JOptionPane.showMessageDialog(this, "Incorrect Username or Password.",
                            "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_loginActionPerformed

    private void closeChat(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeChat
        int result = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to exit the application?",
                "Exit Application",
                JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            this.dispose();
        }
    }//GEN-LAST:event_closeChat

    private void createNewAccountAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createNewAccountAction
        //Username already exists
        String un = newUsername.getText();
        String password1  = newPassword1.getText();
        String password2 = newPassword2.getText();
        try {
            //Password mismatch
            if (!(password1.equals(password2))) {
                JOptionPane.showMessageDialog(createNewUserBox, "Passwords must match.",
                        "Password error", JOptionPane.ERROR_MESSAGE);
            } //Username and passwords are good to be created
            else {
                if(logNewUser(un,password1)){
                    JOptionPane.showMessageDialog(createNewUserBox, "This username already exists.",
                    "Username error", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(this, "You're logged in!",
                        "Logged In", JOptionPane.INFORMATION_MESSAGE);
                    createNewUserBox.hide();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_createNewAccountAction

    public void startClient() {
        this.setVisible(true);        
    }
    
    public void initializeConnection() {
        new ListenFromServer().start();
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
                try {
                    
                    Socket IMServer = new Socket(InetAddress.getByName("localhost"), 4225);
                    ObjectInputStream sInput  = new ObjectInputStream(IMServer.getInputStream());

                    String msg = (String) sInput.readObject();
                    status.setText(msg);

        //            Send login info to server from here
        //            Recall:
        //            1  - LOGON 
        //            From client to server
        //            Format:  1 USERNAME PASSWORD
        //            Example: 1 mzimmerm qaz123


                } catch (Exception ioe) {
                    System.err.println(ioe);
                }
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeChat;
    private javax.swing.JButton createNewUser;
    private javax.swing.JFrame createNewUserBox;
    private javax.swing.JButton jButton1;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JButton login;
    private javax.swing.JLayeredPane loginPanel;
    private javax.swing.JPasswordField newPassword1;
    private javax.swing.JPasswordField newPassword2;
    private javax.swing.JTextField newUsername;
    private javax.swing.JPasswordField password;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JLabel passwordLabel1;
    private javax.swing.JLabel passwordLabel2;
    private javax.swing.JLabel status;
    private javax.swing.JTextField username;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JLabel usernameLabel1;
    // End of variables declaration//GEN-END:variables
}
