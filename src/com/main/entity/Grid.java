package com.main.entity;

import java.awt.Graphics2D;

import com.main.entity.Tile.OccupiedTile;

public class Grid {

	private final Tile[][] tiles;
	private final int row,col;
	
	public Grid(final int row,final int col) {
		this.row = row;
		this.col = col;
		this.tiles = new Tile[10][10];
		
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				this.tiles[i][j] = new OccupiedTile(Candy.random(),i,j);
			}
		}
	}
	
	public Tile getTile(int row,int col) {
		return tiles[row][col];
	}
	
	public void swapTile(Tile tile1,Tile tile2) {
		final int row1 = tile1.getRow();
		final int row2 = tile2.getRow();
		final int col1 = tile1.getCol();
		final int col2 = tile2.getCol();
		
		final int drow = Math.abs(row1 - row2);
		final int dcol = Math.abs(col1 - col2);
		
		System.out.println(drow + "," + dcol);
		
		if((drow == 1 && col1 == col2) ||
				(dcol == 1 && row1 == row2)) {
			final Tile toSwap = Tile.copy(tile1);
			tiles[row1][col1] = tile2;
			tiles[row2][col2] = toSwap;
		}
	}
	
	public void draw(Graphics2D g) {
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				g.drawImage(tiles[i][j].getCandy().getSprite(),j * 64,i * 64,64,64,null);
			}
		}
	}
	
}
