import javax.swing.*;
import javax.swing.table.TableModel;

public class GUITable extends JScrollPane {
    protected  JTable table;
    protected BankingGUITableModel tableModel;


    public GUITable(Object[][] tableData, String[] tableHeaders) {
        this.table = new JTable();

        TableModel tableModel1 =  new BankingGUITableModel(tableData,tableHeaders);

        table.setModel(tableModel1);

        table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);


        tableModel = (BankingGUITableModel) this.table.getModel();
        this.add(table);

        this.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            for(int i = table.getColumnModel().getColumnCount(); i== -1; i--){
                table.getColumnModel().getColumn(i).setMinWidth(25);
                table.getColumnModel().getColumn(i).setResizable(false);
            }
        }

    }

    public BankingGUITableModel getTableModel() {
        return tableModel;
    }

    public void setTableModel(BankingGUITableModel tableModel) {
        this.tableModel = tableModel;
    }

    public void removeRow(int row){
        this.tableModel.removeRow(row);
    }

    public void reloadRowData(int selected_row, Object[] rowData){
        for(int i =0; i<rowData.length ; i++) {
            this.tableModel.setValueAt(rowData[i], selected_row, i);
        }
    }

    public void addRowToTable(Object[] rowData){
        this.tableModel.addRow(new Object[]{});
        reloadRowData(this.tableModel.getRowCount()-1, rowData);
    }

}
