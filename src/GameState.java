import java.awt.*;
import java.util.Random;

public class GameState extends State {
    private final EntityManager entityManager;
    private final Random r;

    public GameState(Game game) {
        super(game);
        int startX = (game.getDisplay().getCanvas().getWidth() - Creature.DEFAULT_CREATURE_WIDTH) / 2;
        int startY = game.getDisplay().getCanvas().getHeight() - Creature.DEFAULT_CREATURE_HEIGHT - 20;
        entityManager = new EntityManager(game, new Player(game, startX, startY));
        r = new Random();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    private void spawnEnemies() {
        int count = r.nextInt(4);
        for (int i = 0; i < count; i++) {
            entityManager.addEntity(new Enemy(
                    game,
                    r.nextInt(game.getWidth() - Creature.DEFAULT_CREATURE_WIDTH),
                    -r.nextInt(Creature.DEFAULT_CREATURE_HEIGHT) - Creature.DEFAULT_CREATURE_HEIGHT,
                    70, 100));
        }
    }

    @Override
    public void tick() {
        if (entityManager.getEntities().stream().filter(e -> !e.isFriendly()).count() < 1) spawnEnemies();
        entityManager.tick();
    }

    @Override
    public void render(Graphics g) {
        entityManager.render(g);
    }
}
