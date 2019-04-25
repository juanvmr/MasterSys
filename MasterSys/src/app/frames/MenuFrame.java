package app.frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import database.dao.*;
import database.models.Usuario;


public class MenuFrame extends JFrame {

    /* attributes: */
    private Connection connection;
    private Usuario user;
    public static AlunoDAO alunoDAO;
    public static AssiduidadeDAO assiduidadeDAO;
    public static FaturaMatriculaDAO faturaMatriculaDAO;
    public static GraduacaoDAO graduacaoDAO;
    public static LocalDAO localDAO;
    public static MatriculaDAO matriculaDAO;
    public static MatriculaModalidadeDAO matriculaModalidadeDAO;
    public static ModalidadeDAO modalidadeDAO;
    public static PlanoDAO planoDAO;
    public static UsuariosDAO usuarioDAO;

    /* components: */
    private JMenuBar menuBar;
    private JMenu menuSistema, menuCadastro, menuProcessos, menuRelatorios, menuUtilitarios, menuAjuda;
    private JMenu menuProcessoFaturamento, menuProcessoMatricular, menuRelatorioFatura;
    private JMenuItem itemSistemaSair, itemSistemaUsuarios, itemCadastroAlunos, itemCadastroModalidades;
    private JMenuItem itemCadastroPlanos, itemProcessoMatriculaAluno;
    private JMenuItem itemProcessoGerarFaturas, itemProcessoConsultarFaturas;
    private JMenuItem itemRelatorioMatricula, itemRelatorioFaturaAberto, itemRelatorioFaturaPago;
    private JMenuItem itemUtilitariosControleAluno, itemAjudaSobre;
    private JDesktopPane desktop;

    private JInternalFrame frameUsuario;
    private JInternalFrame frameCadastrarAlunos, frameCadastrarModalidades, frameCadastrarPlanos;
    private JInternalFrame frameMatricularAluno, frameGerarFaturas, frameConsultarFaturas, frameRealizarPagamentos;
    private JInternalFrame frameRelatorioMatricula;
    private JInternalFrame frameControleAluno;

    /* constructor: */
    public MenuFrame(Connection connection, Usuario user) {
        super("MasterSys");
        this.setDefaultSize(0.75);

        this.initDAO(connection);
        this.user = user;

        this.updateUser();
        this.setJMenuBar(createMenuBar());
        this.setContentPane(createDesktop());
        this.setVisible(true);

        // debug
        this.itemProcessoConsultarFaturas.doClick();
    }

    private void initDAO(Connection connection) {
        alunoDAO = new AlunoDAO(connection);
        assiduidadeDAO = new AssiduidadeDAO(connection);
        faturaMatriculaDAO = new FaturaMatriculaDAO(connection);
        graduacaoDAO = new GraduacaoDAO(connection);
        localDAO = new LocalDAO(connection);
        matriculaDAO = new MatriculaDAO(connection);
        matriculaModalidadeDAO = new MatriculaModalidadeDAO(connection);
        modalidadeDAO = new ModalidadeDAO(connection);
        planoDAO = new PlanoDAO(connection);
    }


    private void updateUser() {
        if (this.user == null) return;

        if (this.user.getPerfil().isEmpty()) {
            this.usuarioDAO = new UsuariosDAO(connection);
            try {
                this.user = (Usuario) usuarioDAO.find(user);
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
            // itemProcessoRealizarPagamentos = new JMenuItem("Realizar Pagamento");

        menuProcessoMatricular = new JMenu("Matrícula");
            itemProcessoMatriculaAluno = new JMenuItem("Aluno");

        menuRelatorioFatura = new JMenu("FaturaMatricula");
            itemRelatorioFaturaAberto = new JMenuItem("Em Aberto");
            itemRelatorioFaturaPago = new JMenuItem("Pagas");
            itemRelatorioMatricula = new JMenuItem("Matrículas");

        itemUtilitariosControleAluno = new JMenuItem("Controle de Alunos");

        itemAjudaSobre = new JMenuItem("Sobre");

        menuSistema.add(itemSistemaUsuarios);
        menuSistema.add(itemSistemaSair);
        menuCadastro.add(itemCadastroAlunos);
        menuCadastro.add(itemCadastroModalidades);
        menuCadastro.add(itemCadastroPlanos);
        menuProcessos.add(menuProcessoMatricular);
        menuProcessos.add(menuProcessoFaturamento);
        menuRelatorios.add(itemRelatorioMatricula);
        menuRelatorios.add(menuRelatorioFatura);
        menuUtilitarios.add(itemUtilitariosControleAluno);
        menuAjuda.add(itemAjudaSobre);

        menuProcessoMatricular.add(itemProcessoMatriculaAluno);
        menuProcessoFaturamento.add(itemProcessoGerarFaturas);
        menuProcessoFaturamento.add(itemProcessoConsultarFaturas);
        // menuProcessoFaturamento.add(itemProcessoRealizarPagamentos);
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
                    frameUsuario = new SistemaUsuariosFrame();
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
                    frameCadastrarAlunos = new CadastrarAlunosFrame();
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
                    frameCadastrarModalidades = new CadastrarModalidadeGraduacaoFrame();
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
                    frameCadastrarPlanos = new CadastrarPlanosFrame();
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
                    frameGerarFaturas = new ProcessosGerarFaturasFrame();
                    frameGerarFaturas.setName(ProcessosGerarFaturasFrame.class.getName());
                    desktop.add(frameGerarFaturas);
                }
            }
        });

        itemProcessoConsultarFaturas.setAction(new AbstractAction(itemProcessoConsultarFaturas.getText()){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkFrame(ProcessosConsultarFaturasFrame.class.getName())) {
                    focusFrame(frameConsultarFaturas);
                } else {
                    frameConsultarFaturas = new ProcessosConsultarFaturasFrame();
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
                if (checkFrame(RelatoriosMatriculasFrame.class.getName())) {
                    focusFrame(frameRelatorioMatricula);
                } else {
                    frameRelatorioMatricula = new RelatoriosMatriculasFrame(connection);
                    frameRelatorioMatricula.setName(RelatoriosMatriculasFrame.class.getName());
                    desktop.add(frameRelatorioMatricula);
                }
            }
        });

        // Utilitarios
        itemUtilitariosControleAluno.setAction(new AbstractAction(itemUtilitariosControleAluno.getText()) {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkFrame(ControleAlunoFrame.class.getName())) {
                    focusFrame(frameControleAluno);
                } else {
                    frameControleAluno = new ControleAlunoFrame(connection);
                    frameControleAluno.setName(ControleAlunoFrame.class.getName());
                    desktop.add(frameControleAluno);
                }
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
