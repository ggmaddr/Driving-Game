import javax.swing.*;
import java.util.Random;
public class Obstacles implements ThingsOnRoad, Comparable<Obstacles>{
    protected int obsX;
    protected int obsY;
    protected final Car interactCar;
    protected final DrivingGame interactGame;
    protected ImageIcon obsIcon;
    protected int coolness;
    protected int weight;
    Obstacles(Car interactCar, DrivingGame interactGame, int weight, ImageIcon icon)
    {
        this.interactCar = interactCar;
        this.interactGame = interactGame;
        this.weight = weight;
        obsIcon = icon;
        this.coolness = this.weight/10;
    }
    Obstacles(Car interactCar, DrivingGame interactGame, ImageIcon icon)
    {
        this.interactCar = interactCar;
        this.interactGame = interactGame;
        obsIcon = icon;
    }
    public int getObsX() {
        return obsX;
    }
    public void setObsX(int obsX) {
        this.obsX = obsX;
    }
    public int getObsY() {
        return obsY;
    }
    public void setObsY(int obsY) {
        this.obsY = obsY;
    }
    public ImageIcon getObsIcon() {
        return obsIcon;
    }
    public void setObsIcon(ImageIcon obsIcon) {
        this.obsIcon = obsIcon;
    }
    @Override
    public int getVerticleDistance()
    {
        return Math.abs(interactCar.getyPos() - obsY);
    }

    int indexInPosArray(int[] posArray, int value)
    {
        int index = -1;
        for (int i = 0; i< posArray.length;i++)
        {
            if (posArray[i] == value)
            {
                index = i;
                break;
            }
        }
        return index;
    }
    @Override
    public void randomPositionGenerator()
    {
            int[] posX = interactGame.getObPosXArray();
            int[] posY = interactGame.getObPosYArray();

            Random random = new Random();
            int randomIndexX, randomIndexY;

            randomIndexX = random.nextInt(posX.length);
            randomIndexY = random.nextInt(posY.length);

            obsX = (posX[randomIndexX]);
            obsY = (posY[randomIndexY]);
    }
    //function is the challenge of the game
    @Override
    public void function() {
        //if car touch obstacles --> lose game;
        if (obsX == interactCar.getxPos() && getVerticleDistance() < interactCar.getCarIcon().getIconHeight())
            interactGame.setGameOver(true);
    }

    @Override
    public int compareTo(Obstacles o) {
        if(this.coolness > o.coolness)
            return -1;
        return 1;
    }
}
