import java.awt.*;
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
        entityManager.getPlayer().setWeapon(new MetallicWeapon(game, entityManager));
        r = new Random();
        spawnEnemies();
        createPlanets();
    }

    private  void createPlanets(){
        for (int i = 0; i < 6; i++) {
            entityManager.addEntity(new Planet(game,r.nextInt(game.getWidth() - Creature.DEFAULT_CREATURE_WIDTH),
                    -r.nextInt(Creature.DEFAULT_CREATURE_HEIGHT) - Creature.DEFAULT_CREATURE_HEIGHT,
                    100, 100));
        }
    }

    private void spawnEnemies() {
        int count = r.nextInt(6);
        for (int i = 0; i < count; i++) {
            entityManager.addEntity(new ShootingEnemy(
                    game, r.nextInt(game.getWidth() - Creature.DEFAULT_CREATURE_WIDTH),
                    -r.nextInt(Creature.DEFAULT_CREATURE_HEIGHT) - Creature.DEFAULT_CREATURE_HEIGHT,
                    70, 100,
                    entityManager));
        }
    }

    @Override
    public void tick() {
        if (entityManager.getEntities().stream().filter(e -> !e.isFriendly()).count() < 1) spawnEnemies();
        entityManager.tick();
        if (entityManager.getPlayer().getHealth() <= 0) {
            game.setMenuState();
        }
    }

    @Override
    public void render(Graphics g) {
        float playerHealth = entityManager.getPlayer().getHealth() / (float) Player.MAX_PLAYER_HEALTH;
        g.setColor(new Color(Math.round(255 * (1 - playerHealth)), Math.round(255 * playerHealth), 0));
        g.fillRect(6, game.getHeight() - 16, Math.round((game.getWidth() - 12) * playerHealth), 10);
        entityManager.render(g);
    }
}
