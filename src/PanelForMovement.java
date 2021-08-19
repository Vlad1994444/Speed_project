import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelForMovement extends JPanel {

    PanelForMovement panelForMovement;
    ImageIcon backStage = new ImageIcon("src\\road.png");

    static final int FIRST_ROAD = 30;
    static final int SECOND_ROAD = 160;
    static final int THIRD_ROAD = 320;

    Buttons start;
    Buttons intermediate;
    Buttons result;

    static JFrame intermediateInformation;

    Car car1, car2, car3;
    Car[] cars;

    PanelForMovement() {

        this.setLayout(null);
        this.setBounds(170, 10, 560, backStage.getIconHeight() - 50);

        car1 = new Car("src\\car1.png", 0, PanelForMovement.FIRST_ROAD, 800, "Винтик");
        car2 = new Car("src\\car2.png", 0, PanelForMovement.SECOND_ROAD, 800, "Болтик");
        car3 = new Car("src\\car3.png", 0, PanelForMovement.THIRD_ROAD, 800, "Шпунтик");
        cars = new Car[]{car1, car2, car3};

        start = new Buttons("Start", 10, 0, 150, 50);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyFrame.stopThreads = false;
                for (Car car : cars) {
                    createParticipant(car);
                }
            }
        });

        result = new Buttons("Результат", 350, 0,150,50);
        result.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FinalResult(cars);
            }
        });

        intermediate = new Buttons("Промежуточный \nрезультат", 180, 0, 150, 50);
        intermediate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (intermediateInformation != null) {
                    intermediateInformation.dispose();
                }
                intermediateResult();
            }
        });

        this.add(start);
        this.add(intermediate);
        this.add(result);
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

                intermediateInformation = new JFrame("Промежуточный результат");
                intermediateInformation.setLayout(null);
                intermediateInformation.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                intermediateInformation.setPreferredSize(iconSize);
                intermediateInformation.setResizable(false);

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

                intermediateInformation.add(container);

                intermediateInformation.pack();
                intermediateInformation.setLocationRelativeTo(null);
                intermediateInformation.setVisible(true);
            }
        });
    }
}
