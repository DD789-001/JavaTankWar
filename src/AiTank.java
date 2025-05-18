import java.util.Vector;

public class AiTank extends Tank {
    int size = 4;//设置Vector大小，即场上坦克存在数量的最大值
    Vector<AiTank> aiTanks = new Vector<>();//创建Vector存储ai坦克对象，保证线程安全

    public AiTank(int x, int y,int direction) {
        super(x, y, direction);
        aiTanks.add(this);
    }

    public Vector<AiTank> getAiTanks() {
        return aiTanks;
    }
}
