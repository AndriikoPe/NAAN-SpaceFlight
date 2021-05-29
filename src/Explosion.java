import java.awt.*;
import java.awt.image.BufferedImage;

public class Explosion extends Entity {
    private final BufferedImage explosionImage;
    private final long spawnTime;
    private final long ttl;
    private final EntityManager entityManager;
    public static final long EXPLOSION_DEFAULT_TTL = Math.round(1000000000 / 2.5);
    public static final int EXPLOSION_DEFAULT_WIDTH = 100;
    public static final int EXPLOSION_DEFAULT_HEIGHT = 100;
    private int scale = 0;

    public Explosion(Game game, EntityManager entityManager,
                     int width, int height, Entity spawner,
                     BufferedImage explosionImage, long ttl) {
        super(game, spawner.getX(), spawner.getY(), width, height);
        this.explosionImage = explosionImage;
        this.ttl = ttl;
        this.entityManager = entityManager;
        spawnTime = System.nanoTime();
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    @Override
    public void tick() {
        if (System.nanoTime() - spawnTime > ttl) {
            entityManager.removeEntity(this);
        } else {
            x -= Math.round(scale / 2f);
            y -= Math.round(scale / 2f);
            width += scale;
            height += scale;
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(explosionImage, Math.round(x), Math.round(y), width, height, null);
    }
}
