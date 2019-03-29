package database.dao.testing;

import database.ConnectionFactory;
import database.dao.AlunoDAO;
import database.models.Aluno;
import database.models.Local;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class AlunoDAOTest {

    private static AlunoDAO dao;

    private static void runInsert(boolean enable) throws SQLException {
        if (!enable) return;

        Aluno aluno = new Aluno("Pedro", new Date(1990, 6, 2), 'M', "(48) 3433-4664",
        "(47) 9707-0799", "pedro@gmail.com", "none", "Rua Mario da Cunha Carneiro", "34",
        "apto 401", "Pio Correa", new Local("CRICIUMA", "SC", "Brasil"), "88811-510");

        dao.Insert(aluno);
    }

    private static void runUpdate(boolean enable) throws SQLException {
        if (!enable) return;

        Aluno aluno = (Aluno) dao.Select(new Aluno("Pedro"));
        aluno.setCEP("99898-010");

        dao.Update(aluno);
    }

    private static void runSelect(boolean enable) throws SQLException {
        if (!enable) return;

        Aluno aluno = (Aluno) dao.Select(new Aluno("Pedro"));
        System.out.println(aluno.toString());
    }

    private static void runDelete(boolean enable) throws SQLException {
        if (!enable) return;

        dao.Delete(new Aluno(2));
    }

    private static void runSelectAll(boolean enable) throws SQLException {
        if (!enable) return;

        List<Object> list = dao.SelectAll();

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        System.out.println("count: " + list.size());
    }

    public static void main(String[] args) {

        Connection connection = ConnectionFactory.getConnection("master", "admin", "admin");
        try {
            connection.setAutoCommit(false);

            dao = new AlunoDAO(connection);

            runInsert(false);
            runSelect(false);
            runUpdate(false);
            runDelete(true);
            runSelectAll(false);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
