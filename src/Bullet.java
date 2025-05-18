import static java.lang.Thread.sleep;

public class Bullet extends MoableObject implements Runnable{
    private int direction = 0;//设置子弹移动方向（默认北），东南西北
    private final int speed = 1;//设置子弹速度
    private final long time = 1;//子弹线程的休眠时间
    private Boolean isLive = true;//子弹的生命，发生碰撞后为false

    public Bullet(int x, int y){
        super(x,y);
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public Boolean getLive() {
        return isLive;
    }

    public void setLive(Boolean live) {
        isLive = live;
    }

    @Override
    public void run(){
        while(isLive){
            try {
                moveDirection(direction);
                sleep(time);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void moveDirection(int direction){
        switch (direction){
            case 0:
                this.moveUP();
                break;
            case 1:
                this.moveRIGHT();
                break;
            case 2:
                this.moveDOWN();
                break;
            case 3:
                this.moveLEFT();
                break;
            default:
                break;
        }
    }

    //==============================
    public void moveUP() {
        setY(getY()-speed);
    }
    public void moveDOWN() {
        setY(getY()+speed);
    }
    public void moveLEFT() {
        setX(getX()-speed);
    }
    public void moveRIGHT() {
        setX(getX()+speed);
    }
}
