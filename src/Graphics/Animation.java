package Graphics;

import java.awt.image.BufferedImage;

/**
 * Klasa Animation odpowiedzialna za animacje
 */
public class Animation {

    private int speed = 0, index = 0;
    private long timer = 0, lastTime = 0;
    private final BufferedImage[] frames;

    /**
     * Konstruktor klasy Animation
     *
     * @param speed  szybkość zmiany grafik
     * @param frames dostępne grafiki do zmiany
     */
    public Animation(int speed, BufferedImage[] frames) {
        this.speed = speed;
        this.frames = frames;
        index = 0;
        timer = 0;
        lastTime = System.currentTimeMillis();
    }

    /**
     * Metoda do aktualizowania parametrów
     */
    public void tick() {
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if (timer > speed) {
            index++;
            timer = 0;
            if (index >= frames.length) {
                index = 0;
            }
        }
    }

    public BufferedImage getCurrentFrame() {
        return frames[index];
    }
}
