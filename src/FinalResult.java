import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinalResult extends JFrame {

    Buttons beginAgain;
    ImageIcon imageIcon = new ImageIcon(getClass().getResource("/result.png"));

    FinalResult(Car[]cars) {
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setPreferredSize(new Dimension(imageIcon.getIconWidth(), imageIcon.getIconHeight()));
        this.setResizable(false);

        JPanel panel = new JPanel();
        panel.setBounds(0,0,imageIcon.getIconWidth(), imageIcon.getIconHeight());
        panel.setLayout(null);

        beginAgain = new Buttons("Begin again", 10,10,100,50);
        beginAgain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MyFrame();
                stopFinalResultFrame();
            }
        });

        JLabel picture = new JLabel();
        picture.setIcon(imageIcon);
        picture.setBounds(0,0,imageIcon.getIconWidth(), imageIcon.getIconHeight());
/*
need this LayeredPane to add pictures of cars
 */
        JLayeredPane jLayeredPaneForPanel = new JLayeredPane();
        jLayeredPaneForPanel.setBounds(0,0, imageIcon.getIconWidth(), imageIcon.getIconHeight());
        jLayeredPaneForPanel.add(picture, Integer.valueOf(0));
        jLayeredPaneForPanel.add(beginAgain, Integer.valueOf(1));

        Car[] carsInOrderFromLooserToWinner = fromFirstToThird(cars);
        for(int i =0; i<carsInOrderFromLooserToWinner.length; i++){
            switch (i){
                case 0:
                    JLabel imageOfLastCar = new JLabel(cars[i].getImage());
                    imageOfLastCar.setBounds(300, 205, 300, 200);

                    JLabel textOne = new JLabel("This car have " + cars[i].getWins() + " wins");
                    textOne.setBounds(360, 155, 200, 100);
                    textOne.setFont(new Font("Ponter s", Font.PLAIN, 20));
                    textOne.setForeground(Color.CYAN);

                    jLayeredPaneForPanel.add(imageOfLastCar, Integer.valueOf(1));
                    jLayeredPaneForPanel.add(textOne,Integer.valueOf(1));

                    break;

                case 1:
                    JLabel imageOfSecondCar = new JLabel(cars[i].getImage());
                    imageOfSecondCar.setBounds(0, 120, 300, 200);

                    JLabel textTwo = new JLabel("This car have " + cars[i].getWins() + " wins");
                    textTwo.setBounds(60, 70, 200, 100);
                    textTwo.setFont(new Font("Ponter s", Font.PLAIN, 20));
                    textTwo.setForeground(Color.CYAN);

                    jLayeredPaneForPanel.add(imageOfSecondCar, Integer.valueOf(1));
                    jLayeredPaneForPanel.add(textTwo,Integer.valueOf(1));

                    break;

                case 2:
                    JLabel imageOfFirstCar = new JLabel(cars[i].getImage());
                    imageOfFirstCar.setBounds(150, 25, 300, 200);

                    JLabel textThree = new JLabel("This car have " + cars[i].getWins() + " wins");
                    textThree.setBounds(210, -25, 200, 100);
                    textThree.setFont(new Font("Ponter s", Font.PLAIN, 20));
                    textThree.setForeground(Color.CYAN);

                    jLayeredPaneForPanel.add(imageOfFirstCar, Integer.valueOf(1));
                    jLayeredPaneForPanel.add(textThree,Integer.valueOf(1));

                    break;
            }

//            JLabel imagesOfCars = new JLabel(cars[i].getImage());
//            imagesOfCars.setBounds(i*150,10+i*80,300,200);
//
//            JLabel text = new JLabel("This car have " + cars[i].getWins() + " wins");
//            text.setBounds(70+i*150,-35+i*80,200,100);
//            text.setFont(new Font("Ponter s", Font.PLAIN, 20));
//            text.setForeground(Color.CYAN);
//
//            jLayeredPaneForPanel.add(imagesOfCars, Integer.valueOf(1));
//            jLayeredPaneForPanel.add(text, Integer.valueOf(1));

        }

        panel.add(jLayeredPaneForPanel);
/*
need this LayeredPane to add panel to Frame
 */
        JLayeredPane jLayeredPane = new JLayeredPane();
        jLayeredPane.setBounds(0,0, imageIcon.getIconWidth(), imageIcon.getIconHeight());
        jLayeredPane.add(panel, Integer.valueOf(0));

        this.add(jLayeredPane);
        this.pack();
        this.setVisible(true);

    }

    public Car[] fromFirstToThird(Car[]cars){
        for(int i=0; i<cars.length;i++ ){
            for(int j = cars.length-1; j>i;j--){
                if(cars[j-1].getWins()>cars[j].getWins()){
                    Car temp = cars[j];
                    cars[j]=cars[j-1];
                    cars[j-1] = temp;
                }
            }
        }
        return cars;
    }

    public void stopFinalResultFrame(){
        this.dispose();//close the old frame of this instance
    }
}





