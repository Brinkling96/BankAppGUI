import javax.swing.*;
import java.util.ArrayList;

public class TestingBankerDash {
    public static void main(String[]  args) {
        JFrame frame = BankingFrame.getInstance();
        CustomerUser s = new CustomerUser("sdbrady","password".toCharArray(),0);
        s.addAccount(new CheckingAccount(1000,s,s.getNumAccounts()));
        ArrayList<User> temp = new ArrayList<>();
        temp.add(s);



        Banker d = new Banker("banker","pass".toCharArray(),0);


        temp.add(d);
        Bank bank = new Bank(temp);

        bank.createTransaction(s.getAccount(s.getAccounts().get(0).accountID), "CheckingDeposit",1000,"USD");
        frame.add(new BankerBankDashboard(frame,d,bank));
        frame.pack();
        frame.setVisible(true);
    }
}
