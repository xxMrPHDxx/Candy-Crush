package com.main.states;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.main.entity.Grid;
import com.main.entity.Tile;
import com.main.game.Game;
import com.main.loaders.ImageFactory;
import com.main.math.Vector;

public class PlayState extends State {
	
	private Grid grid;
	
	private BufferedImage candy = Res.getImage("candies");

	private Tile tile1,tile2;
	private Vector v1;
	
	public PlayState() {
		grid = new Grid(8,8);
	}
	
	public void update(float dt) {
		grid.update(dt);
	}

	public void draw(Graphics2D g) {
		grid.draw(g);
	}

	public void mousePressed(MouseEvent e) {
		v1 = new Vector(e.getX(),e.getY());
		if(tile1 == null && tile2 == null) {
			tile1 = grid.getTile(v1.y / 64,v1.x / 64);
		}else if(tile1 != null) {
			tile2 = grid.getTile(v1.y / 64,v1.x / 64);
			if(tile1.canSwap(tile2)) {
				tile1.swap(tile2);
			}
			tile1 = tile2 = null;
		}
	}

	public void mouseReleased(MouseEvent e) {
		
	}

	public void keyPressed(int key) {
		
	}

	public void keyReleased(int key) {}

}
