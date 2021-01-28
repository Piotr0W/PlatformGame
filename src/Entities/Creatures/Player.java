package Entities.Creatures;

import Audio.Sounds;
import Entities.Entity;
import Game.Handler;
import Graphics.Animation;
import Graphics.Assets;
import Graphics.Text;
import Inventory.Inventory;
import States.State;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Klasa Player obsługuje postać gracza
 */
public class Player extends Creature {

    private static final int DEFAULT_SPEED = 200;
    // Animacja
    private final Animation animDown, animUp, animLeft, animRight, animDownAttack, animUpAttack, animLeftAttack,
            animRightAttack, animAll;

    // Atak
    private final long attackCooldown = 10;
    private long lastAttackTimer = 0, attackTimer = attackCooldown;

    // Ekwipunek
    private Inventory inventory;

    /**
     * Konstruktor klasy Player
     *
     * @param handler obiekt klasy Handler, mający dostęp do całości programu
     * @param x       położenie na osi x gracza
     * @param y       położenie na osi y gracza
     */
    public Player(Handler handler, double x, double y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

        bounds.x = 22;
        bounds.y = 44;
        bounds.width = 19;
        bounds.height = 19;

        // Animacja

        animDown = new Animation(DEFAULT_SPEED, Assets.player_down_2);
        animUp = new Animation(DEFAULT_SPEED, Assets.player_up_2);
        animLeft = new Animation(DEFAULT_SPEED, Assets.player_left_2);
        animRight = new Animation(DEFAULT_SPEED, Assets.player_right_2);

        animDownAttack = new Animation(DEFAULT_SPEED, Assets.player_down_attack);
        animUpAttack = new Animation(DEFAULT_SPEED, Assets.player_up_attack);
        animLeftAttack = new Animation(DEFAULT_SPEED, Assets.player_left_attack);
        animRightAttack = new Animation(DEFAULT_SPEED, Assets.player_right_attack);
        animAll = new Animation(DEFAULT_SPEED, Assets.player_all);

        inventory = new Inventory(handler);

    }

    /**
     * Metoda do aktualizowania parametrów programu dla cześci gracza
     */
    @Override
    public void tick() {

        // Animacja
        animDown.tick();
        animUp.tick();
        animRight.tick();
        animLeft.tick();

        animDownAttack.tick();
        animUpAttack.tick();
        animLeftAttack.tick();
        animRightAttack.tick();

        animAll.tick();

        // Ruch
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);
        // Atak
        checkAttacks();
        // Ekwipunek
        inventory.tick();
        if (handler.getGame().getDisplay().getTime() == 0) {
            State.setState(handler.getGame().menuState);

        }
    }

    /**
     * Metoda sprawdza czy obiekt jest w zasięgu ataku gracza i atakuje
     */
    private void checkAttacks() {
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if (attackTimer < attackCooldown)
            return;

        if (inventory.isActive())
            return;

        Rectangle cb = getCollisionBounds(0, 0); // shorter collisionBalance
        Rectangle attackRectangle = new Rectangle();
        int attackRectangleSize = 20;
        attackRectangle.width = attackRectangleSize;
        attackRectangle.height = attackRectangleSize;

        if (handler.getKeyManager().aUp) {
            attackRectangle.x = cb.x + cb.width / 2 - attackRectangleSize / 2;
            attackRectangle.y = cb.y - attackRectangleSize;
        } else if (handler.getKeyManager().aDown) {
            attackRectangle.x = cb.x + cb.width / 2 - attackRectangleSize / 2;
            attackRectangle.y = cb.y + cb.height;
        } else if (handler.getKeyManager().aLeft) {
            attackRectangle.x = cb.x - attackRectangleSize;
            attackRectangle.y = cb.y + cb.height / 2 - attackRectangleSize / 2;
        } else if (handler.getKeyManager().aRight) {
            attackRectangle.x = cb.x + cb.width;
            attackRectangle.y = cb.y + cb.height / 2 - attackRectangleSize / 2;
        } else {
            return;
        }

        attackTimer = 0;

        for (Entity entity : handler.getWorld().getEntityManager().getEntities()) {
            if (entity.equals(this))
                continue;
            if (entity.getCollisionBounds(0, 0).intersects(attackRectangle)) {
                entity.hurt(1);
                return;
            }
        }

    }

    /**
     * Metoda wywoływana, gdy gracz umiera, aby zmienić stanu gry na stan Menu
     */
    @Override
    public void die() {
        State.setState(handler.getGame().menuState);

    }

    /**
     * Metoda poruszania się za pomocą klawiatury
     */
    private void getInput() {
        xMove = 0;
        yMove = 0;

        if (inventory.isActive())
            return;

        if (handler.getKeyManager().up) {
            yMove = -speed;
        }
        if (handler.getKeyManager().down) {
            yMove = speed;
        }
        if (handler.getKeyManager().left) {
            xMove = -speed;
        }
        if (handler.getKeyManager().right) {
            xMove = speed;
        }

    }

    /**
     * Metoda wyświetlenia w oknie zaktulizowanych obiektów
     *
     * @param graphics do wyświetlenia / renderowania
     */
    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);

        Text.drawString(graphics, "HP >\t" + health + "\t<",
                2, 20, false, Color.BLACK, Assets.font28);

        Text.drawString(graphics, "SPEED >\t" + speed + "\t<",
                2, 40, false, Color.BLACK, Assets.font28);

        Text.drawString(graphics, "POINTS >\t" + handler.getWorld().getDeathZombie() + "\t<",
                2, 60, false, Color.BLACK, Assets.font28);

        Text.drawString(graphics, "TIME >\t" + handler.getGame().getDisplay().getMinute() +
                        ":" + handler.getGame().getDisplay().getSecond() + "\t<",
                2, 80, false, Color.BLACK, Assets.font28);
    }


    /**
     * Metoda sprawia, że ekwipunek jest widoczny przed resztą obiektów
     *
     * @param graphics do wyświetlenia / renderowania
     */
    public void postRender(Graphics graphics) {
        inventory.render(graphics);
    }

    /**
     * Metoda ta pozwala uzyskać odpowiednią grafike przy odpowiednim stanie gracza (położeniu) i odtwarzać dźwięk ataku
     *
     * @return odpowiednie zdjęcie
     */
    private BufferedImage getCurrentAnimationFrame() {
        if (handler.getKeyManager().aUp) {
            Sounds.playMusic("res/audio/wel.wav");
            return animUpAttack.getCurrentFrame();
        } else if (handler.getKeyManager().aDown) {
            Sounds.playMusic("res/audio/wel.wav");
            return animDownAttack.getCurrentFrame();
        } else if (handler.getKeyManager().aLeft) {
            Sounds.playMusic("res/audio/wel.wav");
            return animLeftAttack.getCurrentFrame();
        } else if (handler.getKeyManager().aRight) {
            Sounds.playMusic("res/audio/wel.wav");
            return animRightAttack.getCurrentFrame();
        } else if (xMove < 0) {
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

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

}
