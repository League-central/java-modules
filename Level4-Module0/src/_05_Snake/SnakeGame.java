package _05_Snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SnakeGame implements ActionListener, KeyListener {
    public static final Color BORDER_COLOR = Color.WHITE;
    public static final Color BACKGROUND_COLOR = Color.BLACK;
    public static final Color FOOD_COLOR = Color.RED;
    public static final int WIDTH = 15;
    public static final int HEIGHT = 12;
    public static final int WINDOW_SCALE = 50;
    public static final int WINDOW_WIDTH = WINDOW_SCALE * WIDTH;
    public static final int WINDOW_HEIGHT = WINDOW_SCALE * HEIGHT;

    private JFrame window;
    private JPanel panel;

    private Snake snake;

    private Timer timer;

    private Location foodLocation;

    public SnakeGame() {
        snake = new Snake(new Location(WIDTH / 2, HEIGHT / 2));

        window = new JFrame("Snake");
        panel = new JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            public void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;

                g2.setColor(BACKGROUND_COLOR);
                g2.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

                g2.setColor(FOOD_COLOR);
                g2.drawOval(foodLocation.getX() * WINDOW_SCALE,
                        foodLocation.getY() * WINDOW_SCALE, Snake.BODY_SIZE,
                        Snake.BODY_SIZE);
                snake.draw(g);
            }
        };

        panel.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        window.add(panel);

        timer = new Timer(0, this);

        window.pack();
        window.addKeyListener(this);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

        setFoodLocation();

        startGame();
    }

    public void startGame() {
        String instructions = "Collect food to grow your snake.\nThe game is "
                + "over if you run into your tail or the edge of the window."
                + "\nClick OK to begin!";

        JOptionPane.showMessageDialog(null, instructions);

        // Adjust delay here if you want snake to go slower or faster.

        timer.setDelay(250);

        timer.start();
    }

    public static void main(String[] args) {
        new SnakeGame();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        /*
         * Use a switch statement on e.getKeyCode() to determine which key was
         * pressed. Hint: Remember int KeyCode constants take a form matching
         * KeyEvent.VK_UP.
         * 
         * If an arrow key is pressed, set the snake's direction accordingly.
         * 
         * If the space key is pressed, call the snake's feed method.
         */
        
        

    }

    private void setFoodLocation() {

        /*
         * Create a new Location object that is set to a random location between
         * 0 and the WIDTH and HEIGHT variables.
         */

        

        /*
         * Set the foodLocation member variable equal to the Location object you
         * just created.
         * 
         * Use the Snake class's isLocationOnSnake method to make sure you don't
         * put the food on top of the snake.
         */


    }

    private void gameOver() {

        // Stop the timer member variable.


        
        // Tell the user their snake is dead.



        // Ask the user if they want to play again.

        

        /*
         * If the user wants to play again, call the Snake class's resetLocation
         * method and this class's setFoodLocation method then restart the
         * timer. Otherwise, exit the game.
         */

        

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Call the Snake class's update method.



        /*
         * If the snake is colliding with its own body or if the snake moves
         * outside the bounds of the frame call the gameOver method.
         */



        /*
         * If the location of the snake's head is equal to the location of the
         * food, feed the snake and set the food location.
         */

        

    }
}
