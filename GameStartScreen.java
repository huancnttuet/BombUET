import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameStartScreen {


    public static void setImg(String path, Graphics g){
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(path));
        } catch (IOException e){
            e.printStackTrace();
        }
        g.drawImage(img, 0,0,720,720,null);
    }

    public static void drawStartScreen1(Graphics g){
        setImg("res/Play1.png", g);
    }
    public static void drawStartScreen2(Graphics g){
        setImg("res/Play2.png", g);
    }
    public static void drawStartScreen3(Graphics g){
        setImg("res/Help1.png", g);
    }
    public static void drawStartScreen4(Graphics g){
        setImg("res/Help2.png", g);
    }
    public static void drawStartScreen5(Graphics g){
        setImg("res/Control1.png", g);
    }
    public static void drawStartScreen6(Graphics g){
        setImg("res/Control2.png", g);
    }

    public static void drawHelp(Graphics g){
        setImg("res/HowToPlay.png", g);
    }

    public static void drawControls(Graphics g){
        setImg("res/Controls.png", g);
    }
}
