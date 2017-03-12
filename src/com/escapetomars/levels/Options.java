package com.escapetomars.levels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import com.escapetomars.interfaces.Updateable;

import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class Options extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7360584534632009327L;
	private Updateable updateable;
	private JSlider slider;
	
	public Options(JFrame frame) {
		setBackground(Color.BLACK);
		updateable = (Updateable)frame;
		setLayout(null);
		
		slider = new JSlider();
		slider.setBackground(Color.BLACK);
		slider.setFont(new Font("Tahoma", Font.PLAIN, 25));
		slider.setForeground(Color.WHITE);
		slider.setToolTipText("adjust the game speed");
		slider.setMinimum(1);
		slider.setMaximum(5);
		slider.setBounds(352, 184, 301, 79);
		add(slider);
		
		JLabel lblBack = new JLabel("Back");
		lblBack.setBorder(new LineBorder(Color.WHITE, 1, true));
		lblBack.setForeground(Color.WHITE);
		lblBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				backToMain();
			}
		});
		lblBack.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblBack.setHorizontalAlignment(SwingConstants.CENTER);
		lblBack.setBounds(352, 438, 301, 51);
		add(lblBack);
		
		JLabel lblGameSpeed = new JLabel("Game speed");
		lblGameSpeed.setForeground(Color.WHITE);
		lblGameSpeed.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblGameSpeed.setHorizontalAlignment(SwingConstants.CENTER);
		lblGameSpeed.setBounds(352, 132, 301, 40);
		add(lblGameSpeed);
		
		JLabel label = new JLabel("1");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.BOLD, 24));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(349, 274, 30, 26);
		add(label);
		
		JLabel label_1 = new JLabel("2");
		label_1.setForeground(Color.WHITE);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 24));
		label_1.setBounds(415, 274, 30, 26);
		add(label_1);
		
		JLabel label_2 = new JLabel("3");
		label_2.setForeground(Color.WHITE);
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 24));
		label_2.setBounds(487, 274, 30, 26);
		add(label_2);
		
		JLabel label_3 = new JLabel("4");
		label_3.setForeground(Color.WHITE);
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("Tahoma", Font.BOLD, 24));
		label_3.setBounds(557, 274, 30, 26);
		add(label_3);
		
		JLabel label_4 = new JLabel("5");
		label_4.setForeground(Color.WHITE);
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("Tahoma", Font.BOLD, 24));
		label_4.setBounds(623, 274, 30, 26);
		add(label_4);
		
		JLabel lblQuicker = new JLabel("quicker <--");
		lblQuicker.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuicker.setForeground(Color.WHITE);
		lblQuicker.setFont(new Font("Tahoma", Font.ITALIC, 24));
		lblQuicker.setBounds(201, 203, 141, 34);
		add(lblQuicker);
		
		JLabel lblSlower = new JLabel("--> slower");
		lblSlower.setHorizontalAlignment(SwingConstants.CENTER);
		lblSlower.setForeground(Color.WHITE);
		lblSlower.setFont(new Font("Tahoma", Font.ITALIC, 24));
		lblSlower.setBounds(663, 203, 141, 40);
		add(lblSlower);
	}
	
	public Options(JFrame frame, int gameSpeed) {
		int sliderVal = 0;
		switch (gameSpeed) {
		case 0:
			sliderVal = 1;
			break;
		case 2:
			sliderVal = 2;
			break;
		case 4:
			sliderVal = 3;
			break;
		case 5:
			sliderVal = 4;
			break;
		case 6:
			sliderVal = 5;
			break;

		default:
			break;
		}
		
		setBackground(Color.BLACK);
		updateable = (Updateable)frame;
		setLayout(null);
		
		slider = new JSlider();
		slider.setBackground(Color.BLACK);
		slider.setFont(new Font("Tahoma", Font.PLAIN, 25));
		slider.setForeground(Color.WHITE);
		slider.setToolTipText("adjust the game speed");
		slider.setMinimum(1);
		slider.setMaximum(5);
		slider.setBounds(352, 184, 301, 79);
		add(slider);
		slider.setValue(sliderVal);
		
		JLabel lblBack = new JLabel("Back");
		lblBack.setBorder(new LineBorder(Color.WHITE, 1, true));
		lblBack.setForeground(Color.WHITE);
		lblBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				backToMain();
			}
		});
		lblBack.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblBack.setHorizontalAlignment(SwingConstants.CENTER);
		lblBack.setBounds(352, 438, 301, 51);
		add(lblBack);
		
		JLabel lblGameSpeed = new JLabel("Game speed");
		lblGameSpeed.setForeground(Color.WHITE);
		lblGameSpeed.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblGameSpeed.setHorizontalAlignment(SwingConstants.CENTER);
		lblGameSpeed.setBounds(352, 132, 301, 40);
		add(lblGameSpeed);
		
		JLabel label = new JLabel("1");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.BOLD, 24));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(349, 274, 30, 26);
		add(label);
		
		JLabel label_1 = new JLabel("2");
		label_1.setForeground(Color.WHITE);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 24));
		label_1.setBounds(415, 274, 30, 26);
		add(label_1);
		
		JLabel label_2 = new JLabel("3");
		label_2.setForeground(Color.WHITE);
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 24));
		label_2.setBounds(487, 274, 30, 26);
		add(label_2);
		
		JLabel label_3 = new JLabel("4");
		label_3.setForeground(Color.WHITE);
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("Tahoma", Font.BOLD, 24));
		label_3.setBounds(557, 274, 30, 26);
		add(label_3);
		
		JLabel label_4 = new JLabel("5");
		label_4.setForeground(Color.WHITE);
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("Tahoma", Font.BOLD, 24));
		label_4.setBounds(623, 274, 30, 26);
		add(label_4);
		
		JLabel lblQuicker = new JLabel("quicker <--");
		lblQuicker.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuicker.setForeground(Color.WHITE);
		lblQuicker.setFont(new Font("Tahoma", Font.ITALIC, 24));
		lblQuicker.setBounds(201, 203, 141, 34);
		add(lblQuicker);
		
		JLabel lblSlower = new JLabel("--> slower");
		lblSlower.setHorizontalAlignment(SwingConstants.CENTER);
		lblSlower.setForeground(Color.WHITE);
		lblSlower.setFont(new Font("Tahoma", Font.ITALIC, 24));
		lblSlower.setBounds(663, 203, 141, 40);
		add(lblSlower);
	}


	private void backToMain() {
		System.out.println("number from slider is: " + slider.getValue());
		updateable.update(2, 0, 0, "", 0, slider.getValue());
		this.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
