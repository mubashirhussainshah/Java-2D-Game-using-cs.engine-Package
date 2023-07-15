package Game;

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import javax.swing.*;

public class HealthBar {

    private int maxHealth;
    private int currentHealth;
    private Shape shape;
    private StaticBody[] healthBar;
    private World world;
    private BodyImage HP = new BodyImage ("data/Greenmushroom.png");
    private BodyImage HPG = new BodyImage ("data/GreenmushroomGone.png");

    public HealthBar(World world, int maxHealth) {
        this.world = world;
        this.maxHealth = maxHealth;
        currentHealth = maxHealth;
        shape = new BoxShape(0.5f, 0.5f);
        healthBar = new StaticBody[maxHealth];

        for (int i = 0; i < maxHealth; i++) {
            healthBar[i] = new StaticBody(this.world, shape);
            healthBar[i].setPosition(new Vec2(-33f + i, 16f));
            healthBar[i].addImage(HP);
        }
    }

    public int getHealth() {
        return currentHealth;
    }


    public void setHealth(int health) {
        if(health == 0) gameOver();
        currentHealth = health;

        for (int i = 0; i < maxHealth; i++) {
            if (i < currentHealth) {
                healthBar[i].addImage(HP);
            } else {
                healthBar[i].addImage(HPG);
            }
        }
    }

    public void decreaseHealth() {
        if (currentHealth > 0) {
            currentHealth--;
            for (int i = 0; i < maxHealth; i++) {
                if (i < currentHealth) {
                    // This body represents a point of health that has not been lost yet,
                    // so use the HP image
                    healthBar[i].addImage(HP);
                } else {
                    // This body represents a point of health that has been lost,
                    // so use the HPG image
                    healthBar[i].addImage(HPG);
                }
            }
        }

        if (currentHealth == 0) {
            gameOver();
        }
    }

    public void increaseHealth() {
        if (currentHealth < maxHealth) {
            healthBar[currentHealth].addImage(HP);
            currentHealth++;
        }
    }

    public void gameOver(){
        ImageIcon icon = new ImageIcon("data/GameOver.gif");
        JOptionPane.showMessageDialog(null, "", "", JOptionPane.ERROR_MESSAGE,icon);
        System.exit(0);
    }



}
