import java.awt.*;
import java.util.Random;

public class Planet extends Creature{
    private Image planet;
    Random r = new Random();

    public Planet(Game game, float x, float y, int width, int height) {
        super(game, x, y, width, height);
        setHealth(4);
        this.isFriendly = false;
        randomPlanets();
    }

    @Override
    public void tick() {
        if (y > game.getHeight()) isOffscreen = true;
        y += speed;
    }
    public void randomPlanets(){
        int counter = r.nextInt(6) + 1;
        switch (counter){
            case 1: planet = Assets.planetBlue;
            break;
            case 2: planet = Assets.planetBrown;
                break;
            case 3: planet = Assets.planetDark;
                break;
            case 4: planet = Assets.planetEarth;
                break;
            case 5: planet = Assets.planetPurple;
                break;
            case 6: planet = Assets.planetOrange;
                break;
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(planet, Math.round(x), Math.round(y), null);
    }
}
