import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Displays stock market features relevant to the user
public class UserStockMarketDash extends StockMarketDash {
    protected Account account;

    //General Labels
    protected JLabel securityAccountIDLabel = new JLabel("Security Account ID: ");
    protected JLabel securityAccountIDActual;

    protected JLabel balanceLabel = new JLabel("Current Balance: ");
    protected JLabel balanceActual;
    protected JLabel realizedProfitLabel = new JLabel("Realized Profit: ");
    protected JLabel realizedProfitActual;
    protected JLabel unrealizedProfitLabel = new JLabel("Unrealized Profit");
    protected JLabel unrealizedProfitActual;


    //Stock Market Actions

    protected JButton buyShares = new JButton("Buy Stock Shares");
    protected JPanel smActionsPanel = new JPanel();

    //Current Positions

    protected GUITable currentPositionsTable;

    //Current Positions Actions

    protected JButton sellShares = new JButton("Sell Stock Shares");
    protected JPanel cpActionsPanel = new JPanel();




    public UserStockMarketDash(Window window,User user, Account account, Bank bank) {
        super(window, user, bank, "Stock Market: ");//account.getUser().getUsername()

        ///General Labels

        JPanel accountLabels = new JPanel();

        this.generalLabelsPanel.add(accountLabels);


        this.securityAccountIDActual = new JLabel(account.getAccountID());
        accountLabels.add(securityAccountIDLabel);
        accountLabels.add(securityAccountIDActual);

        this.balanceActual = new JLabel(Integer.toString(account.getBalance()));
        accountLabels.add(balanceLabel);
        accountLabels.add(balanceActual);

        this.realizedProfitActual = new JLabel("NULL");//todo
        accountLabels.add(realizedProfitLabel);
        accountLabels.add(realizedProfitActual);

        this.unrealizedProfitActual = new JLabel("NULL");//todo
        accountLabels.add(unrealizedProfitLabel);
        accountLabels.add(unrealizedProfitActual);

        //stock Market Action

        this.buyShares.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buySharesAction();
            }
        });

        this.smActionsPanel.add(buyShares);
        this.add(Box.createVerticalGlue());
        this.add(smActionsPanel);


        //cp table

        this.currentPositionsTable = new GUITable("Current Positons: ",new Object[][]{{}},
                new String[]{"Stock Name","Stock Price","Bought at Price", "Shares"},
                new Class[]{String.class,Integer.class,Integer.class,Integer.class});
        this.add(Box.createVerticalGlue());
        this.add(currentPositionsTable);

        //cp Action

        this.sellShares.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doSellSharesAction();
            }
        });

        this.cpActionsPanel.add(sellShares);
        this.add(Box.createVerticalGlue());
        this.add(cpActionsPanel);




    }

    private void buySharesAction() {
        //todo
        JOptionPane.showMessageDialog(this,"Unimplemented");
    }

    private void doSellSharesAction() {
        //todo
        JOptionPane.showMessageDialog(this,"Uimplemented");
    }

    @Override
    protected void goBacktoMainMenu() {
        this.window.setVisible(false);
        this.window.remove(this);
        this.window.add(new UserDashboard(window,(CustomerUser) user,bank));
        this.window.pack();
        this.window.setVisible(true);
    }
}
