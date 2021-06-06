import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Game implements Runnable {
    private Display display;

    private final int width, height;
    public String title;

    private boolean running = false;
    private Thread thread;
    private int points;
    private int runScore = 0;

    private Background background;

    // Input.
    private final KeyManager keyManager;
    public final MouseInput mouseInput;

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
        mouseInput = new MouseInput();
    }

    public int getRunScore() {
        return runScore;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int points){
        this.points += points;
        runScore += points;
        try {
            Files.write(Paths.get("Score.txt"), (this.points + "").getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void nextLevel() {
        background.nextLevel();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private void init() {
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        this.getDisplay().getCanvas().addMouseListener(mouseInput);
        this.getDisplay().getCanvas().addMouseMotionListener(mouseInput);
        Assets.init();

        background = new Background(this);
        setMenuState();
        try {
            List<String> score = Files.readAllLines(Paths.get("Score.txt"));
            if(score.size() >= 1) points = Integer.parseInt(score.get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setRunScore(int runScore) {
        this.runScore = runScore;
    }

    public void restart() {
        background = new Background(this);
        Boss.bossCounter = 0;
        Enemy.setPointPerHit(Enemy.DEFAULT_POINTS_PER_HIT);
        setFailState();
        runScore = 0;
    }

    public void setGameState(SelectingOption player, SelectingOption gun) {
        State gameState = new GameState(this, player, gun);
        State.setState(gameState);
    }

    public void setMenuState() {
        State menuState = new MenuState(this);
        State.setState(menuState);
    }

    public void setSelectPlayerState(){
        State selectPlayerState = new SelectPlayerState(this);
        State.setState(selectPlayerState);
    }

    public void setFailState(){
        State failState = new FailState(this);
        State.setState(failState);
    }

    public void setSelectGunState(SelectingOption selection){
        State selectGunState = new SelectGunState(this, selection);
        State.setState(selectGunState);

    }
    public  void setInstructionState(){
        State instructionState = new InstructionState(this);
        State.setState(instructionState);
    }

    public Display getDisplay() {
        return display;
    }

    private void tick() {
        keyManager.tick();
        background.tick();
        if (State.getState() != null) State.getState().tick();
    }

    private void render() {
        BufferStrategy bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.clearRect(0, 0, width, height);
        background.render(g);
        if (State.getState() != null) State.getState().render(g);
        g.setFont(new Font("Courier New", Font.PLAIN, 18));
        g.setColor(Color.green);
        String text = "Score: " + points;
        g.drawString(text, 20, 35);
        g.drawImage(Assets.coin0, 25 + g.getFontMetrics().stringWidth(text), 18, null);
        bs.show();
        g.dispose();
    }

    public void run() {
        init();
        int fps = 60;
        double timePerTick = 1000000000.0 / fps;
        double delta = 0.0;
        long now;
        long lastTime = System.nanoTime();
        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            lastTime = now;
            if (delta >= 1) {
                tick();
                render();
                delta--;
            }
        }
        stop();
    }

    public synchronized void start() {
        if (running) return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (!running) return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
