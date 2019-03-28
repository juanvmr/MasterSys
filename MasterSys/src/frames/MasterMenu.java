package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MasterMenu extends JFrame {

    /* elements: */
    private JMenuBar menu_bar;
    private JMenu menu_system, menu_register;
    private JMenuItem menu_item_system_quit, menu_item_register_alunos;
    private JDesktopPane desktop;

    /* constructor: */
    public MasterMenu(String title) {
        super(title);
        this.setSize(800, 600);

        menu_bar = new JMenuBar();

        menu_system = new JMenu("Sistem");
        menu_item_system_quit = new JMenuItem(new AbstractAction("Sair") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menu_system.add(menu_item_system_quit);

        menu_register = new JMenu("Cadastro");
        menu_item_register_alunos = new JMenuItem("Alunos");
        menu_register.add(menu_item_register_alunos);

        menu_bar.add(menu_system);
        menu_bar.add(menu_register);

        this.setJMenuBar(menu_bar);
        this.setContentPane(CreateContentPane());
        this.setVisible(true);

        desktop.add(new MasterLogin("teste"));
    }

    public Container CreateContentPane() {
        JPanel contentPane = new JPanel(new BorderLayout());

        desktop = new JDesktopPane() {
            Image img = (new ImageIcon("").getImage());

            public void paintComponent(Graphics g) {
                g.drawImage(img, 0, 0, this);
            }
        };

        contentPane.setOpaque(true);;
        contentPane.add(desktop);

        return contentPane;
    }

}
