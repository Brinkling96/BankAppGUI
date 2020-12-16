import java.awt.*;

public class NYSEStockCreationDialog extends StockCreationDialog {

    public NYSEStockCreationDialog(Window owner) {
        super(owner);
        this.pack();
        this.setVisible(true);
    }

    @Override
    protected Stock createStock(String name, Integer value, Integer shares) {
        return new NYSE_Stock(name,value,shares);
    }
}
