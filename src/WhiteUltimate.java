import java.awt.*;

public class WhiteUltimate extends Ultimate {

    private long lastTick;
    private final long TIME_TO_LOAD_ONE_PERCENT;
    private final long TIMER = 6_000_000_000L;
    private long currentTime;
    private boolean running;
    private static final Font numberOfShotsFont = new Font("Verdana", Font.BOLD, 25);

    public WhiteUltimate(Game game) {
        super(game);
        lastTick = System.nanoTime();
        TIME_TO_LOAD_ONE_PERCENT = 400_000_000;
        readiness = 100;
    }

    @Override
    public void tick() {
        if (System.nanoTime() - lastTick >= TIME_TO_LOAD_ONE_PERCENT) {
            lastTick = System.nanoTime();
            if (readiness < 100) readiness++;
        }
        if (running && System.nanoTime() - currentTime >= TIMER) {
            Assets.playSound(Assets.whiteUltimate);
            OrangeUltimateBullet bullet = new OrangeUltimateBullet(game,
                    entityManager.getPlayer().getX() + (entityManager.getPlayer().getWidth()
                            - OrangeUltimateBullet.DEFAULT_ULTIMATE_BULLET_WIDTH) / 2f, entityManager.getPlayer().getY(),
                    Assets.orangeWhiteUltimateBullet);
            bullet.setDamage(250);
            bullet.setEntityManager(entityManager);
            entityManager.addEntity(bullet);
            entityManager.getPlayer().heal(entityManager.getPlayer().getMaxHealth());
            game.addPoints(100);
            running = false;
        }
    }

    @Override
    public void use() {
        if (isReady()) {
            readiness = 0;
            running = true;
            currentTime = System.nanoTime();
        }
    }

    public void render(Graphics g) {
        super.render(g);
        if (running) {
            String str = (TIMER - (System.nanoTime() - currentTime)) / 1_000_000_000L + "";
            g.setFont(numberOfShotsFont);
            g.setColor(Color.WHITE);
            g.drawString(str, game.getWidth() - ULTIMATE_ICON_WIDTH - g.getFontMetrics().stringWidth(str) - 15,
                    game.getHeight() - Creature.DEFAULT_CREATURE_HEIGHT - ULTIMATE_ICON_HEIGHT - 30);
        }
    }
}
