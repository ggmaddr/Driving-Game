abstract public class Vehicles {
    private int speed;

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    private int xPos;
    private int yPos;

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

    public void setType(String type) {
        this.type = type;
    }

    Vehicles (String type)
    {
        this.type = type;
    }
}
