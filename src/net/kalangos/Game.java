package net.kalangos;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 300;
	public static final int HEIGHT = 300;
	
	public final int PLAYER = 1;
	public final int OPPONENT = -1;
	public final int CURRENT = PLAYER;
	
	public BufferedImage PLAYER_SPRITE;
	public BufferedImage OPPONENT_SPRITE;
	
	public int[][] TABULEIRO = new int[3][3];
	
	public Game() {
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		this.addKeyListener(this);
		TABULEIRO[0][0] = PLAYER;
		try {
			PLAYER_SPRITE = ImageIO.read(getClass().getResource("/player.png"));
			OPPONENT_SPRITE = ImageIO.read(getClass().getResource("/opponent.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		if(CURRENT == PLAYER) {
			
		}else if (CURRENT == OPPONENT){
			
		}
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
		
		for(int xx = 0; xx < TABULEIRO.length; xx++) {
			for(int yy = 0; yy < TABULEIRO.length; yy++) {
				g.setColor(Color.black);
				g.drawRect(xx*100, yy*100, 100, 100);
				if(TABULEIRO[xx][yy] == PLAYER) {
					g.drawImage(PLAYER_SPRITE, xx*100, yy*100, null);
				}else if(TABULEIRO[xx][yy] == OPPONENT) {
					g.drawImage(OPPONENT_SPRITE, xx*100, yy*100, null);
				}
			}
		}
		
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
