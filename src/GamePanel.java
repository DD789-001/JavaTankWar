import javax.swing.*;
import java.awt.*;

import static java.lang.Thread.sleep;
//游戏绘制类

public class GamePanel extends JPanel implements Runnable{
    PlayTank playerTank;

    public GamePanel() {
        System.out.println("游戏面板初始化成功！");

    }

    public void paint(Graphics g) {
        //===================绘制游戏背景颜色============
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, 1000, 620);
        g.setColor(Color.GRAY);  // 中等灰色（RGB:128,128,128）
        g.fillRect(0, 620, 1000, 200);
        //============================================0


    }

    public void drawPlayerTank(Graphics g) {

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
