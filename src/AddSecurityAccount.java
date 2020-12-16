/******************************************************************************
 * Class: AddSecurityAccount.java
 * Author: Sarah Shahinpour, Sean Brady, Shuaike Zhou, 
 *******************************************************************************/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * Dialog that allows the user to create a securities account
 */ 
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
        SavingsAccount act = (SavingsAccount) getSelectedAccount(this.savingTable.table.getSelectedRow());
        String warnings = "";
        int balance = 0;

        if (act.getBalance() < SecurityAccount.STARTING_BALANCE) {
            warnings += "Need atleast " + SecurityAccount.STARTING_BALANCE + " in savings to create a security account!/n";
        } else if (balanceField.getText().isEmpty()) {
            warnings += "Need to input a Security Account balance!";
        } else {
            try {
                balance = Integer.parseInt(balanceField.getText());
                if (balance > act.getBalance()) {
                    warnings += "Transfer must be less than balance";
                } else if (balance < SecurityAccount.STARTING_BALANCE) {
                    warnings += "Transfer must be more than " + SecurityAccount.STARTING_BALANCE;
                }
            } catch (NumberFormatException err) {
                warnings += "Transfer must be a number!";
            }
            if (warnings != "") {
                JOptionPane.showMessageDialog(this, warnings, "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                act.setBalance(act.getBalance() - balance);
                this.account = new SecurityAccount(balance, user, bank);
                user.addAccount(account, this.bank);
                this.dispose();
            }
        }
    }
    private Account getSelectedAccount(int selectedRow){
        Account act = null;
        if (selectedRow >= 0) {
            String actnum = (String) this.savingTable.table.getValueAt(selectedRow, 0);

            act = ((CustomerUser) user).getAccount(actnum);
            if(!(act instanceof SavingsAccount)){
                act =null;
            }
        }
        return act;
    }
}
