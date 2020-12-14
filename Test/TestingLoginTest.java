import javax.swing.*;
import java.util.ArrayList;

public class TestingLoginTest {
    public static void main(String[]  args) {
        JFrame frame = BankingFrame.getInstance();
        CustomerUser s = new CustomerUser("sdbrady","password".toCharArray());
        s.addAccount(new CheckingAccount(1000,s));
        ArrayList<User> temp = new ArrayList<>();
        temp.add(s);

        Bank bank = new Bank(temp);

        Banker d = new Banker("banker","pass".toCharArray());

        temp.add(d);

        frame.add(new LoginScreen(frame,bank));
        frame.pack();
        frame.setVisible(true);
    }
}
