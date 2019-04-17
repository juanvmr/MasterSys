package frames;

import frames.components.DateFormattedTextField;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.Date;

public class ProcessosRealizarPagamentoFrame extends JInternalFrame implements ActionListener {

    /* attributes: */
    private static String[] situacaoList = new String[] { "Item 1", "Item 2", "Item 3", "Item 4" };
    private Connection connection;

    /* components: */
    private JLabel ateLabel, deLabel, situacaoLabel;
    private DateFormattedTextField dataInicialField, dataFinalField;
    private JComboBox<String> situacaoComboBox;
    private JTable table;
    private JButton pesquisarButton;

    /* constructor: */
    public ProcessosRealizarPagamentoFrame(Connection connection) {
        super("Pagamentos");

        this.connection = connection;

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.initComponents(this.getContentPane());
        this.pack();
        this.setVisible(true);
    }

    /* methods: */
    private void initComponents(Container container) {
        container.setLayout(new BorderLayout());
        container.add(createTopPanel(), BorderLayout.NORTH);
        //container.add(createTable(), BorderLayout.CENTER);
    }

    private JPanel createTopPanel() {

        int inset = 5;
        int border = 10;

        deLabel = new JLabel("De:");
        ateLabel = new JLabel("Até:");
        situacaoLabel = new JLabel("Situação:");

        dataInicialField = new DateFormattedTextField();
        dataFinalField = new DateFormattedTextField();

        situacaoComboBox = new JComboBox<>();
        situacaoComboBox.setModel(new DefaultComboBoxModel<>(situacaoList));

        pesquisarButton = new JButton("Pesquisar");

        JPanel content = new JPanel(new GridBagLayout());
        content.setBorder(new EmptyBorder(border, border, border, border));
        //this.setContentPane(content);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(inset, inset, inset, inset);
        constraints.fill = GridBagConstraints.HORIZONTAL;

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.weightx = 0;
        content.add(deLabel, constraints);
        constraints.gridx++;
        content.add(dataInicialField, constraints);
        constraints.gridx++;
        content.add(ateLabel, constraints);
        constraints.gridx++;
        content.add(dataFinalField, constraints);
        constraints.gridx++;
        content.add(situacaoLabel, constraints);
        constraints.gridx++;
        content.add(situacaoComboBox, constraints);
        constraints.gridx++;
        content.add(pesquisarButton, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        constraints.gridwidth = 7;
        constraints.weightx = 1;
        content.add(createTable(), constraints);

        return content;
    }

    private JScrollPane createTable() {
        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object [][] {
                        {null, null, null, null, null, null}
                },
                new String [] {
                        "Matrícula", "Aluno", "Vencimento", "Valor", "Pagamento", "Cancelamento"
                }
        ) {
            boolean[] canEdit = new boolean [] {
                    false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });

        JScrollPane scrollPane1 = new JScrollPane(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(0).setPreferredWidth(0);
            table.getColumnModel().getColumn(1).setResizable(false);
            table.getColumnModel().getColumn(2).setResizable(false);
            table.getColumnModel().getColumn(2).setPreferredWidth(0);
            table.getColumnModel().getColumn(3).setResizable(false);
            table.getColumnModel().getColumn(3).setPreferredWidth(0);
            table.getColumnModel().getColumn(4).setResizable(false);
            table.getColumnModel().getColumn(4).setPreferredWidth(0);
            table.getColumnModel().getColumn(5).setResizable(false);
            table.getColumnModel().getColumn(5).setPreferredWidth(0);
        }

        return scrollPane1;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ProcessosRealizarPagamentoFrame frame = new ProcessosRealizarPagamentoFrame(null);
                frame.setVisible(true);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pesquisarButton) {
            // code
        }
    }
}
