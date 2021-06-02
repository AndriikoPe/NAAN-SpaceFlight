import java.awt.*;

public class PinkUltimate extends Ultimate {
    private long lastTick;
    private final long TIME_TO_LOAD_ONE_PERCENT;
    private final Font numberOfShotsFont;

    public PinkUltimate(Game game) {
        super(game);
        lastTick = System.nanoTime();
        numberOfShotsFont = new Font("Verdana", Font.BOLD, 25);
        TIME_TO_LOAD_ONE_PERCENT = 400_000_000;
        readiness = 100;
    }

    @Override
    public void tick() {
        if (System.nanoTime() - lastTick >= TIME_TO_LOAD_ONE_PERCENT) {
            lastTick = System.nanoTime();
            if (readiness < 100) readiness++;
        }
    }

    @Override
    public void use() {
        if (isReady()) {
            readiness = 0;
            if (entityManager.getPlayer().getWeapon() instanceof PinkUltimateWeapon) {
                ((PinkUltimateWeapon) entityManager.getPlayer().getWeapon()).addShots();
            } else {
                entityManager.getPlayer().setWeapon(new PinkUltimateWeapon(
                        game,
                        entityManager,
                        entityManager.getPlayer().getWeapon()
                ));
            }
        }
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        if (entityManager.getPlayer().getWeapon() instanceof PinkUltimateWeapon) {
            g.setFont(numberOfShotsFont);
            g.setColor(Color.WHITE);
            String text = "" + ((PinkUltimateWeapon) entityManager.getPlayer().getWeapon()).shotsLeft();
            int textWidth = g.getFontMetrics().stringWidth(text);
            g.drawString(text, game.getWidth() - ULTIMATE_ICON_WIDTH - textWidth - 15,
                    game.getHeight() - Creature.DEFAULT_CREATURE_HEIGHT - ULTIMATE_ICON_HEIGHT - 30);
        }
    }
}
