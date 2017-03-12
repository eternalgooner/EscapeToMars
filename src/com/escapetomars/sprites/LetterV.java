package com.escapetomars.sprites;

import com.escapetomars.interfaces.Letter;

public class LetterV extends Sprite implements Letter{
	private int counter = 0;

	public LetterV(int x, int y) {
		super(x, y);
		initV();
	}

	private void initV() {
		loadImage("res/V.png");
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
