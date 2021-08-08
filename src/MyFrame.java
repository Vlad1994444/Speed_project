import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame {

    MyPanel panel;
    static MyButton start = new MyButton("Начинаем \nзаезд", 20, 50, 130, 100);
    static MyButton intermediateFinish = new MyButton("Промежуточный \nрезультат", 20, 200, 130, 100);

    MyFrame() {

        panel = new MyPanel();
        panel.setBounds(0, 0, MyPanel.size.width, MyPanel.size.height);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setPreferredSize(MyPanel.size);

        intermediateFinish.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                intermediateResult();
                //finish.setEnabled(false);
            }
        });

        this.add(start);
        this.add(intermediateFinish);
        this.add(panel);

        this.setVisible(true);
        this.pack();
    }

    public void intermediateResult() {
        StringBuilder result = new StringBuilder();
        result.delete(0, result.length());// clean previous saved information
        String string = new String();
        for (int i = 0; i < MyPanel.cars.length; i++) {
            result.append("Имя машины - " + MyPanel.cars[i].name + ", количество побед - " + MyPanel.cars[i].wins + System.lineSeparator());
        }
        System.out.println(result);
        JOptionPane.showMessageDialog(null,
                result,
                "title", JOptionPane.PLAIN_MESSAGE);
    }

}
