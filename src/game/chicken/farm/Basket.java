package game.chicken.farm;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.awt.geom.Rectangle2D;

public class Basket extends entity{
    Image img;
    public int HeightScore = 0;
    public int score = 0;

    public int originallive = 3;
    public int live = 3;

    boolean left = false;
    boolean right = false;
    boolean still = true;

    int originalSpeed ;    
    int speed ;
    
    Basket(int x, int y, int width, int height,int speed,int hp){
        super(x, y, width, height);
        this.originalSpeed = speed ;
        this.speed =speed ;
        this.originallive = hp;
        this.live = hp;

        String imageLocation = "img/Basket0.png";
        URL imageURL = this.getClass().getResource(imageLocation);
        img = Toolkit.getDefaultToolkit().getImage(imageURL);
    }

    public void updatePos(){
        if(still){
            //System.out.print(" || ");
            left = false;
            right = false;
        }
        else if(left&&!right && x> -20 ){
            //System.out.print(" << ");
            x = x - speed ;
        }
        else if(!left&&right && x<520-width){
            //System.out.print(" >> ");
            x = x + speed ;
        }

    }

    public void setLeft(boolean left) {
        this.left = left;
    }
    public void setRight(boolean right) {
        this.right = right;
    }
    public void setStill(boolean still) {
        this.still = still;
    }
    public Image getImage() {
        return img;
    }

    @Override
    public Rectangle2D getBound(){
        return (new Rectangle2D.Double(x+40,y+10,this.width-80, 5));
    }



}
