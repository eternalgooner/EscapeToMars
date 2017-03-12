package com.escapetomars.sprites;

import com.escapetomars.interfaces.Letter;

public class Digit1 extends Sprite implements Letter{
	private int counter = 0;

	public Digit1(int x, int y, int level) {
		super(x, y);
		init1(level);
	}

	private void init1(int level) {
		switch (level) {
		case 1:
			loadImage("res/1.png");
			break;
		case 2:
			loadImage("res/2.png");
			break;
		case 3:
			loadImage("res/3.png");
			break;

		default:
			break;
		}
		
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
}
