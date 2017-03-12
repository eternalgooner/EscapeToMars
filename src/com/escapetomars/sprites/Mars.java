package com.escapetomars.sprites;

public class Mars extends Sprite{

	private int moveCounter = 0;

	public Mars(int x, int y) {
		super(x, y);
		initMars();
	}

	private void initMars() {
		loadImage("res/mars.png");
		setImageDimensions();
	}

	public void moveCloser(){
		++moveCounter;
		if (moveCounter % 5 == 0) {
			y += 1;
		}
	}
}
