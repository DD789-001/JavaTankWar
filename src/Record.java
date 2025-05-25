public class Record {
    private int killCount = 0;
    private int deaths = 0;

    public void setKillCount(int number){
        killCount += number;
    }

    public int getKillCount() {
        return killCount;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int number) {
        this.deaths += number;
    }
}
