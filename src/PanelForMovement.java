import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelForMovement extends JPanel {

    ImageIcon backStage = new ImageIcon(getClass().getResource("/road.png"));

    static final int FIRST_ROAD = 30;
    static final int SECOND_ROAD = 160;
    static final int THIRD_ROAD = 320;

    Car car1, car2, car3;
    Car[] cars;

    PanelForMovement() {

        this.setLayout(null);
        this.setBounds(170, 10, 560, backStage.getIconHeight() - 50);

        car1 = new Car(getClass().getResource("/car1.png"),
                0, PanelForMovement.FIRST_ROAD, 800, "Винтик");
        car2 = new Car(getClass().getResource("/car2.png"),
                0, PanelForMovement.SECOND_ROAD, 800, "Болтик");
        car3 = new Car(getClass().getResource("/car3.png"),
                0, PanelForMovement.THIRD_ROAD, 800, "Шпунтик");
        cars = new Car[]{car1, car2, car3};

        this.setOpaque(false);

    }

    public void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        for (Car car : cars) {
            //g2D.drawImage(car.image, car.xPosition, car.yPosition, null);
            g2D.drawImage(car.image.getImage(), car.xPosition, car.yPosition, null);

        }
    }

    public void createParticipant(Car car) {
        new Thread(() -> {
            new Timer(10, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (MyFrame.stopThreads) {
                        //timer.stop();
                        Thread.currentThread().stop();
                    }
                    if (car.yPosition <= 100) {
                        car.wins += 1;
                        System.out.println(car.wins);
                        for (Car anyCar : cars) {
                            //anyCar.yPosition = backStage.getIconHeight() - (anyCar.getImage().getHeight(null) / 2);
                            anyCar.yPosition = backStage.getIconHeight() - (anyCar.getImage().getIconHeight() / 2);
                            anyCar.speedCar = (int) (Math.random() * 10 + 1);
                        }
                    }
                    car.yPosition = car.yPosition - car.speedCar;
                    repaint();
                }
            }).start();
        }).start();
    }
}
