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

    private BufferStrategy bs;
    private Graphics g;

    private Background background;
    // States.
    private State gameState;
    private State menuState;
    private State selectPlayerState;

    // Input.
    private final KeyManager keyManager;

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int counter){
        points += counter;
        try {
            Files.write(Paths.get("Score.txt"), (points + "").getBytes());
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
        Assets.init();

        background = new Background(this);
        menuState = new MenuState(this);
        State.setState(menuState);
        try {
            List<String> score = Files.readAllLines(Paths.get("Score.txt"));
            if(score.size() >= 1) points = Integer.parseInt(score.get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setGameState() {
        gameState = new GameState(this);
        State.setState(gameState);

//        MouseInput input = ((MenuState) menuState).getMouseInput();
//        display.getCanvas().removeMouseListener(input);
//        display.getCanvas().removeMouseMotionListener(input);
    }

    public void setMenuState() {
        menuState = new MenuState(this);
        State.setState(menuState);
    }

    public void setSelectPlayerState(){
        selectPlayerState = new SelectPlayerState(this);
        State.setState(selectPlayerState);

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
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
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
