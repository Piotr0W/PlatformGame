package Game;

/**
 * Klasa Launcher uruchamiająca grę
 */
public class Launcher {
    private static int width = 680, height = 420;

    /**
     * Metoda main uruchamiająca grę
     *
     * @param args parametr domyślny
     */
    public static void main(String[] args) {
        Game game = new Game("Game!", width, height);
        game.start();
    }

}
