import java.awt.*;

public abstract class Entity {
    protected Game game;
    protected float x, y;
    protected int width, height;
    protected boolean isOffscreen;
    protected boolean isFriendly;

    public Entity(Game game, float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.game = game;
        isOffscreen = false;
        isFriendly = true;
        bounds = new Rectangle(Math.round(x), Math.round(y), width, height);
    }

    public boolean isFriendly() {
        return isFriendly;
    }

    public boolean isOffscreen() {
        return isOffscreen;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Rectangle getBounds() {
        return new Rectangle(Math.round(x), Math.round(y), width, height);
    }

    protected Rectangle bounds;

    public abstract void tick();
    public abstract void render(Graphics g);
}
