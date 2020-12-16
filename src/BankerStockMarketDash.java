import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankerStockMarketDash extends StockMarketDash {

    protected JButton backToMainDashButton = new JButton("Back to MainMenu");

    protected GUITable stockTable;
    protected JLabel stockMarket = new JLabel("StockMarket");
    protected JPanel stockList = new JPanel();


    protected JPanel actionTableButtons = new JPanel();

    protected JButton addStock = new JButton("Add a new Stock entry");
    protected JButton removeStock = new JButton("Remove Stock from Market");
    protected JButton editStock = new JButton("Edit Stock");




    public BankerStockMarketDash(Window window, Banker banker, Bank bank) {
        super(window, banker, bank,"Banker Stock Market");

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
        //todo
    }

    private void removeStockAction() {
        //todo
    }

    private void editStockAction() {
        //todo
    }

    @Override
    protected void goBacktoMainMenu()  {
        this.window.setVisible(false);
        this.window.remove(this);
        this.window.add(new BankerDashboard(window,(Banker) user,bank));
        this.window.setVisible(true);
    }
}
