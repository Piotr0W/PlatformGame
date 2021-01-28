package Graphics;

import Entities.Entity;
import Game.Handler;
import Tile.Tile;

/**
 * Klasa GameCamera obsługuje kamerę gry
 */
public class GameCamera {

    private Handler handler;
    private double xOffset = 0.0, yOffset = 0.0;

    /**
     * Konstruktor klasy GameCamera
     *
     * @param handler obiekt klasy Handler, mający dostęp do całości programu
     * @param xOffset przesunięcie na osi x
     * @param yOffset przesunięcie na osi y
     */
    public GameCamera(Handler handler, double xOffset, double yOffset) {
        this.handler = handler;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    /**
     * Metoda sprawdzania granic mapy
     */
    public void checkBlankSpace() {
        if (xOffset < 0) {
            xOffset = 0;
        } else if (xOffset > handler.getWorld().getWidth() * Tile.TILE_WIDTH - handler.getWidth()) {
            xOffset = handler.getWorld().getWidth() * Tile.TILE_WIDTH - handler.getWidth();
        }

        if (yOffset < 0) {
            yOffset = 0;
        } else if (yOffset > handler.getWorld().getHeight() * Tile.TILE_HEIGHT - handler.getHeight()) {
            yOffset = handler.getWorld().getHeight() * Tile.TILE_HEIGHT - handler.getHeight();
        }
    }

    /**
     * Metoda ustawia widok kamery na postać gracza
     *
     * @param entity postać gracza, na którą ma być ustawiona kamera
     */
    public void centerOnEntity(Entity entity) {
        xOffset = entity.getX() - handler.getWidth() / 2f + entity.getWidth() / 2f;
        yOffset = entity.getY() - handler.getHeight() / 2f + entity.getHeight() / 2f;
        checkBlankSpace();
    }

    /**
     * Metoda przesunięcia kamery
     *
     * @param xAmt przesunięcie na osi x
     * @param yAmt przesunięcie na osi y
     */
    public void move(double xAmt, double yAmt) {
        xOffset += xAmt;
        yOffset += yAmt;
        checkBlankSpace();
    }

    public double getxOffset() {
        return xOffset;
    }

    public void setxOffset(double xOffset) {
        this.xOffset = xOffset;
    }

    public double getyOffset() {
        return yOffset;
    }

    public void setyOffset(double yOffset) {
        this.yOffset = yOffset;
    }

}
