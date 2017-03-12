package com.escapetomars.sprites;

public class HomeBackground extends Sprite {

	public HomeBackground(int x, int y) {
		super(x, y);
		initBackground();
	}

	private void initBackground() {
		loadImage("res/homeScreen.jpg");
		setImageDimensions();
	}
}
