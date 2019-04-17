package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RelatoriosMatriculasFrame extends JInternalFrame implements ActionListener {

    /* components: */
    private JComboBox<String> boxTipo;
    private JButton buttonProcessar;
    private JFormattedTextField formattedDate1;
    private JFormattedTextField formattedDate2;
    private JLabel labelAte;
    private JLabel labelDe;
    private JLabel labelPeriodo;
    private JPanel panelPeriodo;

    /* constructor: */
    public RelatoriosMatriculasFrame() {
        super("Matriculas");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.initComponents(this.getContentPane());
        this.pack();
        this.setVisible(true);
    }

    /* methods: */
    private void initComponents(Container container) {

        labelPeriodo = new JLabel("Período");
        labelDe = new JLabel("De:");
        labelAte = new JLabel("Até:");

        panelPeriodo = new JPanel();

        formattedDate1 = new JFormattedTextField("jFormattedTextField1");
        formattedDate1.addActionListener(this);

        formattedDate2 = new JFormattedTextField("jFormattedTextField2");
        formattedDate2.addActionListener(this);

        buttonProcessar = new JButton("Processar");

        boxTipo = new JComboBox<>();
        boxTipo.setModel(new DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        GroupLayout panelPeriodoLayout = new GroupLayout(panelPeriodo);
        panelPeriodo.setLayout(panelPeriodoLayout);
        panelPeriodoLayout.setHorizontalGroup(
                panelPeriodoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(panelPeriodoLayout.createSequentialGroup()
                                .addGroup(panelPeriodoLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(labelDe, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(labelAte, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelPeriodoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(formattedDate2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(formattedDate1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 46, Short.MAX_VALUE))
                        .addGroup(GroupLayout.Alignment.TRAILING, panelPeriodoLayout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelPeriodo, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23))
        );
        panelPeriodoLayout.setVerticalGroup(
                panelPeriodoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(panelPeriodoLayout.createSequentialGroup()
                                .addComponent(labelPeriodo, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelPeriodoLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(labelDe, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(formattedDate1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelPeriodoLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(labelAte, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(formattedDate2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 15, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(container);
        container.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(panelPeriodo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(30, Short.MAX_VALUE))
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(boxTipo, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(buttonProcessar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panelPeriodo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(buttonProcessar, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                                        .addComponent(boxTipo))
                                .addContainerGap())
        );
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == formattedDate1) {

        } else if (e.getSource() == formattedDate2) {

        }
    }
}
