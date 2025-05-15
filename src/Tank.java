public class Tank extends MoableObject{
    int point01;//左上顶点
    int point02;//左下顶点
    int point03;//右上顶点
    int point04;//右下顶点

    public Tank(int x, int y) {
        super(x, y);
    }

    //与矩形可移动物体的碰撞检测
    public Boolean RCollision(MoableObject MO){
        return false;
    }



}
