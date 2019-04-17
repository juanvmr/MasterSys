package app.test;

import app.frames.UsuariosFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;
import java.sql.Connection;


public class Menus extends JMenuBar {

    /* elements: */
    private Connection connection;
    private JMenu menuSistema, menuCadastro, menuProcessos, menuRelatorios, menuAjuda;
    private JMenuItem itemSistemaSair, itemSistemaUsuarios;
    private JMenuItem itemCadastroAlunos, itemCadastroModalidades, itemCadastroPlanos;
    private JMenu itemProcessoFaturamento, itemProcessoMatricula, itemRelatorioFatura;
    private JMenuItem itemProcessoFaturamentoGerar, itemProcessoFaturamentoConsultar, itemProcessoFaturamentoPagar, itemProcessoMatriculaAluno;
    private JMenuItem itemRelatorioMatricula, itemRelatorioFaturaAberto, itemRelatorioFaturaPago;
    private JMenuItem itemAjudaSobre;
    private JDesktopPane desktop;
    private JInternalFrame usuarioFrame, windowPlanos, windowCadastroAlunos, windowCadastroModalidades;
    private JInternalFrame windowCadastroPlanos, windowMatriculaAluno, windowFaturamentoGerar, windowFaturamentoConsultar;
    private JInternalFrame windowFaturamentoPagar;

    /* constructor: */
    public Menus(Connection connection, String username) {
        super();
        this.initComponents();

        this.connection = connection;
    }

    private void initComponents(){

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

        itemProcessoFaturamento = new JMenu("Faturamento");
            itemProcessoFaturamentoConsultar = new JMenuItem("Consultar");
            itemProcessoFaturamentoGerar = new JMenuItem("Gerar");
            itemProcessoFaturamentoPagar = new JMenuItem("Pagar");
        itemProcessoMatricula = new JMenu("Matrícula");
            itemProcessoMatriculaAluno = new JMenuItem("Aluno");

        itemRelatorioFatura = new JMenu("Fatura");
            itemRelatorioFaturaAberto = new JMenuItem("Em aberto");
            itemRelatorioFaturaPago = new JMenuItem("Pagas");
            itemRelatorioMatricula = new JMenuItem("Matrículas");
        itemAjudaSobre = new JMenuItem("Sobre");

        // menus
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

        // submenus
        itemProcessoFaturamento.add(itemProcessoFaturamentoGerar);
        itemProcessoFaturamento.add(itemProcessoFaturamentoPagar);
        itemProcessoFaturamento.add(itemProcessoFaturamentoConsultar);
        itemProcessoMatricula.add(itemProcessoMatriculaAluno);
        itemRelatorioFatura.add(itemRelatorioFaturaAberto);
        itemRelatorioFatura.add(itemRelatorioFaturaPago);

        // menu bar
        this.setListeners();
        this.add(menuSistema);
        this.add(menuCadastro);
        this.add(menuProcessos);
        this.add(menuRelatorios);
        this.add(menuAjuda);
    }

    private void setListeners() {

        // Sistem
        itemSistemaUsuarios.setAction(new AbstractAction(itemSistemaUsuarios.getText()) {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkFrame(UsuariosFrame.class.getName())) {
                    focusFrame(usuarioFrame);
                } else {
                    //usuarioFrame = new UsuariosFrame(connection);
                }
            }
        });

        itemSistemaSair.setAction(new AbstractAction(itemSistemaSair.getText()) {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Cadastro
        itemCadastroAlunos.setAction(new AbstractAction(itemCadastroAlunos.getText()) {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(itemCadastroAlunos.getText() + " clicked.");
            }
        });

        itemCadastroModalidades.setAction(new AbstractAction(itemCadastroModalidades.getText()) {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(itemCadastroModalidades.getText() + " clicked.");
            }
        });

        itemCadastroPlanos.setAction(new AbstractAction(itemCadastroPlanos.getText()) {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(itemCadastroPlanos.getText() + " clicked.");
            }
        });

        // Processos - Faturas
        itemProcessoFaturamentoConsultar.setAction(new AbstractAction(itemProcessoFaturamentoConsultar.getText()) {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(itemProcessoFaturamentoConsultar.getText() + " clicked.");
            }
        });

        itemProcessoFaturamentoGerar.setAction(new AbstractAction(itemProcessoFaturamentoGerar.getText()) {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(itemProcessoFaturamentoGerar.getText() + " clicked.");
            }
        });

        itemProcessoFaturamentoPagar.setAction(new AbstractAction(itemProcessoFaturamentoPagar.getText()) {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(itemProcessoFaturamentoPagar.getText() + " clicked.");
            }
        });

        // Processos - Matricula
        itemProcessoMatriculaAluno.setAction(new AbstractAction(itemProcessoMatriculaAluno.getText()) {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(itemProcessoMatriculaAluno.getText() + " clicked.");
            }
        });

        // Relatórios
        itemRelatorioMatricula.setAction(new AbstractAction(itemRelatorioMatricula.getText()) {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(itemRelatorioMatricula.getText() + " clicked.");
            }
        });

        itemRelatorioFaturaAberto.setAction(new AbstractAction(itemRelatorioFaturaAberto.getText()) {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(itemRelatorioFaturaAberto.getText() + " clicked.");
            }
        });

        itemRelatorioFaturaPago.setAction(new AbstractAction(itemRelatorioFaturaPago.getText()) {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(itemRelatorioFaturaPago.getText() + " clicked.");
            }
        });

        // Ajuda
        itemAjudaSobre.setAction(new AbstractAction(itemAjudaSobre.getText()) {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(itemAjudaSobre.getText() + " clicked.");
            }
        });
    }

    private boolean checkFrame(String name){
        JInternalFrame[] frames = desktop.getAllFrames();

        for (JInternalFrame f : frames){
            if (f.getName().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }

    // protected
    private void focusFrame(JInternalFrame frame) {
        try {
            frame.setSelected(true);
        } catch (PropertyVetoException e) {
            System.out.printf("focusFrame(): %s\n", e.getMessage());
            e.printStackTrace();
        }
    }
}
