import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Assets {
    public static BufferedImage playerDefault, playerPink, playerBlue, playerOrange, playerBlack;
    public static BufferedImage enemyRed, enemyBlack, enemyBrown, enemyGreen;
    public static BufferedImage coin0, coin1, coin2, coin3, coin4, coin5;
    public static BufferedImage playButtonActive, playButtonInactive, exitButtonActive, exitButtonInactive;
    public static BufferedImage planetDark, planetOrange, planetBrown, planetPurple, planetEarth, planetBlue;
    public static BufferedImage ultimateNotReady, ultimateReady;
    public static BufferedImage explosionImage, bigExplosionImage;
    // Sounds
    public static Clip[] explosionClips = new Clip[3];
    public static Clip[] defaultShotClips = new Clip[3];
    public static Clip[] barrageShotClips = new Clip[3];
    public static Clip[] metallicShotClips = new Clip[3];
    public static Clip[] doubleShotClips = new Clip[3];
    public static Clip[] playerHitClips = new Clip[3];
    // Ultimates sounds
    public static Clip[] blackUltimateShotClips = new Clip[3];
    public static Clip[] pinkUltimateShotClips = new Clip[3];
    public static Clip orangeUltimateShot, orangeUltimateExplosion, defaultUltimateUse;

    private static final int width = 70, height = 100;
    public static final int PLANET_SIZE = 100;
    public static final int COIN_WIDTH = 18;
    public static final int COIN_HEIGHT = 20;
    private static Random r;

    public static void init() {
        r = new Random();

        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("textures/spritesheet.png"));
        SpriteSheet planets = new SpriteSheet(ImageLoader.loadImage("textures/planets.png"));
        SpriteSheet coins = new SpriteSheet(ImageLoader.loadImage("textures/coin.png"));

        ultimateNotReady = ImageLoader.loadImage("textures/ultimateNotReady.png");
        ultimateReady = ImageLoader.loadImage("textures/ultimateReady.png");

        playerBlack = sheet.crop(width * 3, 0, width, height);
        playerPink = sheet.crop(width * 4, 0, width, height);
        playerBlue = sheet.crop(width * 5, 0, width, height);
        playerOrange = sheet.crop(width * 3, height, width, height);
        playerDefault = sheet.crop(width * 4, height, width, height);

        planetDark = planets.crop(0, 0, PLANET_SIZE, PLANET_SIZE);
        planetOrange = planets.crop(PLANET_SIZE, 0, PLANET_SIZE, PLANET_SIZE);
        planetBrown = planets.crop(PLANET_SIZE * 2, 0, PLANET_SIZE, PLANET_SIZE);
        planetPurple = planets.crop(0, PLANET_SIZE, PLANET_SIZE, PLANET_SIZE);
        planetEarth = planets.crop(PLANET_SIZE, PLANET_SIZE, PLANET_SIZE, PLANET_SIZE);
        planetBlue = planets.crop(PLANET_SIZE * 2, PLANET_SIZE, PLANET_SIZE, PLANET_SIZE);

        enemyRed = sheet.crop(0, 0, width, height);
        enemyBlack = sheet.crop(width, 0, width, height);
        enemyBrown = sheet.crop(width * 2, 0, width, height);
        enemyGreen = sheet.crop(0, height, width, height);

        explosionImage = ImageLoader.loadImage("textures/explosion.png");
        bigExplosionImage = ImageLoader.loadImage("textures/bigExplosion.png");

        coin0 = coins.crop(0, 0, COIN_WIDTH, COIN_HEIGHT);
        coin1 = coins.crop(COIN_WIDTH, 0, COIN_WIDTH, COIN_HEIGHT);
        coin2 = coins.crop(COIN_WIDTH * 2, 0, COIN_WIDTH, COIN_HEIGHT);
        coin3 = coins.crop(COIN_WIDTH * 3, 0, COIN_WIDTH, COIN_HEIGHT);
        coin4 = coins.crop(COIN_WIDTH * 4, 0, COIN_WIDTH, COIN_HEIGHT);
        coin5 = coins.crop(COIN_WIDTH * 5, 0, COIN_WIDTH, COIN_HEIGHT);

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

            doubleShotClips[0] = AudioSystem.getClip();
            doubleShotClips[0].open(AudioSystem.getAudioInputStream(new File("sound/doubleShot1.wav")));

            doubleShotClips[1] = AudioSystem.getClip();
            doubleShotClips[1].open(AudioSystem.getAudioInputStream(new File("sound/doubleShot2.wav")));

            doubleShotClips[2] = AudioSystem.getClip();
            doubleShotClips[2].open(AudioSystem.getAudioInputStream(new File("sound/doubleShot3.wav")));

            playerHitClips[0] = AudioSystem.getClip();
            playerHitClips[0].open(AudioSystem.getAudioInputStream(new File("sound/playerHit1.wav")));

            playerHitClips[1] = AudioSystem.getClip();
            playerHitClips[1].open(AudioSystem.getAudioInputStream(new File("sound/playerHit2.wav")));

            playerHitClips[2] = AudioSystem.getClip();
            playerHitClips[2].open(AudioSystem.getAudioInputStream(new File("sound/playerHit3.wav")));

            // Ultimates clips

            blackUltimateShotClips[0] = AudioSystem.getClip();
            blackUltimateShotClips[0].open(AudioSystem.getAudioInputStream(new File("sound/blackUltimateShot1.wav")));

            blackUltimateShotClips[1] = AudioSystem.getClip();
            blackUltimateShotClips[1].open(AudioSystem.getAudioInputStream(new File("sound/blackUltimateShot2.wav")));

            blackUltimateShotClips[2] = AudioSystem.getClip();
            blackUltimateShotClips[2].open(AudioSystem.getAudioInputStream(new File("sound/blackUltimateShot3.wav")));

            pinkUltimateShotClips[0] = AudioSystem.getClip();
            pinkUltimateShotClips[0].open(AudioSystem.getAudioInputStream(new File("sound/pinkUltimateShot1.wav")));

            pinkUltimateShotClips[1] = AudioSystem.getClip();
            pinkUltimateShotClips[1].open(AudioSystem.getAudioInputStream(new File("sound/pinkUltimateShot2.wav")));

            pinkUltimateShotClips[2] = AudioSystem.getClip();
            pinkUltimateShotClips[2].open(AudioSystem.getAudioInputStream(new File("sound/pinkUltimateShot3.wav")));

            orangeUltimateShot = AudioSystem.getClip();
            orangeUltimateShot.open(AudioSystem.getAudioInputStream(new File("sound/orangeUltimateShot.wav")));

            orangeUltimateExplosion = AudioSystem.getClip();
            orangeUltimateExplosion.open(AudioSystem.getAudioInputStream(new File("sound/orangeUltimateExplosion.wav")));

            defaultUltimateUse = AudioSystem.getClip();
            defaultUltimateUse.open(AudioSystem.getAudioInputStream(new File("sound/defaultUltimateUse.wav")));
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

    public static void playDoubleShotSound() {
        playSound(doubleShotClips[r.nextInt(doubleShotClips.length)]);
    }

    public static void playPlayerHitSound() {
        playSound(playerHitClips[r.nextInt(playerHitClips.length)]);
    }

    public static void playBlackUltimateShotSound() {
        playSound(blackUltimateShotClips[r.nextInt(blackUltimateShotClips.length)]);
    }

    public static void playPinkUltimateShotSound() {
        playSound(pinkUltimateShotClips[r.nextInt(pinkUltimateShotClips.length)]);
    }

    public static void playSound(Clip sound) {
        if (sound.isRunning()) sound.stop();
        sound.setFramePosition(0);
        sound.start();
    }
}
