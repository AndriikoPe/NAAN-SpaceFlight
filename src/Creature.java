public abstract class Creature extends Entity {
    public static final int DEFAULT_HEALTH = 100;
    public static final float DEFAULT_SPEED = 3.0f;
    public static final int DEFAULT_CREATURE_WIDTH = 70,
                            DEFAULT_CREATURE_HEIGHT = 100;

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Creature(Game game, float x, float y, int width, int height) {
        super(game, x, y, width, height);
        health = DEFAULT_HEALTH;
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
    }

    public void move() {
        x += xMove;
        y += yMove;
    }

    protected int health;
    protected float speed;
    protected float xMove;
    protected float yMove;
}
