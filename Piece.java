import java.util.*;
import java.awt.Graphics;

public abstract class Piece {
    public String player;
    public int x, y;

    public Piece(String player, int x, int y){
        this.player = player;
        this.x = x;
        this.y = y;
    }

    /**  Determines if the move is valid or not for the specified piece 
    * @param x2 the final x location
    * @param y2 the final y location
    **/
    public abstract boolean isValidMove(int x2, int y2);

    public abstract List<List<Integer>> legalMoves();

    public abstract void tick();
    public abstract void render(Graphics g);
    
    public String getColor(){
        return player;
    }

    public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
}