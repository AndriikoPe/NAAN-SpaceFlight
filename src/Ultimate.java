public abstract class Ultimate extends Entity {
    protected static final int ULTIMATE_ICON_HEIGHT = 40;
    protected static final int ULTIMATE_ICON_WIDTH = 40;
    protected int readiness;

    public Ultimate(Game game) {
        super(game,
                game.getWidth() - ULTIMATE_ICON_WIDTH - 10,
                game.getHeight() - Creature.DEFAULT_CREATURE_HEIGHT - ULTIMATE_ICON_HEIGHT - 30,
                ULTIMATE_ICON_WIDTH, ULTIMATE_ICON_HEIGHT);
        isFriendly = true;
    }

    public abstract void use();

    public boolean isReady() {
        return readiness == 100;
    }

    @Override
    public void tick() {
    }
}
