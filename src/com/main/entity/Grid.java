package com.main.entity;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.main.entity.Tile.OccupiedTile;

public class Grid {

	private final Tile[][] tiles;
	private final int row,col;
	
	private Set<Tile> matches;
	
	public Grid(final int row,final int col) {
		this.row = row;
		this.col = col;
		this.tiles = new Tile[row][col];
		
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				this.tiles[i][j] = new OccupiedTile(Candy.random(),i,j);
				
				//if(i == 2 && j == 2) this.tiles[i][j] = new EmptyTile(i,j);
			}
		}
		
		matches = new HashSet<>();
	}
	
	public Tile getTile(int row,int col) {
		return tiles[row][col];
	}
	
	private void findMatches() {
		ArrayList<Tile> match = new ArrayList<>();

		// Find all horizontal matches
		for(int i=0;i<row;i++) {
			int j=0;
			match.clear();
			while(j < 8) {
				Tile current = tiles[i][j];
				Candy candy = current.getCandy();
				
				if(match.size() == 0 || match.get(0).getCandy().equals(candy)) {
					match.add(current);
				}else if(!match.get(0).getCandy().getType().equals(candy.getType())) {
					match.clear();
					match.add(current);
				}
				
				if(match.size() >= 3) {
					matches.addAll(match);
				}
				
				j++;
			}
		}

		// Find all vertical matches
		for(int j=0;j<col;j++) {
			int i=0;
			match.clear();
			while(i < 8) {
				Tile current = tiles[i][j];
				Candy candy = current.getCandy();
				
				if(match.size() == 0 || match.get(0).getCandy().equals(candy)) {
					match.add(current);
				}else if(!match.get(0).getCandy().getType().equals(candy.getType())) {
					match.clear();
					match.add(current);
				}
				
				if(match.size() >= 3) {
					matches.addAll(match);
				}
				
				i++;
			}
		}
	}
	
	public void pop(Tile tile) {
		int row = tile.row;
		int col = tile.col;
		
		while(row > 0) {
			Tile above = tiles[row-1][col];
			tiles[row][col].candy = above.getCandy();
			above.candy = null;
			row--;
		}
		
		tiles[row][col].candy = Candy.random();
	}
	
	public void update(float dt) {
		findMatches();
		
		Match m = new Match(matches);
		ArrayList<Tile>[] sm = m.sort();
		
		for(int i=0;i<sm.length;i++) {
			//for(Tile tile : sm[i]) pop(tile);
		}
	}
	
	public void draw(Graphics2D g) {
		Tile t;
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				t = tiles[i][j];
					g.drawImage(t.getSprite(),j * 64,i * 64,64,64,null);
			}
		}
	}
	
}
