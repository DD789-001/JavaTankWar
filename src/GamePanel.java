import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
/*
* 0北1东2南3西
* 大小1000*620
* */

import static java.lang.Thread.sleep;
//游戏绘制类
public class GamePanel extends JPanel implements Runnable, KeyListener {
    //===============唐诗曼波头像加载============="/resource/唐诗曼波头像.jpg"
    Image img = getImg("/resource/唐诗曼波头像.jpg");
    //==============耄耋头像加载===================
    Image img01 = getImg("/resource/耄耋头像.jpg");
    PlayTank playerTank;//定义玩家类
    Record playerRecord;//定义玩家记录类
    private ArrayList<Explosion> explosions = new ArrayList<>();

    public GamePanel() {
        AudioPlayer.playBGM();
        playerTank = new PlayTank(960,540,0);//初始化玩家类
        playerRecord = new Record();
        System.out.println("游戏面板初始化成功！");
        createAiTank();
    }

    public void paint(Graphics g) {
        //===================绘制游戏背景颜色============
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, 1000, 620);
        g.setColor(Color.white);
        g.fillRect(0,620,1000,200);
        //================绘制游戏地图==================
        //================绘制玩家得分,被击中次数==================
        // 设置字体（Arial、加粗、24号）
        g.setColor(Color.black);
        g.setFont(new Font("Dialog", Font.BOLD, 24));
        String news = "玩家得分： " + playerRecord.getKillCount();
        g.drawString(news,10,640);
        String news1 = "玩家被击中: " + playerRecord.getDeaths();
        g.drawString(news1,10,670);
        //================绘制Ai坦克===================
        synchronized (AiTank.getAiTanks()) {
            Iterator<AiTank> it = AiTank.getAiTanks().iterator();
            while (it.hasNext()) {
                AiTank aiTank = it.next();
                //遍历玩家的子弹集合，判断是否被玩家的子弹击中
                synchronized (PlayTank.getBullets()){
                    Iterator<Bullet> bit = PlayTank.getBullets().iterator();
                    while (bit.hasNext()) {
                        Bullet bullet = bit.next();
                        if(bullet.isHitTank(aiTank)) aiTank.setLive(false);
                        if(!bullet.getLive()){
                            bit.remove();
                        }
                    }
                }
                //判断坦克是否存活，若死亡则从集合中删除
                if (aiTank.getLive()) {
                    //存活则绘制
                    drawTank(g,2,aiTank);
                    //遍历aiTank的子弹集合，判断是否击中玩家坦克
                    if (aiTank.getAiTankBullets() != null && !aiTank.getAiTankBullets().isEmpty()) {
                        Iterator<Bullet> aiTankBullets = aiTank.getAiTankBullets().iterator();
                        while (aiTankBullets.hasNext()) {
                            Bullet bullet = aiTankBullets.next();
                            //判断子弹是否击中玩家
                            if (bullet.isHitTank(playerTank)) {playerTank.setAlive(false);}
                            if (bullet.getLive()){
                                drawBullet(g,2,bullet);
                            } else{
                                aiTankBullets.remove();
                                System.out.println("子弹：x = " + bullet.getX()+ " " +" y = "+bullet.getY());
                                System.out.println("AiTank的子弹被从集合中移除");
                            }
                        }
                    }
                    if (img01 != null) {
                        g.drawImage(img01, aiTank.getX() - 10, aiTank.getY() - 10, 20, 20, this);
                    }
                }else {
                    //记录击杀得分
                    playerRecord.setKillCount(1);
                    //在爆炸特效集合中添加死亡坦克的坐标
                    explosions.add(new Explosion(aiTank));
                    //死亡则移除
                    it.remove();
                }
            }
        }
        //===================绘制玩家坦克=====================0
        Explosion playerExplosion = new Explosion(playerTank);
        //判断玩家是否死亡
        if(!playerTank.getAlive()){
            playerRecord.setDeaths(1);
            System.out.println("玩家死亡！,玩家生命为" + playerTank.getAlive());
            playerTank.setAlive(true);
        }
        drawTank(g,1,playerTank);
        if (img != null) {
            g.drawImage(img, playerTank.getX() - 12, playerTank.getY() - 12, 25, 25, this);
        }
        //==================绘制玩家子弹================
        //遍历玩家的子弹集合，若有子弹则绘制
        synchronized (playerTank.getBullets()) {
            Iterator<Bullet> it = playerTank.getBullets().iterator();
            while (it.hasNext()) {
                Bullet bullet = it.next();
                if (bullet.getLive()) {
                    drawBullet(g,1,bullet);
                }else {
                    //若子弹生命为false则移除
                    System.out.println("玩家子弹移除了");
                    it.remove();
                }
            }
        }
        //=================绘制爆炸效果==================
        drawExplosions(g);
        //============补充ai坦克=======================
        if(AiTank.getAiTanks().isEmpty())createAiTank();
    }
    public synchronized void drawExplosions(Graphics g) {
        // 绘制所有爆炸效果
        for (int i = 0; i < explosions.size(); i++) {
            Explosion e = explosions.get(i);
            if (e.isAlive()) {
                e.draw(g);
                System.out.println("绘制了爆炸特效");
            } else {
                explosions.remove(i--); // 移除已完成的爆炸
            }
        }

    }

    //==============================绘制坦克=========================================
    //绘制坦克，首先获取x，y，p用于区分玩家与ai坦克,d表示玩家坦克朝向（东南西北）,1表示玩家坦克，2表示ai坦克
    public void drawTank(Graphics g,int p,Tank tank) {
        int x = tank.getX();
        int y = tank.getY();
        int direction = tank.getDirection();

        switch (p) {
            case 1:
                //======黄色表示玩家坦克======
                g.setColor(Color.YELLOW);
                break;
            case 2:
                //======红色表示ai坦克======
                g.setColor(Color.RED);
                break;
            default:
                break;
        }
        //================选择坦克朝向==========================
        switch (direction) {
            case 0://面朝方向上
                g.fill3DRect(x - 20,y - 30,10,60,false);
                g.fill3DRect(x + 10, y - 30,10,60,false);
                g.fill3DRect(x - 10, y - 20,20,40,false);
                g.fillOval(x - 10, y - 10,20,20);
                g.drawLine(x , y - 30, x , y);
                break;
            case 1://面朝方向右
                g.fill3DRect(x - 30,y - 20,60,10,false);//左轮
                g.fill3DRect(x - 30, y + 10,60,10,false);
                g.fill3DRect(x - 20, y - 10,40,20,false);
                g.fillOval(x - 10, y - 10,20,20);
                g.drawLine(x + 30, y, x, y);
                break;
            case 2://面朝方向下
                g.fill3DRect(x - 20,y - 30,10,60,false);
                g.fill3DRect(x + 10, y - 30,10,60,false);
                g.fill3DRect(x - 10, y - 20,20,40,false);
                g.fillOval(x - 10, y - 10,20,20);
                g.drawLine(x , y + 30, x, y);
                break;
            case 3://面朝方向左
                g.fill3DRect(x - 30,y - 20,60,10,false);//左轮
                g.fill3DRect(x - 30, y + 10,60,10,false);
                g.fill3DRect(x - 20, y - 10,40,20,false);
                g.fillOval(x - 10, y - 10,20,20);
                g.drawLine(x - 30, y, x, y);
                break;
            default:
        }
    }

    //==========================绘制子弹==================================
    //p用于区分ai坦克与玩家坦克的子弹
    public void drawBullet(Graphics g,int p,Bullet bullet) {
        //选择子弹类型
        switch (p) {
            case 1:
                g.setColor(Color.YELLOW);
                break;
            case 2:
                g.setColor(new Color(159, 14, 14));
                break;
        }
        g.fillOval(bullet.getX() - 5,bullet.getY() - 5,5,5);
    }
    //==================创建ai坦克================================
    public void createAiTank() {
        for(int i = 0; i < AiTank.size; i++) {
            AiTank aiTank = new AiTank((40 + i * 100), 50, 2);
            AiTank.getAiTanks().add(aiTank);
            aiTank.getPlayTank(playerTank);
            new Thread(aiTank).start();
            new Thread(new AiTankShot(aiTank)).start();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                playerTank.setDirection(0);
                if(playerTank.getY() >  0) {
                    playerTank.moveUP();
                }
                break;
            case KeyEvent.VK_S:
                playerTank.setDirection(2);
                if(playerTank.getY() < 710) {
                    playerTank.moveDOWN();
                }
                break;
            case KeyEvent.VK_A:
                playerTank.setDirection(3);
                if(playerTank.getX() > 0) {
                    playerTank.moveLEFT();
                }
                break;
            case KeyEvent.VK_D:
                playerTank.setDirection(1);
                if(playerTank.getX() < 1000) {
                    playerTank.moveRIGHT();
                }
                break;
            case KeyEvent.VK_J:
                AudioPlayer.playShot();
                playerTank.PlayShot();
                break;
        }

        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        //===不断刷新画面以达到动画效果======================
        while(true) {
            repaint();
            try {
                sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Image getImg(String filePath) {
        Image img; // 从资源目录加载[7](@ref)
        {
            try {
                img = ImageIO.read(getClass().getResourceAsStream(filePath));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        ImageIcon icon = new ImageIcon(img);
        return icon.getImage();
    }
}
