package app.frames;

import database.ConnectionFactory;
import database.models.Usuario;

import javax.swing.*;
import java.sql.Connection;

public class Debug {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                Usuario user = new Usuario();
                user.setUsername("admin");
                user.setPassword("admin");

                Connection connection;
                connection = ConnectionFactory.getConnection("master", "admin", "admin");
                // connection = ConnectionFactory.getDebugConnection(user.getUsername(), user.getPassword());
                MenuFrame frame = new MenuFrame(connection, user);
            }
        });
    }

}
