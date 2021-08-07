import javax.swing.*;
import java.awt.*;

public class Car{

    String pictureAdress;
    int wins;
    int xPosition;
    int yPosition;
    int speedCarOne = (int)(Math.random()*10+1);
    Image image;

    public Car(String pictureAdress, int wins, int xPosition, int yPosition) {
        this.pictureAdress = pictureAdress;
        this.wins = wins;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        image = new ImageIcon(pictureAdress).getImage();
    }

    public Image getImage() {
        return image;
    }

    public String getPictureAdress() {
        return pictureAdress;
    }

    public int getWins() {
        return wins;
    }

    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public int getSpeedCarOne() {
        return speedCarOne;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }
}
