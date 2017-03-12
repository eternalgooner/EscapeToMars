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
import com.escapetomars.sprites.Background;
import com.escapetomars.sprites.Collision;
import com.escapetomars.sprites.Crash;
import com.escapetomars.sprites.Digit1;
import com.escapetomars.sprites.Fireball;
import com.escapetomars.sprites.GameOver;
import com.escapetomars.sprites.LetterE;
import com.escapetomars.sprites.LetterL;
import com.escapetomars.sprites.LetterV;
import com.escapetomars.sprites.Mars;
import com.escapetomars.sprites.Shuttle;
import com.escapetomars.sprites.Sprite;
import com.escapetomars.util.CollisionDetector;
import com.escapetomars.util.JTextFieldLimit;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Rectangle;
import java.awt.Dimension;

public class FinalLevel extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = -6374139939537909650L;
	private final int DELAY;
	private Timer timer;
	private Shuttle shuttle;
	private Background background;
	private Graphics2D g2d;
	private long timeCounter = 0L;
	private CollisionDetector collisionDetector;
	public boolean soundIsOn = true;
	private boolean shuttleIsHit = false;
	private JLabel lblScore;
	private JLabel lblLives;
	private int playerScore;
	private int highScore;
	private Shuttle.LifeIcon lifeIcon;
	private JTextField txtEnterName;
	private JLabel lblEnterYourName;
	private JLabel lblHighScore;
	private JLabel lblSubmit;
	private String playerName;
	private Updateable updateable;
	private ArrayList<Letter> letters;
	private boolean startingLevel = true;
	private boolean levelIsRunning = true;
	private Mars mars;
	private JLabel lblYouMadeIt;
	private boolean gameCompleted = false;


	public FinalLevel(JFrame frame, int playerScore, int highScore, int lives, int gameSpeed) {
		shuttle = new Shuttle(400, 500);
		this.DELAY = gameSpeed;
		this.highScore = highScore;
		this.playerScore = playerScore;
		shuttle.setLives(lives);
		initDisplayArea(frame);
	}

	private void initDisplayArea(JFrame frame) {
		setLayout(null);
		addKeyListener(new TAdapter());
		setFocusable(true);
		setDoubleBuffered(true);
		updateable = (Updateable) frame;
		mars = new Mars(50, -1000);

		// pass 4 for final level image
		background = new Background(0, -3_000, 4);

		// set size of fireball - level 3 so 3 passed to collision detector
		collisionDetector = new CollisionDetector(3);
		lifeIcon = shuttle.new LifeIcon(50, 50);
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

		lblLives = new JLabel("X " + shuttle.getLives());
		lblLives.setForeground(Color.WHITE);
		lblLives.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLives.setBounds(87, 21, 46, 26);
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
		
		lblYouMadeIt = new JLabel("YOU MADE IT!!! \r\n\r\nWELL DONE....NOW GO AND PROCREATE");
		lblYouMadeIt.setVisible(false);
		lblYouMadeIt.setMaximumSize(new Dimension(100, 20));
		lblYouMadeIt.setBounds(new Rectangle(0, 0, 200, 0));
		lblYouMadeIt.setForeground(Color.WHITE);
		lblYouMadeIt.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblYouMadeIt.setHorizontalTextPosition(SwingConstants.CENTER);
		lblYouMadeIt.setHorizontalAlignment(SwingConstants.CENTER);
		lblYouMadeIt.setBounds(117, 212, 840, 43);
		add(lblYouMadeIt);
		lblSubmit.setVisible(false);

		timer.start();
	}

	private void initLetters() {
		letters.add(new LetterL(1400, 302));
		letters.add(new LetterE(1700, 300));
		letters.add(new LetterV(2000, 300));
		letters.add(new LetterE(2300, 300));
		letters.add(new LetterL(2600, 302));
		letters.add(new Digit1(2915, 303, 3));
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		doDrawing(g);
		Toolkit.getDefaultToolkit().sync();
	}

	private void doDrawing(Graphics g) {
		if (levelIsRunning) {
			g2d = (Graphics2D) g;
			g2d.drawImage(background.getImage(), background.getX(), background.getY(), this);
			g2d.drawImage(mars.getImage(), mars.getX(), mars.getY(), this);
			g2d.drawImage(shuttle.getImage(), shuttle.getX(), shuttle.getY(), this);
			g2d.drawImage(lifeIcon.getImage(), lifeIcon.getX(), lifeIcon.getY(), this);

			if (startingLevel) {
				for (Letter letter : letters) {
					g2d.drawImage(letter.getImage(), letter.getX(), letter.getY(), this);
				}
			}

			ArrayList<Collideable> fireballs = background.getFireballs();
			for (Collideable fireball : fireballs) {
				g2d.drawImage(((Sprite) fireball).getImage(), fireball.getX(), fireball.getY(), this);
			}

			//put back in for game
			ArrayList<Collision> collisions = shuttle.getCollisions();
			for (Collision collision : collisions) {
				g2d.drawImage(collision.getImage(), collision.getX() - 60, collision.getY() - 30, this);
			}

			GameOver gameOver = shuttle.getGameOver();
			if (gameOver != null) {
				shuttle.gameOver();
				g2d.drawImage(gameOver.getImage(), gameOver.getX(), gameOver.getY(), this);
			}


			ArrayList<Crash> crashs = shuttle.getCrashs();
			if (!shuttle.getCrashs().isEmpty()) {
				g2d.drawImage(crashs.get(0).getImage(), crashs.get(0).getX(), crashs.get(0).getY(), this);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (startingLevel){
			updateLetters();
		}
		updateBackground();
		
		//interval for increasing mars size
		if(playerScore > 17_000){
			updateMars();
		}
		
		updateShuttle();
		
		// frequency of fireballs
		updateFireBalls();
		if (playerScore < 17_000) {
			if (timeCounter % 50 == 0) {
				background.fireFireball();
			} 
		}
		++timeCounter;
	
		if (!shuttleIsHit){
			checkForCrash();
		}
		// put back in for game
		updateCollisions();
		updateLives();

		updateCrashSite();
		updateScore();
		
		//change score for end game
		if (playerScore == 20_000) {
			gameCompleted();
		}
		
		if (shuttle.getLives() > 0) {
			if (!gameCompleted) {
				++playerScore;
			}
		} else {
			getPlayerName();
		}
		repaint();
	}

	private void gameCompleted() {
		gameCompleted = true;
		int lifeBonus = shuttle.getLives() * 100;
		playerScore += lifeBonus;
		lblYouMadeIt.setVisible(true);
		getPlayerName();
	}

	private void updateMars() {
		mars.moveCloser();
	}

	private void updateLetters() {
		animateLetter(12_265, 12_450, 12_529, 12_530, 0);
		animateLetter(12_282, 12_460, 12_539, 12_540, 1);
		animateLetter(12_299, 12_470, 12_549, 12_550, 2);
		animateLetter(12_316, 12_480, 12_559, 12_560, 3);
		animateLetter(12_333, 12_490, 12_569, 12_570, 4);
		animateLetter(12_350, 12_500, 12_579, 12_580, 5);
	}

	private void animateLetter(int start, int pause, int restart, int stop, int letter) {
		if (playerScore < start) {
			letters.get(letter).move();
		} else if (playerScore < pause) {

		} else if (playerScore < restart) {
			letters.get(letter).move();
		} else if (playerScore > stop) {
			startingLevel = false;
		}
	}

	private void getPlayerName() {
		lblEnterYourName.setVisible(true);
		txtEnterName.setVisible(true);
		txtEnterName.requestFocusInWindow();
		lblSubmit.setVisible(true);
	}

	public void setPlayerName() {
		playerName = txtEnterName.getText();
		System.out.println(playerName);
		levelIsRunning = false;
		this.setVisible(false);
		timer.stop();
		updateable.update(2, playerScore, highScore, playerName, 0);
	}

	private void updateLives() {
		lblLives.setText("X " + shuttle.getLives());
	}

	private void updateScore() {
		lblScore.setText(playerScore + "");
	}

	private void updateCrashSite() {
		if (!shuttle.getCrashs().isEmpty()) {
			ArrayList<Crash> crashs = shuttle.getCrashs();
			crashs.get(0).move(1);
		}
	}

	private void checkForCrash() {
		int[] apachePosition = { shuttle.getX(), shuttle.getY() };

		if (collisionDetector.detectsCollision(apachePosition, background.getFireballs())) {
			shuttle.showDamage();
			shuttle.decrementLife();
			invincibleShuttle();
		}
	}

	// method to allow a 3 second buffer after crash
	private void invincibleShuttle() {
		shuttleIsHit = true;
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(3000);
					shuttleIsHit = false;
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

	private void updateBackground() {
		background.move();
	}

	// - - need to fix for collisions on final level
	private void updateCollisions() {
		ArrayList<Collision> collisions = shuttle.getCollisions();

		for (int i = 0; i < collisions.size(); i++) {
			Collision collision = collisions.get(i);

			// send apache coordinates so smoke stays on apache
			if (collision.isVisible()) {
				collision.move(shuttle.getX(), shuttle.getY());
			} else {
				collisions.remove(i);
			}
		}
	}


	private void updateShuttle() {
		shuttle.move();
	}

	private class TAdapter extends KeyAdapter {
		@Override
		public void keyReleased(KeyEvent e) {
			shuttle.keyReleased(e);
		}

		@Override
		public void keyPressed(KeyEvent e) {
			shuttle.keyPressed(e);
		}
	}

	public void addAllFontChangeListeners(JComponent component, int lblType) {
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

