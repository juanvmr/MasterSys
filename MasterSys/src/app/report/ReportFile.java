package app.report;

import java.io.*;

public class ReportFile {

    /* attributes: */
    private String path;
    private String filename;
    private File file;
    private BufferedReader r_buffer;

    /* constructor: */
    public ReportFile(String path, String filename) {
        this.path = path;
        this.filename = filename;
    }

    public void write(String msg) throws FileNotFoundException, IOException {
        BufferedWriter w_buffer = null;
        try {
            // instance file by file path
            file = new File(path);

            // checks if file exists and directory exists
            if (file.exists() || file.mkdirs()) {
                FileOutputStream fos = new FileOutputStream(path + this.filename);
                OutputStreamWriter osw = new OutputStreamWriter(fos);
                w_buffer = new BufferedWriter(osw);
                w_buffer.write(msg);
            }
            // if directory not found, run code below
            else {
                throw new FileNotFoundException("File Not Found.");
            }
        } finally {
            // always close buffer after using it.
            if (w_buffer != null) {
                w_buffer.flush();
                w_buffer.close();
            }
        }
    }

    public String read() throws FileNotFoundException, IOException {
        // current line
        String line = null;

        // instance file by file path
        file = new File(path);

        // checks if file exists and directory exists
        if (file.exists() || file.mkdirs()) {

            if (r_buffer != null) {
                line = r_buffer.readLine();
            } else {
                FileInputStream fis = new FileInputStream(this.path + this.filename);
                InputStreamReader isr = new InputStreamReader(fis);
                r_buffer = new BufferedReader(isr);
                line = r_buffer.readLine();
            }
        }

        return line;
    }

    public void finalize() throws Throwable {
        if (r_buffer != null) {
            r_buffer.close();
        }
    }
}
