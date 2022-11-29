import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class DrivingGame extends JFrame implements KeyListener, ActionListener
{
    public int getScreenSize() {
        return screenSize;
    }

    final private int screenSize = 700;
    private Car car = new Car("Sedan");
    private boolean gameOver, painting;
    private int score;
    int roadmove = 0;

    int delay = 100;
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public int[] getObPosXArray() {
        return obPosXArray;
    }

    public int[] getObPosYArray() {
        return obPosYArray;
    }

    private int obPosXArray[]={100,200,300,400,500};
    private int obPosYArray[]= {-240,-480,-720,-960,-1200};

    private int cxpos1=0,cxpos2=2,cxpos3=4;
    private Random random = new Random();
    private int cypos1=random.nextInt(5),cypos2=random.nextInt(5),cypos3=random.nextInt(5);

    int ob1Y=obPosYArray[cypos1],ob2Y=obPosYArray[cypos2],ob3Y=obPosYArray[cypos3];

    private Obstacles ob1 = new Obstacles(car, this);
    private Obstacles ob2 = new Obstacles(car, this);
    private Obstacles ob3 = new Obstacles(car, this);


    public DrivingGame() throws HeadlessException {
        setBounds(300,10,700,700);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        addKeyListener(this);
        setFocusable(true);
        setResizable(false);
    }

    @Override
    public void paint(Graphics g) {
        Image bground = new ImageIcon("backgroundimg.jpeg").getImage();
        g.drawImage(bground, 0, 0, null);
        g.setColor(Color.gray);
        g.fillRect(90,0,10,screenSize);
        g.fillRect(600, 0, 10, screenSize);
        g.setColor(Color.black);
        g.fillRect(100, 0, 500, screenSize);

        if(roadmove==0)
        {
            for(int i=0; i<=700; i+=100)
            {
                g.setColor(Color.white);
                g.fillRect(350, i,10, 70);

            }
            roadmove=1;
        }
        else if(roadmove==1)
        {
            for(int i=50; i<=700; i+=100)
            {
                g.setColor(Color.white);
                g.fillRect(350, i,10, 70);
            }
            roadmove=0;
        }
        car.setCarIcon(new ImageIcon("gamecar1.png"));
//        car.setxPos(300);
        car.getCarIcon().paintIcon(this, g, car.getxPos(), car.getyPos());

//        ypos-=40;
//        if(ypos<500)
//        {
//            ypos=500;
//        }

        ob1.setObsIcon(new ImageIcon("gamecar2.png"));
        ob2.setObsIcon(new ImageIcon("gamecar3.png"));
        ob3.setObsIcon(new ImageIcon("gamecar4.png"));

        LinkedList<Obstacles> obList = new LinkedList<Obstacles>();

        obList.add(ob1);
        obList.add(ob2);
        obList.add(ob3);

        ListIterator<Obstacles> list_Iter = obList.listIterator();

        while(list_Iter.hasNext())
        {
            Obstacles currentOb = list_Iter.next();
            currentOb.getObsIcon().paintIcon(this,g, currentOb.getObsX(), currentOb.getObsY());
            currentOb.setObsY(currentOb.getObsY() + 50);
            currentOb.randomPositionGenerator();
        }
        drawScore(g);
        //delay
        try
        {
            TimeUnit.MILLISECONDS.sleep(delay);
        }
        catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void drawScore(Graphics g)
    {
        g.setColor(Color.gray);
        g.fillRect(120,35,220,50);
        g.setColor(Color.DARK_GRAY);
        g.fillRect(125,40, 210, 40);
        g.setColor(Color.gray);
        g.fillRect(385,35,180,50);
        g.setColor(Color.DARK_GRAY);
        g.fillRect(390,40, 170, 40);
        g.setColor(Color.white);
        g.setFont(new Font("Arial",Font.BOLD,30));
        g.drawString("Score : "+score, 130, 67);
        g.drawString(car.getSpeed()+" Km/h", 400, 67);
        score++;
        car.setSpeed(car.getSpeed()+1);
        if(car.getSpeed()>140)
        {
            car.setSpeed(240-delay);
        }
    }

    public void drawGameOver(Graphics g)
    {
        if(gameOver)
        {
            g.setColor(Color.gray);
            g.fillRect(120, 210, 460, 200);
            g.setColor(Color.DARK_GRAY);
            g.fillRect(130, 220, 440, 180);
            g.setFont(new Font("Serif",Font.BOLD,50));
            g.setColor(Color.yellow);
            g.drawString("Game Over !",210, 270);
            g.setColor(Color.white);
            g.setFont(new Font("Arial",Font.BOLD,30));
            g.drawString("Press Enter to Restart", 190, 340);
            if(!painting)
            {
                repaint();
                painting=true;
            }
        }
        else
        {
            repaint();
        }
    }

    public static void main(String[] args) {
        DrivingGame newGame = new DrivingGame();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_LEFT && !gameOver)
        {
            car.move('l');
        }
        if(e.getKeyCode()==KeyEvent.VK_RIGHT&&!gameOver)
        {
            car.move('r');
        }
        if(e.getKeyCode()==KeyEvent.VK_ENTER && gameOver)
        {
            gameOver=false;
            painting =false;

            repaint();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
