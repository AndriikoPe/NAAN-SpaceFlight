import java.util.Timer;
import java.util.TimerTask;

public class BlackUltimate extends Ultimate {
    private long lastTick;
    private long lastShot;
    private boolean isShooting;
    private int shotCounter;
    private final long TIME_TO_LOAD_ONE_PERCENT;
    private final int TOTAL_SHOTS = 10;
    private final int TOTAL_FIRES = 3;
    private final long SHOT_PAUSE = 450_000_000;
    private final float DX;

    public BlackUltimate(Game game) {
        super(game);
        readiness = 0;
        lastTick = System.nanoTime();
        TIME_TO_LOAD_ONE_PERCENT = 666_666_666;
        readiness = 100;
        isShooting = false;
        shotCounter = 0;
        DX = game.getWidth() / (float) TOTAL_SHOTS;
    }

    @Override
    public void tick() {
        if (System.nanoTime() - lastTick >= TIME_TO_LOAD_ONE_PERCENT) {
            lastTick = System.nanoTime();
            if (readiness < 100) readiness++;
        }
        if (isShooting && System.nanoTime() - lastShot >= SHOT_PAUSE) {
            if (shotCounter < TOTAL_FIRES) {
                shotCounter++;
                fire();
                lastShot = System.nanoTime();
            } else {
                shotCounter = 0;
                isShooting = false;
            }
        }
    }

    @Override
    public void use() {
        if (isReady()) {
            isShooting = true;
            fire();
            readiness = 0;
        }
    }

    private void fire() {
        Assets.playBlackUltimateShotSound();
        for (int i = 0; i < TOTAL_SHOTS; i++) {
            shoot(i * DX);
        }
    }

    private void shoot(float x) {
        Bullet bullet = new Bullet(game, x, game.getHeight(),0, 10, Assets.blackUltimateShot);
        bullet.setEntityManager(entityManager);
        bullet.setWidth(30);
        bullet.setHeight(30);
        bullet.setHealPerHit(5);
        entityManager.addEntity(bullet);
    }
}
