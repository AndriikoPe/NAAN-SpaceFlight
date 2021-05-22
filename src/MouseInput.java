import org.w3c.dom.css.Rect;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInput implements MouseListener, MouseMotionListener {
    private Game game;
    private MenuState state;

    private Rectangle playButtonRect;
    private Rectangle exitButtonRect;

    public MouseInput(Game game, MenuState state) {
        this.game = game;
        this.state = state;
        playButtonRect = new Rectangle(
                state.getX_PLAY_BUTTON_POS(),
                state.getY_PLAY_BUTTON_POS(),
                state.getPlayButtonWidth(),
                state.getPlayButtonHeight());
        exitButtonRect = new Rectangle(
                state.getX_EXIT_BUTTON_POS(),
                state.getY_EXIT_BUTTON_POS(),
                state.getExitButtonWidth(),
                state.getExitButtonHeight());
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (playButtonRect.contains(e.getX(), e.getY())) {
            game.setGameState();
        } else if (exitButtonRect.contains(e.getX(), e.getY())) {
            game.getDisplay().getFrame().dispose();
            System.exit(0);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {
        if (playButtonRect.contains(e.getX(), e.getY())) {
            state.setPlayButtonActive(true);
            state.setExitButtonActive(false);
        } else if (exitButtonRect.contains(e.getX(), e.getY())) {
            state.setPlayButtonActive(false);
            state.setExitButtonActive(true);
        } else {
            state.setPlayButtonActive(false);
            state.setExitButtonActive(false);
        }
    }
}
