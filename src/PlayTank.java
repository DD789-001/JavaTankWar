import java.util.Vector;
public class PlayTank extends Tank {
    static Vector<PlayTank> PlayTanks = new Vector<>();//用Vector类存储玩家坦克类（单机一般就一个）
    private static Vector<Bullet> PlayBullets = new Vector<>();//用于存储玩家发射出的子弹
    private Boolean alive = true;//玩家生命

    public PlayTank(int x, int y,int direction) {
        super(x, y, direction);
        PlayTanks.add(this);
        setSpeed(6);
    }

    public static Vector<Bullet> getBullets() {
        return PlayBullets;
    }


    public void PlayShot() {
        Bullet bullet = new Bullet(getX(),getY());
        bullet.setDirection(getDirection());
        PlayBullets.add(bullet);
        new Thread(bullet).start();
    }

    public Boolean getAlive() {
        return alive;
    }

    public void setAlive(Boolean alive) {
        this.alive = alive;
    }


}
