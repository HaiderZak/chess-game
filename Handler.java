//Zak Haider

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	
	LinkedList<Piece> object = new LinkedList<Piece>();
	
	public void tick() {
		for(int i=0; i<object.size(); i++) {
			Piece obj = object.get(i);
			obj.tick();
		}
	}
	
	public void render(Graphics g) {
		for(int i=0; i<object.size(); i++) {
			Piece obj = object.get(i);
			obj.render(g);
		}
	}
	
	public void addObject(Piece object) {
		this.object.add(object);
	}
	
	public void removeObject(Piece object) {
		this.object.remove(object);
	}
}
