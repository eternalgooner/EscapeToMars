package com.escapetomars.sprites;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Sprite {

	protected int x;
	protected int y;
	protected int width;
	protected int height;
	public boolean vis;
	protected Image image;

	public Sprite(int x, int y) {
		this.x = x;
		this.y = y;
		vis = true;
	}

	public void loadImage(String imageName) {
		ImageIcon ii = new ImageIcon(imageName);
		image = ii.getImage();
	}

	protected void setImageDimensions() {
		width = image.getWidth(null);
		height = image.getHeight(null);
	}
	
	protected void setImageDimensions(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public Image getImage() {
		return image;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isVisible() {
		return vis;
	}

	public void setVisible(Boolean visible) {
		vis = visible;
	}
}
