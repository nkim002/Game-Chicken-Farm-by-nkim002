package game.chicken.farm;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics;
import javax.swing.*;

import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePlay extends JPanel implements ActionListener{
    private final ImageIcon map1 = new ImageIcon(this.getClass().getResource("img/map1.png"));
    private final ImageIcon map2 = new ImageIcon(this.getClass().getResource("img/map2.png"));
    private final ImageIcon map3 = new ImageIcon(this.getClass().getResource("img/map3.png"));
    private final ImageIcon map4 = new ImageIcon(this.getClass().getResource("img/map4.png"));
    private final ImageIcon map5 = new ImageIcon(this.getClass().getResource("img/map5.png"));
    private final ImageIcon map6 = new ImageIcon(this.getClass().getResource("img/map6.png"));
    private final ImageIcon map7 = new ImageIcon(this.getClass().getResource("img/map7.png"));
    private final ImageIcon map8 = new ImageIcon(this.getClass().getResource("img/map8.png"));
    private final ImageIcon map9 = new ImageIcon(this.getClass().getResource("img/map9.png"));

    private ImageIcon BGmenu2 = new ImageIcon(this.getClass().getResource("img/BGmenu2.png"));
    private ImageIcon start = new ImageIcon(this.getClass().getResource("img/start.png"));
    private ImageIcon exit = new ImageIcon(this.getClass().getResource("img/exit.png"));
    
    public JButton BStart = new JButton(start);
    public JButton BExit  = new JButton(exit);

    //egg
    public ArrayList<egg> egg = new ArrayList<>();
    public ArrayList<eggGolden> eggGoldens = new ArrayList<>();
    public ArrayList<chicken> chickens = new ArrayList<>();
    public ArrayList<chicken> chickens2 = new ArrayList<>();
    public ArrayList<chicken> chickens3 = new ArrayList<>();

    Basket basket = new Basket(158,389,185,111,2,3);
    GamePanel gamePanel = new GamePanel();

    public int NumEggGolden = 0;

    public boolean newScore = false ;
    public boolean startGame = false ;
    public boolean Alive = true ;
    public int times = 0;
    public double fps = 0;
    public int MAP = 0;
    int s = 0;

    public static void main(String[] args) {
    }

    GamePlay(){
        this.setFocusable(true);
        this.setLayout(null);

        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_A:
                        basket.setStill(false);
                        basket.setLeft(true);
                        break;
                    case KeyEvent.VK_D:
                        basket.setStill(false);
                        basket.setRight(true);
                        break;
                }
            }
            public void keyReleased(KeyEvent e) {
                basket.setStill(true);
            } 
        });



        // หน้าเมนู การกดปุ่ม
        BStart.addActionListener(this);
        BExit.addActionListener(this);

        BackUPThread.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(Alive){//เริ่มเกม
            if(basket.live <=0 || basket.score < 0){
                Alive = false ;
                times = -1000;
            }

            if(times<=50 ){
                g.drawImage(map1.getImage(), 0, 0, 500, 500, this);
                MAP = 1;
            }else if(times<=100 ){
                g.drawImage(map2.getImage(), 0, 0, 500, 500, this);
                MAP = 2;
            }else if(times<=150 ){
                g.drawImage(map3.getImage(), 0, 0, 500, 500, this);
                MAP = 3;
            }else if(times<=200 ){
                g.drawImage(map4.getImage(), 0, 0, 500, 500, this);
                MAP = 4;
            }else if(times<=250 ){
                g.drawImage(map5.getImage(), 0, 0, 500, 500, this);
                MAP = 5;
            }else if(times<=300 ){
                g.drawImage(map6.getImage(), 0, 0, 500, 500, this);
                MAP = 6;
            }else if(times<=350 ){
                g.drawImage(map7.getImage(), 0, 0, 500, 500, this);
                MAP = 7;
            }else if(times<=400 ){
                g.drawImage(map8.getImage(), 0, 0, 500, 500, this);
                MAP = 8;
            }else{
                g.drawImage(map9.getImage(), 0, 0, 500, 500, this);
                MAP = 9;
                if(times % 100 == 0){
                    MAP++;
                }
            }
    
            g.drawImage(basket.getImage(), basket.x, basket.y, basket.width, basket.height, this);

            this.FixBug();

            this.spawnChicken(chickens, g);
            this.spawnChicken(chickens2, g);
            this.spawnChicken(chickens3, g);
            this.spawnEgg(egg, g);
            this.spawnEggGolden(eggGoldens, g);

            updateScore();

            g.setFont(new Font("Agency FB Bold", Font.PLAIN, 25));
            g.setColor(Color.WHITE);
            g.drawString("HP : " + basket.live, 10, 30);
            g.drawString("HIGHT SCORE : " + basket.HeightScore, 10, 55);
            g.drawString("SCORE : " + basket.score, 10, 80);
            
        }else{//======================================================================  ตาย

            g.drawImage(BGmenu2.getImage(), 0, 0, 500,500,this);

            // ================ score ================
            g.setFont(new Font("Agency FB Bold", Font.CENTER_BASELINE, 50));
            g.setColor(Color.WHITE);

            if(newScore && s==0){
                gamePanel.playEF(4);
                s++;
            }

            if(newScore){
                g.drawString("NEWWW... HIGHT SCORE !!!!!!" ,15, 66);
                g.drawString(""+basket.HeightScore , 15, 136);
            }else{
                g.drawString("YOUR SCORE" , 15, 66);
                g.drawString(""+basket.score , 15, 126);
            }
            
            BStart.setBounds(330, 430, 150, 50);
            fixImgButton(BStart);
            add(BStart);
            
            BExit.setBounds(180,430,150,50);
            fixImgButton(BExit);
            add(BExit);

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == BStart){//กดเริ่มเกม
            gamePanel.playEF(5);
            System.out.println("new Game");
            resetGame();
            Alive = true ;
        }

        if(e.getSource() == BExit){//กดออกเกม
            gamePanel.playEF(5);
            System.exit(0);
        }

        this.validate();
        this.repaint();
    }

    public int posX(){
        Random rn = new Random();
        int min =100;
        int max =400;

        int px = rn.nextInt(max - min +1 ) + min;
        return px;
    }

    public boolean Intersect(Rectangle2D a, Rectangle2D b) {
        return (a.intersects(b));
    }

    public void setSpeedEgg(ArrayList<egg> e,int g){
        for(int i=0;i<e.size();i++){
            e.get(i).setG(g);
        }
    }
    public void setSpeedEggGolden(ArrayList<eggGolden> e,int g){
        for(int i=0;i<e.size();i++){
            e.get(i).setG(g);
        }
    }
    public void setSpeedChicken(ArrayList<chicken> e,int g){
        for(int i=0;i<e.size();i++){
            e.get(i).setG(g);
        }
    }

    public void spawnEgg(ArrayList<egg> e, Graphics g){
        //วาดไข่
        for (int i = 0; i < e.size(); i++) {
            g.drawImage(e.get(i).getImage()
            , e.get(i).getX(), e.get(i).getY()
            , e.get(i).width, e.get(i).height
            , this);
            
        }

        //ไข่ชนตะกร้า
        for (int i = 0; i < e.size(); i++) {
            if (Intersect(e.get(i).getBound(),basket.getBound() )) {
                // for(int y=0;y<1000;y++){
                // g.setFont(new Font("Agency FB Bold", Font.CENTER_BASELINE, 10));
                // g.setColor(Color.GREEN);

                // g.drawString("SCORE +"+e.get(i).point *MAP , basket.x+20, basket.y-20);
                // g.drawString("HP + 1" , basket.x+20, basket.y-10);
                // }

                e.get(i).setY(e.get(i).getY()+10);
                basket.score += e.get(i).point *MAP;
                basket.live += 1;
                e.remove(i);

                gamePanel.playEF(3);
            }
            
        }

        for(int i=0;i<e.size();i++){
            if(e.get(i).getY() >= 500){
                basket.live -= e.get(i).hit*MAP;
                e.remove(i);

                gamePanel.playEF(1);
            }
        }

    }
    public void spawnEggGolden(ArrayList<eggGolden> e, Graphics g){
        //วาดไข่
        for (int i = 0; i < e.size(); i++) {
            g.drawImage(e.get(i).getImage()
            , e.get(i).getX(), e.get(i).getY()
            , e.get(i).width, e.get(i).height
            , this);

            
        }

        //ไข่ชนตะกร้า
        for (int i = 0; i < e.size(); i++) {
            if (Intersect(e.get(i).getBound(),basket.getBound() )) {
                // for(int y=0;y<10;y++){
                // g.setFont(new Font("Agency FB Bold", Font.CENTER_BASELINE, 10));
                // g.setColor(Color.GREEN);

                // g.drawString("SCORE +"+e.get(i).point *MAP , basket.x+20, basket.y-20);
                // g.drawString("HP +"+e.get(i).hit*MAP , basket.x+20, basket.y-10);
                // }

                e.get(i).setY(e.get(i).getY()+10);
                basket.score += e.get(i).point*MAP;
                basket.live += e.get(i).hit*MAP;
                e.remove(i);
                
                gamePanel.playEF(3);
            }
            
        }

        for(int i=0;i<e.size();i++){
            if(e.get(i).getY() >= 500){
                basket.live -= e.get(i).hit*MAP;
                e.remove(i);
                
                gamePanel.playEF(1);
            }
        }
    }
    public void spawnChicken(ArrayList<chicken> e, Graphics g){

        //วาดไก่
        for (int i = 0; i < e.size(); i++) {
            g.drawImage(e.get(i).getImage()
            , e.get(i).getX(), e.get(i).getY()
            , e.get(i).width, e.get(i).height
            , this);
        }

        //ไก่ชนตะกร้า
        for (int i = 0; i < e.size(); i++) {
            if (Intersect(e.get(i).getBound(),basket.getBound() )) {
                e.get(i).setY(e.get(i).getY()+5);
                gamePanel.playEF(0);
                basket.live -= e.get(i).hit*MAP;
                e.remove(i);
                
                gamePanel.playEF(0);
            }
            
        }

    }

    public void FixBug(){
        overlapFix(chickens,chickens);
        overlapFix(chickens,chickens2);
        overlapFix(chickens,chickens3);

        overlapFix(chickens2,chickens2);
        overlapFix(chickens2,chickens3);

        overlapFix(chickens3,chickens3);

        overlapFixEgg(chickens,egg);
        overlapFixEgg(chickens2,egg);
        overlapFixEgg(chickens3,egg);

        overlapFixEggGolden(chickens,eggGoldens);
        overlapFixEggGolden(chickens,eggGoldens);
        overlapFixEggGolden(chickens,eggGoldens);
    }

    public void overlapFix(ArrayList<chicken> a,ArrayList<chicken> b){
        for (int i = 0; i < a.size(); i++) {
            for (int j = i+1; j < b.size(); j++){
                if (Intersect(a.get(i).getBound(),b.get(j).getBound() )) {
                    System.out.println("overlap chicken");
                    b.get(j).y += 10;
                }
            }
        }
    }
    public void overlapFixEgg(ArrayList<chicken> a,ArrayList<egg> b){
        for (int i = 0; i < a.size(); i++) {
            for (int j = i+1; j < b.size(); j++){
                if (Intersect(a.get(i).getBound(),b.get(j).getBound() )) {
                    System.out.println("overlap egg");
                    b.get(j).y += 20;
                }
            }
        }
    }
    public void overlapFixEggGolden(ArrayList<chicken> a,ArrayList<eggGolden> b){
        for (int i = 0; i < a.size(); i++) {
            for (int j = i+1; j < b.size(); j++){
                if (Intersect(a.get(i).getBound(),b.get(j).getBound() )) {
                    System.out.println("overlap eggGolden");
                    b.get(j).y += 20;
                }
            }
        }
    }

    public void fixImgButton(JButton b)//การแก้ไขปุ่มกด เย้ๆ
    {
        b.setBorderPainted(false);
        b.setFocusPainted(false);
        b.setContentAreaFilled(false);
    }

    public void resetGame(){
        this.requestFocusInWindow();
        newScore = false;

        System.out.println("reset!");

        for (int i=0;i<egg.size();i++){
            egg.remove(i);
        }
        for (int i=0;i<eggGoldens.size();i++){
            eggGoldens.remove(i);
        }
        for (int i=0;i<chickens.size();i++){
            chickens.remove(i);
        }
        for (int i=0;i<chickens2.size();i++){
            chickens2.remove(i);
        }
        for (int i=0;i<chickens3.size();i++){
            chickens3.remove(i);
        }



        times = -3;
        basket.live = basket.originallive;
        basket.speed = basket.originalSpeed;
        basket.score = 0 ;
        NumEggGolden = 0;
        s=0;
        
        remove(BExit);
        remove(BStart);
    }

    public void updateScore(){
        if (basket.score > basket.HeightScore){
            basket.HeightScore = basket.score ;
            newScore = true ;
            System.out.println("new HeightScore : "+basket.HeightScore);
            
        }
        
    }



//======================================  Thread  ========================================
    Thread BackUPThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true){
                //System.out.println("BACKUP! SLEEPP");
                try {
                    Thread.sleep(1000);
                    if(startGame){
                        //System.out.println("BACKUP! START");
                        FPSThread.start();
                        EventThread.start();
                        spawnEggThread.start();
                        spawnEggGoldenThread.start();
                        spawnChickenThread.start();
                        spawnChicken2Thread.start();
                        spawnChicken3Thread.start();
                        NumEggGolden =0;
                    }
                }catch(Exception e){
                }

            }
        }
    });
    Thread FPSThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (startGame){
                try {
                    Thread.sleep(1000/120);
                    basket.updatePos();
                    repaint();
                    fps+=1;
                    if(fps>=120){
                        times++;
                        System.out.println("FPS :"+fps+" |time"+times +" gold :" + NumEggGolden);
                        fps -= 120;
                    }
                }catch(Exception e){
                }

            }
        }
    });

    Thread EventThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (startGame){
                try {
                    Thread.sleep(1000);
                    if(times%50 == 0 && times>10){
                        NumEggGolden += 3;
                    }else if(times % 100 == 0 && times>10){
                        NumEggGolden += 10;
                    }else if(times % 500 == 0 && times>10) {
                        NumEggGolden += 20;
                    }else if(times % 100 == 0 && times>10){
                        NumEggGolden += 50;
                    }

                    if(basket.score >= -1 && basket.score < 500 ){
                        basket.speed = 2;
                    }else if(basket.score >= 500 && basket.score < 1000){
                        basket.speed = 3;
                    }else if(basket.score >= 1000 && basket.score < 1500){
                        basket.speed = 4;
                    }else if(basket.score >= 1500 && basket.score < 2000){
                        basket.speed = 5;
                    }else if(basket.score >= 2000 ){
                        basket.speed = 6;
                    }

                }catch(Exception e){
                }

            }
        }
    });

    Thread spawnEggThread = new Thread(new Runnable() {
        int rateSpawn = 6000; // ยิ่งน้อยยิ่งเกิดไว
        public void run() {
            while (startGame) {
                try {
                    //System.out.println("spawn");
                    Thread.sleep((long) (Math.random() * rateSpawn) + 250 );   
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(times<0){
                    setSpeedEgg(egg, 0);
                }else if(times > 0 && times <=50){       //=========== lv 1
                    egg.add(new egg( posX() ));
                    setSpeedEgg(egg, 8);
                    rateSpawn =6000;

                }else if(times > 50 && times <=100){    //=========== lv 2
                    egg.add(new egg( posX() ));
                    setSpeedEgg(egg, 10);
                    rateSpawn =5000;

                }else if(times > 100 && times <=150){   //=========== lv 3
                    egg.add(new egg( posX() ));
                    setSpeedEgg(egg, 12);
                    rateSpawn =4000;

                }else if(times > 150 && times <=200){   //=========== lv 4
                    egg.add(new egg( posX() ));
                    setSpeedEgg(egg, 14);
                    rateSpawn =3500;

                }else if(times > 200 && times <=250){   //=========== lv 5
                    egg.add(new egg( posX() ));
                    setSpeedEgg(egg, 16);
                    rateSpawn =3000;

                }else if(times > 250 && times <=300){   //=========== lv 6
                    egg.add(new egg( posX() ));
                    setSpeedEgg(egg, 18);
                    rateSpawn =2500;

                }else if(times > 300 && times <=350){   //=========== lv 7
                    egg.add(new egg( posX() ));
                    setSpeedEgg(egg, 20);
                    rateSpawn =2000;

                }else if(times > 350&& times <=400){    //=========== lv 8
                    egg.add(new egg( posX() ));
                    setSpeedEgg(egg, 22);
                    rateSpawn =1500;

                }else if(times >400){
                    egg.add(new egg( posX() ));         //=========== lv 9
                    setSpeedEgg(egg, 24);
                    rateSpawn =1000;
                }

            }
        }
    });

    Thread spawnEggGoldenThread = new Thread(new Runnable() {
        int rateSpawn =5000; // ยิ่งน้อยยิ่งเกิดไว
        public void run() {
            while (startGame) {
                try {
                    //System.out.println("spawn gold");
                    Thread.sleep((long) (Math.random() * rateSpawn) + 250 );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(times<0){
                    setSpeedEggGolden(eggGoldens, 0);
                }else if(times > 0 && times <=50){            //=========== lv 1

                }else if(times > 50 && times <=100){    //=========== lv 2
                    if(NumEggGolden>0){
                        NumEggGolden--;
                        eggGoldens.add(new eggGolden( posX() ));
                        setSpeedEggGolden(eggGoldens, 12);
                    }

                }else if(times > 100 && times <=150){   //=========== lv 3
                    if(NumEggGolden>0){
                        NumEggGolden--;
                        eggGoldens.add(new eggGolden( posX() ));
                        setSpeedEggGolden(eggGoldens, 14);
                    }

                }else if(times > 150 && times <=200){   //=========== lv 4
                    if(NumEggGolden>0){
                        NumEggGolden--;
                        eggGoldens.add(new eggGolden( posX() ));
                        setSpeedEggGolden(eggGoldens, 16);
                    }

                }else if(times > 200 && times <=250){   //=========== lv 5
                    if(NumEggGolden>0){
                        NumEggGolden--;
                        eggGoldens.add(new eggGolden( posX() ));
                        setSpeedEggGolden(eggGoldens, 18);
                    }

                }else if(times > 250 && times <=300){   //=========== lv 6
                    if(NumEggGolden>0){
                        NumEggGolden--;
                        eggGoldens.add(new eggGolden( posX() ));
                        setSpeedEggGolden(eggGoldens, 20);
                    }

                }else if(times > 300 && times <=350){   //=========== lv 7
                    if(NumEggGolden>0){
                        NumEggGolden--;
                        eggGoldens.add(new eggGolden( posX() ));
                        setSpeedEggGolden(eggGoldens, 22);
                    }

                }else if(times > 350&& times <=400){    //=========== lv 8
                    if(NumEggGolden>0){
                        NumEggGolden--;
                        eggGoldens.add(new eggGolden( posX() ));
                        setSpeedEggGolden(eggGoldens, 24);
                    }

                }else if(times>400){                                  //=========== lv 9
                    if(NumEggGolden>0){
                        NumEggGolden--;
                        eggGoldens.add(new eggGolden( posX() ));
                        setSpeedEggGolden(eggGoldens, 26);
                    }

                }
                
            }
        }
    });

    Thread spawnChickenThread = new Thread(new Runnable() {
        int rateSpawn = 2000; // ยิ่งน้อยยิ่งเกิดไว
        public void run() {
            while (startGame) {
                try {
                    //System.out.println("spawn chicken");
                    Thread.sleep((long) (Math.random() * rateSpawn) + 500 );
                    
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(times<0){
                    setSpeedChicken(chickens, 10);
                }else if(times > 0 && times <=50){            //=========== lv 1

                }else if(times > 50 && times <=100){    //=========== lv 2

                }else if(times > 100 && times <=150){   //=========== lv 3
                    chickens.add(new chicken( posX()));
                    setSpeedChicken(chickens, 9);
                    rateSpawn =2000;

                }else if(times > 150 && times <=200){   //=========== lv 4
                    chickens.add(new chicken( posX()));
                    setSpeedChicken(chickens, 11);
                    rateSpawn =1800;

                }else if(times > 200 && times <=250){   //=========== lv 5
                    chickens.add(new chicken( posX()));
                    setSpeedChicken(chickens, 13);
                    rateSpawn =1600;

                }else if(times > 250 && times <=300){   //=========== lv 6
                    chickens.add(new chicken( posX()));
                    setSpeedChicken(chickens, 15);
                    rateSpawn =1400;

                }else if(times > 300 && times <=350){   //=========== lv 7
                    chickens.add(new chicken( posX()));
                    setSpeedChicken(chickens, 17);
                    rateSpawn =1200;

                }else if(times > 350&& times <=400){    //=========== lv 8
                    chickens.add(new chicken( posX()));
                    setSpeedChicken(chickens, 19);
                    rateSpawn =1000;

                }else if (times>400){                                  //=========== lv ต
                    chickens.add(new chicken( posX()));
                    setSpeedChicken(chickens, 21);
                    rateSpawn =800;

                    if(times % 100 == 0 && rateSpawn > 400){
                        rateSpawn -= 100;
                    }

                }

            }
        }
    });
    Thread spawnChicken2Thread = new Thread(new Runnable() {
        int rateSpawn = 1800; // ยิ่งน้อยยิ่งเกิดไว
        public void run() {
            while (startGame) {
                try {
                    //System.out.println("spawn chicken");
                    Thread.sleep((long) (Math.random() * rateSpawn) + 500 );
                    
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                if(times<0){
                    setSpeedChicken(chickens2, 10);
                }else if(times > 0 && times <=50){            //=========== lv 1

                }else if(times > 50 && times <=100){    //=========== lv 2

                }else if(times > 100 && times <=150){   //=========== lv 3

                }else if(times > 150 && times <=200){   //=========== lv 4
                    chickens2.add(new chicken( posX()));
                    setSpeedChicken(chickens2, 11);
                    rateSpawn =1800;

                }else if(times > 200 && times <=250){   //=========== lv 5
                    chickens2.add(new chicken( posX()));
                    setSpeedChicken(chickens2, 13);
                    rateSpawn =1600;

                }else if(times > 250 && times <=300){   //=========== lv 6
                    chickens2.add(new chicken( posX()));
                    setSpeedChicken(chickens2, 15);
                    rateSpawn =1400;

                }else if(times > 300 && times <=350){   //=========== lv 7
                    chickens2.add(new chicken( posX()));
                    setSpeedChicken(chickens2, 17);
                    rateSpawn =1200;

                }else if(times > 350&& times <=400){    //=========== lv 8
                    chickens2.add(new chicken( posX()));
                    setSpeedChicken(chickens2, 19);
                    rateSpawn =1000;

                }else if(times > 400){                                  //=========== lv ต
                    chickens2.add(new chicken( posX()));
                    setSpeedChicken(chickens2, 21);
                    rateSpawn =800;

                    if(times % 100 == 0 && rateSpawn > 400){
                        rateSpawn -= 100;
                    }

                }

            }
        }
    });
    Thread spawnChicken3Thread = new Thread(new Runnable() {
        int rateSpawn = 1600; // ยิ่งน้อยยิ่งเกิดไว
        public void run() {
            while (startGame) {
                try {
                    //System.out.println("spawn chicken");
                    Thread.sleep((long) (Math.random() * rateSpawn) + 500 );
                    
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(times<0){
                    setSpeedChicken(chickens3, 10);
                }else if(times > 0 && times <=50){            //=========== lv 1

                }else if(times > 50 && times <=100){    //=========== lv 2

                }else if(times > 100 && times <=150){   //=========== lv 3

                }else if(times > 150 && times <=200){   //=========== lv 4

                }else if(times > 200 && times <=250){   //=========== lv 5
                    chickens3.add(new chicken( posX()));
                    setSpeedChicken(chickens3, 13);
                    rateSpawn =1600;

                }else if(times > 250 && times <=300){   //=========== lv 6
                    chickens3.add(new chicken( posX()));
                    setSpeedChicken(chickens3, 15);
                    rateSpawn =1400;

                }else if(times > 300 && times <=350){   //=========== lv 7
                    chickens3.add(new chicken( posX()));
                    setSpeedChicken(chickens3, 17);
                    rateSpawn =1200;

                }else if(times > 350&& times <=400){    //=========== lv 8
                    chickens3.add(new chicken( posX()));
                    setSpeedChicken(chickens3, 19);
                    rateSpawn =1000;

                }else if (times >400){                                  //=========== lv ต
                    chickens3.add(new chicken( posX()));
                    setSpeedChicken(chickens3, 21);
                    rateSpawn =800;

                    if(times % 100 == 0 && rateSpawn > 400){
                        rateSpawn -= 100;
                    }

                }

            }
        }
    });
}
