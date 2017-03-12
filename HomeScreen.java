package com.escapetomars.levels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import com.escapetomars.util.MusicFactory;
import com.escapetomars.util.PlayerScore;
import com.escapetomars.interfaces.Updateable;
import com.escapetomars.sprites.HomeBackground;
import com.escapetomars.sprites.InsertCoin;
import com.escapetomars.sprites.Title;
import com.escapetomars.util.StartBtnAnimation;


public class HomeScreen extends JPanel implements ActionListener {

	private final int DELAY = 10;
	private Timer timer;
	private Graphics2D g2d;
	private Updateable updateable;
	private HomeBackground background;
	private static final long serialVersionUID = 5479767419401617611L;
	private InsertCoin insertCoin;
	private List<PlayerScore> scores;
	private JLabel lblHighScore;
	private JLabel lblHighName;
	private JLabel lblCredits;
	private JLabel lblStartGame;
	private JLabel lblStartGame2;
	private JLabel lblSaveScores;
	private int credits = 0;
	private Title title;
	private boolean autoLoadScoresSelected = true;
	private boolean startBtnIsAnimated;
	private StartBtnAnimation startBtnAnimation;
	@SuppressWarnings("unused") //is used, but eclipse keeps showing warning message
	private boolean gameOverMusicIsPlaying = true;
	@SuppressWarnings("unused") //is used, but eclipse keeps showing warning message
	private boolean okToCheckIfGameOverMusicPlaying = true;
	private int gameSpeed;

	public HomeScreen(JFrame frame, int gameSpeed) {
		this.gameSpeed = gameSpeed;
		initDisplayArea(frame);
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public HomeScreen(JFrame frame) {
		initDisplayArea(frame);
	}
	
	public void setGameSpeed(int setGameSpeed){
		this.gameSpeed = setGameSpeed;
	}

	private void initDisplayArea(JFrame frame) {
		setLayout(null);
		setFocusable(true);
		setDoubleBuffered(true);
		updateable = (Updateable) frame;
		background = new HomeBackground(0, 0);
		title = new Title(100, 100);
		insertCoin = new InsertCoin(4, 10);
		scores = new ArrayList<>();
		title = new Title(400, 250);
		startBtnAnimation = null;

		JLabel lblLoadScores = new JLabel("Load Scores");
		lblLoadScores.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoadScores.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				loadScores();
			}
		});
		lblLoadScores.setBorder(null);
		lblLoadScores.setOpaque(false);
		lblLoadScores.setBackground(Color.BLACK);
		lblLoadScores.setForeground(Color.WHITE);
		lblLoadScores.setFont(new Font("Tahoma", Font.BOLD, 30));
		addAllFontChangeListeners(lblLoadScores, 1);
		lblLoadScores.setBounds(33, 403, 250, 71);
		add(lblLoadScores);

		lblStartGame = new JLabel("START GAME");
		lblStartGame.setHorizontalAlignment(SwingConstants.CENTER);
		lblStartGame.setBorder(null);
		lblStartGame.setOpaque(false);
		lblStartGame.setBackground(Color.BLACK);
		lblStartGame.setForeground(Color.GRAY);
		lblStartGame.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblStartGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				startGame();
			}
		});
		lblStartGame.setBounds(378, 572, 275, 50);
		addAllFontChangeListeners(lblStartGame, 0);
		
		lblStartGame2 = new JLabel("START GAME");
		lblStartGame2.setHorizontalAlignment(SwingConstants.CENTER);
		lblStartGame2.setBorder(null);
		lblStartGame2.setOpaque(false);
		lblStartGame2.setBackground(Color.BLACK);
		lblStartGame2.setForeground(Color.RED);
		lblStartGame2.setFont(new Font("Tahoma", Font.BOLD, 33));
		lblStartGame2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				startGame();
			}
		});
		lblStartGame2.setBounds(378, 572, 275, 50);
		addAllFontChangeListeners(lblStartGame2, 0);
		lblStartGame2.setVisible(false);
		add(lblStartGame2);
		add(lblStartGame);
		

		lblSaveScores = new JLabel("Save Scores");
		lblSaveScores.setHorizontalAlignment(SwingConstants.CENTER);
		lblSaveScores.setBorder(null);
		lblSaveScores.setOpaque(false);
		lblSaveScores.setBackground(Color.BLACK);
		lblSaveScores.setForeground(Color.WHITE);
		addAllFontChangeListeners(lblSaveScores, 1);
		lblSaveScores.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblSaveScores.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					saveScores();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		lblSaveScores.setBounds(33, 476, 250, 71);
		add(lblSaveScores);

		JLabel highScore = new JLabel("High Score:");
		highScore.setHorizontalAlignment(SwingConstants.RIGHT);
		add(highScore);
		highScore.setForeground(Color.WHITE);
		highScore.setBounds(597, 30, 151, 25);
		highScore.setFont(new Font("Tahoma", Font.BOLD, 24));

		lblHighScore = new JLabel("0");
		lblHighScore.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblHighScore);
		lblHighScore.setForeground(Color.WHITE);
		lblHighScore.setBounds(758, 30, 129, 25);
		lblHighScore.setFont(new Font("Tahoma", Font.BOLD, 24));

		lblHighName = new JLabel("");
		lblHighName.setForeground(Color.WHITE);
		lblHighName.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblHighName.setBounds(897, 20, 133, 35);
		add(lblHighName);
		timer = new Timer(DELAY, this);

		JLabel lblCoinInserted = new JLabel();
		lblCoinInserted.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				coinInserted();
			}
		});
		lblCoinInserted.setBounds(940, 555, 90, 86);
		add(lblCoinInserted);

		JLabel credits = new JLabel("Credits x");
		add(credits);
		credits.setForeground(Color.WHITE);
		credits.setBounds(944, 655, 120, 25);
		credits.setFont(new Font("Tahoma", Font.BOLD, 16));

		lblCredits = new JLabel("" + this.credits);
		add(lblCredits);
		lblCredits.setForeground(Color.WHITE);
		lblCredits.setBounds(1022, 655, 44, 25);
		lblCredits.setFont(new Font("Tahoma", Font.BOLD, 16));

		JLabel btnAbout = new JLabel("About");
		btnAbout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				displayAboutScreen();
			}
		});
		btnAbout.setHorizontalAlignment(SwingConstants.CENTER);
		btnAbout.setOpaque(false);
		btnAbout.setForeground(Color.WHITE);
		btnAbout.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnAbout.setBorder(null);
		btnAbout.setBackground(Color.BLACK);
		addAllFontChangeListeners(btnAbout, 1);
		btnAbout.setBounds(33, 614, 250, 71);
		add(btnAbout);
		
		JLabel lblOptions = new JLabel("Options");
		lblOptions.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				displayOptions(gameSpeed);
			}
		});
		lblOptions.setOpaque(false);
		addAllFontChangeListeners(lblOptions, 1);
		lblOptions.setHorizontalAlignment(SwingConstants.CENTER);
		lblOptions.setForeground(Color.WHITE);
		lblOptions.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblOptions.setBorder(null);
		lblOptions.setBackground(Color.BLACK);
		lblOptions.setBounds(33, 542, 250, 71);
		add(lblOptions);

		if (autoLoadScoresSelected) {
			autoLoadScores();
			updateScores();
		}

		timer.start();
	}

	private void displayOptions(int gameSpeed) {
		System.out.println("clicking display option in home screen ");
		updateable.update(7, 0, 0, "", 0, gameSpeed);
	}

	private void displayAboutScreen() {
		updateable.update(0, 0, 0, "", 0, gameSpeed);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		doDrawing(g);
		Toolkit.getDefaultToolkit().sync();
	}

	private void doDrawing(Graphics g) {
		g2d = (Graphics2D) g;
		g2d.drawImage(background.getImage(), background.getX(), background.getY(), this);
		g2d.drawImage(insertCoin.getImage(), insertCoin.getX(), insertCoin.getY(), this);
		g2d.drawImage(title.getImage(), title.getX(), title.getY(), this);
		if(startBtnAnimation != null) g2d.drawImage(startBtnAnimation.getImage(), startBtnAnimation.getX(), startBtnAnimation.getY(), this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (this.isVisible()) {
			if (gameOverMusicPresent()) {
				stopGameOverMusic();
			} 
		}
		repaint();
	}
	
	private boolean gameOverMusicPresent() {
		Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
		Iterator<Thread> iterator = threadSet.iterator();
		while (iterator.hasNext()) {
			Thread currentThread = iterator.next();
			if(currentThread.getName().equals("game over")){
				return true;
			}else {
				//System.out.println("GOM NOT PRESENT - should return true");
			}
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	private void stopGameOverMusic() {
		Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
		Iterator<Thread> iterator = threadSet.iterator();
		while (iterator.hasNext()) {
			Thread currentThread = iterator.next();
			
			if(currentThread.getName().equals("game over")){
				currentThread.stop();
				currentThread = null;
				//System.out.println("GAME OVER MUSIC FOUND AND STOPPED");
				playMusic();
				gameOverMusicIsPlaying = false;
				this.okToCheckIfGameOverMusicPlaying = false;
				break;
			}else {
				//System.out.println("thread not found...+");
			}
		}
	}
	
	private void playMusic() {		
		Thread prologueMusicThread = new Thread(MusicFactory.getMusic(MusicFactory.PROLOGUE_MUSIC));
		prologueMusicThread.setName("prologue music");
		prologueMusicThread.start();
	}

	private void coinInserted() {
		System.out.println("coin inserted");
		++credits;
		System.out.println("in coin inserted method - game speed is :" + gameSpeed);
		if (credits > 0){
			lblStartGame.setForeground(Color.WHITE);
			lblStartGame2.setVisible(true);
			if(!startBtnIsAnimated){
				startBtnAnimation();
				startBtnIsAnimated = true;
			}
		}
		lblCredits.setText("" + credits);
	}

	private void startBtnAnimation() {
		System.out.println("created new btn animation");
		startBtnAnimation = new StartBtnAnimation(0, 0);
		Thread thread = new Thread(startBtnAnimation);
		thread.start();
	}

	private void startGame() {
		if(credits > 0){
			this.setVisible(false);
			updateable.update(1, 0, 0, null, 0, gameSpeed);
		}
	}

	public void addScore(int score, String name) {
		scores.add(new PlayerScore(score, name));
	}

	public void updateScreen() {
		updateScores();
		updateCredits();
	}

	// decrement by 2 as coinInserted increments by one
	private void updateCredits() {
		credits -= 2;
		coinInserted();
		if (credits < 1)
			lblStartGame.setVisible(false);
	}

	private void updateScores() {
		if (!scores.isEmpty()) {
			Collections.sort(scores);
			lblHighScore.setText("" + scores.get(0).getScore());
			lblHighName.setText(scores.get(0).getName());
		}
	}

	private void saveScores() throws IOException {
		try {
			JFileChooser userFile = new JFileChooser();
			userFile.showSaveDialog(null);
			String fileName;
			try {
				fileName = userFile.getSelectedFile().toString();
				if (!fileName.endsWith(".ser"))
					fileName += ".ser";
				FileOutputStream fileOut = new FileOutputStream(fileName);
				ObjectOutputStream oos = new ObjectOutputStream(fileOut);
				oos.writeObject(scores);
				oos.close();
				giveUserChoiceToAutoLoadHighScores();
			} catch (NullPointerException npe) {
				System.out.println("no file selected");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void giveUserChoiceToAutoLoadHighScores() {
		int userChoice = JOptionPane.showConfirmDialog(this,
				"Would you like this file to auto load when opening the program in future?", null,
				JOptionPane.YES_NO_OPTION);
		switch (userChoice) {
		case 1:
			System.out.println("1 selected - no pressed");
			break;
		case -1:
			System.out.println("-1 - no selected");
			break;
		case 0:
			System.out.println("0 - yes selected");
			break;
		default:
			break;
		}
	}

	@SuppressWarnings("unchecked")
	private void loadScores() {
		try {
			JFileChooser userFile = new JFileChooser();
			userFile.showOpenDialog(null);
			try {
				FileInputStream fileIn = new FileInputStream(userFile.getSelectedFile());
				ObjectInputStream objectIn = new ObjectInputStream(fileIn);
				scores = (List<PlayerScore>) objectIn.readObject();
				objectIn.close();
				fileIn.close();
			} catch (NullPointerException npe) {
				System.out.println("no file selected");
			}
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
			return;
		}
		updateScores();
	}

	@SuppressWarnings("unchecked")
	private void autoLoadScores() {
		try {
			FileInputStream fileIn = new FileInputStream("C:\\Users\\User\\Documents\\marsScores.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			scores = (List<PlayerScore>) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
			return;
		}
		updateScores();
	}

	public List<PlayerScore> getScoreList() {
		return scores;
	}

	private void enlargeFont(JComponent component, int lblType) {
		//1 is normal sized component - Start game isn't
		if(lblType == 1){
			component.setFont(new Font("Tahoma", Font.BOLD, 36));
		}else{
			if(credits > 0)
			component.setFont(new Font("Tahoma", Font.BOLD, 37));
		}
	}

	private void shrinkFont(JComponent component, int lblType) {
		if(lblType == 1){
			component.setFont(new Font("Tahoma", Font.BOLD, 30));
		}else{
			if(credits > 0)
			component.setFont(new Font("Tahoma", Font.BOLD, 32));
		}
	}
	
	private void addAllFontChangeListeners(JComponent component, int lblType){
		component.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				enlargeFont(component, lblType);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				shrinkFont(component, lblType);
			}
		});
	}
}
