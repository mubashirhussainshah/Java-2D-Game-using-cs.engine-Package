//package Game;
//
//import city.cs.engine.BodyImage;
//import city.cs.engine.BoxShape;
//import city.cs.engine.DynamicBody;
//import city.cs.engine.Shape;
//import org.jbox2d.common.Vec2;
//
//public class Enemy extends DynamicBody {
//    private static final Shape shape = new BoxShape(1, 2);
//    BodyImage idle = new BodyImage("/Users/user/Documents/IN1007/GitHub/javaproject2023-kalqemlas/data/mushroom.gif", 4);
//
//    private static final float WALK_SPEED = 5f;
//    private static final float JUMP_SPEED = 12f;
//
//    private boolean isJumping = false;
//
//
//    public Enemy(GameWorld gameWorld) {
//        super(gameWorld, shape);
//
//        this.addImage(idle);
//         this.setPosition(new Vec2(0,-6f));
//    }
//}
package Game;

import Levels.GameWorld;
import Listeners.EnemyAttackListerner;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;


import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Enemy extends Walker implements ActionListener {

    private static final Shape shape = new BoxShape(1, 1);

    public static SoundClip EnemySound;
    private static final float WALK_SPEED = 5f;
    private static final float JUMP_SPEED = 12f;
    private boolean isJumping = false;
    private float direction = 1f;
    private final Timer timer;
    private boolean dead = false;
    private GameWorld gameWorld;

    //setup the sound
    static {
        try {
            EnemySound = new SoundClip("data/RedMushroom.wav");
            EnemySound.setVolume(.3);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }


    public Enemy(GameWorld gameWorld, BodyImage idle, boolean isFlying) {
        super(gameWorld, shape);
        this.gameWorld = gameWorld;
        this.setName("ENEMY");

        addImage(idle);
        setPosition(new Vec2(0, -6f));
        setLinearVelocity(new Vec2(WALK_SPEED, 0f));
        addCollisionListener(new EnemyAttackListerner(gameWorld, isFlying));

        timer = new Timer(2000, this); // change direction every 2 seconds
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer) {
            direction = -direction; // reverse direction
            setLinearVelocity(new Vec2(direction * WALK_SPEED, 0f)); // set new velocity
        }
    }


//    public void jump() {
//        if (!isJumping) {
//            setLinearVelocity(new Vec2(direction * WALK_SPEED, JUMP_SPEED));
//            isJumping = true;
//        }
//    }
//
//    public void land() {
//        isJumping = false;
//    }

    public boolean isDead() {
        return dead;
    }

    @Override
    public void destroy() {
        EnemySound.play();
        dead = true;
        super.destroy();

    }


}

