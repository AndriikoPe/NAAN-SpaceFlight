import java.awt.*;

import static java.awt.Font.BOLD;

public class PauseState extends State {
    private final GameState previousState;
    private final long pauseTime;
    public static final long PAUSE_COOLDOWN = 1_000_000_000;
    private static final Font PAUSE_INFO_FONT = new Font("Time New Roman", BOLD, 35);
    private static final String PAUSE_INFO_STRING = "Paused. ESC to continue";

    public PauseState(Game game, GameState previousState) {
        super(game);
        this.previousState = previousState;
        pauseTime = System.nanoTime();
    }

    @Override
    public void tick() {
        if (System.nanoTime() - pauseTime > PAUSE_COOLDOWN && game.getKeyManager().pause) {
            State.setState(previousState);
            previousState.setLastPauseTime(System.nanoTime());
        }
    }

    @Override
    public void render(Graphics g) {
        previousState.render(g);
        g.setColor(Color.WHITE);
        g.setFont(PAUSE_INFO_FONT);
        g.drawString(PAUSE_INFO_STRING, 40, 100);
    }
}
