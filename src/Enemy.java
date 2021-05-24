import java.awt.*;

public class Enemy extends Creature {
    public Enemy(Game game, float x, float y, int width, int height) {
        super(game, x, y, width, height);
        setHealth(3);
        this.speed = 2f;
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
