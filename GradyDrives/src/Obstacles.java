import javax.swing.*;
import java.util.Random;

public class Obstacles implements ThingsOnRoad{
    private int obsX;
    private int obsY;
    private Car interactCar;
    private DrivingGame interactGame;

    private ImageIcon obsIcon;

    Obstacles(Car interactCar, DrivingGame interactGame) {
        this.interactCar = interactCar;
        this.interactGame = interactGame;
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
    int getVerticleDistance()
    {
        return Math.abs(interactCar.getyPos() - obsY);
    }

    void randomPositionGenerator()
    {
        if(obsY > interactGame.getScreenSize())
        {
            int[] posX = interactGame.getObPosXArray();
            int[] posY = interactGame.getObPosYArray();

            Random random = new Random();
            int randomIndexX, randomIndexY;
            randomIndexX = random.nextInt(posX.length);
            obsX = (posX[randomIndexX]);
            randomIndexY = random.nextInt(posY.length);
            obsX = (posX[randomIndexX]);
        }

    }
    //function is the challenge of the game
    @Override
    public void function() {
        //if car touch obstacles --> lose game;
        if (obsX == interactCar.getxPos()
                && obsY == interactCar.getyPos())
            interactGame.setGameOver(false);
    }
}
