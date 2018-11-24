import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Player {

    private int curBomb; //Số lượng Bomb đã sử dụng hiện tại
    private int maxBomb; //Số lượng Bomb tối đa
    private int lengthBomb;
    private int speed;
    private int x, y;        //Vi tri tren map

    private boolean die;
    private ArrayList<Bomb> bombs;
    private BufferedImage icon;
    private int xDirection, yDirection, lastDirection;
    private int walkState;
    private boolean win;
    private int TOD;

    public Player(int vitri){
        if(vitri == 1) {
            x = 51;
            y = 51;
        }
        if(vitri == 2){
            x = 627;
            y = 627;
        }
        maxBomb = 1;
        lengthBomb = 1;
        speed = 3;
        die = false;
        icon = null;
        lastDirection = 0;
        walkState = 0;
        xDirection = yDirection = 0;
        win = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }

    public int getCurBomb() {
        return curBomb;
    }

    public int getMaxBomb() {
        return maxBomb;
    }

    public int getLengthBomb() {
        return lengthBomb;
    }

    public boolean isDie() {
        return die;
    }

    public boolean isWin() {
        return win;
    }

    public void setDie(boolean die) {
        this.die = die;
    }

    public void setWin(boolean win) {
        this.win = win;
    }


    public void increaseX(){
        this.x += 3;
    }
    public void reduceX(){
        this.x -= 3;
    }
    public void increaseY(){
        this.y += 3;
    }
    public void reduceY(){
        this.y -= 3;
    }

    public void increaseWalkState(){
        this.walkState++;
    }
    public void resetWalkState(){
        this.walkState = 0;
    }

    public void setXDirection(int x){
        xDirection = x;
        if(x == 1) lastDirection = 1;
        if(x == -1) lastDirection = 3;
    }
    public void setYDirection(int y){
        yDirection = y;
        if(y == 1) lastDirection = 0;
        if(y == -1) lastDirection = 2;
    }

    public int getxDirection() {
        return xDirection;
    }

    public int getyDirection() {
        return yDirection;
    }

    public int getTOD() {
        return TOD;
    }

    //Khi đặt Bomb
    public void addBomb(int row, int col, int time){
        bombs.add(new Bomb(row, col, lengthBomb, time));
        curBomb++;
    }
    public void removeBomb(int k){
        bombs.remove(k);
        curBomb--;
    }
    public Bomb getBomb(int k){
        return bombs.get(k);
    }
    public Bomb getLastBomb(){
        return bombs.get(bombs.size() - 1);
    }


    public void dieAnimation(int time){
        TOD = time;
        die = true;
        try{
            icon = ImageIO.read(new File("res/die1.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void dieAnimation2(){
        try {
            icon = ImageIO.read(new File("res/die2.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void dieAnimation3(){
        try {
            icon = ImageIO.read(new File("res/die3.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void dieAnimation4(){
        setDie(true);
        try {
            icon = ImageIO.read(new File("res/die4.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void setIcon(String path) {
        try {
            icon = ImageIO.read(new File(path));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void win1(){
        if(win){
            setIcon("res/win1.png");
        }
    }
    public void win2(){
        if(win){
            setIcon("res/win2.png");
        }
    }

    public void drawPlayer(Graphics g){
        if(!die){
            if(xDirection == 0 && yDirection == 0){
                switch (lastDirection) {
                    case 0:
                        setIcon("res/standDown.png");
                        break;
                    case 1:
                        setIcon("res/standRight.png");
                        break;
                    case 2:
                        setIcon("res/standUp.png");
                        break;
                    case 3:
                        setIcon("res/standLeft.png");
                        break;
                }
            }
            //Di sang phai
            if(xDirection == 1){
                 if(walkState % 2 == 0){
                     setIcon("res/standRight.png");
                 }
                 if(walkState % 4 == 1){
                     setIcon("res/walkRight1.png");
                 }
                 if(walkState % 4 == 3){
                     setIcon("res/walkRight2.png");
                 }
            }
            //Di sang trai
            if(xDirection == -1){
                if(walkState % 2 == 0){
                    setIcon("res/standLeft.png");
                }
                if(walkState % 4 == 1){
                    setIcon("res/walkLeft1.png");
                }
                if(walkState % 4 == 3){
                    setIcon("res/walkLeft2.png");
                }
            }
            //Di xuong
            if(yDirection == 1){
                if(walkState % 2 == 0){
                    setIcon("res/standDown.png");
                }
                if(walkState % 4 == 1){
                    setIcon("res/walkDown1.png");
                }
                if(walkState % 4 == 3){
                    setIcon("res/walkDown2.png");
                }
            }
            //Di chuyen len
            if(yDirection == -1){
                if(walkState % 2 == 0){
                    setIcon("res/standUp.png");
                }
                if(walkState % 4 == 1){
                    setIcon("res/walkUp1.png");
                }
                if(walkState % 4 == 3){
                    setIcon("res/walkUp2.png");
                }
            }
        }
        g.drawImage(icon, x - 6, y - 30, 54, 72, null);
    }
}
