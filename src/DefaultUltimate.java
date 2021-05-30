public class DefaultUltimate extends Ultimate {
    private long lastTick;
    private final long TIME_TO_LOAD_ONE_PERCENT;

    public DefaultUltimate(Game game) {
        super(game);
        lastTick = System.nanoTime();
        TIME_TO_LOAD_ONE_PERCENT = 1_000_000_000 / 2;
        readiness = 0;
    }

    @Override
    public void use() {
        if (isReady()) {
            Assets.playSound(Assets.defaultUltimateUse);
            entityManager.getPlayer().heal(entityManager.getPlayer().getMaxHealth());
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
