import javax.swing.*;
import java.awt.*;

import static java.lang.Thread.sleep;
//游戏绘制类
public class GamePanel extends JPanel implements Runnable{
    PlayTank playerTank;//定义玩家类

    public GamePanel() {
        playerTank = new PlayTank(500,400);//初始化玩家类
        System.out.println("游戏面板初始化成功！");
    }

    public void paint(Graphics g) {
        //===================绘制游戏背景颜色============
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, 1000, 620);
        //============================================0


    }

    //绘制坦克，首先获取x，y，p用于区分玩家与ai坦克,d表示玩家坦克朝向（东南西北）
    public void drawPlayerTank(int x,int y,Graphics g,int p,int direction) {
        switch (p) {
            case 1:
                //======蓝色表示玩家坦克======
                g.setColor(Color.BLUE);
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

    @Override
    public void run() {
        //===不断刷新画面以达到动画效果======================
        while(true) {
            repaint();
            try {
                sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
