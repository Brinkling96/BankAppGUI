import javax.swing.*;
import java.util.ArrayList;

public class testingUserDash {
    public static void main(String[]  args) {
        JFrame frame = BankingFrame.getInstance();
        CustomerUser s = new CustomerUser("sdbrady","password".toCharArray(),0);
        s.addAccount(new CheckingAccount(s.getUserID(),"1000"));

        CustomerUser d = new CustomerUser("Barack","1776".toCharArray(),1);
        d.addAccount(new CheckingAccount(d.getUserID(),"2000"));

        ArrayList<User> temp = new ArrayList<>();

        temp.add(s);
        temp.add(d);


        Bank bank = new Bank(temp);

        bank.createTransaction(s.getAccount(s.getAccounts().get(0).accountID),"Checking", 1000,"USD");


        frame.add(new UserBankDashboard(frame,d,bank));
        frame.pack();
        frame.setVisible(true);
    }
}
