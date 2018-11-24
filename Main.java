
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Main {

    public static void main(String[] args) {

        JFrame frame = new JFrame();
        MyPanel game = new MyPanel();
        frame.setSize(game.getSize());
        frame.add(game);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}

class MyPanel extends JPanel implements KeyListener, ActionListener{
    private Player player1;
    private Player player2;

    int background;
    InGame inGame;
    Timer timer;
    private int count, time, timeWin;


    public MyPanel(){
        player1 = new Player(1);
        //thời gian
        timer = new Timer(0, this);
        timer.start();
        count = 0;
        time = 0;
        timeWin = 10000;

        background = 9;
        //map = 15 x 15
        inGame = new InGame(15);
        //kích cỡ Panel
        setSize(735,750);
        setVisible(true);
        addKeyListener(this);
        setFocusable(true);
    }

    public void reset(){
        timer.stop();
        timer = new Timer(0 , this);
        timer.start();
        count = 0;
        time = 0;
        timeWin = 10000;

        background = 1;
        inGame = new InGame(15);

    }

    @Override
    protected void paintComponent(Graphics g) {
//        //vẽ nền
//        g.setColor(Color.ORANGE);
//        g.fillRect(500,0,150,150);
//        //Hiện Player
//        player1.drawPlayer(g);
        switch (background){
            case 1: GameStartScreen.drawStartScreen1(g);break;
            case 2: GameStartScreen.drawStartScreen2(g);break;
            case 3: GameStartScreen.drawStartScreen3(g);break;
            case 4: GameStartScreen.drawStartScreen4(g);break;
            case 5: GameStartScreen.drawStartScreen5(g);break;
            case 6: GameStartScreen.drawStartScreen6(g);break;
            case 7: GameStartScreen.drawHelp(g);break;
            case 8: GameStartScreen.drawControls(g);break;
            case 9:
//                g.setColor(Color.ORANGE);
//                g.fillRect(0,0,800,800);
                inGame.drawMap(g);

                if(!player1.isDie()) player1.drawPlayer(g);
                if(player1.isWin()){
                    g.setColor(Color.WHITE);
                    g.fillRect(90,310,540,95);
                    g.setColor(Color.BLACK);
                    g.fillRect(100, 320, 520, 75);
                    g.setColor(Color.WHITE);
                    g.setFont(new Font("MSGothic", Font.BOLD, 64));
                    g.drawString("Player 1 Wins!", 150, 375);
                }
        }
    }
    @Override
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        char ch = e.getKeyChar();

        if(background == 9){
            //Control
            if(ch == 'w' || ch == 's') player1.setYDirection(0);
            if(ch == 'a' || ch == 'd') player1.setXDirection(0);
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        char ch = e.getKeyChar();

        if(background == 9) {

            if (ch == 'w') {
                player1.setYDirection(-1);
            }
            if (ch == 's') {
                player1.setYDirection(1);
            }
            if (ch == 'a') {
                player1.setXDirection(-1);
            }
            if (ch == 'd') {
                player1.setXDirection(1);
            }

            System.out.println(player1.getX() + " " + player1.getY());
        }

    }

    @Override
    public void keyTyped(KeyEvent e){}

    public void actionPerformed(ActionEvent e) {

        if(background == 9){
                player1.increaseWalkState();
                if(player1.getxDirection() == 1 && player1.getY() / 48 == (player1.getY() + 41) / 48
                        && inGame.getItem((player1.getX() + 44) / 48,player1.getY() / 48 ).isMoveOn())
                    player1.increaseX();
                if(player1.getxDirection() == -1 && player1.getY() / 48 == (player1.getY() + 41) / 48
                        && inGame.getItem((player1.getX()-2) / 48,player1.getY() / 48 ).isMoveOn())
                    player1.reduceX();
                if(player1.getyDirection() == 1 && player1.getX() / 48 == (player1.getX() + 41) / 48
                        && inGame.getItem(player1.getX() / 48,(player1.getY()+44) / 48 ).isMoveOn())
                    player1.increaseY();
                if(player1.getyDirection() == -1 && player1.getX() / 48 == (player1.getX() + 41) / 48
                        && inGame.getItem(player1.getX() / 48,(player1.getY()-2) / 48 ).isMoveOn())
                    player1.reduceY();

        }
        repaint();
    }
}
