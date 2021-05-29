public abstract class Weapon {
    protected Game game;
    protected long shotPause;
    protected long lastShotTime;
    protected EntityManager entityManager;

    protected static final long DEFAULT_SHOT_PAUSE = 1000000000 / 3;
    protected static final long METALLIC_SHOT_PAUSE = 1000000000 / 2;
    protected static final long BARRAGE_SHOT_PAUSE = 1000000000;
    protected static final long ENEMY_SHOT_PAUSE = 1000000000 * 2;

    public Weapon(Game game, EntityManager entityManager, long shotPause) {
        this.game = game;
        this.entityManager = entityManager;
        this.shotPause = shotPause;
    }

    public void shoot() { }
}
