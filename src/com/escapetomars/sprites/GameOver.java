package com.escapetomars.sprites;

public class GameOver extends Sprite{

	public GameOver(int x, int y) {
		super(400, 100);
		initSprite();
	}
	
	private void initSprite() {
		loadImage("res/gameOver.png");
		setImageDimensions();
	}

	public void move(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
