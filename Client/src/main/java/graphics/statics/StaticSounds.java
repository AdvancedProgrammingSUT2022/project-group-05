package graphics.statics;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class StaticSounds {
    private static final Media mainTheme = new Media(StaticSounds.class.getResource("maintheme.mp3").toString());
    private static final MediaPlayer mainThemePlayer = new MediaPlayer(mainTheme);
    private static boolean isPlaying = false;
    private static final Media buttonHover = new Media(StaticSounds.class.getResource("b.mp3").toString());
    private static final MediaPlayer buttonHoverPlayer = new MediaPlayer(buttonHover);
    static {
        mainThemePlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                mainThemePlayer.stop();
                mainThemePlayer.play();
            }
        });
    }

    public static void playTileHover () {
        MediaPlayer temp = new MediaPlayer(buttonHover);
        temp.play();//
    }

    public static void mainTheme () {
        if (!isPlaying) {
            mainThemePlayer.play();
            isPlaying = true;
        }
        else {
            mainThemePlayer.pause();
            isPlaying = false;
        }
    }
}
