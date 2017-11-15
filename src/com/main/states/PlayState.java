package com.main.states;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import com.main.entity.Grid;
import com.main.entity.Tile;
import com.main.loaders.ImageFactory;
import com.main.math.Vector;

public class PlayState extends State {
	
	private ImageFactory imf;
	private Grid grid;

	private Tile tile;
	private Vector v1,v2;
	
	public PlayState() {
		imf = new ImageFactory();
		imf.addImage("candies","res/img/Candy.png");

		grid = new Grid(8,8);
	}
	
	public void update(float dt) {
		grid.update(dt);
	}

	public void render(Graphics2D g) {
		grid.draw(g);
	}

	public void keyPressed(int key) {
		
	}

	public void keyReleased(int key) {
		
	}

	public void mousePressed(MouseEvent e) {
		v1 = new Vector(e.getX(),e.getY());
		tile = grid.getTile(v1.y / 64,v1.x / 64);
	}

	public void mouseReleased(MouseEvent e) {
		v2 = new Vector(e.getX(),e.getY());
		
		grid.swapTile(tile,v2.sub(v1).angle());
	}

}
