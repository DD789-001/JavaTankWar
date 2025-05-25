import java.awt.*;

import static java.lang.Thread.sleep;

public class Explosion  {
    private Tank tank;
    private int x, y;
    private int step = 0;
    private boolean alive = true;
    private final int[] diameters = {4, 7, 12, 18, 26, 32, 49, 30, 14, 6}; // 直径变化数组[4](@ref)

    public Explosion(Tank tank) {
        this.x = tank.getX() - diameters[0] / 2; // 居中定位
        this.y = tank.getY() - diameters[0] / 2;
    }

    public void draw(Graphics g) {
        try {
            sleep(7);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (step >= diameters.length) {
            alive = false;
            return;
        }

        // 动态颜色渐变
        Color[] colors = {
                new Color(255, 165, 0),   // 橙色
                new Color(255, 69, 0),    // 红橙色
                new Color(255, 215, 0)    // 金色
        };
        g.setColor(colors[step % colors.length]);

        // 绘制动态圆形
        int currentDia = diameters[step];
        g.fillOval(x - currentDia / 2, y - currentDia / 2, currentDia, currentDia);

        step++;
        System.out.println("绘制了爆炸特效");
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

}
