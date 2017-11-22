package com.main.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Match {

	private Set<Tile> tiles;
	private Set<Tile> sorted;
	
	public Match(Set<Tile> tiles) {
		this.tiles = tiles;
		this.sorted = new HashSet<>();
	}

	public ArrayList<Tile>[] sort() {
		ArrayList<Tile>[] rows = (ArrayList<Tile>[])new ArrayList[8];
		
		for(int i=0;i<8;i++) {
			rows[i] = new ArrayList<>();
		}
		
		for(Tile tile : tiles) {
			rows[tile.row].add(tile);
		}
		return rows;
	}
	
}
