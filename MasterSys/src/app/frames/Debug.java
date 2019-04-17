package app.frames;

import database.ConnectionFactory;

import javax.swing.*;
import java.sql.Connection;

public class Debug {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Connection connection = ConnectionFactory.getConnection("master", "admin", "admin");
                CadastrarModalidadeGraduacaoFrame frame = new CadastrarModalidadeGraduacaoFrame(connection);
            }
        });
    }

}
