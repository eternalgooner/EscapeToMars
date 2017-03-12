package com.escapetomars.levels;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import com.escapetomars.interfaces.Collideable;
import com.escapetomars.interfaces.Letter;
import com.escapetomars.interfaces.Updateable;
import com.escapetomars.sprites.Apache;
import com.escapetomars.sprites.Background;
import com.escapetomars.sprites.Bomb;
import com.escapetomars.sprites.Collision;
import com.escapetomars.sprites.Crash;
import com.escapetomars.sprites.Digit1;
import com.escapetomars.sprites.Fireball;
import com.escapetomars.sprites.GameOver;
import com.escapetomars.sprites.LetterE;
import com.escapetomars.sprites.LetterL;
import com.escapetomars.sprites.LetterV;
import com.escapetomars.sprites.Lightning;
import com.escapetomars.sprites.Sprite;
import com.escapetomars.sprites.Apache.LifeIcon;
import com.escapetomars.util.CollisionDetector;
import com.escapetomars.util.JTextFieldLimit;
import com.escapetomars.util.SoundOn;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;

public class Level1 extends JPanel implements ActionListener {

	private static final long serialVersionUID = -6133879969482494060L;
	private final int APACHE_X = 140;
	private final int APACHE_Y = 190;
	private final int DELAY;
	private Timer timer;
	private Apache apache;
	private Background background;
	private Graphics2D g2d;
	private long timeCounter = 0L;
	private CollisionDetector collisionDetector;
	private SoundOn soundSettings;
	public boolean soundIsOn = true;
	private boolean apacheIsHit = false;
	private JLabel lblScore;
	private JLabel lblLives;
	private int playerScore = 0;
	private int highScore = 0;
	private LifeIcon lifeIcon;
	private JTextField txtEnterName;
	private JLabel lblEnterYourName;
	private JLabel lblHighScore;
	private JLabel lblSubmit;
	private String playerName;
	private Updateable updateable;
	private ArrayList<Letter> letters;
	private boolean startingLevel = true;
	private boolean levelIsRunning = true;

	public Level1(JFrame frame, int highScore, int gameSpeed) {
		System.out.println("game speed is :" + gameSpeed);
		this.DELAY = gameSpeed;
		this.highScore = highScore;
		initDisplayArea(frame);
	}

	private void initDisplayArea(JFrame frame) {
		setLayout(null);
		addKeyListener(new TAdapter());
		setFocusable(true);
		setDoubleBuffered(true);
		updateable = (Updateable) frame;
		background = new Background(0, 0, 1);
		apache = new Apache(APACHE_X, APACHE_Y);
		//set size of fireball - level 1 so 1 passed to collision detector
		collisionDetector = new CollisionDetector(1);
		soundSettings = new SoundOn(800, 550);
		lifeIcon = apache.new LifeIcon(50, 50);
		letters = new ArrayList<Letter>();
		initLetters();
		
		timer = new Timer(DELAY, this);
		
		lblScore = new JLabel("100");
		lblScore.setForeground(Color.WHITE);
		lblScore.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblScore.setBounds(798, 11, 60, 26);
		add(lblScore);
		
		JLabel score = new JLabel("Score:");
		score.setForeground(Color.WHITE);
		score.setFont(new Font("Tahoma", Font.BOLD, 13));
		score.setHorizontalAlignment(SwingConstants.RIGHT);
		score.setBounds(720, 11, 68, 26);
		add(score);
		
		lblHighScore = new JLabel("" + highScore);
		lblHighScore.setForeground(Color.WHITE);
		lblHighScore.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblHighScore.setBounds(991, 11, 79, 26);
		add(lblHighScore);
		
		JLabel HighScore = new JLabel("High Score:");
		HighScore.setHorizontalAlignment(SwingConstants.RIGHT);
		HighScore.setForeground(Color.WHITE);
		HighScore.setFont(new Font("Tahoma", Font.BOLD, 13));
		HighScore.setBounds(897, 11, 84, 26);
		add(HighScore);
		
		lblLives = new JLabel("X " + apache.getLives());
		lblLives.setForeground(Color.WHITE);
		lblLives.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLives.setBounds(97, 11, 51, 26);
		add(lblLives);
		
		txtEnterName = new JTextField();
		txtEnterName.setForeground(Color.WHITE);
		txtEnterName.setHorizontalAlignment(SwingConstants.CENTER);
		txtEnterName.setBorder(null);
		txtEnterName.setOpaque(false);
		txtEnterName.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtEnterName.setBounds(501, 315, 86, 26);
		add(txtEnterName);
		txtEnterName.setDocument(new JTextFieldLimit(7));
		txtEnterName.setVisible(false);
		
		lblEnterYourName = new JLabel("Enter Your Name");
		lblEnterYourName.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterYourName.setForeground(Color.WHITE);
		lblEnterYourName.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEnterYourName.setBounds(476, 278, 139, 26);
		add(lblEnterYourName);
		lblEnterYourName.setVisible(false);
		
		lblSubmit = new JLabel("SUBMIT");
		lblSubmit.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setPlayerName();
			}
		});
		lblSubmit.setBorder(null);
		lblSubmit.setOpaque(false);
		lblSubmit.setBackground(Color.BLACK);
		lblSubmit.setForeground(Color.WHITE);
		addAllFontChangeListeners(lblSubmit, 1);
		lblSubmit.setBounds(501, 354, 86, 23);
		add(lblSubmit);
		lblSubmit.setVisible(false);
		
		timer.start();
	}

	private void initLetters() {
		letters.add(new LetterL(1400, 302));
		letters.add(new LetterE(1700, 300));
		letters.add(new LetterV(2000, 300));
		letters.add(new LetterE(2300, 300));
		letters.add(new LetterL(2600, 302));
		letters.add(new Digit1(2915, 303, 1));
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		doDrawing(g);
		Toolkit.getDefaultToolkit().sync();
	}

	private void doDrawing(Graphics g) {
		if(levelIsRunning){
			g2d = (Graphics2D) g;
			g2d.drawImage(background.getImage(), background.getX(), background.getY(), this);
			g2d.drawImage(apache.getImage(), apache.getX(), apache.getY(), this);
			g2d.drawImage(lifeIcon.getImage(), lifeIcon.getX(), lifeIcon.getY(), this);
			
			if(startingLevel){
				for (Letter letter : letters) {
					g2d.drawImage(letter.getImage(), letter.getX(), letter.getY(), this);
				}
			}
			
			ArrayList<Bomb> bombs = apache.getBombs();
			for (Bomb bomb : bombs) {
				g2d.drawImage(bomb.getImage(), bomb.getX(), bomb.getY(), this);
			}
	
			ArrayList<Collideable> fireballs = background.getFireballs();
			for (Collideable fireball : fireballs) {
				g2d.drawImage(((Sprite) fireball).getImage(), fireball.getX(), fireball.getY(), this);
			}
	
			ArrayList<Collideable> lightnings = background.getLightnings();
			for (Collideable lightning : lightnings) {
				g2d.drawImage(((Sprite) lightning).getImage(), lightning.getX(), lightning.getY(), this);
			}
			
			ArrayList<Collideable> trees = background.getTrees();
			for (Collideable tree1 : trees) {
				g2d.drawImage(((Sprite) tree1).getImage(), tree1.getX(), tree1.getY(), this);
			}
			
			ArrayList<Collision> collisions = apache.getCollisions();
			for (Collision collision : collisions) {
				g2d.drawImage(collision.getImage(), collision.getX() - 60, collision.getY() - 30, this);
			}
			
			GameOver gameOver = apache.getGameOver();
			if(gameOver != null){
				apache.gameOver();
				g2d.drawImage(gameOver.getImage(), gameOver.getX(), gameOver.getY(), this);
			}	
			
			ArrayList<Crash> crashs = apache.getCrashs();
			if(!apache.getCrashs().isEmpty()){
				g2d.drawImage(crashs.get(0).getImage(), crashs.get(0).getX(), crashs.get(0).getY(), this);
			}	
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		updateBackground();
		
		if(startingLevel) updateLetters();
		
		updateApache();
		updateBombs();
		updateFireBalls();
		
		++timeCounter;
		// frequency of fireballs
		if (timeCounter % 50 == 0)
			background.fireFireball();

		// frequency of lightning
		if (timeCounter % 1000 == 0) {
			background.fireLightning(soundIsOn);
		}
		
		// frequency of trees
		if (timeCounter % 100 == 0) {
			background.newTree();
		}
		
		updateTrees();
		updateLightnings();
		if(!apacheIsHit) checkForCrash();
		updateCollisions();
		updateLives();
		updateCrashSite();
		updateScore();
		updateSoundSymbol();
		
		checkForNewLevel();

		if (apache.getLives() > 0){
			 ++playerScore;	
		}else{
			getPlayerName();
		}
		repaint();
	}
	
	private void checkForNewLevel() {
		//for game use 10_000
		if(playerScore == 10_000){
			this.setVisible(false);
			updateable.update(4, playerScore, highScore, "", apache.getLives(), DELAY);
		}
	}

	private void updateLetters() {
		animateLetter(65, 250, 329, 330, 0);
		animateLetter(82, 260, 339, 340, 1);
		animateLetter(99, 270, 349, 350, 2);
		animateLetter(116, 280, 359, 360, 3);
		animateLetter(133, 290, 369, 370, 4);
		animateLetter(150, 300, 379, 380, 5);
	}

	private void animateLetter(int start, int pause, int restart, int stop, int letter) {
		if (playerScore < start ) {
				letters.get(letter).move();
		}else if(playerScore < pause){
			
		}else if(playerScore < restart){
				letters.get(letter).move();
		}else if(playerScore > stop){
			startingLevel = false;
		}
	}

	private void updateTrees() {
		ArrayList<Collideable> trees = background.getTrees();
	
		for (int i = 0; i < trees.size(); i++) {
			Collideable tree1 = trees.get(i);
	
			if (tree1.isVisible()) {
				tree1.move();
			} else {
				trees.remove(i);
			}
		}
	}

	private void getPlayerName() {
		lblEnterYourName.setVisible(true);
		txtEnterName.setVisible(true);
		txtEnterName.requestFocusInWindow();
		lblSubmit.setVisible(true);
	}
	
	public void setPlayerName(){
		playerName = txtEnterName.getText();
		System.out.println(playerName);
		levelIsRunning = false;
		this.setVisible(false);
		timer.stop();
		updateable.update(2, playerScore, highScore, playerName, 0);
	}

	private void updateLives() {
		lblLives.setText("X " + apache.getLives());
	}

	private void updateScore() {
		lblScore.setText(playerScore+"");
	}

	private void updateCrashSite() {
		if(!apache.getCrashs().isEmpty()){
			ArrayList<Crash> crashs = apache.getCrashs();
			crashs.get(0).move(1);
		}		
	}

	private void updateSoundSymbol() {
		if(!soundSettings.keepDisplayingSymbol()){
			soundSettings.vis = false;
			soundIsOn = false;
		};
	}

	private void checkForCrash() {
		int[] apachePosition = { apache.getX(), apache.getY() };
		
		if (collisionDetector.detectsCollision(apachePosition, background.getFireballs())) {
			apache.showDamage();
			apache.decrementLife();
			invincibleApache();
		}
		
		if (collisionDetector.detectsCollision(apachePosition, background.getTrees())) {
			apache.showDamage();
			apache.decrementLife();
			invincibleApache();
		}
		
		
		if (collisionDetector.detectsCollision(apachePosition, background.getLightnings())) {
			apache.showDamage();
			apache.decrementLife();
			invincibleApache();
		}
	}

	//method to allow a 3 second buffer after crash
	private void invincibleApache() {
		apacheIsHit = true;
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(3000);
					apacheIsHit = false;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		thread.start();
	}

	private void updateFireBalls() {
		ArrayList<Collideable> fireballs = background.getFireballs();

		for (int i = 0; i < fireballs.size(); i++) {
			Fireball fireball = (Fireball) fireballs.get(i);

			if (fireball.isVisible()) {
				fireball.move();
			} else {
				fireballs.remove(i);
			}
		}
	}

	private void updateBombs() {
		ArrayList<Bomb> bombs = apache.getBombs();

		for (int i = 0; i < bombs.size(); i++) {
			Bomb b = (Bomb) bombs.get(i);

			if (b.isVisible()) {
				b.move();
			} else {
				bombs.remove(i);
			}
		}
	}

	private void updateLightnings() {
		ArrayList<Collideable> lightnings = background.getLightnings();

		for (int i = 0; i < lightnings.size(); i++) {
			Lightning lightning = (Lightning) lightnings.get(i);

			if (lightning.isVisible()) {
				lightning.move();
			} else {
				lightnings.remove(i);
			}
		}
	}

	private void updateBackground() {
		background.move();
	}

	private void updateCollisions() {
		ArrayList<Collision> collisions = apache.getCollisions();

		for (int i = 0; i < collisions.size(); i++) {
			Collision collision = collisions.get(i);

			// send apache coordinates so smoke stays on apache
			if (collision.isVisible()) {
				collision.move(apache.getX(), apache.getY());
			} else {
				collisions.remove(i);
			}
		}
	}

	private void updateApache() {
		apache.move();
	}

	private class TAdapter extends KeyAdapter {
		@Override
		public void keyReleased(KeyEvent e) {
			apache.keyReleased(e);
		}

		@Override
		public void keyPressed(KeyEvent e) {
			apache.keyPressed(e);
			soundSettings.keyPressed(e);
		}
	}
	
	public void addAllFontChangeListeners(JComponent component, int lblType){
		component.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				enlargeFont(component);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				shrinkFont(component);
			}
		});
	}
	
	public void enlargeFont(JComponent component) {
			component.setFont(new Font("Tahoma", Font.BOLD, 20));
	}
	
	public void shrinkFont(JComponent component) {
			component.setFont(new Font("Tahoma", Font.BOLD, 14));
	}
}
