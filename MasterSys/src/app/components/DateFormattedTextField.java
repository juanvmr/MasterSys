package app.components;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DateFormattedTextField extends JFormattedTextField implements FocusListener {

    /* attributes: */
    private static DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    /* constructor: */
    public DateFormattedTextField() {
        super();
        MaskFormatter mask = null;
        try {
            mask = new MaskFormatter("##/##/####");
            mask.setPlaceholderCharacter('_');
            this.setFormatterFactory(new DefaultFormatterFactory(mask));
            this.addFocusListener(this);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /* methods: */
    public Date getDate() {
        return toDate(this.getText());
    }

    public void setValue(Date date) {
        super.setValue(toString(date));
    }

    private Date toDate(String s) {
        Date tmp = null;
        if (s == null) return null;
        try {
            tmp = (Date) format.parseObject(s);
        } catch (ParseException e) {
            // ignore
        }
        return tmp;
    }

    private String toString(Date date) {
        try {
            return format.format(date);
        } catch (Exception e) {
            return "";
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (getFocusLostBehavior() == JFormattedTextField.PERSIST) {
            setFocusLostBehavior((JFormattedTextField.COMMIT_OR_REVERT));
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        try {
            Date tmp = (Date) format.parseObject(this.getText());
            this.setValue(format.format(tmp));
        } catch (ParseException ex) {
            this.setFocusLostBehavior((JFormattedTextField.PERSIST));
            this.setText("");
            this.setValue(null);
        }
    }
}
