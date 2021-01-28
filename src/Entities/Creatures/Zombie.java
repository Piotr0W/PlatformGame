package Entities.Creatures;

import java.awt.*;
import java.awt.image.BufferedImage;

import Game.Handler;
import Graphics.Assets;
import Graphics.Animation;
import Tile.Tile;

/**
 * Klasa Zombie obsługuje obiekty Zombie
 */
public class Zombie extends Creature {

    private static final int DEFAULT_SPEED = 100;
    // Aniamcje
    private Animation animDown, animUp, animLeft, animRight, animAll;
    public int deathZombies = 0;

    // Atak
    private final long attackCooldown = 800;
    private long lastAttackTimer = 0, attackTimer = attackCooldown;

    /**
     * Konstruktor klasy Zombie
     *
     * @param handler obiekt klasy Handler, mający dostęp do całości programu
     * @param x       położenie na osi x gracza
     * @param y       położenie na osi y gracza
     */
    public Zombie(Handler handler, double x, double y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

        speed /= 5;

        bounds.x = 4;
        bounds.y = 44;
        bounds.width = 54;
        bounds.height = 19;

        // Aniamcje

        animDown = new Animation(DEFAULT_SPEED, Assets.zombie_down);
        animUp = new Animation(DEFAULT_SPEED, Assets.zombie_up);
        animLeft = new Animation(DEFAULT_SPEED, Assets.zombie_left);
        animRight = new Animation(DEFAULT_SPEED, Assets.zombie_right);
        animAll = new Animation(DEFAULT_SPEED, Assets.zombie_all);
    }

    /**
     * Metoda do aktualizowania parametrów programu dla cześci obiektów typu Zombie
     */
    @Override
    public void tick() {

        // Aniamcje
        animAll.tick();
        animDown.tick();
        animUp.tick();
        animRight.tick();
        animLeft.tick();

        // Ruch
        getInput();
        move();

        // Atak
        checkAttacks();

    }

    /**
     * Metoda sprawdza czy obiekt jest w zasięgu ataku zombie i atakuje
     */
    private void checkAttacks() {
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if (attackTimer < attackCooldown)
            return;
        Rectangle cb = getCollisionBounds(0, 0); //
        Rectangle attackRectangle = new Rectangle();
        int attackRectangleSize = 20;
        attackRectangle.width = attackRectangleSize;
        attackRectangle.height = attackRectangleSize;

        if (getY() > handler.getWorld().getEntityManager().getPlayer().getY() &&
                getX() > handler.getWorld().getEntityManager().getPlayer().getX() - getWidth() / 2.0 &&
                getX() < handler.getWorld().getEntityManager().getPlayer().getX() + getWidth() / 2.0) {
            attackRectangle.x = cb.x + cb.width / 2 - attackRectangleSize / 2;
            attackRectangle.y = cb.y - attackRectangleSize;

            //System.out.println("Góra");

            //System.out.println("Górny Lewy");*/
        } else if (getY() < handler.getWorld().getEntityManager().getPlayer().getY() &&
                getX() > handler.getWorld().getEntityManager().getPlayer().getX() - getWidth() / 2.0 &&
                getX() < handler.getWorld().getEntityManager().getPlayer().getX() + getWidth() / 2.0) {
            attackRectangle.x = cb.x + cb.width / 2 - attackRectangleSize / 2;
            attackRectangle.y = cb.y + cb.height;

            //System.out.println("Dół");

        } else if (getX() > handler.getWorld().getEntityManager().getPlayer().getX() &&
                getY() > handler.getWorld().getEntityManager().getPlayer().getY() - getHeight() / 2.0 &&
                getY() < handler.getWorld().getEntityManager().getPlayer().getY() + getHeight() / 2.0) {
            attackRectangle.x = cb.x - attackRectangleSize;
            attackRectangle.y = cb.y + cb.height / 2 - attackRectangleSize / 2;

            //System.out.println("Lewo");
        } else if (getX() < handler.getWorld().getEntityManager().getPlayer().getX() &&
                getY() > handler.getWorld().getEntityManager().getPlayer().getY() - getHeight() / 2.0 &&
                getY() < handler.getWorld().getEntityManager().getPlayer().getY() + getHeight() / 2.0) {
            attackRectangle.x = cb.x + cb.width;
            attackRectangle.y = cb.y + cb.height / 2 - attackRectangleSize / 2;

            //System.out.println("Prawo");
        }
        attackTimer = 0;
        if (handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0, 0)
                .intersects(attackRectangle)) {
            handler.getWorld().getEntityManager().getPlayer().hurt(1);
        }
    }

    /**
     * Metoda wywoływana, gdy zombie ginie, inkrementuje liczbę martwych zombie
     */
    @Override
    public void die() {
        deathZombies++;
        //System.out.println("Zombie is dead!");
    }

    /**
     * Metoda zeruje zmienne od przemieszczania się i wywołuje metode moveToPlayer()
     */
    private void getInput() {
        xMove = 0;
        yMove = 0;

        moveToPlayer();

    }

    /**
     * Metoda poruszania się zombie do gracza
     */
    private void moveToPlayer() {
        if (handler.getWorld().getEntityManager().getPlayer().getY() -
                handler.getWorld().getEntityManager().getPlayer().getHeight() / 2.0 > getY() - getHeight() / 2.0)
            yMove = speed;
        if (handler.getWorld().getEntityManager().getPlayer().getY() -
                handler.getWorld().getEntityManager().getPlayer().getHeight() / 2.0 < getY() - getHeight() / 2.0)
            yMove = -speed;
        if (handler.getWorld().getEntityManager().getPlayer().getX() -
                handler.getWorld().getEntityManager().getPlayer().getWidth() / 2.0 < getX() - getWidth() / 2.0 - 1)
            xMove = -speed;
        if (handler.getWorld().getEntityManager().getPlayer().getX() -
                handler.getWorld().getEntityManager().getPlayer().getWidth() / 2.0 > getX() - getWidth() / 2.0)
            xMove = speed;
    }

    /**
     * Metoda wyświetlenia w oknie zaktulizowanych obiektów zombie
     *
     * @param graphics do wyświetlenia / renderowania
     */
    @Override
    public void render(Graphics graphics) {

        graphics.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);


        //
        /*graphics.setColor(Color.RED);
        graphics.fillRect((int) (x - handler.getGameCamera().getxOffset() + width / 2),
                (int) (y - handler.getGameCamera().getyOffset()), (int) ((width / 2) + xMove),
                (int) ((height / 2) + yMove));*/


        /*for (int i = 0; i < handler.getWorld().zombie.size(); i++) {
            Tile.tempTile.render(graphics, (int) (x - handler.getGameCamera().getxOffset() + width),
                    (int) (y - handler.getGameCamera().getyOffset()));
        }*/
    }

    /**
     * Metoda ta pozwala uzyskać odpowiednią grafike przy odpowiednim stanie zombie (położeniu)
     *
     * @return odpowiednie zdjęcie
     */
    private BufferedImage getCurrentAnimationFrame() {
        if (xMove < 0) {
            return animLeft.getCurrentFrame();
        } else if (xMove > 0) {
            return animRight.getCurrentFrame();
        } else if (yMove < 0) {
            return animUp.getCurrentFrame();
        } else if (yMove > 0) {
            return animDown.getCurrentFrame();
        } else {
            return animAll.getCurrentFrame();
        }
    }


    // Getters & Setters

    public int getDeathZombies() {
        return deathZombies;
    }

    public void setDeathZombies(int deathZombies) {
        this.deathZombies = deathZombies;
    }

    public void setAnimDown(Animation animDown) {
        this.animDown = animDown;
    }
}
