package com.escapetomars.util;

import java.util.Timer;
import java.util.TimerTask;

import com.escapetomars.interfaces.Crashable;
import com.escapetomars.sprites.Apache;

public class CrashBuffer implements Runnable {
	private Crashable crashable;
	private boolean isNormalCrashable;
	private int bufferCounter = 0;
	
	public CrashBuffer(Crashable crashable) {
		this.crashable = crashable;
	}

	//use thread to swap between images for a given period to give feedback to the user that the apache was hit
	@Override
	public void run() {
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				if(isNormalCrashable){
					if (crashable instanceof Apache) {
						crashable.loadImage("res/apacheHit.gif");
					}else{
						crashable.loadImage("res/shuttleHit.gif");
					}
					isNormalCrashable = false;
				}else{
					if (crashable instanceof Apache) {
						crashable.loadImage("res/apache.gif");
					}else{
						crashable.loadImage("res/shuttleHit.gif");
					}
					isNormalCrashable = true;
				}
				
				++bufferCounter;
				
				if(bufferCounter == 7){
					timer.cancel();
				}
			}
		}, 300, 300);
	}


}
