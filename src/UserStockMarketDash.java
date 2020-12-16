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


    //General Actions
    protected JButton backToUserDash = new JButton("Back to MainMenu");

    //Current Positions

    protected GUITable currentPositionsTable;

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

    public UserStockMarketDash(Window window,User user, Account account, Bank bank) {
        super(window, user, bank, "Stock Market: ");//account.getUser().getUsername()

        ///General Labels
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
    }

    private void doSellSharesAction() {
        //todo
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
