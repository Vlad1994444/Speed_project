import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

    MyPanel panel;

    MyFrame(){

        panel = new MyPanel();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setPreferredSize(MyPanel.size);

        panel.setBounds(0,0,MyPanel.size.width,MyPanel.size.height);
        this.add(new MyButton("Заканчиваем \nзаезд", 20,50,130,100));
        this.add(panel);

        this.setVisible(true);
        this.pack();
    }

}
