import javax.swing.table.DefaultTableModel;

//Used to display items like accounts and transactions
public class BankingGUITableModel extends DefaultTableModel {
    protected Class[] types;
    public BankingGUITableModel(Object[][] data, Object[] columnNames, Class[] classes) {
        super(data, columnNames);
        this.types = classes;

    }

    @Override
    public Class getColumnClass(int columnIndex) {
        return types[columnIndex];
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
