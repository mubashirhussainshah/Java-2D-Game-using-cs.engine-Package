package Handlers;

import Game.Student;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardHandler implements KeyListener {
    private Student student;
    // private BodyImage standingImage, walkingImage;

    public KeyboardHandler() {
    }

    public void addStudentToKeyboardHandler(Student student) {
        this.student = student;
        // standingImage = new BodyImage("data/standing.png", 2);
        // walkingImage = new BodyImage("/Users/user/Documents/IN1007/GitHub/javaproject2023-kalqemlas/data/run.gif", 2);
        // student.addImage(standingImage);
    }


    @Override
//    public void keyPressed(KeyEvent e) {
//        if (this.student == null) return;
//        // handle key press event
//        int keyCode = e.getKeyCode();
//        if (keyCode == KeyEvent.VK_UP) {
//            if (this.student.getAmountJumps() >= 2) {
//                return;
//            }
//            student.jump();
//            // handle up arrow key press
//        } else if (keyCode == KeyEvent.VK_DOWN) {
//            // handle down arrow key press
//        } else if (keyCode == KeyEvent.VK_LEFT) {
//            student.startWalking(-4);
//            student.removeAllImages();
//            student.moveRight();
//            student.addImage(walkingImage); // add left-facing image
//            // handle left arrow key press
//        } else if (keyCode == KeyEvent.VK_RIGHT) {
//            student.startWalking(4);
//            student.removeAllImages();
//            student.moveRight();
//            student.addImage(walkingImage); // add right-facing image
//            // handle right arrow key press
//        }
//    }

    public void keyPressed(KeyEvent e) {
        if (this.student == null) return;
        // handle key press event
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_UP) {

            student.collided_walking_left = false;
            student.collided_walking_right = false;
            student.collided= false;
            if(student.isJumping())
                return;

            student.setJumpImage();
            student.jump();
            // handle up arrow key press
        } else if (keyCode == KeyEvent.VK_DOWN) {
            // handle down arrow key press
        } else if (keyCode == KeyEvent.VK_LEFT) {
            if (student.collided_walking_right) {
                student.collided_walking_right = false;
                student.collided = false;
            }
            if (!student.collided) student.startWalking(-4);
            else {
                student.stopWalking();
                student.collided_walking_left = true;
            }
            student.moveLeft();

        } else if(keyCode == KeyEvent.VK_RIGHT){
            if (student.collided_walking_left) {
                student.collided_walking_left = false;
                student.collided = false; }

            if (!student.collided) student.startWalking(+4);
            else {
                student.stopWalking();
                student.collided_walking_right = true;
            }
            student.moveRight();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT) {
            student.stopWalking();
        } else if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN) {
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {
        // Handle key typed event
    }
}
