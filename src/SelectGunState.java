import java.awt.*;
import java.util.*;
import java.util.List;

public class SelectGunState extends State {


    private Map<SelectingOption, Triple<String, Integer, Boolean>> guns;
    private Game game;
    private int angle;
    private boolean nextCharacter;
    private int direction;
    private Integer[] symbols;
    private List<SelectingOption> listOfGuns;
    private static final Font fontDescription = new Font("Courier New", Font.BOLD, 24);
    private boolean buttonActive = false;
    private final Rectangle buttonRect;
    private boolean showNotEnough = false;
    private SelectingOption[] options;
    private final long loadingTime;
    private static final long TIME_TO_LOAD = 1000000000;
    private final SelectingOption selection;

    public SelectGunState(Game game, SelectingOption selection) {
        super(game);
        this.game = game;
        this.selection = selection;
        loadingTime = System.nanoTime();
        symbols = SelectingOption.readInfo("PurchasedGuns.txt");
        guns = new HashMap<>();
        options = new SelectingOption[4];
        listOfGuns = new ArrayList<>();
        options[0] = new SelectingOption(game, Assets.gunBlue, Math.toRadians(0), Option.GUN_DEFAULT);
        options[1] = new SelectingOption(game, Assets.gunGreen, Math.toRadians(90), Option.GUN_DOUBLE);
        options[2] = new SelectingOption(game, Assets.gunDarkblue, Math.toRadians(180), Option.GUN_TRIPLE);
        options[3] = new SelectingOption(game, Assets.gunWhite, Math.toRadians(270), Option.GUN_MASSIVE);
        listOfGuns.addAll(Arrays.asList(options));
        guns.put(listOfGuns.get(0), new Triple<>("Fast single shot!", 0, symbols[0] == 1));
        guns.put(listOfGuns.get(1), new Triple<>("Fast double shot!", 10000,
                symbols[1] == 1));
        guns.put(listOfGuns.get(2), new Triple<>("Triple shot!",
                15000, symbols[2] == 1));
        guns.put(listOfGuns.get(3), new Triple<>("Massive shot that \ndoesn't get destroyed!", 12000,
                symbols[3] == 1));
        buttonRect = new Rectangle(game.getWidth() / 2 - 150, 650, 300, 120);
    }

    private SelectingOption getChosen() {
        return listOfGuns.get(listOfGuns.size() - 1);
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
            if (angle % 60 == 0) {
                nextCharacter = false;
            }
        }
        listOfGuns.forEach(player -> player.tick(Math.toRadians(angle - 45)));
        if(System.nanoTime() - loadingTime < TIME_TO_LOAD) return;
        checkMouse();
    }

    private void checkMouse() {
        if (buttonRect.contains(game.mouseInput.getX(), game.mouseInput.getY())) {
            if (game.mouseInput.getLeftClicked()) {
                if (!guns.get(getChosen()).isPurchased) {
                    if (game.getPoints() < guns.get(getChosen()).price) {
                        showNotEnough = true;
                    } else {
                        buy();
                    }
                } else
                    game.setGameState(selection, getChosen());
            } else {
                buttonActive = true;
            }
        } else {
            buttonActive = false;
        }
    }

    private void buy() {
        game.addPoints(-guns.get(getChosen()).price);
        guns.get(getChosen()).isPurchased = true;
        SelectingOption.createInfo("PurchasedGuns.txt", options, guns);
    }

    @Override
    public void render(Graphics g) {
        Collections.sort(listOfGuns);
        for (SelectingOption player : listOfGuns) {
            player.render((Graphics2D) g);
        }
        SelectingOption chosenPlayer = getChosen();
        g.setColor(Color.yellow);
        ((Graphics2D) g).setStroke(new BasicStroke(3));
        g.drawRoundRect(chosenPlayer.getImgX(), chosenPlayer.getImgY(),
                chosenPlayer.getWidth(), chosenPlayer.getHeight(), 10, 10);
        g.setFont(fontDescription);
        g.setColor(Color.WHITE);
        String description = guns.get(getChosen()).description;
        int yPosition = 200;
        for (String l : description.split("\n")) {
            g.drawString(l, (game.getWidth() - g.getFontMetrics().stringWidth(l)) / 2, yPosition);
            yPosition += g.getFontMetrics().getHeight();
        }
        if (guns.get(getChosen()).isPurchased) {
            g.drawImage(buttonActive ? Assets.startActive : Assets.startInactive,
                    game.getWidth() / 2 - 150,
                    650,
                    300,
                    120,
                    null);
        } else {
            if (game.getPoints() < guns.get(getChosen()).price) {
                g.setColor(Color.red);
                if (showNotEnough) {
                    String text = "Not enough money!";
                    g.drawString(text, (game.getWidth() - g.getFontMetrics().stringWidth(text)) / 2, 630);
                }
            }
            String price = guns.get(getChosen()).price + "";
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
