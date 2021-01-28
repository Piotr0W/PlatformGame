package Graphics;

import java.awt.image.BufferedImage;

/**
 * Klasa SpriteSheet obsługuje wczytywanie jednej grafiki z wieloma mniejszymi
 */
public class SpriteSheet {

    private BufferedImage sheet;

    /**
     * Konstruktor klasy SpriteSheet
     *
     * @param sheet ustawia operacje dla podanej zmiennej typu BufferedImage
     */
    public SpriteSheet(BufferedImage sheet) {
        this.sheet = sheet;
    }

    /**
     * Metoda pozyskuje wybraną część grafiki z jednej większej grafiki
     *
     * @param x      położenie wzdłuż osi x tej konkretnej grafiki
     * @param y      położenie wzdłuż osi y tej konkretnej grafiki
     * @param width  szerokość tej konkretnej grafiki
     * @param height wysokość tej konkretnej grafiki
     * @return wybraną grafikę typu BufferedImage
     */
    public BufferedImage crop(int x, int y, int width, int height) {
        return sheet.getSubimage(x, y,
                width, height);
    }

    public int getWidth() {
        return sheet.getWidth();
    }

    public int getHeight() {
        return sheet.getHeight();
    }

}
