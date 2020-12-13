import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BankerDashboard extends Dashboard{



    protected GUITable userAccountTable;

    //Account Table Options
    protected JButton viewAccountButton;
    protected JButton removeAccountButton;
    protected JButton setTimeButton;
    protected JPanel userTableActionPanel;

    //
    protected GUITable transcationTable;

    public BankerDashboard(JFrame host, User user, Bank bank) {
        super(host, user,bank);


        this.setTimeButton = new JButton("Set Time");
        this.setTimeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTimeButtonAction();
            }
        });
        this.generalActionsPanel.add(setTimeButton);

        //////////////////////////////////////////////////////////////////
        //User Account table
        Object[][] tableData = new Object[bank.users.size()-1][CustomerUser.numMembersToDisplay];


        for(int i = 0; i<bank.users.size(); i++){
            User account = bank.users.get(i);

            if(account instanceof CustomerUser) {
                Object[] row = tableData[i];
                int j =0;
                row[j++] = account.getUserID();
                row[j++] = account.getUsername();
                row[j++] = ((CustomerUser) account).getAccounts().size();
            }

        }

        this.userAccountTable = new GUITable(tableData,new String[]{"AccountID","Username","Number of Accounts"});
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
        //Transcations

        ArrayList<Transaction> tds = bank.getTransactions();


        this.transcationTable = new GUITable(createTDTable(tds),new String[]{"TransactionID","TranscationType","UserID","AccountID","Date","Amount"});

        add(Box.createVerticalBox());
        add(transcationTable);
    }
    private Object[][] createTDTable(ArrayList<Transaction> tds){
        Object[][] returnlist = new Object[tds.size()][Transaction.numMemsToDisplay];

        for(int i =0; i < returnlist.length; i++){
            Object[] returnRow = returnlist[i];
            Transaction transaction = tds.get(i);
            int j =0;
            returnRow[j++] = transaction.getID();
            returnRow[j++] = transaction.getTransactionType();
            returnRow[j++] = "null for now"; //todo
            returnRow[j++] = transaction.getAccount().getAccountID();
            returnRow[j++] = transaction.getTime().toString();
            returnRow[j++] = transaction.getAmount();
        }

        return returnlist;
    }


    private void setTimeButtonAction() {
        //todo
        JDialog dialog = new BankTimeSetter(this.host,true);
    }

    private void viewAccountAction() {
        int selectedRow = this.userAccountTable.table.getSelectedRow();
        if(selectedRow >= 0) {
            String accountID = (String) this.userAccountTable.table.getValueAt(selectedRow, 0);
            JDialog dialog = new JDialog(new UserDashboard(null,bank.get));
        }
    }

    @Override
    protected void stockMarketAction() {

    }

    @Override
    protected void removeAccountButtonActionPerformed() {
        /*
        int result = JOptionPane.showConfirmDialog(this, "Are you sure?", "Confirm Account Deletion", JOptionPane.YES_NO_OPTION);
        if(result == JOptionPane.OK_OPTION){
            int selectedRow = this.moneyAccountTable.table.getSelectedRow();
            if(selectedRow >= 0){
                //todo
            /*
            CheckingAccount account = getSelectedAccount(selectedRow);
            if(account != null){
                user.getAccounts().remove(account);
            }

        }

         */
    }
}
