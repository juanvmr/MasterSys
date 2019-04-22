package app.frames;

import app.components.DateField;
import database.dao.GraduacaoDAO;
import database.dao.MatriculaModalidadeDAO;
import database.dao.ModalidadeDAO;
import database.dao.PlanoDAO;
import database.models.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class AdicionarModalidadesDialog extends JDialog implements ActionListener, ItemListener {

    /* attributes:"*/
    private MatriculaModalidade matricula_modalidade;
    private ModalidadeDAO modalidadeDAO;
    private GraduacaoDAO graduacaoDAO;
    private PlanoDAO planoDAO;
    private List<Object> modalidadeList, graduacaoList, planoList;

    /* components: */
    private JComboBox<Object> modalidadeComboBox, graduacaoComboBox, planoComboBox;
    private DateField dataInicioField, dataFimField;
    private JButton addButton;

    /* constructor: */

    public AdicionarModalidadesDialog(JFrame parent, Connection connection) {
        super(parent, "Adicionar Modalidades", true);

        this.modalidadeDAO = new ModalidadeDAO(connection);
        this.graduacaoDAO = new GraduacaoDAO(connection);
        this.planoDAO = new PlanoDAO(connection);

        this.initComponents();
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setVisible(true);
    }

    /* methods: */
    private void initComponents() {

        JLabel modalidadeLabel = new JLabel("Modalidade:", JLabel.CENTER);
        JLabel graduacaoLabel = new JLabel("Graduação:", JLabel.CENTER);
        JLabel planoLabel = new JLabel("Plano:", JLabel.CENTER);
        JLabel dataInicioLabel = new JLabel("Data Início:", JLabel.CENTER);
        JLabel dataFimLabel = new JLabel("Data Fim:", JLabel.CENTER);

        modalidadeComboBox = new JComboBox<>();
        modalidadeComboBox.addItemListener(this);
        this.updateModalidadeComboBox();

        graduacaoComboBox = new JComboBox<>();
        // graduacaoComboBox.addItemListener(this);
        this.updateGraduacaoComboBox();

        planoComboBox = new JComboBox<>();
        // planoComboBox.addItemListener(this);
        this.updatePlanoComboBox();

        dataInicioField = new DateField();
        dataInicioField.setValue(new Date());

        dataFimField = new DateField();
        dataFimField.setValue(new Date());

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
        content.add(graduacaoComboBox, constraints);
        constraints.gridy++;
        content.add(planoComboBox, constraints);
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

    private void updateModalidadeComboBox() {
        try {
            modalidadeList = modalidadeDAO.select();
            if (modalidadeList != null) {
                modalidadeComboBox.setModel(new DefaultComboBoxModel<>(modalidadeList.toArray()));
            } else {
                modalidadeComboBox.setModel(new DefaultComboBoxModel<>());
            }
        } catch (SQLException e) {
            System.err.printf("SQLException (%d): %s\n", e.getErrorCode(), e.getMessage());
        }
    }

    private void updateGraduacaoComboBox() {
        Modalidade m = (Modalidade) modalidadeComboBox.getSelectedItem();
        if (m != null) {
            try {
                graduacaoList = graduacaoDAO.select(m);
                graduacaoComboBox.setModel(new DefaultComboBoxModel<>(graduacaoList.toArray()));
            } catch (SQLException e) {
                System.err.printf("SQLException (%d): %s\n", e.getErrorCode(), e.getMessage());
            }
        } else {
            graduacaoList = null;
            graduacaoComboBox.setModel(new DefaultComboBoxModel<>());
        }
    }

    private void updatePlanoComboBox() {
        Modalidade m = (Modalidade) modalidadeComboBox.getSelectedItem();
        if (m != null) {
            try {
                planoList = planoDAO.select(m);
                planoComboBox.setModel(new DefaultComboBoxModel<>(planoList.toArray()));
            } catch (SQLException e) {
                System.err.printf("SQLException (%d): %s\n", e.getErrorCode(), e.getMessage());
            }
        } else {
            planoList = null;
            planoComboBox.setModel(new DefaultComboBoxModel<>());
        }
    }

    private MatriculaModalidade readInput() {
        MatriculaModalidade tmp = new MatriculaModalidade();
        if (modalidadeComboBox.getSelectedItem() != null) tmp.setModalidade(((Modalidade) modalidadeComboBox.getSelectedItem()).getModalidade());
        if (graduacaoComboBox.getSelectedItem() != null) tmp.setGraduacao(((Graduacao) graduacaoComboBox.getSelectedItem()).getGraduacao());
        if (planoComboBox.getSelectedItem() != null) tmp.setPlano(((Plano) planoComboBox.getSelectedItem()).getPlano());
        if (dataInicioField.getDate() != null) tmp.setDataInicio(dataInicioField.getDate());
        if (dataFimField.getDate() != null) tmp.setDataFim(dataFimField.getDate());
        return tmp;
    }

    public MatriculaModalidade getValue() {
        return matricula_modalidade;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == addButton) {
            // check if input fields are filled
            if ((modalidadeComboBox.getSelectedItem() != null) && (graduacaoComboBox.getSelectedItem() != null) &&
                (planoComboBox.getSelectedItem() != null) && (dataInicioField.getDate() != null) && (dataFimField.getDate() != null)) {
                // update variable to return
                matricula_modalidade = readInput();
                // close window
                this.dispose();
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == modalidadeComboBox) {
            updateGraduacaoComboBox();
            updatePlanoComboBox();
        }
    }

}
