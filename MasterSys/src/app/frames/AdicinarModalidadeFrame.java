package app.frames;

import app.components.DateFormattedTextField;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class AdicinarModalidadeFrame extends JFrame implements ActionListener {

    /* attributes:"*/
    private Connection connection;

    /* components: */
    private JLabel modalidadeLabel, graduacaoLabel, planoLabel, dataInicioLabel, dataFimLabel;
    private JComboBox modalidadeComboBox;
    private JTextField graduacaoField, planoField;
    private DateFormattedTextField dataInicioField, dataFimField;
    private JButton addButton;

    /* constructor: */

    public AdicinarModalidadeFrame(Connection connection) {
        super("Nova Modalidade");

        this.connection = connection;

        this.setLayout(null);
        this.setResizable(false);
        this.initComponents();
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    /* methods: */
    private void initComponents() {

        modalidadeLabel = new JLabel("Modalidade:", JLabel.CENTER);
        graduacaoLabel = new JLabel("Graduação:", JLabel.CENTER);
        planoLabel = new JLabel("Plano:", JLabel.CENTER);
        dataInicioLabel = new JLabel("Data Início:", JLabel.CENTER);
        dataFimLabel = new JLabel("Data Fim:", JLabel.CENTER);

        modalidadeComboBox = new JComboBox();
        graduacaoField = new JTextField(16);
        planoField = new JTextField(16);
        dataInicioField = new DateFormattedTextField();
        dataFimField = new DateFormattedTextField();

        addButton = new JButton("OK");
        addButton.addActionListener(this);

        JPanel content = new JPanel(new GridBagLayout());
        content.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.setContentPane(content);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);

        constraints.gridx = 0;
        constraints.gridy = 0;
        content.add(modalidadeLabel, constraints);
        constraints.gridy++;
        content.add(graduacaoLabel, constraints);
        constraints.gridy++;
        content.add(planoLabel, constraints);
        constraints.gridy++;
        content.add(dataInicioLabel, constraints);
        constraints.gridy++;
        content.add(dataFimLabel, constraints);

        constraints.gridx++;
        constraints.gridy = 0;
        content.add(modalidadeComboBox, constraints);
        constraints.gridy++;
        content.add(graduacaoField, constraints);
        constraints.gridy++;
        content.add(planoField, constraints);
        constraints.gridy++;
        content.add(dataInicioField, constraints);
        constraints.gridy++;
        content.add(dataFimField, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        constraints.weightx = 1;
        constraints.gridwidth = 2;
        content.add(addButton, constraints);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == addButton) {
            // read input fields
            //String modalidade = modalidadeComboBox.getName();     // NÃO SEI LER COMBO BOX
            String graduacao = graduacaoField.getText();
            String plano = planoField.getText();
            String dataInicio = dataInicioField.getText();          // PROBLEMAS PARA LER NO FORMATO util.Date.
            String dataFim = dataFimField.getText();                // PROBLEMAS PARA LER NO FORMATO util.Date.

            // instanciar um objeto do tipo MatriculaModalidade
            //MatriculaModalidade tmp = new MatriculaModalidade(modalidade, graduacao, plano, dataInicio, dataFim);

            //System.out.println("Modalidade: " + modalidade);
            System.out.println("Graduação.: " + graduacao);
            System.out.println("Plano.....: " + plano);
            System.out.println("DataInício: " + dataInicio);
            System.out.println("DataFim...: " + dataFim);

            /*
            // tentar inserir no banco de dados
            try {
                MatriculaModalidadeDAO dao = new MatriculaModalidadeDAO(connection);
                dao.insert(new MatriculaModalidade(modalidade, graduacao, plano, dataInicio, dataFim));
            } catch (SQLException e) {
                System.err.printf("SQLException (%d): %s\n", e.getErrorCode(), e.getMessage());
                e.printStackTrace();
            }
            */
        }
    }
}
