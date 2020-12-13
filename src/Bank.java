import java.util.ArrayList;

public class Bank {
    protected ArrayList<User> users;

    public Bank(ArrayList<User> users) {
        this.users = users;
    }

    public User getUser(String username, char[] password){
        for(User user : users){
            if(user.getUsername().equals(username)){
                if(user.isPasswordCorrect(password)){
                    return user;
                }
            }
        }
        return null;
    }

    public void addUser(CustomerUser user){
        this.users.add(user);
    }

}
