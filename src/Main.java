import javax.swing.*;

public class Main extends JFrame {
    GamePanel gp;

    public Main(){
        gp = new GamePanel();
        new Thread(gp).start();
        this.add(gp);
        this.setTitle("Tank War");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//使退出按钮响应程序关闭事件
        this.setSize(1000, 618+150);//多出来的150用于操控界面以及分数记录
        this.setVisible(true);
    }

    public static void main(String[] args) {
        Main frame = new Main();
    }
}
