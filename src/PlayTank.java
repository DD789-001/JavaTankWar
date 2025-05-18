import java.util.Vector;

public class PlayTank extends Tank {
    Vector<PlayTank> PlayTanks = new Vector<>();

    public PlayTank(int x, int y) {
        super(x, y);
        PlayTanks.add(this);
    }


}
