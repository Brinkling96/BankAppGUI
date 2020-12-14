import javax.swing.*;
import java.util.ArrayList;

public class TestingLoginThingy {
    public static void main(String[]  args) {
        JFrame frame = BankingFrame.getInstance();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        CustomerUser s = new CustomerUser("sdbrady","password".toCharArray());
        s.addAccount(new CheckingAccount(1000,s));
        ArrayList<User> temp = new ArrayList<>();
        temp.add(s);

        Bank bank = new Bank(temp);

        frame.add(new LoginScreen(frame,bank));
        frame.pack();
        frame.setVisible(true);
    }
}
