import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Bomberman {

    public static void main(String[] args) {
        JFrame j = new JFrame();
        MyPanel m = new MyPanel();
        j.setSize(m.getSize());
        j.add(m);
        j.setVisible(true);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Audio.playMenu();

    }
}

class MyPanel extends JPanel implements MouseListener, KeyListener, ActionListener{

    private Player p1;
    private Player p2;
    private Player p3;
    private Player p4;
    private Boss boss1;
    private Boss boss2;
    private Boss boss3;
    private Player boss4;

    private final int SIZE = 21;
    private Timer timer;
    private int count, time, countBoss;
    Arena arena;
    private int timeVictory;

    int background;

    Audio audio;

    public MyPanel()
    {
        p1 = new Player(1);
        p2 = new Player(2);
        p3 = new Player(3);
        p4 = new Player(4);

        boss1 = new Boss(2);
        boss2 = new Boss(3);
        boss3 = new Boss(4);
        boss4 = new Player(3);

        timer = new Timer(0,this);
        timer.start();
        count = 0;
        time = 0;
        arena = new Arena(SIZE);

        setSize(1023, 1048);
        setVisible(true);
        addMouseListener(this);
        addKeyListener(this);
        setFocusable(true);

        background = 1;

        audio = new Audio(0);
        timeVictory = 10000;
    }

    public void reset()
    {
        timer.stop();
        
        p1 = new Player(1);
        p2 = new Player(2);
        p3 = new Player(3);
        p4 = new Player(4);

        boss1 = new Boss(2);
        boss2 = new Boss(3);
        boss3 = new Boss(4);
        boss4 = new Player(3);

        timer = new Timer(0,this);
        timer.start();
        count = 0; countBoss = 0;
        time = 0;
        arena = new Arena(SIZE);

        background = 1;

        audio = new Audio(0);
        timeVictory = 10000;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        if(background == 1) {GUI.drawStartScreen1(g);}
        if(background == 2) {GUI.drawStartScreen2(g);}
        if(background == 3) {GUI.drawStartScreen3(g);}
        if(background == 4) {GUI.drawStartScreen4(g);}
        if(background == 5) {GUI.drawStartScreen5(g);}
        if(background == 6) {GUI.drawStartScreen6(g);}
        if(background == 7) {GUI.drawPlayScreen1(g);}
        if(background == 8) {GUI.drawPlayScreen2(g);}
        if(background == 9) {GUI.drawPlayScreen3(g);}
        if(background == 10) {GUI.drawPlayScreen4(g);}
        if(background == 11) {GUI.drawPlayScreen5(g);}
        if(background == 12) {GUI.drawPlayScreen6(g);}
        if(background == 14) {GUI.drawControls(g);}
        if(background == 13) {
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, 1000, 1000);

            arena.drawArena(g);
            g.setColor(Color.WHITE);

            if (p1.doesExist()) p1.drawPlayer(g);
            if (p4.doesExist()) p4.drawPlayer(g);
            if (boss1.doesExist()) boss1.drawPlayer(g);
            else {
                boss1.setX(0);
                boss1.setY(0);
            }
            if (boss2.doesExist()) boss2.drawPlayer(g);
            else {
                boss2.setX(0);
                boss2.setY(0);
            }
            if (boss3.doesExist()) boss3.drawPlayer(g);
            else {
                boss3.setX(0);
                boss3.setY(0);
            }
//            if(boss4.doesExist()) boss4.drawPlayer(g);
//            else {
//                boss4.setX(0);
//                boss4.setY(0);
//            }
            if(p1.isWinner() || boss2.isWinner() || boss3.isWinner() || boss1.isWinner() ||boss4.isWinner())
            {
                g.setColor(Color.ORANGE);
                g.fillRect(200, 350, 600, 120);
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(250, 370, 520, 75);
            }
            g.setColor(Color.BLUE);
            g.setFont(new Font(".Vn", Font.BOLD, 64));
            if(p1.isWinner()) g.drawString("You Win!", 335, 430);
            if(boss1.isWinner() || boss2.isWinner() || boss3.isWinner() || boss4.isWinner()) g.drawString("Game Over!", 320, 430);
        }
        if(background == 15)
        {
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, 800, 800);

            arena.drawArena(g);

            g.setColor(Color.BLUE);
            if (p1.doesExist()) p1.drawPlayer(g);
            if (p2.doesExist()) p2.drawPlayer(g);
            if (p3.doesExist()) p3.drawPlayer(g);
            if (p4.doesExist()) p4.drawPlayer(g);

            if(p1.isWinner() || p2.isWinner() || p3.isWinner() || p4.isWinner())
            {
                g.setColor(Color.WHITE);
                g.fillRect(180, 350, 570, 95);
                g.setColor(Color.BLACK);
                g.fillRect(200, 360, 520, 75);
            }
            g.setColor(Color.WHITE);
            g.setFont(new Font("MSGothic", Font.BOLD, 64));
            if(p1.isWinner()) g.drawString("Player 1 Wins!", 240, 415);
            if(p2.isWinner()) g.drawString("Player 2 Wins!", 240, 415);
            if(p3.isWinner()) g.drawString("Player 3 Wins!", 240, 415);
            if(p4.isWinner()) g.drawString("Player 4 Wins!", 240, 415);
        }
    }

    @Override
    public void mousePressed(MouseEvent e){}
    @Override
    public void mouseClicked(MouseEvent e){}
    @Override
    public void mouseReleased(MouseEvent e){}
    @Override
    public void mouseEntered(MouseEvent e){}
    @Override
    public void mouseExited(MouseEvent e){}

    @Override
    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();
        char ch = e.getKeyChar();
        if(background == 13){
            if (ch == 'q' && p1.getNumBombs() < p1.getMaxBombs() && p1.doesExist() && p1.isAlive()) {
                int r = (p1.getY() + 21) / 48;
                int c = (p1.getX() + 21) / 48;
                p1.addBomb(r, c, time);
                arena.dropBomb(p1.getLastBomb());
                Audio.playBombDrop();
            }

            if (ch == 'w' || ch == 's') p1.setYDirection(0);
            if (ch == 'a' || ch == 'd') p1.setXDirection(0);
        }
        if(background == 15)
        {
            if (ch == 'q' && p1.getNumBombs() < p1.getMaxBombs() && p1.doesExist() && p1.isAlive()) {
                int r = (p1.getY() + 21) / 48;
                int c = (p1.getX() + 21) / 48;
                p1.addBomb(r, c, time);
                arena.dropBomb(p1.getLastBomb());
                Audio.playBombDrop();
            }

            if (key == KeyEvent.VK_NUMPAD0 && p2.getNumBombs() < p2.getMaxBombs() && p2.doesExist() && p2.isAlive()) {
                int r = (p2.getY() + 21) / 48;
                int c = (p2.getX() + 21) / 48;
                p2.addBomb(r, c, time);
                arena.dropBomb(p2.getLastBomb());
                Audio.playBombDrop();
            }

            if (ch == 'u' && p3.getNumBombs() < p3.getMaxBombs() && p3.doesExist() && p3.isAlive()) {
                int r = (p3.getY() + 21) / 48;
                int c = (p3.getX() + 21) / 48;
                p3.addBomb(r, c, time);
                arena.dropBomb(p3.getLastBomb());
                Audio.playBombDrop();
            }

            if (key == KeyEvent.VK_NUMPAD7 && p4.getNumBombs() < p4.getMaxBombs() && p4.doesExist() && p4.isAlive()) {
                int r = (p4.getY() + 21) / 48;
                int c = (p4.getX() + 21) / 48;
                p4.addBomb(r, c, time);
                arena.dropBomb(p4.getLastBomb());
                Audio.playBombDrop();
            }

            if (ch == 'w' || ch == 's') p1.setYDirection(0);
            if (ch == 'a' || ch == 'd') p1.setXDirection(0);

            if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) p2.setYDirection(0);
            if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) p2.setXDirection(0);

            if (ch == 'i' || ch == 'k') p3.setYDirection(0);
            if (ch == 'j' || ch == 'l') p3.setXDirection(0);

            if (key == KeyEvent.VK_NUMPAD8 || key == KeyEvent.VK_NUMPAD5) p4.setYDirection(0);
            if (key == KeyEvent.VK_NUMPAD4 || key == KeyEvent.VK_NUMPAD6) p4.setXDirection(0);
        }
    }
    @Override
    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();
        char ch = e.getKeyChar();

        if(background == 1 || background == 2)
        {
            if(key == KeyEvent.VK_UP) {background = 5; Audio.playMenuMove();}
            if(key == KeyEvent.VK_DOWN) {background = 3; Audio.playMenuMove();}
            if(key == KeyEvent.VK_SPACE) {background = 7; Audio.playMenuSelect();}
            return;
        }

        if(background == 3 || background == 4)
        {
            if(key == KeyEvent.VK_UP) {background = 1; Audio.playMenuMove();}
            if(key == KeyEvent.VK_DOWN) {background = 5; Audio.playMenuMove();}
            if(key == KeyEvent.VK_SPACE) {
                background = 13;
                Audio.playMenuSelect();
                p1.setExist(true);
//                p4.setMaxBombs(1);
//                p4.setExist(true);
                boss1.setExist(true);
                boss2.setExist(true);
                boss3.setExist(true);
//                boss4.setExist(true);
            }
            return;
        }

        if(background == 5 || background == 6)
        {
            if(key == KeyEvent.VK_UP) {background = 3; Audio.playMenuMove();}
            if(key == KeyEvent.VK_DOWN) {background = 1; Audio.playMenuMove();}
            if(key == KeyEvent.VK_SPACE) {background = 14; Audio.playMenuSelect();}
            return;
        }

        if(background == 7 || background == 8)
        {
            if(key == KeyEvent.VK_UP) {background = 11; Audio.playMenuMove();}
            if(key == KeyEvent.VK_DOWN) {background = 9;Audio.playMenuMove();}
            if(key == KeyEvent.VK_SPACE)
            {
                p1.setExist(true);
                p2.setExist(true);
                background = 15;
                //audio.stopMenu();
                Audio.playMenuSelect();
            }
            if(key == KeyEvent.VK_BACK_SPACE) {background = 1; Audio.playMenuMove();}
            return;
        }

        if(background == 9 || background == 10)
        {
            if(key == KeyEvent.VK_UP) {background = 7; Audio.playMenuMove();}
            if(key == KeyEvent.VK_DOWN) {background = 11; Audio.playMenuMove();}
            if(key == KeyEvent.VK_SPACE)
            {
                p1.setExist(true);
                p2.setExist(true);
                p3.setExist(true);
                background = 15;
                //audio.stopMenu();
                Audio.playMenuSelect();
            }
            if(key == KeyEvent.VK_BACK_SPACE) {background = 1; Audio.playMenuMove();}
            return;
        }

        if(background == 11 || background == 12)
        {
            if(key == KeyEvent.VK_UP) {
                background = 9;
                Audio.playMenuMove();
            }
            if(key == KeyEvent.VK_DOWN) {
                background = 7;
                Audio.playMenuMove();
            }
            if(key == KeyEvent.VK_SPACE)
            {
                p1.setExist(true);
                p2.setExist(true);
                p3.setExist(true);
                p4.setExist(true);
                background = 15;
                //audio.stopMenu();
                Audio.playMenuSelect();
            }
            if(key == KeyEvent.VK_BACK_SPACE)
                background = 1; Audio.playMenuMove();
        }

        if(background == 13)
        {
            if (ch == 'w')
                p1.setYDirection(-1);
            if (ch == 's')
                p1.setYDirection(1);
            if (ch == 'a')
                p1.setXDirection(-1);
            if (ch == 'd')
                p1.setXDirection(1);
            if(key == KeyEvent.VK_BACK_SPACE)
                reset();
            return;
        }

        if(background == 14)
        {
            if(key == KeyEvent.VK_SPACE || key == KeyEvent.VK_BACK_SPACE)
                background = 5; Audio.playMenuMove();
            return;
        }

        if(background == 15)
        {
            if (ch == 'w')
                p1.setYDirection(-1);
            if (ch == 's')
                p1.setYDirection(1);
            if (ch == 'a')
                p1.setXDirection(-1);
            if (ch == 'd')
                p1.setXDirection(1);

            if (key == KeyEvent.VK_UP)
                p2.setYDirection(-1);
            if (key == KeyEvent.VK_DOWN)
                p2.setYDirection(1);
            if (key == KeyEvent.VK_LEFT)
                p2.setXDirection(-1);
            if (key == KeyEvent.VK_RIGHT)
                p2.setXDirection(1);

            if (ch == 'i')
                p3.setYDirection(-1);
            if (ch == 'k')
                p3.setYDirection(1);
            if (ch == 'j')
                p3.setXDirection(-1);
            if (ch == 'l')
                p3.setXDirection(1);

            if (key == KeyEvent.VK_NUMPAD8)
                p4.setYDirection(-1);
            if (key == KeyEvent.VK_NUMPAD5)
                p4.setYDirection(1);
            if (key == KeyEvent.VK_NUMPAD4)
                p4.setXDirection(-1);
            if (key == KeyEvent.VK_NUMPAD6)
                p4.setXDirection(1);

            if(key == KeyEvent.VK_BACK_SPACE)
                reset();
        }
    }
    @Override
    public void keyTyped(KeyEvent e){}

    public void actionPerformed(ActionEvent e) {
        count++;
        if((count % 20)==0) {
            time++;
            //NHap Nhay
            if (background == 1 && (time / 2) % 2 == 0) background = 2;
            if (background == 2 && (time / 2) % 2 == 1) background = 1;
            if (background == 3 && (time / 2) % 2 == 0) background = 4;
            if (background == 4 && (time / 2) % 2 == 1) background = 3;
            if (background == 5 && (time / 2) % 2 == 0) background = 6;
            if (background == 6 && (time / 2) % 2 == 1) background = 5;
            if (background == 7 && (time / 2) % 2 == 0) background = 8;
            if (background == 8 && (time / 2) % 2 == 1) background = 7;
            if (background == 9 && (time / 2) % 2 == 0) background = 10;
            if (background == 10 && (time / 2) % 2 == 1) background = 9;
            if (background == 11 && (time / 2) % 2 == 0) background = 12;
            if (background == 12 && (time / 2) % 2 == 1) background = 11;
        }
        if(background == 13){
            if(count%20 == 0) {
                putBomb(p1);

//                //Player Win
                if (p1.doesExist() && !boss2.doesExist() && !boss3.doesExist() && p4.doesExist()) {
                    p1.win();
                    if (time < timeVictory) {
                        timeVictory = time;
                    }
                }
                if ((time / 3) % 2 == 1) p1.win2();
                if ((time / 3) % 2 == 0) p1.win1();
                //Boss Win
                if (!p1.doesExist()) {
                    boss2.win();
                    if (time < timeVictory) {
                        timeVictory = time;
                    }
                }
                if ((time / 3) % 2 == 1) p1.win2();
                if ((time / 3) % 2 == 0) p1.win1();
                if(time == timeVictory + 1) Audio.playVictory();
            }

            //Die
            kill(p1);
            kill(boss1);
            kill(boss2);
            kill(boss3);

            //Boss 1
            int key = (int)(Math.random()*30);

            if(key == 0) {
                if (countBoss == 0) boss1.setXDirection(1);
                if (countBoss == 1) boss1.setXDirection(-1);
            }else if(key == 1){
                if (countBoss == 0) boss1.setYDirection(1);
                if (countBoss == 1) boss1.setYDirection(-1);
            }

            if(count%(14 - boss1.getSpeed()) == 0) {
                if(count%((14- boss1.getSpeed()/2)*2) == 0) boss1.incrementWalk();
                if(moveRight(boss1)){
                    boss1.incrementX();
                }
                else  {
                    countBoss = 1;
                    boss1.setXDirection(-1);
                }
                if (moveLeft(boss1))  {
                    boss1.decrementX();
                }
                else{
                    countBoss = 0;
                }
                if (moveDown(boss1)){
                    boss1.incrementY();
                } else{
                    countBoss = 1;
                    boss1.setYDirection(-1);
                }
                if (moveUp(boss3)){
                    boss3.decrementY();
                } else {
                    countBoss = 0;
                }
            }

            //Boss 2
            int key2 = (int)(Math.random()*30);
            if(key2%30==0){
                if(p1.getX() < boss2.getX()){
                    boss2.setXDirection(-1);
                } else {
                    boss2.setXDirection(1);
                }
            } else if(key2%30 == 1){
                if(p1.getY() < boss2.getY()){
                    boss2.setYDirection(-1);
                } else {
                    boss2.setYDirection(1);
                }
            }
            //Boss 4

//            if((int)(Math.random()*50) == 0) {
//                if (boss4.getNumBombs() < boss4.getMaxBombs() && boss4.doesExist() && boss4.isAlive()) {
//                    int r = (boss4.getY() + 21) / 48;
//                    int c = (boss4.getX() + 21) / 48;
//                    boss4.addBomb(r, c, time);
//                    arena.dropBomb(boss4.getLastBomb());
//                    Audio.playBombDrop();
//                }
//            }
//            putBomb(boss4);
//            int key4 = (int)(Math.random()*30);
//
//            if(key4 == 0) {
//                if (countBoss == 0) boss4.setXDirection(1);
//                if (countBoss == 1) boss4.setXDirection(-1);
//            }else if(key4 == 1){
//                if (countBoss == 0) boss4.setYDirection(1);
//                if (countBoss == 1) boss4.setYDirection(-1);
//            }
//
//            if(count%(19 - boss4.getSpeed()) == 0) {
//                if(count%((19 - boss4.getSpeed()/2)*2) == 0) boss4.incrementWalk();
//                if(moveRight(boss4)){
//                    boss4.incrementX();
//                }
//                else  {
//                    countBoss = 1;
//                    boss4.setXDirection(-1);
//                }
//                if (moveLeft(boss4))  {
//                    boss4.decrementX();
//                }
//                else{
//                    countBoss = 0;
//                }
//                if (moveDown(boss4)){
//                    boss4.incrementY();
//                } else{
//                    countBoss = 1;
//                    boss4.setYDirection(-1);
//                }
//                if (moveUp(boss4)){
//                    boss4.decrementY();
//                } else {
//                    countBoss = 0;
//                }
//            }
            //Boss 3
            int key3 = (int)(Math.random()*30);

            if(key3 == 0) {
                if (countBoss == 0) boss3.setXDirection(1);
                if (countBoss == 1) boss3.setXDirection(-1);
            }else if(key3 == 1){
                if (countBoss == 0) boss3.setYDirection(1);
                if (countBoss == 1) boss3.setYDirection(-1);
            }
            if(count%(19 - boss3.getSpeed()) == 0) {
                if(count%((19 - boss3.getSpeed()/2)*2) == 0) boss3.incrementWalk();
                if(moveRight(boss3)){
                   boss3.incrementX();
                }
                else {
                    countBoss = 1;
                    boss3.setXDirection(-1);
                }
                if (moveLeft(boss3))  {
                    boss3.decrementX();
                }
                else{
                    countBoss = 0;
                }
                if (moveDown(boss3)){
                    boss3.incrementY();
                } else{
                    countBoss = 1;
                    boss3.setYDirection(-1);
                }
                if (moveUp(boss3)){
                    boss3.decrementY();
                } else {
                    countBoss = 0;
                }
            }
            /////////
            //Xử lý va chạm khi di chuyen
            if(count%(9 - p1.getSpeed()) == 0) {
                if(count%((9 - p1.getSpeed()/2)*2) == 0) p1.incrementWalk();
                if (moveRight(p1))   p1.incrementX();
                if (moveLeft(p1)) p1.decrementX();
                if (moveDown(p1)) p1.incrementY();
                if (moveUp(p1)) p1.decrementY();
            }
            if(count%(9 - boss2.getSpeed()) == 0) {
                if(count%((9 - boss2.getSpeed()/2)*2) == 0) boss2.incrementWalk();
                if (moveRight(boss2)) boss2.incrementX();
                if (moveLeft(boss2)) boss2.decrementX();
                if (moveDown(boss2)) boss2.incrementY();
                if (moveUp(boss2)) boss2.decrementY();
            }
            if(count%(9 - boss3.getSpeed()) == 0) {
                if(count%((9 - boss3.getSpeed()/2)*2) == 0) boss3.incrementWalk();
                if (moveRight(boss3)) boss3.incrementX();
                if (moveLeft(boss3)) boss3.decrementX();
                if (moveDown(boss3)) boss3.incrementY();
                if (moveUp(boss3)) boss3.decrementY();
            }
            if(count%(9 - boss1.getSpeed()) == 0) {
                if(count%((9 - boss1.getSpeed()/2)*2) == 0) boss1.incrementWalk();
                if (moveRight(boss1)) boss1.incrementX();
                if (moveLeft(boss1)) boss1.decrementX();
                if (moveDown(boss1)) boss1.incrementY();
                if (moveUp(boss1)) boss1.decrementY();
            }
            //va cham voi boss
            if(p1.collision(boss3) || p1.collision(boss2) || p1.collision(boss1)){
                p1.die(time);
            }
            if(time == p1.getTOD() + 1) p1.die2();
            if(time == p1.getTOD() + 2) p1.die3();
            if(time == p1.getTOD() + 3) p1.die4();
            //va cham voi Item
            moveItem(p1);
        }

        if (background == 15 ) {
            if((count%20)==0) {
                putBomb(p1);
                putBomb(p2);
                putBomb(p3);
                putBomb(p4);

                if (p1.doesExist() && !p2.doesExist() && !p3.doesExist() && !p4.doesExist())
                {
                    p1.win();
                    if(time < timeVictory) timeVictory = time;
                }
                if ((time/3) % 2 == 1) p1.win2();
                if ((time/3) % 2 == 0) p1.win1();

                if (!p1.doesExist() && p2.doesExist() && !p3.doesExist() && !p4.doesExist())
                {
                    p2.win();
                    if(time < timeVictory) timeVictory = time;
                }
                if ((time/3) % 2 == 1) p2.win2();
                if ((time/3) % 2 == 0) p2.win1();

                if (!p1.doesExist() && !p2.doesExist() && p3.doesExist() && !p4.doesExist())
                {
                    p3.win();
                    if(time < timeVictory) timeVictory = time;
                }
                if ((time/3) % 2 == 1) p3.win2();
                if ((time/3) % 2 == 0) p3.win1();

                if (!p1.doesExist() && !p2.doesExist() && !p3.doesExist() && p4.doesExist())
                {
                    p4.win();
                    if(time < timeVictory) timeVictory = time;
                }
                if ((time/3) % 2 == 1) p4.win2();
                if ((time/3) % 2 == 0) p4.win1();

                if(time == timeVictory + 1) Audio.playVictory();
            }

            //Die
            kill(p1);
            kill(p2);
            kill(p3);
            kill(p4);


            //Xử lý va chạm khi di chuyen
            if(count%(9 - p2.getSpeed()) == 0) {
                if(count%((9 - p2.getSpeed()/2)*2) == 0) p1.incrementWalk();
                if (moveRight(p1))   p1.incrementX();
                if (moveLeft(p1)) p1.decrementX();
                if (moveDown(p1)) p1.incrementY();
                if (moveUp(p1)) p1.decrementY();
            }
            if(count%(9 - p2.getSpeed()) == 0) {
                if(count%((9 - p2.getSpeed()/2)*2) == 0) p2.incrementWalk();
                if (moveRight(p2)) p2.incrementX();
                if (moveLeft(p2)) p2.decrementX();
                if (moveDown(p2)) p2.incrementY();
                if (moveUp(p2)) p2.decrementY();
            }
            if(count%(9 - p3.getSpeed()) == 0) {
                if(count%((9 - p3.getSpeed()/2)*2) == 0) p3.incrementWalk();
                if (moveRight(p3)) p3.incrementX();
                if (moveLeft(p3)) p3.decrementX();
                if (moveDown(p3)) p3.incrementY();
                if (moveUp(p3)) p3.decrementY();
            }
            if(count%(9 - p4.getSpeed()) == 0) {
                if(count%((9 - p4.getSpeed()/2)*2) == 0) p4.incrementWalk();
                if (moveRight(p4)) p4.incrementX();
                if (moveLeft(p4)) p4.decrementX();
                if (moveDown(p4)) p4.incrementY();
                if (moveUp(p4)) p4.decrementY();
            }

            //va cham voi Item
            moveItem(p1);
            moveItem(p2);
            moveItem(p3);
            moveItem(p4);
        }
        repaint();
        System.out.println(time);

    }
    public boolean moveRight(Player p){
        if (p.getXDirection() == 1 && p.getY() / 48 == (p.getY() + 41) / 48 && arena.getPiece(p.getY() / 48, (p.getX() + 44) / 48).isIntangible())
            return true;
        return false;
    }
    public boolean moveLeft(Player p){
        if (p.getXDirection() == -1 && p.getY() / 48 == (p.getY() + 41) / 48 && arena.getPiece(p.getY() / 48, (p.getX() - 2) / 48).isIntangible())
            return true;
        return false;
    }
    public boolean moveDown(Player p){
        if (p.getYDirection() == 1 && p.getX() / 48 == (p.getX() + 41) / 48 && arena.getPiece((p.getY() + 44) / 48, p.getX() / 48).isIntangible())
            return true;
        return false;
    }
    public boolean moveUp(Player p){
        if (p.getYDirection() == -1 && p.getX() / 48 == (p.getX() + 41) / 48 && arena.getPiece((p.getY() - 2) / 48, p.getX() / 48).isIntangible())
            return true;
        return false;
    }

    public boolean moveRight(Boss p){
        if (p.getXDirection() == 1 && p.getY() / 48 == (p.getY() + 41) / 48 && arena.getPiece(p.getY() / 48, (p.getX() + 44) / 48).isIntangible())
            return true;
        return false;
    }
    public boolean moveLeft(Boss p){
        if (p.getXDirection() == -1 && p.getY() / 48 == (p.getY() + 41) / 48 && arena.getPiece(p.getY() / 48, (p.getX() - 2) / 48).isIntangible())
            return true;
        return false;
    }
    public boolean moveDown(Boss p){
        if (p.getYDirection() == 1 && p.getX() / 48 == (p.getX() + 41) / 48 && arena.getPiece((p.getY() + 44) / 48, p.getX() / 48).isIntangible())
            return true;
        return false;
    }
    public boolean moveUp(Boss p){
        if (p.getYDirection() == -1 && p.getX() / 48 == (p.getX() + 41) / 48 && arena.getPiece((p.getY() - 2) / 48, p.getX() / 48).isIntangible())
            return true;
        return false;
    }

    public void putBomb(Player p){
        for (int k = 0; k < p.getNumBombs(); k++) {
            if (time == p.getBomb(k).getStart() + 5)
                arena.bomb2(p.getBomb(k));
            if (time == p.getBomb(k).getStart() + 10) {
                arena.bomb3(p.getBomb(k));
                arena.setTangible(p.getBomb(k));
            }
            if (time == p.getBomb(k).getStart() + 15)
                arena.bomb2(p.getBomb(k));
            if (time == p.getBomb(k).getStart() + 20)
                arena.dropBomb(p.getBomb(k));
            if (time == p.getBomb(k).getStart() + 25)
                arena.bomb2(p.getBomb(k));
            if (time == p.getBomb(k).getStart() + 30)
                arena.bomb3(p.getBomb(k));
            if (time == p.getBomb(k).getStart() + 35)
                arena.bomb2(p.getBomb(k));
            if (time == p.getBomb(k).getStartExplosion()) {
                for (int i = 0; i < p.getNumBombs(); i++)
                    if (p.getBomb(i).inRange(p.getBomb(k)))
                        p.getBomb(i).setStart(p.getBomb(k).getStart());
//                for (int i = 0; i < p2.getNumBombs(); i++)
//                    if (p2.getBomb(i).inRange(p2.getBomb(k)))
//                        p2.getBomb(i).setStart(p2.getBomb(k).getStart());
//
//                for (int i = 0; i < p3.getNumBombs(); i++)
//                    if (p3.getBomb(i).inRange(p2.getBomb(k)))
//                        p3.getBomb(i).setStart(p2.getBomb(k).getStart());
//
//                for (int i = 0; i < p4.getNumBombs(); i++)
//                    if (p4.getBomb(i).inRange(p2.getBomb(k)))
//                        p4.getBomb(i).setStart(p2.getBomb(k).getStart());
            }
            if (time == p.getBomb(k).getStartExplosion())
            {
                arena.explode(p.getBomb(k));
                Audio.playBombExplode();
            }
            if (time == p.getBomb(k).getStartExplosion() + 1)
                arena.expand(p.getBomb(k));
            if (time == p.getBomb(k).getStartExplosion() + 4)
                arena.unexpand(p.getBomb(k));
            if (time == p.getBomb(k).getStartExplosion() + 5) {
                arena.clearFire(p.getBomb(k));
                p.removeBomb(k);
                k--;
            }
        }
    }
    public void moveItem(Player p){
        if (arena.getPiece((p.getY() + 21) / 48, (p.getX() + 21) / 48).getID().equals("speedUp")) {
            arena.getPiece((p.getY() + 21) / 48, (p.getX() + 21) / 48).setID("blank");
            p.incrementSpeed();
        }
        if (arena.getPiece((p.getY() + 21) / 48, (p.getX() + 21) / 48).getID().equals("powerUp")) {
            arena.getPiece((p.getY() + 21) / 48, (p.getX() + 21) / 48).setID("blank");
            p.incrementPower();
        }
        if (arena.getPiece((p.getY() + 21) / 48, (p.getX() + 21) / 48).getID().equals("bombsUp")) {
            arena.getPiece((p.getY() + 21) / 48, (p.getX() + 21) / 48).setID("blank");
            p.incrementMaxBombs();
        }
    }

    public void kill(Player p){
        if (arena.getPiece((p.getY() + 21) / 48, (p.getX() + 21) / 48).getID().contains("fire"))
            p.die(time);
        if (time == p.getTOD() + 1)
            p.die2();
        if (time == p.getTOD() + 2)
            p.die3();
        if (time == p.getTOD() + 3)
            p.die4();
    }
    public void kill(Boss p){
        if (arena.getPiece((p.getY() + 21) / 48, (p.getX() + 21) / 48).getID().contains("fire"))
            p.die(time);
        if (time == p.getTOD() + 1)
            p.die2();
        if (time == p.getTOD() + 2)
            p.die3();
        if (time == p.getTOD() + 3)
            p.die4();
    }
}