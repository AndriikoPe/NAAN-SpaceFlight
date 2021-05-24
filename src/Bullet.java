import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet extends Entity {
    private Game game;
    private final BufferedImage bulletImg;
    private static final int SPEED = 10;
    public static final int BULLET_WIDTH = 20, BULLET_HEIGHT = 20;

    public Bullet(Game game, float x, float y) {
        super(game, x, y, BULLET_WIDTH, BULLET_HEIGHT);
        this.game = game;
        this.x = x;
        this.y = y;

        // TODO: - Implement selecting bullet. Default for now.
        bulletImg = Assets.playerOrange;
    }

    @Override
    public void tick() {
        y -= SPEED;
        if (y < 0) isOffscreen = true;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(bulletImg, Math.round(x), Math.round(y), BULLET_WIDTH, BULLET_HEIGHT, null);
    }
}
