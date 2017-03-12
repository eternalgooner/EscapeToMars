package com.escapetomars.sprites;

import com.escapetomars.interfaces.Letter;

public class LetterE extends Sprite implements Letter{
	private int counter = 0;

	public LetterE(int x, int y) {
		super(x, y);
		initE();
	}

	private void initE() {
		loadImage("res/E.png");
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
