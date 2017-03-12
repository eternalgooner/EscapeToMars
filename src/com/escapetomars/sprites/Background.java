package com.escapetomars.sprites;

import java.util.ArrayList;
import java.util.Random;

import com.escapetomars.interfaces.Collideable;

public class Background extends Sprite {
	private int whenToScroll = 0;
	private ArrayList<Collideable> fireballs;
	private ArrayList<Collideable> trees;
	private ArrayList<Collideable> lightnings;
	private int level;

	public Background(int x, int y, int level) {
		super(0, -50);
		this.level = level;
		initBackground(level);
	}

	private void initBackground(int level) {
		switch (level) {
		case 1:
			loadImage("res/level1pic.jpg");
			break;
		case 2:
			loadImage("res/level2pic.jpg");
			break;
		case 3:
			loadImage("res/level3pic.jpg");
			break;
		case 4:
			loadImage("res/bigSpace.jpg");
			break;
		default:
			break;
		}
	
		setImageDimensions();
		fireballs = new ArrayList<Collideable>();
		trees = new ArrayList<Collideable>();
		lightnings = new ArrayList<Collideable>();
	}

	public ArrayList<Collideable> getLightnings() {
		return lightnings;
	}

	public ArrayList<Collideable> getFireballs() {
		return fireballs;
	}
	
	public ArrayList<Collideable> getTrees() {
		return trees;
	}

	public void move() {
		//speed of background image - gets faster each level
		if(level == 1){
			if(whenToScroll % 4 == 0)x -= 1;
			++whenToScroll;
		}else if(level == 2){
			if(whenToScroll % 2 == 0)x -= 1;
			++whenToScroll;
		}else if(level == 3){
			 x -= 1;
		}else{
			y += 7;
			if(y > -100){
				y = -3_000;
			}
		}
	}

	public void fireFireball() {
		// position fireball enters sky
		Random random = new Random();
		int skyPostion = random.nextInt(1400);
		fireballs.add(new Fireball(skyPostion, -100, level));		
	}

	public void fireLightning(boolean soundIsOn) {
		Random random = new Random();
		int lightningPosition = random.nextInt(1000);
		// position of lightning
		lightnings.add(new Lightning(lightningPosition, 0));
	}
	
	public void newTree() {
		Random random = new Random();
		int treeTpe = random.nextInt(3);
		// position of tree
		
		switch (treeTpe) {
		case 0: trees.add(new Tree1(1100, randomTreeHeight(1), level));			
			break;
		case 1: trees.add(new Tree2(1100, randomTreeHeight(2), level));			
			break;
		case 2: trees.add(new Tree3(1100, randomTreeHeight(3), level));			
			break;
		default:
			break;
		}
	}

	private int randomTreeHeight(int tree) {
		Random random = new Random();
		switch (tree) {
		case 1: return random.nextInt(200) + 365;
		case 2: return random.nextInt(100) + 450;
		case 3: return random.nextInt(170) + 370;
		default:
			break;
		}
		return 0;
	}

	public void moveQuicker() {
		x -= 3;
	}
}
