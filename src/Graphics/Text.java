package Graphics;

import java.awt.*;

/**
 * Klasa Text obsługuje wyświetlanie tekstu w oknie
 */
public class Text {
    /**
     * Metoda wyświetla podany tekst
     *
     * @param graphics parametr typu Graphics, aby móc wyświetlić tam tekst
     * @param text     podany tekst do wyświetlenia
     * @param xPos     położenie na osi x tekstu
     * @param yPos     położenie na osi y tekstu
     * @param center   czy tekst wyśrodkowany
     * @param c        kolor tekstu
     * @param font     czcionka tekstu
     */
    public static void drawString(Graphics graphics, String text, int xPos, int yPos,
                                  boolean center, Color c, Font font) {
        graphics.setColor(c);
        graphics.setFont(font);
        int x = xPos;
        int y = yPos;
        if (center) {
            FontMetrics fm = graphics.getFontMetrics(font);
            x = xPos - fm.stringWidth(text) / 2;
            y = (yPos - fm.getHeight() / 2) + fm.getAscent();
        }
        graphics.drawString(text, x, y);
    }

}
