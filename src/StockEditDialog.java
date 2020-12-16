import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StockEditDialog extends JDialog{
    protected Stock stock;


    protected JPanel host = new JPanel();


    protected JPanel inputPanel = new JPanel();
    protected JLabel name;
    protected FieldInputTextCombo valueInput = new FieldInputTextCombo("Stock Value");
    protected FieldInputTextCombo sharesInput = new FieldInputTextCombo("Number of Shares");


    protected JPanel buttonPanel = new JPanel();
    protected JButton submit = new JButton("Submit");
    protected JButton cancel = new JButton("Cancel");


    public StockEditDialog(Window owner,Stock stock) {
        super(owner, Dialog.DEFAULT_MODALITY_TYPE);

        this.host.setLayout(new BoxLayout(host, BoxLayout.Y_AXIS));
        this.stock = stock;
        name = new JLabel(stock.getName());

        valueInput.textField.setText(Integer.toString(stock.value));

        sharesInput.textField.setText(Integer.toString(stock.shares));

        inputPanel.add(name);
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
        this.pack();
        this.setVisible(true);


    }

    private void submitAction() {
        String returnString = null;
        String balanceString = "";
        Integer value = 0;
        Integer shares =0;

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
            this.stock.setValue(value);
            this.stock.setShares(shares);
            this.dispose();
        }

    }
    private void cancelAction() {
        this.dispose();
    }
}
