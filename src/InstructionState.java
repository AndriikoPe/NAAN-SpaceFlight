import java.awt.*;
public class InstructionState extends State{

    private final int ESC_WIDTH = 80;
    private final int ESC_HEIGHT = 50;

    private final int ONE_SIZE = 40;

    private final int STRELKA_WIDTH = 100;
    private final int STRELKA_HEIGHT = 50;

    private final int WHITESPACE_WIDTH = 100;
    private final int WHITESPACE_HEIGHT = 30;

    private final int OK_BUTTON_WIDTH = 250;
    private final int OK_BUTTON_HEIGHT = 150;
    private final int X_OK_BUTTON_POS;
    private final int Y_OK_BUTTON_POS;
    private boolean okButtonActive = false;
    private final Rectangle okButtonRect;

    private static final Font font = new Font("Courier New", Font.BOLD, 24);

    public InstructionState(Game game) {
        super(game);
        X_OK_BUTTON_POS = (game.getWidth() - OK_BUTTON_WIDTH) / 2;
        Y_OK_BUTTON_POS = game.getHeight() - OK_BUTTON_HEIGHT - 30;
        okButtonRect = new Rectangle(X_OK_BUTTON_POS, Y_OK_BUTTON_POS, OK_BUTTON_WIDTH, OK_BUTTON_HEIGHT);
    }

    @Override
    public void tick() {
        if (okButtonRect.contains(game.mouseInput.getX(), game.mouseInput.getY())) {
            if (game.mouseInput.getLeftClicked()) {
                game.setMenuState();
            } else {
                okButtonActive = true;
            }
        }else {
            okButtonActive = false;
        }
    }

    public void drawingStr(String text, Graphics g, int yPosition){
        for (String l : text.split("\n")) {
            g.drawString(l, (game.getWidth() - g.getFontMetrics().stringWidth(l)) / 2, yPosition);
            yPosition += g.getFontMetrics().getHeight();
        }
    }
    @Override
    public void render(Graphics g) {
        g.setFont(font);
        g.setColor(Color.WHITE);
        drawingStr("1. Collect coins, destroy enemies\nand avoid collisions\nwith planets!", g, 100);
        drawingStr("2. Destroy bosses to go to\nthe next level!", g, 100 + 4 * g.getFontMetrics().getHeight());
        String text = "3. Press ";
        g.drawString(text, 10, 100 + 7 * g.getFontMetrics().getHeight());
        g.drawImage(Assets.arrows, 20 + g.getFontMetrics().stringWidth(text),
                95 + 7 * g.getFontMetrics().getHeight() - STRELKA_HEIGHT / 2, STRELKA_WIDTH, STRELKA_HEIGHT, null);
        String nexttext = "to control your \nspaceship!";
        int yPosition = 100 + 7 * g.getFontMetrics().getHeight();
        int x = 30 + g.getFontMetrics().stringWidth(text) + STRELKA_WIDTH;
        for (String l : nexttext.split("\n")) {
            g.drawString(l, x, yPosition);
            yPosition += 2*g.getFontMetrics().getHeight();
            x = 20;
        }
        g.drawString("4. Press ", 10, 100 + 11 * g.getFontMetrics().getHeight());
        g.drawImage(Assets.whitespace, 20 + g.getFontMetrics().stringWidth(text),
                95 + 11 * g.getFontMetrics().getHeight() - WHITESPACE_HEIGHT / 2, WHITESPACE_WIDTH,
                WHITESPACE_HEIGHT, null);
        g.drawString(" to shoot!", 30 + g.getFontMetrics().stringWidth(text) + WHITESPACE_WIDTH,
                100 + 11 * g.getFontMetrics().getHeight());
        text = "5. Press ";
        g.drawString(text, 10, 100 + 13 * g.getFontMetrics().getHeight());
        g.drawImage(Assets.one, 20 + g.getFontMetrics().stringWidth(text),
                95 + 13 * g.getFontMetrics().getHeight() - ONE_SIZE / 2, ONE_SIZE, ONE_SIZE, null);
        nexttext = "to activate your \nultimate!";
        yPosition = 100 + 13 * g.getFontMetrics().getHeight();
        x = 40 + g.getFontMetrics().stringWidth(text) + ONE_SIZE;
        for (String l : nexttext.split("\n")) {
            g.drawString(l, x, yPosition);
            yPosition += 2*g.getFontMetrics().getHeight();
            x = 20;
        }
        text = "6. Press ";
        g.drawString(text, 10, 100 + 17 * g.getFontMetrics().getHeight());
        g.drawImage(Assets.esc, 20 + g.getFontMetrics().stringWidth(text),
                95 + 17 * g.getFontMetrics().getHeight() - ESC_HEIGHT / 2, ESC_WIDTH, ESC_HEIGHT, null);
        g.drawString(" to pause!", 30 + g.getFontMetrics().stringWidth(text) + ESC_WIDTH,
                100 + 17 * g.getFontMetrics().getHeight());
        g.drawImage(okButtonActive ? Assets.okActive : Assets.okInactive,
                X_OK_BUTTON_POS,
                Y_OK_BUTTON_POS,
                OK_BUTTON_WIDTH,
                OK_BUTTON_HEIGHT,
                null);
    }
}
