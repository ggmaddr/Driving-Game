import javax.swing.*;
public class Car extends Vehicles{
    private ImageIcon carIcon;
    Car(String type) {
        super(type);
    }
    public void setCarIcon(ImageIcon carIcon) {
        this.carIcon = carIcon;
    }
    public ImageIcon getCarIcon() {
        return carIcon;
    }
    @Override
    void move(char command) {
        switch (command)
        {
            case 'l':
            {
                setxPos(getxPos() - 100);

                if (getxPos() < 100)
                {
                    setxPos(100);
                }
            }
            case 'r':
            {
                setxPos(getxPos() + 100);

                if (getxPos() > 500)
                {
                    setxPos(500);
                }
            }

        }
    }
}
