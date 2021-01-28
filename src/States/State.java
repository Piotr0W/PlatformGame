package States;

import Game.Handler;
import UI.UIManager;

import java.awt.*;

/**
 * Klasa abstrakcyjna State do tworzenia rozszerzonych stanów gry
 */
public abstract class State {

    private static State currentState = null;

    public static void setState(State state) {
        currentState = state;
    }

    public static State getState() {
        return currentState;
    }

    // Class
    protected Handler handler;

    /**
     * Konstruktor klasy State
     *
     * @param handler obiekt klasy Handler, mający dostęp do całości programu
     */
    public State(Handler handler) {
        this.handler = handler;

    }

    public abstract void tick();

    public abstract void render(Graphics graphics);

    public UIManager getUiManager() {
        return null;
    }

}
