package com.escapetomars.sprites;

import java.util.Timer;
import java.util.TimerTask;

public class InsertCoin extends Sprite{
	private boolean canSeeImg;

	public InsertCoin(int x, int y) {
		super(940, 555);
		initSprite();
	}
		
	private void initSprite() {
		loadImage("res/insertCoin3.png");
		setImageDimensions();
		
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				if(canSeeImg){
					loadImage("res/insertCoin3.png");
					canSeeImg = false;
				}else{
					hideImage();
					canSeeImg = true;
				}

			}
		}, 200, 1000);
	}
	
	private void hideImage() {
		this.loadImage("");
	}
}
