import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AddSecurityAccount extends AddAccountDialog{


    public AddSecurityAccount(Window owner, CustomerUser user) {
        super(owner, user,"Transfer: ");
        getContentPane().setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
        this.getContentPane().add(new JLabel("NUYLL"),0);

    }

    @Override
    protected void okButtonAction(ActionEvent e) {
        //todo
    }
}
