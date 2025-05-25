import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioPlayer {
    private static String fileName01 = "resource\\曼波不再.wav";//背景音乐曼波不在
    private static String fileName02 = "resource\\曼波.wav";//射击音效哈基米

    public static void playBGM() {
        playWAV(fileName01);
    }

    public static void playShot() {
        playWA(fileName02);
    }

    public static void playWAV(String filePath) {
        try {
            // 从资源文件夹加载音频文件
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(
                    AudioPlayer.class.getResourceAsStream(filePath)
            );

            // 获取音频格式并创建数据行
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            // 设置音量
            if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                float targetDB = -20.0f; // 设为-10dB
                volumeControl.setValue(targetDB);
            }

            // 设置循环播放（可选）
            clip.loop(Clip.LOOP_CONTINUOUSLY);

            // 开始播放
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void playWA(String filePath) {
        try {
            // 从资源文件夹加载音频文件
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(
                    AudioPlayer.class.getResourceAsStream(filePath)
            );

            // 获取音频格式并创建数据行
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);

            // 开始播放
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

