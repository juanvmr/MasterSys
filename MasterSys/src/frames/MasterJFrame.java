package frames;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;

public class MasterJFrame extends JFrame {

    /* elements: */
    private JMenuBar menuBar;
    private JMenu menuSistema, menuCadastro, menuProcessos, menuRelatorios, menuAjuda;
    private JMenuItem itemSistemaSair, itemSistemaUsuarios, itemCadastroAlunos, itemCadastroModalidades, itemCadastroPlanos;
    private JMenuItem itemProcessoMatricula, itemProcessoMatriculaAluno, itemProcessoFaturamento;
    private JMenuItem itemProcessoFaturamentoGerar, itemProcessoFaturamentoConsultar, itemProcessoFaturamentoPagar;
    private JMenuItem itemRelatorioMatricula, itemRelatorioFatura, itemRelatorioFaturaAberto, itemRelatorioFaturaPago;
    private JMenuItem itemAjudaSobre;
    private JDesktopPane desktop;

    /* constructor: */
    public MasterJFrame(String title) {
        super(title);
        this.setSize(800, 600);

        createMenu();
        addListeners();

        this.setJMenuBar(menuBar);
        this.setContentPane(CreateContentPane());
        this.setVisible(true);
    }

    private void createMenu(){
        menuBar = new JMenuBar();

        menuSistema = new JMenu("Sistema");
        menuCadastro = new JMenu("Cadastro");

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

        itemProcessoFaturamento.add(itemProcessoFaturamentoGerar);
        itemProcessoFaturamento.add(itemProcessoFaturamentoPagar);
        itemProcessoFaturamento.add(itemProcessoFaturamentoConsultar);
        itemProcessoMatricula.add(itemProcessoMatriculaAluno);
        itemRelatorioFatura.add(itemRelatorioFaturaAberto);
        itemRelatorioFatura.add(itemRelatorioFaturaPago);

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

        contentPane.setOpaque(true);;
        contentPane.add(desktop);

        return contentPane;
    }

}
