package com.main.entity;

import java.awt.image.BufferedImage;

public abstract class Tile {

	private static final BufferedImage EMPTY_SPRITE = new BufferedImage(64,64,BufferedImage.TYPE_INT_RGB);
	
	protected Candy candy;
	protected int row,col;
	public final int size = 64;
	
	private Tile(Candy candy,int row,int col) {
		this.candy = candy;
		this.row = row;
		this.col = col;
	}
	
	public double distance(Tile other) {
		return Math.sqrt(Math.pow(this.row - other.row,2) + Math.pow(this.col - other.col,2));
	}
	
	public void setCandy(Candy candy) {
		this.candy = candy;
	}
	
	public void swap(Tile other) {
		Tile temp = Tile.copy(this);
		this.candy = other.candy;
		other.candy = temp.candy;
	}
	
	public void pop() {
		this.candy = null;
	}
	
	public boolean canSwap(Tile other) {
		if(this.candy.equals(other.candy)) return false;
		int dr = (int)Math.abs(row - other.row);
		int dc = (int)Math.abs(col - other.col);
		return (dr == 1 && dc == 0) || (dc == 1 && dr == 0);
	}
	
	public BufferedImage getSprite() {
		return (isEmpty()) ? EMPTY_SPRITE : this.candy.getSprite();
	}
	
	public static Tile copy(Tile t) {
		if(!t.isEmpty())
			return new OccupiedTile(t.candy,t.row,t.col);
		else return new EmptyTile(t.row,t.col);
	}

	public abstract boolean isEmpty();
	public abstract Candy getCandy();
	
	public static final class EmptyTile extends Tile {
		
		public EmptyTile(int row,int col) {
			super(null,row,col);
		}
		
		public boolean isEmpty() {
			return true;
		}
		
		public Candy getCandy() {
			return null;
		}
		
		public String toString() {
			return "Empty(" + row + "," + col + ")";
		}
		
	}
	
	public static final class OccupiedTile extends Tile {
		
		public OccupiedTile(Candy candy,int row,int col) {
			super(candy,row,col);
		}
		
		public boolean isEmpty() {
			return false;
		}
		
		public Candy getCandy() {
			return candy;
		}
		
		public String toString() {
			return (candy == null) ? "Nothing" : candy.toString() + "(" + row + "," + col + ")";
			//return "Tile(" + row + "," + col + ") : " + candy.toString();
		}
		
	}
	
}
