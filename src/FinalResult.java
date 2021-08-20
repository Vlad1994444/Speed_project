import javax.swing.*;
import java.awt.*;

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

        JLabel picture = new JLabel();
        picture.setIcon(imageIcon);
        picture.setBounds(0,0,imageIcon.getIconWidth(), imageIcon.getIconHeight());

        JLayeredPane jLayeredPaneForPanel = new JLayeredPane();
        jLayeredPaneForPanel.setBounds(0,0, imageIcon.getIconWidth(), imageIcon.getIconHeight());
        jLayeredPaneForPanel.add(picture, Integer.valueOf(0));

        Car[] carsInOrderWithPlacesInRace = fromFirstToThird(cars);
        for(int i =(carsInOrderWithPlacesInRace.length-1); i>=0; i--){

            JLabel imagesOfCars = new JLabel(cars[i].getImage());
            imagesOfCars.setBounds(i*150,10+i*80,300,200);

            JLabel text = new JLabel("This car have " + cars[i].getWins() + " wins");
            text.setBounds(70+i*150,-35+i*80,200,100);
            text.setFont(new Font("Ponter s", Font.PLAIN, 20));
            text.setForeground(Color.CYAN);

            jLayeredPaneForPanel.add(imagesOfCars, Integer.valueOf(1));
            jLayeredPaneForPanel.add(text, Integer.valueOf(1));

        }

        panel.add(jLayeredPaneForPanel);
        //panel.add(picture);

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
                if(cars[j-1].getWins()<cars[j].getWins()){
                    Car temp = cars[j];
                    cars[j]=cars[j-1];
                    cars[j-1] = temp;
                }
            }
        }
        return cars;
    }
}





