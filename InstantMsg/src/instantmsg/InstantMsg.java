package instantmsg;

import java.io.Serializable;

/**
 *
 * @author pioni_000
 */
public class InstantMsg implements Serializable {

    protected static final long serialVersionUID = 1112122200L;

    // The different types of message sent by the Client
    // WHOISIN to receive the list of the users connected
    // MESSAGE an ordinary message
    // LOGOUT to disconnect from the Server

    static final int CREATEACCOUNT = 0, LOGON = 1, LOGOFF = 2, INOUTMESSAGE = 3,
            BUDDYON = 4, BUDDYOFF = 5, LOGGEDONSUCCESS = 6, LOGGEDONFAIL = 7;
    private int type;
    private String message;

    // constructor
    InstantMsg(int type, String message) {
        this.type = type;
        this.message = message;
    }

    // getters
    int getType() {
        return type;
    }

    String getMessage() {
        return message;
    }

}
