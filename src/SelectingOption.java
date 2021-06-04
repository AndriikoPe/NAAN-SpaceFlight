import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


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
    private final Option option;

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

    public SelectingOption(Game game, BufferedImage image, double initialAngle, Option option) {
        this.game = game;
        this.image = image;
        this.initialAngle = initialAngle;
        this.option = option;
    }

    public Option getOption() {
        return option;
    }

    public static Integer[] readInfo(String fileName) {
        try {
            List<String> players = Files.readAllLines(Paths.get(fileName));
            if (players.size() >= 1) return Arrays.stream(players.get(0)
                    .split(""))
                    .map(Integer::parseInt)
                    .toArray(Integer[]::new);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void createInfo(String fileName, SelectingOption[] options
            , Map<SelectingOption, Triple<String, Integer, Boolean>> players) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            for (SelectingOption option : options) {
                stringBuilder.append(players.get(option).isPurchased ? 1 : 0);
            }
            Files.write(Paths.get(fileName), stringBuilder.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
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
enum Option {
    PLAYER_DEFAULT, PLAYER_BLACK, PLAYER_BLUE, PLAYER_ORANGE, PLAYER_PINK, PLAYER_WHITE, PLAYER_GRAY, PLAYER_PURPLE,
    GUN_DEFAULT, GUN_TRIPLE, GUN_DOUBLE, GUN_MASSIVE;
}