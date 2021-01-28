package Entities.Statics;

import Entities.Entity;
import Game.Handler;

/**
 * Klasa abstrakcyjna StaticEntity obsługuje obiekty martwe
 */
public abstract class StaticEntity extends Entity {
    /**
     * Konstruktor klasy StaticEntity
     *
     * @param handler obiekt klasy Handler, mający dostęp do całości programu
     * @param x       położenie na osi x obiektu
     * @param y       położenie na osi y obiektu
     * @param width   szerokość obiektu
     * @param height  wysokość obiektu
     */
    public StaticEntity(Handler handler, double x, double y, int width, int height) {
        super(handler, x, y, width, height);
    }

}
