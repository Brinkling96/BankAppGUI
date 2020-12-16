import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;

public class GUITable extends JPanel {


    protected JPanel labelHost = new JPanel();
    protected JLabel label;

    protected JScrollPane scrollPane = new JScrollPane();
    protected  JTable table;
    protected BankingGUITableModel tableModel;


    public GUITable(String labelString,Object[][] tableData, String[] tableHeaders, Class[] classes) {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        label= new JLabel(labelString,SwingConstants.LEFT);


        this.add(label);

        this.table = new JTable();

        TableModel tableModel1 =  new BankingGUITableModel(tableData,tableHeaders,classes);

        table.setModel(tableModel1);

        table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);


        tableModel = (BankingGUITableModel) this.table.getModel();
        this.scrollPane.add(table);

        this.scrollPane.setViewportView(table);


        if (table.getColumnModel().getColumnCount() > 0) {
            for(int i = table.getColumnModel().getColumnCount(); i== -1; i--){
                table.getColumnModel().getColumn(i).setMinWidth(25);
                table.getColumnModel().getColumn(i).setResizable(false);
            }
        }
        this.add(Box.createVerticalGlue());
        this.add(scrollPane);

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

    public Dimension getTableSize(){
        return new Dimension(tableModel.getColumnCount(),tableModel.getRowCount());
    }

}
