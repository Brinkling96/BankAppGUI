import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class StockMarketDash extends BankDashboard{

    protected JLabel windowLabel;

    protected JButton backToMainDashButton = new JButton("Back to MainMenu");

    protected GUITable stockTable;
    protected JLabel stockMarket = new JLabel("StockMarket");
    protected JPanel stockList = new JPanel();


    protected JPanel actionTableButtons = new JPanel();

    public StockMarketDash(Window window, User user, Bank bank, String windowLabelString) {
        super(window, user, bank);
        this.windowLabel =  new JLabel(windowLabelString);

        this.generalLabelsPanel.add(windowLabel);

        //General Actions
        this.backToMainDashButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBacktoMainMenu();
            }
        });

        this.generalActionsPanel.add(backToMainDashButton);


        //First table
        this.stockTable = new GUITable("Stock Market: ",new Object[][]{{"t"}}, new String[]{"T"},new Class[]{String.class});
        this.stockList.add(stockMarket);
        this.stockList.add(stockTable);
        add(stockList);
    }


    protected abstract void goBacktoMainMenu();
}
