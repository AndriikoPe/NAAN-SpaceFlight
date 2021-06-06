import java.awt.*;

public class FailState extends State {
    private final int OK_BUTTON_WIDTH = 250;
    private final int OK_BUTTON_HEIGHT = 150;
    private final int X_OK_BUTTON_POS;
    private final int Y_OK_BUTTON_POS;
    private boolean okButtonActive = false;
    private final Rectangle okButtonRect;
    private static final Font font = new Font("Courier New", Font.BOLD, 46);
    private final int runScore;

    public FailState(Game game) {
        super(game);
        X_OK_BUTTON_POS = (game.getWidth() - OK_BUTTON_WIDTH) / 2;
        Y_OK_BUTTON_POS = game.getHeight() - OK_BUTTON_HEIGHT - 30;
        okButtonRect = new Rectangle(X_OK_BUTTON_POS, Y_OK_BUTTON_POS, OK_BUTTON_WIDTH, OK_BUTTON_HEIGHT);
        runScore = game.getRunScore();
    }

    @Override
    public void tick() {
        if (okButtonRect.contains(game.mouseInput.getX(), game.mouseInput.getY())) {
            if (game.mouseInput.getLeftClicked()) {
                game.setMenuState();
            } else {
                okButtonActive = true;
            }
        } else {
            okButtonActive = false;
        }

    }

    @Override
    public void render(Graphics g) {
        g.setFont(font);
        g.setColor(Color.WHITE);
        String text = "You failed!";
        g.drawString(text, (game.getWidth() - g.getFontMetrics().stringWidth(text)) / 2,
                game.getHeight() / 2 - g.getFontMetrics().getHeight() * 4);
        g.drawImage(okButtonActive ? Assets.okActive : Assets.okInactive,
                X_OK_BUTTON_POS,
                Y_OK_BUTTON_POS,
                OK_BUTTON_WIDTH,
                OK_BUTTON_HEIGHT,
                null);
        g.drawImage(Assets.failPicture, (game.getWidth() - 240) / 2, game.getHeight() - OK_BUTTON_HEIGHT - 325,
                240, 290, null);
        g.setFont(new Font("Courier New", Font.BOLD, 28));
        text = "Score for run: " + runScore;
        g.drawString(text, (game.getWidth() - g.getFontMetrics().stringWidth(text)) / 2,
                game.getHeight() / 2 - g.getFontMetrics().getHeight() * 4);
        g.drawImage(Assets.coin0, (game.getWidth() - g.getFontMetrics().stringWidth(text)) / 2 +
                        g.getFontMetrics().stringWidth(text) + 10,
                game.getHeight() / 2 - g.getFontMetrics().getHeight() * 4 - Assets.COIN_HEIGHT + 3, null);
    }
}
