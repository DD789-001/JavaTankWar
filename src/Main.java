import javax.swing.*;
import java.awt.event.KeyAdapter;

public class Main extends JFrame {
    GamePanel gp;

    public Main(){
        gp = new GamePanel();
        new Thread(gp).start();
        this.add(gp);
        this.addKeyListener(gp);
        this.setTitle("Tank War");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//使退出按钮响应程序关闭事件
        this.setSize(1000, 718);
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        Main frame = new Main();
    }
}
