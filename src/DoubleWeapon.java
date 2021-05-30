public class DoubleWeapon extends Weapon {
    public DoubleWeapon(Game game, EntityManager entityManager) {
        super(game, entityManager, DOUBLE_SHOT_PAUSE);
    }

    @Override
    public void shoot() {
        if (System.nanoTime() - lastShotTime > shotPause) {
            Bullet bulletLeft = new Bullet(
                    game,
                    entityManager.getPlayer().getX(),
                    entityManager.getPlayer().getY(),
                    0, 7,
                    Assets.playerBlue);
            Bullet bulletRight = new Bullet(
                    game,
                    entityManager.getPlayer().getX() + entityManager.getPlayer().getWidth() - Bullet.BULLET_WIDTH,
                    entityManager.getPlayer().getY(),
                    0, 7,
                    Assets.playerBlue);
            bulletRight.setEntityManager(entityManager);
            bulletLeft.setEntityManager(entityManager);
            entityManager.addEntity(bulletRight);
            entityManager.addEntity(bulletLeft);
            Assets.playDoubleShotSound();
            lastShotTime = System.nanoTime();
        }
    }
}
