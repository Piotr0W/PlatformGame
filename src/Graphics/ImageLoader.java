package Graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Klasa ImageLoader wczytuje grafiki z pliku
 */
public class ImageLoader {
    /**
     * Metoda wczytuje grafikę z pliku
     *
     * @param path ścieżka do pliku z grafiką
     * @return grafika typu BufferedImage albo null
     */
    public static BufferedImage loadImage(String path) {
        File imageFile = new File(path);
        try {
            return ImageIO.read(imageFile);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}
