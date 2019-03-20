package panels;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Menu extends JFrame {
	
	private JMenuBar menu_bar;
	
	private JMenu menu_system;
	private JMenu menu_register;
	
	private JMenuItem menu_item_system_quit;
	private JMenuItem menu_item_register_alunos;
	
	private JDesktopPane desktop;
	
	public Menu() {
		
		setSize(500, 500);
		setTitle("Menu Principal");
		
		menu_bar = new JMenuBar();
		
		menu_system = new JMenu("Sistem");
		menu_item_system_quit = new JMenuItem(new AbstractAction("Sair") {
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
		
		setJMenuBar(menu_bar);
		setContentPane(CreateContentPane());
		
		setVisible(true);
	}
	
	public Container CreateContentPane() {
		JPanel contentPane = new JPanel(new BorderLayout());
		
		desktop = new JDesktopPane() {
			Image img = (new ImageIcon("")).getImage();
			
			public void paintComponent(Graphics g) {
				g.drawImage(img, 0, 0, this);
			}
		};
		
		contentPane.setOpaque(true);
		
		contentPane.add(desktop);
		
		return contentPane;
	}
	
}
