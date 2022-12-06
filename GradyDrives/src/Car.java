import javax.swing.*;
public class Car extends Vehicles{
    private ImageIcon carIcon;
    Car(int xPos, int yPos, int speedlimit, String type, ImageIcon carIcon) {
        super(type);
        this.carIcon=carIcon;
        this.xPos = xPos;
        this.yPos = yPos;
        this.speedlimit = speedlimit;
    }
    public ImageIcon getCarIcon() {
        return carIcon;
    }
    @Override
    void move(char command) {
        switch (command) {
            case 'l' -> {
                xPos -= 100;
                if (xPos < 100) xPos = 100;
            }
            case 'r' -> {
                xPos += 100;

                if (xPos > 500) xPos = 500;
            }
        }
    }
}
