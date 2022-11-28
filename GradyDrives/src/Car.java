import javax.swing.*;

public class Car extends Vehicles {
    private ImageIcon carIcon;

    Car(int positionX, int positionY, int speed, String type) {
        super(positionX, positionY, speed, type);
    }

    public void setCarIcon(ImageIcon carIcon) {
        this.carIcon = carIcon;
    }

    public ImageIcon getCarIcon() {
        return carIcon;
    }

    @Override
    void move() {

    }
}
