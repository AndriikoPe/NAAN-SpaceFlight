import java.awt.*;

public class DefaultUltimate extends Ultimate {
    private long lastTick;
    private final long TIME_TO_LOAD_ONE_PERCENT;
    private final Color LOADING_CIRCLE_COLOR;
    private final BasicStroke LOADING_CIRCLE_STROKE;

    public DefaultUltimate(Game game) {
        super(game);
        lastTick = System.nanoTime();
        TIME_TO_LOAD_ONE_PERCENT = 1_000_000_000;
        LOADING_CIRCLE_COLOR = Color.red;
        LOADING_CIRCLE_STROKE = new BasicStroke(3);
        readiness = 0;
    }

    @Override
    public void use() {
        if (isReady()) {
            readiness = 0;
            // TODO: - implement ultimate usage.
        }
    }

    @Override
    public void tick() {
        if (System.nanoTime() - lastTick >= TIME_TO_LOAD_ONE_PERCENT) {
            lastTick = System.nanoTime();
            if (readiness < 100) readiness++;
        }
    }

    @Override
    public void render(Graphics g) {
        System.out.println("Readiness is " + readiness);
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
