public class DefaultWeapon extends Weapon {
    public DefaultWeapon(Game game, EntityManager entityManager) {
        super(game, entityManager, Weapon.DEFAULT_SHOT_PAUSE);
    }

    @Override
    public void shoot() {
        if (System.nanoTime() - lastShotTime > shotPause) {
            Bullet bullet = new Bullet(
                    game,
                    entityManager.getPlayer().getX() + entityManager.getPlayer().getWidth() / 2f - Bullet.BULLET_WIDTH / 2f,
                    entityManager.getPlayer().getY(),
                    0, 10,
                    Assets.playerBlue);
            bullet.setEntityManager(entityManager);
            entityManager.addEntity(bullet);
            Assets.playDefaultShotSound();
            lastShotTime = System.nanoTime();
        }
    }
}
