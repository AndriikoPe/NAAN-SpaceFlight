import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

public class SelectPlayerState extends State {

    private Map<SelectingOption, Triple<String, Integer, Boolean>> players;
    private Game game;
    private int angle;
    private boolean nextCharacter;
    private int direction;
    private Integer[] symbols;
    private List<SelectingOption> listOfPlayers;
    private static final Font fontDescription = new Font("Courier New", Font.BOLD, 24);
    private boolean buttonActive = false;
    private final Rectangle buttonRect;
    private boolean showNotEnough = false;
    private SelectingOption[] options;


    public SelectPlayerState(Game game) {
        super(game);
        this.game = game;
        symbols = SelectingOption.readInfo("PurchasedPlayers.txt");
        players = new HashMap<>();
        options = new SelectingOption[8];
        listOfPlayers = new ArrayList<>();
        options[0] = new SelectingOption(game, Assets.playerDefault, Math.toRadians(0), Option.PLAYER_DEFAULT);
        options[1] = new SelectingOption(game, Assets.playerBlack, Math.toRadians(45), Option.PLAYER_BLACK);
        options[2] = new SelectingOption(game, Assets.playerOrange, Math.toRadians(90), Option.PLAYER_ORANGE);
        options[3] = new SelectingOption(game, Assets.playerPink, Math.toRadians(135), Option.PLAYER_PINK);
        options[4] = new SelectingOption(game, Assets.playerBlue, Math.toRadians(180), Option.PLAYER_BLUE);
        options[5] = new SelectingOption(game, Assets.playerWhite, Math.toRadians(225), Option.PLAYER_WHITE);
        options[6] = new SelectingOption(game, Assets.playerPurple, Math.toRadians(270), Option.PLAYER_PURPLE);
        options[7] = new SelectingOption(game, Assets.playerGray, Math.toRadians(315), Option.PLAYER_GRAY);
        listOfPlayers.addAll(Arrays.asList(options));
        players.put(listOfPlayers.get(0), new Triple<>("Fully heals itself!", 0, symbols[0] == 1));
        players.put(listOfPlayers.get(1), new Triple<>("Triple shoot with 10 bullets!", 11000,
                symbols[1] == 1));
        players.put(listOfPlayers.get(2), new Triple<>("Shoots a giant bomb,\nthat blows up everything!",
                10000, symbols[2] == 1));
        players.put(listOfPlayers.get(3), new Triple<>("Your next 5 shoots \nwill be fivefold!", 5000,
                symbols[3] == 1));
        players.put(listOfPlayers.get(4), new Triple<>("+100 coins for every destroyed \nenemy for 30 sec", 8000, symbols[4] == 1));
        players.put(listOfPlayers.get(5), new Triple<>("Shoots a giant bomb after 5 sec \nand fully heals itself!", 9000, symbols[5] == 1));
        players.put(listOfPlayers.get(6), new Triple<>("Shoots a long red laser\n for 5 sec!", 11000, symbols[6] == 1));
        players.put(listOfPlayers.get(7), new Triple<>("Becomes faster and +40 coins \nfor every destroyed enemy \nfor 30 sec!", 10000, symbols[7] == 1));
        buttonRect = new Rectangle(game.getWidth() / 2 - 150, 650, 300, 120);
    }

    private SelectingOption getChosen() {
        return listOfPlayers.get(listOfPlayers.size() - 1);
    }

    @Override
    public void tick() {
        showNotEnough = false;
        if (game.getKeyManager().left) {
            nextCharacter = true;
            direction = 1;
        } else if (game.getKeyManager().right) {
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
        listOfPlayers.forEach(player -> player.tick(Math.toRadians(angle - 45)));
        checkMouse();
    }

    private void checkMouse(){
        if (buttonRect.contains(game.mouseInput.getX(), game.mouseInput.getY())) {
            if (game.mouseInput.getLeftClicked()) {
                if (!players.get(getChosen()).isPurchased) {
                    if (game.getPoints() < players.get(getChosen()).price) {
                        showNotEnough = true;
                    } else {
                        buy();
                    }
                } else
                    game.setSelectGunState(getChosen());
            } else {
                buttonActive = true;
            }
        } else {
            buttonActive = false;
        }
    }

    private void buy() {
        game.addPoints(-players.get(getChosen()).price);
        players.get(getChosen()).isPurchased = true;
        SelectingOption.createInfo("PurchasedPlayers.txt", options, players);
    }

    @Override
    public void render(Graphics g) {
        Collections.sort(listOfPlayers);
        for (SelectingOption player : listOfPlayers) {
            player.render((Graphics2D) g);
        }
        SelectingOption chosenPlayer = getChosen();
        g.setColor(Color.yellow);
        ((Graphics2D) g).setStroke(new BasicStroke(3));
        g.drawRoundRect(chosenPlayer.getImgX(), chosenPlayer.getImgY(),
                chosenPlayer.getWidth(), chosenPlayer.getHeight(), 10, 10);
        g.setFont(fontDescription);
        g.setColor(Color.WHITE);
        String description = players.get(getChosen()).description;
        int yPosition = 200;
        for (String l : description.split("\n")) {
            g.drawString(l, (game.getWidth() - g.getFontMetrics().stringWidth(l)) / 2, yPosition);
            yPosition += g.getFontMetrics().getHeight();
        }
        if (players.get(getChosen()).isPurchased) {
            g.drawImage(buttonActive ? Assets.startActive : Assets.startInactive,
                    game.getWidth() / 2 - 150,
                    650,
                    300,
                    120,
                    null);
        } else {
            if (game.getPoints() < players.get(getChosen()).price) {
                g.setColor(Color.red);
                if (showNotEnough) {
                    String text = "Not enough money!";
                    g.drawString(text, (game.getWidth() - g.getFontMetrics().stringWidth(text)) / 2, 630);
                }
            }
            String price = players.get(getChosen()).price + "";
            g.drawString(price, (game.getWidth() - g.getFontMetrics().stringWidth(price) - Assets.COIN_WIDTH) / 2,
                    600);
            g.drawImage(Assets.coin0, (game.getWidth() - g.getFontMetrics().stringWidth(price) - Assets.COIN_WIDTH) / 2
                    + 15 + g.getFontMetrics().stringWidth(price), 600 - Assets.COIN_HEIGHT * 3 / 4, null);
            g.drawImage(buttonActive ? Assets.buyActive : Assets.buy,
                    game.getWidth() / 2 - 150,
                    650,
                    300,
                    120,
                    null);
        }
    }
}

class Triple<K, V, M> {
    public final K description;
    public final V price;
    public M isPurchased;

    public Triple(K description, V price, M isPurchased) {
        this.description = description;
        this.price = price;
        this.isPurchased = isPurchased;
    }
}
