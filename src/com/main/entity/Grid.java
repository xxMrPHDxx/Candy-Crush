package com.main.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.main.entity.Tile.EmptyTile;
import com.main.entity.Tile.OccupiedTile;

public class Grid {

	private final Tile[][] tiles;
	private final int row,col;
	
	public Grid(final int row,final int col) {
		this.row = row;
		this.col = col;
		this.tiles = new Tile[row][col];
		
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				this.tiles[i][j] = new OccupiedTile((Math.random() < 0.2) ? new Candy(Candy.Type.Circle) : Candy.random(),i,j);
				
				//if(i == 2 && j == 2) this.tiles[i][j] = new EmptyTile(i,j);
			}
		}
	}
	
	public Tile getTile(int row,int col) {
		return tiles[row][col];
	}
	
	private void check() {
		Map<Candy,ArrayList<Tile>> matches = new HashMap<>();
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
					if(match.size() >= 3) {
						matches.put(candy, match);
						System.out.println(match);
					}
					match.clear();
					match.add(current);
				}
				j++;
			}
			if(j == 7 && match.size() >= 3) {
				match.add(tiles[i][j]);
				matches.put(tiles[i][j].getCandy(), match);
				//System.out.println(match);
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
					if(match.size() >= 3) {
						matches.put(candy, match);
						//System.out.println(match);
					}
					match.clear();
					match.add(current);
				}
				i++;
			}
			if(i == 7 && match.size() >= 3) {
				matches.put(tiles[i][j].getCandy(), match);
				//System.out.println(match);
			}
		}
	}
	
	public void update(float dt) {
		check();
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
