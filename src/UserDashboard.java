import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UserDashboard extends Dashboard{



    //User Account Table
    protected GUITable moneyAccountTable;

    //Account Table Options
    protected JButton addAccountButton;
    protected JButton removeAccountButton;
    protected JButton depositButton;
    protected JButton withdrawnButton;
    protected JButton displayTransactionButton;
    protected JPanel accountTableActionPanel;

    //Transcation tables
    protected GUITable transactionTable;

    public UserDashboard(Window window, CustomerUser user, Bank bank) {
        //HOST PANEL SETUP
        super(window, user,bank);

        ////////////////////////////////////////////////////////////////////////////
        //Accounts Table
        Object[][] tableData = new Object[user.getAccounts().size()][Account.numMemsToDisplay];
        Class[] aclasses = new Class[2];
        aclasses[0] = String.class;
        aclasses[1] = Integer.class;

        for(int i = 0; i<user.getAccounts().size(); i++){
            Account account = user.getAccounts().get(i);
            Object[] row = tableData[i];
            row[0] = account.getAccountID();
            row[1] = account.getBalance();

        }

        this.moneyAccountTable = new GUITable("Account Table: ",tableData,new String[]{"AccountID","Balance"},aclasses);
        add(Box.createVerticalGlue());
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

            this.displayTransactionButton = new JButton("Display");
            this.displayTransactionButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    displayTransactions();
                }
            });

            this.accountTableActionPanel.add(addAccountButton);
            this.accountTableActionPanel.add(removeAccountButton);
            this.accountTableActionPanel.add(depositButton);
            this.accountTableActionPanel.add(withdrawnButton);
            this.accountTableActionPanel.add(displayTransactionButton);
            add(Box.createVerticalBox());
            add(accountTableActionPanel);
        }
        /////////////////////////////////////////////////////////////////////////////////////////
        //Transcation table
        Class[] tclasses = new Class[6];
        tclasses[0] = String.class;
        tclasses[1] = String.class;
        tclasses[2] = String.class;
        tclasses[3] = String.class;
        tclasses[4] = Integer.class;
        tclasses[5] = String.class;
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        
        
        this.transactionTable = new GUITable("Transaction Table: ",createTDTable(transactions),new String[]{"TransactionID","TranscationType","Date","Amount", "Currency"}, tclasses);
        add(Box.createVerticalGlue());
        add(transactionTable);


        /////////////////////////////////////////////////////////////////////
    }



    private Object[][] createTDTable(ArrayList<Transaction> tds){
        ArrayList<Transaction> userTds = tds;
        Object[][] returnlist = new Object[userTds.size()][Transaction.numMemsToDisplay-2];

        for(int i =0; i < returnlist.length; i++){
            Object[] returnRow = returnlist[i];
            Transaction transaction = tds.get(i);
            int j =0;
            returnRow[j++] = transaction.getID();
            returnRow[j++] = transaction.getTransactionType();
            returnRow[j++] = transaction.getTime();
            returnRow[j++] = transaction.getAmount();
            returnRow[j++] = transaction.getCurrency();
        }

        return returnlist;
    }

    protected void stockMarketAction(){
        //todo
    }

    private void addAccountButtonAction() {
        MoneyAccountTypeSelectionMenu d = new MoneyAccountTypeSelectionMenu(window, ((CustomerUser) user), this.bank);
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
                    //todo print this to the user instead of the command line
                    if(account.getBalance() < this.bank.getClosureFee()){
                        System.out.println("Can't close account unless value is greater than the closer fee of $" + this.bank.getClosureFee());
                    }else{
                        ((CustomerUser) user).removeAccount(account, this.bank);
                        this.moneyAccountTable.tableModel.removeRow(selectedRow);
                    }
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

    public void displayTransactions() {
        int selectedRow = this.moneyAccountTable.table.getSelectedRow();
        Account account = getSelectedAccount(selectedRow);
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        if (account != null) {
            transactions = DataKeeper.getTransactionsFromAccount(account);
            int num = this.transactionTable.tableModel.getRowCount();
            for (int i = 0; i < num; i++) {
                this.transactionTable.tableModel.removeRow(i);
            }
            for (Transaction transaction : transactions) {
                Object[] newData = new Object[] {
                    transaction.getID(),
                    transaction.getTransactionType(),
                    transaction.getTime().toString(),
                    transaction.getAmount()
                };
                this.transactionTable.addRowToTable(newData);
            }
        }
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
            JDialog wWindow = new WithdrawMenu(window,account,this.bank);

        }
        Object[] input = new Object[]{account.accountID, account.balance};
        this.moneyAccountTable.reloadRowData(selectedRow,input);
    }
}


