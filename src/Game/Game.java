package Game;

import Handlers.KeyboardHandler;
import Levels.GameWorld;
import Levels.WorldOne;
import Levels.WorldThree;
import Levels.WorldTwo;
import city.cs.engine.SoundClip;


import javax.swing.JFrame;

    /**
     * Main Game entry point
     */
    public class Game {
        private final KeyboardHandler keyboardHandler;
        private GameWorld gameWorld;
        private GameView view;
        private SoundClip backgroundMusic;
        private double volume;
        private int currentLevel = 1;
        JFrame frame = new JFrame("City Game");

    /**
    * Initialise a new Game.
    */
    public Game() {
                    try {
                        backgroundMusic = new SoundClip("data/BackgroundMusic.wav"); // Open the audio file from the path given
                        this.volume = 0.10d;
                        backgroundMusic.setVolume(volume);
                        backgroundMusic.loop();
                        backgroundMusic.play();
                        System.out.println("Playing");
                    } catch (Exception e) {
                        System.out.println(e); // Print the error to the console
                    }

        keyboardHandler = new KeyboardHandler();

        // Starting with 1st level
        gameWorld = new WorldOne(this, keyboardHandler);

        // make a view to look into the Game world
        view = new GameView(gameWorld, 1400, 700, currentLevel);


        //4. create a Java window (frame) and add the Game view to it
        frame.add(view);

       // add keyboard handler to frame
        frame.addKeyListener(keyboardHandler);

        // enable the frame to quit the application
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        // don't let the frame be resized
        frame.setResizable(false);
        // size the frame to fit the world view
        frame.pack();
        // finally, make the frame visible
        frame.setVisible(true);

        // start our Game world simulation!
        gameWorld.start();
    }

        public void progressLevel(){
            gameWorld.stop();

            if(currentLevel==1) gameWorld = new WorldTwo(this, keyboardHandler);                // assigning level 2
            else if(currentLevel==2) gameWorld = new WorldThree(this, keyboardHandler);         // assigning level 3

            switch (currentLevel) {
                case 1:
                    case 2:
                        currentLevel++;
                        frame.getContentPane().removeAll();                                     // remove previous content from jFrame
                        view = new GameView(gameWorld, 1400,700, currentLevel);     // updating the view
                        view.setWorld(gameWorld);                                               // set view to game
                        frame.add(view);                                                        // add it to frame
                        gameWorld.start();                                                      // start game
                        frame.setVisible(true);                                                 // make frame visible
                        break;
                default:
                    break;
            }
        }

    public static void main(String[] args) {
        new Game();
    }
}
