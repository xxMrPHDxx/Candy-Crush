package com.main.states;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public abstract class State {

	public abstract void update(float dt);
	public abstract void render(Graphics2D g);
	public abstract void keyPressed(int key);
	public abstract void keyReleased(int key);
	public abstract void mousePressed(MouseEvent e);
	public abstract void mouseReleased(MouseEvent e);
	
}
