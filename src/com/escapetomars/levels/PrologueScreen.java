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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import com.escapetomars.interfaces.Updateable;
import com.escapetomars.sprites.Prologue;

public class PrologueScreen extends JPanel implements ActionListener {

	private static final long serialVersionUID = -4306416114199953551L;
	private Timer timer;
	private Graphics2D g2d;
	private Prologue prologue;
	private JLabel lblStartGame;
	private Updateable updateable;
	private int gameSpeed;

	public PrologueScreen(JFrame frame, int gameSpeed) {
		updateable = (Updateable) frame;
		this.gameSpeed = gameSpeed;
		System.out.println("Prologue constructor - game speed is:" + gameSpeed);
		initDisplayArea();
	}

	private void initDisplayArea() {
		setLayout(null);
		setFocusable(true);
		setDoubleBuffered(true);
		setBackground(Color.BLACK);
		prologue = new Prologue(0, 0);
		timer = new Timer(10, this);
		
		lblStartGame = new JLabel("SKIP");
		lblStartGame.setHorizontalAlignment(SwingConstants.CENTER);
		lblStartGame.setBorder(null);
		lblStartGame.setOpaque(false);
		lblStartGame.setBackground(Color.BLACK);
		lblStartGame.setForeground(Color.RED);
		lblStartGame.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblStartGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				goToGameScreen();
			}
		});
		lblStartGame.setBounds(866, 622, 275, 50);
		add(lblStartGame);
		timer.start();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		doDrawing(g);
		Toolkit.getDefaultToolkit().sync();
	}

	private void doDrawing(Graphics g) {
		g2d = (Graphics2D) g;
		g2d.drawImage(prologue.getImage(), prologue.getX(), prologue.getY(), this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		prologue.move();
		repaint();
	}
	
	private void goToGameScreen() {
		this.setVisible(false);
		updateable.update(3, 0, 0, "", 0, gameSpeed);
	}
}

