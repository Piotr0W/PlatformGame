package Entities.Statics;

import Game.Handler;
import Graphics.Assets;
import Items.Item;
import Tile.Tile;

import java.awt.*;

/**
 * Klasa Tree obsługuje obiekt drzewa
 */
public class Tree extends StaticEntity {
    /**
     * Konstruktor klasy Tree
     *
     * @param handler obiekt klasy Handler, mający dostęp do całości programu
     * @param x       położenie na osi x drzewa
     * @param y       położenie na osi y drzewa
     */
    public Tree(Handler handler, double x, double y) {
        super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT * 2);

        bounds.x = 10;
        bounds.y = (int) (height / 1.5f);
        bounds.width = width - 20;
        bounds.height = (int) (height - height / 1.5f);
    }

    /**
     * Metoda do aktualizowania parametrów programu dla cześci obiektu drzewa
     */
    @Override
    public void tick() {

    }

    /**
     * Metoda wywoływana, gdy obiekt drzewa się niszczy, powoduje utworzenie nowego elementu itemu
     */
    @Override
    public void die() {
        handler.getWorld().getItemManager().addItem(Item.woodItem.createNew((int) x, (int) y));
    }

    /**
     * Metoda wyświetlenia w oknie zaktulizowanych obiektów drzewa
     *
     * @param graphics do wyświetlenia / renderowania
     */
    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(Assets.tree, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }

}
