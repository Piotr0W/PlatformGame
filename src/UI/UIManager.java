package UI;

import Game.Handler;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Klasa UIManager obsługująca obiekty dla stanu menu
 */
public class UIManager {

    private Handler handler;
    private ArrayList<UIObject> objects;

    /**
     * Konstruktor klasy UIManager
     *
     * @param handler obiekt klasy Handler, mający dostęp do całości programu
     */
    public UIManager(Handler handler) {
        this.handler = handler;
        objects = new ArrayList<UIObject>();
    }

    /**
     * Metoda do aktualizowania obiektów menu
     */
    public void tick() {
        for (UIObject object : objects)
            object.tick();
    }

    /**
     * Metoda wyświetlenia w oknie zaktulizowanych obiektów menu
     *
     * @param graphics do wyświetlenia / renderowania
     */
    public void render(Graphics graphics) {
        for (UIObject object : objects)
            object.render(graphics);
    }

    public void onMouseMove(MouseEvent event) {
        for (UIObject object : objects)
            object.onMouseMove(event);
    }

    public void onMouseRelease(MouseEvent event) {
        for (UIObject object : objects)
            object.onMouseRelease(event);
    }

    public void addObject(UIObject object) {
        objects.add(object);
    }

    public void removeObject(UIObject object) {
        objects.remove(object);
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ArrayList<UIObject> getObjects() {
        return objects;
    }

    public void setObjects(ArrayList<UIObject> objects) {
        this.objects = objects;
    }

}
