package Game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Student extends Walker {
    private static final Shape studentShape = new BoxShape(1, 2);
    private int coinCount = 0;
    private final HealthBar healthBar;
    public boolean collided = false;
    public boolean collided_walking_left = false;
    public boolean collided_walking_right = false;
    private final BodyImage idle = new BodyImage("data/idle.gif", 4);
    private final BodyImage walking_right = new BodyImage("data/run.gif", 4);
    private final BodyImage walking_left = new BodyImage("data/runLeft.gif", 4);
    private final BodyImage jumping = new BodyImage("data/jump.png", 4);
    private static final float JUMP_SPEED = 14f;





    public Student(World world) {
        super(world, studentShape);
        this.addImage(idle);
        this.setName("Student");

        healthBar = new HealthBar(world,5);
        healthBar.setHealth(healthBar.getHealth());
    }


    public void jump() {
        this.setLinearVelocity(new Vec2(this.getLinearVelocity().x, JUMP_SPEED));
    }

    public boolean isJumping() {
        return  this.getLinearVelocity().y > 0.01f || this.getLinearVelocity().y < -0.01f;
    }

    public void setJumpImage() {
        this.removeAllImages();
        this.addImage(jumping);
    }

    public void moveLeft() {
        this.removeAllImages();
        this.addImage(walking_left);
    }

    public void moveRight() {
        this.removeAllImages();
        this.addImage(walking_right);
    }

    @Override
    public void stopWalking(){
        super.stopWalking();
        this.removeAllImages();
        this.addImage(idle);
    }

    public HealthBar getHealthBar() {
        return healthBar;
    }

    public int getCoinsAmount(){
        return this.coinCount;
    }
    public void setCoinCount(int coinCount){
        this.coinCount = coinCount;
    }

    public void collectCoins() {
        coinCount++;
    }

}





