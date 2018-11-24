import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Item {
    private String nameItem;
    private int x,y;
    private BufferedImage icon;
    private boolean moveOn;
    private int sizeItem;

    public Item(String nameItem){
        this.nameItem = nameItem;
        icon = null;
        sizeItem = 48;
    }

    public String getNameItem() {
        return nameItem;
    }

    public boolean isMoveOn() {
        return moveOn;
    }

    public int getXItem(){
        return this.x;
    }
    public int getYItem(){
        return this.y;
    }

    public int getSizeItem() {
        return sizeItem;
    }

    public void setNameItem(String nameItem) {
        this.nameItem = nameItem;
        setItem();
    }

    public void setMoveOn() {
        this.moveOn= true;
    }

    public void setIcon(String path){
        try {
            this.icon = ImageIO.read(new File(path));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void setItem(){
        switch (nameItem) {
            case "blank":
                moveOn = true;
                setIcon("res/blank.png");
                break;
            case "sortBlock":
                moveOn = false;         //Không cho đi qua
                setIcon("res/sortBlock.png");
                break;
            case "hardBlock":
                moveOn = false;         //Không cho đi qua
                setIcon("res/hardBlock.png");
                break;
            case "bomb" :
                setIcon("res/bomb.png");
                break;
            case "bomb1":
                setIcon("res/bomb1.png");
                break;
            case "bomb2":
                setIcon("res/bomb2.png");
                break;
            case "fireCenter":
                setIcon("res/fireCenter.png");
                break;
            case "fireCenter2":
                setIcon("res/fireCenter2.png");
                break;
            case "fireVer":
                setIcon("res/fireVertical.png");
                break;
            case "fireVer2":
                setIcon("res/fireVertical2.png");
                break;
            case "fireHor":
                setIcon("res/fireHorizonal.png");
                break;
            case "fireHor2":
                setIcon("res/fireHorizonal2.png");
                break;
            case "fireUp":
                setIcon("res/fireUEnd.png");
                break;
            case "fireUp2":
                setIcon("res/fireUEnd2.png");
                break;
            case "fireDown":
                setIcon("res/fireDEnd.png");
                break;
            case "fireDown2":
                setIcon("res/fireDEnd2.png");
                break;
            case "fireRight":
                setIcon("res/fireREnd.png");
                break;
            case "fireRight2":
                setIcon("res/fireREnd2.png");
                break;
            case "fireLeft":
                setIcon("res/fireLEnd.png");
                break;
            case "fireLeft2":
                setIcon("res/fireLEnd2.png");
                break;
            case "powerUp":
                setIcon("res/powerUp.png");
                break;
            case "speedUp":
                setIcon("res/speedUp.png");
                break;
            case "bombsUp":
                setIcon("res/bombsUp.png");
                break;
        }

        if(nameItem.contains("fire") || nameItem.contains("Up") || nameItem.contains("bomb")) {
            moveOn = true;
        }
    }
    public void setxyItem(int x, int y){
        this.x = x;
        this.y = y;
    }
    public void drawItem(Graphics g, int x, int y){
        setItem();
        g.drawImage(icon, x, y, sizeItem, sizeItem,null);
    }

}
