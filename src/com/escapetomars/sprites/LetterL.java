package com.escapetomars.sprites;

import com.escapetomars.interfaces.Letter;

public class LetterL extends Sprite implements Letter, Runnable{
	private int counter = 0;
	private boolean running = true;

	public LetterL(int x, int y) {
		super(x, y);
		initL();
	}

	private void initL() {
		loadImage("res/L2.png");
		setImageDimensions();
	}

	public void move() {
		x -= 16;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	@Override
	public void run() {
		while (running) {
			move();
			++counter;
			System.out.println(Thread.currentThread() + " : counter is : " + counter);
			if (counter == 150) {
				System.out.println("exiting run method in thread");
				return;
			} 
		}
	}
}
