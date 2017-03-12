package com.escapetomars.sprites;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import com.escapetomars.util.BufferedImageLoader;
import com.escapetomars.util.SpriteSheet;

public class RunningMan extends Sprite {
	private BufferedImage spriteSheet;
	private BufferedImage sprite;
	private int dx;
	private int dy;
	private ArrayList<BufferedImage> spriteList;
	private int spriteStartX;
	private int spriteStartY;
	private int spriteCounter = -1;
	private int spriteFrameRate = 1;
	private BufferedImage currentImage;
	private final int RIGHT = 1;
	private boolean isRunningLeft;
	private boolean isRunningOnTheSpot;

	public RunningMan(int x, int y) {
		super(x, y);
		currentImage = null;
		spriteList = new ArrayList<BufferedImage>();
		initSprites();
	}

	private void initSprites() {
		for (int i = 0; i < 16; ++i) {
			if (i == 8) {
				spriteStartX = 2;
				spriteStartY += 140;
			}
			spriteList.add(getImage(spriteStartX, spriteStartY));
			spriteStartX += 108;
		}
		currentImage = spriteList.get(0);
	}

	private BufferedImage getImage(int spriteStartX, int spriteStartY){
		BufferedImageLoader loader = new BufferedImageLoader();
		BufferedImage spriteSheet = null;
		try {
			spriteSheet = loader.loadImage("res/spriteBoy.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		SpriteSheet ss = new SpriteSheet(spriteSheet);
		
		sprite = ss.grabSprite(spriteStartX, spriteStartY, 100, 140);
		image = sprite;
		return (BufferedImage) image;
	}

	public void move() {
		x += dx;
		y += dy;
	}
	
	public BufferedImage grabSprite(int x, int y, int width, int height){
		BufferedImage sprite = spriteSheet.getSubimage(x, y, width, height);
		return sprite;
	}

	// speed of movement
	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		//left
		if (key == KeyEvent.VK_A) {
			++spriteFrameRate;
			dx = -2;
			getNextSprite(0);
		}

		//right
		if (key == KeyEvent.VK_D) {
			if (!isRunningOnTheSpot) {
				++spriteFrameRate;
				dx = 2;
				getNextSprite(1);
			}else{
				++spriteFrameRate;
				getNextSprite(1);
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_A) {
			dx = 0;
		}

		if (key == KeyEvent.VK_D) {
			dx = 0;
		}
	}
	
	public BufferedImage getCurrentSpriteImage(){
		return currentImage;
	}

	//cycle through the 16 sprites of running man
	private void getNextSprite(int direction) {
		if (spriteFrameRate % 4 == 0) {
			if (direction == RIGHT) {
				if (isRunningLeft) {
					spriteCounter = -1;
				}
				if (spriteCounter == 7){
					spriteCounter = -1;
				}
				++spriteCounter;
				currentImage = spriteList.get(spriteCounter);
				++spriteFrameRate;
				isRunningLeft = false;
			}else{
				isRunningLeft = true;
				if (spriteCounter == 15){
					spriteCounter = 8;
				}
				++spriteCounter;
				currentImage = spriteList.get(spriteCounter);
				++spriteFrameRate;
			}
		}
	}

	public void setIsRunningOnTheSpot(boolean b) {
		isRunningOnTheSpot = b;
	}
}
