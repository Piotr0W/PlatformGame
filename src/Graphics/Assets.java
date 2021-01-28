package Graphics;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Klasa Assets pozwalająca wczytac i ustawić odpowiednie grafiki
 */
public class Assets {

    private static int width = 32, height = 32, howManyImages = 4;

    public static Font font28;

    public static BufferedImage dirt, grass, stone, emptyTile, tree, rock, wood, inventoryScreen, temTile;
    public static BufferedImage[] player_down_1, player_up_1, player_left_1, player_right_1, player_down_2,
            player_up_2, player_left_2, player_right_2, player_down_attack, player_up_attack, player_left_attack,
            player_right_attack, player_all, zombie_down, zombie_up, zombie_left, zombie_right,
            zombie_all, startButton, stopButton, soundButton, saveButton, background;

    private static String path = "res/textures/";

    /**
     * Metoda ustawiająca odpowiednie grafiki
     */
    public static void init() {

        SpriteSheet tempSheet = new SpriteSheet(ImageLoader.loadImage(path + "Hero.png"));

        width = tempSheet.getWidth() / 8;
        height = tempSheet.getHeight() / 3;

        player_down_2 = new BufferedImage[howManyImages];
        player_up_2 = new BufferedImage[howManyImages];
        player_left_2 = new BufferedImage[howManyImages];
        player_right_2 = new BufferedImage[howManyImages];

        player_down_attack = new BufferedImage[howManyImages / 2];
        player_up_attack = new BufferedImage[howManyImages / 2];
        player_left_attack = new BufferedImage[howManyImages / 2];
        player_right_attack = new BufferedImage[howManyImages / 2];

        player_all = new BufferedImage[1];

        player_all[0] = tempSheet.crop(width * 4, 0, width, height);

        player_down_2[0] = tempSheet.crop(width * 4, 0, width, height);
        player_down_2[1] = tempSheet.crop(width * 5, 0, width, height);
        player_down_2[2] = tempSheet.crop(width * 6, 0, width, height);
        player_down_2[3] = tempSheet.crop(width * 7, 0, width, height);

        player_down_attack[0] = tempSheet.crop(width * 6, height * 2, width, height);
        player_down_attack[1] = tempSheet.crop(width * 7, height * 2, width, height);

        player_up_2[0] = tempSheet.crop(0, 0, width, height);
        player_up_2[1] = tempSheet.crop(width, 0, width, height);
        player_up_2[2] = tempSheet.crop(width * 2, 0, width, height);
        player_up_2[3] = tempSheet.crop(width * 3, 0, width, height);

        player_up_attack[0] = tempSheet.crop(width * 4, height * 2, width, height);
        player_up_attack[1] = tempSheet.crop(width * 5, height * 2, width, height);

        player_right_2[0] = tempSheet.crop(width * 4, height, width, height);
        player_right_2[1] = tempSheet.crop(width * 5, height, width, height);
        player_right_2[2] = tempSheet.crop(width * 6, height, width, height);
        player_right_2[3] = tempSheet.crop(width * 7, height, width, height);

        player_right_attack[0] = tempSheet.crop(width * 2, height * 2, width, height);
        player_right_attack[1] = tempSheet.crop(width * 3, height * 2, width, height);

        player_left_2[0] = tempSheet.crop(0, height, width, height);
        player_left_2[1] = tempSheet.crop(width, height, width, height);
        player_left_2[2] = tempSheet.crop(width * 2, height, width, height);
        player_left_2[3] = tempSheet.crop(width * 3, height, width, height);

        player_left_attack[0] = tempSheet.crop(0, height * 2, width, height);
        player_left_attack[1] = tempSheet.crop(width, height * 2, width, height);


        font28 = FontLoader.loadFont("res/fonts/slkscr.ttf", 28);

        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage(path + "sheet.png"));

        width = sheet.getWidth() / 8;
        height = sheet.getHeight() / 8;

        inventoryScreen = ImageLoader.loadImage(path + "inventoryScreen.png");

        wood = sheet.crop(width, height, width, height);

        player_down_1 = new BufferedImage[2];
        player_up_1 = new BufferedImage[2];
        player_left_1 = new BufferedImage[2];
        player_right_1 = new BufferedImage[2];

        player_down_1[0] = sheet.crop(width * 4, 0, width, height);
        player_down_1[1] = sheet.crop(width * 5, 0, width, height);
        player_up_1[0] = sheet.crop(width * 6, 0, width, height);
        player_up_1[1] = sheet.crop(width * 7, 0, width, height);
        player_right_1[0] = sheet.crop(width * 4, height, width, height);
        player_right_1[1] = sheet.crop(width * 5, height, width, height);
        player_left_1[0] = sheet.crop(width * 6, height, width, height);
        player_left_1[1] = sheet.crop(width * 7, height, width, height);

        zombie_down = new BufferedImage[2];
        zombie_up = new BufferedImage[2];
        zombie_left = new BufferedImage[2];
        zombie_right = new BufferedImage[2];
        zombie_all = new BufferedImage[2];

        zombie_all[0] = sheet.crop(width * 4, height * 2, width, height);
        zombie_all[1] = sheet.crop(width * 4, height * 2, width, height);

        zombie_down[0] = sheet.crop(width * 4, height * 2, width, height);
        zombie_down[1] = sheet.crop(width * 5, height * 2, width, height);
        zombie_up[0] = sheet.crop(width * 6, height * 2, width, height);
        zombie_up[1] = sheet.crop(width * 7, height * 2, width, height);
        zombie_right[0] = sheet.crop(width * 4, height * 3, width, height);
        zombie_right[1] = sheet.crop(width * 5, height * 3, width, height);
        zombie_left[0] = sheet.crop(width * 6, height * 3, width, height);
        zombie_left[1] = sheet.crop(width * 7, height * 3, width, height);

        dirt = sheet.crop(width, 0, width, height);
        grass = sheet.crop(width * 2, 0, width, height);
        stone = sheet.crop(width * 3, 0, width, height);

        temTile = sheet.crop(width * 3, height, width, height);


        //
        emptyTile = sheet.crop(width * 3, height, width, height);

        tree = sheet.crop(0, 0, width, height * 2);
        rock = sheet.crop(0, height * 2, width, height);

        SpriteSheet buttons = new SpriteSheet(ImageLoader.loadImage(path + "buttons.png"));
        width = buttons.getWidth();
        height = buttons.getHeight() / 8;

        startButton = new BufferedImage[2];
        startButton[0] = buttons.crop(0, 0, width, height);
        startButton[1] = buttons.crop(0, height, width, height);

        stopButton = new BufferedImage[2];
        stopButton[0] = buttons.crop(0, height * 2, width, height);
        stopButton[1] = buttons.crop(0, height * 3, width, height);

        soundButton = new BufferedImage[2];
        soundButton[0] = buttons.crop(0, height * 4, width, height);
        soundButton[1] = buttons.crop(0, height * 5, width, height);

        saveButton = new BufferedImage[2];
        saveButton[0] = buttons.crop(0, height * 6, width, height);
        saveButton[1] = buttons.crop(0, height * 7, width, height);

        SpriteSheet backgrounds = new SpriteSheet(ImageLoader.loadImage(path + "background.jpg"));
        width = backgrounds.getWidth();
        height = backgrounds.getHeight();

        background = new BufferedImage[2];
        background[0] = backgrounds.crop(0, 0, width, height);
        background[1] = backgrounds.crop(0, 0, width, height);

    }

}
