import javax.swing.*;
import java.util.ArrayList;

public class TestingBankerDash {
    public static void main(String[]  args) {
        JFrame frame = BankingFrame.getInstance();
        ArrayList<User> temp = new ArrayList<>();
        Bank bank = new Bank(temp);
        Banker d = new Banker("banker","pass".toCharArray(),0, bank);
        temp.add(d);

        CustomerUser s = new CustomerUser("sdbrady","password".toCharArray(),0);
        s.addAccount(new CheckingAccount(1000,s,s.getNumAccounts()), bank);
        temp.add(s);


        bank.createTransaction(s.getAccount(s.getAccounts().get(0).accountID), "CheckingDeposit",1000,"USD");
        frame.add(new BankerDashboard(frame,d,bank));
        frame.pack();
        frame.setVisible(true);
    }
}
