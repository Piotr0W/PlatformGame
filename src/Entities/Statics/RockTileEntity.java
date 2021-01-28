package Entities.Statics;

import Game.Handler;
import Tile.Tile;
import Graphics.Assets;

import java.awt.*;

public class RockTileEntity extends StaticEntity {
    public RockTileEntity(Handler handler, double x, double y) {
        super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);

        bounds.x = 3;
        bounds.y = (int) (height / 2f);
        bounds.width = width - 6;
        bounds.height = (int) (height - height / 2f);
    }

    /**
     * Metoda do aktualizowania parametrów
     */
    @Override
    public void tick() {

    }

    @Override
    public void die() {
        handler.getWorld().getEntityManager().getPlayer().setHealth(
                handler.getWorld().getEntityManager().getPlayer().getHealth() + 1);
    }

    /**
     * Metoda wyświetlenia w oknie zaktulizowanych obiektów
     *
     * @param graphics do wyświetlenia / renderowania
     */
    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(Assets.stone, (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }

}
