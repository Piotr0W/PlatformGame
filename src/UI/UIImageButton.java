package UI;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class UIImageButton extends UIObject {

    private BufferedImage[] images;
    private ClickListener clicker;

    public UIImageButton(double x, double y, int width, int height, BufferedImage[] images, ClickListener clicker) {
        super(x, y, width, height);
        this.images = images;
        this.clicker = clicker;
    }

    /**
     * Metoda do aktualizowania parametrów
     */
    @Override
    public void tick() {
    }

    /**
     * Metoda wyświetlenia w oknie zaktulizowanych obiektów
     *
     * @param graphics do wyświetlenia / renderowania
     */
    @Override
    public void render(Graphics graphics) {
        if (hovering)
            graphics.drawImage(images[1], (int) x, (int) y, width, height, null);
        else
            graphics.drawImage(images[0], (int) x, (int) y, width, height, null);
    }

    @Override
    public void onClick() {
        clicker.onClick();
    }

}
