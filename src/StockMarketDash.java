import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//Contains necessary features that should be displayed when accessing the stock market
public abstract class StockMarketDash extends BankDashboard{

    protected JLabel windowLabel;
    protected JButton backToMainDashButton = new JButton("Back to MainMenu");
    protected GUITable stockTable;
    protected JPanel actionTableButtons = new JPanel();

    public StockMarketDash(Window window, User user, Bank bank, String windowLabelString) {
        super(window, user, bank);
        this.windowLabel =  new JLabel(windowLabelString);

        this.generalLabelsPanel.add(windowLabel);
        this.generalLabelsPanel.setLayout(new GridLayout(2,1));

        //General Actions
        this.backToMainDashButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBacktoMainMenu();
            }
        });

        this.generalActionsPanel.add(backToMainDashButton);

        //First table
        this.stockTable = new GUITable("Stock Market: ",createStockTData(),
                new String[]{"Stock Name", "Stock Price","Avaliable Shares"},new Class[]{String.class,Integer.class,Integer.class});
        add(Box.createVerticalGlue());
        add(stockTable);
    }

    private Object[][] createStockTData(){
        ArrayList<Stock> s =  bank.getStocks();
        ArrayList<NYSE_Stock> nyse = new ArrayList<>();

        for(Stock x : s){
            if(x instanceof NYSE_Stock){
                nyse.add((NYSE_Stock) x);
            }

        }
        Object[][] returnlist = new Object[nyse.size()][3];
        for(int i=0; i<returnlist.length; i++){
            NYSE_Stock g = nyse.get(i);
            int j=0;
            returnlist[i][j++] = g.name;
            returnlist[i][j++] = g.value;
            returnlist[i][j++] = g.shares;
        }

        return returnlist;
    }

    protected abstract void goBacktoMainMenu();
}
