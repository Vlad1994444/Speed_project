import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame {

    PanelForBackground panelForBackground;
    PanelForMovement panelForMovement;
    ImageIcon backImage = new ImageIcon(getClass().getResource("/road.png"));
    Dimension size = new Dimension(backImage.getIconWidth(), backImage.getIconHeight());

    IntermediateResult intermediateResult;

    Buttons start;
    Buttons intermediate;
    Buttons restart;
    Buttons result;

    static boolean stopThreads = false;

    MyFrame() {

        panelForBackground = new PanelForBackground();

        panelForMovement = new PanelForMovement();

        start = new Buttons("Start", 10, 0, 150, 50);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopThreads = false;
                for (Car car : panelForMovement.cars) {
                    panelForMovement.createParticipant(car);
                }
                start.setEnabled(false);
                intermediate.setEnabled(true);
                result.setEnabled(true);
                restart.setEnabled(true);
            }
        });

        intermediate = new Buttons("Промежуточный \nрезультат", 180, 0, 150, 50);
        intermediate.setEnabled(false);
        intermediate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (intermediateResult != null) {
                    intermediateResult.dispose();
                }
                new IntermediateResult(panelForMovement.cars);
            }
        });

        result = new Buttons("Результат", 500, 0, 150, 50);
        result.setEnabled(false);
        result.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FinalResult(panelForMovement.cars);
                stopMyFrame();
            }
        });


        restart = new Buttons("Restart", 340, 10, 150, 50);
        restart.setEnabled(false);
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopMyFrame();
                startNewMyFrame();
            }
        });

        this.setPreferredSize(size);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, backImage.getIconWidth(), backImage.getIconHeight());
        layeredPane.add(panelForBackground, Integer.valueOf(0));
        layeredPane.add(panelForMovement, Integer.valueOf(1));
        layeredPane.add(start, Integer.valueOf(2));
        layeredPane.add(intermediate, Integer.valueOf(2));
        layeredPane.add(result, Integer.valueOf(2));
        layeredPane.add(restart, Integer.valueOf(2));


        this.add(layeredPane);
        this.pack();
        //this.setLocationRelativeTo(null);
        this.setVisible(true);
    }


    public void stopMyFrame(){
        stopThreads = true;
        if(intermediateResult!=null){// close intermediate window if it is opened
            intermediateResult.dispose();
        }
        //this.remove(panelForBackground);
        this.dispose();//close the old frame of this instance
    }

    public void startNewMyFrame(){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
               new MyFrame();//create a new instance
            }
        });
    }

}
