import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet extends Entity {
    private Game game;
    private final BufferedImage bulletImg;
    private static final int SPEED = 10;
    public static final int BULLET_WIDTH = 20, BULLET_HEIGHT = 20;
    private EntityManager entityManager;

    public Bullet(Game game, float x, float y) {
        super(game, x, y, BULLET_WIDTH, BULLET_HEIGHT);
        this.game = game;
        this.x = x;
        this.y = y;
        // TODO: - Implement selecting bullet. Default for now.
        bulletImg = Assets.playerOrange;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void tick() {
        y -= SPEED;
        if (y < 0) isOffscreen = true;
        for (int i = 0; i < entityManager.getEntities().size(); i++) {
            Entity e = entityManager.getEntities().get(i);
            if (!e.isFriendly()) {
                if (getBounds().intersects(e.getBounds())) {
                    entityManager.getEntities().remove(e);
                    entityManager.getEntities().remove(this);
                    Assets.playExplosionSound();
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(bulletImg, Math.round(x), Math.round(y), BULLET_WIDTH, BULLET_HEIGHT, null);
    }
}
