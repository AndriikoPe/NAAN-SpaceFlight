import java.awt.*;
import java.util.LinkedList;

public class EntityManager {
    private Game game;
    private Player player;
    private LinkedList<Entity> entities;

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
        entities.remove(e);
    }

    public void tick() {
        for (int i = 0; i < entities.size(); i++) {
            Entity e = entities.get(i);
            if (e.isOffscreen()) entities.remove(e);
            else e.tick();
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < entities.size(); i++) {
            Entity e = entities.get(i);
            if (e != null) e.render(g);
        }
    }
}
