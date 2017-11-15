package com.main.entity;

import java.awt.Image;
import java.awt.image.BufferedImage;

import com.main.game.Game;

public class Candy {

	public enum Type{
		Circle(0,0),
		Pentagon(0,1);
		protected final int row;
		protected final int col;
		
		private Type(final int row,final int col) {
			this.row = row;
			this.col = col;
		}
		
		public BufferedImage getSprite() {
			BufferedImage candies = (BufferedImage)(Game.res.get("candies"));
			return candies.getSubimage(32 * col,32 * row, 32,32);
		};
	}
	
	private Type type;
	
	public Candy(Type type) {
		this.type = type;
	}
	
	public BufferedImage getSprite() {
		return type.getSprite();
	}
	
	public static Candy random() {
		Type[] types = Type.values();
		return new Candy(types[(int)(Math.random() * types.length)]);
	}
	
}
