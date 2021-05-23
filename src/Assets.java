import java.awt.*;
import java.awt.image.BufferedImage;

public class Assets {
    public static BufferedImage playerDefault, playerPink, playerBlue, playerOrange, playerBlack;

    public static BufferedImage playButtonActive, playButtonInactive, exitButtonActive, exitButtonInactive;

    private static final int width = 70, height = 100;

    public static void init() {
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("textures/spritesheet.png"));

        playerBlack = sheet.crop(0, 0, width, height);
        playerPink = sheet.crop(width, 0, width, height);
        playerBlue = sheet.crop(width * 2, 0, width, height);
        playerOrange = sheet.crop(0, height, width, height);
        playerDefault = sheet.crop(width, height, width, height);

        playButtonActive = ImageLoader.loadImage("textures/play_button_active.png");
        playButtonInactive = ImageLoader.loadImage("textures/play_button_inactive.png");
        exitButtonActive = ImageLoader.loadImage("textures/exit_button_active.png");
        exitButtonInactive = ImageLoader.loadImage("textures/exit_button_inactive.png");
    }
}
