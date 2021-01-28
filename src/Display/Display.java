package Display;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

/**
 * Klasa Display tworzy okno programu
 */
public class Display {

    private JFrame frame;
    private Canvas canvas;

    Timer timer;
    int second = 0, minute = 0;
    String decimalFormatSecond = null, decimalFormatMinute = null;
    DecimalFormat decimalFormat = new DecimalFormat("00");


    private final String title;
    private int width = 0, height = 0;

    /**
     * Knonstruktor klasy Display
     *
     * @param title  nazwa okna
     * @param width  szerokość okna
     * @param height wysokość okna
     */
    public Display(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

        createDisplay();
    }

    /**
     * Tworzenie potrzebnych zmiennych i ich konfiguracja
     */
    private void createDisplay() {
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(false);
        frame.add(canvas);

        frame.pack();


        second = 0;
        minute = 1;
        countdownTimer();
    }

    /**
     * Metoda odpowiedzialna za odliczanie w dół od zadanego czasu
     */
    public void countdownTimer() {
        timer = new Timer(1000, e -> {
            second--;
            decimalFormatSecond = decimalFormat.format(second);
            decimalFormatMinute = decimalFormat.format(minute);

            if (second == -1) {
                second = 59;
                minute--;
                decimalFormatSecond = decimalFormat.format(second);
                decimalFormatMinute = decimalFormat.format(minute);
            }
            if (minute == 0 && second == 0) {
                timer.stop();
            }
        });
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public JFrame getFrame() {
        return frame;
    }

    public int getTime() {
        return minute * 60 + second;
    }

    public Timer getTimer() {
        return timer;
    }

    public String getSecond() {
        return decimalFormat.format(second);
    }

    public String getMinute() {
        return decimalFormat.format(minute);
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }
}
