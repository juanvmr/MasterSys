package app.components;

import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.*;

public class MonthChooser extends JPanel implements ActionListener, MouseListener {

    /* attributes: */
    private static String[] month = new String[] { "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho",
            "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro" };
    private static final int TIME_FROZEN = 3;
    private int timerFlag = 0;
    private static Dimension minSize = new Dimension(185, 24);

    /* components: */
    private GregorianCalendar gregorianCalendar;
    private Timer timer;
    private JLabel dateLabel, nextMonth, nextYear, previousMonth, previousYear;
    private JPanel navegatePanel;

    public MonthChooser() {
        // super();
        this.initComponents();
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        //this.setSize(minSize);
        this.setMinimumSize(minSize);
        this.setPreferredSize(minSize);
        this.setDaysAndFill();
    }

    private void initComponents() {

        gregorianCalendar = new GregorianCalendar();

        navegatePanel = new JPanel();
        previousYear = new JLabel();
        previousMonth = new JLabel();
        dateLabel = new JLabel();
        nextMonth = new JLabel();
        nextYear = new JLabel();

        setLayout(new BorderLayout());

        navegatePanel.setLayout(null);

        navegatePanel.setPreferredSize(new Dimension(22, 22));
        previousYear.setFont(new java.awt.Font("Arial", 0, 9));
        previousYear.setText("<<");
        previousYear.setHorizontalAlignment(SwingConstants.CENTER);
        previousYear.setBorder(BorderFactory.createRaisedBevelBorder());
        previousYear.addMouseListener(this);

        navegatePanel.add(previousYear);
        previousYear.setBounds(1, 1, 20, 20);

        previousMonth.setFont(new java.awt.Font("Arial", 0, 9));
        previousMonth.setText("<");
        previousMonth.setHorizontalAlignment(SwingConstants.CENTER);
        previousMonth.setBorder(BorderFactory.createRaisedBevelBorder());
        previousMonth.addMouseListener(this);

        navegatePanel.add(previousMonth);
        previousMonth.setBounds(21, 1, 20, 20);

        dateLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dateLabel.setText("Escolha a Data");
        navegatePanel.add(dateLabel);
        dateLabel.setBounds(42, 1, 100, 20);

        nextMonth.setFont(new java.awt.Font("Arial", 0, 9));
        nextMonth.setText(">");
        nextMonth.setHorizontalAlignment(SwingConstants.CENTER);
        nextMonth.setBorder(BorderFactory.createRaisedBevelBorder());
        nextMonth.addMouseListener(this);

        navegatePanel.add(nextMonth);
        nextMonth.setBounds(142, 1, 20, 20);

        nextYear.setFont(new java.awt.Font("Arial", 0, 9));
        nextYear.setText(">>");
        nextYear.setHorizontalAlignment(SwingConstants.CENTER);
        nextYear.setBorder(BorderFactory.createRaisedBevelBorder());
        nextYear.addMouseListener(this);

        navegatePanel.add(nextYear);
        nextYear.setBounds(162, 1, 20, 20);

        add(navegatePanel, BorderLayout.NORTH);
    }

    private void initTimer (final boolean b1, final boolean b2, final boolean b3, final boolean b4) {
        timer = new Timer(100,
            new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    if (timerFlag > TIME_FROZEN) {
                        prepare(b1, b2, b3, b4);
                    } else {
                        timerFlag++;
                    }
                }
            }
        );

        timer.start();
    }

    private void terminateTimer() {
        timer.stop();
        timer = null;
    }

    private void setDaysAndFill() {
        gregorianCalendar.set(Calendar.DAY_OF_MONTH, 1);
        String monthText = month[gregorianCalendar.get(Calendar.MONTH)];
        dateLabel.setText(String.valueOf(monthText + ", " + gregorianCalendar.get(Calendar.YEAR)));
    }

    public void prepare(boolean rollYear, boolean rollMonth, boolean up, boolean instantiateGc) {
        if (instantiateGc) {
            gregorianCalendar = new GregorianCalendar();
        }

        if (rollYear) {
            gregorianCalendar.roll(Calendar.YEAR, up);
        }

        if (rollMonth) {
            if ((up && gregorianCalendar.get(Calendar.MONTH) == Calendar.DECEMBER) || (!up && gregorianCalendar.get(Calendar.MONTH) == Calendar.JANUARY)) {
                gregorianCalendar.roll(Calendar.YEAR, up);
            }
            gregorianCalendar.roll(Calendar.MONTH, up);
        }

        setDaysAndFill();
    }

    public Date getDate() {
        return gregorianCalendar.getTime();
    }

    public void setDate(Date date) {
        gregorianCalendar.setTime(date);
    }

    public void stopTimer() {
        if (timer != null) {
            timer.stop();
        }
    }

    /**
     * Método para ser sobrescrito pela classe que usará
     * este objeto, afim de executar alguma ação quando
     * a data for alterada.
     */
    public void ExecuteSomething() {}

    @Override
    public void actionPerformed(ActionEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent evt) {
        if (evt.getSource() == nextYear) {
            nextYear.setBorder(BorderFactory.createLoweredBevelBorder());
            if (SwingUtilities.isLeftMouseButton(evt)) {
                initTimer(true, false, true, false);
            }
        } else if (evt.getSource() == nextMonth) {
            nextMonth.setBorder(BorderFactory.createLoweredBevelBorder());
            if (SwingUtilities.isLeftMouseButton(evt)) {
                initTimer(false, true, true, false);
            }
        } else if (evt.getSource() == previousMonth) {
            previousMonth.setBorder(BorderFactory.createLoweredBevelBorder());
            if (SwingUtilities.isLeftMouseButton(evt)) {
                initTimer(false, true, false, false);
            }
        } else if (evt.getSource() == previousYear) {
            previousYear.setBorder(BorderFactory.createLoweredBevelBorder());
            if (SwingUtilities.isLeftMouseButton(evt)) {
                initTimer(true, false, false, false);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent evt) {
        if (evt.getSource() == nextYear) {
            nextYear.setBorder(BorderFactory.createRaisedBevelBorder());
            if (SwingUtilities.isLeftMouseButton(evt)) {
                terminateTimer();
                if (timerFlag <= TIME_FROZEN) {
                    prepare(true, false, true, false);
                }
                timerFlag = 0;
            }
            ExecuteSomething();
        } else if (evt.getSource() == nextMonth) {
            nextMonth.setBorder(BorderFactory.createRaisedBevelBorder());
            if (SwingUtilities.isLeftMouseButton(evt)) {
                terminateTimer();
                if (timerFlag <= TIME_FROZEN) {
                    prepare(false, true, true, false);
                }
                timerFlag = 0;
            }
            ExecuteSomething();
        } else if (evt.getSource() == previousMonth) {
            previousMonth.setBorder(BorderFactory.createRaisedBevelBorder());
            if (SwingUtilities.isLeftMouseButton(evt)) {
                terminateTimer();
                if (timerFlag <= TIME_FROZEN) {
                    prepare(false, true, false, false);
                }
                timerFlag = 0;
            }
            ExecuteSomething();
        } else if (evt.getSource() == previousYear) {
            previousYear.setBorder(BorderFactory.createRaisedBevelBorder());
            if (SwingUtilities.isLeftMouseButton(evt)) {
                terminateTimer();
                if (timerFlag <= TIME_FROZEN) {
                    prepare(true, false, false, false);
                }
                timerFlag = 0;
            }
            ExecuteSomething();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
