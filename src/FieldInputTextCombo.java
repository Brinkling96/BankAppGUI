import javax.swing.*;
import java.awt.*;

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
