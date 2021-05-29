import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SelectPlayerState extends State{

    private List<SelectingOption> players;
    private Game game;
    private int angle;
    private boolean nextCharacter;
    private int direction;


    public SelectPlayerState(Game game) {
        super(game);
        this.game = game;
        players = new ArrayList<>();
        players.add(new SelectingOption(game, Assets.playerDefault, Math.toRadians(0)));
        players.add(new SelectingOption(game,Assets.playerBlack, Math.toRadians(45)));
        players.add(new SelectingOption(game,Assets.playerOrange, Math.toRadians(90)));
        players.add(new SelectingOption(game,Assets.playerPink, Math.toRadians(135)));
        players.add(new SelectingOption(game,Assets.playerBlue, Math.toRadians(180)));
        players.add(new SelectingOption(game,Assets.playerRed, Math.toRadians(225)));
        players.add(new SelectingOption(game,Assets.playerBlackImproved, Math.toRadians(270)));
        players.add(new SelectingOption(game,Assets.playerRedImproved, Math.toRadians(315)));
    }

    private SelectingOption getChosen(){
        return players.get(players.size() - 1);
    }

    @Override
    public void tick() {
        if (game.getKeyManager().left) {
            nextCharacter = true;
            direction = 1;
        }
        else if (game.getKeyManager().right) {
            nextCharacter = true;
            direction = -1;
        }

        if (nextCharacter) {
            angle += direction * 5;
            while (angle < 0) {
                angle += 360;
            }
            if (angle % 10 == 0) {
                nextCharacter = false;
            }
        }
        players.forEach(player -> player.tick(Math.toRadians(angle - 45)));
    }

    @Override
    public void render(Graphics g) {
        Collections.sort(players);
        for (SelectingOption player : players) {
            player.render((Graphics2D) g);
        }
        SelectingOption chosenPlayer = getChosen();
        g.setColor(Color.yellow);
        ((Graphics2D) g).setStroke(new BasicStroke(3));
        g.drawRoundRect(chosenPlayer.getImgX(), chosenPlayer.getImgY(),
                chosenPlayer.getWidth(), chosenPlayer.getHeight(), 10, 10);
    }
}
