package com.escapetomars.sprites;

public class Title extends Sprite{
	public Title(int x, int y) {
		super(200, 100);
		initSprite();
	}
		
	private void initSprite() {
		loadImage("res/title1.png");
		setImageDimensions();
	}
}
