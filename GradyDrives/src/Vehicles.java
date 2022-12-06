abstract public class Vehicles {
    public int getxPos() {
        return xPos;
    }
    public int getyPos() {
        return yPos;
    }
    protected int xPos, yPos, speedlimit, speed;

    private String type;
    abstract void move(char command);

    //Setters and getters
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public String getType() {
        return type;
    }

    Vehicles (String type)
    {
        this.type = type;
    }

    public int getSpeedlimit() {
        return speedlimit;
    }

}
