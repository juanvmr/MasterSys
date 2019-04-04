package  frames;

import database.dao.ModalidadeDAO;
import database.models.Modalidade;

import java.awt.Container;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

public class PlanosFrame extends JFrame {

    private Connection conn;

    private JButton adicionarButton = new JButton("Buscar");
    private JButton buscaButton = new JButton("Adicionar");
    private JButton removerButton = new JButton("Remover");
    private JButton salvarButton = new JButton("Salvar");
    private JLabel modalidadeLabel = new JLabel("Modalidade:");
    private JLabel jLabelPlano = new JLabel("Plano:");
    private JLabel jLabelValor = new JLabel("Valor:");
    private JTextField jTextFieldPlano = new JTextField();
    private JTextField jTextFieldValor = new JTextField();
    private JComboBox<String> jComboBoxModalidades;

    public PlanosFrame(String title, Connection conn) {
        super(title);
        this.setSize(800, 600);

        this.conn = conn;
    }

    public void initComponents(Container pane) {

        /* AQUI FICA A STRING QUE RECEBE AS MODALIDADES */
        jComboBoxModalidades = new JComboBox<>();

        try {
            ModalidadeDAO modalidadeDAO = new ModalidadeDAO(conn);

            List<Object> list = modalidadeDAO.selectAll();
            String[] vetor = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                vetor[i] = list.get(i).toString();
            }


            jComboBoxModalidades.setModel(new DefaultComboBoxModel<>(vetor));
        } catch (SQLException e) {
            System.err.printf("SQLException (%d): %s\n", e.getErrorCode(), e.getMessage());
        }

        //jComboBoxModalidades.setModel(new DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTextFieldValor.setText("0,00");
        jTextFieldValor.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                jTextFieldValorMouseClicked(evt);
            }
        });
        jTextFieldValor.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                jTextFieldValorKeyTyped(evt);
            }
        });

        GroupLayout layout = new GroupLayout(pane);
        pane.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabelValor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabelPlano, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(modalidadeLabel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(buscaButton, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(adicionarButton, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(removerButton, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(salvarButton, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jComboBoxModalidades, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextFieldPlano)
                                        .addComponent(jTextFieldValor, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 101, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(buscaButton, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                                        .addComponent(adicionarButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(removerButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(salvarButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(modalidadeLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jComboBoxModalidades, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabelPlano, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jTextFieldPlano)
                                                .addGap(5, 5, 5)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabelValor, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jTextFieldValor, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                                .addGap(5, 5, 5)))
                                .addContainerGap(84, Short.MAX_VALUE))
        );
    }

    private void jTextFieldValorKeyTyped(KeyEvent evt) {
        String caracteres="0987654321";
        if(!caracteres.contains(evt.getKeyChar()+"")){
            evt.consume();
        }
    }

    private void jTextFieldValorMouseClicked(MouseEvent evt) {
        jTextFieldValor.setText("");
    }

/*
    public static void main(String args[]) {

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger( frames.PlanosFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger( frames.PlanosFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger( frames.PlanosFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger( frames.PlanosFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PlanosFrame frame = new  frames.PlanosFrame();
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.initComponents(frame.getContentPane());
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
*/
}
