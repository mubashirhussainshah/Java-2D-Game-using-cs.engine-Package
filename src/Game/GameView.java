package Game;

import Levels.GameWorld;
import city.cs.engine.UserView;

import javax.swing.*;
import java.awt.*;


public class GameView extends UserView {
    private Image background;
    private Student student;
    private Image gameOverImage;
    private boolean isGameOver;


    public GameView(GameWorld w, int width, int height, int level) {
        super(w, width, height);
        this.student = w.getStudent();
        if(level==1) this.background = new ImageIcon("data/Brown.png").getImage();
        else if(level==2) this.background = new ImageIcon("data/background2.png").getImage();
        else this.background = new ImageIcon("data/background3.jpg").getImage();

        this.gameOverImage = new ImageIcon("data/GameOver.gif").getImage();
        this.isGameOver = false;
    }


    @Override
    protected void paintBackground(Graphics2D g) {
        g.setColor(Color.YELLOW);
        g.drawImage(this.background, 0, 0, 1400, 700, this);
        Font font = new Font("Arial", Font.BOLD, 25); // Set font type, style and size
        g.setFont(font); // Use the new font for drawing the text
        g.drawString("Score: " + student.getCoinsAmount(), (getWidth() / 2) - 50, 25);
    }


}

