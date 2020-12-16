import javax.swing.*;
import java.awt.*;

//Panel that can be used whenever the user should enter text next to a field
public class FieldInputTextCombo extends JPanel {

    protected JLabel label;

    protected JTextField textField;

    public FieldInputTextCombo(String label) {
        this.setLayout(new GridLayout(1,2));
        this.label = new JLabel(label);
        this.textField = new JTextField();
        this.add(this.label);
        this.add(textField);
    }

    public boolean isTextInputted(){
        return textField.getText().isEmpty();
    }

    public String getTextInputted(){
        return textField.getText();
    }


}
