package com.escapetomars.util;

import java.util.Timer;
import java.util.TimerTask;

import com.escapetomars.sprites.Sprite;

public class StartBtnAnimation extends Sprite implements Runnable{
	private int nextButton = 3;
	private boolean animateUp;
	//public boolean isRunning = true;
	
	public StartBtnAnimation(int x, int y) {
		super(360, 535);
		init();
	}

	private void init() {
		loadImage("res/startGameBorder0.png");
		setImageDimensions();
	}

	@Override
	public void run() {
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				//while (isRunning) {
					if (nextButton == 3) {
						x = 358;
						loadImage("res/startGameBorder3.png");
						--nextButton;
						animateUp = false;
					} else if (nextButton == 2) {
						x = 355;
						loadImage("res/startGameBorder2.png");
						if (!animateUp) {
							--nextButton;
						} else {
							++nextButton;
						}

					} else if (nextButton == 1) {
						x = 360;
						loadImage("res/startGameBorder1.png");
						if (!animateUp) {
							--nextButton;
						} else {
							++nextButton;
						}

					} else if (nextButton == 0) {
						x = 358;
						loadImage("res/startGameBorder0.png");
						++nextButton;
						animateUp = true;
					} 
				//}
			}
		}, 100, 100);
	}
}
