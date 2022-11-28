abstract public class Vehicles {
    private int speed;
    private int[] position = new int[2];
    private String type;
    abstract void move();

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int[] getPosition() {
        return position;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    Vehicles (int positionX, int positionY, int speed, String type)
    {
        position[0] = positionX;
        position[1] = positionY;
        this.speed = speed;
        this.type = type;
    }
}