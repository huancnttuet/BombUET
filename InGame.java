import java.awt.*;

public class InGame {
    private int size;
    private  Item[][] map;

    public InGame(int size){
        this.size = size;
        map = new Item[size][size];
        //Map trong Game
        for (int x = 0; x < size; ++x) {
            for (int y = 0; y < size; ++y) {
                if (x == 0 || y == 0 || x == size - 1 || y == size - 1 || x % 2 == 0 && y % 2 == 0) {
                    map[x][y] = new Item("hardBlock");
                } else if ((x == 1 || x == 2 || x == size - 2 || x == size - 3) && (y == 1 || y == 2 || y == size - 2 || y == size - 3)) {
                    map[x][y] = new Item("blank");
                } else if ((int) (Math.random() * 3) == 0) {
                    map[x][y] = new Item("sortBlock");
                } else {
                    map[x][y] = new Item("blank");
                }
            }
        }
    }

    public void drawMap(Graphics g){
        int sizeItem = map[0][0].getSizeItem(); // == 48
        int i = 0;
        for (int x = 0; x < size; ++x){
            for (int y = 0; y < size; ++y){
                map[x][y].drawItem(g, x * sizeItem, y * sizeItem);
                map[x][y].setxyItem(x,y);
            }
        }
    }

    public void putBomb(Bomb b){
        int x = b.getRow();
        int y = b.getCol();
        map[x][y].setNameItem("bomb");
    }

    public void putBomb2(Bomb b){
        int x = b.getRow();
        int y = b.getCol();
        map[x][y].setNameItem("bomb2");
    }

    public void putBomb3(Bomb b){
        int x = b.getRow();
        int y = b.getCol();
        map[x][y].setNameItem("bomb3");
    }

    public void setVoHinh(Bomb b){
        int x = b.getRow();
        int y = b.getCol();
        map[x][y].setMoveOn();
    }

    //Bomb nổ
    public void explode(Bomb b){
        int x = b.getRow();
        int y = b.getCol();
        int lengthBomb = b.getLength();

        int k = 1;
        boolean end = false;
        String nameItem;
        Item item;
        //Chieu X +
        while (k <= lengthBomb && !end){

            item = this.getItem(x + k, y);
            nameItem = item.getNameItem();
            //Bomb nổ gặp vật cản
            if(nameItem.equals("hardBlock")){
                map[x + k - 1][y].setNameItem("fireRight");
            }
            else if(nameItem.contains("Up")){
                item.setNameItem("fireRight");
            }
            else if(nameItem.equals("bomb") || nameItem.contains("fire")){
                item.setNameItem("fireHor");
            }
            else if(nameItem.equals("sortBlock")){
                int num = (int)(Math.random() * 10);
                if(num == 0) item.setNameItem("powerUp");
                else if(num == 1) item.setNameItem("speedUp");
                else if(num == 2) item.setNameItem("bombsUp");
                else item.setNameItem("fireHor");
            }
            else if(nameItem.equals("blank")){
                if(k == lengthBomb){
                    item.setNameItem("fireRight");
                }
                else{
                    item.setNameItem("fireHor");
                    k++;
                    continue;
                }
            }
            end = true;
        }
        k = 1; end = false;
        //Chieu X-
        while (k <= lengthBomb && !end){
            item = this.getItem(x - k, y);
            nameItem = item.getNameItem();
            if(nameItem.equals("hardBlock")){
                map[x - k + 1][y].setNameItem("fireLeft");
            }
            else if(nameItem.contains("Up")){
                item.setNameItem("fireLeft");
            }
            else if(nameItem.equals("bomb") || nameItem.contains("fire")){
                item.setNameItem("fireHor");
            }
            else if(nameItem.equals("sortBlock")){
                int num = (int)(Math.random() * 10);
                if(num == 0) item.setNameItem("powerUp");
                else if(num == 1) item.setNameItem("speedUp");
                else if(num == 2) item.setNameItem("bombsUp");
                else item.setNameItem("fireHor");
            }
            else if(nameItem.equals("blank")){
                if(k == lengthBomb){
                    item.setNameItem("fireLeft");
                }
                else{
                    item.setNameItem("fireHor");
                    k++;
                    continue;
                }
            }
            end = true;
        }
        k = 1; end = false;
        //Chieu Y+
        while (k <= lengthBomb && !end){
            item = this.getItem(x , y + k);
            nameItem = item.getNameItem();
            if(nameItem.equals("hardBlock")){
                map[x][y + k - 1].setNameItem("fireDown");
            }
            else if(nameItem.contains("Up")){
                item.setNameItem("fireDown");
            }
            else if(nameItem.equals("bomb") || nameItem.contains("fire")){
                item.setNameItem("fireVer");
            }
            else if(nameItem.equals("sortBlock")){
                int num = (int)(Math.random() * 10);
                if(num == 0) item.setNameItem("powerUp");
                else if(num == 1) item.setNameItem("speedUp");
                else if(num == 2) item.setNameItem("bombsUp");
                else item.setNameItem("fireVer");
            }
            else if(nameItem.equals("blank")){
                if(k == lengthBomb){
                    item.setNameItem("fireDown");
                }
                else{
                    item.setNameItem("fireVer");
                    k++;
                    continue;
                }
            }
            end = true;
        }
        k = 1; end = false;
        //Chieu Y-
        while (k <= lengthBomb && !end){
            item = this.getItem(x , y - k);
            nameItem = item.getNameItem();
            if(nameItem.equals("hardBlock")){
                map[x][y - k + 1].setNameItem("fireUp");
            }
            else if(nameItem.contains("Up")){
                item.setNameItem("fireUp");
            }
            else if(nameItem.equals("bomb") || nameItem.contains("fire")){
                item.setNameItem("fireVer");
            }
            else if(nameItem.equals("sortBlock")){
                int num = (int)(Math.random() * 10);
                if(num == 0) item.setNameItem("powerUp");
                else if(num == 1) item.setNameItem("speedUp");
                else if(num == 2) item.setNameItem("bombsUp");
                else item.setNameItem("fireVer");
            }
            else if(nameItem.equals("blank")){
                if(k == lengthBomb){
                    item.setNameItem("fireUp");
                }
                else{
                    item.setNameItem("fireVer");
                    k++;
                    continue;
                }
            }
            end = true;
        }
        //Tâm vụ nổ
        map[x][y].setNameItem("fireCenter");
    }

    public void explade2(Bomb b){
        int x = b.getRow();
        int y = b.getCol();
        int lengthBomb = b.getLength();
        String nameItem;
        Item item;

        map[x][y].setNameItem("fireCenter2");
        int k = 1;
        boolean end = false;
        //Chieu X +
        while (k <= lengthBomb && !end){
            item = this.getItem(x + k, y);
            nameItem = item.getNameItem();
            if(nameItem.contains("fire")){
                if(nameItem.equals("fireRight")){
                    item.setNameItem("fireRight2");
                } else{
                    item.setNameItem("fireHor2");
                    k++;
                    continue;
                }
            }
            end = true;
        }
        //Chieu X -
        k = 1; end = false;
        while (k <= lengthBomb && !end){
            item = this.getItem(x - k, y);
            nameItem = item.getNameItem();
            if(nameItem.contains("fire")){
                if(nameItem.equals("fireLeft")){
                    item.setNameItem("fireLeft2");
                } else{
                    item.setNameItem("fireHor2");
                    k++;
                    continue;
                }
            }
            end = true;
        }
        //Chieu Y+
        k = 1; end = false;
        while (k <= lengthBomb && !end){
            item = this.getItem(x, y + k);
            nameItem = item.getNameItem();
            if(nameItem.contains("fire")){
                if(nameItem.equals("fireDown")){
                    item.setNameItem("fireDown2");
                } else{
                    item.setNameItem("fireVer2");
                    k++;
                    continue;
                }
            }
            end = true;
        }
        //Chieu Y-
        k = 1; end = false;
        while (k <= lengthBomb && !end){
            item = this.getItem(x, y - k);
            nameItem = item.getNameItem();
            if(nameItem.contains("fire")){
                if(nameItem.equals("fireUp")){
                    item.setNameItem("fireUp2");
                } else{
                    item.setNameItem("fireVer2");
                    k++;
                    continue;
                }
            }
            end = true;
        }
    }

    public void explade3(Bomb b){
        int x = b.getRow();
        int y = b.getCol();
        int lengthBomb = b.getLength();
        String nameItem;
        Item item;

        map[x][y].setNameItem("fireCenter");
        int k = 1;
        boolean end = false;
        //Chieu X +
        while (k <= lengthBomb && !end){
            item = this.getItem(x + k, y);
            nameItem = item.getNameItem();
            if(nameItem.contains("fire")){
                if(nameItem.equals("fireRight2")){
                    item.setNameItem("fireRight");
                } else{
                    item.setNameItem("fireHor");
                    k++;
                    continue;
                }
            }
            end = true;
        }
        //Chieu X -
        k = 1; end = false;
        while (k <= lengthBomb && !end){
            item = this.getItem(x - k, y);
            nameItem = item.getNameItem();
            if(nameItem.contains("fire")){
                if(nameItem.equals("fireLeft2")){
                    item.setNameItem("fireLeft");
                } else{
                    item.setNameItem("fireHor");
                    k++;
                    continue;
                }
            }
            end = true;
        }
        //Chieu Y+
        k = 1; end = false;
        while (k <= lengthBomb && !end){
            item = this.getItem(x, y + k);
            nameItem = item.getNameItem();
            if(nameItem.contains("fire")){
                if(nameItem.equals("fireDown2")){
                    item.setNameItem("fireDown");
                } else{
                    item.setNameItem("fireVer");
                    k++;
                    continue;
                }
            }
            end = true;
        }
        //Chieu Y-
        k = 1; end = false;
        while (k <= lengthBomb && !end){
            item = this.getItem(x, y - k);
            nameItem = item.getNameItem();
            if(nameItem.contains("fire")){
                if(nameItem.equals("fireUp2")){
                    item.setNameItem("fireUp");
                } else{
                    item.setNameItem("fireVer");
                    k++;
                    continue;
                }
            }
            end = true;
        }
    }

    public void clearFire(Bomb b){
        int x = b.getRow();
        int y = b.getCol();
        int lengthBomb = b.getLength();

        map[x][y].setNameItem("blank");
        int k = 1;
        boolean end = false;
        //Chieu X+
        while (k <= lengthBomb && !end){
            if(map[x + k][y].getNameItem().contains("fire")){
                map[x + k][y].setNameItem("blank");
                k++;
                continue;
            }
            end = true;
        }
        k = 1; end = false;
        //Chieu X-
        while (k <= lengthBomb && !end){
            if(map[x - k][y].getNameItem().contains("fire")){
                map[x - k][y].setNameItem("blank");
                k++;
                continue;
            }
            end = true;
        }
        k = 1; end = false;
        //Chieu Y+
        while (k <= lengthBomb && !end){
            if(map[x][y + k].getNameItem().contains("fire")){
                map[x][y + k].setNameItem("blank");
                k++;
                continue;
            }
            end = true;
        }
        k = 1; end = false;
        //Chieu Y-
        while (k <= lengthBomb && !end){
            if(map[x][y - k].getNameItem().contains("fire")){
                map[x][y - k].setNameItem("blank");
                k++;
                continue;
            }
            end = true;
        }
    }

    public Item getItem(int x, int y){
        return map[x][y];
    }
}
