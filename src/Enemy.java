import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Enemy extends Creature {
    Random r = new Random();
    private final BufferedImage image;
    private static int pointPerHit = 10;
    
    public Enemy(Game game, float x, float y, int width, int height) {
        super(game, x, y, width, height);
        setHealth(3);
        speed = r.nextInt(2) + 3;
        this.isFriendly = false;
        this.image = getImage();
    }

    public static int getPointPerHit() {
        return pointPerHit;
    }

    public static void setPointPerHit(int pointPerHit) {
        Enemy.pointPerHit = pointPerHit;
    }

    private BufferedImage getImage() {
        switch (r.nextInt(4)) {
            case 0: return Assets.enemyBrown;
            case 1: return Assets.enemyGreen;
            case 2: return Assets.enemyRed;
            case 3: return Assets.enemyBlack;
        }
        return null;
    }

    @Override
    public boolean getHit(Entity e, EntityManager manager) {
        manager.removeEntity(this);
        game.addPoints(Enemy.getPointPerHit());
        manager.addEntity(new Explosion(game, manager,
                            Explosion.EXPLOSION_DEFAULT_WIDTH, Explosion.EXPLOSION_DEFAULT_HEIGHT,
                            this, Assets.explosionImage, Explosion.EXPLOSION_DEFAULT_TTL));
        return true;
    }

    @Override
    public void tick() {
        if (y > game.getHeight()) isOffscreen = true;
        y += speed;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, Math.round(x), Math.round(y), null);
    }
}
