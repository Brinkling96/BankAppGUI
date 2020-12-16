import javax.swing.*;
import java.util.ArrayList;

public class TestingBankerDash {
    public static void main(String[]  args) {

        JFrame frame = BankingFrame.getInstance();
        ArrayList<User> temp = new ArrayList<>();

        Banker d = new Banker("banker","password".toCharArray(),0);
        temp.add(d);

        CustomerUser s = new CustomerUser("sdbrady","password".toCharArray(),1);

        temp.add(s);

        Bank bank = new Bank(temp);

        s.addAccount(new CheckingAccount(2131,s,12,bank),bank);

        bank.createTransaction(s.getAccount(s.getAccounts().get(0).accountID), "CheckingDeposit",1000,"USD");
        frame.add(new BankerDashboard(frame,d,bank));
        frame.pack();
        frame.setVisible(true);

    }
}
