package app.components;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.*;
import java.util.Date;
import java.util.SimpleTimeZone;

public class DateField extends JFormattedTextField implements FocusListener {

    /* attributes: */
    private static DateFormat textFormat = new SimpleDateFormat("dd/MM/yyyy");
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final char TOKEN = ' ';

    /* constructor: */

    public DateField() {
        super();
        MaskFormatter dateFormatter = null;
        try {
            dateFormatter = new MaskFormatter("##/##/####");
            //dateFormatter.setValidCharacters("0123456789");
            dateFormatter.setPlaceholderCharacter(TOKEN);
            //dateFormatter.setValueClass(Date.class);
            this.setFormatterFactory(new DefaultFormatterFactory(dateFormatter));
            this.addFocusListener(this);
        } catch (ParseException e) {
            System.err.println("Formatter: " + e.getMessage());
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
        if (s == null) return null;
        Date tmp = null;
        try {
            tmp = (Date) textFormat.parseObject(s);
        } catch (ParseException e) {
            // ignore
        }
        return tmp;
    }

    private String toString(Date date) {
        try {
            return textFormat.format(date);
        } catch (Exception e) {
            return "";
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (this.getFocusLostBehavior() == JFormattedTextField.PERSIST) {
            this.setFocusLostBehavior((JFormattedTextField.COMMIT_OR_REVERT));
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        try {
            Date tmp = (Date) textFormat.parseObject(this.getText());
            this.setValue(textFormat.format(tmp));
        } catch (ParseException ex) {
            this.setFocusLostBehavior((JFormattedTextField.PERSIST));
            this.setText("");
            this.setValue(null);
        }
    }
}