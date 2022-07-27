package graphics.statics;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class StaticSounds {
    private static final Media mainTheme = new Media(StaticSounds.class.getResource("maintheme.mp3").toString());
    private static final MediaPlayer mainThemePlayer = new MediaPlayer(mainTheme);
    private static boolean isPlaying = false;
    //private static final Media tileHover = new Media("file:src/main/resources/sounds/aaa.ogg");
    private static final Media buttonHover = new Media(StaticSounds.class.getResource("aaa.mp3").toString());
    //public static void playButtonHover() {
    //    new MediaPlayer(buttonHover).play();
    //}
//
    //public static void playMainTheme() {
    //    new MediaPlayer(mainTheme).play();
    //}

    public static void playTileHover () {
        //new MediaPlayer(tileHover).play();
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
