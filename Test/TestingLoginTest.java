import javax.swing.*;
import java.util.ArrayList;

public class TestingLoginTest {
    public static void main(String[]  args) {
        JFrame frame = BankingFrame.getInstance();
        ArrayList<User> temp = new ArrayList<>();
        Bank bank = new Bank(temp);
        Banker d = new Banker("banker","pass".toCharArray(),0, bank);

        temp.add(d);
        CustomerUser s = new CustomerUser("sdbrady","password".toCharArray(),0);
        s.addAccount(new CheckingAccount(1000,s,s.getNumAccounts()), bank);
        temp.add(s);



        frame.add(new LoginScreen(frame,bank));
        frame.pack();
        frame.setVisible(true);
    }
}
