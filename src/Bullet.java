import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet extends Entity {
    private final BufferedImage bulletImg;
    public static final int BULLET_WIDTH = 20, BULLET_HEIGHT = 20;
    private EntityManager entityManager;
    private final float xMove, yMove;
    private boolean isDestroyable;

    public Bullet(Game game, float x, float y, float xMove, float yMove, BufferedImage bulletImg) {
        super(game, x, y, BULLET_WIDTH, BULLET_HEIGHT);
        this.x = x;
        this.y = y;
        this.xMove = xMove;
        this.yMove = yMove;
        isDestroyable = true;
        // TODO: - Implement selecting bullet. Default for now.
        this.bulletImg = bulletImg;
    }

    public void setDestroyable(boolean destroyable) {
        isDestroyable = destroyable;
    }

    public void setFriendly(boolean friendly) {
        isFriendly = friendly;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void tick() {
        y -= yMove;
        x += xMove;
        if (y < 0 || y > game.getHeight()) isOffscreen = true;
        if (!isFriendly) return;
        for (int i = 0; i < entityManager.getEntities().size(); i++) {
            Entity e = entityManager.getEntities().get(i);
            if (!e.isFriendly() && !(e instanceof Bullet)) {
                if (getBounds().intersects(e.getBounds())) {
                    entityManager.getEntities().remove(e);
                    entityManager.getPlayer().heal(1);
                    if (isDestroyable) entityManager.getEntities().remove(this);
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
