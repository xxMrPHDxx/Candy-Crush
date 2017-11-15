package com.main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class Res {
	
	public static Map<String,Object> res;
	
	public Res() {
		res = new HashMap<String,Object>();
	}
	
	public void loadImage(String key,String path) {
		try {
			BufferedImage image = ImageIO.read(new File(path));
			res.put(key, image);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Object get(String key) {
		return res.get(key);
	}
	
}
