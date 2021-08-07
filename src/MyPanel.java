import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyPanel extends JPanel {

    static Image road = new ImageIcon("src\\road.png").getImage();
    private static final int FIRST_ROAD = 180;
    private static final int SECOND_ROAD = 320;
    static Thread thread;
    Car car1;
    Car car2;
    static Car[] cars;
    static Dimension size = new Dimension(road.getWidth(null), road.getHeight(null));
    Timer timer;

    MyPanel() {

        //this.setPreferredSize(size);

        car1 = new Car("src\\car1.png", 0, FIRST_ROAD, 800, "Винтик");
        car2 = new Car("src\\car2.png", 0, SECOND_ROAD, 800, "Болтик");
        cars = new Car[]{car1, car2};
        this.setLayout(null);

        MyFrame.start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Car car : cars) {
                    thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            timer = new Timer(10, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (car.yPosition <= 100) {
                                        car.wins += 1;
                                        System.out.println(car.wins);
                                        for(Car car : cars){
                                            car.yPosition = road.getHeight(null) - (car.getImage().getHeight(null) / 2);
                                            car.speedCarOne = (int) (Math.random() * 10 + 1);
                                        }
                                    }
                                    car.yPosition = car.yPosition - car.speedCarOne;
                                    repaint(car.xPosition, 0, car.getImage().getWidth(null), road.getHeight(null));
                                }
                            });
                            timer.start();
                        }
                    });
                    thread.start();
                }
            }
        });

    }

    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(road, 0, 0, null);
        g2D.drawImage(car1.image, car1.xPosition, car1.yPosition, null);
        g2D.drawImage(car2.image, car2.xPosition, car2.yPosition, null);
    }
}
