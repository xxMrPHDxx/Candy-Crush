package com.main.entity;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.main.entity.Candy.Type;
import com.main.entity.Tile.*;

public class Grid {

	private final Tile[][] tiles;
	private final int row,col;
	
	// Debug variables
	private boolean once = false;
	private boolean shouldClear = false;
	
	public Grid(final int row,final int col) {
		this.row = row;
		this.col = col;
		this.tiles = new Tile[row][col];
		
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				this.tiles[i][j] = new OccupiedTile(Candy.random(),i,j);
			}
		}
	}
	
	public Tile getTile(int row,int col) {
		return tiles[row][col];
	}
	
	public void pop() {
		shouldClear = true;
	}
	
	private void check() {
		Map<Candy,ArrayList<Tile>> matches = new HashMap<>();
		
		for(int i=0,ncol=0;i<row;i++) {
			ArrayList<Tile> match = new ArrayList<>();
			
			// Find all horizontal matches
			int j=0;
			while(j < 8) {
				Tile current = tiles[i][j];
				Candy candy = current.getCandy();
				
				
				if(i==0 && !once) {
					System.out.println(current + " , " + candy.getType().equals(Type.Circle));
				}
				
				
				if(match.size() == 0 || match.get(0).getCandy().getType().equals(candy.getType())) {
					match.add(current);
				} 
				if(!match.get(0).getCandy().getType().equals(candy.getType())) {
					if(match.size() >= 3) {
						matches.put(candy, match);
						if(shouldClear) {
							for(Tile t : match) {
								t.pop();
							}
						}
					}
					match.clear();
				}
				j++;
			}
		}
		once = true;
	}
	
	public void update(float dt) {
		check();
	}
	
	public void draw(Graphics2D g) {
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				g.drawImage(tiles[i][j].getCandy().getSprite(),j * 64,i * 64,64,64,null);
			}
		}
	}
	
}
