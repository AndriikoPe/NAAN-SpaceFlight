import java.awt.*;

public class MenuState extends State {
    private final int PLAY_BUTTON_WIDTH = 330;
    private final int PLAY_BUTTON_HEIGHT = 150;
    private final int EXIT_BUTTON_WIDTH = 300;
    private final int EXIT_BUTTON_HEIGHT = 150;
    private final int INSTRUCTION_BUTTON_SIZE = 100;

    private final int X_PLAY_BUTTON_POS;
    private final int Y_PLAY_BUTTON_POS;
    private final int X_EXIT_BUTTON_POS;
    private final int Y_EXIT_BUTTON_POS;
    private final int X_INSTRUCTION_BUTTON_POS = 10;
    private final int Y_INSTRUCTION_BUTTON_POS;

    private boolean playButtonActive = false;
    private boolean exitButtonActive = false;
    private boolean instructionButtonActive = false;

    private final Rectangle playButtonRect;
    private final Rectangle exitButtonRect;
    private final Rectangle instructionButtonRect;


    public MenuState(Game game) {
        super(game);
        X_PLAY_BUTTON_POS = (game.getWidth() - PLAY_BUTTON_WIDTH) / 2;
        Y_PLAY_BUTTON_POS = (game.getHeight() - PLAY_BUTTON_HEIGHT) / 4;
        X_EXIT_BUTTON_POS = (game.getWidth() - EXIT_BUTTON_WIDTH) / 2;
        Y_EXIT_BUTTON_POS = (game.getHeight() - EXIT_BUTTON_HEIGHT) * 3 / 4;
        Y_INSTRUCTION_BUTTON_POS = game.getHeight() - INSTRUCTION_BUTTON_SIZE - 10;

        playButtonRect = new Rectangle(X_PLAY_BUTTON_POS, Y_PLAY_BUTTON_POS, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
        exitButtonRect = new Rectangle(X_EXIT_BUTTON_POS, Y_EXIT_BUTTON_POS, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
        instructionButtonRect = new Rectangle(X_INSTRUCTION_BUTTON_POS, Y_INSTRUCTION_BUTTON_POS, INSTRUCTION_BUTTON_SIZE,
                INSTRUCTION_BUTTON_SIZE);
    }

    @Override
    public void tick() {
        if (playButtonRect.contains(game.mouseInput.getX(), game.mouseInput.getY())) {
            if (game.mouseInput.getLeftClicked()) {
                game.setSelectPlayerState();
            } else {
                playButtonActive = true;
                exitButtonActive = false;
                instructionButtonActive = false;
            }
        } else if (exitButtonRect.contains(game.mouseInput.getX(), game.mouseInput.getY())) {
            if (game.mouseInput.getLeftClicked()) {
                game.getDisplay().getFrame().dispose();
                System.exit(0);
            } else {
                playButtonActive = false;
                instructionButtonActive = false;
                exitButtonActive = true;
            }
        } else if (instructionButtonRect.contains(game.mouseInput.getX(), game.mouseInput.getY())) {
            if (game.mouseInput.getLeftClicked()) {
                game.setInstructionState();
            } else {
                playButtonActive = false;
                instructionButtonActive = true;
                exitButtonActive = false;
            }
        } else {
            playButtonActive = false;
            exitButtonActive = false;
            instructionButtonActive = false;
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
        g.drawImage(instructionButtonActive ? Assets.instructionActive : Assets.instructionInactive,
                X_INSTRUCTION_BUTTON_POS,
                Y_INSTRUCTION_BUTTON_POS,
                INSTRUCTION_BUTTON_SIZE,
                INSTRUCTION_BUTTON_SIZE,
                null);
    }
}
