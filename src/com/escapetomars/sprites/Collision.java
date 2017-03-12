package com.escapetomars.sprites;

public class Collision extends Sprite {

	public Collision(int x, int y) {
		super(x, y);
		initSprite();
	}

	private void initSprite() {
		loadImage("res/smallSmoke.png");
		setImageDimensions();
	}

	public void move(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
