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

    //判断是否击中坦克的方法
    public Boolean isHitTank(Tank tank){
        if((this.getX() >= tank.getPoint01().x) && (this.getY() <= tank.getPoint01().y) && (this.getX() <= tank.getPoint04().x) && (this.getY() >= tank.getPoint04().y)){
            this.isLive = false;
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void run(){
        while(isLive){
            try {
                moveDirection(direction);
                OutOfBounds();
                sleep(time);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("子弹线程结束");
    }

    public void OutOfBounds(){
        if(getX() < 0){
            System.out.println("子弹销毁了");
            setLive(false);
        }else if(getX() > 1000){
            System.out.println("子弹销毁了");
            setLive(false);
        }else if(getY() < 0){
            System.out.println("子弹销毁了");
            setLive(false);
        }else if(getY() > 620){
            System.out.println("子弹销毁了");
            setLive(false);
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
