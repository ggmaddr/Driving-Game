import javax.swing.*;
public class Gift extends Obstacles{
    private int scoreAdder;

    Gift(Car interactCar, DrivingGame interactGame, int scoreAdder, ImageIcon icon) {
        super(interactCar, interactGame, icon);
        this.scoreAdder = scoreAdder;
        this.coolness = scoreAdder/10;
    }
    @Override
    public void function()
    {
        if (obsX == interactCar.getxPos() && getVerticleDistance() < interactCar.getCarIcon().getIconHeight())
        {
            interactGame.setScore(interactGame.getScore()+scoreAdder);
            obsX = interactGame.getScreenSize() +100;
            obsY = interactGame.getScreenSize()+100;
        }
    }
}
