package app.frames;

import javax.swing.*;
import java.awt.*;

public abstract class BasicInternalFrame extends JInternalFrame {

    /* attributes: */
    public static int inset = 5;
    private static boolean isResizable = false;
    private static boolean isClosable = true;
    private static boolean isMaximizable = false;
    private static boolean isIconifiable = false;

    /* constructors: */
    public BasicInternalFrame(String title) {
        super(title, isResizable, isClosable, isMaximizable, isIconifiable);
    }

    /* methods: */
    public abstract void initComponents();

    public abstract void initComponents(Container container);

    public abstract void setupData();

    public abstract Object getData();

    public abstract void resetData();

    public abstract void updateData(Object obj);

    public void setDefaultSize(double percentage) {

        Dimension screenResolution = Toolkit.getDefaultToolkit().getScreenSize();

        int width = (int) (percentage * screenResolution.getWidth());
        int height = (int) (percentage * screenResolution.getHeight());
        int x = (int) (screenResolution.getWidth() - width) / 2;
        int y = (int) (screenResolution.getHeight() - height) / 2;

        this.setBounds(x, y, width, height);
    }

    public void setDefaultSize() {

        Dimension screenResolution = Toolkit.getDefaultToolkit().getScreenSize();

        int width = this.getWidth();
        int height = this.getHeight();
        int x = (int) (screenResolution.getWidth() - width) / 2;
        int y = (int) (screenResolution.getHeight() - height) / 2;

        this.setBounds(x, y, width, height);
    }
}
