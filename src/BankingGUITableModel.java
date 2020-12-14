import javax.swing.table.DefaultTableModel;

public class BankingGUITableModel extends DefaultTableModel {
    protected Class[] types;
    public BankingGUITableModel(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
        if(data.length >0) {

            int tableLength = columnNames.length;

            types = new Class[tableLength];


            for (int i = 0; i < tableLength; i++) {
                types[i] = data[0][i].getClass();
            }
        }

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
