package com.main.states;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import com.main.entity.Grid;
import com.main.entity.Tile;
import com.main.loaders.ImageFactory;

public class PlayState extends State {
	
	private ImageFactory imf;
	private Grid grid;
	
	private Tile firstTile,secondTile;
	
	public PlayState() {
		imf = new ImageFactory();
		imf.addImage("candies","res/img/Candy.png");

		grid = new Grid(8,8);
	}
	
	public void init() {
		
	}

	public void update(float dt) {

	}

	public void render(Graphics2D g) {
		grid.draw(g);
	}

	public void keyPressed(int key) {
		
	}

	public void keyReleased(int key) {
		
	}

	public void mousePressed(MouseEvent e) {
		final int row = e.getY() / 64;
		final int col = e.getX() / 64;
		firstTile = grid.getTile(row,col);
		
		System.out.print("Tile["+row+"]["+col+"] <==> ");
	}

	public void mouseReleased(MouseEvent e) {
		final int row = e.getY() / 64;
		final int col = e.getX() / 64;
		secondTile = grid.getTile(row,col);
		
		System.out.println("Tile["+row+"]["+col+"]");
		
		grid.swapTile(firstTile, secondTile);
	}

}
