package Game;

import Levels.GameWorld;
import Listeners.HealthPickUpListener;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class HealthItem extends DynamicBody {

    private static SoundClip pickupSound;

    //setup the sound
    static {
        try {
            pickupSound = new SoundClip("data/GreenMushroom.wav");
            pickupSound.setVolume(.9);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);

        }
    }
    private static final Shape shape = new BoxShape(1, (float )1.3);
    private static final BodyImage idle = new BodyImage("data/Greenmushroom.png", 3);


    public HealthItem(GameWorld gameWorld) {
        super(gameWorld, shape);
        this.setName("HEALTHPACK");

        addImage(idle);
        setPosition(new Vec2(0, -6f));

        addCollisionListener(new HealthPickUpListener());
    }

    @Override
    public void destroy(){
        pickupSound.play();
        super.destroy();
    }
}
