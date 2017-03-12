package com.escapetomars.sprites;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.IntStream;

import com.escapetomars.interfaces.Crashable;
import com.escapetomars.util.CrashBuffer;
import com.escapetomars.util.MusicFactory;

public class Apache extends Sprite implements Crashable{

	private int dx;
	private int dy;
	private ArrayList<Bomb> bombs;
	private ArrayList<Collision> collisions;
	private GameOver gameOver;
	private ArrayList<Crash> crashs;
	private int lives = 5;
	private boolean hasLandingPad = false;
	private boolean hasLanded;
	private int[] landingXCoordinates = {274, 275, 276, 277, 278, 279, 280, 281, 282, 283, 284, 285, 286, 287, 288, 289, 290};
	private int[] landingYCoordinates = {600, 601, 602, 603, 604, 605, 606, 607, 608, 609, 610};
	private boolean gameIsOver = true;
	private boolean okToStartEndMusic = true;
	//private int crashBuffer = 0;

	public Apache(int x, int y) {
		super(x, y);
		initCraft();
	}

	private void initCraft() {
		bombs = new ArrayList<Bomb>();
		collisions = new ArrayList<Collision>();
		gameOver = null;
		crashs = new ArrayList<Crash>();
		loadImage("res/apache.gif");
		setImageDimensions();
	}

	public void move() {
		if(lives != 0) {
			//normal moving
			if (!hasLandingPad) {
				x += dx;
				y += dy;
			//moving with landing pad on screen
			}else{
				if (!hasLanded) {
					x += dx;
					y += dy;
					//tryToLand();
				}
			}
		}else{
			gameOver();
			crash();
			if(this.getY() == 700){
				crashs.add(new Crash(this.getX() - 100, this.getY() - 150));
			}
		}		
	}

	public void crash() {
		x += 1;
		y += 1;
	}

	public void decrementLife() {
		if (lives >= 1) {
			--lives;
			loadImage("res/apacheHit.gif");
			giveApacheCrashBuffer();
		}
	}

	private void giveApacheCrashBuffer() {
		Thread crashBuffer = new Thread(new CrashBuffer(this));
		crashBuffer.start();
	}

	public void gameOver() {
		if (gameIsOver) {
			stopMainMusic();
		}
		if (okToStartEndMusic) {
			startGameOverMusic();
		}
		gameOver = new GameOver(200, 300);
	}

	private void startGameOverMusic() {
		okToStartEndMusic = false;
		Thread gameOverThread = new Thread(MusicFactory.getMusic(MusicFactory.GAME_OVER_MUSIC));
		gameOverThread.setName("game over");
		gameOverThread.start();
	}

	@SuppressWarnings("deprecation")
	private void stopMainMusic() {
		Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
		Iterator<Thread> iterator = threadSet.iterator();
		while (iterator.hasNext()) {
			Thread currentThread = iterator.next();
			
			if(currentThread.getName().equals("main music")){
				currentThread.stop();
				System.out.println("thread interrupted +++");
				gameIsOver = false;
				return;
			}else {
				System.out.println("thread not found...+");
			}
		}
	}

	public ArrayList<Bomb> getBombs() {
		return bombs;
	}

	public ArrayList<Collision> getCollisions() {
		return collisions;
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

		if (key == KeyEvent.VK_B) {
			fireBomb();
		}

		if (key == KeyEvent.VK_LEFT) {
			if(x > 10){
				dx = -2;	
			}
		}

		if (key == KeyEvent.VK_RIGHT) {
			if (x < 980) {
				dx = 2;
			}
		}

		//if up or down changed, then collision detection may need 
		//to be changed. As extra coordinates for detection go in increments of 5
		if (key == KeyEvent.VK_UP) {
			if (y > 20) {
				dy = -3;
			}
		}

		if (key == KeyEvent.VK_DOWN) {
			if (y < 640) {
				dy = 3;
			}
		}
		
	}

	private void fireBomb() {
		// position bomb leaves apache
		bombs.add(new Bomb((x + width) - 45, (y + height / 2) + 10));
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
			if (x < 825) {
				dx = 0;
			}else{
				dx = 0;
			}
		}

		if (key == KeyEvent.VK_UP) {
			dy = 0;
		}

		if (key == KeyEvent.VK_DOWN) {
			dy = 0;
		}
	}

	public class LifeIcon extends Sprite{

		public LifeIcon(int x, int y) {
			super(28, 12);
			initSprite();
		}
		
		private void initSprite() {
			loadImage("res/apacheLifeIcon.gif");
			setImageDimensions();
		}
	}
	
	public void tryToLand(){
		if(canLand()){
			hasLanded = true;
		}
	}
	
	private boolean canLand() {
		if(matchingXCoordinate() && matchingYCoordinate()){
			return true;
		}else{
			return false;	
		}
	}

	public void setHasLandingPad(boolean b){
		this.hasLandingPad = b;
	}
	
	private boolean matchingXCoordinate(){
		if(IntStream.of(landingXCoordinates).anyMatch(x -> x == this.getX())){
			return true;
		}else{
			return false;
		}
	}
	
	private boolean matchingYCoordinate(){
		if(IntStream.of(landingYCoordinates).anyMatch(y -> y == this.getY())){
			return true;
		}else{
			return false;
		}
	}

	public boolean getHasLanded() {
		return hasLanded;
	}

	public void moveOffScreen() {
		if(hasLanded){
			x -= 2;
		}
	}
}


