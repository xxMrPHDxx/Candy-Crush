package com.main.entity;

public abstract class Tile {

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
	
	public boolean canSwap(Tile other) {
		int dr = Math.abs(row - other.row);
		int dc = Math.abs(col - other.col);
		return (dr == 1 && dc == 0) || (dc == 1 && dr == 0);
	}
	
	public void swap(Tile other) {
		Tile temp = Tile.copy(this);
		this.row = other.row;
		this.col = other.col;
		this.candy = other.candy;
		other.row = temp.row;
		other.col = temp.col;
		other.candy = temp.candy;
	}
	
	public void pop() {
		this.candy = null;
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
			return candy.toString() + "(" + row + "," + col + ")";
			//return "Tile(" + row + "," + col + ") : " + candy.toString();
		}
		
	}
	
}
