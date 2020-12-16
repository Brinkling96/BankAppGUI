import javax.swing.*;
import java.util.ArrayList;

public class TestingLoginThingy {
    public static void main(String[]  args) {
        JFrame frame = BankingFrame.getInstance();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ArrayList<User> temp = new ArrayList<>();
        Bank bank = new Bank(temp);
        CustomerUser s = new CustomerUser("sdbrady","password".toCharArray(),0);
        s.addAccount(new CheckingAccount(1000,s,bank), bank);
        temp.add(s);


        frame.add(new LoginScreen(frame,bank));
        frame.pack();
        frame.setVisible(true);
    }
}
