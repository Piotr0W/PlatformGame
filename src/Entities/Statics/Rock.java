package Entities.Statics;

import Game.Handler;
import Graphics.Assets;
import Items.Item;
import Tile.Tile;

import java.awt.*;

/**
 * Klasa Rock obsługuje obiekt skały
 */
public class Rock extends StaticEntity {
    /**
     * Konstruktor klasy Rock
     *
     * @param handler obiekt klasy Handler, mający dostęp do całości programu
     * @param x       położenie na osi x skały
     * @param y       położenie na osi y skały
     */
    public Rock(Handler handler, double x, double y) {
        super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);

        bounds.x = 3;
        bounds.y = (int) (height / 2f);
        bounds.width = width - 6;
        bounds.height = (int) (height - height / 2f);
    }

    /**
     * Metoda do aktualizowania parametrów programu dla cześci obiektu skały
     */
    @Override
    public void tick() {

    }

    /**
     * Metoda wywoływana, gdy obiekt skały się niszczy, powoduje utworzenie nowego elementu itemu
     */
    @Override
    public void die() {
        handler.getWorld().getItemManager().addItem(Item.rockItem.createNew((int) x, (int) y));
    }

    /**
     * Metoda wyświetlenia w oknie zaktulizowanych obiektów skały
     *
     * @param graphics do wyświetlenia / renderowania
     */
    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(Assets.rock, (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }

}
