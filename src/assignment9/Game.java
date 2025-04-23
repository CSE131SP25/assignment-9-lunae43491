package assignment9;

import java.awt.event.KeyEvent;

import edu.princeton.cs.introcs.StdDraw;

public class Game {
	Snake snake = new Snake();
	Food food = new Food(true);
	Food badFood = new Food(false);
	// Obstacle dimensions
	private double obstacleX = 0.3;
	private double obstacleY = 0.6;
	private double obstacleWidth = 0.1;
	private double obstacleHeight = 0.05;

	private int score = 0;
	
	
	public Game() {
		StdDraw.enableDoubleBuffering();
		
		//FIXME - construct new Snake and Food objects
	}
	
	public void play() {  
		while (snake.isInbounds() && !hitObstacle()) {
	        int dir = getKeypress();
	        snake.changeDirection(dir);
	        snake.move();

	        // Good food logic — grow + score++
	        if (snake.eatFood(food)) {
	            food = new Food(true);
	            score++;
	        }

	        if (snake.eatFood(badFood))  
	        { 
	            badFood = new Food(false); 
	            score--; // or snake dies, or shrinks, etc.
	        } 

	        updateDrawing();
	    }

	    // Optional: Add a game over screen here if you want bonus polish
	}

	
	private int getKeypress() {
		if(StdDraw.isKeyPressed(KeyEvent.VK_W)) {
			return 1;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_S)) {
			return 2;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_A)) {
			return 3;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_D)) {
			return 4;
		} else {
			return -1;
		}
	}
	
	/**
	 * Clears the screen, draws the snake and food, pauses, and shows the content
	 */
	private void updateDrawing() {
		//FIXME
		StdDraw.clear();
		snake.draw();
		food.draw();
		badFood.draw();
		// Draw the obstacle
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.filledRectangle(obstacleX, obstacleY, obstacleWidth / 2, obstacleHeight / 2);

		//draw score
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.textLeft(0.02, 0.98, "Score: " + score);
		StdDraw.pause(50);
		StdDraw.show();
		
		
		/*
		 * 1. Clear screen
		 * 2. Draw snake and food
		 * 3. Pause (50 ms is good)
		 * 4. Show
		 */
	}
	private boolean hitObstacle() {
	    double headX = snake.getHeadX();
	    double headY = snake.getHeadY();

	    return headX >= (obstacleX - obstacleWidth / 2) &&
	           headX <= (obstacleX + obstacleWidth / 2) &&
	           headY >= (obstacleY - obstacleHeight / 2) &&
	           headY <= (obstacleY + obstacleHeight / 2);
	}

	public static void main(String[] args) {
		Game g = new Game();
		g.play();
	}
}
