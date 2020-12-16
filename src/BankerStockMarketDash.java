import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankerStockMarketDash extends BankDashboard {
    protected JLabel windowLabel = new JLabel("Banker Stock Market");

    protected JButton backToMainDashButton = new JButton("Back to MainMenu");

    protected GUITable stockTable;
    protected JLabel stockMarket = new JLabel("StockMarket");
    protected JPanel stockList = new JPanel();


    protected JPanel actionTableButtons = new JPanel();

    protected JButton addStock = new JButton("Add a new Stock entry");
    protected JButton removeStock = new JButton("Remove Stock from Market");
    protected JButton editStock = new JButton("Edit Stock");




    public BankerStockMarketDash(Window window, Bank bank) {
        super(window, bank);
        //General Labels
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


        //Table actions
        this.addStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStockAction();
            }
        });

        this.removeStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeStockAction();
            }
        });
        this.editStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editStockAction();
            }
        });
        this.actionTableButtons.add(addStock);
        this.actionTableButtons.add(removeStock);
        this.actionTableButtons.add(editStock);
        add(actionTableButtons);









    }

    private void addStockAction() {
    }

    private void removeStockAction() {
    }

    private void editStockAction() {
    }

    protected void goBacktoMainMenu() {
        //todo
    }
}
