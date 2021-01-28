package Game;

import Graphics.GameCamera;
import Input.KeyManager;
import Input.MouseManager;
import Worlds.World;

/**
 * Klasa Handler komunikuje pozostałe klasy, pomaga utrzymać porządek w programie
 */
public class Handler {

    private Game game;
    private World world;

    /**
     * Konstruktor klasy Handler
     *
     * @param game obiekt typu Game, ustawia go na aktualną grę
     */
    public Handler(Game game) {
        this.game = game;
    }

    public GameCamera getGameCamera() {
        return game.getGameCamera();
    }

    public KeyManager getKeyManager() {
        return game.getKeyManager();
    }

    public MouseManager getMouseManager() {
        return game.getMouseManager();
    }

    public int getWidth() {
        return game.getWidth();
    }

    public int getHeight() {
        return game.getHeight();
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

}
