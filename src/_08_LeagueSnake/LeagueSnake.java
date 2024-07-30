package _08_LeagueSnake;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import processing.core.PApplet;

public class LeagueSnake extends PApplet {
	static final int WIDTH = 500;
	static final int HEIGHT = 500;

	/*
	 * Game variables
	 * 
	 * Put all the game variables here.
	 */
	Segment head;
	int foodX;
	int foodY;
	int direction = RIGHT;
	int pieces = 0;
	ArrayList<Segment> segments = new ArrayList<>();

	/*
	 * Setup methods
	 * 
	 * These methods are called at the start of the game.
	 */
	@Override
	public void settings() {
		setSize(WIDTH, HEIGHT);
	}

	@Override
	public void setup() {
		head = new Segment(10, 10);
		frameRate(20);
		dropFood();
	}

	void dropFood() {
		// Set the food in a new random location
		foodX = ((int) random(50) * 10);
		foodY = ((int) random(50) * 10);
	}

	/*
	 * Draw Methods
	 * 
	 * These methods are used to draw the snake and its food
	 */

	@Override
	public void draw() {
		move();
		background(0, 0, 0);
		drawFood();
		drawSnake();
		eat();
	}

	void drawFood() {
		// Draw the food
		fill(0, 0, 255);
		rect(foodX, foodY, 10, 10);

	}

	void drawSnake() {
		// Draw the head of the snake followed by its tail
		fill(255, 165, 0);
		rect(head.x, head.y, 10, 10);
		manageTail();
	}

	void drawTail() {
		// Draw each segment of the tail
		for (int i = 0; i < segments.size(); i++) {
			fill(255, 165, 0);
			rect(segments.get(i).x, segments.get(i).y, 10, 10);
		}

	}

	/*
	 * Tail Management methods
	 * 
	 * These methods make sure the tail is the correct length.
	 */

	void manageTail() {
		// After drawing the tail, add a new segment at the "start" of the tail and
		// remove the one at the "end"
		// This produces the illusion of the snake tail moving.
		checkTailCollision();
		drawTail();
		Segment NewTail = new Segment(head.x, head.y);
		segments.add(NewTail);
		segments.remove(0);
	}

	void checkTailCollision() {
		// If the snake crosses its own tail, shrink the tail back to one segment
		for (int i = 0; i < segments.size(); i++) {
			if (head.x == segments.get(i).x && head.y == segments.get(i).y) {
				pieces = 1;
				Segment replace = new Segment(head.x, head.y);
				segments.clear();
				System.out.println(pieces);
			}
		}
	}

	/*
	 * Control methods
	 * 
	 * These methods are used to change what is happening to the snake
	 */

	@Override
	public void keyPressed() {
		// Set the direction of the snake according to the arrow keys pressed

		if (keyCode == KeyEvent.VK_UP && direction != DOWN) {
			direction = UP;
		}
		if (keyCode == KeyEvent.VK_DOWN && direction != UP) {
			direction = DOWN;
		}
		if (keyCode == KeyEvent.VK_RIGHT && direction != LEFT) {
			direction = RIGHT;
		}
		if (keyCode == KeyEvent.VK_LEFT && direction != RIGHT) {
			direction = LEFT;
		}
	}

	void move() {
		// Change the location of the Snake head based on the direction it is moving.

		if (direction == UP) {
			head.y -= 10;
		} else if (direction == DOWN) {
			head.y += 10;
		} else if (direction == LEFT) {
			head.x -= 10;
		} else if (direction == RIGHT) {
			head.x += 10;
		}
		checkBoundaries();
	}

	void checkBoundaries() {
		// If the snake leaves the frame, make it reappear on the other side
		if (head.x < 0) {
			head.x = WIDTH - 10;
			eliminated();
		}
		if (head.x > WIDTH) {
			head.x = 10;
			eliminated();
		}
		if (head.y < 0) {
			head.y = HEIGHT - 10;
			eliminated();
		}
		if (head.y > HEIGHT) {
			head.y = 10;
			eliminated();
		}
	}
void eliminated() {
	pieces = 1;
	Segment replace = new Segment(head.x, head.y);
	segments.clear();
	System.out.println(pieces);
}
	void eat() {
		// When the snake eats the food, its tail should grow and more
		// food appear
		if (head.x == foodX && head.y == foodY) {
			pieces++;
			dropFood();
			System.out.println(pieces);
			Segment eaten = new Segment(head.x, head.y);
			segments.add(eaten);
		}
	}

	static public void main(String[] passedArgs) {
		PApplet.main(LeagueSnake.class.getName());
	}

}
