import jdk.jfr.DataAmount;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.*;
import java.util.List;

public class Patterns {

    private final static List<Map<Point2D, ID>> patterns = new ArrayList<>();
    private final static Random r = new Random();

    public static void init(Game game) {
        initFirst(game);
        initSecond(game);
        initThird(game);
        initFourth(game);
        initFifth(game);
        initSixth(game);
        initSeventh(game);
        initEighth(game);
        initNinth(game);
        initTenth(game);
    }

    private static void initFirst(Game game) {
        Map<Point2D, ID> p = new HashMap<>();
        p.put(new Point(10, 10 - game.getHeight()), ID.SHOOTING_ENEMY);
        p.put(new Point(30 + Creature.DEFAULT_CREATURE_WIDTH, 10 - game.getHeight()), ID.SHOOTING_ENEMY);
        Point pointPlanet = new Point(game.getWidth() - 200, 50 + Creature.DEFAULT_CREATURE_HEIGHT
                - game.getHeight());
        p.put(pointPlanet, ID.PLANET);
        wrapPlanetWithCoins(pointPlanet, p);
        p.put(new Point(game.getWidth() - 260 - Creature.DEFAULT_CREATURE_WIDTH, 80 + Creature.DEFAULT_CREATURE_HEIGHT
                - game.getHeight()), ID.ENEMY);
        p.put(new Point(game.getWidth() - 210, 340 + Creature.DEFAULT_CREATURE_HEIGHT - game.getHeight()), ID.ENEMY);
        p.put(new Point(game.getWidth() - 180 + Creature.DEFAULT_CREATURE_WIDTH,
                340 + Creature.DEFAULT_CREATURE_HEIGHT - game.getHeight()), ID.SHOOTING_ENEMY);
        pointPlanet = new Point(120, -200);
        p.put(pointPlanet, ID.PLANET);
        wrapPlanetWithCoins(pointPlanet, p);
        patterns.add(p);
    }

    private static void initSecond(Game game) {
        Map<Point2D, ID> p = new HashMap<>();
        p.put(new Point(48, 220 - game.getHeight()), ID.SHOOTING_ENEMY);
        p.put(new Point(96 + Creature.DEFAULT_CREATURE_WIDTH, 220 - game.getHeight()), ID.SHOOTING_ENEMY);
        p.put(new Point(144 + 2 * Creature.DEFAULT_CREATURE_WIDTH, 220 - game.getHeight()), ID.SHOOTING_ENEMY);
        Point planet = new Point(250 - Assets.PLANET_SIZE / 2, 600 - Assets.PLANET_SIZE / 2 - game.getHeight());
        p.put(planet, ID.PLANET);
        wrapPlanetWithCoins(planet, p);
        patterns.add(p);
    }

    private static void initThird(Game game) {
        Map<Point2D, ID> p = new HashMap<>();
        Point pointPlanet = new Point(110, -150);
        p.put(pointPlanet, ID.PLANET);
        wrapPlanetWithCoins(pointPlanet, p);
        pointPlanet = new Point(250 - Assets.PLANET_SIZE / 2, 400 - Assets.PLANET_SIZE / 2 - game.getHeight());
        p.put(pointPlanet, ID.PLANET);
        wrapPlanetWithCoins(pointPlanet, p);
        pointPlanet = new Point(game.getWidth() - 200, 50 - game.getHeight());
        p.put(pointPlanet, ID.PLANET);
        wrapPlanetWithCoins(pointPlanet, p);
        p.put(new Point(game.getWidth() - 80, 340 + Creature.DEFAULT_CREATURE_HEIGHT - game.getHeight()), ID.ENEMY);
        p.put(new Point(game.getWidth() - 160, 250 + Creature.DEFAULT_CREATURE_HEIGHT - game.getHeight()), ID.ENEMY);
        p.put(new Point(30, 250 + Creature.DEFAULT_CREATURE_HEIGHT - game.getHeight()), ID.ENEMY);
        p.put(new Point(110, 160 + Creature.DEFAULT_CREATURE_HEIGHT - game.getHeight()), ID.ENEMY);
        patterns.add(p);
    }

    private static void initFourth(Game game) {
        Map<Point2D, ID> p = new HashMap<>();
        Point pointPlanet = new Point(75, -200);
        p.put(pointPlanet, ID.PLANET);
        wrapPlanetWithCoins(pointPlanet, p);
        pointPlanet = new Point(305, -200);
        p.put(pointPlanet, ID.PLANET);
        wrapPlanetWithCoins(pointPlanet, p);
        p.put(new Point(game.getWidth() - 35 - Creature.DEFAULT_CREATURE_WIDTH, 250 +
                Creature.DEFAULT_CREATURE_HEIGHT - game.getHeight()), ID.ENEMY);
        p.put(new Point(game.getWidth() - 145 - Creature.DEFAULT_CREATURE_WIDTH, 250 +
                Creature.DEFAULT_CREATURE_HEIGHT - game.getHeight()), ID.SHOOTING_ENEMY);
        p.put(new Point(35, 250 + Creature.DEFAULT_CREATURE_HEIGHT - game.getHeight()), ID.SHOOTING_ENEMY);
        p.put(new Point(145, 250 + Creature.DEFAULT_CREATURE_HEIGHT - game.getHeight()), ID.SHOOTING_ENEMY);
        pointPlanet = new Point(250 - Assets.PLANET_SIZE / 2, 150 - Assets.PLANET_SIZE / 2 - game.getHeight());
        p.put(pointPlanet, ID.PLANET);
        wrapPlanetWithCoins(pointPlanet, p);
        patterns.add(p);
    }

    private static void initFifth(Game game) {
        Map<Point2D, ID> p = new HashMap<>();
        Point pointPlanet = new Point(75, 100 - game.getHeight());
        p.put(pointPlanet, ID.PLANET);
        wrapPlanetWithCoins(pointPlanet, p);
        pointPlanet = new Point(305, 100 - game.getHeight());
        p.put(pointPlanet, ID.PLANET);
        wrapPlanetWithCoins(pointPlanet, p);
        p.put(new Point(game.getWidth() - 35 - Creature.DEFAULT_CREATURE_WIDTH,
                250 + Creature.DEFAULT_CREATURE_HEIGHT - game.getHeight()), ID.ENEMY);
        p.put(new Point(game.getWidth() - 145 - Creature.DEFAULT_CREATURE_WIDTH,
                250 + Creature.DEFAULT_CREATURE_HEIGHT - game.getHeight()), ID.SHOOTING_ENEMY);
        p.put(new Point(35, 250 + Creature.DEFAULT_CREATURE_HEIGHT - game.getHeight()), ID.ENEMY);
        p.put(new Point(145, 250 + Creature.DEFAULT_CREATURE_HEIGHT - game.getHeight()), ID.SHOOTING_ENEMY);
        pointPlanet = new Point(250 - Assets.PLANET_SIZE / 2, -200);
        p.put(pointPlanet, ID.PLANET);
        wrapPlanetWithCoins(pointPlanet, p);
        patterns.add(p);
    }

    private static void initSixth(Game game) {
        Map<Point2D, ID> p = new HashMap<>();
        Point pointPlanet = new Point(game.getWidth() - 200, 250 - game.getHeight());
        p.put(pointPlanet, ID.PLANET);
        wrapPlanetWithCoins(pointPlanet, p);
        p.put(new Point(70, 150 + Creature.DEFAULT_CREATURE_HEIGHT - game.getHeight()), ID.ENEMY);
        p.put(new Point(game.getWidth() - 35 - Creature.DEFAULT_CREATURE_WIDTH, 300 +
                Creature.DEFAULT_CREATURE_HEIGHT - game.getHeight()), ID.ENEMY);
        p.put(new Point(game.getWidth() - 145 - Creature.DEFAULT_CREATURE_WIDTH, 300 +
                Creature.DEFAULT_CREATURE_HEIGHT - game.getHeight()), ID.SHOOTING_ENEMY);
        p.put(new Point(35, 300 + Creature.DEFAULT_CREATURE_HEIGHT - game.getHeight()), ID.SHOOTING_ENEMY);
        p.put(new Point(145, 300 + Creature.DEFAULT_CREATURE_HEIGHT - game.getHeight()), ID.SHOOTING_ENEMY);
        p.put(new Point(50, -170), ID.ENEMY);
        pointPlanet = new Point(210, -150);
        p.put(pointPlanet, ID.PLANET);
        wrapPlanetWithCoins(pointPlanet, p);
        patterns.add(p);
    }

    private static void initSeventh(Game game) {
        Map<Point2D, ID> p = new HashMap<>();
        p.put(new Point(48, 400 - Creature.DEFAULT_CREATURE_HEIGHT / 2 - game.getHeight()), ID.SHOOTING_ENEMY);
        p.put(new Point(96 + Creature.DEFAULT_CREATURE_WIDTH, 400 -
                Creature.DEFAULT_CREATURE_HEIGHT / 2 - game.getHeight()), ID.SHOOTING_ENEMY);
        p.put(new Point(144 + 2 * Creature.DEFAULT_CREATURE_WIDTH, 400 -
                Creature.DEFAULT_CREATURE_HEIGHT / 2 - game.getHeight()), ID.SHOOTING_ENEMY);
        Point planet = new Point(250 - Assets.PLANET_SIZE / 2, 200 - game.getHeight());
        p.put(planet, ID.PLANET);
        wrapPlanetWithCoins(planet, p);
        patterns.add(p);
    }

    private static void initEighth(Game game) {
        Map<Point2D, ID> p = new HashMap<>();
        Point pointPlanet = new Point(game.getWidth() - 200, -150);
        p.put(pointPlanet, ID.PLANET);
        wrapPlanetWithCoins(pointPlanet, p);
        pointPlanet = new Point(250 - Assets.PLANET_SIZE / 2, 400 - Assets.PLANET_SIZE / 2 - game.getHeight());
        p.put(pointPlanet, ID.PLANET);
        wrapPlanetWithCoins(pointPlanet, p);
        pointPlanet = new Point(110, 50 - game.getHeight());
        p.put(pointPlanet, ID.PLANET);
        wrapPlanetWithCoins(pointPlanet, p);
        p.put(new Point(110, 500 + Creature.DEFAULT_CREATURE_HEIGHT - game.getHeight()), ID.ENEMY);
        p.put(new Point(game.getWidth() - 100, 250 + Creature.DEFAULT_CREATURE_HEIGHT - game.getHeight()), ID.ENEMY);
        p.put(new Point(30, 250 + Creature.DEFAULT_CREATURE_HEIGHT - game.getHeight()), ID.SHOOTING_ENEMY);
        p.put(new Point(game.getWidth() - 120, 70 - game.getHeight()), ID.ENEMY);
        patterns.add(p);
    }

    private static void initNinth(Game game){
        Map<Point2D, ID> p = new HashMap<>();
        p.put(new Point(game.getWidth() - 35 - Creature.DEFAULT_CREATURE_WIDTH, 50 +
                Creature.DEFAULT_CREATURE_HEIGHT - game.getHeight()), ID.ENEMY);
        p.put(new Point(game.getWidth() - 145 - Creature.DEFAULT_CREATURE_WIDTH, 50 +
                Creature.DEFAULT_CREATURE_HEIGHT - game.getHeight()), ID.SHOOTING_ENEMY);
        p.put(new Point(35, 50 + Creature.DEFAULT_CREATURE_HEIGHT - game.getHeight()), ID.SHOOTING_ENEMY);
        p.put(new Point(145, 50 + Creature.DEFAULT_CREATURE_HEIGHT - game.getHeight()), ID.SHOOTING_ENEMY);
        p.put(new Point(game.getWidth() - 35 - Creature.DEFAULT_CREATURE_WIDTH, 250 +
                Creature.DEFAULT_CREATURE_HEIGHT - game.getHeight()), ID.ENEMY);
        p.put(new Point(game.getWidth() - 145 - Creature.DEFAULT_CREATURE_WIDTH, 250 +
                Creature.DEFAULT_CREATURE_HEIGHT - game.getHeight()), ID.SHOOTING_ENEMY);
        p.put(new Point(35, 250 + Creature.DEFAULT_CREATURE_HEIGHT - game.getHeight()), ID.SHOOTING_ENEMY);
        p.put(new Point(145, 250 + Creature.DEFAULT_CREATURE_HEIGHT - game.getHeight()), ID.SHOOTING_ENEMY);
        patterns.add(p);
    }

    private static void initTenth(Game game){
        Map<Point2D, ID> p = new HashMap<>();
        p.put(new Point(game.getWidth() - 35 - Creature.DEFAULT_CREATURE_WIDTH, 50 +
                Creature.DEFAULT_CREATURE_HEIGHT - game.getHeight()), ID.SHOOTING_ENEMY);
        p.put(new Point(game.getWidth() - 145 - Creature.DEFAULT_CREATURE_WIDTH, 50 +
                Creature.DEFAULT_CREATURE_HEIGHT - game.getHeight()), ID.SHOOTING_ENEMY);
        p.put(new Point(35, 50 + Creature.DEFAULT_CREATURE_HEIGHT - game.getHeight()), ID.SHOOTING_ENEMY);
        p.put(new Point(145, 50 + Creature.DEFAULT_CREATURE_HEIGHT - game.getHeight()), ID.SHOOTING_ENEMY);
        Point pointPlanet = new Point(250 - Assets.PLANET_SIZE / 2, 400 - Assets.PLANET_SIZE / 2 - game.getHeight());
        p.put(pointPlanet, ID.PLANET);
        wrapPlanetWithCoins(pointPlanet, p);
        p.put(new Point(game.getWidth() - 35 - Creature.DEFAULT_CREATURE_WIDTH, 450 +
                Creature.DEFAULT_CREATURE_HEIGHT - game.getHeight()), ID.ENEMY);
        p.put(new Point(game.getWidth() - 145 - Creature.DEFAULT_CREATURE_WIDTH, 450 +
                Creature.DEFAULT_CREATURE_HEIGHT - game.getHeight()), ID.ENEMY);
        p.put(new Point(35, 450 + Creature.DEFAULT_CREATURE_HEIGHT - game.getHeight()), ID.ENEMY);
        p.put(new Point(145, 450 + Creature.DEFAULT_CREATURE_HEIGHT - game.getHeight()), ID.ENEMY);
        patterns.add(p);
    }

    private static void wrapPlanetWithCoins(Point point, Map<Point2D, ID> map) {
        Point center = new Point(point.x + Assets.PLANET_SIZE / 2, point.y + Assets.PLANET_SIZE / 2);
        int planetRadius = Math.round(Assets.PLANET_SIZE * (float) Math.sqrt(2)) / 2;
        //Horizontal line
        int x = center.x - (planetRadius + Math.round(Assets.COIN_WIDTH * 2.5f));
        int y = center.y - Assets.COIN_HEIGHT / 2;
        int xx = center.x + (planetRadius + Assets.COIN_WIDTH * 2);
        map.put(new Point(x, y), ID.COIN);
        map.put(new Point(xx, y), ID.COIN);
        //Vertical line
        x = center.x - Assets.COIN_WIDTH / 2;
        y = center.y - (planetRadius + Math.round(Assets.COIN_HEIGHT * 2.5f));
        int yy = center.y + (planetRadius + Assets.COIN_HEIGHT * 2);
        map.put(new Point(x, y), ID.COIN);
        map.put(new Point(x, yy), ID.COIN);
        //Additional lines(corners)
        int diagonalCoin = Math.round((float) Math.sqrt(Assets.COIN_WIDTH * Assets.COIN_WIDTH +
                Assets.COIN_HEIGHT * Assets.COIN_HEIGHT));
        int catet = Math.round((diagonalCoin * 2.5f + planetRadius) * (float) Math.sqrt(2) / 2);
        x = center.x - catet;
        y = center.y - catet;
        map.put(new Point(x, y), ID.COIN);
        yy = center.y + catet;
        map.put(new Point(x, yy), ID.COIN);
        xx = center.x + catet;
        map.put(new Point(xx, y), ID.COIN);
        map.put(new Point(xx, yy), ID.COIN);
    }

    public static Map<Point2D, ID> nextPattern() {
        return patterns.get(r.nextInt(patterns.size()));
    }
}

enum ID {
    COIN, PLANET, ENEMY, SHOOTING_ENEMY;
}
