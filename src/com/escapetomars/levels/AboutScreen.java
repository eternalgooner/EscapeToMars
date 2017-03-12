package com.escapetomars.levels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.escapetomars.interfaces.Updateable;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class AboutScreen extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3537898323768497623L;
	private Updateable updateable;

	public AboutScreen(JFrame frame) {
		setBackground(Color.BLACK);
		setLayout(null);
		updateable = (Updateable) frame;
		
		JLabel lblBackToMain = new JLabel("Back to Main Menu");
		lblBackToMain.setBorder(new LineBorder(Color.WHITE, 1, true));
		lblBackToMain.setForeground(Color.WHITE);
		lblBackToMain.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				backToMain();
			}
		});
		lblBackToMain.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblBackToMain.setHorizontalAlignment(SwingConstants.CENTER);
		lblBackToMain.setBounds(387, 632, 232, 35);
		add(lblBackToMain);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(131, 43, 790, 578);
		add(scrollPane);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(new ImageIcon("res/about3.png"));
		scrollPane.setViewportView(lblNewLabel);
	}

	private void backToMain() {
		this.setVisible(false);
		updateable.update(2, 0, 0, "", 0);
	}
}
