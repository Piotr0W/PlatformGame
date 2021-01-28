package Audio;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Klasa Sounds obsługuje dźwięki w grze
 */
public class Sounds {
    private static boolean activeSound = true;

    public Sounds() {
    }

    /**
     * Statyczna metoda odpowiedzialna za odtworzenie dźwięku z podanej ścieżki
     *
     * @param path ścieżka do pliku dźwiękowego
     */
    public static void playMusic(String path) {
        if (activeSound) {
            InputStream music;
            try {
                music = new FileInputStream(new File(path));
                AudioStream audios = new AudioStream(music);
                AudioPlayer.player.start(audios);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error");
            }
        }
    }

    public static boolean isActiveSound() {
        return activeSound;
    }

    public static void setActiveSound(boolean activeSound) {
        Sounds.activeSound = activeSound;
    }
}