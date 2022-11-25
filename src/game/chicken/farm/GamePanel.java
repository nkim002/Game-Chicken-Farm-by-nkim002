package game.chicken.farm;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class GamePanel extends JPanel{
    
    private ImageIcon BGmenu = new ImageIcon(this.getClass().getResource("img/BGmenu.png"));
    private ImageIcon logo = new ImageIcon(this.getClass().getResource("img/logo.png"));
    private ImageIcon start = new ImageIcon(this.getClass().getResource("img/start.png"));
    private ImageIcon exit = new ImageIcon(this.getClass().getResource("img/exit.png"));
    
    public JButton BStart = new JButton(start);
    public JButton BExit  = new JButton(exit);

    Sound sound = new Sound();
    
    public static void main(String[] args) {
    }

    public GamePanel() {
        setLayout(null);
        
        BStart.setBounds(175, 310, 150, 50);
        fixImgButton(BStart);
        add(BStart);
        
        BExit.setBounds(175,380,150,50);
        fixImgButton(BExit);
        add(BExit);        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        g.drawImage(BGmenu.getImage(), 0, 0, 500,500,this);
        g.drawImage(logo.getImage(), 0, 0, 500,500,this);
        /*
         * เพิ่ม คะแนนสูงสุด และชื่อเกม
         * 
         */
        g.setColor(Color.WHITE);
        g.setFont(new Font("Courier",Font.CENTER_BASELINE,10));		
        g.drawString("GAME : Chicken Farm BY KIM",10,490);
    }

    void fixImgButton(JButton b)//การแก้ไขปุ่มกด เย้ๆ
    {
        b.setBorderPainted(false);
        b.setFocusPainted(false);
        b.setContentAreaFilled(false);
    }

    public void playMusic(int i){
        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public void stopMusic(int i){
        sound.stop();
    }

    public void playEF(int i){
        sound.setFile(i);
        sound.play();
    }

    
}
