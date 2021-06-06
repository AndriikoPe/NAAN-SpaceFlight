import java.awt.*;

public class PurpleUltimate extends Ultimate {
    private long lastTick;
    private final long TIME_TO_LOAD_ONE_PERCENT;
    private final long TIMER = 5_000_000_000L;
    private long currentTime;
    private boolean running;
    private static final Font numberOfShotsFont = new Font("Verdana", Font.BOLD, 25);
    private Bullet purpleUltimateBullet;

    public PurpleUltimate(Game game) {
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
        if (running) {
            if (System.nanoTime() - currentTime > TIMER) {
                entityManager.removeEntity(purpleUltimateBullet);
                running = false;
            } else {
                purpleUltimateBullet.setX(entityManager.getPlayer().getX()
                        + (entityManager.getPlayer().getWidth() - PurpleUltimateWeapon.WIDTH) / 2f + 3);
            }
        }
    }

    @Override
    public void use() {
        if (isReady()) {
            Assets.playSound(Assets.purpleUltimate);
            purpleUltimateBullet = new PurpleUltimateWeapon(game, entityManager.getPlayer().getX()
                    + (entityManager.getPlayer().getWidth() - PurpleUltimateWeapon.WIDTH) / 2f + 3);
            purpleUltimateBullet.setEntityManager(entityManager);
            entityManager.addEntity(purpleUltimateBullet);
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
