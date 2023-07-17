package net.kalangos;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 300;
	public static final int HEIGHT = 300;

	public final int PLAYER = 1;
	public final int OPPONENT = -1;
	public int CURRENT = PLAYER;

	public BufferedImage PLAYER_SPRITE;
	public BufferedImage OPPONENT_SPRITE;

	public int[][] TABULEIRO = new int[3][3];

	public static int mouseX, mouseY;
	public static boolean isPressed = false;

	public Game() {
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.addMouseListener(this);
		TABULEIRO[0][0] = PLAYER;
		try {
			PLAYER_SPRITE = ImageIO.read(getClass().getResource("/player.png"));
			OPPONENT_SPRITE = ImageIO.read(getClass().getResource("/opponent.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resetTabuleiro();
	}

	public void resetTabuleiro() {
		for (int xx = 0; xx < TABULEIRO.length; xx++) {
			for (int yy = 0; yy < TABULEIRO.length; yy++) {
				TABULEIRO[xx][yy] = 0;
			}
		}
	}

	public int checkVictory() {
		// verificar se o player ganhou na horizontal

		if (TABULEIRO[0][0] == PLAYER && TABULEIRO[1][0] == PLAYER && TABULEIRO[2][0] == PLAYER) {
			// player ganhou
			return PLAYER;
		}
		if (TABULEIRO[0][1] == PLAYER && TABULEIRO[1][1] == PLAYER && TABULEIRO[2][1] == PLAYER) {
			// player ganhou
			return PLAYER;
		}
		if (TABULEIRO[0][2] == PLAYER && TABULEIRO[1][2] == PLAYER && TABULEIRO[2][2] == PLAYER) {
			// player ganhou
			return PLAYER;
		}

		// verificar se o player ganhou na vertical

		if (TABULEIRO[0][0] == PLAYER && TABULEIRO[0][1] == PLAYER && TABULEIRO[0][2] == PLAYER) {
			// player ganhou
			return PLAYER;
		}
		if (TABULEIRO[1][0] == PLAYER && TABULEIRO[1][1] == PLAYER && TABULEIRO[1][2] == PLAYER) {
			// player ganhou
			return PLAYER;
		}
		if (TABULEIRO[2][0] == PLAYER && TABULEIRO[2][1] == PLAYER && TABULEIRO[2][2] == PLAYER) {
			// player ganhou
			return PLAYER;
		}

		// verificar se o player ganhou na diagonal

		if (TABULEIRO[0][0] == PLAYER && TABULEIRO[1][1] == PLAYER && TABULEIRO[2][2] == PLAYER) {
			// player ganhou
			return PLAYER;
		}
		if (TABULEIRO[2][0] == PLAYER && TABULEIRO[1][1] == PLAYER && TABULEIRO[0][2] == PLAYER) {
			// player ganhou
			return PLAYER;
		}

		// verificar se o oponente ganhou na horizontal

		if (TABULEIRO[0][0] == OPPONENT && TABULEIRO[1][0] == OPPONENT && TABULEIRO[2][0] == OPPONENT) {
			// oponente ganhou
			return OPPONENT;
		}
		if (TABULEIRO[0][1] == OPPONENT && TABULEIRO[1][1] == OPPONENT && TABULEIRO[2][1] == OPPONENT) {
			// oponente ganhou
			return OPPONENT;
		}
		if (TABULEIRO[0][2] == OPPONENT && TABULEIRO[1][2] == OPPONENT && TABULEIRO[2][2] == OPPONENT) {
			// oponente ganhou
			return OPPONENT;
		}

		// verificar se o oponente ganhou na vertical

		if (TABULEIRO[0][0] == OPPONENT && TABULEIRO[0][1] == OPPONENT && TABULEIRO[0][2] == OPPONENT) {
			// oponente ganhou
			return OPPONENT;
		}
		if (TABULEIRO[1][0] == OPPONENT && TABULEIRO[1][1] == OPPONENT && TABULEIRO[1][2] == OPPONENT) {
			// oponente ganhou
			return OPPONENT;
		}
		if (TABULEIRO[2][0] == OPPONENT && TABULEIRO[2][1] == OPPONENT && TABULEIRO[2][2] == OPPONENT) {
			// oponente ganhou
			return OPPONENT;
		}

		// verificar se o oponente ganhou na diagonal

		if (TABULEIRO[0][0] == OPPONENT && TABULEIRO[1][1] == OPPONENT && TABULEIRO[2][2] == OPPONENT) {
			// oponente ganhou
			return OPPONENT;
		}
		if (TABULEIRO[2][0] == OPPONENT && TABULEIRO[1][1] == OPPONENT && TABULEIRO[0][2] == OPPONENT) {
			// oponente ganhou
			return OPPONENT;
		}

		int curScore = 0;

		// EMPATE
		for (int xx = 0; xx < TABULEIRO.length; xx++) {
			for (int yy = 0; yy < TABULEIRO.length; yy++) {
				if (TABULEIRO[xx][yy] != 0) {
					curScore++;
				}
			}
		}

		if (curScore == TABULEIRO.length * TABULEIRO[0].length) {
			return 0;
		}
		// ninguém ganhou
		return -10;
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
		if (CURRENT == PLAYER) {
			if (isPressed) {
				isPressed = false;
				mouseX /= 100;
				mouseY /= 100;
				if (TABULEIRO[mouseX][mouseY] == 0) {
					TABULEIRO[mouseX][mouseY] = PLAYER;
					CURRENT = OPPONENT;
				}
			}
		} else if (CURRENT == OPPONENT) {
			if (isPressed) {
				isPressed = false;
				mouseX /= 100;
				mouseY /= 100;
				if (TABULEIRO[mouseX][mouseY] == 0) {
					TABULEIRO[mouseX][mouseY] = OPPONENT;
					CURRENT = PLAYER;
				}
			}
		}
		if (checkVictory() == PLAYER) {
			System.out.println("Player Ganhou");

		} else if (checkVictory() == OPPONENT) {
			System.out.println("Oponente Ganhou");

		} else if (checkVictory() == 0) {
			System.out.println("O jogo empatou!");

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
		g.fillRect(0, 0, WIDTH, HEIGHT);

		for (int xx = 0; xx < TABULEIRO.length; xx++) {
			for (int yy = 0; yy < TABULEIRO.length; yy++) {
				g.setColor(Color.black);
				g.drawRect(xx * 100, yy * 100, 100, 100);
				if (TABULEIRO[xx][yy] == PLAYER) {
					g.drawImage(PLAYER_SPRITE, xx * 100 + 25, yy * 100 + 25, 50, 50, null);
				} else if (TABULEIRO[xx][yy] == OPPONENT) {
					g.drawImage(OPPONENT_SPRITE, xx * 100 + 25, yy * 100 + 25, 50, 50, null);
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
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		isPressed = true;
		mouseX = e.getX();
		mouseY = e.getY();

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
