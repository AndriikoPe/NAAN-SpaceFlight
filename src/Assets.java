import javax.sound.sampled.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Assets {
    private static final Random r = new Random();;

    public static BufferedImage playerDefault, playerPink, playerBlue, playerOrange, playerBlack;
    public static BufferedImage enemyRed, enemyBlack, enemyBrown, enemyGreen;
    public static BufferedImage playButtonActive, playButtonInactive, exitButtonActive, exitButtonInactive;
    public static Clip[] explosionClips = new Clip[3];
    public static Clip[] defaultShotClips = new Clip[3];
    public static Clip[] barrageShotClips = new Clip[3];
    public static Clip[] metallicShotClips = new Clip[3];

    private static final int width = 70, height = 100;

    public static void init() {

        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("textures/spritesheet.png"));

        playerBlack = sheet.crop(width * 3, 0, width, height);
        playerPink = sheet.crop(width * 4, 0, width, height);
        playerBlue = sheet.crop(width * 5, 0, width, height);
        playerOrange = sheet.crop(width * 3, height, width, height);
        playerDefault = sheet.crop(width * 4, height, width, height);

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

            defaultShotClips[0] = AudioSystem.getClip();
            defaultShotClips[0].open(AudioSystem.getAudioInputStream(new File("sound/defaultShot1.wav")));

            defaultShotClips[1] = AudioSystem.getClip();
            defaultShotClips[1].open(AudioSystem.getAudioInputStream(new File("sound/defaultShot2.wav")));

            defaultShotClips[2] = AudioSystem.getClip();
            defaultShotClips[2].open(AudioSystem.getAudioInputStream(new File("sound/defaultShot3.wav")));

            barrageShotClips[0] = AudioSystem.getClip();
            barrageShotClips[0].open(AudioSystem.getAudioInputStream(new File("sound/barrageShot1.wav")));

            barrageShotClips[1] = AudioSystem.getClip();
            barrageShotClips[1].open(AudioSystem.getAudioInputStream(new File("sound/barrageShot2.wav")));

            barrageShotClips[2] = AudioSystem.getClip();
            barrageShotClips[2].open(AudioSystem.getAudioInputStream(new File("sound/barrageShot3.wav")));

            metallicShotClips[0] = AudioSystem.getClip();
            metallicShotClips[0].open(AudioSystem.getAudioInputStream(new File("sound/metallicShot1.wav")));

            metallicShotClips[1] = AudioSystem.getClip();
            metallicShotClips[1].open(AudioSystem.getAudioInputStream(new File("sound/metallicShot2.wav")));

            metallicShotClips[2] = AudioSystem.getClip();
            metallicShotClips[2].open(AudioSystem.getAudioInputStream(new File("sound/metallicShot3.wav")));
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void playExplosionSound() {
        playSound(explosionClips[r.nextInt(explosionClips.length)]);
    }

    public static void playDefaultShotSound() {
        playSound(defaultShotClips[r.nextInt(defaultShotClips.length)]);
    }

    public static void playMetallicShotSound() {
        playSound(metallicShotClips[r.nextInt(metallicShotClips.length)]);
    }

    public static void playBarrageShotSound() {
        playSound(barrageShotClips[r.nextInt(barrageShotClips.length)]);
    }

    public static void playSound(Clip sound) {
        sound.setFramePosition(0);
        sound.start();
    }
}
