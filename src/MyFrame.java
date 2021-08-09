import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame {

    PanelForBackground panelForBackground;
    PanelForMovement panelForMovement;
    ImageIcon backImage = new ImageIcon("src\\road.png");
    Dimension size = new Dimension(backImage.getIconWidth(), backImage.getIconHeight());

    Buttons restart;

    static boolean stopThreads = false;

    MyFrame() {

        panelForBackground = new PanelForBackground();
        panelForMovement = new PanelForMovement();

        this.setPreferredSize(size);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        restart = new Buttons("Restart", 10, 10, 150, 50);
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopThreads = true;
                if(PanelForMovement.intermediateInformation!=null){// close intermediate window if it is opened
                    PanelForMovement.intermediateInformation.dispose();
                }
                remove(panelForBackground);
                dispose();//close the old frame of this instance
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        MyFrame mainFrame = new MyFrame();//create a new instance
                    }
                });
            }
        });

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, backImage.getIconWidth(), backImage.getIconHeight());
        layeredPane.add(panelForBackground, Integer.valueOf(0));
        layeredPane.add(panelForMovement, Integer.valueOf(1));
        layeredPane.add(restart, Integer.valueOf(2));


        this.add(layeredPane);
        this.pack();
        //this.setLocationRelativeTo(null);
        this.setVisible(true);

    }



}
