import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import javax.imageio.ImageIO;

public class Chess extends Canvas implements Runnable, MouseListener {
	private static final long serialVersionUID = -1969447397438808410L;
	private boolean running;
	private Thread thread;
	private int[] selectedBox;
	private boolean clicked = false;
	private int turn = 0; //0 = White, 1 = Black
	private int blockWidth;
	private int blockHeight;
	private int[] pair;
	private int[] newPair;
	private boolean whiteInCheck = false;
	private boolean blackInCheck = false;
	private BufferedImage image;
	private Handler handler;
	private boolean whiteCheckmated = false, blackCheckmated = false;

	private Bishop b_b1, b_b2, w_b1, w_b2;
	private Knight b_kn1, b_kn2, w_kn1, w_kn2;
	private Rook b_r1, b_r2, w_r1, w_r2;
	private King b_k, w_k;
	private Queen b_q, w_q;
	private Pawn b_p1, b_p2, b_p3, b_p4, b_p5, b_p6, b_p7, b_p8,
	 w_p1, w_p2, w_p3, w_p4, w_p5, w_p6, w_p7, w_p8;

	public Chess() {
		new Main(this);
		addMouseListener(this);
		handler = new Handler();
		if(gameState == STATE.MENU) {
			URL resource = getClass().getResource("menu.png");
			try {
				image = ImageIO.read(resource);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void initBoard() {
			selectedBox = new int[2];
			blockWidth = this.getWidth() / 8;
			blockHeight = this.getHeight() / 8;

			//Black Pieces initialization
			b_b1 = new Bishop(handler, this, "B", 2, 0);
			handler.addObject(b_b1);
			b_b2 = new Bishop(handler, this, "B", 5, 0);
			handler.addObject(b_b2);
			b_r1 = new Rook(handler, this, "B", 0, 0);
			handler.addObject(b_r1);
			b_r2 = new Rook(handler, this, "B", 7, 0);
			handler.addObject(b_r2);
			b_kn1 = new Knight(handler, this, "B", 1, 0);
			handler.addObject(b_kn1);
			b_kn2 = new Knight(handler, this, "B", 6, 0);
			handler.addObject(b_kn2);
			b_q = new Queen(handler, this, "B", 3, 0);
			handler.addObject(b_q);
			b_k = new King(handler, this, "B", 4, 0);
			handler.addObject(b_k);
			b_p1 = new Pawn(handler, this, "B", 0, 1);
			handler.addObject(b_p1);
			b_p2 = new Pawn(handler, this, "B", 1, 1);
			handler.addObject(b_p2);
			b_p3 = new Pawn(handler, this, "B", 2, 1);
			handler.addObject(b_p3);
			b_p4 = new Pawn(handler, this, "B", 3, 1);
			handler.addObject(b_p4);
			b_p5 = new Pawn(handler, this, "B", 4, 1);
			handler.addObject(b_p5);
			b_p6 = new Pawn(handler, this, "B", 5, 1);
			handler.addObject(b_p6);
			b_p7 = new Pawn(handler, this, "B", 6, 1);
			handler.addObject(b_p7);
			b_p8 = new Pawn(handler, this, "B", 7, 1);
			handler.addObject(b_p8);

			//White Pieces Initialization
			w_b1 = new Bishop(handler, this, "W", 2, 7);
			handler.addObject(w_b1);
			w_b2 = new Bishop(handler, this, "W", 5, 7);
			handler.addObject(w_b2);
			w_r1 = new Rook(handler, this, "W", 0, 7);
			handler.addObject(w_r1);
			w_r2 = new Rook(handler, this, "W", 7, 7);
			handler.addObject(w_r2);
			w_kn1 = new Knight(handler, this, "W", 1, 7);
			handler.addObject(w_kn1);
			w_kn2 = new Knight(handler, this, "W", 6, 7);
			handler.addObject(w_kn2);
			w_q = new Queen(handler, this, "W", 3, 7);
			handler.addObject(w_q);
			w_k = new King(handler, this, "W", 4, 7);
			handler.addObject(w_k);
			w_p1 = new Pawn(handler, this, "W", 0, 6);
			handler.addObject(w_p1);
			w_p2 = new Pawn(handler, this, "W", 1, 6);
			handler.addObject(w_p2);
			w_p3 = new Pawn(handler, this, "W", 2, 6);
			handler.addObject(w_p3);
			w_p4 = new Pawn(handler, this, "W", 3, 6);
			handler.addObject(w_p4);
			w_p5 = new Pawn(handler, this, "W", 4, 6);
			handler.addObject(w_p5);
			w_p6 = new Pawn(handler, this, "W", 5, 6);
			handler.addObject(w_p6);
			w_p7 = new Pawn(handler, this, "W", 6, 6);
			handler.addObject(w_p7);
			w_p8 = new Pawn(handler, this, "W", 7, 6);
			handler.addObject(w_p8);
	}

	public double getBlockHeight(){
		return blockHeight;
	}

	public double getBlockWidth(){
		return blockWidth;
	}

	public enum STATE {
		MENU,
		PLAYING,
		GAMEOVER
	}

	public STATE gameState = STATE.MENU;


	public int getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		if(gameState == STATE.MENU) {
			initBoard();
			if(e.getX() >= 237 && e.getX() <= 470 && e.getY() >= 334 && e.getY() <= 380) {
				gameState = STATE.PLAYING;
			}
			if(e.getX() >= 237 && e.getX() <= 470 && e.getY() >= 400 && e.getY() <= 446) {
				System.exit(0);
			}
		}
		else {
			if(clicked == true){
				selectedBox[0] = e.getX();
				selectedBox[1] = e.getY();
				newPair = getBlockFromMouse(selectedBox[0], selectedBox[1]);

				//System.out.println(newPair[0] + "," + newPair[1]);
			}
			else{
				selectedBox[0] = e.getX();
				selectedBox[1] = e.getY();
				pair = getBlockFromMouse(selectedBox[0], selectedBox[1]);
				clicked = true;	
		
				//System.out.println(pair[0] + "," + pair[1]);
			}
		}		
	}
	public void mouseReleased(MouseEvent e) {
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();

		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			if(delta >= 1) {
				tick();
				delta--;
			}
			render();

			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
			}

		}
		stop();			
	}

	public void tick() {
		if(gameState == STATE.PLAYING) {
			for(int i=0; i<handler.object.size(); i++){
				if(handler.object.get(i).getClass().getName().equals("Pawn")){
					Object obj = Piece.class.cast(handler.object.get(i));
					Pawn p = (Pawn) obj;
					if(p.getColor().equals("W") && p.getY() == 0){
						int x = p.getX();
						int y = p.getY();
						handler.removeObject(p);
						handler.addObject(new Queen(handler, this, "W", x, y));
					}
					if(p.getColor().equals("B") && p.getY() == 7){
						int x = p.getX();
						int y = p.getY();
						handler.removeObject(p);
						handler.addObject(new Queen(handler, this, "B", x, y));
					}
				}
			}

			//Check for white checkmate
			if(whiteInCheck){
				boolean flag = false;
				for(int i=0; i<handler.object.size(); i++){
					if(handler.object.get(i).getColor().equals("W")){
						List<List<Integer>> legalMoves = handler.object.get(i).legalMoves();
						int objX = handler.object.get(i).getX();
						int objY = handler.object.get(i).getY();
						for(int j=0; j<legalMoves.size(); j++){
							handler.object.get(i).setX(legalMoves.get(j).get(0));
							handler.object.get(i).setY(legalMoves.get(j).get(1));
							isWhiteInCheck();
							if(!whiteInCheck){
								flag = true;
								break;
							}
						}
						handler.object.get(i).setX(objX);
						handler.object.get(i).setY(objY);
					}
				}
				if(!flag){
					whiteCheckmated = true;
				}
				else{
					whiteCheckmated = false;
				}
			}
			//Check for black checkmate
			if(blackInCheck){
				boolean flag = false;
				for(int i=0; i<handler.object.size(); i++){
					if(handler.object.get(i).getColor().equals("B")){
						List<List<Integer>> legalMoves = handler.object.get(i).legalMoves();
						int objX = handler.object.get(i).getX();
						int objY = handler.object.get(i).getY();
						for(int j=0; j<legalMoves.size(); j++){
							handler.object.get(i).setX(legalMoves.get(j).get(0));
							handler.object.get(i).setY(legalMoves.get(j).get(1));
							isBlackInCheck();
							if(!blackInCheck){
								flag = true;
								break;
							}
						}
						handler.object.get(i).setX(objX);
						handler.object.get(i).setY(objY);
					}
				}
				if(!flag){
					blackCheckmated = true;
				}
				else{
					blackCheckmated = false;
				}
			}
			handler.tick();
		}
	}

	public void isBlackInCheck(){
		boolean flag = false;
		for(int i=0; i<handler.object.size(); i++){
			List<List<Integer>> legalMoves = handler.object.get(i).legalMoves();
			for(int k=0; k<handler.object.size(); k++){
				if(handler.object.get(k).getClass().getName().equals("King")){
					Object obj = Piece.class.cast(handler.object.get(k));
					King king = (King) obj;
					for(int j=0; j<legalMoves.size(); j++){
						if(legalMoves.get(j).get(0) == handler.object.get(k).getX() && legalMoves.get(j).get(1) == handler.object.get(k).getY()
							&& !handler.object.get(i).getColor().equals(king.getColor())){
							if(king.getColor().equals("B")){
								blackInCheck = true;
							}
							flag = true;
						}
					}
					if(!flag){
						blackInCheck = false;
					}
				}
			}
		}
	}

	public void isWhiteInCheck(){
		boolean flag = false;
		for(int i=0; i<handler.object.size(); i++){
			List<List<Integer>> legalMoves = handler.object.get(i).legalMoves();
			for(int k=0; k<handler.object.size(); k++){
				if(handler.object.get(k).getClass().getName().equals("King")){
					Object obj = Piece.class.cast(handler.object.get(k));
					King king = (King) obj;
					for(int j=0; j<legalMoves.size(); j++){
						if(legalMoves.get(j).get(0) == handler.object.get(k).getX() && legalMoves.get(j).get(1) == handler.object.get(k).getY()
							&& !handler.object.get(i).getColor().equals(king.getColor())){
							if(king.getColor().equals("W")){
								whiteInCheck = true;
							}
							flag = true;
						}
					}
					if(!flag){
						whiteInCheck = false;
					}
				}
			}
		}
	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();

		// DRAW BOARD

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 710, 580);

		if(gameState == STATE.MENU) {
			g.drawImage(image, 0, 0, this);
		}
		if(gameState == STATE.PLAYING) {
			g.setColor(Color.BLACK);

			Color darkSpot = new Color(125,135,150);
			Color lightSpot = new Color(232,235,239);
			for(int i=0; i<8; i++){
				for(int j=0; j<8; j++){
					boolean isLight = (i % 2) == (j % 2);
					boolean isDark = (i % 2) != (j % 2);
					if(isLight){
						g.setColor(lightSpot);
						g.fillRect(i * blockWidth, j * blockHeight, blockWidth, blockHeight);		
					}
					if(isDark){
						g.setColor(darkSpot);
						g.fillRect(i * blockWidth, j * blockHeight, blockWidth, blockHeight);		
					}
				}
			}

			if(whiteInCheck){
				g.setColor(Color.RED);
				for(Piece p: handler.object){
					if(p.getClass().getName().equals("King") && p.getColor().equals("W")){
						g.drawRect(p.getX() * blockWidth, p.getY() * blockHeight, blockWidth, blockHeight);
					}
				}
			}
			if(blackInCheck){
				g.setColor(Color.RED);
				for(Piece p: handler.object){
					if(p.getClass().getName().equals("King") && p.getColor().equals("B")){
						g.drawRect(p.getX() * blockWidth, p.getY() * blockHeight, blockWidth, blockHeight);
					}
				}
			}
			// Draw selected boxes onto screen
			Color tan = Color.decode("#d2b48c");

			g.setFont(new Font("Arial", Font.PLAIN, 35)); 
			g.setColor(Color.BLACK);
			if(clicked && !blackCheckmated && !whiteCheckmated) {
				Color boxCol = new Color(0,0,0,45);
				g.setColor(boxCol);
				g.fillRect(pair[0] * blockWidth, pair[1] * blockHeight, blockWidth, blockHeight);
				boolean f = false;
				if(turn == 0){// White turn
					isBlackInCheck();
					isWhiteInCheck(); 

					for(int i=0; i<handler.object.size(); i++){
						if(pair != null && handler.object.get(i).getColor().equals("W") && handler.object.get(i).getX() == pair[0] && handler.object.get(i).getY() == pair[1]){
							f = true;
							List<List<Integer>> legalMoves = handler.object.get(i).legalMoves();
							//System.out.println(handler.object.get(i).getX() + "," + handler.object.get(i).getY());

							for(int j=0; j<legalMoves.size(); j++){
								for(int c=0; c<handler.object.size(); c++){
									if(handler.object.get(c).getColor().equals("B") && legalMoves.get(j).get(0) == handler.object.get(c).getX() && legalMoves.get(j).get(1) == handler.object.get(c).getY()){
										g.setColor(tan);
										g.fillRect(legalMoves.get(j).get(0) * blockWidth, legalMoves.get(j).get(1) * blockHeight, blockWidth, blockHeight);
									} 
									else{
										g.setColor(boxCol);
										g.fillOval(legalMoves.get(j).get(0) * blockWidth + 33, legalMoves.get(j).get(1) * blockHeight + 25, 15, 15);
									}
								}

								if(newPair != null && newPair[0] == handler.object.get(i).getX() && newPair[1] == handler.object.get(i).getY()){ //Don't count clicking on own piece as a move
									turn = 0;
									clicked = false;
									newPair = null;
								}
								else if(newPair != null && newPair[0] == legalMoves.get(j).get(0) && newPair[1] == legalMoves.get(j).get(1)){
									handler.object.get(i).setX(newPair[0]);
									handler.object.get(i).setY(newPair[1]);
									Piece pR = null;
									for(int k=0; k<handler.object.size(); k++){ // Check for collision white to black
										if(handler.object.get(k).getColor().equals("B") && handler.object.get(k).getX() == handler.object.get(i).getX() && handler.object.get(k).getY() == handler.object.get(i).getY()){
											pR = handler.object.get(k);
											handler.removeObject(handler.object.get(k));
										}
									}
									isBlackInCheck();
									isWhiteInCheck();
									
									if(whiteInCheck){
										if(pR != null){
											handler.addObject(pR);
										}
										handler.object.get(i).setX(pair[0]);
										handler.object.get(i).setY(pair[1]);
										clicked = false;
										turn = 0;
										pair = null;
										newPair = null;
									}
									else{
										if(pR != null){
											handler.removeObject(pR);
										}
										if(handler.object.get(i).getClass().getName().equals("Pawn")){
											Object obj = Piece.class.cast(handler.object.get(i));
											Pawn p = (Pawn) obj;
											p.setFirstMove(false);
										}
										//System.out.println("Pair: " + pair[0] + "," + pair[1]);
										//System.out.println("New Pair: " + newPair[0] + "," + newPair[1]);
										pair = null;
										newPair = null;
										turn = 1;
										clicked = false;
									}
								}
							}
						}
					}
					if(!f){
						clicked = false;
						newPair = null;
					}
				}
				boolean t = false;
				if (turn == 1){ // Black turn
					isBlackInCheck();
					isWhiteInCheck();

					for(int i=0; i<handler.object.size(); i++){
						if(pair != null && handler.object.get(i).getColor().equals("B") && handler.object.get(i).getX() == pair[0] && handler.object.get(i).getY() == pair[1]){
							t = true;
							List<List<Integer>> legalMoves = handler.object.get(i).legalMoves();
							for(int j=0; j<legalMoves.size(); j++){
								for(int c=0; c<handler.object.size(); c++){
									if(handler.object.get(c).getColor().equals("W") && legalMoves.get(j).get(0) == handler.object.get(c).getX() && legalMoves.get(j).get(1) == handler.object.get(c).getY()){
										g.setColor(tan);
										g.fillRect(legalMoves.get(j).get(0) * blockWidth, legalMoves.get(j).get(1) * blockHeight, blockWidth, blockHeight);
									} 
									else{
										g.setColor(boxCol);
										g.fillOval(legalMoves.get(j).get(0) * blockWidth + 33, legalMoves.get(j).get(1) * blockHeight + 25, 15, 15);
									}
								}
								
								//g.fillRect(legalMoves.get(j).get(0) * blockWidth, legalMoves.get(j).get(1) * blockHeight, blockWidth, blockHeight);
								if(newPair != null && newPair[0] == handler.object.get(i).getX() && newPair[1] == handler.object.get(i).getY()){ //Don't count clicking on own piece as a move
									turn = 1;
									clicked = false;
									newPair = null;
								}
								else if(newPair != null && newPair[0] == legalMoves.get(j).get(0) && newPair[1] == legalMoves.get(j).get(1)){

									handler.object.get(i).setX(newPair[0]);
									handler.object.get(i).setY(newPair[1]);
									Piece pR = null;
									for(int k=0; k<handler.object.size(); k++){ // Check for collision black to white
										if(handler.object.get(k).getColor().equals("W") && handler.object.get(k).getX() == handler.object.get(i).getX() && handler.object.get(k).getY() == handler.object.get(i).getY()){
											pR = handler.object.get(k);
											handler.removeObject(handler.object.get(k));
										}
									}
									isBlackInCheck();
									isWhiteInCheck();
									
									if(blackInCheck){
										if(pR != null){
											handler.addObject(pR);
										}
										handler.object.get(i).setX(pair[0]);
										handler.object.get(i).setY(pair[1]);
										clicked = false;
										turn = 1;
										pair = null;
										newPair = null;
									}
									else{
										if(pR != null){
											handler.removeObject(pR);
										}
										if(handler.object.get(i).getClass().getName().equals("Pawn")){
											Object obj = Piece.class.cast(handler.object.get(i));
											Pawn p = (Pawn) obj;
											p.setFirstMove(false);
										}
										pair = null;
										newPair = null;
										turn = 0;
										clicked = false;
									}
								}
							}
						}
					}
					if(!t){
						clicked = false;
						newPair = null;
					}
				}
			}
		handler.render(g);

		g.setColor(tan);
		g.setFont(new Font("Arial", Font.PLAIN, 35)); 
		if(blackCheckmated){
			g.drawString("WHITE WINS!", 225, 250);
		}
		if(whiteCheckmated){
			g.drawString("BLACK WINS!", 225, 250);
		}
		}

		g.dispose();
		bs.show();
	}

	public int[] getBlockFromMouse(int x, int y){
		return new int[] {x / blockWidth, y / blockHeight};
	}


	public static void main(String[] args) {
		new Chess();
	}
}