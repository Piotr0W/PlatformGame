package Game;

import Display.Display;
import Graphics.Assets;
import Input.KeyManager;
import Input.MouseManager;
import States.GameState;
import States.MenuState;
import States.State;
import Graphics.GameCamera;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

/**
 * Klasa Game tworzy grę
 */
public class Game implements Runnable {

    private Display display;
    private int width = 0, height = 0;
    public String title;

    private boolean running = false;
    private Thread thread;

    private BufferStrategy bufferStrategy;
    private Graphics graphics;

    // States
    public State gameState;
    public State menuState;

    // Input
    private final KeyManager keyManager;
    private final MouseManager mouseManager;

    // Camera
    private GameCamera gameCamera;

    // Handler
    private Handler handler;

    /**
     * Konstruktor klasy Game
     *
     * @param title  nazwa okna
     * @param width  szerokość okna
     * @param height wysokość okna
     */
    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
    }

    /**
     * Metoda inicjalizuje zmienne, tworzy obiekty obsługujące program
     */
    private void init() {
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        Assets.init();

        handler = new Handler(this);
        gameCamera = new GameCamera(handler, 0, 0);

        gameState = new GameState(handler);
        menuState = new MenuState(handler);
        State.setState(menuState);

    }

    /**
     * Metoda aktualizuje dane, obsługa klawiatury, wywyłuje aktualizacje kolejnych obiektów
     */
    private void tick() {
        keyManager.tick();

        if (State.getState() != null)
            State.getState().tick();

        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_Q)) {
            State.setState(menuState);
            handler.getGame().getDisplay().getTimer().stop();
            handler.getMouseManager().setUIManager(menuState.getUiManager());
        }
    }

    /**
     * Metoda wyświetla okno programu, wywyłuje wyświetlanie kolejnych obiektów
     */
    private void render() {
        bufferStrategy = display.getCanvas().getBufferStrategy();
        if (bufferStrategy == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        graphics = bufferStrategy.getDrawGraphics();

        graphics.clearRect(0, 0, width, height);

        if (State.getState() != null)
            State.getState().render(graphics);

        bufferStrategy.show();
        graphics.dispose();
    }

    /**
     * Metoda uruchamia grę
     */
    public void run() {
        init();

        int fps = 60;
        double timePerTick = 1000000000f / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1) {
                tick();
                render();
                ticks++;
                delta--;
            }

            if (timer >= 1000000000) {
                //System.out.println("T & F - " + ticks);
                ticks = 0;
                timer = 0;
            }
        }

        stop();

    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }

    public GameCamera getGameCamera() {
        return gameCamera;
    }

    public Display getDisplay() {
        return display;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public synchronized void start() {
        if (running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (!running)
            return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}