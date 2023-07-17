package net.kalangos;

public class Node {

	public int x;
	public int y;
	public int score = 0;
	public int depth = 0;
	
	public Node(int x, int y, int score, int depth) {
		this.x = x;
		this.y = y;
		this.score = score;
		this.depth = depth;
	}
}
