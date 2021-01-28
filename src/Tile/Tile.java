package Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Klasa Tile obsługuje mapę gry
 */
public class Tile {

    //STATIC STUFF HERE
    public static Tile[] tiles = new Tile[256];
    public static Tile grassTile = new GrassTile(0);
    public static Tile dirtTile = new DirtTile(1);
    public static Tile rockTile = new RockTile(2);

    //
public static Tile tempTile = new TempTile(3);

    //CLASS

    public static final int TILE_WIDTH = 64, TILE_HEIGHT = 64;

    protected BufferedImage texture;
    protected final int id;

    /**
     * Konstruktor klasy Tile
     *
     * @param texture grafika występująca w danym miejscu na mapie
     * @param id      id jako zmienna typu int oznaczająca konkretny rodzaj grafiki mapy
     */
    public Tile(BufferedImage texture, int id) {
        this.texture = texture;
        this.id = id;

        tiles[id] = this;
    }

    /**
     * Metoda do aktualizowania parametrów
     */
    public void tick() {

    }

    /**
     * Metoda wyświetlenia w oknie grafik mapy
     *
     * @param graphics do wyświetlenia / renderowania
     * @param x        położenie kafelka grafiki mapy na osi x
     * @param y        położenie kafelka grafiki mapy na osi y
     */
    public void render(Graphics graphics, int x, int y) {
        graphics.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }

    /**
     * Metoda mówiąca, czy na danym kafelku grafiki mapy można stanąć
     *
     * @return true jeśli tak, false, jeśli nie
     */
    public boolean isSolid() {
        return false;
    }

    public int getId() {
        return id;
    }

}

