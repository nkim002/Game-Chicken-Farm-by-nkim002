package game.chicken.farm;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.awt.geom.Rectangle2D;

public class chicken extends entity {
    Image img;
    int hit = 10;
    int point = 50;
    
    int originalG = 0;
    int g = 0;

    int w = 92;
    int h = 80;

    chicken(int x){
        this.width = w;
        this.height  = h;
        this.y = -height;
        this.x = x;

        String imageLocation = "img/ck.gif";
        URL imageURL = this.getClass().getResource(imageLocation);
        img = Toolkit.getDefaultToolkit().getImage(imageURL);
        dorpThread.start();
    }

    Thread dorpThread = new Thread(new Runnable() {
        public void run() {
            while (true) {
                y += g;
                try {
                    Thread.sleep(40);
                } catch (InterruptedException e) {
                }
            }
        }
    });

    public void setG(int g) {
        this.g = g;
    }
    public Image getImage() {
        return img;
    }

    @Override
    public Rectangle2D getBound(){
        return (new Rectangle2D.Double(x+25,y+50,w-50,h-50));
    }

}
