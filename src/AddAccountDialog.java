import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class AddAccountDialog extends JDialog{
    protected CustomerUser user;
    protected Account account;
    protected Bank bank;


    protected JPanel panel = new JPanel();
    protected JLabel balanceLabel;
    protected JTextField balanceField;


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

    private void initComponents(String balanceLabelString){
        balanceLabel = new JLabel(balanceLabelString);
        balanceField = new JTextField();
        okButton = new JButton("OK");
        cancelButton = new JButton("CANCEL");

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Add Account Menu");

        panel.setLayout(new GridLayout(3,2,5,5));

        panel.add(balanceLabel);
        panel.add(balanceField);


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


        panel.add(okButton);
        panel.add(cancelButton);
        this.add(panel);


        pack();
        setVisible(true);
    }

    private void cancelButtonAction(ActionEvent e){
        this.dispose();
    }

    protected abstract void okButtonAction(ActionEvent e);
    public Account getAccount(){
        return account;
    }


}
