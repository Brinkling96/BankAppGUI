import javax.swing.*;
import java.util.ArrayList;

public class testingUserDash {
    public static void main(String[]  args) {
        JFrame frame = BankingFrame.getInstance();
        CustomerUser s = new CustomerUser("sdbrady","password".toCharArray());
        s.addAccount(new CheckingAccount(1000));

        CustomerUser d = new CustomerUser("Barack","1776".toCharArray());
        d.addAccount(new CheckingAccount(2000));

        ArrayList<User> temp = new ArrayList<>();

        temp.add(s);
        temp.add(d);


        Bank bank = new Bank(temp);

        bank.createTransaction(s.getAccount(0),"Checking", 1000,"USD");


        frame.add(new UserDashboard(frame,d,bank));
        frame.pack();
        frame.setVisible(true);
    }
}
