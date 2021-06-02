import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Map;
import java.util.Random;

public class GameState extends State {
    private final EntityManager entityManager;
    private final Random r;

    public GameState(Game game) {
        super(game);
        int startX = (game.getDisplay().getCanvas().getWidth() - Creature.DEFAULT_CREATURE_WIDTH) / 2;
        int startY = game.getDisplay().getCanvas().getHeight() - Creature.DEFAULT_CREATURE_HEIGHT - 20;
        entityManager = new EntityManager(game, new Player(game, startX, startY, PlayerSelection.DEFAULT));
        entityManager.getPlayer().setEntityManager(entityManager);
        entityManager.getPlayer().setWeapon(new DoubleWeapon(game, entityManager));
        r = new Random();
        Patterns.init(game);
        spawnEnemies();
    }

    private void spawnEnemies() {
        Map<Point2D, ID> map = Patterns.nextPattern();
        for (Point2D point2D: map.keySet()) {
            switch (map.get(point2D)){
                case COIN:
                    entityManager.addEntity(new Coin(game, (float) point2D.getX(), (float) point2D.getY(),
                            18, 20));
                    break;
                case PLANET:
                    entityManager.addEntity(new Planet(game, (float) point2D.getX(), (float) point2D.getY(),
                            Assets.PLANET_SIZE, Assets.PLANET_SIZE));
                    break;
                case ENEMY:
                    entityManager.addEntity(new Enemy(game, (float) point2D.getX(), (float) point2D.getY(),
                            Enemy.DEFAULT_CREATURE_WIDTH, Enemy.DEFAULT_CREATURE_HEIGHT));
                    break;
                case SHOOTING_ENEMY:
                    entityManager.addEntity(new ShootingEnemy(game, (float) point2D.getX(), (float) point2D.getY(),
                            Enemy.DEFAULT_CREATURE_WIDTH, Enemy.DEFAULT_CREATURE_HEIGHT, entityManager));
            }
        }
    }

    @Override
    public void tick() {
        if (entityManager.getEntities().stream().filter(e -> !e.isFriendly()).count() < 4) spawnEnemies();
        entityManager.tick();
        if (entityManager.getPlayer().getHealth() <= 0) {
            game.setMenuState();
        }
    }

    @Override
    public void render(Graphics g) {
        float playerHealth = entityManager.getPlayer().getHealth() / (float) entityManager.getPlayer().getMaxHealth();
        g.setColor(new Color(Math.round(255 * (1 - playerHealth)), Math.round(255 * playerHealth), 0));
        g.fillRect(6, game.getHeight() - 16, Math.round((game.getWidth() - 12) * playerHealth), 10);
        entityManager.render(g);
    }
}
