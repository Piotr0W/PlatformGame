package Inventory;

import Game.Handler;
import Graphics.Assets;
import Graphics.Text;
import Items.Item;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Inventory {

    private Handler handler;
    private boolean active = false;
    private final ArrayList<Item> inventoryItems;

    private final int invX = 64, invY = 48,
            invWidth = 512, invHeight = 384,
            invListCenterX = invX + 171,
            invListCenterY = invY + invHeight / 2 + 5,
            invListSpacing = 30, invImageX = 452, invImageY = 82,
            invImageWidth = 64, invImageHeight = 64, invCountX = 484, invCountY = 172;

    private int selectedItem = 0;

    public Inventory(Handler handler) {
        this.handler = handler;
        inventoryItems = new ArrayList<Item>();
    }

    public void tick() {
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_E))
            active = !active;
        if (!active)
            return;

        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_W))
            selectedItem--;
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_S))
            selectedItem++;

        if (selectedItem < 0)
            selectedItem = inventoryItems.size() - 1;
        else if (selectedItem >= inventoryItems.size())
            selectedItem = 0;

        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER) &&
                inventoryItems.get(selectedItem).getName().equals("Rock") &&
                inventoryItems.get(selectedItem).getCount() > 0) {
            handler.getWorld().getEntityManager().getPlayer().getInventory().deleteItem(Item.rockItem);
            handler.getWorld().getEntityManager().getPlayer().setHealth(
                    handler.getWorld().getEntityManager().getPlayer().getHealth() + 1);
        }
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER) &&
                inventoryItems.get(selectedItem).getName().equals("Wood") &&
                inventoryItems.get(selectedItem).getCount() > 0) {
            handler.getWorld().getEntityManager().getPlayer().getInventory().deleteItem(Item.woodItem);
            handler.getWorld().getEntityManager().getPlayer().setSpeed(
                    handler.getWorld().getEntityManager().getPlayer().getSpeed() + 1);
        }
    }

    /**
     * Metoda wyświetlenia w oknie zaktulizowanych obiektów
     *
     * @param graphics do wyświetlenia / renderowania
     */
    public void render(Graphics graphics) {
        if (!active)
            return;

        graphics.drawImage(Assets.inventoryScreen, invX, invY, invWidth, invHeight, null);

        int len = inventoryItems.size();
        if (len == 0)
            return;
        for (int i = -5; i < 6; i++) {
            if (selectedItem + i < 0 || selectedItem + i >= len)
                continue;
            if (i == 0) {
                Text.drawString(graphics, "> " + inventoryItems.get(selectedItem + i).getName() + " <",
                        invListCenterX,
                        invListCenterY + i * invListSpacing, true, Color.YELLOW, Assets.font28);
            } else {
                Text.drawString(graphics, inventoryItems.get(selectedItem + i).getName(), invListCenterX,
                        invListCenterY + i * invListSpacing, true, Color.WHITE, Assets.font28);
            }
        }
        Item item = inventoryItems.get(selectedItem);
        if (item.getCount() != 0) {
            graphics.drawImage(item.getTexture(), invImageX, invImageY, invImageWidth, invImageHeight, null);
            Text.drawString(graphics, Integer.toString(item.getCount()), invCountX, invCountY, true, Color.WHITE,
                    Assets.font28);
        }
    }

    // Inventory methods

    public void addItem(Item item) {
        for (Item tempItem : inventoryItems) {
            if (tempItem.getId() == item.getId()) {
                tempItem.setCount(tempItem.getCount() + item.getCount());
                return;
            }
        }
        inventoryItems.add(item);
    }

    public void deleteItem(Item item) {
        for (Item tempItem : inventoryItems) {
            if (tempItem.getId() == item.getId()) {
                if (tempItem.getCount() == 0) {
                    inventoryItems.remove(item);
                    return;
                } else {
                    tempItem.setCount(tempItem.getCount() - item.getCount());
                    return;
                }
            }
        }
    }

    // Getters & Setters

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public boolean isActive() {
        return active;
    }

    public int inventoryItemsSize() {
        return inventoryItems.size();
    }

}
