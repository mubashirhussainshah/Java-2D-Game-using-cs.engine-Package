package Levels;

import Game.Game;
import Game.CoinView;
import Game.Enemy;
import Game.Student;
import Handlers.KeyboardHandler;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;


public class WorldTwo extends GameWorld{
    private static final int AMOUNT_ENEMIES = 6;
    private static final int AMOUNT_HEALTH = 2;
    private static final int AMOUNT_COINS = 15;

    public WorldTwo(Game game, KeyboardHandler keyboardHandler) {
        super(game, keyboardHandler);

        // Make a character
        this.student = new Student(this);
        this.student.setPosition(new Vec2(-3, -8));
        this.keyboardHandler.addStudentToKeyboardHandler(student);

       this.generateEnemies(AMOUNT_ENEMIES);   // generating enemy mushrooms
        generateHealth(AMOUNT_HEALTH);    //generating health mushrooms
        coinPopulation();               //populate coins


        // create a BodyImage object and set it to the platforms
        BodyImage image = new BodyImage("data/Small blue platform.png", 1.5f);
        createPlatforms(image);

    }

    @Override
    public void generateEnemies(int AMOUNT_ENEMIES){
        super.generateEnemies(AMOUNT_ENEMIES);
        Enemy e1 = new Enemy(this, idle, false);
        e1.setPosition(new Vec2(0, 0f));

        BodyImage flyingEnemy = new BodyImage("data/flyingEnemy.png", 3f);

        Enemy e2 = new Enemy(this, flyingEnemy, true);
        e2.setGravityScale(0);
        e2.setAngularVelocity(0);
        e2.setPosition(new Vec2(-24,5f));

        Enemy e3 = new Enemy(this, flyingEnemy, true);
        e3.setGravityScale(0);
        e3.setAngularVelocity(0);
        e3.setPosition(new Vec2(27,3f));

    }


   @Override
    public void coinPopulation() {
    super.coinPopulation();

        // Coin 15
        CoinView coin15 = new CoinView(this, 1);
        coin15.setPosition(new Vec2(15, -4f));
    }


    @Override
    public void checkLevelCompleted() {
        int sum = 0;
        for (Enemy enemy : enemies) {
            if (enemy.isDead()) {
                sum++;
            }
        }

        if( student.getCoinsAmount() == AMOUNT_COINS && sum + 1 == AMOUNT_ENEMIES) {
       // if(student.getCoinsAmount() == 4) {

            StaticBody portal = createPortal(); //creating portal to move to next leve

            portal.addCollisionListener(new CollisionListener() {
                @Override
                public void collide(CollisionEvent e) {
                    if (e.getOtherBody() == student) {
                            student.setCoinCount(0);
                            JOptionPane.showMessageDialog(frame, "Next Level", "You win", JOptionPane.INFORMATION_MESSAGE);
                            game.progressLevel();
                    }
                }

            });
        }
    }
}
