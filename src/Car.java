import javax.swing.*;
import java.awt.*;

public class Car {

    String pictureAddress;
    int wins;
    int xPosition;
    int yPosition;
    int speedCar = (int) (Math.random() * 10 + 1);
    ImageIcon image;
    //Image image;
    String name;

    public Car(String pictureAddress, int wins, int xPosition, int yPosition, String name) {
        this.pictureAddress = pictureAddress;
        this.wins = wins;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.name = name;
        //image = new ImageIcon(pictureAddress).getImage();
        image = new ImageIcon(pictureAddress);

    }

    public Car(int wins, String name) {
        this.wins = wins;
        this.name = name;
    }

    //public Image getImage() {
    public ImageIcon getImage() {

        return image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return speedCar;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }
}
