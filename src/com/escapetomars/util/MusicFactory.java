package com.escapetomars.util;

import com.escapetomars.interfaces.Audible;

public abstract class MusicFactory {
	public static final int PROLOGUE_MUSIC = 0;
	public static final int MAIN_MUSIC = 1;
	public static final int GAME_OVER_MUSIC = 2;
	public static final int CRASH_SOUND = 3;
	
	public static Audible getMusic(int musicType){
		switch (musicType) {
		case 0:
			return new PrologueMusic();
		case 1:
			return new MainMusic();
		case 2:
			return new GameOverMusic();
		case 3:
			return new CrashSound();
		}
		return null;
	}

}
