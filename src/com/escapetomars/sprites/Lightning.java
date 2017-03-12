package com.escapetomars.sprites;

import com.escapetomars.interfaces.Collideable;

public class Lightning extends Sprite implements Collideable{

	private int lightningOnScreen = 0;
	//private boolean soundIsOn = true;

	public Lightning(int x, int y) {
		super(x, y);
		initLightning();
	}

	private void initLightning() {
		loadImage("res/lightning.jpg");
		setImageDimensions();
	}

	public void strike() {
		new Lightning(0, 0);
	}

	public void move() {
		if (lightningOnScreen > 20){
			vis = false;
		}
	
		++lightningOnScreen;
		x -= 1;
		y += 1;
	}
}
