package database.dao.testing;

import database.ConnectionFactory;
import database.dao.LocalDAO;
import database.models.Local;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class LocalDAOTest {

    /* attributes: */
    private static LocalDAO local_dao;

    /* methods: */
    private static void printList(List<Object> list, int limit) {
        int cnt = 0;
        for (int i = 0; i < list.size(); i++) {
            if (cnt++ > limit) {
                System.out.println();
                cnt = 0;
            }
            String space = (i < list.size() - 1) ? ", " : "\n";
            System.out.printf(list.get(i) + space);
        }
    }

    private static void runSelectAll(boolean enable) throws SQLException {
        if (!enable) return;

        List<Object> list = local_dao.SelectAll();

        printList(list, 5);

        System.out.println("count: " + list.size());
    }

    /*
     *  LocalDAO.Select() não serve para muita coisa.
     */
    private static void runSelect(boolean enable) throws SQLException {
        if (!enable) return;

        Local x = new Local("CRICIUMA", "SC", "Brasil");

        Local y = (Local) local_dao.Select(x);

        System.out.println(y.toString());
    }

    private static void runSelectPais(boolean enable) throws SQLException {
        if (!enable) return;

        List<Object> list = local_dao.SelectPais();

        printList(list, 10);

        System.out.println("count: " + list.size());
    }

    private static void runSelectEstado(boolean enable) throws SQLException {
        if (!enable) return;

        List<Object> list = local_dao.SelectEstado("Brasil");

        printList(list, 30);

        System.out.println("count: " + list.size());
    }

    private static void runInsert(boolean enable) throws SQLException {
        if (!enable) return;

        Local t = new Local("Cidade", "AB", "Pais");
        local_dao.Insert(t);
    }

    private static void runUpdate(boolean enable) throws SQLException {
        if (!enable) return;

        Local t = new Local("Cidade", "AB", "Pais");
        local_dao.Update(t);
    }

    private static void runDelete(boolean enable) throws SQLException {
        if (!enable) return;

        Local t = new Local("Cidade", "AB", "Pais");
        local_dao.Delete(t);
    }

    public static void main(String[] args) {

        Connection connection = ConnectionFactory.getConnection("master", "admin", "admin");
        try {
            connection.setAutoCommit(false);

            local_dao = new LocalDAO(connection);

            runSelect(false);
            runSelectAll(true);
            runSelectPais(false);
            runSelectEstado(true);
            runInsert(false);
            runUpdate(false);
            runDelete(false);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
