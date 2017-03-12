package com.escapetomars.interfaces;

import java.awt.Image;

public interface Plantable extends Collideable{

	Image getImage();
	int getX();
	int getY();
	boolean isVisible();
	void move();
}
