package Listeners;

import Levels.GameWorld;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import Game.Student;

public class CoinPickUpListener implements CollisionListener {
    private GameWorld gameWorld;

    public CoinPickUpListener(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    @Override
    public void collide(CollisionEvent collisionEvent) {
        if ( collisionEvent.getOtherBody() instanceof Student) {
            Student student = (Student)collisionEvent.getOtherBody();
            student.collectCoins();

            collisionEvent.getReportingBody().destroy();

            gameWorld.checkLevelCompleted();
        }
    }
}
