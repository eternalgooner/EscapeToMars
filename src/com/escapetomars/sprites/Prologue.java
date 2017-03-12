package com.escapetomars.sprites;

public class Prologue extends Sprite {
	private int moveCounter = 0;
	public Prologue(int x, int y) {
		super(100, 700);
		init();
	}

	private void init() {
		loadImage("res/prologue.png");
		setImageDimensions();

	}
	
	public void move() {
		if(moveCounter % 4 == 0){
			y -= 2;	
		}
		++moveCounter;
	}

}
