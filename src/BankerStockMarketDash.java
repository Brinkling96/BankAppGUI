/******************************************************************************
 * Class: BankerStockMarketDash.java
 * Author: Sarah Shahinpour, Sean Brady, Shuaike Zhou, 
 *******************************************************************************/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Displays stock market features relevant to the banker
public class BankerStockMarketDash extends StockMarketDash {

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
        add(Box.createVerticalGlue());
        add(actionTableButtons);

    }

    private void addStockAction() {
        StockCreationDialog d = new NYSEStockCreationDialog(window);
        Stock stock = d.stock;
        if (stock != null) {
            stockTable.addRowToTable(new Object[]{stock.getName(), stock.getValue(),stock.getShares()});
            bank.addStock(d.stock);
            DataKeeper.updateStocks(d.stock, "add");
        }
    }

    private void removeStockAction() {
        int selectedRow = this.stockTable.table.getSelectedRow();
        if (selectedRow >= 0) {
            int result = JOptionPane.showConfirmDialog(this, "Are you sure?", "Confirm Stock Deletion",
                    JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                //todo
                Stock stock = getSelectedStock(selectedRow);
                if (stock != null) {
                    //todo payback everyone
                    bank.removeStock(stock);
                    DataKeeper.updateStocks(stock, "remove");
                    this.stockTable.tableModel.removeRow(selectedRow);
                }
            }
        }
    }

    private void editStockAction() {
        int selectedRow = this.stockTable.table.getSelectedRow();
        Stock stock = getSelectedStock(selectedRow);
        if (stock != null) {
            StockEditDialog d = new StockEditDialog(window,stock);
            Stock newstock = d.stock;
            if (newstock != null) {
                DataKeeper.updateStocks(d.stock, "update");
                stockTable.reloadRowData(selectedRow,new Object[]{stock.getName(),stock.getValue(),stock.getShares()});
            }
        }
        else {
            JOptionPane.showMessageDialog(this, "Stock Not Found",
                    "EditStockError", JOptionPane.ERROR_MESSAGE);
        }
    }

    private  Stock getSelectedStock(int selectedRow){
        if (selectedRow >= 0) {
            String stockName = (String) this.stockTable.table.getValueAt(selectedRow, 0);
            return bank.findStock(stockName);
        }
        return null;
    }

    @Override
    protected void goBacktoMainMenu()  {
        this.window.setVisible(false);
        this.window.remove(this);
        this.window.add(new BankerDashboard(window,(Banker) user,bank));
        this.window.pack();
        this.window.setVisible(true);
    }
}
