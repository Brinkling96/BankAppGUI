import javax.swing.*;
import java.util.ArrayList;

public class TestingLoginTest {
    public static void main(String[]  args) {
        JFrame frame = new JFrame("Test");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        BankUser s = new BankUser("sdbrady","password".toCharArray());
        s.addCheckingAccount(new CheckingAccount(1000));
        ArrayList<BankUser> temp = new ArrayList<>();
        temp.add(s);

        Bank bank = new Bank(temp);

        frame.add(new LoginScreen(frame,bank));
        frame.pack();
        frame.setVisible(true);
    }
}
