package frames;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import database.models.Usuario;
import database.dao.UsuariosDAO;


public class MasterMenu extends JFrame {

    /* elements: */
    private Connection conn;
    private JMenuBar menuBar;
    private JMenu menuSistema, menuCadastro, menuProcessos, menuRelatorios, menuAjuda;
    private JMenuItem itemSistemaSair, itemSistemaUsuarios, itemCadastroAlunos, itemCadastroModalidades, itemCadastroPlanos;
    private JMenuItem itemProcessoMatricula, itemProcessoMatriculaAluno, itemProcessoFaturamento;
    private JMenuItem itemProcessoFaturamentoGerar, itemProcessoFaturamentoConsultar, itemProcessoFaturamentoPagar;
    private JMenuItem itemRelatorioMatricula, itemRelatorioFatura, itemRelatorioFaturaAberto, itemRelatorioFaturaPago;
    private JMenuItem itemAjudaSobre;
    private JDesktopPane desktop;
    private JInternalFrame windowUsuario, windowPlanos, windowCadastroAlunos, windowCadastroModalidades;
    private JInternalFrame windowCadastroPlanos, windowMatriculaAluno, windowFaturamentoGerar, windowFaturamentoConsultar;
    private JInternalFrame windowFaturamentoPagar;

    /* constructor: */
    public MasterMenu(String title, Connection conn, String username) {
        super(title);
        this.setSize(800, 600);

        createMenu();
        addListeners();

        this.setJMenuBar(menuBar);
        this.setContentPane(CreateContentPane());
        this.setAccessibleMenus(username, conn);
        this.setVisible(true);

        this.conn = conn;
    }

    private void createMenu(){
        menuBar = new JMenuBar();

        menuSistema = new JMenu("Sistema");
        menuCadastro = new JMenu("Cadastro");
        menuProcessos = new JMenu("Processos");
        menuRelatorios = new JMenu("Relatórios");
        menuAjuda = new JMenu("Ajuda");

        itemSistemaSair = new JMenuItem("Sair");
        itemSistemaUsuarios = new JMenuItem("Usuários");
        itemCadastroAlunos = new JMenuItem("Alunos");
        itemCadastroModalidades = new JMenuItem("Modalidades");
        itemCadastroPlanos = new JMenuItem("Planos");
        itemProcessoFaturamento = new JMenuItem("Faturamento");
        itemProcessoFaturamentoConsultar = new JMenuItem("Consultar");
        itemProcessoFaturamentoGerar = new JMenuItem("Gerar");
        itemProcessoFaturamentoPagar = new JMenuItem("Pagar");
        itemProcessoMatricula = new JMenuItem("Matrícula");
        itemProcessoMatriculaAluno = new JMenuItem("Aluno");
        itemRelatorioFatura = new JMenuItem("Fatura");
        itemRelatorioFaturaAberto = new JMenuItem("Em aberto");
        itemRelatorioFaturaPago = new JMenuItem("Pagas");
        itemRelatorioMatricula = new JMenuItem("Matrículas");
        itemAjudaSobre = new JMenuItem("Info");

        menuSistema.add(itemSistemaUsuarios);
        menuSistema.add(itemSistemaSair);
        menuCadastro.add(itemCadastroAlunos);
        menuCadastro.add(itemCadastroModalidades);
        menuCadastro.add(itemCadastroPlanos);
        menuProcessos.add(itemProcessoFaturamento);
        menuProcessos.add(itemProcessoMatricula);
        menuRelatorios.add(itemRelatorioMatricula);
        menuRelatorios.add(itemRelatorioFatura);
        menuAjuda.add(itemAjudaSobre);

        itemProcessoFaturamento.add(itemProcessoFaturamentoGerar);
        itemProcessoFaturamento.add(itemProcessoFaturamentoPagar);
        itemProcessoFaturamento.add(itemProcessoFaturamentoConsultar);
        itemProcessoMatricula.add(itemProcessoMatriculaAluno);
        itemRelatorioFatura.add(itemRelatorioFaturaAberto);
        itemRelatorioFatura.add(itemRelatorioFaturaPago);

        menuBar.add(menuSistema);
        menuBar.add(menuCadastro);
        menuBar.add(menuProcessos);
        menuBar.add(menuRelatorios);
        menuBar.add(menuAjuda);
    }
    
    private void addListeners(){
        itemSistemaSair.setAction(new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        itemSistemaUsuarios.setAction(new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(JanelaVerificar(UsuariosFrame.class.getName())){
                    JanelaFocar(windowUsuario);
                }
                else{
                    windowUsuario = new UsuariosFrame("Usuário", conn);
                }
                desktop.add(windowUsuario);
            }
        });

        itemCadastroAlunos.setAction(new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        itemCadastroModalidades.setAction(new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        itemCadastroPlanos.setAction(new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                new PlanosFrame("Planos", conn);
            }
        });

        itemProcessoFaturamentoGerar.setAction(new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        itemProcessoFaturamentoPagar.setAction(new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        itemProcessoFaturamentoConsultar.setAction(new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        itemProcessoMatriculaAluno.setAction(new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        itemRelatorioFaturaAberto.setAction(new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        itemRelatorioFaturaPago.setAction(new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        itemRelatorioMatricula.setAction(new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        itemAjudaSobre.setAction(new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
    }

    public Container CreateContentPane() {
        JPanel contentPane = new JPanel(new BorderLayout());

        desktop = new JDesktopPane() {
            Image img = (new ImageIcon("").getImage());

            public void paintComponent(Graphics g) {
                g.drawImage(img, 0, 0, this);
            }
        };

        contentPane.setOpaque(true);
        contentPane.add(desktop);

        return contentPane;
    }

    private void setAccessibleMenus(String username, Connection conn){
        menuRelatorios.setEnabled(false);
        if("admin".compareTo(username)==0){
            return;
        }

        itemSistemaUsuarios.setEnabled(false);

        Usuario usuario = new Usuario(username);
        try{
            UsuariosDAO dao = new UsuariosDAO(conn);
            usuario = (Usuario)dao.select(usuario);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
        if("Cadastral".compareTo(usuario.getPerfil())==0){
            menuProcessos.setEnabled(false);
        }
        else if("Financeiro".compareTo(usuario.getPerfil())==0){
            menuCadastro.setEnabled(false);
            itemProcessoMatricula.setEnabled(false);
        }
        else if("Matricular".compareTo(usuario.getPerfil())==0){
            menuCadastro.setEnabled(false);
            itemProcessoFaturamento.setEnabled(false);
        }
    }

    private boolean JanelaVerificar(String nome){
		JInternalFrame[] janelas = desktop.getAllFrames();
		
		for (JInternalFrame frame : janelas){
			if (frame.getName().equalsIgnoreCase(nome)){
				return true;
			}
		}
		
		return false;
	}
	
	protected void JanelaFocar(JInternalFrame janela)
	{
		try
	    {
			janela.setSelected(true);
		}
		catch (PropertyVetoException e) {
			e.printStackTrace();
		}
	}
}
