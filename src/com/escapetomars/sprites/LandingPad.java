package com.escapetomars.sprites;

public class LandingPad extends Sprite {
	
	private boolean isMoving = true;
	private boolean shouldBeMoving = true;

	public LandingPad(int x, int y) {
		super(2500, 578);
		initLandingPad();
	}

	private void initLandingPad() {
		loadImage("res/landingPad2.png");
		setImageDimensions();
	}

	public void move() {
		if(shouldBeMoving){
			x += -1;
			isMoving = true;
			return;
		}
		isMoving = false;
	}

	public boolean getIsMoving() {
		return isMoving;
	}

	public void setShouldBeMoving(boolean b) {
		shouldBeMoving = b;
	}
}
