public class ShootingEnemy extends Enemy {
    private final Weapon weapon;

    public ShootingEnemy(Game game, float x, float y, int width, int height, EntityManager entityManager) {
        super(game, x, y, width, height);
        weapon = new ShootingEnemyWeapon(game, entityManager, this);
    }

    @Override
    public void tick() {
        super.tick();
        weapon.shoot();
    }
}
