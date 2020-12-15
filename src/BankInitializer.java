import javax.swing.*;
import java.util.ArrayList;

public class BankInitializer {
    public BankInitializer() {

    }

    public void initialize() {
        JFrame frame = BankingFrame.getInstance();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(new LoginScreen(frame,createBank()));
        frame.pack();
        frame.setVisible(true);
    }

    private Bank createBank() {
        Bank bank = DataKeeper.initBank();
        if (bank == null) {
            ArrayList<User> users = new ArrayList<User>();
            bank = new Bank(users);
        }
        return bank;
    }

}