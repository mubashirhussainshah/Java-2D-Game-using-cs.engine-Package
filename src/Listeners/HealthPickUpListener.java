package Listeners;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import Game.Student;

public class HealthPickUpListener implements CollisionListener {

    @Override
    public void collide(CollisionEvent collisionEvent) {
        if ( collisionEvent.getOtherBody() instanceof Student ) {

            Student student = (Student)collisionEvent.getOtherBody();
            student.getHealthBar().increaseHealth();
            collisionEvent.getReportingBody().destroy();
        }
    }
}
