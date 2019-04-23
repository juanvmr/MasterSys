package app.frames;

import app.components.DateField;
import app.components.MonthChooser;
import database.dao.FaturaDAO;
import database.dao.MatriculaDAO;
import database.dao.MatriculaModalidadeDAO;
import database.models.Fatura;
import database.models.Matricula;
import database.models.MatriculaModalidade;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class ProcessosGerarFaturasFrame extends BasicInternalFrame implements ActionListener {

    /* config: */
    private static boolean isResizable = false;
    private static boolean isClosable = true;
    private static boolean isMaximizable = false;
    private static boolean isIconifiable = false;

    /* attributes: */
    private MatriculaDAO matriculaDAO;
    private MatriculaModalidadeDAO matriculaModalidadeDAO;
    private FaturaDAO faturaDAO;

    /* components: */
    private MonthChooser monthChooser;
    private JButton submitButton;

    /* constructors: */
    public ProcessosGerarFaturasFrame(Connection connection) {
        super("Gerar Faturas");

        this.matriculaDAO = new MatriculaDAO(connection);
        this.matriculaModalidadeDAO = new MatriculaModalidadeDAO(connection);
        this.faturaDAO = new FaturaDAO(connection);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.initComponents();
        this.setVisible(true);
    }

    @Override
    public void initComponents() {
        JLabel dataLabel = new JLabel("Data da Fatura:", JLabel.RIGHT);

        monthChooser = new MonthChooser();

        submitButton = new JButton("Gerar Faturas");
        submitButton.addActionListener(this);

        JPanel content = new JPanel(new GridBagLayout());
        content.setBorder(new EmptyBorder(inset, inset, inset, inset));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(inset, inset, inset, inset);
        constraints.fill = GridBagConstraints.HORIZONTAL;

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.weightx = 0;
        content.add(dataLabel, constraints);
        constraints.gridx++;
        constraints.gridwidth = 2;
        constraints.weightx = 1;
        content.add(monthChooser, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 3;
        constraints.weightx = 0;
        content.add(submitButton, constraints);

        this.setContentPane(content);
        this.pack();
    }

    @Override
    public void initComponents(Container container) {}

    @Override
    public void setupData() {
        monthChooser.setEnabled(true);
    }

    @Override
    public Object getData() {
        return monthChooser.getDate();
    }

    @Override
    public void resetData() {
        monthChooser.setDate(new Date());
    }

    @Override
    public void updateData(Object obj) {
        Date date = (Date) obj;
        monthChooser.setDate(date);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {

            try {

                Date currentDate = monthChooser.getDate();
                Date lastDate = new Date(currentDate.getYear(), currentDate.getMonth(), 30);
                List<MatriculaModalidade> list;

                List<Object> matriculaList = matriculaDAO.select();
                for (Object obj : matriculaList) {
                    Matricula m = (Matricula) obj;

                    int codigo_matricula = m.getCodigoMatricula();
                    Date data_vecimento = new Date(currentDate.getYear(), currentDate.getMonth(), m.getDiaVencimento());

                    System.out.println("Current Date: " + currentDate + ", Expire Date: " + data_vecimento);

                    Fatura f = new Fatura(
                        codigo_matricula,
                        data_vecimento,
                        faturaDAO.getFaturaValue(codigo_matricula, data_vecimento),
                        null,
                        null
                    );

                    if ((f.getValor() > 0) && (m.getDataEncerramento() == null)) {
                        System.out.println("Fatura: " + f);
                        faturaDAO.insert(f);
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
