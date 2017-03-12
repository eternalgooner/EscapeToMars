package com.escapetomars.sprites;

public class BigCollision extends Collision{

	public BigCollision(int x, int y) {
		super(x, y);
		initSprite();
	}
	
	private void initSprite() {
		loadImage("res/bigSmoke.png");
		setImageDimensions();
	}

	public void move(int x, int y) {
		this.x = x;
		this.y = y;
	}

}
