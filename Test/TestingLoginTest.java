import javax.swing.*;
import java.util.ArrayList;

public class TestingLoginTest {
    public static void main(String[]  args) {
        JFrame frame = BankingFrame.getInstance();
        CustomerUser s = new CustomerUser("sdbrady","password".toCharArray(),0);
        s.addAccount(new CheckingAccount(1000,s,s.getNumAccounts()));
        ArrayList<User> temp = new ArrayList<>();
        temp.add(s);

        Bank bank = new Bank(temp);

        Banker d = new Banker("banker","pass".toCharArray(),0);

        temp.add(d);

        frame.add(new LoginScreen(frame,bank));
        frame.pack();
        frame.setVisible(true);
    }
}
