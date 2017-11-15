package com.main.states;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.Stack;

public class GSM {
	
	private Stack<State> states;
	
	public GSM() {
		states = new Stack<State>();
	}
	
	public void push(State s) {
		states.push(s);
	}
	
	public void pop() {
		states.pop();
	}
	
	public void set(State s) {
		states.push(s);
		states.pop();
	}
	
	public void update(float dt) {
		states.peek().update(dt);
	}
	
	public void render(Graphics2D g) {
		states.peek().render(g);
	}
	
	public void keyPressed(int key) {
		states.peek().keyPressed(key);
	}
	
	public void keyReleased(int key) {
		states.peek().keyReleased(key);
	}

	public void mouseClicked(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		states.peek().mousePressed(e);
	}

	public void mouseReleased(MouseEvent e) {
		states.peek().mouseReleased(e);
	}
	
}
