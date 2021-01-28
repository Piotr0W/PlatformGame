package Worlds;

import Entities.Creatures.Player;
import Entities.Creatures.Zombie;
import Entities.EntityManager;
import Entities.Statics.Rock;
import Entities.Statics.Tree;
import Game.Handler;
import Items.ItemManager;
import Tile.Tile;
import Utils.Utils;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Klasa World obsługuje i tworzy mapę oraz obiekty
 */
public class World {
    private static final int NUMBER_OF_ZOMBIE = 600, NUMBER_OF_ENTITY = 600;
    private static final int HOW_FAR = 8000;
    private Handler handler;
    private int width = 0, height = 0, spawnX = 0, spawnY = 0, deathZombie = 0;
    private final long attackCooldown = 1;
    private long lastAttackTimer = 0, attackTimer = attackCooldown;

    private int[][] tiles;
    //Entities
    private EntityManager entityManager;
    // Item
    private ItemManager itemManager;

    public ArrayList<Zombie> zombie = new ArrayList<Zombie>();

    Random random = new Random();

    /**
     * Konstruktor klasy World
     *
     * @param handler obiekt klasy Handler, mający dostęp do całości programu
     * @param path    ścieżka do pliku dla wczytania mapy
     */
    public World(Handler handler, String path) {
        this.handler = handler;

        zombie.add(new Zombie(handler, 650, 650));
        zombie.add(new Zombie(handler, 1200, 650));
        zombie.add(new Zombie(handler, 1200, 1200));
        zombie.add(new Zombie(handler, 650, 1200));

        loadWorld(path);

        int i = 0;
        while (i < NUMBER_OF_ZOMBIE) {
            int a = random.nextInt(HOW_FAR) + 100, b = random.nextInt(HOW_FAR) + 100;
            if (!getTile(a, b).isSolid()) {
                zombie.add(new Zombie(handler, a, b));
                i++;
            }
        }


        for (i = 0; i < NUMBER_OF_ZOMBIE + 4; i++) {
            entityManager = new EntityManager(handler, new Player(handler, 200, 200),
                    zombie, i);
        }

        itemManager = new ItemManager(handler);


        i = 0;
        while (i < NUMBER_OF_ENTITY) {
            int a = random.nextInt(HOW_FAR) + 2350, b = random.nextInt(HOW_FAR) + 1400,
                    c = random.nextInt(HOW_FAR) + 2350, d = random.nextInt(HOW_FAR) + 1400;
            if (!getTile(a, b).isSolid()) {
                entityManager.addEntity(new Rock(handler, a, b));
                i++;
            }
            if (!getTile(c, d).isSolid()) {
                entityManager.addEntity(new Tree(handler, c, d));
                i++;
            }
            /*if (!getTile(a, d).isSolid()) {
                entityManager.addEntity(new RockTileEntity(handler, a, d));
                i++;
            }*/
        }
        entityManager.addEntity(new Rock(handler, 450, 100));
        entityManager.addEntity(new Rock(handler, 750, 100));
        entityManager.addEntity(new Rock(handler, 1200, 100));
        entityManager.addEntity(new Rock(handler, 1950, 100));


        entityManager.addEntity(new Rock(handler, 950, 1400));
        entityManager.addEntity(new Rock(handler, 1550, 1400));
        entityManager.addEntity(new Rock(handler, 2350, 1400));


        entityManager.addEntity(new Tree(handler, 600, 100));
        entityManager.addEntity(new Tree(handler, 950, 100));
        entityManager.addEntity(new Tree(handler, 1550, 100));
        entityManager.addEntity(new Tree(handler, 2350, 100));

        entityManager.addEntity(new Tree(handler, 450, 1400));
        entityManager.addEntity(new Tree(handler, 750, 1400));
        entityManager.addEntity(new Tree(handler, 1200, 1400));
        entityManager.addEntity(new Tree(handler, 1950, 1400));

        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);
    }

    /**
     * Metoda tworzy nowy obiekt typu Zombie, jeśli zombie zginął
     */
    public void newZombie() {
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if (attackTimer < attackCooldown)
            return;

        for (int i = 0; i < zombie.size(); i++) {
            Zombie zombies = zombie.get(i), zombie1;
            int a = random.nextInt(HOW_FAR / 2), b = random.nextInt(HOW_FAR / 2),
                    c = random.nextInt(HOW_FAR / 2), d = random.nextInt(HOW_FAR / 2);
            while (!zombies.isActive() && !getTile(a, b).isSolid() && !getTile(c, d).isSolid()) {
                deathZombie++;
                zombie.remove(i);

                zombie.add(i, new Zombie(handler, a, b));

                zombie1 = new Zombie(handler, c, d);
                zombie.add(zombie1);

                zombies = zombie.get(i);
                entityManager.addEntity(zombies);
                entityManager.addEntity(zombie1);

                attackTimer = 0;
                return;
            }
        }

    }

    /**
     * Metoda do aktualizowania parametrów programu
     */
    public void tick() {
        newZombie();
        itemManager.tick();
        entityManager.tick();

    }

    /**
     * Metoda wyświetlenia w oknie zaktulizowanych obiektów
     *
     * @param graphics do wyświetlenia / renderowania
     */

    public void render(Graphics graphics) {
        int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILE_WIDTH);
        int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILE_WIDTH + 1);
        int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILE_HEIGHT);
        int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILE_HEIGHT + 1);

        for (int y = yStart; y < yEnd; y++) {
            for (int x = xStart; x < xEnd; x++) {
                    getTile(x, y).render(graphics, (int) (x * Tile.TILE_WIDTH - handler.getGameCamera().getxOffset()),
                            (int) (y * Tile.TILE_HEIGHT - handler.getGameCamera().getyOffset()));
            }
        }
        // Items
        itemManager.render(graphics);
        //Entities
        entityManager.render(graphics);
    }

    /**
     * Metoda zwracająca rodzaj podłoża
     *
     * @param x położenie na osi x
     * @param y położenie na osi y
     * @return odpowiedni kafelek podłoża
     */
    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height)
            return Tile.grassTile;

        Tile t = Tile.tiles[tiles[x][y]];
        if (t == null)
            return Tile.dirtTile;

        return t;
    }

    /**
     * Metoda wczytuje mapę z pliku
     *
     * @param path ścieżka do pliku tekstowego tworzącego mapę
     */
    private void loadWorld(String path) {
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);

        tiles = new int[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
            }
        }
    }

    // Getters & Setters

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ItemManager getItemManager() {
        return itemManager;
    }

    public void setItemManager(ItemManager itemManager) {
        this.itemManager = itemManager;
    }

    public int getDeathZombie() {
        return deathZombie;
    }

    public int[][] getTiles() {
        return tiles;
    }
}

