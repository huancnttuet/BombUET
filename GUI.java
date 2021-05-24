import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GUI {
    private static final int x = 0;
    private static final int y = 0;
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 1000;

    public static void drawStartScreen1(Graphics g)
    {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("Resources/PlayGameStartScreen1.png"));
        } catch (IOException ex) {ex.printStackTrace();}

        g.drawImage(img,x, y, WIDTH, HEIGHT,null);
    }


    public static void drawStartScreen2(Graphics g)
    {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("Resources/PlayGameStartScreen2.png"));
        } catch (IOException ex) {ex.printStackTrace();}

        g.drawImage(img,x, y, WIDTH, HEIGHT,null);
    }


    public static void drawStartScreen3(Graphics g)
    {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("Resources/HowToPlayStartScreen1.png"));
        } catch (IOException ex) {ex.printStackTrace();}

        g.drawImage(img,x, y, WIDTH, HEIGHT,null);
    }


    public static void drawStartScreen4(Graphics g)
    {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("Resources/HowToPlayStartScreen2.png"));
        } catch (IOException ex) {ex.printStackTrace();}

        g.drawImage(img,x, y, WIDTH, HEIGHT,null);
    }


    public static void drawStartScreen5(Graphics g)
    {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("Resources/ControlsStartScreen1.png"));
        } catch (IOException ex) {ex.printStackTrace();}

        g.drawImage(img,x, y, WIDTH, HEIGHT,null);
    }


    public static void drawStartScreen6(Graphics g)
    {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("Resources/ControlsStartScreen2.png"));
        } catch (IOException ex) {ex.printStackTrace();}

        g.drawImage(img,x, y, WIDTH, HEIGHT,null);
    }


    public static void drawPlayScreen1(Graphics g)
    {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("Resources/PlayGame2PlayerScreen1.png"));
        } catch (IOException ex) {ex.printStackTrace();}

        g.drawImage(img,x, y, WIDTH, HEIGHT,null);
    }


    public static void drawPlayScreen2(Graphics g)
    {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("Resources/PlayGame2PlayerScreen2.png"));
        } catch (IOException ex) {ex.printStackTrace();}

        g.drawImage(img,x, y, WIDTH, HEIGHT,null);
    }


    public static void drawPlayScreen3(Graphics g)
    {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("Resources/PlayGame3PlayerScreen1.png"));
        } catch (IOException ex) {ex.printStackTrace();}

        g.drawImage(img,x, y, WIDTH, HEIGHT,null);
    }


    public static void drawPlayScreen4(Graphics g)
    {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("Resources/PlayGame3PlayerScreen2.png"));
        } catch (IOException ex) {ex.printStackTrace();}

        g.drawImage(img,x, y, WIDTH, HEIGHT,null);
    }


    public static void drawPlayScreen5(Graphics g)
    {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("Resources/PlayGame4PlayerScreen1.png"));
        } catch (IOException ex) {ex.printStackTrace();}

        g.drawImage(img,x, y, WIDTH, HEIGHT,null);
    }

    public static void drawPlayScreen6(Graphics g) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("Resources/PlayGame4PlayerScreen2.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        g.drawImage(img, x, y, WIDTH, HEIGHT, null);
    }

//    public static void drawHowToPlay(Graphics g)
//    {
//        BufferedImage img = null;
//        try {
//            img = ImageIO.read(new File("Resources/HowToPlay.png"));
//        } catch (IOException ex) {ex.printStackTrace();}
//
//        g.drawImage(img,x, y, WIDTH, HEIGHT,null);
//    }


    public static void drawControls(Graphics g)
    {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("Resources/Controls.png"));
        } catch (IOException ex) {ex.printStackTrace();}

        g.drawImage(img,x, y, WIDTH, HEIGHT,null);
    }
}
