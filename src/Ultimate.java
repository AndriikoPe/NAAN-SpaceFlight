import java.awt.*;

public abstract class Ultimate extends Entity {
    protected static final int ULTIMATE_ICON_HEIGHT = 40;
    protected static final int ULTIMATE_ICON_WIDTH = 40;
    protected int readiness;
    private final Color LOADING_CIRCLE_COLOR = Color.red;
    private final BasicStroke LOADING_CIRCLE_STROKE = new BasicStroke(3);
    protected EntityManager entityManager;

    public Ultimate(Game game) {
        super(game,
                game.getWidth() - ULTIMATE_ICON_WIDTH - 10,
                game.getHeight() - Creature.DEFAULT_CREATURE_HEIGHT - ULTIMATE_ICON_HEIGHT - 30,
                ULTIMATE_ICON_WIDTH, ULTIMATE_ICON_HEIGHT);
        isFriendly = true;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public abstract void use();

    public boolean isReady() {
        return readiness == 100;
    }

    @Override
    public void render(Graphics g) {
        if (readiness < 100) {
            g.drawImage(Assets.ultimateNotReady, Math.round(x), Math.round(y), width, height, null);
            g.setColor(LOADING_CIRCLE_COLOR);
            ((Graphics2D) g).setStroke(LOADING_CIRCLE_STROKE);
            g.drawArc(Math.round(x), Math.round(y), width, height, 90,
                    Math.round(360f * (readiness / 100f)));
        } else {
            g.drawImage(Assets.ultimateReady, Math.round(x), Math.round(y), width, height, null);
        }
    }
}
