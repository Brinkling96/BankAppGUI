import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserDashboard extends JPanel{

    protected BankUser user;
    //protected transactions

    protected JFrame host;

    //Dashboard Generals
    protected JLabel usernameLabel;
    protected JPanel generalLabelsPanel;

    //DashBoardOptions
    protected JPanel generalActionsPanel;
    protected JButton logoutButton;
    protected JButton stockMarketButton;


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

    public UserDashboard(JFrame host,BankUser user) {
        //HOST PANEL SETUP
        super();
        this.host = host;
        this.user = user;
        setMinimumSize(new Dimension(600, 1200));
        this.setLayout(new GridLayout(5, 1));
        ///////////////////////////////////////////////////////////////////////
        //INFO PANEL SETUP

        this.usernameLabel = new JLabel(user.getUsername());
        this.generalLabelsPanel = new JPanel();

        this.generalLabelsPanel.add(usernameLabel);

        add(generalLabelsPanel);

        ///////////////////////////////////////////////////////////////////////
        //GENERAL ACTIONS BUTTONS PANEL

        this.generalActionsPanel = new JPanel();
        this.logoutButton = new JButton("Logout");

        this.logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logout();
            }
        });

        this.stockMarketButton = new JButton("View Stock Market");
        this.stockMarketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stockMarketAction();
            }
        });
        this.generalActionsPanel.add(logoutButton);
        this.generalActionsPanel.add(stockMarketButton);

        add(generalActionsPanel);

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

            add(accountTableActionPanel);
        }
        /////////////////////////////////////////////////////////////////////////////////////////
        //Transcation table

        this.transcationTable = new GUITable(new Object[][]{{1, "test"}},new String[]{"Integer","String"});
        add(transcationTable);


        /////////////////////////////////////////////////////////////////////
    }

    private void logout(){
        //todo
    }

    private void stockMarketAction(){
        //todo
    }

    private void addAccountButtonAction() {
        MoneyAccountTypeSelectionMenu d = new MoneyAccountTypeSelectionMenu(this.host, this.user);
        Account act = d.account;
        if (act != null) {
            moneyAccountTable.addRowToTable(new Object[]{act.getAccountID(), act.getBalance()});
        }
    }

    public void removeAccountButtonActionPerformed(){
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

                 */
            }
        }
    }

    public Account getSelectedAccount(int selectedRow){
        Account act = null;
        if (selectedRow >= 0) {
            int actnum = (int) this.moneyAccountTable.table.getValueAt(selectedRow, 0);
            act = user.getAccount(actnum);
        }
        return act;
    }



    public void deposit(){
        int selectedRow = this.moneyAccountTable.table.getSelectedRow();
        Account account = getSelectedAccount(selectedRow);
        if(account != null){
             JDialog dWindow = new DepositMenu(host,true,account);
        }
        Object[] input = new Object[]{account.accountID, account.balance};
        this.moneyAccountTable.reloadRowData(selectedRow,input);

    }

    public void withdraw(){
        int selectedRow = this.moneyAccountTable.table.getSelectedRow();

        Account account = getSelectedAccount(selectedRow);
        if(account != null){
            JDialog wWindow = new WithdrawMenu(host,true,account);

        }
        Object[] input = new Object[]{account.accountID, account.balance};
        this.moneyAccountTable.reloadRowData(selectedRow,input);


    }
}


