package com.escapetomars.sprites;

public class Rockets extends Sprite{

	public Rockets(int x, int y) {
		super(x, y);
		initRockets();
	}

	private void initRockets() {
		loadImage("res/takeOff2.png");
		setImageDimensions();
	}
	
	public void move(){
		y -= 1; 
	}

}
