import javax.swing.*;
import java.util.ArrayList;

public class TestingBankerDash {
    public static void main(String[]  args) {
        JFrame frame = new JFrame("BankerDash Test");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        CustomerUser s = new CustomerUser("sdbrady","password".toCharArray());
        s.addAccount(new CheckingAccount(1000));
        ArrayList<User> temp = new ArrayList<>();
        temp.add(s);



        Banker d = new Banker("sdbrady","password".toCharArray());

        temp.add(d);
        Bank bank = new Bank(temp);
        frame.add(new BankerDashboard(frame,s,bank));
        frame.pack();
        frame.setVisible(true);
    }
}
