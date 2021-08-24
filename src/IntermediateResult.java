import javax.swing.*;
import java.awt.*;

public class IntermediateResult extends JFrame {

    public IntermediateResult(Car[] cars) {
        StringBuilder result = new StringBuilder();
        result.delete(0, result.length());// clean previously saved information
        for (int i = 0; i < cars.length; i++) {
            result.append("Имя машины - " + cars[i].name + ", количество побед - " + cars[i].wins + "\n");
        }
        String[] str = result.toString().split("\n");
//        System.out.println(result);
//        creating window to display result
        ImageIcon icon = new ImageIcon(getClass().getResource("/racer.png"));
        Dimension iconSize = new Dimension(icon.getIconWidth(), icon.getIconHeight());

//        new JFrame("Промежуточный результат");
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setPreferredSize(iconSize);
        this.setResizable(false);
        this.setLocation(50, 50);
        //this.setClosable(true);

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

        this.add(container);

        this.pack();
        //this.setLocationRelativeTo(null);
        this.setVisible(true);
//            }
//        });
    }
}
