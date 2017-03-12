package com.escapetomars.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.escapetomars.interfaces.Audible;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class MainMusic implements Audible {
	private Player player;
	@SuppressWarnings("unused")	//is used, but eclipse keeps showing warning message
	private boolean isRunning = true;

	@Override
	public void run() {
		File audioFile = new File("res/main-music.mp3");
		FileInputStream fileInputStream;
		BufferedInputStream bufferedInputStream;
		try {
			fileInputStream = new FileInputStream(audioFile);
			bufferedInputStream = new BufferedInputStream(fileInputStream);
			player = new Player(bufferedInputStream);
			player.play();
		} catch (FileNotFoundException | JavaLayerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void playMusic() {
		run();
	}

	@Override
	public void stopMusic() {
		player.close();
		isRunning = false;		
	}
}
