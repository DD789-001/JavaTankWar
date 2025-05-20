import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

/*
 * 游戏Panel大小为1000*620
 * 坦克长为60 宽为40
 * */
public class Tank extends MoableObject implements RectangleDemo {
    //====设置四个角顶点用于碰撞检测
    private Point point01 = new Point(0,0);//左上顶点
    private Point point02 = new Point(0,0);//左下顶点
    private Point point03 = new Point(0,0);//右上顶点
    private Point point04 = new Point(0,0);//右下顶点
    ArrayList<Point> points = new ArrayList<>();
    //=======================
    private int direction = 0;//设置默认方向
    private int speed = 4;//设置默认速度

    public Tank(int x, int y,int direction) {
        super(x, y);
        this.direction = direction;
        point01 = new Point(x - 20,y + 30);
        point02 = new Point(x - 20,y - 30);
        point03 = new Point(x + 20,y + 30);
        point04 = new Point(x + 20,y - 30);
        points.add(point01);
        points.add(point02);
        points.add(point03);
        points.add(point04);
    }
    //=========碰撞检测=============
    //与子弹检测
    @Override
    public Boolean checkCollision(Bullet bullet){
        return false;
    }
    //与坦克检测
    @Override
    public Boolean checkCollision(Tank tank){
        return false;
    }

    //===========坦克移动===================
    //注意坦克移动不可以超出游戏画面
    public void moveUP() {
        if(getY() >= (0 + 20)) {
            setY(getY() - speed);
        }
    }
    public void moveDOWN() {
        if(getY() <= (620 - 60)) {
            setY(getY() + speed);
        }
    }
    public void moveLEFT() {
        if(getX() >= (0 +30)) {
            setX(getX() - speed);
        }
    }
    public void moveRIGHT() {
        if(getX() <= (1000 -  30)) {
            setX(getX() + speed);
        }
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Point getPoint01() {
        return point01;
    }

    public Point getPoint02() {
        return point02;
    }

    public Point getPoint03() {
        return point03;
    }

    public Point getPoint04() {
        return point04;
    }
}
