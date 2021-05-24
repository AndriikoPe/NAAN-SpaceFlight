import java.awt.*;

public class GameState extends State {
    private final EntityManager entityManager;

    public GameState(Game game) {
        super(game);
        int startX = (game.getDisplay().getCanvas().getWidth() - Creature.DEFAULT_CREATURE_WIDTH) / 2;
        int startY = game.getDisplay().getCanvas().getHeight() - Creature.DEFAULT_CREATURE_HEIGHT - 20;
        entityManager = new EntityManager(game, new Player(game, startX, startY));
        entityManager.addEntity(new Enemy(game, 0, -60, 70, 100));
        entityManager.addEntity(new Enemy(game, 100, -90, 70, 100));
        entityManager.addEntity(new Enemy(game, 200, -20, 70, 100));
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public void tick() {
        entityManager.tick();
    }

    @Override
    public void render(Graphics g) {
        entityManager.render(g);
    }
}
