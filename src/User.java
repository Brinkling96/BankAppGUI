import java.time.LocalDateTime;

public abstract class User {

    private String username;
    private char[] password;
    private String userID;
    private LocalDateTime lastLogin;
    private LocalDateTime currentLogin;
    public User(String username, char[] password, int userNumber) {
        this.username = username;
        this.password = password;
        this.userID = String.format("%04d", userNumber);
        this.lastLogin = Clock.getClock().getTime();
        this.currentLogin = this.lastLogin;
    }

    public User(String username, String password, String uid, String time) {
        this.username = username;
        this.password = password.toCharArray();
        this.userID = uid;
        this.lastLogin = Clock.getClock().getLocalDateTimeFromString(time);
        this.currentLogin = Clock.getClock().getTime();
    }

    public LocalDateTime getCurrentLogin() {
        return this.currentLogin;
    }

    public LocalDateTime getLastLogin() {
        return this.lastLogin;
    }

    public void logout() {
        this.lastLogin = this.currentLogin;
        DataKeeper.updateUser(this, "logout");
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

    public String toString() {
        String out = "";
        out += getUserID() + ",";
        out += getUsername() + ",";
        out += String.valueOf(getPassword()) + ",";
        if (this instanceof CustomerUser) {
            out += "customer,";
        } else {
            out += "banker,";
        }
        out += Clock.getClock().getTimeAsString(currentLogin);
        out += "\n";
        return out;
    }
}
