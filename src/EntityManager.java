import java.awt.*;
import java.util.ArrayList;

public class EntityManager {
    private Game game;
    private Player player;
    private ArrayList<Entity> entities;

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

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }

    public EntityManager(Game game, Player player) {
        this.game = game;
        this.player = player;
        entities = new ArrayList<>();
    }

    private void addEntity(Entity e) {
        entities.add(e);
    }


    public void tick() {
        for (Entity e: entities) {
            e.tick();
        }
        player.tick();
    }

    public void render(Graphics g) {
        for (Entity e: entities) {
            e.render(g);
        }
        player.render(g);
    }
}
