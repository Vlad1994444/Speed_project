import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyPanel extends JPanel {

    Image road = new ImageIcon("src\\road.png").getImage();
    Image carOne = new ImageIcon("src\\car1.png").getImage();
    int winsCarOne = 0;
    int speedCarOne = (int)(Math.random()*10+1);// from 5 to 15 pixels in 100 milliseconds
    Image carTwo;
    Dimension size = new Dimension(road.getWidth(null), road.getHeight(null));
    //int xVelocityFirstCar = 0;
    Timer timer;
    int xPositionFirst = carOne.getWidth(null)*2;
    int yPositionFirst = road.getHeight(null)-(carOne.getHeight(null))/2;

    MyPanel(){

        this.setPreferredSize(size);
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(yPositionFirst<=100){
                    winsCarOne+=1;
                    System.out.println(winsCarOne);
                    yPositionFirst = road.getHeight(null)-(carOne.getHeight(null))/2;
                    speedCarOne = (int)(Math.random()*10+1);// from 5 to 15 pixels in 100 milliseconds
                }
                yPositionFirst = yPositionFirst-speedCarOne;
                repaint();
            }
        });
        timer.start();

    }
    public void paint(Graphics g){
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(road, 0,0,null);
        g2D.drawImage(carOne, xPositionFirst, yPositionFirst, null);
    }

}
