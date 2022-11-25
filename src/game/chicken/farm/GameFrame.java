package game.chicken.farm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class GameFrame extends JFrame implements ActionListener{
    final static int FrameWidth = 516;
    final static int FrameHeight = 539;

    GamePanel menuPanel = new GamePanel();
    GamePlay gamePlay = new GamePlay();;


    
    public static void main(String[] args) {
        JFrame jf = new GameFrame();
        //โครงสร้างหน้าต่าง
        jf.setSize(FrameWidth,FrameHeight);
        jf.setTitle("Chickennnn Frame");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        jf.setResizable(false);
        jf.setLocationRelativeTo(null);
        
    }

    public GameFrame() {
        //โครงสร้างหน้าต่าง
        this.add(menuPanel);
        this.setSize(FrameWidth,FrameHeight);
        this.setTitle("Chickennnn Frame");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        // หน้าเมนู การกดปุ่ม
        menuPanel.BStart.addActionListener(this);
        menuPanel.BExit.addActionListener(this);

    }
    
    @Override//เวลากดปุ่ม บนเกม
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == menuPanel.BStart){//กดเริ่มเกม

            menuPanel.playMusic(6);
            menuPanel.playEF(5);
            
            System.out.println("START!!!!!!!!");
            gamePlay.startGame = true ;
            this.setLocationRelativeTo(null);
            this.remove(menuPanel);
            
            this.setSize(FrameWidth, FrameHeight);
            this.add(gamePlay);

            gamePlay.times = -1;
            gamePlay.requestFocusInWindow();
        }

        if(e.getSource() == menuPanel.BExit){//กดออกเกม
            menuPanel.playEF(5);
            System.exit(0);
        }

        this.validate();
        this.repaint();
    }
    
}
