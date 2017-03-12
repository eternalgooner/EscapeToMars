package com.escapetomars.util;

import java.awt.event.KeyEvent;

import com.escapetomars.sprites.Sprite;

public class SoundOn extends Sprite {
	private boolean soundIsOn = true;
	private int showSoundCounter = 0;

	public SoundOn(int x, int y) {
		super(510, 20);
		initSound();
	}

	private void initSound() {
		loadImage("res/soundOn3.png");
		setImageDimensions();
	}

//	private void changeSoundSettings() {
//		if(soundIsOn){
//			loadImage("res/soundOff3.png");
//			soundIsOn = false;
//		}else if(!soundIsOn){
//			loadImage("res/soundOn3.png");
//			soundIsOn = true;
//		}
//	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_S) {
			if (soundIsOn) {
				soundIsOn = false;
			}else if(!soundIsOn){
				soundIsOn = true;
			};
		}
	}
	
	public boolean keepDisplayingSymbol(){
		if(showSoundCounter < 100){
			++showSoundCounter;
			return true;
		}else{
			//vis = false;
			return false;
		}
		
	}
}
