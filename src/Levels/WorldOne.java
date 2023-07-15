package Levels;

import Game.Game;
import Game.Enemy;
import Game.Student;
import Handlers.KeyboardHandler;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;



import javax.swing.*;

public class WorldOne extends GameWorld {
    private static final int AMOUNT_ENEMIES = 4;
    private static final int AMOUNT_HEALTH = 2;
    private static final int AMOUNT_COINS = 14;

    public WorldOne(Game game, KeyboardHandler keyboardHandler) {
        super(game, keyboardHandler);

        // Make a character
        this.student = new Student(this);
        this.student.setPosition(new Vec2(-3, -8));
        this.keyboardHandler.addStudentToKeyboardHandler(student);

        generateEnemies(AMOUNT_ENEMIES);   // generating enemy mushrooms
        generateHealth(AMOUNT_HEALTH);    //generating health mushrooms
        coinPopulation();               //populate coins

        // create a BodyImage object and set it to the platforms
        BodyImage image = new BodyImage("data/Small blue platform.png", 1.5f);
        createPlatforms(image);
    }

    @Override
    public void generateEnemies(int AMOUNT_ENEMIES){
        super.generateEnemies(AMOUNT_ENEMIES);

        BodyImage flyingEnemy = new BodyImage("data/flyingEnemy.png", 3f);

        Enemy e1 = new Enemy(this, flyingEnemy, true);
        e1.setGravityScale(0);
        e1.setAngularVelocity(0);
        e1.setPosition(new Vec2(-23,5f));


    }

    public void checkLevelCompleted() {
        int sum = 0;
        for (Enemy enemy : enemies) {
            if (enemy.isDead()) {
                sum++;
            }
        }
        if( student.getCoinsAmount() == AMOUNT_COINS && sum == AMOUNT_ENEMIES) {
        //if(  sum >= 2) {
            StaticBody portal = createPortal(); //creating portal to move to next level
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
