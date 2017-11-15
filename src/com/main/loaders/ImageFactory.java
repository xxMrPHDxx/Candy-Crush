package com.main.loaders;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class ImageFactory {
	
	private Map<String,BufferedImage> images;
	
	public ImageFactory() {
		images = new HashMap<String,BufferedImage>();
	}
	
	public void addImage(String key,String path) {
		try {
			File file = new File(path);
			BufferedImage image = ImageIO.read(file);
			images.put(key, image);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public BufferedImage getImage(String key) {
		return images.get(key);
	}
	
}
