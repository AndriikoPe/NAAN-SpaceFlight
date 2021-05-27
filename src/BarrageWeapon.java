public class BarrageWeapon extends Weapon {
    public BarrageWeapon(Game game, EntityManager entityManager) {
        super(game, entityManager, Weapon.BARRAGE_SHOT_PAUSE);
    }

    @Override
    public void shoot() {
        if (System.nanoTime() - lastShotTime <= shotPause) return;
        Bullet bulletLeft = new Bullet(
                game,
                entityManager.getPlayer().getX() + entityManager.getPlayer().getWidth() / 2f - Bullet.BULLET_WIDTH / 2f,
                entityManager.getPlayer().getY(),
                -2, 8,
                Assets.playerBlack);
        Bullet bulletCenter = new Bullet(
                game,
                entityManager.getPlayer().getX() + entityManager.getPlayer().getWidth() / 2f - Bullet.BULLET_WIDTH / 2f,
                entityManager.getPlayer().getY(),
                0, 9,
                Assets.playerBlack);
        Bullet bulletRight = new Bullet(
                game,
                entityManager.getPlayer().getX() + entityManager.getPlayer().getWidth() / 2f - Bullet.BULLET_WIDTH / 2f,
                entityManager.getPlayer().getY(),
                2, 8,
                Assets.playerBlack);
        bulletLeft.setEntityManager(entityManager);
        bulletCenter.setEntityManager(entityManager);
        bulletRight.setEntityManager(entityManager);
        entityManager.addEntity(bulletLeft);
        entityManager.addEntity(bulletCenter);
        entityManager.addEntity(bulletRight);
        Assets.playBarrageShotSound();
        lastShotTime = System.nanoTime();
    }
}
