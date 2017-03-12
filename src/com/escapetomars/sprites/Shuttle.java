package com.escapetomars.sprites;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import com.escapetomars.interfaces.Crashable;
import com.escapetomars.util.CrashBuffer;

public class Shuttle extends Sprite implements Crashable{

	private int dx;
	private int dy;
	private ArrayList<Collision> collisions;
	private GameOver gameOver;
	private ArrayList<Crash> crashs;
	private int lives = 5;
	private boolean soundIsOn = true;
	private int moveInCounter;
	private boolean isReadyToTakeOff;
	public Rockets rockets;

	public Shuttle(int x, int y) {
		super(x, y);
		initCraft();
	}

	private void initCraft() {
		collisions = new ArrayList<Collision>();
		gameOver = null;
		crashs = new ArrayList<Crash>();
		rockets = new Rockets(945, 710);
		loadImage("res/shuttle.png");
		setImageDimensions();
	}

	public void move() {		
		y += dy;
		x += dx;
	}
	
	public void launchMove() {		
		y -= 1;
	}

	public void crash() {
		x += 1;
		y += 1;
	}

	public void decrementLife() {
		if (lives >= 1) {
			--lives;
			loadImage("res/shuttleHit.gif");
			giveShuttleCrashBuffer();
		}
	}

	private void giveShuttleCrashBuffer() {
		Thread crashBuffer = new Thread(new CrashBuffer(this));
		crashBuffer.start();
	}

	public void gameOver() {
		gameOver = new GameOver(200, 300);
	}
	
	public GameOver getGameOver() {
		return gameOver;
	}
	
	public ArrayList<Crash> getCrashs() {
		return crashs;
	}

	//speed of movement
	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			dx = -5;
		}

		if (key == KeyEvent.VK_RIGHT) {
			dx = 5;
		}

		//if up or down changed, then collision detection may need 
		//to be changed. As extra coordinates for detection go in increments of 5
		if (key == KeyEvent.VK_UP) {
			dy = -5;
		}

		if (key == KeyEvent.VK_DOWN) {
			dy = 5;
		}
		
		if (key == KeyEvent.VK_S) {
			if (soundIsOn) {
				soundIsOn = false;
			}else if(!soundIsOn){
				soundIsOn = true;
			};
		}
	}

	public void showDamage() {
		// position of smoke on apache
		if(lives == 4){
			collisions.add(new SmallCollision(this.getX(), this.getY()));
		}else if (lives == 2) {
			collisions.add(new BigCollision(this.getX(), this.getY()));
		}		
	}
	
	public int getLives(){
		return this.lives;
	}
	
	public void setLives(int lives){
		this.lives = lives;
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			dx = 0;
		}

		if (key == KeyEvent.VK_RIGHT) {
			dx = 0;
		}

		if (key == KeyEvent.VK_UP) {
			dy = 0;
		}

		if (key == KeyEvent.VK_DOWN) {
			dy = 0;
		}
	}

	public boolean isReadyToTakeOff() {
		return isReadyToTakeOff;
	}

	public boolean isSoundOn() {
		return soundIsOn;
	}

	public void setSoundOn(boolean soundIsOn) {
		this.soundIsOn = soundIsOn;
	}

	public void moveIntoScreen() {
		if (moveInCounter < 32) {
			++moveInCounter;
			x -= 2;
		}else{
			isReadyToTakeOff = true;
		}
	}

	public Rockets getRockets() {
		return rockets;
	}
	
	//life icon inner class
	public class LifeIcon extends Sprite{

		public LifeIcon(int x, int y) {
			super(28, 12);
			initSprite();
		}
		
		private void initSprite() {
			loadImage("res/smallShuttle.png");
			setImageDimensions();
		}
	}

	public ArrayList<Collision> getCollisions() {
		return collisions;
	}
}
