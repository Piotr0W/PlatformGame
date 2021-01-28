package Entities;

import Entities.Creatures.Player;
import Entities.Creatures.Zombie;
import Game.Handler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Klasa EntityManager obsługuje obiekty
 */
public class EntityManager {

    private Handler handler;
    private Player player;

    private final ArrayList<Zombie> zombie = new ArrayList<Zombie>();

    private ArrayList<Entity> entities;
    private final Comparator<Entity> renderSorter = new Comparator<Entity>() {
        @Override
        public int compare(Entity a, Entity b) {
            if (a.getY() + a.getHeight() < b.getY() + b.getHeight())
                return -1;
            return 1;
        }
    };

    /**
     * Konstruktor klasy EntityManager
     *
     * @param handler obiekt klasy Handler, mający dostęp do całości programu
     * @param player  obiekt klasy Player
     * @param zombie  lista obiektów typu Zombie
     * @param index   indeks dla Zombie
     */
    public EntityManager(Handler handler, Player player, ArrayList<Zombie> zombie, int index) {
        this.handler = handler;
        this.player = player;
        for (int i = 0; i < zombie.size(); i++) {
            if (i == index) {
                this.zombie.add(zombie.get(index));
            }
        }

        entities = new ArrayList<Entity>();
        addEntity(player);

        for (int i = 0; i < 4; i++) {
            addEntity(zombie.get(i));
        }
    }

    /**
     * Metoda do aktualizowania parametrów programu dla cześci obiektów
     */
    public void tick() {
        Iterator<Entity> iterator = entities.iterator();
        while (iterator.hasNext()) {
            Entity entity = iterator.next();
            entity.tick();
            if (!entity.isActive())
                iterator.remove();
        }
        entities.sort(renderSorter);

    }

    /**
     * Metoda wyświetlenia w oknie zaktulizowanych obiektów
     *
     * @param graphics do wyświetlenia / renderowania
     */
    public void render(Graphics graphics) {
        for (Entity entity : entities) {
            entity.render(graphics);
        }
        player.postRender(graphics);
    }

    /**
     * Metoda dodaje obiekt do listy obiektów
     *
     * @param entity dodawany obiekt
     */
    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    // Getters & Setters

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }

    public ArrayList<Zombie> getZombie() {
        return zombie;
    }
}
