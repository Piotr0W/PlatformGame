package Tile;

import Graphics.Assets;

public class TempTile extends Tile {
    /**
     * Konstruktor klasy TempTile
     *
     * @param id id jako zmienna typu int oznaczająca konkretny rodzaj grafiki mapy
     */
    public TempTile(int id) {
        super(Assets.temTile, id);
    }
}
