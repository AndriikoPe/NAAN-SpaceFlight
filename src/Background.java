import java.awt.*;
import java.awt.image.BufferedImage;

public class Background {
    private Game game;

    private BufferedImage bgImage;
    private BufferedImage bgImageCopy;

    private static final int BACKGROUND_SPEED = 5;
    private final int BACKGROUND_HEIGHT;

    private int yPos;
    private int yCopyPos;

    public Background(Game game) {
        this.game = game;
        bgImage = ImageLoader.loadImage("textures/background4.jpg");
        bgImageCopy = ImageLoader.loadImage("textures/background4.jpg");
        BACKGROUND_HEIGHT = bgImage.getHeight();
        yPos = game.getHeight() - BACKGROUND_HEIGHT;
        yCopyPos = yPos - BACKGROUND_HEIGHT;
    }

    public void tick() {
        yPos += BACKGROUND_SPEED;
        yCopyPos += BACKGROUND_SPEED;
        if (yPos >= game.getHeight()) {
            yPos = yCopyPos - BACKGROUND_HEIGHT;
        }
        if (yCopyPos >= game.getHeight()) {
            yCopyPos = yPos - BACKGROUND_HEIGHT;
        }
    }

    public void render(Graphics g) {
        g.drawImage(bgImage, 0, yPos, game.getWidth(), BACKGROUND_HEIGHT, null);
        g.drawImage(bgImageCopy, 0, yCopyPos, game.getWidth(), BACKGROUND_HEIGHT, null);
    }
}
