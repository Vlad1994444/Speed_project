import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MyFrame extends JFrame{

    MyPanel panel;
    static MyButton finish = new MyButton("Заканчиваем \nзаезд", 20,50,130,100);
    static MyButton start = new MyButton("Начинаем \nзаезд", 20,200,130,100);

    MyFrame(){

        panel = new MyPanel();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setPreferredSize(MyPanel.size);

        panel.setBounds(0,0,MyPanel.size.width,MyPanel.size.height);

        finish.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList names = new ArrayList();
                ArrayList wins = new ArrayList();
                for(Car car:MyPanel.cars){
                    names.add(car.name);
                    wins.add(car.wins);
                }
                JOptionPane.showMessageDialog(null,
                        names.get(0)+" отъедил " + wins.get(0) +" участков, а "+
                                names.get(1)+" отъедил " + wins.get(1) +" участков",
                        "title", JOptionPane.PLAIN_MESSAGE);
            }
        });


       //start = new MyButton("Начинаем \nзаезд", 20,200,130,100);


        this.add(start);
        this.add(finish);
        this.add(panel);

        this.setVisible(true);
        this.pack();
    }

}
