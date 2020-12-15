import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UserBankDashboard extends MainBankDashboard {



    //User Account Table
    protected GUITable moneyAccountTable;

    //Account Table Options
    protected JButton addAccountButton;
    protected JButton removeAccountButton;
    protected JButton depositButton;
    protected JButton withdrawnButton;
    protected JPanel accountTableActionPanel;


    //Transcation tables
    protected GUITable transcationTable;

    public UserBankDashboard(Window window, CustomerUser user, Bank bank) {
        //HOST PANEL SETUP
        super(window, user,bank);

        ////////////////////////////////////////////////////////////////////////////
        //Accounts Table
        Object[][] tableData = new Object[user.getAccounts().size()][Account.numMemsToDisplay];


        for(int i = 0; i<user.getAccounts().size(); i++){
            Account account = user.getAccounts().get(i);
            Object[] row = tableData[i];
            row[0] = account.getAccountID();
            row[1] = account.getBalance();

        }

        this.moneyAccountTable = new GUITable(tableData,new String[]{"AccountID","Balance"});
        add(Box.createVerticalBox());
        add(moneyAccountTable);

        //////////////////////////////////////////////////////////////////////////////
        //Accounts Actions
        {
            this.accountTableActionPanel = new JPanel();
            this.addAccountButton = new JButton("Add Account");
            this.addAccountButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    addAccountButtonAction();
                }
            });

            this.removeAccountButton = new JButton("Remove Account");
            this.removeAccountButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    removeAccountButtonActionPerformed();
                }
            });

            this.depositButton = new JButton("Deposit");
            this.depositButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    deposit();
                }
            });

            this.withdrawnButton = new JButton("Withdraw");
            this.withdrawnButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    withdraw();
                }
            });

            this.accountTableActionPanel.add(addAccountButton);
            this.accountTableActionPanel.add(removeAccountButton);
            this.accountTableActionPanel.add(depositButton);
            this.accountTableActionPanel.add(withdrawnButton);
            add(Box.createVerticalBox());
            add(accountTableActionPanel);
        }
        /////////////////////////////////////////////////////////////////////////////////////////
        //Transcation table

        this.transcationTable = new GUITable(createTDTable(bank.getTransactions()),new String[]{"TransactionID","TranscationType","AccountID","Date","Amount"});
        add(Box.createVerticalBox());
        add(transcationTable);


        /////////////////////////////////////////////////////////////////////
    }



    private Object[][] createTDTable(ArrayList<Transaction> tds){
        ArrayList<Transaction> userTds = new ArrayList<>();

        for(Transaction t: tds){
            for(Account a: ((CustomerUser) user).getAccounts()){
                if(t.getAccount().getAccountID() == a.getAccountID()){
                    userTds.add(t);
                }
            }
        }


        Object[][] returnlist = new Object[userTds.size()][Transaction.numMemsToDisplay-1];

        for(int i =0; i < returnlist.length; i++){
            Object[] returnRow = returnlist[i];
            Transaction transaction = tds.get(i);
            int j =0;
            returnRow[j++] = transaction.getID();
            returnRow[j++] = transaction.getTransactionType();
            returnRow[j++] = transaction.getAccount().getAccountID();
            returnRow[j++] = transaction.getTime().toString();
            returnRow[j++] = transaction.getAmount();
        }

        return returnlist;
    }

    protected void stockMarketAction(){
        this.window.setVisible(false);
        this.window.remove(this);
        this.window.add(new UserStockMarketBankDashboard(window,new CheckingAccount(user.getUserID(), "500"),bank));
        this.window.setVisible(true);
    }

    private void addAccountButtonAction() {
        MoneyAccountTypeSelectionMenu d = new MoneyAccountTypeSelectionMenu(window, ((CustomerUser) user));
        Account act = d.account;
        if (act != null) {
            moneyAccountTable.addRowToTable(new Object[]{act.getAccountID(), act.getBalance()});
        }
    }

    public void removeAccountButtonActionPerformed() {
        int selectedRow = this.moneyAccountTable.table.getSelectedRow();
        if (selectedRow >= 0) {
            int result = JOptionPane.showConfirmDialog(this, "Are you sure?", "Confirm Account Deletion", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                //todo
                Account account = getSelectedAccount(selectedRow);
                if (account != null) {
                    ((CustomerUser) user).removeAccount(account);
                    this.moneyAccountTable.tableModel.removeRow(selectedRow);
                }
            }
        }
    }

    public Account getSelectedAccount(int selectedRow){
        Account act = null;
        if (selectedRow >= 0) {
            String actnum = (String) this.moneyAccountTable.table.getValueAt(selectedRow, 0);
            act = ((CustomerUser) user).getAccount(actnum);
        }
        return act;
    }



    public void deposit(){
        int selectedRow = this.moneyAccountTable.table.getSelectedRow();
        Account account = getSelectedAccount(selectedRow);
        if(account != null){
             JDialog dWindow = new DepositMenu(window,account);
        }
        Object[] input = new Object[]{account.accountID, account.balance};
        this.moneyAccountTable.reloadRowData(selectedRow,input);

    }

    public void withdraw(){
        int selectedRow = this.moneyAccountTable.table.getSelectedRow();

        Account account = getSelectedAccount(selectedRow);
        if(account != null){
            JDialog wWindow = new WithdrawMenu(window,account);

        }
        Object[] input = new Object[]{account.accountID, account.balance};
        this.moneyAccountTable.reloadRowData(selectedRow,input);


    }
}


