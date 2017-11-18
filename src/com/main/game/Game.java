package com.main.game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.main.Res;
import com.main.states.GSM;
import com.main.states.PlayState;

public class Game extends JFrame {

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 640,HEIGHT = 640;
	public static final String TITLE = "Candy Crush";
	
	// Resources
	public static final Res res = new Res();
	
	public Game() {
		super(TITLE);
		setContentPane(new Panel());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	public static final void main(String[] args) {
		new Game();
	}
	
	private static class Panel extends JPanel implements Runnable,KeyListener,MouseListener {

		private static final long serialVersionUID = 1L;
		
		// Dimension
		public static final int WIDTH = 512,HEIGHT=512;
		
		// Game thread
		private Thread thread;
		private boolean running;
		private int FPS = 60;
		private long targetTime = 1000 / FPS;
		private float delta = 0;
		
		// Image
		private BufferedImage image;
		private Graphics2D g;
		
		// GSM
		private GSM gsm;
		
		public Panel() {
			super();
			setPreferredSize(new Dimension(WIDTH,HEIGHT));
			setFocusable(true);
			requestFocus();
		}
		
		@Override
		public void addNotify() {
			super.addNotify();
			if(thread == null) {
				thread = new Thread(this);
				addKeyListener(this);
				addMouseListener(this);
				thread.start();
			}
		}
		
		public void init() {
			image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
			g = (Graphics2D) image.getGraphics();
			
			gsm = new GSM();
			gsm.push(new PlayState());
			
			res.loadImage("candies", "res/img/Candy.png");
			
			running = true;
		}

		@Override
		public void run() {
			init();
			
			long start,elapsed,wait;
			
			while(running) {
				start = System.nanoTime();
				
				update();
				draw();
				drawToScreen();
				
				elapsed = System.nanoTime() - start;
				
				delta += elapsed / 1000000000 * FPS;
				
				wait = targetTime - elapsed / 1000000;
				
				if(wait < 0) wait = 5;
				
				try {
					Thread.sleep(wait);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		public void update() {
			gsm.update(delta);
		}
		
		public void draw() {
			g.setColor(getBackground());
			g.fillRect(0, 0, WIDTH, HEIGHT);
			
			// DRAW START
			
			gsm.render(g);
			
			// DRAW END
		}
		
		public void drawToScreen() {
			Graphics g2 = getGraphics();
			g2.drawImage(image, 0, 0, null);
			g2.dispose();
		}

		public void keyPressed(KeyEvent e) {
			System.out.println((e.getKeyCode()==KeyEvent.VK_Z) + " is pressed!");
			gsm.keyPressed(e.getKeyCode());
		}

		public void keyReleased(KeyEvent e) {
			gsm.keyReleased(e.getKeyCode());
		}

		@Override
		public void keyTyped(KeyEvent e) {
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			gsm.mousePressed(e);
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			gsm.mouseReleased(e);
		}
		
	}

}
