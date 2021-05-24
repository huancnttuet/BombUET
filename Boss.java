import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Boss {

    private int port;

    private int speed;
    private boolean exist;
    private boolean alive;
    private int x, y;
    private int xDirection, yDirection;

    private BufferedImage icon;
    private int lastDirection;
    private int walkState;
    private int TOD;    // Time Of Death
    private boolean winner;

    public Boss(int p) {
        port = p;
        speed = 5;

        if(port == 2){x = 916; y = 916;}
        if(port == 3){x = 916; y = 51;}
        if(port == 4){x = 51; y = 916;}

        icon = null;
        lastDirection = 0;
        walkState = 0;
        TOD = 10000;
        alive = true;
        winner = false;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    public int getSpeed() { return speed; }
    public void incrementX() { x+=3; }
    public void incrementY() { y+=3; }
    public void decrementX() { x-=3; }
    public void decrementY() { y-=3; }

    public void setXDirection(int x){
        xDirection = x;
        if(x == 1) lastDirection = 1;
        if(x == -1) lastDirection = 3;
    }
    public void setYDirection(int y){
        yDirection = y;
        if(y == 1) lastDirection = 0;
        if(y == -1) lastDirection = 2;}
    public int getXDirection(){ return xDirection; }
    public int getYDirection(){ return yDirection; }



    public void setExist(boolean b){ exist = b; }
    public boolean doesExist(){ return exist; }

    public boolean isAlive(){ return alive; }
    public int getTOD(){ return TOD; }

    public void die(int time){
        TOD = time;
        alive = false;
        try {
            icon = ImageIO.read(new File("Resources/die1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void die2(){
        try {
            icon = ImageIO.read(new File("Resources/die2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void die3(){
        try {
            icon = ImageIO.read(new File("Resources/die3.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void die4(){
        setExist(false);
        try {
            icon = ImageIO.read(new File("Resources/die4.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void drawPlayer(Graphics g)
    {
        if(alive && !winner) {
            if (xDirection == 0 && yDirection == 0) {
                if (lastDirection == 0)
                    try {
                        icon = ImageIO.read(new File("Resources/Bdown1.png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                if (lastDirection == 1)
                    try {
                        icon = ImageIO.read(new File("Resources/Bright1.png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                if (lastDirection == 2)
                    try {
                        icon = ImageIO.read(new File("Resources/Bup1.png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                if (lastDirection == 3)
                    try {
                        icon = ImageIO.read(new File("Resources/Bleft1.png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }

            if (xDirection == 1) {
                if (walkState % 2 == 0)
                    try {
                        icon = ImageIO.read(new File("Resources/Bright1.png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                if (walkState % 4 == 1)
                    try {
                        icon = ImageIO.read(new File("Resources/Bright2.png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                if (walkState % 4 == 3)
                    try {
                        icon = ImageIO.read(new File("Resources/Bright3.png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }

            if (xDirection == -1) {
                if (walkState % 2 == 0)
                    try {
                        icon = ImageIO.read(new File("Resources/Bleft1.png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                if (walkState % 4 == 1)
                    try {
                        icon = ImageIO.read(new File("Resources/Bleft2.png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                if (walkState % 4 == 3)
                    try {
                        icon = ImageIO.read(new File("Resources/Bleft3.png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }

            if (yDirection == 1) {
                if (walkState % 2 == 0)
                    try {
                        icon = ImageIO.read(new File("Resources/Bdown1.png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                if (walkState % 4 == 1)
                    try {
                        icon = ImageIO.read(new File("Resources/Bdown2.png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                if (walkState % 4 == 3)
                    try {
                        icon = ImageIO.read(new File("Resources/Bdown3.png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }

            if (yDirection == -1) {
                if (walkState % 2 == 0)
                    try {
                        icon = ImageIO.read(new File("Resources/Bup1.png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                if (walkState % 4 == 1)
                    try {
                        icon = ImageIO.read(new File("Resources/Bup2.png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                if (walkState % 4 == 3)
                    try {
                        icon = ImageIO.read(new File("Resources/Bup3.png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }

        g.drawImage(icon, x - 6, y - 30, 54, 72, null);
    }

    public void incrementWalk(){ walkState++; }

    public void incrementSpeed(){ if(speed<8) speed++; }

    public boolean isWinner(){ return winner; }
    public void win()
    {
        winner = true;
    }

    public void win1()
    {
        if (winner) {
            try {
                icon = ImageIO.read(new File("Resources/Bwin1.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void win2()
    {
        if (winner) {
            try {
                icon = ImageIO.read(new File("Resources/Bwin2.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

