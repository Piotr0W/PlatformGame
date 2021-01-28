package Entities.Creatures;

import Entities.Entity;
import Game.Handler;
import Tile.Tile;

/**
 * Klasa abstrakcyjna Creature obsługuje stworzenia takie jak gracz lub zombie
 */
public abstract class Creature extends Entity {

    public static final double DEFAULT_SPEED = 5.0;
    public static final int DEFAULT_CREATURE_WIDTH = 64, DEFAULT_CREATURE_HEIGHT = 64;

    protected double speed = 0.0, xMove = 0.0, yMove = 0.0;

    /**
     * Konstruktor klasy Creature
     *
     * @param handler obiekt klasy Handler, mający dostęp do całości programu
     * @param x       położenie na osi x stworzenia
     * @param y       położenie na osi y stworzenia
     * @param width   szerokość stworzenia
     * @param height  wysokość stworzenia
     */
    public Creature(Handler handler, double x, double y, int width, int height) {
        super(handler, x, y, width, height);
        speed = DEFAULT_SPEED;
        xMove = 0.0;
        yMove = 0.0;
    }

    /**
     * Metoda poruszania się stworzenia jeśli to możliwe, sprawdza kolizje
     */
    public void move() {
        if (!checkEntityCollisions(xMove, 0.0))
            moveX();
        if (!checkEntityCollisions(0.0, yMove))
            moveY();
    }

    public void moveX() {
        if (xMove > 0) { // Moving right
            int tempX = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILE_WIDTH;

            if (!collisionWithTile(tempX, (int) (y + bounds.y) / Tile.TILE_HEIGHT) &&
                    !collisionWithTile(tempX, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)) {
                x += xMove;
            } else {
                x = tempX * Tile.TILE_WIDTH - bounds.x - bounds.width - 1;
            }

        } else if (xMove < 0) { // Moving left
            int tempX = (int) (x + xMove + bounds.x) / Tile.TILE_WIDTH;

            if (!collisionWithTile(tempX, (int) (y + bounds.y) / Tile.TILE_HEIGHT) &&
                    !collisionWithTile(tempX, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)) {
                x += xMove;
            } else {
                x = tempX * Tile.TILE_WIDTH + Tile.TILE_WIDTH - bounds.x;
            }

        }
    }

    public void moveY() {
        if (yMove < 0) { // Moving up
            int tempY = (int) (y + yMove + bounds.y) / Tile.TILE_HEIGHT;

            if (!collisionWithTile((int) (x + bounds.x) / Tile.TILE_WIDTH, tempY) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, tempY)) {
                y += yMove;
            } else {
                y = tempY * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - bounds.y;
            }

        } else if (yMove > 0) { // Moving down
            int tempY = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILE_HEIGHT;

            if (!collisionWithTile((int) (x + bounds.x) / Tile.TILE_WIDTH, tempY) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, tempY)) {
                y += yMove;
            } else {
                y = tempY * Tile.TILE_HEIGHT - bounds.y - bounds.height - 1;
            }

        }
    }

    protected boolean collisionWithTile(int x, int y) {
        return handler.getWorld().getTile(x, y).isSolid();
    }

    // Getters & Setters

    public double getxMove() {
        return xMove;
    }

    public void setxMove(double xMove) {
        this.xMove = xMove;
    }

    public double getyMove() {
        return yMove;
    }

    public void setyMove(double yMove) {
        this.yMove = yMove;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

}
