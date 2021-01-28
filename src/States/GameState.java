package States;

import Audio.Sounds;
import Game.Handler;
import Worlds.World;

import java.awt.*;

/**
 * Klasa GameState obsługuje stan gry programu
 */
public class GameState extends State {

    private final World world;

    /**
     * Konstruktor klasy GameState
     *
     * @param handler obiekt klasy Handler, mający dostęp do całości programu
     */
    public GameState(Handler handler) {
        super(handler);
        world = new World(handler, "res/worlds/world0.txt");
        handler.setWorld(world);
        Sounds.playMusic("res/audio/start.wav");
    }

    /**
     * Metoda do aktualizowania parametrów programu dla cześci stanu gry
     */
    @Override
    public void tick() {
        world.tick();
    }

    /**
     * Metoda wyświetlenia w oknie zaktulizowanych obiektów stanu gry
     *
     * @param graphics do wyświetlenia / renderowania
     */
    @Override
    public void render(Graphics graphics) {
        if (!handler.getWorld().getEntityManager().getPlayer().isActive())
            return;
        world.render(graphics);


    }

}
