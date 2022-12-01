import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.sql.Array;
import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class DrivingGame extends JFrame implements KeyListener, ActionListener
{
    final private int screenSize = 700;
    String gameOvertext = "";


    private Car car = new Car(300, 500,200,"Ferrari", new ImageIcon("ferrari.png"));
    private boolean gameOver;
    private int score;
    int roadmove;

    int delay;
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


    private Obstacles ob1 = new Obstacles(car, this, 3, new ImageIcon("motorbike.png"));
    private Obstacles ob2 = new Obstacles(car, this,2, new ImageIcon("rock.png"));
    private Obstacles ob3 = new Obstacles(car, this,1, new ImageIcon("roadwork.png"));

    private LinkedList<Obstacles> obList = new LinkedList<Obstacles>();
    private ListIterator<Obstacles> list_Iter;

    public void fileReader(FileReader file) throws IOException {
        BufferedReader reader = new BufferedReader(file);
        String line;
        while ((line = reader.readLine()) != null)
        {
            if(line.startsWith("@"))
                continue;
            if (line.equals(".stop"))
                break;
            if(line.equals("\\n"))
            {
                gameOvertext+= '\n';
                continue;
            }
            gameOvertext += line + " ";
        }
        System.out.println(gameOvertext);
        reader.close();
    }
    public void printScore(File file) throws FileNotFoundException {
        PrintWriter printer = new PrintWriter(file);
        printer.println("From Printer: Your score is " + score + " using a "+ car.getType() + " car ");
        printer.println("Your maximum speed is " + car.getSpeed() + " mph");
        printer.close();
    }
    public DrivingGame() throws HeadlessException, IOException {
        roadmove = 0;
        score = 0;
        delay = 100;
        car.setSpeed(90);
        ob1.randomPositionGenerator();
        ob2.randomPositionGenerator();
        ob3.randomPositionGenerator();
        gameOver =false;
        obList.add(ob1);
        obList.add(ob2);
        obList.add(ob3);
        Collections.sort(obList);

        setBounds(300,10,screenSize+200,screenSize);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        addKeyListener(this);
        setFocusable(true);
        setResizable(false);

        //file IO
        fileReader(new FileReader("gameover.txt"));
    }
    @Override
    public void paint(Graphics g) {
        Image bground = new ImageIcon("backgroundimg.jpeg").getImage();
        g.drawImage(bground, 0, 0, null);
        g.setColor(new Color(204, 213, 174));
        g.fillRect(90,0,10,screenSize);
        g.fillRect(600, 0, 10, screenSize);
        g.setColor(new Color(80, 51, 34));
        g.fillRect(100, 0, 500, screenSize);
        drawComparison(g);

        if(roadmove==0)
        {
            for(int i=0; i<=screenSize; i+=100)
            {
                g.setColor(Color.white);
                g.fillRect(350, i,20, 70);

            }
            roadmove=1;
        }
        else
        {
            for(int i=50; i<=screenSize; i+=100)
            {
                g.setColor(Color.white);
                g.fillRect(350, i,20, 70);
            }
            roadmove=0;
        }
        car.getCarIcon().paintIcon(this, g, car.getxPos(), car.getyPos());

        list_Iter = obList.listIterator();
        while(list_Iter.hasNext())
        {
            Obstacles currentOb = list_Iter.next();
            currentOb.getObsIcon().paintIcon(this, g, currentOb.getObsX(), currentOb.getObsY());
            currentOb.setObsY(currentOb.getObsY() + 50);
            if (currentOb.getObsY() > screenSize)
            {
                currentOb.randomPositionGenerator();
            }
        }
        drawScore(g);
        checkEnd();
        //delay
        try
        {
            TimeUnit.MILLISECONDS.sleep(delay);
        }
        catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            drawGameOver(g);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void drawComparison(Graphics g) {
        //BEAUTIFUL title!
        g.setColor(new Color(24, 78, 119));
        g.fillRect(650, 60, 230, 40);
        g.setColor(new Color(197, 255, 9));
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Obstacles Coolness", 660, 87);

        //number
        g.setColor(new Color(255, 241, 203));
        g.setFont(new Font("Arial", Font.BOLD, 60));

        g.fillOval(630, 140,70,80);
        g.fillOval(630, 340,70,80);
        g.fillOval(630, 540,70,80);

        g.setColor(new Color(188, 71, 73));

        g.drawString("1", 650, 200);
        g.drawString("2", 650, 400);
        g.drawString("3", 650, 600);

        int iconY = -90;
        list_Iter = obList.listIterator();
        while (list_Iter.hasNext()) {
            Obstacles currentOb = list_Iter.next();
            currentOb.getObsIcon().paintIcon(this, g, 730, iconY += 200);
        }
    }

    public void drawScore(Graphics g)
    {

        g.setColor(new Color(255, 241, 203));
        g.fillRect(125,40, 210, 40);
        g.fillRect(390,40, 170, 40);
        g.setColor(new Color(26, 117, 159));
        g.setFont(new Font("Arial",Font.BOLD,30));
        g.drawString("Score : "+score, 130, 67);
        g.drawString(car.getSpeed()+" mph", 400, 67);

        score++;
        car.setSpeed(car.getSpeed()+1);
        if(car.getSpeed()>car.getSpeedlimit())
        {
            car.setSpeed(car.getSpeedlimit());
        }
    }

    public void checkEnd()
    {
        list_Iter = obList.listIterator();
        recursiveCheckEnd();
    }
     void recursiveCheckEnd()
     {
         if (!list_Iter.hasNext()) return;
         list_Iter.next().function();
         recursiveCheckEnd();
     }
    public void drawGameOver(Graphics g) throws FileNotFoundException {
        if(gameOver)
        {
            //border
            g.setColor(new Color(26, 117, 159));
            g.fillRect(110, 200, 480, 360);

            //rec
            g.setColor(new Color(242, 232, 207));
            g.fillRect(130, 220, 440, 320);

            //text
            g.setFont(new Font("Serif",1,30));
            g.setColor(new Color(188, 71, 73));

            drawMultipleLineString(g, gameOvertext);

            //draw playAgain
            g.setColor(new Color(106, 153, 78));
            g.drawString("Press Space to Play again", 170, 470);

            //readjust overestimate score and speed to print correctly to score.txt
            score--;
            car.setSpeed(car.getSpeed()-1);
            printScore(new File("score.txt"));
        }
        else
        {
            repaint();
        }
    }
    public static void main(String[] args) throws IOException {
        DrivingGame newGame = new DrivingGame();
    }

    public void drawMultipleLineString(Graphics g, String string)
    {
        //draw multiple-line strings
        String[] textArr = string.split("\n");
        int yText = 240;
        for(String text : textArr)
        {
            g.drawString(text,150, yText+=50);
        }
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
        if(e.getKeyCode()==KeyEvent.VK_SPACE && gameOver)
        {
            gameOver=false;
            try {
                new DrivingGame();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}

