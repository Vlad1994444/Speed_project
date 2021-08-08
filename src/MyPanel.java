import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyPanel extends JPanel {

    JPanel panel;

    MyButton start = new MyButton("Начинаем \nзаезд", 20, 50, 130, 100);

    MyButton intermediateFinish = new MyButton("Промежуточный \nрезультат", 20, 200, 130, 100);
    JFrame frameWithIntermediateInformation;

    boolean stop = false;

    Image road = new ImageIcon("src\\road.png").getImage();

    private static final int FIRST_ROAD = 200;
    private static final int SECOND_ROAD = 340;
    private static final int THIRD_ROAD = 480;

    Car car1, car2, car3;
    Car[] cars;
    Timer timer;

    MyPanel() {

        panel = this;

        //this.setPreferredSize(size);

        car1 = new Car("src\\car1.png", 0, FIRST_ROAD, 800, "Винтик");
        car2 = new Car("src\\car2.png", 0, SECOND_ROAD, 800, "Болтик");
        car3 = new Car("src\\car3.png", 0, THIRD_ROAD, 800, "Шпунтик");
        cars = new Car[]{car1, car2, car3};

        this.setLayout(null);

        start.addActionListener((e) -> {
            //start.setEnabled(false);
            for (Car car : cars) {
                createParticipant(car);
            }
        });

        intermediateFinish.addActionListener((e) -> {
            if (frameWithIntermediateInformation != null) {
                frameWithIntermediateInformation.dispose();
            }
            intermediateResult();
        });

        this.add(start);
        this.add(intermediateFinish);
    }


    public void intermediateResult(){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                StringBuilder result = new StringBuilder();
                result.delete(0, result.length());// clean previously saved information
                for (int i = 0; i < cars.length; i++) {
                    result.append("Имя машины - " + cars[i].name + ", количество побед - " + cars[i].wins + "\n");
                }
                String[] str = result.toString().split("\n");

                //System.out.println(result);
                //creating window to display result
                ImageIcon icon = new ImageIcon("src\\racer.png");
                Dimension iconSize = new Dimension(icon.getIconWidth(), icon.getIconHeight());

                frameWithIntermediateInformation = new JFrame("Промежуточный результат");
                frameWithIntermediateInformation.setLayout(null);
                frameWithIntermediateInformation.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frameWithIntermediateInformation.setPreferredSize(iconSize);
                frameWithIntermediateInformation.setResizable(false);

                JPanel container = new JPanel();
                container.setBounds(0, 0, iconSize.width, iconSize.height);
                container.setLayout(null);

                JLabel picture = new JLabel();
                picture.setIcon(icon);
                picture.setBounds(0, 0, iconSize.width, iconSize.height);
                picture.setHorizontalAlignment(JLabel.CENTER);

                //creation of separate labels for every car
                for (int i = 0; i < str.length; i++) {
                    JLabel text = new JLabel();
                    text.setBounds(10, 180 + i * 30, 500, 30);
                    text.setText(str[i]);
                    text.setFont(new Font("Ponter s", Font.PLAIN, 20));
                    text.setForeground(Color.CYAN);
                    container.add(text);
                }

                container.add(picture);

                frameWithIntermediateInformation.add(container);

                frameWithIntermediateInformation.pack();
                frameWithIntermediateInformation.setLocationRelativeTo(null);
                frameWithIntermediateInformation.setVisible(true);
            }
        });
    }


    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(road, 0, 0, null);
        for (Car car : cars) {
            g2D.drawImage(car.image, car.xPosition, car.yPosition, null);
        }
    }


    public void createParticipant(Car car) {
        new Thread(() -> {
            timer = new Timer(10, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (stop) {
                        timer.stop();
                        Thread.currentThread().stop();
                    }
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
