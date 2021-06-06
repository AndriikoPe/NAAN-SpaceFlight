import java.awt.*;

public class PurpleUltimateWeapon extends Bullet {
    public static final int WIDTH = 25;
    public static final int HEIGHT = 495;
    private static final int y = 190;

    public PurpleUltimateWeapon(Game game, float x) {
        super(game, x, y, 0, 0, Assets.redLaser);
        this.isDestroyable = false;
        setHealPerHit(10);
        width = WIDTH;
        height = HEIGHT;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(Math.round(x), y, WIDTH, HEIGHT);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(bulletImg, Math.round(x), Math.round(y), WIDTH, HEIGHT, null);
    }
}
