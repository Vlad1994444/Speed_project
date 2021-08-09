import javax.swing.*;
import java.awt.*;

class Buttons extends JButton {

    Buttons(String name, int xPosition, int yPosition, int xWidth, int yHeight) {
        super("<html>" + "<center>" + name.replaceAll("\n", "<br>") + "</center>" + "</html>");//adding multiline string and implementing to middle
        this.setBounds(xPosition, yPosition, xWidth, yHeight);
        this.setHorizontalTextPosition(JButton.CENTER);
        this.setBackground(Color.CYAN);
        this.setFocusable(false);
    }

}
