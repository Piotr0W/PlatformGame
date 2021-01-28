package States;

import Audio.Sounds;
import Game.Handler;
import Graphics.Assets;
import UI.ClickListener;
import UI.UIParameters;
import UI.UIImageButton;
import UI.UIManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Klasa MenuState obsługuje stan menu programu
 */
public class MenuState extends State {

    private UIManager uiManager;

    /**
     * Konstruktor klasy MenuState
     *
     * @param handler obiekt klasy Handler, mający dostęp do całości programu
     */
    public MenuState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);

        uiManager.addObject(new UIImageButton(0, 0, 680, 420,
                Assets.background, new ClickListener() {
            @Override
            public void onClick() {
            }
        }));

        uiManager.addObject(new UIImageButton(250, 50, 128, 64,
                Assets.startButton, new ClickListener() {
            @Override
            public void onClick() {
                try {
                    handler.getGame().getDisplay().setSecond(Integer.parseInt(
                            UIParameters.seconds.getText()));
                    handler.getGame().getDisplay().setMinute(Integer.parseInt(
                            UIParameters.minutes.getText()));
                    handler.getWorld().getEntityManager().getPlayer().setHealth(Integer.parseInt(
                            UIParameters.health.getText()));
                    handler.getWorld().getEntityManager().getPlayer().setSpeed(Integer.parseInt(
                            UIParameters.speed.getText()));
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(handler.getGame().getDisplay().getFrame(),
                            "Podaj poprawne liczby\n" + exception.getLocalizedMessage(),
                            "Game error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                handler.getGame().getDisplay().getFrame().remove(UIParameters.seconds);
                handler.getGame().getDisplay().getFrame().remove(UIParameters.minutes);
                handler.getGame().getDisplay().getFrame().remove(UIParameters.health);
                handler.getGame().getDisplay().getFrame().remove(UIParameters.speed);
                handler.getGame().getDisplay().getFrame().remove(UIParameters.secondsLabel);
                handler.getGame().getDisplay().getFrame().remove(UIParameters.minutesLabel);
                handler.getGame().getDisplay().getFrame().remove(UIParameters.healthLabel);
                handler.getGame().getDisplay().getFrame().remove(UIParameters.speedLabel);

                handler.getGame().getDisplay().getFrame().requestFocus();
                handler.getGame().getDisplay().getFrame().pack();


                State.setState(handler.getGame().gameState);
                handler.getMouseManager().setUIManager(null);
                handler.getGame().getDisplay().getTimer().start();


            }
        }));

        uiManager.addObject(new UIImageButton(250, 150, 128, 64,
                Assets.stopButton, new ClickListener() {
            @Override
            public void onClick() {
                handler.getGame().getDisplay().getFrame().setVisible(false);
                handler.getGame().getDisplay().getFrame().dispose();
                handler.getMouseManager().setUIManager(null);
                System.exit(0);
            }

        }));
        uiManager.addObject(new UIImageButton(250, 250, 128, 64,
                Assets.soundButton, new ClickListener() {
            @Override
            public void onClick() {
                Sounds.setActiveSound(!Sounds.isActiveSound());
            }
        }));
        uiManager.addObject(new UIImageButton(250, 350, 128, 64,
                Assets.saveButton, new ClickListener() {
            @Override
            public void onClick() {
                try (PrintWriter out = new PrintWriter("score.txt")) {
                    out.println("Player score:\n" + handler.getWorld().getDeathZombie());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }));


        new UIParameters(150, 50, UIParameters.getSIZE() * 2, UIParameters.getSIZE(), handler, new ClickListener() {
            @Override
            public void onClick() {

            }
        });

    }

    /**
     * Metoda do aktualizowania parametrów programu dla stanu menu
     */
    @Override
    public void tick() {
        uiManager.tick();
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_Z)) {
            State.setState(handler.getGame().gameState);
            handler.getGame().getDisplay().getTimer().start();
            handler.getMouseManager().setUIManager(null);
        }
        if (!handler.getWorld().getEntityManager().getPlayer().isActive() ||
                handler.getGame().getDisplay().getTime() == 0) {
            handler.getMouseManager().setUIManager(uiManager);
            if (uiManager.getObjects().get(1).isHovering() && handler.getMouseManager().isLeftPressed()) {
                handler.getGame().getDisplay().getFrame().setVisible(false);
                handler.getGame().getDisplay().getFrame().dispose();
                handler.getGame().run();
            }
        }

    }

    /**
     * Metoda wyświetlenia w oknie zaktulizowanych obiektów stanu menu
     *
     * @param graphics do wyświetlenia / renderowania
     */
    @Override
    public void render(Graphics graphics) {
        uiManager.render(graphics);
    }

    public UIManager getUiManager() {
        return uiManager;
    }


}
