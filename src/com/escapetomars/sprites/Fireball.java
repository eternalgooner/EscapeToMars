package com.escapetomars.sprites;

import java.util.Random;

import com.escapetomars.interfaces.Collideable;

public class Fireball extends Sprite implements Collideable{
	private final int BOARD_HEIGHT = 720;
    private int fireballAngle = 0;
    private int fireballAcceleration = 0;

	public Fireball(int x, int y, int type) {
			super(x, y);
			initFireball(type);
	}
	
	private void initFireball(int type) {	  
		Random random = new Random();
		switch (type) {
		case 1:
			fireballAngle = random.nextInt(3);
			switch (fireballAngle) {
			case 0:
				loadImage("res/fireball1-1.png");
				break;
			case 1:
				loadImage("res/fireball01.png");
				break;
			case 2:
				loadImage("res/fireball1-3.png");
				break;
			default:
				break;
			}
			fireballAcceleration = 1;
			break;
		case 2:
			fireballAngle = random.nextInt(3);
			switch (fireballAngle) {
			case 0:
				loadImage("res/fireball2-11.png");
				break;
			case 1:
				loadImage("res/fireball02.png");
				break;
			case 2:
				loadImage("res/fireball2-31.png");
				break;
			default:
				break;
			}
			fireballAcceleration = 2;
			break;
		case 3:
			fireballAngle = random.nextInt(3);
			switch (fireballAngle) {
			case 0:
				loadImage("res/fireball3-1.png");
				break;
			case 1:
				loadImage("res/fireball03.png");
				break;
			case 2:
				loadImage("res/fireball3-3.png");
				break;
			default:
				break;
			}
			fireballAcceleration = 3;
			break;
		default:
			loadImage("res/finalFireball.png");
			fireballAngle = 0;
			fireballAcceleration = 9;
			break;
		}
	    setImageDimensions();
	}


	public void move() {
		x -= fireballAngle;
		y += fireballAcceleration;
		
		if (y > BOARD_HEIGHT) {
			vis = false;
		}
	}
}
