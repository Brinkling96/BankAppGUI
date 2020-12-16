/******************************************************************************
 * Class: AddAccountDialog.java
 * Author: Sarah Shahinpour, Sean Brady, Shuaike Zhou, 
 *******************************************************************************/


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * a general Dialog that allows a user to create an account
 */
public abstract class AddAccountDialog extends JDialog{
    protected CustomerUser user;
    protected Account account;
    protected Bank bank;
    protected JPanel host = new JPanel();
    protected JPanel bpanel = new JPanel();
    protected JLabel balanceLabel;
    protected JTextField balanceField;
    protected JPanel buttonPanel = new JPanel();
    protected JButton okButton;
    protected JButton cancelButton;

    public AddAccountDialog(Window owner, CustomerUser user, String balanceLabelString, Bank bank) {
        super(owner, Dialog.DEFAULT_MODALITY_TYPE);
        this.user = user;
        this.bank = bank;
        setLocationRelativeTo(owner);
        initComponents(balanceLabelString);
    }

    @SuppressWarnings("unchecked")

    /**
     * Creates the necessary field/panels for user input
     * @param balanceLabelString
     */
    private void initComponents(String balanceLabelString){
        balanceLabel = new JLabel(balanceLabelString);
        balanceField = new JTextField();
        okButton = new JButton("OK");
        cancelButton = new JButton("CANCEL");

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Add Account Menu");
        this.host.setLayout(new BoxLayout(this.host,BoxLayout.Y_AXIS));

        bpanel.setLayout(new GridLayout(1,2));

        bpanel.add(balanceLabel);
        bpanel.add(balanceField);

        this.host.add(bpanel);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                okButtonAction(e);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelButtonAction(e);
            }
        });


        buttonPanel.setLayout(new GridLayout(1,2));
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        this.host.add(buttonPanel);

        this.add(host);

    }

    private void cancelButtonAction(ActionEvent e){
        this.dispose();
    }
    /**
     * OK button needs to be implemented by subclass
     * @param e
     */
    protected abstract void okButtonAction(ActionEvent e);
    public Account getAccount(){
        return account;
    }
}
