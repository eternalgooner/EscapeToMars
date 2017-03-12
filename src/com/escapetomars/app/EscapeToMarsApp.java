package com.escapetomars.app;

import java.awt.EventQueue;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JFrame;

import com.escapetomars.interfaces.Audible;
import com.escapetomars.interfaces.Updateable;
import com.escapetomars.levels.Level1;
import com.escapetomars.levels.AboutScreen;
import com.escapetomars.levels.FinalLevel;
import com.escapetomars.levels.HomeScreen;
import com.escapetomars.levels.Level2;
import com.escapetomars.levels.Level3;
import com.escapetomars.levels.Options;
import com.escapetomars.levels.PrologueScreen;
import com.escapetomars.util.MusicFactory;

public class EscapeToMarsApp extends JFrame implements Updateable {
	private HomeScreen homeScreen;
	private Level1 level1;
	private PrologueScreen prologueScreen;
	private Level2 level2;
	private Level3 level3;
	private FinalLevel finalLevel;
	private AboutScreen aboutScreen;
	private Audible prologueMusic;
	private Audible mainMusic;
	private Options options;
	private int gameSpeed = 6;

	private static final long serialVersionUID = 7087898557186535687L;

	public EscapeToMarsApp() {
		initUI();
	}

	private void initUI() {
		//start prologue music
		prologueMusic = MusicFactory.getMusic(MusicFactory.PROLOGUE_MUSIC);
		Thread prologueMusicThread = new Thread(prologueMusic);
		prologueMusicThread.start();
		
		System.out.println("JFrame init game speed is : " + gameSpeed);
		homeScreen = new HomeScreen(this, gameSpeed);
		add(homeScreen);

		setSize(1080, 720);
		setResizable(false);
		setTitle("Escape To Mars");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				EscapeToMarsApp app = new EscapeToMarsApp();
				app.setVisible(true);
			}
		});
	}

	// switch to decide which screen to go to
	@Override
	public void update(int screen, int score, int highScore, String name, int lives, int choice) {
		System.out.println(" in JFrame update method - user choice is: " + choice);
		switch (screen) {
		case 1:
			showPrologue(gameSpeed);
			break;
		case 2:
			gameSpeed = getGameSpeed(choice);
			homeScreen.setGameSpeed(gameSpeed);
			homeScreen.addScore(score, name);
			backToHome();
			break;
		case 3:
			startGame(gameSpeed);
			break;
		case 4:
			startLevel2(score, highScore, lives, gameSpeed);
			break;
		case 5:
			startLevel3(score, highScore, lives, gameSpeed);
			break;
		case 6:
			startFinalLevel(score, highScore, lives, gameSpeed);
			break;
		case 7:
			displayOptionsScreen(gameSpeed);
			break;
		default:
			showAboutScreen();
			break;
		}
	}
	
	private int getGameSpeed(int choice){
		switch (choice) {
		case 1:
			gameSpeed = 0;
			break;
		case 2:
			gameSpeed = 2;
			break;
		case 3:
			gameSpeed = 4;
			break;
		case 4:
			gameSpeed = 5;
			break;
		case 5:
			gameSpeed = 6;
			break;
		default:
			gameSpeed = 6;
			break;
		}
		return gameSpeed;
	}
	
	private void startFinalLevel(int playerScore, int highScore, int lives, int gameSpeed) {
		finalLevel = new FinalLevel(this, playerScore, highScore, lives, gameSpeed);
		add(finalLevel);
		finalLevel.grabFocus();
		level3.setVisible(false);
		finalLevel.setVisible(true);
	}

	private void startLevel2(int playerScore, int highScore, int lives, int gameSpeed) {
		level2 = new Level2(this, playerScore, highScore, lives, gameSpeed);
		add(level2);
		level2.grabFocus();
		level1.setVisible(false);
		level2.setVisible(true);
	}
	
	private void startLevel3(int playerScore, int highScore, int lives, int gameSpeed) {
		level3 = new Level3(this, playerScore, highScore, lives, gameSpeed);
		add(level3);
		level3.grabFocus();
		level1.setVisible(false);
		level3.setVisible(true);
	}

	private void backToHome() {
		System.out.println("back to home method in FRAME: main frame told that button pressed in game screen");
		System.out.println(gameSpeed);
		homeScreen.setVisible(true);
		homeScreen.updateScreen();
	}

	private void startGame(int gameSpeed) {
		System.out.println("in start game method in JFrame - game speed is :" + gameSpeed);
		//change music from prologue music to main game music
		prologueMusic.stopMusic();
		stopPrologueMusicThreads();
		mainMusic = MusicFactory.getMusic(MusicFactory.MAIN_MUSIC);
		Thread mainMusicThread = new Thread(mainMusic);
		mainMusicThread.setName("main music");
		mainMusicThread.start();

		System.out.println("start game method in FRAME: main frame told that button pressed in home screen");
		// use IF to pass high score to display on game screen - if none pass 0
		// ELSE pass high score
		if (homeScreen.getScoreList().isEmpty()) {
			level1 = new Level1(this, 0, gameSpeed);
			add(level1);
			level1.grabFocus();
		} else {
			level1 = new Level1(this, homeScreen.getScoreList().get(0).getScore(), gameSpeed);
			add(level1);
			level1.grabFocus();
		}
	}

	@SuppressWarnings("deprecation")
	private void stopPrologueMusicThreads() {
		Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
		Iterator<Thread> iterator = threadSet.iterator();
		while (iterator.hasNext()) {
			Thread currentThread = iterator.next();
			
			if(currentThread.getName().equals("prologue music")){
				currentThread.stop();
				System.out.println("thread stopped +++");
				return;
			}else {
				System.out.println("thread not found...+");
			}
		}
	}

	private void showPrologue(int gameSpeed) {
		System.out.println("in show prologue method & about to create new prologue screen + game speed is:" + gameSpeed);
		prologueScreen = new PrologueScreen(this, gameSpeed);
		add(prologueScreen);
		prologueScreen.grabFocus();
		homeScreen.setVisible(false);
		prologueScreen.setVisible(true);
	}
	
	private void showAboutScreen() {
		System.out.println("in show about screen method");
		aboutScreen = new AboutScreen(this);
		add(aboutScreen);
		aboutScreen.grabFocus();
		homeScreen.setVisible(false);
		aboutScreen.setVisible(true);
	}

	@Override
	public void update(int screen, int score, int highScore, String name, int lives) {
		switch (screen) {
		case 2:
			homeScreen.addScore(score, name);
			backToHome();
			break;
		case 7:
			displayOptionsScreen();
			break;
		default:
			showAboutScreen();
			break;
		}
	}

	private void displayOptionsScreen() {
		System.out.println("in options screen method");
		options = new Options(this);
		add(options);
		options.grabFocus();
		homeScreen.setVisible(false);
		options.setVisible(true);
	}
	
	//overloaded method taking 1 param
	private void displayOptionsScreen(int gameSpeed) {
		System.out.println("in overloaded options screen method with game speed");
		options = new Options(this, gameSpeed);
		add(options);
		options.grabFocus();
		homeScreen.setVisible(false);
		options.setVisible(true);
	}
}
