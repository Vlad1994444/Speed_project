import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;

public class MyFrame extends JFrame{

    MyPanel panel;
    MyFrame frame;

    MyButton restart = new MyButton("Начать \nзаново", 20, 350, 130, 100);

    Image road = new ImageIcon("src\\road.png").getImage();
    Dimension size = new Dimension(road.getWidth(null), road.getHeight(null));

    MyFrame() {

        frame = this;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setPreferredSize(size);
        this.setResizable(false);

        panel = new MyPanel();
        panel.setBounds(0, 0, size.width, size.height);

        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                remove(panel);
                dispose();//close the old frame of this instance
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        MyFrame mainFrame = new MyFrame();//create a new instance
                    }
                });
            }
        });

        this.add(panel);
        this.add(restart);

        this.pack();//"pack->setLoc->setVis" make window be in middle
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}

