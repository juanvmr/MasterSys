package app.report;

import app.frames.MenuFrame;
import database.dao.MatriculaDAO;

import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RelatorioMatriculas {

    private String path = System.getenv("user-p");
    private static DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    /* attributes: */
    private MatriculaDAO matriculaDAO = MenuFrame.matriculaDAO;
    private String filename;

    /* constructor: */
    public RelatorioMatriculas(Date fromDate, Date toDate) {
        this.filename = String.format("relatorio_matriculas_%s_%s", format.format(fromDate), format.format(toDate));
    }


}
