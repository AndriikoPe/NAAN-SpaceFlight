import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet extends Entity {
    protected final BufferedImage bulletImg;
    public static final int BULLET_WIDTH = 20, BULLET_HEIGHT = 20;
    protected EntityManager entityManager;
    private int healPerHit;
    protected final float xMove, yMove;
    protected boolean isDestroyable;

    public Bullet(Game game, float x, float y, float xMove, float yMove, BufferedImage bulletImg) {
        super(game, x, y, BULLET_WIDTH, BULLET_HEIGHT);
        this.x = x;
        this.y = y;
        this.xMove = xMove;
        this.yMove = yMove;
        isDestroyable = true;
        // TODO: - Implement selecting bullet. Default for now.
        this.bulletImg = bulletImg;
        healPerHit = 1;
    }

    public void setHealPerHit(int healPerHit) {
        this.healPerHit = healPerHit;
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
                    entityManager.removeEntity(e);
                    entityManager.addEntity(new Explosion(game, entityManager,
                            Explosion.EXPLOSION_DEFAULT_WIDTH, Explosion.EXPLOSION_DEFAULT_HEIGHT,
                            e, Assets.explosionImage, Explosion.EXPLOSION_DEFAULT_TTL));
                    entityManager.getPlayer().heal(healPerHit);
                    if (isDestroyable) entityManager.removeEntity(this);
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
