package com.escapetomars.interfaces;

public interface Updateable {
	public void update(int screen, int score, int highScore, String name, int lives, int gameSpeed);
	public void update(int screen, int score, int highScore, String name, int lives);
}
