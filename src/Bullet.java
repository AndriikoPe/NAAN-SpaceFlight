import javax.sound.sampled.Clip;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Random;

public class Bullet extends Entity {
    private Game game;
    private final BufferedImage bulletImg;
    private static final int SPEED = 10;
    public static final int BULLET_WIDTH = 20, BULLET_HEIGHT = 20;
    private LinkedList<Entity> entities;

    public Bullet(Game game, float x, float y) {
        super(game, x, y, BULLET_WIDTH, BULLET_HEIGHT);
        this.game = game;
        this.x = x;
        this.y = y;
        entities = ((GameState) State.getState()).getEntityManager().getEntities();
        // TODO: - Implement selecting bullet. Default for now.
        bulletImg = Assets.playerOrange;
    }

    @Override
    public void tick() {
        y -= SPEED;
        if (y < 0) isOffscreen = true;
        for (int i = 0; i < entities.size(); i++) {
            Entity e = entities.get(i);
            if (!e.isFriendly()) {
                if (getBounds().intersects(e.getBounds())) {
                    entities.remove(e);
                    entities.remove(this);
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
