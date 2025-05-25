import java.util.Random;

import static java.lang.Thread.sleep;

public class AiTankShot implements Runnable{
    private AiTank Tank;

    public AiTankShot(AiTank Tank) {
        this.Tank = Tank;
    }

    public void aiTankShot() {
        int count = new Random().nextInt(2);
        if (count == 1) {
            Bullet bullet = new Bullet(Tank.getX(),Tank.getY());
            bullet.setDirection(Tank.getDirection());
            bullet.setTime(5);
            Tank.getAiTankBullets().add(bullet);
            bullet.setLive(true);
            new Thread(bullet).start();
            System.out.println("AiTankShot线程启动了,子弹生命为：" + bullet.getLive());
        }
    }

    @Override
    public void run() {
        while (Tank.getLive()) {
            aiTankShot();
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
