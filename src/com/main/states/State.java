package com.main.states;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import com.main.Res;

public abstract class State {

	protected final Res Res = new Res();
	
	public abstract void update(float dt);
	public abstract void draw(Graphics2D g);
	public abstract void keyPressed(int key);
	public abstract void keyReleased(int key);
	public abstract void mousePressed(MouseEvent e);
	public abstract void mouseReleased(MouseEvent e);
	
}
