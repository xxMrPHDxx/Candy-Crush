package com.main.entity;

public abstract class Tile {

	protected Candy candy;
	protected int row,col;
	
	private Tile(Candy candy,int row,int col) {
		this.candy = candy;
		this.row = row;
		this.col = col;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public static Tile copy(Tile t) {
		return new OccupiedTile(t.getCandy(),t.getRow(),t.getCol());
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
		
	}
	
}
