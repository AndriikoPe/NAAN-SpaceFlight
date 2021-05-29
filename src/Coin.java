import java.awt.*;
import java.awt.image.BufferedImage;

public class Coin extends Creature{
    private final BufferedImage[] coins = new BufferedImage[6];
    private final Animation anim;
    public Coin(Game game, float x, float y, int width, int height) {
        super(game, x, y, width, height);
        this.width = 18;
        this.height = 20;

        coins[0] = Assets.coin0;
        coins[1] = Assets.coin1;
        coins[2] = Assets.coin2;
        coins[3] = Assets.coin3;
        coins[4] = Assets.coin4;
        coins[5] = Assets.coin5;

        anim = new Animation(8,  coins[0], coins[1], coins[2], coins[3], coins[4], coins[5]);
    }


    @Override
    public void tick() {
        anim.runAnimation();
        if (y > game.getHeight()) isOffscreen = true;
        y += speed;
    }

    @Override
    public void render(Graphics g) {
        anim.drawingAnimation(g, x, y, 0);
    }

    public Rectangle getBounds(){
        return new Rectangle((int) x,(int) y, width, height);
    }
}
