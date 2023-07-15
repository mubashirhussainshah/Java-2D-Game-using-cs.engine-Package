package Levels;

import Game.Game;
import Game.Enemy;
import Game.HealthItem;
import Game.Student;
import Game.CoinView;
import Handlers.KeyboardHandler;
import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import java.awt.*;

abstract public class GameWorld extends World {
    protected Student student;
    protected Enemy[] enemies;
    protected HealthItem[] healthItems;
    protected BodyImage gameOverImage;
    protected KeyboardHandler keyboardHandler;
    protected Game game;
    protected Component frame;
    protected BodyImage idle = new BodyImage("data/mushroom.gif", 4);

    abstract public void checkLevelCompleted();


    public GameWorld(Game game, KeyboardHandler keyboardHandler) {
        this(keyboardHandler);
        this.game = game;
        // gameover image
        gameOverImage = new BodyImage("data/GameOver.gif", 1.5f);
    }

    public GameWorld(KeyboardHandler keyboardHandler) {
        super();
        this.keyboardHandler = keyboardHandler;
    }

    public Student getStudent() {
        return student;
    }

    public void generateEnemies(int AMOUNT_ENEMIES){
        //populate the GameWorld with bodies (ex: platforms, collectibles, characters)
        // Enemies
        this.enemies = new Enemy[AMOUNT_ENEMIES];
        for (int i = 0; i < AMOUNT_ENEMIES; i++) {
            this.enemies[i] = new Enemy(this, idle, false);
            // Set each location
            this.enemies[i].setPosition(new Vec2(-26f + (i * 15), -10f));
        }
    }

    public void generateHealth(int AMOUNT_HEALTH){
        //health pack

        this.healthItems = new HealthItem[AMOUNT_HEALTH ];
        for (int i = 0; i < AMOUNT_HEALTH ; i++) {
            this.healthItems[i] = new HealthItem(this);
            // Set each location
            this.healthItems[i].setPosition(new Vec2(-32f + (i * 50), 10f));
        }
    }

    public StaticBody createPortal(){
        BodyImage image = new BodyImage("data/Portal.gif", 8f);
        Shape portalShape = new BoxShape(3, 0.3f);
        StaticBody portal = new StaticBody(this, portalShape);
        portal.setPosition(new Vec2(33, -8f));
        portal.addImage(image);
        return portal;

    }

    public  void createPlatforms(BodyImage image){

        // Make a ground platform
        Shape shape = new BoxShape(80, 0.5f);
        StaticBody ground= new StaticBody(this, shape);
        ground.setPosition(new Vec2(0f, -12f));

        // create a BodyImage object and set it to the ground platform
        BodyImage image2 = new BodyImage("data/1.0terrain.png", 1.5f);
        ground.addImage(image2);


        // Make a suspended platform
        Shape platformShape = new BoxShape(3, 0.5f);
        StaticBody platform1 = new StaticBody(this, platformShape);
        platform1.setPosition(new Vec2(-13, -4f));

        // Make platform 2
        Shape platformShape2 = new BoxShape(3, 0.5f);
        StaticBody platform2 = new StaticBody(this, platformShape2);
        platform2.setPosition(new Vec2(13, -4f));


        // Make platform 3
        Shape platformShape3 = new BoxShape(3, 0.5f);
        StaticBody platform3 = new StaticBody(this, platformShape3);
        platform3.setPosition(new Vec2(16, 5f));

        // Make platform 4
        Shape platformShape4 = new BoxShape(3, 0.5f);
        StaticBody platform4 = new StaticBody(this, platformShape4);
        platform4.setPosition(new Vec2(-16, 5f));


        // Make platform 5
        Shape platformShape5 = new BoxShape(3, 0.5f);
        StaticBody platform5 = new StaticBody(this, platformShape5);
        platform5.setPosition(new Vec2(-28, 10f));


        // Make platform 6
        Shape platformShape6 = new BoxShape(3, 0.5f);
        StaticBody platform6 = new StaticBody(this, platformShape6);
        platform6.setPosition(new Vec2(28, 10f));


        // Make platform 7
        Shape platformShape7 = new BoxShape(3, 0.5f);
        StaticBody platform7 = new StaticBody(this, platformShape7);
        // platform7.setPosition(new Vec2(-8, 11f));


        //Image platform 1
        platform1.addImage(image);
        //Image platform 2
        platform2.addImage(image);
        //Image platform 3
        platform3.addImage(image);
        //Image platform 4
        platform4.addImage(image);
        //Image platform 5
        platform5.addImage(image);
        //Image platform 6
        platform6.addImage(image);
        //Image platform 7
        platform7.addImage(image);


        // Boundries

        // Make wall boundry left
        Shape platformShape20 = new BoxShape(1, 60f);
        StaticBody platform20 = new StaticBody(this, platformShape20);
        platform20.setPosition(new Vec2(-36, 3f));

        // Make wall boundry right
        Shape platformShape21 = new BoxShape(1, 60f);
        StaticBody platform21 = new StaticBody(this, platformShape21);
        platform21.setPosition(new Vec2(36, 3f));

        // Make top screen boundary
        Shape platformShape22 = new BoxShape(60f, 1f);
        StaticBody platform22 = new StaticBody(this, platformShape22);
        platform22.setPosition(new Vec2(0f, 20f));


    }

    public void coinPopulation() {
        //coin 1
        CoinView coin1 = new CoinView(this, 1);
        coin1.setPosition(new Vec2(28, 20));

        //coin2
        CoinView coin2 = new CoinView(this, 1);
        coin2.setPosition(new Vec2(14, 4));

        //coin3
        CoinView coin3 = new CoinView(this, 1);
        coin3.setPosition(new Vec2(25, 20));

        //coin 4
        CoinView coin4 = new CoinView(this, 1);
        coin4.setPosition(new Vec2(15, 20));

        //coin 5
        CoinView coin5 = new CoinView(this, 1);
        coin5.setPosition(new Vec2(-17, 20));

        //coin 6
        CoinView coin6 = new CoinView(this, 1);
        coin6.setPosition(new Vec2(-30, 8));

        //coin 7
        CoinView coin7 = new CoinView(this, 1);
        coin7.setPosition(new Vec2(-34, 10));

        //coin 8
        CoinView coin8 = new CoinView(this, 1);
        coin8.setPosition(new Vec2(-26, 20));

        //coin 9
        CoinView coin9 = new CoinView(this, 1);
        coin9.setPosition(new Vec2(-30, 15));

        //coin 10
        CoinView coin10 = new CoinView(this, 1);
        coin10.setPosition(new Vec2(-17, 3));

        //coin 11
        CoinView coin11 = new CoinView(this, 1);
        coin11.setPosition(new Vec2(11, 20));

        //coin 12
        CoinView coin12 = new CoinView(this, 1);
        coin12.setPosition(new Vec2(-11, 16));

        //coin 13
        CoinView coin13 = new CoinView(this, 1);
        coin13.setPosition(new Vec2(-1, 0));

        //coin 14
        CoinView coin14 = new CoinView(this, 1);
        coin14.setPosition(new Vec2(-13, 0));

    }



}



