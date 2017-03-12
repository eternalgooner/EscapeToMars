package com.escapetomars.sprites;

public class SmallCollision extends Collision{

	public SmallCollision(int x, int y) {
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
