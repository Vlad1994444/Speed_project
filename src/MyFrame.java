import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame implements ActionListener{

    MyPanel panel;
    MyFrame frame;
    static boolean stop = false;

    static MyButton start = new MyButton("Начинаем \nзаезд", 20, 50, 130, 100);
    static MyButton intermediateFinish = new MyButton("Промежуточный \nрезультат", 20, 200, 130, 100);
    static MyButton finish = new MyButton("Конечный \nрезультат", 20, 350, 130, 100);
    JFrame frameWithIntermediateInformation;

    MyFrame() {

        frame = this;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setPreferredSize(MyPanel.size);
        this.setResizable(false);

        panel = new MyPanel();
        panel.setBounds(0, 0, MyPanel.size.width, MyPanel.size.height);

        intermediateFinish.addActionListener((e) -> {
            if (frameWithIntermediateInformation != null) {
                frameWithIntermediateInformation.dispose();
            }
            intermediateResult();
        });


        finish.setEnabled(false);
        finish.addActionListener(this);


        this.add(start);
        this.add(intermediateFinish);
        this.add(finish);
        this.add(panel);

        this.pack();//"pack->setLoc->setVis" make window be in middle
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    public void intermediateResult() {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                StringBuilder result = new StringBuilder();
                result.delete(0, result.length());// clean previously saved information
                for (int i = 0; i < MyPanel.cars.length; i++) {
                    result.append("Имя машины - " + MyPanel.cars[i].name + ", количество побед - " + MyPanel.cars[i].wins + "\n");
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==finish){
            stop = true;
            this.remove(panel);
            panel = new MyPanel();
            this.add(panel);
            SwingUtilities.updateComponentTreeUI(this);
        }
    }
}

