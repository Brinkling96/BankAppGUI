import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
public class DailyReportMenu extends InputIntMenu{
    private Bank bank;
    public DailyReportMenu(Window owner, Bank bank) {
        super(owner, "Daily Report Menu", "Date (MMddyyyy)");
        this.bank = bank;
        pack();
        setVisible(true);

    }

    @Override
    protected void submitButtonAction(ActionEvent e) {
        String warning = null;

        if(intField.getText().isEmpty()){
            warning = "Date must be entered";
        }
        else{
            try{
                int date = Integer.parseInt(intField.getText());
                if(Integer.toString(date).length() == 8) {
                    int result = JOptionPane.showConfirmDialog(this, "Date: " + Integer.toString(date) + " ?");
                    if (result == JOptionPane.OK_OPTION) {
                        bank.setDailyReport(DataKeeper.getDailyTransactions(Integer.toString(date)));
                        this.dispose();
                    }
                }
                else{
                    warning = "Date must be in format MMddyyyy";
                }

            }catch (NumberFormatException err){
                warning = "Enter a valid date in format: MMddyyyy";
            }
        }
        if(warning != null){
            JOptionPane.showMessageDialog(this,warning,"Input Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
}
