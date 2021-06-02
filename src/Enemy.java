import java.awt.*;
import java.util.Random;

public class Enemy extends Creature {
    Random r = new Random();

    public Enemy(Game game, float x, float y, int width, int height) {
        super(game, x, y, width, height);
        setHealth(3);
        speed = r.nextInt(2) + 3;
        this.isFriendly = false;
    }

    @Override
    public void tick() {
        if (y > game.getHeight()) isOffscreen = true;
        y += speed;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.enemyGreen, Math.round(x), Math.round(y), null);
    }
}
