package Game;

import city.cs.engine.*;


public class Portal extends StaticBody {


    /*
    constructor takes a World object as an argument, which represents the 2D world in which the portal will exist.

    */
    public Portal(World world) {
        super(world, new BoxShape(1f, 1f));
        addImage(new BodyImage("data/Portal.gif", 2f));

    }
}