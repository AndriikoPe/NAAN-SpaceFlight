public class OrangeUltimate extends Ultimate {
    private long lastTick;
    private final long TIME_TO_LOAD_ONE_PERCENT;

    public OrangeUltimate(Game game) {
        super(game);
        lastTick = System.nanoTime();
        TIME_TO_LOAD_ONE_PERCENT = 1_000_000_000;
        readiness = 100;
    }

    @Override
    public void use() {
        if (isReady()) {
            Assets.playSound(Assets.orangeUltimateShot);
            OrangeUltimateBullet bullet = new OrangeUltimateBullet(
                    game,
                    entityManager.getPlayer().getX() + (entityManager.getPlayer().getWidth()
                            - OrangeUltimateBullet.DEFAULT_ULTIMATE_BULLET_WIDTH) / 2f, entityManager.getPlayer().getY(),
                    Assets.planetBlue);
            bullet.setEntityManager(entityManager);
            entityManager.addEntity(bullet);
            readiness = 0;
        }
    }

    @Override
    public void tick() {
        if (System.nanoTime() - lastTick >= TIME_TO_LOAD_ONE_PERCENT) {
            lastTick = System.nanoTime();
            if (readiness < 100) readiness++;
        }
    }
}
