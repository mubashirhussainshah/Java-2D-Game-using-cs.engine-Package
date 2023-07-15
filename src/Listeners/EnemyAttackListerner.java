package Listeners;

import Levels.GameWorld;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import Game.Enemy;
import Game.Student;

public class EnemyAttackListerner implements CollisionListener {
    private GameWorld gameWorld;
    boolean isFlyingEnemy;

    public EnemyAttackListerner(GameWorld gameWorld, boolean isFlyingEnemy) {
        this.gameWorld = gameWorld;
        this.isFlyingEnemy = isFlyingEnemy;
    }


    @Override
    public void collide(CollisionEvent collisionEvent) {
        if (collisionEvent.getOtherBody() instanceof Student) {
            Enemy enemy = (Enemy) collisionEvent.getReportingBody();
            Student student = (Student) collisionEvent.getOtherBody();

            if (collisionEvent.getOtherBody() instanceof Student) {
                if (isFlyingEnemy) {
                    student.getHealthBar().setHealth(0);
                    return;
                }
                //check if player on top of the enemmy
                float dist = student.getPosition().y - enemy.getPosition().y;

                if (dist >= 2.5) {
                    //player attack
                    enemy.destroy();
                    gameWorld.checkLevelCompleted();
                } else {
                    //enemy attack*
                    student.getHealthBar().decreaseHealth();
                    student.collided = true;
                }
            }
        }
    }
}
