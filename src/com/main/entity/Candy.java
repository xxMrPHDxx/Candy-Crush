package com.main.entity;

import java.awt.image.BufferedImage;

import com.main.game.Game;

public class Candy {

	public enum Type{
		Circle(0,0),
		Pentagon(0,1),
		Triangle(0,2),
		Diamond(0,3);
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
		
		public boolean equals(Type other) {
			return (this.name() == other.name() && this.toString() == other.toString());
		}
		
		public String toString() {
			return this.name();
		}
	}
	
	private Type type;
	
	public Candy(Type type) {
		this.type = type;
	}
	
	public Type getType() {
		return type;
	}
	
	public BufferedImage getSprite() {
		return type.getSprite();
	}
	
	public static Candy random() {
		Type[] types = Type.values();
		return new Candy(types[(int)(Math.random() * types.length)]);
	}
	
	public static Candy copy(Candy c) {
		return new Candy(c.type);
	}
	
	public String toString() {
		return this.type.toString();
	}
	
}
