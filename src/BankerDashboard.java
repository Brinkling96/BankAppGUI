/******************************************************************************
 * Class: BankerDashboard.java
 * Author: Sarah Shahinpour, Sean Brady, Shuaike Zhou, 
 *******************************************************************************/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//Displays the different functionalities of the banker's account
public class BankerDashboard extends MainDashboard {

    protected GUITable userAccountTable;

    //Account Table Options
    protected JButton viewAccountButton;
    protected JButton removeAccountButton;
    protected JButton setTimeButton;
    protected JButton viewDailyReportButton;
    protected JPanel userTableActionPanel;
    protected GUITable transactionTable;

    public BankerDashboard(Window window, Banker banker, Bank bank) {
        super(window, banker, bank);

        this.setTimeButton = new JButton("Set Time");
        this.setTimeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTimeButtonAction();
            }
        });
        this.viewDailyReportButton = new JButton("View Daily Report");
        this.viewDailyReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewDailyReport();
            }
        });
        this.generalActionsPanel.add(viewDailyReportButton);

        //////////////////////////////////////////////////////////////////
        //User Account table
        Object[][] tableData = new Object[bank.users.size()][CustomerUser.numMembersToDisplay];
        Class[] aclasses = new Class[3];
        aclasses[0] = String.class;
        aclasses[1] = String.class;
        aclasses[2] = Integer.class;

        for (int i = 0; i < bank.users.size(); i++) {
            User account = bank.users.get(i);

            if (account instanceof CustomerUser) {
                Object[] row = tableData[i];
                int j = 0;
                row[j++] = account.getUserID();
                row[j++] = account.getUsername();
                row[j++] = ((CustomerUser) account).getAccounts().size();
            }

        }


        this.userAccountTable = new GUITable("Users Table: ",tableData, new String[]{"AccountID", "Username", "Number of Accounts"}, aclasses);
        add(Box.createVerticalBox());
        add(userAccountTable);
        //////////////////////////////////////////////////////////////////////////
        //User Table actions
        this.userTableActionPanel = new JPanel();

        this.viewAccountButton = new JButton("View User");
        this.viewAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewAccountAction();
            }
        });

        this.userTableActionPanel.add(viewAccountButton);

        this.removeAccountButton = new JButton("Remove Account");
        this.removeAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAccountButtonActionPerformed();
            }
        });

        
        this.userTableActionPanel.add(removeAccountButton);


        add(Box.createVerticalBox());
        add(userTableActionPanel);
        ///////////////////////////////////////////////////
        //Transactions

        ArrayList<Transaction> tds = new ArrayList<Transaction>();
        Class[] tclasses = new Class[6];
        tclasses[0] = String.class;
        tclasses[1] = String.class;
        tclasses[2] = String.class;
        tclasses[3] = Integer.class;
        tclasses[4] = String.class;
        this.transactionTable = new GUITable("Transactions table: ",createTDTable(tds), new String[]{"TransactionID", "TranscationType", "Date", "Amount", "Currency"}, tclasses);

        add(Box.createVerticalBox());
        add(transactionTable);
    }

    private Object[][] createTDTable(ArrayList<Transaction> tds) {
        Object[][] returnlist = new Object[tds.size()][Transaction.numMemsToDisplay];

        for (int i = 0; i < returnlist.length; i++) {
            Object[] returnRow = returnlist[i];
            Transaction transaction = tds.get(i);
            int j = 0;
            returnRow[j++] = transaction.getID();
            returnRow[j++] = transaction.getTransactionType();
            returnRow[j++] = transaction.getTime();
            returnRow[j++] = transaction.getAmount();
            returnRow[j++] = transaction.getCurrency();
        }

        return returnlist;
    }

    private void setTimeButtonAction() {
        //todo, if we need it
        JDialog dialog = new BankTimeSetter(window);
    }

    private void viewAccountAction() {
        System.out.println("Viewing accounts");
        int selectedRow = this.userAccountTable.table.getSelectedRow();
        if (selectedRow >= 0) {
            String accountID = (String) this.userAccountTable.table.getValueAt(selectedRow, 0);
            if (accountID != null) {
                JDialog dialog = new JDialog();
                User user = bank.getUser(accountID);
                ((CustomerUser) user).loadAccounts(DataKeeper.getAccountsFromUser(user));
                BankDashboard dash = new UserDashboard(dialog, ((CustomerUser) bank.getUser(accountID)), bank);//todo
                dialog.add(dash);
                dialog.pack();
                dialog.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "UserID Not Found", "User Dash pull", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void viewDailyReport() {
        JDialog drwindow = new DailyReportMenu(window,bank);
        ArrayList<Transaction> transactions = bank.getDailyReport();
        // Rows have to be removed from buttom to top
        int num = this.transactionTable.tableModel.getRowCount();
        if (num > 0) {
            for (int i = num - 1; i >= 0; i--) {
                this.transactionTable.tableModel.removeRow(i);
            }
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

    @Override
    protected void stockMarketAction() {
        this.window.setVisible(false);
        this.window.remove(this);
        this.window.add(new BankerStockMarketDash(window,(Banker) user,bank));
        this.window.pack();
        this.window.setVisible(true);
    }

    protected void removeAccountButtonActionPerformed() {
        int result = JOptionPane.showConfirmDialog(this, "Are you sure?", "Confirm Account Deletion", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            int selectedRow = this.userAccountTable.table.getSelectedRow();
            if (selectedRow >= 0) {
                User user = getSelectedUser(selectedRow);
                if(user != null) {
                    bank.removeUser(user);
                    DataKeeper.updateUser(user, "remove");
                    userAccountTable.tableModel.removeRow(selectedRow);
                }

            }
        }
    }
    public User getSelectedUser(int selectedRow){
        User user = null;
        if (selectedRow >= 0) {
            String actnum = (String) this.userAccountTable.table.getValueAt(selectedRow, 0);
            user = bank.getUser(actnum);
        }
        return user;
    }
}
