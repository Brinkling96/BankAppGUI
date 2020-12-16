import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserStockMarketDash extends BankDashboard {
    protected Account account;
    protected Bank bank;


    //General Labels
    protected JLabel windowLabel;
    protected JLabel securityAccountIDLabel = new JLabel("Security Account ID: ");
    protected JLabel securityAccountIDActual;

    protected JLabel balanceLabel = new JLabel("Current Balance: ");
    protected JLabel balanceActual;
    protected JLabel realizedProfitLabel = new JLabel("Realized Profit: ");
    protected JLabel realizedProfitActual;
    protected JLabel unrealizedProfitLabel = new JLabel("Unrealized Profit");
    protected JLabel unrealizedProfitActual;




    protected JButton backToUserDash = new JButton("Back to MainMenu");

    //Current Positions

    protected GUITable currentPositionsTable;
    protected JLabel currentPositionsLabel = new JLabel("Current Position");
    protected JPanel currentPositionsPanel = new JPanel();

    //Current Positions Actions

    protected JButton sellShares = new JButton("Sell Stock Shares");
    protected JPanel cpActionsPanel = new JPanel();

    //Stock Market Table

    protected GUITable stockMarketTable;
    protected JLabel stockMarketLabel = new JLabel("Stock Market");
    protected JPanel stockMarketPanel = new JPanel();

    //Stock Market Actions

    protected JButton buyShares = new JButton("Buy Stock Shares");
    protected JPanel smActionsPanel = new JPanel();



    public UserStockMarketDash(Window window, Account account, Bank bank) {
        super(window, bank);

        ///General Labels
        this.windowLabel = new JLabel("NULL"); //account.getUser().getUsername()
        this.generalLabelsPanel.add(windowLabel);

        this.securityAccountIDActual = new JLabel(account.getAccountID());
        this.generalLabelsPanel.add(securityAccountIDLabel);
        this.generalLabelsPanel.add(securityAccountIDActual);

        this.balanceActual = new JLabel(Integer.toString(account.getBalance()));
        this.generalLabelsPanel.add(balanceLabel);
        this.generalLabelsPanel.add(balanceActual);

        this.realizedProfitActual = new JLabel("NULL");//todo
        this.generalLabelsPanel.add(realizedProfitLabel);
        this.generalLabelsPanel.add(realizedProfitActual);

        this.unrealizedProfitActual = new JLabel("NULL");//todo
        this.generalLabelsPanel.add(unrealizedProfitLabel);
        this.generalLabelsPanel.add(unrealizedProfitActual);


        //General Action
        this.backToUserDash.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBackToDash();
            }
        });
        this.generalActionsPanel.add(backToUserDash);

        //cp table

        this.currentPositionsTable = new GUITable("Current Positons: ",new Object[][]{{"t"}}, new String[]{"T"}, new Class[]{String.class});
        this.currentPositionsPanel.add(currentPositionsLabel);
        this.currentPositionsPanel.add(currentPositionsTable);
        this.add(currentPositionsPanel);

        //cp Action

        this.sellShares.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doSellSharesAction();
            }
        });

        this.cpActionsPanel.add(sellShares);

        //Stock Market
        this.stockMarketTable = new GUITable("Stock Market: ",new Object[][]{{"w"}}, new String[]{"W"},new Class[]{String.class});
        this.stockMarketPanel.add(stockMarketLabel);
        this.stockMarketPanel.add(stockMarketTable);
        this.add(stockMarketPanel);

        //stock Market Action

        this.buyShares.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buySharesAction();
            }
        });

        this.smActionsPanel.add(buyShares);
        this.add(smActionsPanel);

    }

    private void buySharesAction() {
        //todo
    }

    private void doSellSharesAction() {
        //todo
    }

    private void goBackToDash() {
        //todo
    }

}
