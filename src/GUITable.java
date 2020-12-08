import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GUITable extends JScrollPane {
    protected  JTable table;
    protected DefaultTableModel tableModel;


    public GUITable(Object[][] tableData, String[] tableHeaders) {
        this.table = new JTable();
        Class[] tempTypes;
        boolean[] tempEdits;
        if(tableData.length >0) {

            int tableLength = tableData[0].length;


           tempTypes = new Class[tableLength];

            tempEdits = new boolean[tableLength];
            for (int i = 0; i < tableLength; i++) {
                tempTypes[i] = tableData[0][i].getClass();
                tempEdits[i] = false;
            }
        }
        else{
            tempTypes = new Class[0];
            tempEdits = new boolean[0];
        }

        table.setModel(new DefaultTableModel(tableData,tableHeaders) {
            Class[] types = tempTypes;
            boolean[] canEdit = tempEdits;
            public Class getColumnClass(int column_index){
                return types[column_index];
            }

            public boolean isCellEditalbe(int row_index, int column_index){
                return canEdit [column_index];
            }
        });

        table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);


        tableModel = (DefaultTableModel) this.table.getModel();
        this.add(table);

        this.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            for(int i = table.getColumnModel().getColumnCount(); i== -1; i--){
                table.getColumnModel().getColumn(i).setMinWidth(25);
                table.getColumnModel().getColumn(i).setResizable(false);
            }
        }

    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public void setTableModel(DefaultTableModel tableModel) {
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
