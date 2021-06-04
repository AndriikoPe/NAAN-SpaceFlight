import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Creature {
    public static final int MAX_PLAYER_HEALTH = 100;
    private final int maxHealth;
    private EntityManager entityManager;
    private BufferedImage playerImage;
    private Weapon weapon;
    protected Ultimate ultimate;

    public int getMaxHealth() {
        return maxHealth;
    }

    public Player(Game game, float x, float y, Option selection) {
        super(game, x, y, DEFAULT_CREATURE_WIDTH, DEFAULT_CREATURE_HEIGHT);
        maxHealth = MAX_PLAYER_HEALTH;
        initPlayer(selection);
        speed = 4.0f;
    }

    public void heal(int amount) {
        if (health + amount > 100)
            health = 100;
        else
            health += amount;
    }

    private void initPlayer(Option selection) {
        // TODO: - implement player initialization based on selection.
        switch (selection) {
            case PLAYER_DEFAULT:
                ultimate = new DefaultUltimate(game);
                playerImage = Assets.playerDefault;
                break;
            case PLAYER_BLUE:
                playerImage = Assets.playerBlue;
                //TODO: - blue ultimate.
                break;
            case PLAYER_PURPLE:
                playerImage = Assets.playerPurple;
                break;
            case PLAYER_GRAY:
                playerImage = Assets.playerGray;
                break;
            case PLAYER_WHITE:
                playerImage = Assets.playerWhite;
                break;
            case PLAYER_PINK:
                ultimate = new PinkUltimate(game);
                playerImage = Assets.playerPink;
                break;
            case PLAYER_BLACK:
                ultimate = new BlackUltimate(game);
                playerImage = Assets.playerBlack;
                break;
            case PLAYER_ORANGE:
                ultimate = new OrangeUltimate(game);
                playerImage = Assets.playerOrange;
                break;
            default:
                System.out.println("Something went wrong");
        }
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
        ultimate.setEntityManager(entityManager);
    }

    @Override
    public void tick() {
        getInput();
        ultimate.tick();
        for (int i = 0; i < entityManager.getEntities().size(); i++) {
            Entity e = entityManager.getEntities().get(i);
            if (!e.isFriendly()) {
                if (getBounds().intersects(e.getBounds())) {
                    Assets.playPlayerHitSound();
                    entityManager.addEntity(new Explosion(game, entityManager,
                            e.getWidth(), e.getWidth(), e, Assets.explosionImage, Explosion.EXPLOSION_DEFAULT_TTL / 2));
                    entityManager.getEntities().remove(e);
                    health -= 20;
                }
            }else if (e instanceof Coin){
                if (getBounds().intersects(e.getBounds())) {
                    entityManager.getEntities().remove(e);
                    game.addPoints(1);
                }
            }
        }
        move();
    }

    public void shoot() {
        if (weapon != null) weapon.shoot();
    }

    private void useUltimate() {
        ultimate.use();
    }

    public void getInput() {
        xMove = 0;
        yMove = 0;
        if (game.getKeyManager().shoot) shoot();
        if (game.getKeyManager().ultimate) useUltimate();
        if (game.getKeyManager().left) {
            if (x > 0) xMove = -speed;
        }
        if (game.getKeyManager().right) {
            if (x + width < game.getWidth()) xMove = speed;
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(playerImage, Math.round(x), Math.round(y), width, height, null);
        ultimate.render(g);
    }
}

