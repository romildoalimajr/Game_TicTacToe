package net.kalangos;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 300;
	public static final int HEIGHT = 300;
	
	public final int PLAYER = 1;
	public final int OPPONENT = 2;
	
	public Game() {
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
	
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		JFrame frame = new JFrame("Tic Tac Toe - Kalango's");
		frame.add(game);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		new Thread(game).start();
	}
	
	public void tick() {
		
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0,0,WIDTH,HEIGHT);
		
		g.dispose();
		bs.show();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		double fps = 60.0;
		while (true) {
			tick();
			render();
			try {
				Thread.sleep((int) (1000 / fps));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
