import java.util.ArrayList;
import java.util.List;

public class PinkUltimateWeapon extends Weapon {
    private final int STARTING_SHOTS_NUMBER = 5;
    private int totalShots = STARTING_SHOTS_NUMBER;
    private int numberOfShots;
    private final Weapon playerWeapon;

    public void addShots() {
        totalShots += STARTING_SHOTS_NUMBER;
    }

    public int shotsLeft() {
        return totalShots - numberOfShots;
    }

    public PinkUltimateWeapon(Game game, EntityManager entityManager, Weapon playerWeapon) {
        super(game, entityManager, Weapon.DEFAULT_SHOT_PAUSE);
        this.playerWeapon = playerWeapon;
    }

    @Override
    public void shoot() {
        if (System.nanoTime() - lastShotTime <= shotPause) return;
        float x = entityManager.getPlayer().getX() + entityManager.getPlayer().getWidth() / 2f - Bullet.BULLET_WIDTH / 2f;
        float y = entityManager.getPlayer().getY();
        List<Bullet> bullets = new ArrayList<>();
        bullets.add(new Bullet(game, x, y, -3f, 8, Assets.fivefoldShot));
        bullets.add(new Bullet(game, x, y, -2f, 9, Assets.fivefoldShot));
        bullets.add(new Bullet(game, x, y, 0, 10, Assets.fivefoldShot));
        bullets.add(new Bullet(game, x, y, 2f, 9, Assets.fivefoldShot));
        bullets.add(new Bullet(game, x, y, 3f, 8, Assets.fivefoldShot));
        bullets.forEach(e -> {
            e.setEntityManager(entityManager);
            e.setHealPerHit(5);
            entityManager.addEntity(e);
        });
        Assets.playPinkUltimateShotSound();
        if (++numberOfShots >= totalShots) {
            entityManager.getPlayer().setWeapon(playerWeapon);
            playerWeapon.setLastShotTime(System.nanoTime());
        } else {
            lastShotTime = System.nanoTime();
        }
    }
}
