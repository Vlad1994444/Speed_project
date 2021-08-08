import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyPanel extends JPanel {

    static Image road = new ImageIcon("src\\road.png").getImage();
    JPanel panel;

    private static final int FIRST_ROAD = 200;
    private static final int SECOND_ROAD = 340;
    private static final int THIRD_ROAD = 480;
    Car car1, car2, car3;
    static Car[] cars;
    static Dimension size = new Dimension(road.getWidth(null), road.getHeight(null));
    Timer timer;

    MyPanel() {

        panel = this;

        //this.setPreferredSize(size);

        car1 = new Car("src\\car1.png", 0, FIRST_ROAD, 800, "Винтик");
        car2 = new Car("src\\car2.png", 0, SECOND_ROAD, 800, "Болтик");
        car3 = new Car("src\\car3.png", 0, THIRD_ROAD, 800, "Шпунтик");
        cars = new Car[]{car1, car2, car3};

        this.setLayout(null);

        MyFrame.start.addActionListener((e) -> {
            for (Car car : cars) {
                createParticipant(car);
            }
        });
    }

    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(road, 0, 0, null);
        g2D.drawImage(car1.image, car1.xPosition, car1.yPosition, null);
        g2D.drawImage(car2.image, car2.xPosition, car2.yPosition, null);
        g2D.drawImage(car3.image, car3.xPosition, car3.yPosition, null);
    }

    public void createParticipant(Car car) {
        new Thread(() -> {
            timer = new Timer(10, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
//                    if(MyFrame.stop){
//                        Thread.currentThread().stop();
//                    }
                    if (car.yPosition <= 100) {
                        car.wins += 1;
                        System.out.println(car.wins);
                        for (Car anyCar : cars) {
                            anyCar.yPosition = road.getHeight(null) - (anyCar.getImage().getHeight(null) / 2);
                            anyCar.speedCar = (int) (Math.random() * 10 + 1);
                        }
                    }
                    car.yPosition = car.yPosition - car.speedCar;
                    repaint(car.xPosition, 0, car.getImage().getWidth(null), road.getHeight(null));
                }
            });
            timer.start();
        }).start();
    }
}
