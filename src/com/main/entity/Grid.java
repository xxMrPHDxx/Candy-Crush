package com.main.entity;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
				this.tiles[i][j] = new OccupiedTile(Candy.random(),i,j);
			}
		}
	}
	
	public Tile getTile(int row,int col) {
		return tiles[row][col];
	}
	
	public void swapTile(Tile tile,double angle) {
		int dr=0,dc=0;
		
		if((angle < Math.PI / 4 && angle > 0) || (angle > Math.PI * 7 / 4 && angle < 2 * Math.PI)) {
			dc = 1;
		}else if(angle > Math.PI / 4 && angle < Math.PI * 3 / 4) {
			dr = -1;
		}else if(angle > Math.PI * 3 / 4 && angle < Math.PI * 5 / 4) {
			dc = -1;
		}else if(angle > Math.PI * 5 / 4 && angle < Math.PI * 7 / 4) {
			dr = 1;
		}
		
		Tile other = tiles[tile.row + dr][tile.col - dc];
		
		if(tile.getCandy().getType().equals(other.getCandy().getType()));
		tile.swap(other);;
	}
	
	private void check() {
		Map<Candy,ArrayList<Tile>> matches = new HashMap<>();
		
		for(int i=0;i<row;i++) {
			ArrayList<Tile> matchTiles = new ArrayList<>();
			
			for(int j=0;j<col;j++) {
				int nrow=0,ncol=0;
				
				// Find all vertical matches => row
				if(j+1 < 8) {
					Tile current = tiles[i][j],next = tiles[i][j+1];
					
					while(next != null && j + 1 < 8){
						if(current.getCandy().getType().equals(next.getCandy().getType())) {
							System.out.println(i+","+j+","+(current.getCandy().getType() == next.getCandy().getType()));
							matchTiles.add(current);
							ncol++;
						} else {
							matchTiles.add(current);
							if(matchTiles.size() >= 3) {
								matches.put(matchTiles.get(0).getCandy(), matchTiles);
							}
							matches.clear();
							
							j += ncol;
							ncol = 0;
							break;
						}
						current = next;
						next = (current.col + 1 < 8) ? tiles[i][current.col+1] : null;
					}
					
					j += ncol;
				}
			}
			
			if(matchTiles.size() >= 3) {
				matches.put(matchTiles.get(0).getCandy(), matchTiles);
			}
		}
		
		for(ArrayList<Tile> tiles : matches.values()) {
			Tile t1 = tiles.get(0);
			//Tile t2 = tiles.get(tiles.size() - 2);
			System.out.print(matches.size() + " => ");
			//System.out.println(t2.getCandy().getType() + " , [" + t2.row + "," + t2.col + "]");
			
			for(int i=1;i<tiles.size()-1;i++) {
				Tile t = tiles.get(i);
//				System.out.println(t.row + "," + t.col);
				System.out.print(t.getCandy().getType() + " , [" + t.row + "," + t.col + "] || ");
			}

			Tile t2 = tiles.get(tiles.size() - 1);
			System.out.println(t2.getCandy().getType() + " , [" + t2.row + "," + t2.col + "]");
		}
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
