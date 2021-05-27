import java.awt.*;

public class Player extends Creature {
    public static final int MAX_PLAYER_HEALTH = 100;
    private EntityManager entityManager;
    private Weapon weapon;

    public Player(Game game, float x, float y) {
        super(game, x, y, DEFAULT_CREATURE_WIDTH, DEFAULT_CREATURE_HEIGHT);
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void tick() {
        getInput();
        move();
    }

    public void shoot() {
        if (weapon != null) weapon.shoot();
    }

    public void getInput() {
        xMove = 0;
        yMove = 0;
        if (game.getKeyManager().left) {
            if (x > 0) xMove = -speed;
        }
        if (game.getKeyManager().right) {
            if (x + width < game.getDisplay().getCanvas().getWidth()) xMove = speed;
        }
        for (int i = 0; i < entityManager.getEntities().size(); i++) {
            Entity e = entityManager.getEntities().get(i);
            if (!e.isFriendly()) {
                if (getBounds().intersects(e.getBounds())) {
                    entityManager.getEntities().remove(e);
                    health -= 20;
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.playerDefault, Math.round(x), Math.round(y), width, height, null);
    }
}
