import java.util.Vector;

public class AiTank extends Tank implements Runnable{
    static int size = 4;//设置Vector大小，即场上坦克存在数量的最大值
    static Vector<AiTank> aiTanks = new Vector<>();//创建Vector存储ai坦克对象，保证线程安全
    private Boolean isLive = true;

    public AiTank(int x, int y,int direction) {
        super(x, y, direction);
        aiTanks.add(this);
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

    @Override
    public void run() {
        while(getLive()){

        }
    }
}
