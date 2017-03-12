package com.escapetomars.sprites;

import com.escapetomars.util.MusicFactory;

public class Crash extends Sprite{

	public Crash(int x, int y) {
		super(x, y);
		initSprite();
	}
	
	private void initSprite() {
		loadImage("res/explosion.png");
		setImageDimensions();
		Thread crashSound = new Thread(MusicFactory.getMusic(MusicFactory.CRASH_SOUND));
		crashSound.start();
	}

	public void move(int x) {
		this.x -= x;
	}

}
