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
            break;
            case 'r':
            {
                setxPos(getxPos() + 100);

                if (getxPos() > 500)
                {
                    setxPos(500);
                }
            }
            break;

        }
    }
}
