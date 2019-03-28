package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Logger;
import java.util.logging.Level;

public class PlanosFrame extends JFrame {

    private JButton adicionarButton;
    private JButton buscaButton;
    private JButton removeButton;
    private JButton salvarButton;
    private JComboBox<String> modalidadesComboBox;
    private JLabel modalidadeLabel;
    private JLabel planoLabel;
    private JLabel valorLabel;
    private JTextField planoField;
    private JTextField valorField;

    public PlanosFrame(String title) {
        super(title);
    }

    private void initComponents(Container pane) {

        buscaButton = new JButton("Buscar");
        adicionarButton = new JButton("Adicionar");
        removeButton = new JButton("Remover");
        salvarButton = new JButton("Salvar");
        modalidadeLabel = new JLabel("Modalidade:");
        planoLabel = new JLabel("Plano:");
        valorLabel = new JLabel("Valor:");
        modalidadesComboBox = new JComboBox<>();
        planoField = new JTextField();
        valorField = new JTextField();


        /* AQUI FICA A STRING QUE RECEBE AS MODALIDADES */
        modalidadesComboBox.setModel(new DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        valorField.setText("0,00");
        valorField.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                valorFieldMouseClicked(evt);
            }
        });
        valorField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                valorFieldKeyTyped(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        pane.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(valorLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(planoLabel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(modalidadeLabel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(buscaButton, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(adicionarButton, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(removeButton, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(salvarButton, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(modalidadesComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(planoField)
                                        .addComponent(valorField, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 101, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(buscaButton, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                                        .addComponent(adicionarButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(removeButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(salvarButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(modalidadeLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(modalidadesComboBox, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(planoLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(planoField)
                                                .addGap(5, 5, 5)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(valorLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(valorField, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                                .addGap(5, 5, 5)))
                                .addContainerGap(84, Short.MAX_VALUE))
        );
    }

    private void valorFieldKeyTyped(KeyEvent event) {
        String characters = "0987654321";
        if (!characters.contains(event.getKeyChar() + "")) {
            event.consume();
        }
    }

    private void valorFieldMouseClicked(MouseEvent event) {
        valorField.setText("");
    }

    public static void main(String[] args) {

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PlanosFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(PlanosFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PlanosFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(PlanosFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                PlanosFrame frame = new PlanosFrame("Planos");
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.initComponents(frame.getContentPane());
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

}
