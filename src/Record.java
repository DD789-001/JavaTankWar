public class Record {
    private int killCount = 0;

    public void setKillCount(int number){
        killCount += number;
    }

    public int getKillCount() {
        return killCount;
    }
}
