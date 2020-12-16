import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class AddSecurityAccount extends AddAccountDialog{

    protected GUITable savingTable;

    public AddSecurityAccount(Window owner, CustomerUser user, Bank bank) {
        super(owner, user,"Transfer: ", bank);

        savingTable = new GUITable("Savings Accounts: ", createACTable(),new String[]{"Account ID", "Balance"},
                new Class[]{String.class,Integer.class});

        this.host.add(savingTable,0);

        this.pack();
        this.setVisible(true);

    }

    private Object[][] createACTable(){
        CustomerUser user = (CustomerUser) this.user;

        ArrayList<Account> accounts = user.getAccounts();
        ArrayList<SavingsAccount> savingsAccounts = new ArrayList<>();
        for(Account act : accounts){
            if(act instanceof SavingsAccount){
                savingsAccounts.add((SavingsAccount) act);
            }
        }

        Object[][] tableData = new Object[savingsAccounts.size()][Account.numMemsToDisplay];
        for(int i = 0; i<user.getAccounts().size(); i++){
            Account account = user.getAccounts().get(i);
            Object[] row = tableData[i];
            row[0] = account.getAccountID();
            row[1] = account.getBalance();

        }
        return tableData;
    }

    @Override
    protected void okButtonAction(ActionEvent e) {
        //todo
    }
}
