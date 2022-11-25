package game.chicken.farm;
import java.awt.geom.Rectangle2D;

public class entity {
    int x;
    int y;
    int width;
    int height;

    entity(){
        this.x = 25;
        this.y = 25;
        this.width = 10;
        this.height = 10;
    }
    entity(int x,int y, int w,int h){
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
    }

    public Rectangle2D getBound(){
        return (new Rectangle2D.Double(x,y,this.width,this.height));
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getHeight() {
        return height;
    }
    
    public int getWidth() {
        return width;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    


    
}
