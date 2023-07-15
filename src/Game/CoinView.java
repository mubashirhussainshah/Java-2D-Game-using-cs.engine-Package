///**
// * A class for making the coin object that the main walker needs to collect
// */
//package Game;
//
////import Levels.Levels;
//import city.cs.engine.*;
//import org.jbox2d.common.Vec2;
//
//import javax.sound.sampled.LineUnavailableException;
//import javax.sound.sampled.UnsupportedAudioFileException;
//import java.io.IOException;
//
//
//public class Coins extends DynamicBody {
//
//
//    public Coins(GameWorld world, int posX, int posY) {
//        super(world, new BoxShape(.9f, .9f));
//        new AttachedImage(this, new BodyImage("data/level/Coin.gif"), 2f, 0, new Vec2(0, 0));
//
//
//
//
//        //@Override
//        //public void destroy() {
//            super.destroy();
//        }
//
//
//    }
//
package Game;

import Levels.GameWorld;
import Listeners.CoinPickUpListener;
import city.cs.engine.*;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class CoinView extends DynamicBody {

    private static SoundClip pickupSound;
    //setup the sound
    static {
        try {
            pickupSound = new SoundClip("data/coin.wav");
            pickupSound.setVolume(.1);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);

        }
    }
    private static final Shape coinShape = new CircleShape(0.5f);
    private static final BodyImage coinImage = new BodyImage("data/coin.png", 1);
    private int value;

    public CoinView(GameWorld world, int value) {
        super(world, coinShape);
        addImage(coinImage);
        this.setName("COIN");
        this.value = value;
        addCollisionListener(new CoinPickUpListener(world));
    }



    @Override
    public void destroy(){
        pickupSound.play();
        super.destroy();
    }

}
