import java.util.ArrayList;

public class Banker extends User{
    protected Bank bank;


    public Banker(String username, char[] password, int numUsers) {
        super(username, password, numUsers);
    }

    public Banker(String username, String password, String uid, String status) {
        super(username, password, uid, status);
    }

}
