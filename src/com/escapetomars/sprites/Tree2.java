package com.escapetomars.sprites;

import com.escapetomars.interfaces.Plantable;

public class Tree2 extends Sprite implements Plantable{
	private int level;
	private int treeSpeed;
	
	public Tree2(int x, int y, int level) {
		super(x, y);
		this.level = level;
		initBackground();
		
	}

	private void initBackground() {
		loadImage("res/tree02.png");
		setImageDimensions();
		switch (level) {
		case 1:
			treeSpeed = 2;
			break;
		case 2:
			treeSpeed = 5;
			break;
		case 3:
			treeSpeed = 7;
			break;
		default:
			break;
		}
	}

	public void move() {
		x -= treeSpeed;
		//if off screen then remove from arraylist
		if (x < -300) {
			vis = false;
		}
	}
}
