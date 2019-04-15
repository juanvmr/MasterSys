package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import database.models.Usuario;
import database.dao.UsuariosDAO;


public class MainFrame extends JFrame {

    /* attributes: */
    private Connection connection;
    private String username;

    /* components: */
    private JMenuBar menuBar;
    private JMenu menuSistema, menuCadastro, menuProcessos, menuRelatorios, menuAjuda;
    private JMenu menuProcessoFaturamento, menuProcessoMatricula, menuRelatorioFatura;
    private JMenuItem itemSistemaSair, itemSistemaUsuarios, itemCadastroAlunos, itemCadastroModalidades;
    private JMenuItem itemCadastroPlanos, itemProcessoMatriculaAluno;
    private JMenuItem itemProcessoFaturamentoGerar, itemProcessoFaturamentoConsultar, itemProcessoFaturamentoPagar;
    private JMenuItem itemRelatorioMatricula, itemRelatorioFaturaAberto, itemRelatorioFaturaPago, itemAjudaSobre;
    private JDesktopPane desktop;
    private JInternalFrame frameUsuario, framePlanos, frameCadastroAlunos, frameCadastroModalidades;
    private JInternalFrame frameCadastroPlanos, frameMatriculaAluno, frameFaturamentoGerar;
    private JInternalFrame frameFaturamentoConsultar, frameFaturamentoPagar;

    /* constructor: */
    public MainFrame(String title, Connection connection, String username) {
        super(title);

        this.connection = connection;
        this.username = username;

        this.setDefaultSize(0.75);
        this.setJMenuBar(createMenuBar());
        this.setContentPane(createDesktop());
        this.setVisible(true);
    }

    private JDesktopPane createDesktop() {
        desktop = new JDesktopPane();
        desktop.setBackground(new Color(210, 210, 210));

        return desktop;
    }

    private JMenuBar createMenuBar(){
        // menu bar
        menuBar = new JMenuBar();

        // menu
        menuSistema = new JMenu("Sistema");
        menuCadastro = new JMenu("Cadastro");
        menuProcessos = new JMenu("Processos");
        menuRelatorios = new JMenu("Relatórios");
        menuAjuda = new JMenu("Ajuda");

        // submenus
        itemSistemaSair = new JMenuItem("Sair");
        itemSistemaUsuarios = new JMenuItem("Usuários");

        itemCadastroAlunos = new JMenuItem("Alunos");
        itemCadastroModalidades = new JMenuItem("Modalidades");
        itemCadastroPlanos = new JMenuItem("Planos");

        menuProcessoFaturamento = new JMenu("Faturamento");
            itemProcessoFaturamentoConsultar = new JMenuItem("Consultar");
            itemProcessoFaturamentoGerar = new JMenuItem("Gerar");
            itemProcessoFaturamentoPagar = new JMenuItem("Pagar");

        menuProcessoMatricula = new JMenu("Matrícula");
            itemProcessoMatriculaAluno = new JMenuItem("Aluno");

        menuRelatorioFatura = new JMenu("Fatura");
            itemRelatorioFaturaAberto = new JMenuItem("Em Aberto");
            itemRelatorioFaturaPago = new JMenuItem("Pagas");
            itemRelatorioMatricula = new JMenuItem("Matrículas");

        itemAjudaSobre = new JMenuItem("Sobre");

        menuSistema.add(itemSistemaUsuarios);
        menuSistema.add(itemSistemaSair);
        menuCadastro.add(itemCadastroAlunos);
        menuCadastro.add(itemCadastroModalidades);
        menuCadastro.add(itemCadastroPlanos);
        menuProcessos.add(menuProcessoFaturamento);
        menuProcessos.add(menuProcessoMatricula);
        menuRelatorios.add(itemRelatorioMatricula);
        menuRelatorios.add(menuRelatorioFatura);
        menuAjuda.add(itemAjudaSobre);

        menuProcessoFaturamento.add(itemProcessoFaturamentoGerar);
        menuProcessoFaturamento.add(itemProcessoFaturamentoPagar);
        menuProcessoFaturamento.add(itemProcessoFaturamentoConsultar);
        menuProcessoMatricula.add(itemProcessoMatriculaAluno);
        menuRelatorioFatura.add(itemRelatorioFaturaAberto);
        menuRelatorioFatura.add(itemRelatorioFaturaPago);

        menuBar.add(menuSistema);
        menuBar.add(menuCadastro);
        menuBar.add(menuProcessos);
        menuBar.add(menuRelatorios);
        menuBar.add(menuAjuda);

        this.setListeners();
        // this.setAccessibleMenus();

        return menuBar;
    }
    
    private void setListeners(){

        // Sistema
        itemSistemaUsuarios.setAction(new AbstractAction(itemSistemaUsuarios.getText()){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkFrame(UsuariosFrame.class.getName())) {
                    focusFrame(frameUsuario);
                } else {
                    frameUsuario = new UsuariosFrame(connection);
                    frameUsuario.setName(UsuariosFrame.class.getName());
                    desktop.add(frameUsuario);
                }
            }
        });

        itemSistemaSair.setAction(new AbstractAction(itemSistemaSair.getText()){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Cadastro
        itemCadastroAlunos.setAction(new AbstractAction(itemCadastroAlunos.getText()){
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        itemCadastroModalidades.setAction(new AbstractAction(itemCadastroModalidades.getText()){
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        itemCadastroPlanos.setAction(new AbstractAction(itemCadastroPlanos.getText()){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkFrame(PlanosFrame.class.getName())) {
                    focusFrame(framePlanos);
                } else {
                    framePlanos = new PlanosFrame("Planos", connection);
                    desktop.add(framePlanos);
                }
            }
        });

        // Processos
        itemProcessoFaturamentoGerar.setAction(new AbstractAction(itemProcessoFaturamentoGerar.getText()){
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        itemProcessoFaturamentoPagar.setAction(new AbstractAction(itemProcessoFaturamentoPagar.getText()){
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        itemProcessoFaturamentoConsultar.setAction(new AbstractAction(itemProcessoFaturamentoConsultar.getText()){
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        itemProcessoMatriculaAluno.setAction(new AbstractAction(itemProcessoMatriculaAluno.getText()){
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        // Relatórios
        itemRelatorioFaturaAberto.setAction(new AbstractAction(itemRelatorioFaturaAberto.getText()){
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        itemRelatorioFaturaPago.setAction(new AbstractAction(itemRelatorioFaturaPago.getText()){
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        itemRelatorioMatricula.setAction(new AbstractAction(itemRelatorioMatricula.getText()){
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        // Ajuda
        itemAjudaSobre.setAction(new AbstractAction(itemAjudaSobre.getText()){
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
    }

    private void setAccessibleMenus() {

        menuRelatorios.setEnabled(false);

        if (username.equalsIgnoreCase("admin")) {
            System.out.println("Username is equal to ADMIN");
            return;
        }

        itemSistemaUsuarios.setEnabled(false);

        Usuario user = new Usuario(username);
        try {
            UsuariosDAO dao = new UsuariosDAO(connection);
            user = (Usuario) dao.select(user);
        } catch (SQLException e) {
            System.err.printf("SQLException (%d): %s\n", e.getErrorCode(), e.getMessage());
            return;
        } catch (NullPointerException e) {
            System.err.printf("WARNING: Connection not found.");
            menuSistema.setEnabled(false);
            menuCadastro.setEnabled(false);
            menuProcessos.setEnabled(false);
            menuRelatorios.setEnabled(false);
            menuAjuda.setEnabled(false);
            return;
        }
        
        if (user.getPerfil().equals("Cadastral")) {
            menuProcessos.setEnabled(false);
        } else if (user.getPerfil().equals("Financeiro")){
            menuCadastro.setEnabled(false);
            menuProcessoMatricula.setEnabled(false);
        } else if (user.getPerfil().equals("Matricular")) {
            menuCadastro.setEnabled(false);
            menuProcessoFaturamento.setEnabled(false);
        }
    }

    /**
     * Searches opened frames and checks if it's enabled.
     * @param name      -- frame name
     * @return boolean
     */
    private boolean checkFrame(String name){
		JInternalFrame[] frames = desktop.getAllFrames();
		
		for (JInternalFrame f : frames){
			if (f.getName().equalsIgnoreCase(name)){
				return true;
			}
		}
		return false;
	}

    /**
     * Selects a opened frame from desktop panel.
     * @param frame     -- frame reference to be focused.
     */
	// protected
	private void focusFrame(JInternalFrame frame) {
		try {
			frame.setSelected(true);
		} catch (PropertyVetoException e) {
			System.out.printf("focusFrame(): %s\n", e.getMessage());
			e.printStackTrace();
		}
	}

    /**
     * Sets main frame size base on computer screen size.
     * @param percentage        -- screen size percentage
     */
    private void setDefaultSize(double percentage) {

        Dimension screenResolution = Toolkit.getDefaultToolkit().getScreenSize();

        int width = (int) (percentage * screenResolution.getWidth());
        int height = (int) (percentage * screenResolution.getHeight());
        int x = (int) (screenResolution.getWidth() - width) / 2;
        int y = (int) (screenResolution.getHeight() - height) / 2;

        this.setBounds(x, y, width, height);
    }
}
