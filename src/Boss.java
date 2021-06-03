import java.awt.*;

public class Boss extends Creature {
    private Color healthBarColor;
    private int healthBarWidth;
    private final int MAX_HEALTH;
    private static final int X_POS = 6;
    private static final int Y_POS  = 10;
    private static final int HEALTH_BAR_HEIGHT  = 10;


    public Boss(Game game, float x, float y, int width, int height, int maxHealth) {
        super(game, x, y, width, height);
        MAX_HEALTH = maxHealth;
        health = MAX_HEALTH;
        isFriendly = false;
        healthBarColor = new Color(0, 255, 0);
        healthBarWidth = Math.round(game.getWidth() - X_POS * 2);
    }

    @Override
    public boolean getHit(Entity e, EntityManager manager) {
        health -= e.getDamage();
        if (health < 0) health = 0;
        return true;
    }

    @Override
    public void tick() {
        if (y < Y_POS + HEALTH_BAR_HEIGHT + 10) {
            y += speed;
        }
        float perCentHealth = health / (float) MAX_HEALTH;
        healthBarColor = new Color(Math.round(255 * (1 - perCentHealth)), Math.round(255 * perCentHealth), 0);
        healthBarWidth = Math.round((game.getWidth() - X_POS * 2) * perCentHealth);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(healthBarColor);
        g.fillRect(X_POS, Y_POS, healthBarWidth, HEALTH_BAR_HEIGHT);
        g.drawImage(Assets.boss1, Math.round(x), Math.round(y), width, height, null);
    }
}
