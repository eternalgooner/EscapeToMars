package com.escapetomars.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BufferedImageLoader {

	public BufferedImage loadImage(String pathToSpriteSheet) throws IOException{
		//URL url = this.getClass().getResource(pathToSpriteSheet);
		File file = new File(pathToSpriteSheet);
		//BufferedImage image = ImageIO.read(url);
		BufferedImage image = ImageIO.read(file);
		return image;
	}
}
