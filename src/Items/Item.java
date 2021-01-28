package Items;

import Game.Handler;
import Graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Item {

    // Handler
    public static Item[] items = new Item[256];
    public static Item woodItem = new Item(Assets.wood, "Wood", 0);
    public static Item rockItem = new Item(Assets.rock, "Rock", 1);

    // Class

    public static final int ITEMWIDTH = 32, ITEMHEIGHT = 32;

    protected Handler handler;
    protected BufferedImage texture;
    protected String name;
    protected final int id;

    protected Rectangle bounds;

    protected int x, y, count;
    protected boolean pickedUp = false;

    public Item(BufferedImage texture, String name, int id) {
        this.texture = texture;
        this.name = name;
        this.id = id;
        count = 1;

        bounds = new Rectangle(x, y, ITEMWIDTH, ITEMHEIGHT);

        items[id] = this;
    }

    /**
     * Metoda do aktualizowania parametrów
     */
    public void tick() {
        if (handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f,
                0f).intersects(bounds)) {
            pickedUp = true;
            handler.getWorld().getEntityManager().getPlayer().getInventory().addItem(this);
        }
    }

    /**
     * Metoda wyświetlenia w oknie zaktulizowanych obiektów
     *
     * @param graphics do wyświetlenia / renderowania
     */
    public void render(Graphics graphics) {
        if (handler == null)
            return;
        render(graphics, (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()));
    }

    public void render(Graphics graphics, int x, int y) {
        graphics.drawImage(texture, x, y, ITEMWIDTH, ITEMHEIGHT, null);
    }

    public Item createNew(int count) {
        Item item = new Item(texture, name, id);
        item.setPickedUp(true);
        item.setCount(count);
        return item;
    }

    public Item createNew(int x, int y) {
        Item item = new Item(texture, name, id);
        item.setPosition(x, y);
        return item;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        bounds.x = x;
        bounds.y = y;
    }

    // Getters & Setters

    public Handler getHandler() {
        return handler;
    }

    public void setPickedUp(boolean pickedUp) {
        this.pickedUp = pickedUp;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public BufferedImage getTexture() {
        return texture;
    }

    public void setTexture(BufferedImage texture) {
        this.texture = texture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public boolean isPickedUp() {
        return pickedUp;
    }

}
