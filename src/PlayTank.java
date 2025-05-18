import java.util.Vector;

public class PlayTank extends Tank {
    Vector<PlayTank> PlayTanks = new Vector<>();//用Vector类存储玩家坦克类（单机一般就一个）
    Vector<Bullet> PlayBullets = new Vector<>();//用于存储玩家发射出的子弹

    public PlayTank(int x, int y,int direction) {
        super(x, y, direction);
        PlayTanks.add(this);
        setSpeed(6);
    }

    public Vector<Bullet> getBullets() {
        return PlayBullets;
    }


    public void PlayShot() {
        Bullet bullet = new Bullet(getX(),getY());
        bullet.setDirection(getDirection());
        PlayBullets.add(bullet);
        new Thread(bullet).start();
    }
}
