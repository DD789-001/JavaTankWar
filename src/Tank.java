public class Tank extends MoableObject{
    //====设置四个角顶点用于碰撞检测
    private int point01;//左上顶点
    private int point02;//左下顶点
    private int point03;//右上顶点
    private int point04;//右下顶点
    //=======================
    private int direction = 0;//设置默认方向
    private int speed = 4;//设置默认速度

    public Tank(int x, int y,int direction) {
        super(x, y);
        this.direction = direction;
    }

    //==============================
    public void moveUP() {
        setY(getY()-speed);
    }
    public void moveDOWN() {
        setY(getY()+speed);
    }
    public void moveLEFT() {
        setX(getX()-speed);
    }
    public void moveRIGHT() {
        setX(getX()+speed);
    }



    //与矩形可移动物体的碰撞检测
    public Boolean RCollision(MoableObject MO){
        return false;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getPoint01() {
        return point01;
    }

    public void setPoint01(int point01) {
        this.point01 = point01;
    }

    public int getPoint02() {
        return point02;
    }

    public void setPoint02(int point02) {
        this.point02 = point02;
    }

    public int getPoint03() {
        return point03;
    }

    public void setPoint03(int point03) {
        this.point03 = point03;
    }

    public int getPoint04() {
        return point04;
    }

    public void setPoint04(int point04) {
        this.point04 = point04;
    }
}
