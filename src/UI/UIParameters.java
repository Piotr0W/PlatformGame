package UI;

import Game.Handler;

import javax.swing.*;
import java.awt.*;

public class UIParameters extends UIObject {
    private static final int SIZE = 30;

    private Handler handler;
    private ClickListener clicker;
    public static JTextField seconds, minutes, health, speed;
    public static JLabel secondsLabel, minutesLabel, healthLabel, speedLabel;

    public UIParameters(double x, double y, int width, int height, Handler handler, ClickListener clicker) {
        super(x, y, width, height);
        this.handler = handler;
        this.clicker = clicker;

        seconds = new JTextField();
        seconds.setPreferredSize(new Dimension(SIZE, SIZE));
        seconds.setBounds((int) x, (int) y, width, height);
        seconds.setHorizontalAlignment(JLabel.CENTER);
        seconds.setFont(new Font("Arial", Font.PLAIN, 12));
        seconds.setBackground(Color.lightGray);
        seconds.setOpaque(true);
        seconds.setText("0");
        seconds.setVisible(true);
        secondsLabel = new JLabel("Seconds");
        secondsLabel.setPreferredSize(new Dimension(SIZE, SIZE));
        secondsLabel.setBounds((int) x - width, (int) y, width, height);
        secondsLabel.setHorizontalAlignment(JLabel.CENTER);
        secondsLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        secondsLabel.setBackground(Color.lightGray);
        secondsLabel.setOpaque(true);
        secondsLabel.setVisible(true);

        minutes = new JTextField();
        minutes.setPreferredSize(new Dimension(SIZE, SIZE));
        minutes.setBounds((int) x, (int) y + SIZE, width, height);
        minutes.setHorizontalAlignment(JLabel.CENTER);
        minutes.setFont(new Font("Arial", Font.PLAIN, 12));
        minutes.setBackground(Color.lightGray);
        minutes.setOpaque(true);
        minutes.setText("2");
        minutes.setVisible(true);
        minutesLabel = new JLabel("Minutes");
        minutesLabel.setPreferredSize(new Dimension(SIZE, SIZE));
        minutesLabel.setBounds((int) x - width, (int) y + SIZE, width, height);
        minutesLabel.setHorizontalAlignment(JLabel.CENTER);
        minutesLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        minutesLabel.setBackground(Color.lightGray);
        minutesLabel.setOpaque(true);
        minutesLabel.setVisible(true);


        health = new JTextField();
        health.setPreferredSize(new Dimension(SIZE, SIZE));
        health.setBounds((int) x, (int) y + SIZE * 2, width, height);
        health.setHorizontalAlignment(JLabel.CENTER);
        health.setFont(new Font("Arial", Font.PLAIN, 12));
        health.setBackground(Color.lightGray);
        health.setOpaque(true);
        health.setText("5");
        health.setVisible(true);
        healthLabel = new JLabel("Health");
        healthLabel.setPreferredSize(new Dimension(SIZE, SIZE));
        healthLabel.setBounds((int) x - width, (int) y + SIZE * 2, width, height);
        healthLabel.setHorizontalAlignment(JLabel.CENTER);
        healthLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        healthLabel.setBackground(Color.lightGray);
        healthLabel.setOpaque(true);
        healthLabel.setVisible(true);

        speed = new JTextField();
        speed.setPreferredSize(new Dimension(SIZE, SIZE));
        speed.setBounds((int) x, (int) y + SIZE * 3, width, height);
        speed.setHorizontalAlignment(JLabel.CENTER);
        speed.setFont(new Font("Arial", Font.PLAIN, 12));
        speed.setBackground(Color.lightGray);
        speed.setOpaque(true);
        speed.setText("5");
        speed.setVisible(true);
        speedLabel = new JLabel("Speed");
        speedLabel.setPreferredSize(new Dimension(SIZE, SIZE));
        speedLabel.setBounds((int) x - width, (int) y + SIZE * 3, width, height);
        speedLabel.setHorizontalAlignment(JLabel.CENTER);
        speedLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        speedLabel.setBackground(Color.lightGray);
        speedLabel.setOpaque(true);
        speedLabel.setVisible(true);


        handler.getGame().getDisplay().getFrame().add(seconds);
        handler.getGame().getDisplay().getFrame().add(minutes);
        handler.getGame().getDisplay().getFrame().add(health);
        handler.getGame().getDisplay().getFrame().add(speed);
        handler.getGame().getDisplay().getFrame().add(secondsLabel);
        handler.getGame().getDisplay().getFrame().add(minutesLabel);
        handler.getGame().getDisplay().getFrame().add(healthLabel);
        handler.getGame().getDisplay().getFrame().add(speedLabel);

        handler.getGame().getDisplay().getFrame().add(handler.getGame().getDisplay().getCanvas());
        handler.getGame().getDisplay().getFrame().pack();
    }

    /**
     * Metoda do aktualizowania parametrów
     */
    @Override
    public void tick() {
    }

    /**
     * Metoda wyświetlenia w oknie zaktulizowanych obiektów
     *
     * @param graphics do wyświetlenia / renderowania
     */
    @Override
    public void render(Graphics graphics) {
    }

    @Override
    public void onClick() {
        clicker.onClick();
    }

    public static int getSIZE() {
        return SIZE;
    }
}
