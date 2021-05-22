import java.awt.*;

public class Player extends Creature {
    public Player(Game game, float x, float y) {
        super(game, x, y, DEFAULT_CREATURE_WIDTH, DEFAULT_CREATURE_HEIGHT);
    }

    @Override
    public void tick() {
        getInput();
        move();
    }

    public void getInput() {
        xMove = 0;
        yMove = 0;
        if (game.getKeyManager().left) {
            if (x > 0) xMove = -speed;
        }
        if (game.getKeyManager().right) {
            if (x + width < game.getDisplay().getCanvas().getWidth()) xMove = speed;
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.playerDefault, Math.round(x), Math.round(y), width, height, null);
    }
}
