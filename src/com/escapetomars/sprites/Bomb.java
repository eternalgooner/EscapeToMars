package com.escapetomars.sprites;

public class Bomb extends Sprite {

	private final int SCREEN_HEIGHT = 720;
	private final int BOMB_SPEED = 2;

	public Bomb(int x, int y) {
		super(x, y);
		initBomb();
	}

	private void initBomb() {
		loadImage("res/missile.png");
		setImageDimensions();
	}

	public void move() {
		y += BOMB_SPEED;

		if (y > SCREEN_HEIGHT) {
			vis = false;
		}
	}
}
