import java.util.Random;
import java.util.Vector;

import static java.lang.Thread.sleep;

public class AiTank extends Tank implements Runnable{
    static int size = 6;//设置Vector大小，即场上坦克存在数量的最大值
    static Vector<AiTank> aiTanks = new Vector<>();//创建Vector存储ai坦克对象，保证线程安全
    private Boolean isLive = true;
    private static final int UPDATE_INTERVAL = 200; // AI决策间隔(毫秒)
    private PlayTank target;
    private Vector<Bullet> aiTankBullets = new Vector<>();//aiTank发射出的子弹集合

    public AiTank(int x, int y,int direction) {
        super(x, y, direction);
        aiTanks.add(this);
    }
    //aiTank射击
//    public void aiTankShot() {
//        int count = new Random().nextInt(2);
//        if (count == 1) {
//            Bullet bullet = new Bullet(getX(), getY());
//            bullet.setDirection(getDirection());
//            aiTankBullets.add(bullet);
//            new Thread(bullet).start();
//        }
//    }

    public Vector<Bullet> getAiTankBullets() {
        return aiTankBullets;
    }

    public static Vector<AiTank> getAiTanks() {
        return aiTanks;
    }

    public Boolean getLive() {
        return isLive;
    }

    public void setLive(Boolean isLive) {
        this.isLive = isLive;
    }

    public void getPlayTank(PlayTank playTank) {
        target = playTank;
    }

    //aiTank自动向玩家寻路
    private void calculateMovement() {
        setSpeed(1);
        int time = 15;//控制移动速度
        Random rand = new Random();
        int direction = rand.nextInt(4);//0,1,2,3
        int xDistanceBefore = Math.abs(getX() - target.getX());//ai坦克与玩家x轴上的距离
        int yDistanceBefore = Math.abs(getY() - target.getY());//ai坦克与玩家y轴上的距离

        int count = rand.nextInt(300) + 20; //[5,15)
        //0北1东2南3西
        switch (direction) {
            case 0://向上
                if(yDistanceBefore >= Math.abs(getY() - target.getY() - getSpeed()) ) {
                    setDirection(0);
                    for (int i = 0; i < count; i++) {
                        try {
                            sleep(time);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        moveUP();
                    }
                }
                break;
            case 1://向右
                if(xDistanceBefore >= Math.abs(getX() - target.getX() + getSpeed()) ) {
                    setDirection(1);
                    for (int i = 0; i < count; i++) {
                        try {
                            sleep(time);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        moveRIGHT();
                    }
                }
                break;
            case 2://向下
                if(yDistanceBefore >= Math.abs(getY() - target.getY() + getSpeed()) ) {
                    setDirection(2);
                    for (int i = 0; i < count; i++) {
                        try {
                            sleep(time);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        moveDOWN();
                    }
                }
                break;
            case 3://向左
                if(xDistanceBefore >= Math.abs(getX() - target.getX() - getSpeed()) ) {
                    setDirection(3);
                    for (int i = 0; i < count; i++) {
                        try {
                            sleep(time);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        moveLEFT();
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void run() {
        while(getLive()){
            calculateMovement();
            try {
                sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}


