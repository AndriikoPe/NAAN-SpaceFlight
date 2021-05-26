import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;


public class SelectingOption implements Comparable<SelectingOption> {

    private final BufferedImage image;
    private double x;
    private double z;
    private int imgX;
    private int imgY;
    private int height;
    private int width;
    private Game game;
    private final double initialAngle;
    private final AffineTransform transform;

    public int getImgX() {
        return imgX;
    }

    public int getImgY() {
        return imgY;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public SelectingOption(Game game, BufferedImage image, double initialAngle) {
        this.game = game;
        this.image = image;
        this.initialAngle = initialAngle;
        transform = new AffineTransform();
    }


    public void tick(double angle) {
        z = 1.2 + Math.sin(angle + initialAngle) / 2.0;
        x = 80 * Math.cos(angle + initialAngle);
    }

    public void render(Graphics2D g) {
        height = (int) Math.round(image.getHeight() * z);
        width = (int) Math.round(image.getWidth() * z);
        imgX = (int) Math.round((x + (game.getWidth() - image.getWidth() * z) / 2));
        imgY = (int) Math.round((game.getHeight() - image.getHeight() * z) / 2 + x);
        g.drawImage(image, imgX, imgY,width, height, null);
    }

    @Override
    public int compareTo(SelectingOption o) {
        return (int) Math.signum(z - o.z);
    }

}