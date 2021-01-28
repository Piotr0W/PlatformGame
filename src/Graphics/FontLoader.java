package Graphics;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

/**
 * Klasa FontLoader obsługuje czcionkę
 */
public class FontLoader {
    /**
     * Metoda wczytuje zewnętrzną czcionkę
     *
     * @param path ścieżka do pliku czcionki
     * @param size wielkość nowej czcionki
     * @return nowa czcionka typu Font albo null
     */
    public static Font loadFont(String path, float size) {
        try {
            return Font.createFont(Font.TRUETYPE_FONT, new File(path)).deriveFont(Font.PLAIN, size);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

}
