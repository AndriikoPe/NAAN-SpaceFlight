import java.awt.*;

public class MenuState extends State {
    private final int PLAY_BUTTON_WIDTH = 330;
    private final int PLAY_BUTTON_HEIGHT = 150;
    private final int EXIT_BUTTON_WIDTH = 300;
    private final int EXIT_BUTTON_HEIGHT = 150;

    private final int X_PLAY_BUTTON_POS;
    private final int Y_PLAY_BUTTON_POS;
    private final int X_EXIT_BUTTON_POS;
    private final int Y_EXIT_BUTTON_POS;

    private boolean playButtonActive = false;
    private boolean exitButtonActive = false;

    public void setPlayButtonActive(boolean playButtonActive) {
        this.playButtonActive = playButtonActive;
    }

    public void setExitButtonActive(boolean exitButtonActive) {
        this.exitButtonActive = exitButtonActive;
    }

    public int getPlayButtonWidth() {
        return PLAY_BUTTON_WIDTH;
    }

    public int getPlayButtonHeight() {
        return PLAY_BUTTON_HEIGHT;
    }

    public int getExitButtonWidth() {
        return EXIT_BUTTON_WIDTH;
    }

    public int getExitButtonHeight() {
        return EXIT_BUTTON_HEIGHT;
    }

    public int getX_PLAY_BUTTON_POS() {
        return X_PLAY_BUTTON_POS;
    }

    public int getY_PLAY_BUTTON_POS() {
        return Y_PLAY_BUTTON_POS;
    }

    public int getX_EXIT_BUTTON_POS() {
        return X_EXIT_BUTTON_POS;
    }

    public int getY_EXIT_BUTTON_POS() {
        return Y_EXIT_BUTTON_POS;
    }

    private MouseInput mouseInput;

    public MouseInput getMouseInput() {
        return mouseInput;
    }

    public MenuState(Game game) {
        super(game);
        X_PLAY_BUTTON_POS = (game.getWidth() - PLAY_BUTTON_WIDTH) / 2;
        Y_PLAY_BUTTON_POS = (game.getHeight() - PLAY_BUTTON_HEIGHT) / 4;
        X_EXIT_BUTTON_POS = (game.getWidth() - EXIT_BUTTON_WIDTH) / 2;
        Y_EXIT_BUTTON_POS = (game.getHeight() - EXIT_BUTTON_HEIGHT) * 3 / 4;
        mouseInput = new MouseInput(game, this);
        game.getDisplay().getCanvas().addMouseListener(mouseInput);
        game.getDisplay().getCanvas().addMouseMotionListener(mouseInput);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(playButtonActive ? Assets.playButtonActive : Assets.playButtonInactive,
                X_PLAY_BUTTON_POS,
                Y_PLAY_BUTTON_POS,
                PLAY_BUTTON_WIDTH,
                PLAY_BUTTON_HEIGHT,
                null);
        g.drawImage(exitButtonActive ? Assets.exitButtonActive : Assets.exitButtonInactive,
                X_EXIT_BUTTON_POS,
                Y_EXIT_BUTTON_POS,
                PLAY_BUTTON_WIDTH,
                PLAY_BUTTON_HEIGHT,
                null);
    }
}
