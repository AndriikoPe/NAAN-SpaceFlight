import java.awt.*;
import java.util.LinkedList;

public class EntityManager {
    private Game game;
    private Player player;
    private LinkedList<Entity> entities;

    private long lastShotTime;

    private final long SHOT_PAUSE = 1000000000 / 2;

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public LinkedList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(LinkedList<Entity> entities) {
        this.entities = entities;
    }

    public EntityManager(Game game, Player player) {
        this.game = game;
        this.player = player;
        entities = new LinkedList<>();
        entities.add(player);
    }

    public void addEntity(Entity e) {
        entities.add(e);
    }

    public void removeEntity(Entity e) {
        entities.add(e);
    }

    public void shoot() {
        if (System.nanoTime() - lastShotTime > SHOT_PAUSE) {
            addEntity(new Bullet(
                    game,
                    player.getX() + player.getWidth() / 2f - Bullet.BULLET_WIDTH / 2f,
                    player.getY()));
            lastShotTime = System.nanoTime();
        }
    }

    public void tick() {
        if (game.getKeyManager().shoot) shoot();
        for (int i = 0; i < entities.size(); i++) {
            Entity e = entities.get(i);
            if (e instanceof Bullet && e.getY() < 0) entities.remove(e);
            e.tick();
        }
    }

    public void render(Graphics g) {
        for (Entity e: entities) {
            e.render(g);
        }
    }
}
