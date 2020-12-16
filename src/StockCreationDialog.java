import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//Dialog that gives the user a way to create any type of stock
public abstract class StockCreationDialog extends JDialog {

    protected Stock stock;
    protected JPanel host = new JPanel();
    protected JPanel inputPanel = new JPanel();
    protected FieldInputTextCombo nameInput = new FieldInputTextCombo("Stock Name");
    protected FieldInputTextCombo valueInput = new FieldInputTextCombo("Stock Value");
    protected FieldInputTextCombo sharesInput = new FieldInputTextCombo("Number of Shares");


    protected JPanel buttonPanel = new JPanel();
    protected JButton submit = new JButton("Submit");
    protected JButton cancel = new JButton("Cancel");

    public StockCreationDialog(Window owner) {
        super(owner, Dialog.DEFAULT_MODALITY_TYPE);
        this.host.setLayout(new BoxLayout(host, BoxLayout.Y_AXIS));

        inputPanel.add(nameInput);
        inputPanel.add(valueInput);
        inputPanel.add(sharesInput);
        host.add(inputPanel);

        buttonPanel.add(submit);
        buttonPanel.add(cancel);

        host.add(Box.createVerticalGlue());
        host.add(buttonPanel);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitAction();
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelAction();
            }
        });

        this.add(host);

    }

    private void submitAction() {
        String returnString = null;
        String balanceString = "";
        Integer value = 0;
        Integer shares =0;

        if(nameInput.textField.getText().isEmpty()) {
            returnString += "Input a Stock name!\n";
        }

        if (valueInput.textField.getText().isEmpty()) {
            returnString += "Input a Stock worth!\n";
        } else {
            try {
                value = Integer.parseInt(valueInput.textField.getText());
                if(value < 1) {
                    returnString = "Stock value be greater than 0\n";
                }
            } catch (NumberFormatException err) {
                returnString += "Stock value must be a number!\n";
            }
        }
        if(sharesInput.textField.getText().isEmpty()){
            returnString += "Input Shares!\n";
        }
        else {
            try {
                shares = Integer.parseInt(sharesInput.textField.getText());
                if (shares < 0) {
                    returnString += "Need shares to be greater than 0!\n";
                }
            } catch (NumberFormatException err) {
                returnString += "Share value must be a number!\n";
            }
        }


        if (returnString != null) {
            JOptionPane.showMessageDialog(this, returnString, "Input Warning", JOptionPane.WARNING_MESSAGE);
        } else {
             this.stock = createStock(nameInput.textField.getText(),value,shares);

            this.dispose();
        }

    }
    protected abstract Stock createStock(String name, Integer value, Integer shares);

    private void cancelAction() {
        this.dispose();
    }

}
