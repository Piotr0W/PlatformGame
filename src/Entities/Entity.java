package Entities;

import Game.Handler;

import java.awt.*;

/**
 * Klasa abstrakcyjna Entity obsługuje obiekty
 */
public abstract class Entity {

    public static final int DEFAULT_HEALTH = 10;
    protected Handler handler;
    protected double x = 0.0, y = 0.0;
    protected int width = 0, height = 0, health = 0;
    protected boolean active = true;
    protected Rectangle bounds;

    /**
     * Konstruktor klasy Entity
     *
     * @param handler obiekt klasy Handler, mający dostęp do całości programu
     * @param x       położenie na osi x obiektu
     * @param y       położenie na osi y obiektu
     * @param width   szerokość obiektu
     * @param height  wysokość obiektu
     */
    public Entity(Handler handler, double x, double y, int width, int height) {
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        health = DEFAULT_HEALTH;

        bounds = new Rectangle(0, 0, width, height);
    }

    /**
     * Moteoda do aktualizowania parametrów programu dla cześci obiektu
     */
    public abstract void tick();

    /**
     * Metoda wyświetlenia w oknie zaktalizowanych obiektów
     *
     * @param graphics do wyświetlenia / renderowania
     */
    public abstract void render(Graphics graphics);

    /**
     * Metoda wywoływana, gdy obiekt się niszczy
     */
    public abstract void die();

    /**
     * Metoda zadawania ataku, odejmuje zadane obrażenia od wartości Hp obiektu
     *
     * @param amt wartość zadanego ataku
     */
    public void hurt(int amt) {
        health -= amt;
        if (health <= 0) {
            active = false;
            die();
        }
    }

    /**
     * Metoda sprawdza kolizje obiektu
     *
     * @param xOffset
     * @param yOffset
     * @return
     */
    public boolean checkEntityCollisions(double xOffset, double yOffset) {
        for (Entity entity : handler.getWorld().getEntityManager().getEntities()) {
            if (entity.equals(this))
                continue;
            if (entity.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)))
                return true;
        }
        return false;
    }

    public Rectangle getCollisionBounds(double xOffset, double yOffset) {
        return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
    }

    // Getters & Setters

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
