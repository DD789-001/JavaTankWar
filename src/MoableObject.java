//所有可移动物体的父类
//坐标轴在左上角


public class MoableObject {
    private int x;
    private int y;
    public MoableObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
