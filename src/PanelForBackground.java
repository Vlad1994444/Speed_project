import javax.swing.*;

public class PanelForBackground extends JPanel {

    PanelForBackground panelForBackground;
    ImageIcon backStage = new ImageIcon(getClass().getResource("/road.png"));

    PanelForBackground(){

        panelForBackground=this;
        this.setLayout(null);
        this.setBounds(0, 0, backStage.getIconWidth(), backStage.getIconHeight());

        JLabel label = new JLabel();
        label.setIcon(backStage);
        label.setBounds(0, 0, backStage.getIconWidth(), backStage.getIconHeight());

        this.add(label);
    }
}
