import java.awt.Dimension;

import javax.swing.JFrame;

public class MyJFrame extends JFrame{

    String name;

    public MyJFrame(String name) {
        super();
        this.name = name;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(800,600));
        this.setTitle(name);
    }
}
