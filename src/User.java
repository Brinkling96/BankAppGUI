import java.util.ArrayList;

public abstract class User {

    public static int numUsers = 0;

    private String username;
    private char[] password;

    private String userID;

    public User(String username, char[] password) {
        this.username = username;
        this.password = password;
        this.userID = String.format("%04d", numUsers);
        numUsers++;
        DataKeeper.newUser(this);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public boolean isPasswordCorrect(char[] inputpassword){
        if(inputpassword == null){
            return false;
        }
        else if(inputpassword.length != password.length){
            return false;
        }

        for(int i=0; i<inputpassword.length; i++){
            if(password[i] != inputpassword[i]){
                return false;
            }
        }
        return true;
    }

}
