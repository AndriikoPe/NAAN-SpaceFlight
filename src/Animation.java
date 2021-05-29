import java.awt.*;
import java.awt.image.BufferedImage;

public class Animation {

    private int speed;
    private final int frames;
    private int index = 0;
    private int count = 0;
    private final BufferedImage img1, img2, img3, img4, img5, img6;
    private BufferedImage currentImg;

    public Animation(int speed, BufferedImage img1, BufferedImage img2, BufferedImage img3,
                     BufferedImage img4, BufferedImage img5, BufferedImage img6) {
        this.speed = speed;
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
        this.img4 = img4;
        this.img5 = img5;
        this.img6 = img6;
        currentImg = img1;
        frames = 6;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void runAnimation() {
        index++;
        if (index > speed) {
            index = 0;
            nextFrame();
        }
    }

    public void nextFrame() {
        if (frames == 6){
            if (count == 0) currentImg = img1;
            if (count == 1) currentImg = img2;
            if (count == 2) currentImg = img3;
            if (count == 3) currentImg = img4;
            if (count == 4) currentImg = img5;
            if (count == 5) currentImg = img6;
            count++;
            if (count > frames) count = 0;
        }
    }

    public void drawingAnimation(Graphics g, double x, double y, int offset){
        g.drawImage(currentImg, (int) x - offset, (int) y, null);
    }
}
