import java.util.ArrayList;

public class Bank {
    protected ArrayList<BankUser> users;

    public Bank(ArrayList<BankUser> users) {
        this.users = users;
    }

    public BankUser getUser(String username, char[] password){
        for(BankUser user : users){
            if(user.getUsername().equals(username)){
                if(user.isPasswordCorrect(password)){
                    return user;
                }
            }
        }
        return null;
    }

    public void addUser(BankUser user){
        this.users.add(user);
    }

}
