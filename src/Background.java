import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Background {
    private final Game game;

    private BufferedImage bgImage;
    private BufferedImage bgImageCopy;

    private final ArrayList<BufferedImage> allImages;

    private static final int BACKGROUND_SPEED = 5;
    private final int BACKGROUND_HEIGHT;

    private int yPos;
    private int yCopyPos;

    private int currentBackgroundNumber = 0;
    private int changedImages = 0;

    public Background(Game game) {
        this.game = game;
        allImages = new ArrayList<>();
        allImages.add(ImageLoader.loadImage("textures/background.jpg"));
        allImages.add(ImageLoader.loadImage("textures/background2.jpg"));
        allImages.add(ImageLoader.loadImage("textures/background3.jpg"));
        allImages.add(ImageLoader.loadImage("textures/background4.jpg"));
        bgImage = allImages.get(0);
        bgImageCopy = copyImage(bgImage);
        BACKGROUND_HEIGHT = bgImage.getHeight();
        yPos = game.getHeight() - BACKGROUND_HEIGHT;
        yCopyPos = yPos - BACKGROUND_HEIGHT;
    }

    public void nextLevel() {
        if (currentBackgroundNumber + 1 < allImages.size()) {
            currentBackgroundNumber++;
            changedImages = 0;
        }
    }

    private BufferedImage copyImage(BufferedImage source){
        BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
        Graphics g = b.getGraphics();
        g.drawImage(source, 0, 0, null);
        g.dispose();
        return b;
    }

    public void tick() {
        yPos += BACKGROUND_SPEED;
        yCopyPos += BACKGROUND_SPEED;
        if (yPos >= game.getHeight()) {
            yPos = yCopyPos - BACKGROUND_HEIGHT;
            if (changedImages == 0) {
                changedImages++;
                bgImage = allImages.get(currentBackgroundNumber);
            } else if (changedImages == 1) {
                changedImages++;
                bgImage = copyImage(allImages.get(currentBackgroundNumber));
            }
        }
        if (yCopyPos >= game.getHeight()) {
            yCopyPos = yPos - BACKGROUND_HEIGHT;
            if (changedImages == 0) {
                changedImages++;
                bgImageCopy = allImages.get(currentBackgroundNumber);
            } else if (changedImages == 1) {
                    changedImages++;
                    bgImageCopy = copyImage(allImages.get(currentBackgroundNumber));
            }
        }
    }

    public void render(Graphics g) {
        g.drawImage(bgImage, 0, yPos, game.getWidth(), BACKGROUND_HEIGHT, null);
        g.drawImage(bgImageCopy, 0, yCopyPos, game.getWidth(), BACKGROUND_HEIGHT, null);
    }
}
