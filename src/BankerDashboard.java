import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankerDashboard extends Dashboard{

    protected Bank bank;

    protected GUITable userAccountTable;

    //Account Table Options
    protected JButton viewAccountButton;
    protected JButton removeAccountButton;
    protected JPanel userTableActionPanel;

    //
    protected GUITable transcationTable;

    public BankerDashboard(JFrame host, User user, Bank bank) {
        super(host, user);
        this.bank = bank;

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

        this.transcationTable = new GUITable(new Object[][]{{1, "test", "Jello"}},new String[]{"Integer","String","String"});
        add(Box.createVerticalBox());
        add(transcationTable);
    }

    private void viewAccountAction() {
        //todo
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
