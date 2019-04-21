package app.frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import database.models.Usuario;
import database.dao.UsuariosDAO;


public class MenuFrame extends JFrame {

    /* attributes: */
    private Connection connection;
    private UsuariosDAO usuariosDAO;
    private Usuario user;

    /* components: */
    private JMenuBar menuBar;
    private JMenu menuSistema, menuCadastro, menuProcessos, menuRelatorios, menuUtilitarios, menuAjuda;
    private JMenu menuProcessoFaturamento, menuProcessoMatricular, menuRelatorioFatura;
    private JMenuItem itemSistemaSair, itemSistemaUsuarios, itemCadastroAlunos, itemCadastroModalidades;
    private JMenuItem itemCadastroPlanos, itemProcessoMatriculaAluno;
    private JMenuItem itemProcessoGerarFaturas, itemProcessoConsultarFaturas, itemProcessoRealizarPagamentos;
    private JMenuItem itemRelatorioMatricula, itemRelatorioFaturaAberto, itemRelatorioFaturaPago, itemAjudaSobre;
    private JDesktopPane desktop;

    // Sistema
    private JInternalFrame frameUsuario;
    // Cadastro
    private JInternalFrame frameCadastrarAlunos, frameCadastrarModalidades, frameCadastrarPlanos;
    // Processos
    private JInternalFrame frameMatricularAluno, frameGerarFaturas, frameConsultarFaturas, frameRealizarPagamentos;

    /* constructor: */
    public MenuFrame(Connection connection, Usuario user) {
        super("MasterSys");
        this.setDefaultSize(0.75);

        this.connection = connection;
        this.user = user;

        this.updateUser();
        this.setJMenuBar(createMenuBar());
        this.setContentPane(createDesktop());
        this.setVisible(true);
    }

    private void updateUser() {
        if (this.user == null) return;

        if (this.user.getPerfil().isEmpty()) {
            this.usuariosDAO = new UsuariosDAO(connection);
            try {
                this.user = (Usuario) usuariosDAO.find(user);
            } catch (SQLException e) {
                System.err.printf("SQLException (%d): %s\n", e.getErrorCode(), e.getMessage());
            }
        }
        System.out.println("USER: " + user.toString());
    }

    private JDesktopPane createDesktop() {
        desktop = new JDesktopPane();
        // desktop.setBackground(new Color(210, 210, 210));

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
        menuUtilitarios = new JMenu("Utilitários");
        menuAjuda = new JMenu("Ajuda");

        // submenus
        itemSistemaSair = new JMenuItem("Sair");
        itemSistemaUsuarios = new JMenuItem("Usuários");

        itemCadastroAlunos = new JMenuItem("Alunos");
        itemCadastroModalidades = new JMenuItem("Modalidades");
        itemCadastroPlanos = new JMenuItem("Planos");

        menuProcessoFaturamento = new JMenu("Faturamento");
            itemProcessoGerarFaturas = new JMenuItem("Gerar Faturas");
            itemProcessoConsultarFaturas = new JMenuItem("Consultar Faturas");
            itemProcessoRealizarPagamentos = new JMenuItem("Realizar Pagamento");

        menuProcessoMatricular = new JMenu("Matrícula");
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
        menuProcessos.add(menuProcessoMatricular);
        menuRelatorios.add(itemRelatorioMatricula);
        menuRelatorios.add(menuRelatorioFatura);
        menuAjuda.add(itemAjudaSobre);

        menuProcessoFaturamento.add(itemProcessoGerarFaturas);
        menuProcessoFaturamento.add(itemProcessoConsultarFaturas);
        menuProcessoFaturamento.add(itemProcessoRealizarPagamentos);
        menuProcessoMatricular.add(itemProcessoMatriculaAluno);
        menuRelatorioFatura.add(itemRelatorioFaturaAberto);
        menuRelatorioFatura.add(itemRelatorioFaturaPago);

        menuBar.add(menuSistema);
        menuBar.add(menuCadastro);
        menuBar.add(menuProcessos);
        menuBar.add(menuRelatorios);
        menuBar.add(menuUtilitarios);
        menuBar.add(menuAjuda);

        this.setListeners();
        this.configMenu();

        return menuBar;
    }
    
    private void setListeners(){

        // Sistema
        itemSistemaUsuarios.setAction(new AbstractAction(itemSistemaUsuarios.getText()){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkFrame(SistemaUsuariosFrame.class.getName())) {
                    focusFrame(frameUsuario);
                } else {
                    frameUsuario = new SistemaUsuariosFrame(connection);
                    frameUsuario.setName(SistemaUsuariosFrame.class.getName());
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
                if (checkFrame(CadastrarAlunosFrame.class.getName())) {
                    focusFrame(frameCadastrarAlunos);
                } else {
                    frameCadastrarAlunos = new CadastrarAlunosFrame(connection);
                    frameCadastrarAlunos.setName(CadastrarAlunosFrame.class.getName());
                    desktop.add(frameCadastrarAlunos);
                }
            }
        });

        itemCadastroModalidades.setAction(new AbstractAction(itemCadastroModalidades.getText()){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkFrame(CadastrarModalidadeGraduacaoFrame.class.getName())) {
                    focusFrame(frameCadastrarModalidades);
                } else {
                    frameCadastrarModalidades = new CadastrarModalidadeGraduacaoFrame(connection);
                    frameCadastrarModalidades.setName(CadastrarModalidadeGraduacaoFrame.class.getName());
                    desktop.add(frameCadastrarModalidades);
                }
            }
        });

        itemCadastroPlanos.setAction(new AbstractAction(itemCadastroPlanos.getText()){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkFrame(CadastrarPlanosFrame.class.getName())) {
                    focusFrame(frameCadastrarPlanos);
                } else {
                    frameCadastrarPlanos = new CadastrarPlanosFrame(connection);
                    frameCadastrarPlanos.setName(CadastrarPlanosFrame.class.getName());
                    desktop.add(frameCadastrarPlanos);
                }
            }
        });

        // Processos
        itemProcessoGerarFaturas.setAction(new AbstractAction(itemProcessoGerarFaturas.getText()){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkFrame(ProcessosGerarFaturasFrame.class.getName())) {
                    focusFrame(frameGerarFaturas);
                } else {
                    frameGerarFaturas = new ProcessosGerarFaturasFrame(connection);
                    frameGerarFaturas.setName(ProcessosGerarFaturasFrame.class.getName());
                    desktop.add(frameGerarFaturas);
                }
            }
        });

        itemProcessoRealizarPagamentos.setAction(new AbstractAction(itemProcessoRealizarPagamentos.getText()){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkFrame(ProcessosRealizarPagamentoFrame.class.getName())) {
                    focusFrame(frameRealizarPagamentos);
                } else {
                    frameRealizarPagamentos = new ProcessosRealizarPagamentoFrame(connection);
                    frameRealizarPagamentos.setName(ProcessosRealizarPagamentoFrame.class.getName());
                    desktop.add(frameRealizarPagamentos);
                }
            }
        });

        itemProcessoConsultarFaturas.setAction(new AbstractAction(itemProcessoConsultarFaturas.getText()){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkFrame(ProcessosConsultarFaturasFrame.class.getName())) {
                    focusFrame(frameConsultarFaturas);
                } else {
                    frameConsultarFaturas = new ProcessosConsultarFaturasFrame(connection);
                    frameConsultarFaturas.setName(ProcessosConsultarFaturasFrame.class.getName());
                    desktop.add(frameConsultarFaturas);
                }
            }
        });

        itemProcessoMatriculaAluno.setAction(new AbstractAction(itemProcessoMatriculaAluno.getText()){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkFrame(ProcessosMatricularAlunosFrame.class.getName())) {
                    focusFrame(frameMatricularAluno);
                } else {
                    frameMatricularAluno = new ProcessosMatricularAlunosFrame(connection);
                    frameMatricularAluno.setName(ProcessosMatricularAlunosFrame.class.getName());
                    desktop.add(frameMatricularAluno);
                }
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

    private void configMenu() {

        itemSistemaUsuarios.setEnabled(false);
        menuCadastro.setEnabled(false);
        menuProcessos.setEnabled(false);
        menuProcessoMatricular.setEnabled(false);
        menuProcessoFaturamento.setEnabled(false);
        menuRelatorios.setEnabled(false);
        menuUtilitarios.setEnabled(false);

        switch (this.user.getPerfil()) {
            case "Cadastral":
                menuCadastro.setEnabled(true);
                break;
            case "Matricular":
                menuProcessos.setEnabled(true);
                menuProcessoMatricular.setEnabled(true);
                break;
            case "Financeiro":
                menuProcessos.setEnabled(true);
                menuProcessoFaturamento.setEnabled(true);
                break;
            case "Completo":
                itemSistemaUsuarios.setEnabled(true);
                menuCadastro.setEnabled(true);
                menuProcessos.setEnabled(true);
                menuProcessoMatricular.setEnabled(true);
                menuProcessoFaturamento.setEnabled(true);
                menuRelatorios.setEnabled(true);
                menuUtilitarios.setEnabled(true);
                break;
        }
    }

    /**
     * Searches opened app and checks if it's enabled.
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
			System.err.printf("focusFrame(): %s\n", e.getMessage());
			e.printStackTrace();
		} catch (NullPointerException e) {
		    System.err.println("Frame " + frame.getTitle() + " not found.");
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
