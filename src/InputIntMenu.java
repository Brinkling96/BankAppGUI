import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//General Dialog that allows the user to input an int
public abstract class InputIntMenu extends JDialog {
    protected JLabel intLabel;
    protected JTextField intField;
    protected JLabel currencyLabel;
    protected JComboBox currencyField;
    protected JButton submitButton;
    protected JButton cancelButton;


    public InputIntMenu(Window owner, String title, String label, String currency) {
        super(owner,Dialog.DEFAULT_MODALITY_TYPE);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle(title);

        initComponents(label, currency);
        setLocationRelativeTo(owner);
    }

    private void initComponents(String label, String currency) {
        this.intLabel = new JLabel(label);
        this.intField = new JTextField();
        this.currencyLabel = new JLabel(currency);
        this.currencyField = new JComboBox(
            new String[]{Bank.VALID_CURRENCIES[0],Bank.VALID_CURRENCIES[1],Bank.VALID_CURRENCIES[2]});
        this.submitButton = new JButton("Submit");
        this.cancelButton = new JButton("Cancel");

        getContentPane().setLayout(new GridLayout(3,2,5,5));

        getContentPane().add(intLabel);
        getContentPane().add(intField);
        getContentPane().add(currencyLabel);
        getContentPane().add(currencyField);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitButtonAction(e);
            }
        });

        getContentPane().add(submitButton);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelButtonAction(e);
            }
        });

        getContentPane().add(cancelButton);

    }

    protected void cancelButtonAction(ActionEvent e){
        this.dispose();
    }

    protected abstract void submitButtonAction(ActionEvent e);
}