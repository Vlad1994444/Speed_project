import javax.swing.*;
import java.awt.*;

public class Car{

    String pictureAddress;
    int wins;
    int xPosition;
    int yPosition;
    int speedCarOne = (int)(Math.random()*10+1);
    Image image;
    String name;

    public Car(String pictureAddress, int wins, int xPosition, int yPosition, String name) {
        this.pictureAddress = pictureAddress;
        this.wins = wins;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.name = name;
        image = new ImageIcon(pictureAddress).getImage();
    }

    public Image getImage() {
        return image;
    }

    public String getPictureAddress() {
        return pictureAddress;
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
