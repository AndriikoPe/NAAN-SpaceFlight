import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Assets {
    private static final Random r = new Random();;

    public static BufferedImage playerDefault, playerPink, playerBlue, playerOrange, playerBlack
            , playerRed, playerBlackImproved, playerRedImproved;
    public static BufferedImage enemyRed, enemyBlack, enemyBrown, enemyGreen;
    public static BufferedImage playButtonActive, playButtonInactive, exitButtonActive, exitButtonInactive;
    public static BufferedImage planetDark, planetOrange, planetBrown, planetPurple, planetEarth, planetBlue;
    public static Clip[] explosionClips = new Clip[3];
    public static Clip[] defaultShopClips = new Clip[3];

    private static final int width = 70, height = 100;
    private static int PLANET_SIZE = 100;

    public static void init() {

        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("textures/spritesheet.png"));
        SpriteSheet planets = new SpriteSheet(ImageLoader.loadImage("textures/planets.png"));

        playerRed = sheet.crop(0, 0, width, height);
        playerBlackImproved = sheet.crop(width, 0, width, height);
        playerRedImproved = sheet.crop(width * 2, 0, width, height);
        playerBlack = sheet.crop(width * 3, 0, width, height);
        playerPink = sheet.crop(width * 4, 0, width, height);
        playerBlue = sheet.crop(width * 5, 0, width, height);
        playerOrange = sheet.crop(width * 3, height, width, height);
        playerDefault = sheet.crop(width * 4, height, width, height);

        planetDark = sheet.crop(0, 0, PLANET_SIZE, PLANET_SIZE);
        planetOrange = sheet.crop(PLANET_SIZE, 0, PLANET_SIZE, PLANET_SIZE);
        planetBrown = sheet.crop(PLANET_SIZE * 2, 0, PLANET_SIZE, PLANET_SIZE);
        planetPurple = sheet.crop(0, PLANET_SIZE, PLANET_SIZE, PLANET_SIZE);
        planetEarth = sheet.crop(PLANET_SIZE, PLANET_SIZE, PLANET_SIZE, PLANET_SIZE);
        planetBlue = sheet.crop(PLANET_SIZE * 2, PLANET_SIZE, PLANET_SIZE, PLANET_SIZE);

        enemyRed = sheet.crop(0, 0, width, height);
        enemyBlack = sheet.crop(width, 0, width, height);
        enemyBrown = sheet.crop(width * 2, 0, width, height);
        enemyGreen = sheet.crop(0, height, width, height);

        playButtonActive = ImageLoader.loadImage("textures/play_button_active.png");
        playButtonInactive = ImageLoader.loadImage("textures/play_button_inactive.png");
        exitButtonActive = ImageLoader.loadImage("textures/exit_button_active.png");
        exitButtonInactive = ImageLoader.loadImage("textures/exit_button_inactive.png");

        try {
            explosionClips[0] = AudioSystem.getClip();
            explosionClips[0].open(AudioSystem.getAudioInputStream(new File("sound/explosion1.wav")));

            explosionClips[1] = AudioSystem.getClip();
            explosionClips[1].open(AudioSystem.getAudioInputStream(new File("sound/explosion2.wav")));

            explosionClips[2] = AudioSystem.getClip();
            explosionClips[2].open(AudioSystem.getAudioInputStream(new File("sound/explosion3.wav")));

            defaultShopClips[0] = AudioSystem.getClip();
            defaultShopClips[0].open(AudioSystem.getAudioInputStream(new File("sound/defaultShot1.wav")));

            defaultShopClips[1] = AudioSystem.getClip();
            defaultShopClips[1].open(AudioSystem.getAudioInputStream(new File("sound/defaultShot2.wav")));

            defaultShopClips[2] = AudioSystem.getClip();
            defaultShopClips[2].open(AudioSystem.getAudioInputStream(new File("sound/defaultShot3.wav")));
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void playExplosionSound() {
//        Clip sound = explosionClips[r.nextInt(explosionClips.length)];
//        sound.setFramePosition(0);
//        sound.start();
//        sound.drain();
    }

    public static void playDefaultShotSound() {
//        Clip sound = defaultShopClips[r.nextInt(defaultShopClips.length)];
//        sound.setFramePosition(0);
//        sound.start();
//        sound.drain();
    }
}
