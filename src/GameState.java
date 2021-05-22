import java.awt.*;

public class GameState extends State {
    private EntityManager entityManager;

    public GameState(Game game) {
        super(game);
        int startX = (game.getDisplay().getCanvas().getWidth() - Creature.DEFAULT_CREATURE_WIDTH) / 2;
        int startY = game.getDisplay().getCanvas().getHeight() - Creature.DEFAULT_CREATURE_HEIGHT - 20;
        entityManager = new EntityManager(game, new Player(game, startX, startY));
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
