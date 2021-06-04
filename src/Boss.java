import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Boss extends Creature {
    // Entity manager;
    private final EntityManager entityManager;

    // Boss image.
    private BufferedImage bossImage;

    // Health.
    private Color healthBarColor;
    private int healthBarWidth;
    private final int MAX_HEALTH;

    // Coordinates.
    private static final int X_POS = 6;
    private static final int Y_POS  = 10;
    private static final int HEALTH_BAR_HEIGHT  = 10;
    public static final int DEFAULT_BOSS_WIDTH = 150,
            DEFAULT_BOSS_HEIGHT = 150;
    private static final int ADDS_SPACING = 30;

    // Counters.
    public static int bossCounter = 0;
    public static final int TOTAL_BOSSES = 3;
    private final int previousPointPerHit;
    private long spawnPause;
    private long lastSpawnTime;
    private final Random r = new Random();

    public Boss(Game game, float x, float y, int width, int height, int maxHealth, EntityManager entityManager) {
        super(game, x, y, width, height);
        this.entityManager = entityManager;

        bossCounter++;

        MAX_HEALTH = maxHealth;
        health = MAX_HEALTH;
        healthBarColor = new Color(0, 255, 0);
        healthBarWidth = Math.round(game.getWidth() - X_POS * 2);

        isFriendly = false;

        bossImage = getBossImage();

        previousPointPerHit = Enemy.getPointPerHit();
        Enemy.setPointPerHit(bossCounter);

        setPause();
        lastSpawnTime = System.nanoTime();

        xMove = speed + bossCounter;
    }

    private void setPause() {
        spawnPause = 3_000_000_000L - r.nextInt(10) * 150_000_000L;
    }

    private void spawnAdds() {
        if (System.nanoTime() - lastSpawnTime > spawnPause) {
            for (int i = 0; i < r.nextInt(bossCounter) + 2; i++) {
                float xPos = x + (width - (Creature.DEFAULT_CREATURE_WIDTH * bossCounter +
                        ADDS_SPACING * (bossCounter - 1))) / 2f +
                        (Creature.DEFAULT_CREATURE_WIDTH + ADDS_SPACING) * i;
                if (xPos + Creature.DEFAULT_CREATURE_WIDTH > game.getWidth()) break;
                Enemy enemy = new ShootingEnemy(
                        game,
                        xPos,
                        y + height / 2f,
                        Creature.DEFAULT_CREATURE_WIDTH,
                        Creature.DEFAULT_CREATURE_HEIGHT,
                        entityManager
                );
                entityManager.addEntity(enemy);
                lastSpawnTime = System.nanoTime();
                setPause();
            }
        }
    }

    private BufferedImage getBossImage() {
        switch (bossCounter) {
            case 1:
                return xMove < 0 ? Assets.boss1Left : Assets.boss1Right;
            case 2:
                return xMove < 0 ? Assets.boss2Left : Assets.boss2Right;
            case 3:
                return xMove < 0 ? Assets.boss3Left : Assets.boss3Right;
        }
        return null;
    }

    @Override
    public boolean getHit(Entity e, EntityManager manager) {
        health -= e.getDamage();
        entityManager.removeEntity(e);
        if (health <= 0) {
            health = 0;
            game.addPoints(250 + 100 * bossCounter);
            Enemy.setPointPerHit(previousPointPerHit + bossCounter);
            entityManager.getPlayer().makeFasterBy(0.5f);
        }
        return true;
    }

    @Override
    public void tick() {
        if (y < Y_POS + HEALTH_BAR_HEIGHT + 10) {
            y += speed;
        } else {
            move();
            spawnAdds();
        }
        float perCentHealth = health / (float) MAX_HEALTH;
        healthBarColor = new Color(Math.round(255 * (1 - perCentHealth)), Math.round(255 * perCentHealth), 0);
        healthBarWidth = Math.round((game.getWidth() - X_POS * 2) * perCentHealth);
    }

    @Override
    public void move() {
        if (x <= 0 || x + width > game.getWidth()) {
            xMove = -xMove;
            bossImage = getBossImage();
        }
        x += xMove;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(healthBarColor);
        g.fillRect(X_POS, Y_POS, healthBarWidth, HEALTH_BAR_HEIGHT);
        g.drawImage(bossImage, Math.round(x), Math.round(y), width, height, null);
    }
}
