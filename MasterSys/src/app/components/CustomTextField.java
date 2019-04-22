package app.components;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class CustomTextField extends JTextField {

    public CustomTextField() {
        super();
        this.setColumns(8);
        this.setMinimumSize(new Dimension(50, 25));
        this.setPreferredSize(new Dimension(100, 25));
        this.setBorder(new LineBorder(Color.GRAY));
        this.setBackground(Color.WHITE);
        this.setHorizontalAlignment(SwingConstants.CENTER);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (this.isEnabled()) {
            this.setBorder(new LineBorder(Color.GRAY));
            this.setBackground(Color.WHITE);
        } else {
            this.setBorder(new LineBorder(Color.GRAY));
            this.setBackground(Color.LIGHT_GRAY);
        }
    }

    @Override
    public void setEditable(boolean b) {
        super.setEditable(b);
        if (this.isEditable()) {
            this.setBorder(new LineBorder(Color.GRAY));
            this.setBackground(Color.WHITE);
        } else {
            this.setBorder(new LineBorder(Color.GRAY));
            this.setBackground(Color.LIGHT_GRAY);
        }
    }
}
