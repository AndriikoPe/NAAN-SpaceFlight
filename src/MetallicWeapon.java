public class MetallicWeapon extends Weapon {
    public MetallicWeapon(Game game, EntityManager entityManager) {
        super(game, entityManager, Weapon.METALLIC_SHOT_PAUSE);
    }

    @Override
    public void shoot() {
        if (System.nanoTime() - lastShotTime > shotPause) {
            Bullet bullet = new Bullet(
                    game,
                    entityManager.getPlayer().getX() + entityManager.getPlayer().getWidth() / 2f - Bullet.BULLET_WIDTH / 2f,
                    entityManager.getPlayer().getY(),
                    0, 8,
                    Assets.playerPink);
            bullet.setEntityManager(entityManager);
            bullet.setDestroyable(false);
            bullet.setDamage(35);
            entityManager.addEntity(bullet);
            Assets.playMetallicShotSound();
            lastShotTime = System.nanoTime();
        }
    }
}
