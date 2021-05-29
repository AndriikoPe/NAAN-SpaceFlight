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

    private final Rectangle playButtonRect;
    private final Rectangle exitButtonRect;

    private final MouseInput mouseInput;

    public MouseInput getMouseInput() {
        return mouseInput;
    }

    public MenuState(Game game) {
        super(game);
        X_PLAY_BUTTON_POS = (game.getWidth() - PLAY_BUTTON_WIDTH) / 2;
        Y_PLAY_BUTTON_POS = (game.getHeight() - PLAY_BUTTON_HEIGHT) / 4;
        X_EXIT_BUTTON_POS = (game.getWidth() - EXIT_BUTTON_WIDTH) / 2;
        Y_EXIT_BUTTON_POS = (game.getHeight() - EXIT_BUTTON_HEIGHT) * 3 / 4;
        playButtonRect = new Rectangle(X_PLAY_BUTTON_POS, Y_PLAY_BUTTON_POS, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
        exitButtonRect = new Rectangle(X_EXIT_BUTTON_POS, Y_EXIT_BUTTON_POS, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
        mouseInput = new MouseInput();
        game.getDisplay().getCanvas().addMouseListener(mouseInput);
        game.getDisplay().getCanvas().addMouseMotionListener(mouseInput);
    }

    @Override
    public void tick() {
        if (playButtonRect.contains(mouseInput.getX(), mouseInput.getY())) {
            if (mouseInput.getLeftClicked()) {
//                game.setSelectPlayerState();
                game.setGameState();
            } else {
                playButtonActive = true;
                exitButtonActive = false;
            }
        } else if (exitButtonRect.contains(mouseInput.getX(), mouseInput.getY())) {
            if (mouseInput.getLeftClicked()) {
                game.getDisplay().getFrame().dispose();
                System.exit(0);
            } else {
                playButtonActive = false;
                exitButtonActive = true;
            }
        } else  {
            playButtonActive = false;
            exitButtonActive = false;
        }
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
