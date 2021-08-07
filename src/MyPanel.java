import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyPanel extends JPanel {

    static Image road = new ImageIcon("src\\road.png").getImage();
    Car car = new Car("src\\car1.png", 0, 180, 800);
    Car car2  =new Car("src\\car2.png", 0, 250,800);
    static Dimension size = new Dimension(road.getWidth(null), road.getHeight(null));
    Timer timer;

    MyPanel() {

        //this.setPreferredSize(size);
        this.setLayout(null);

        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (car.yPosition <= 100) {
                    car.wins += 1;
                    System.out.println(car.wins);
                    car.yPosition = road.getHeight(null) - (car.getImage().getHeight(null) / 2);
                    car.speedCarOne = (int) (Math.random() * 10 + 1);
                }
                car.yPosition = car.yPosition - car.speedCarOne;
                repaint((int) (car.getImage().getWidth(null) * 1.5), 0, 500, road.getHeight(null));
            }
        });
        timer.start();
    }

    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(road, 0, 0, null);
        g2D.drawImage(car.image, car.xPosition, car.yPosition, null);
    }
}
