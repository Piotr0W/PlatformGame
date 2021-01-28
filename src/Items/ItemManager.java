package Items;

import Game.Handler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class ItemManager {

    private Handler handler;
    private final ArrayList<Item> items;

    public ItemManager(Handler handler) {
        this.handler = handler;
        items = new ArrayList<Item>();
    }

    /**
     * Metoda do aktualizowania parametrów
     */
    public void tick() {
        Iterator<Item> iterator = items.iterator();
        while (iterator.hasNext()) {
            Item item = iterator.next();
            item.tick();
            if (item.isPickedUp())
                iterator.remove();
        }
    }

    /**
     * Metoda wyświetlenia w oknie zaktulizowanych obiektów
     *
     * @param graphics do wyświetlenia / renderowania
     */
    public void render(Graphics graphics) {
        for (Item item : items)
            item.render(graphics);
    }

    public void addItem(Item item) {
        item.setHandler(handler);
        items.add(item);

    }

    public void deleteItem(Item item) {
        item.setHandler(handler);
        items.remove(item);
    }

    // Getters & Setters

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

}
