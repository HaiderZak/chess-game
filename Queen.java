import java.util.List;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import java.awt.Graphics;

public class Queen extends Piece {

    Chess ch;
    private BufferedImage image;
    private Handler handler;
    private int closestDown, closestLeft, closestRight, closestUp, topLeft, topRight, bottomLeft, bottomRight;
    public Queen(Handler handler, Chess ch, String player, int x, int y) {
        super(player, x, y);
        this.ch = ch;
        this.handler = handler;
        URL resource;
        if(player.equals("B")){
            resource = getClass().getResource("/resources/b_queen.png");
        }
        else{
            resource = getClass().getResource("/resources/w_queen.png");
        }
        try {
            image = ImageIO.read(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isValidMove(int x2, int y2) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<List<Integer>> legalMoves() {
        List<List<Integer>> legMoves = new ArrayList<List<Integer>>();
        // List<List<Integer>> illegalMoves = new ArrayList<List<Integer>>();
        closestUp = 0;
        closestDown = 7;
        closestRight = 7;
        closestLeft = 0;

        int x = this.getX();
        int y = this.getY();
        
        boolean flag = true;
        //Right
        x = this.getX() + 1;
        y = this.getY();
        while (x <= 7 && flag){
            for(int i=0; i<handler.object.size(); i++){
                if(handler.object.get(i).getX() == x && this.getY() == handler.object.get(i).getY()){
                    if(!this.getColor().equals(handler.object.get(i).getColor())){
                        closestRight = x;
                    }
                    else{
                        closestRight = x - 1;
                    }
                    flag = false;
                }
            }
            x++;
        }
        //Left
        x = this.getX() - 1;
        y = this.getY();
        flag = true;
        while (x >= 0 && flag){
            for(int i=0; i<handler.object.size(); i++){
                if(handler.object.get(i).getX() == x && this.getY() == handler.object.get(i).getY()){
                    if(!this.getColor().equals(handler.object.get(i).getColor())){
                        closestLeft = x;
                    }
                    else{
                        closestLeft = x + 1;
                    }
                    flag = false;
                }
            }
            x--;
        }
        //Up
        x = this.getX();
        y = this.getY() - 1;
        flag = true;
        while (y >= 0 && flag){
            for(int i=0; i<handler.object.size(); i++){
                if(handler.object.get(i).getY() == y && this.getX() == handler.object.get(i).getX()){
                    if(!this.getColor().equals(handler.object.get(i).getColor())){
                        closestUp = y;
                    }
                    else{
                        closestUp = y + 1;
                    }
                    flag = false;
                }
            }
            y--;
        }
        //Down
        x = this.getX();
        y = this.getY() + 1;
        flag = true;
        while (y <= 7 && flag){
            for(int i=0; i<handler.object.size(); i++){
                if(handler.object.get(i).getY() == y && this.getX() == handler.object.get(i).getX()){
                    if(!this.getColor().equals(handler.object.get(i).getColor())){
                        closestDown = y;
                    }
                    else{
                        closestDown = y - 1;
                    }
                    flag = false;
                }
            }
            y++;
        }
        legMoves.add(new ArrayList<Integer>());
        legMoves.get(0).add(this.getX());
        legMoves.get(0).add(this.getY());
        int p = this.getY() - 1;
        int k = 1;
        while(p >= closestUp){
            legMoves.add(new ArrayList<Integer>());
            legMoves.get(k).add(this.getX());
            legMoves.get(k).add(p);
            k++;
            p--;
        }
        p = this.getY() + 1;
        while(p <= closestDown){
            legMoves.add(new ArrayList<Integer>());
            legMoves.get(k).add(this.getX());
            legMoves.get(k).add(p);
            k++;
            p++;
        }
        p = this.getX() + 1;
        while(p <= closestRight){
            legMoves.add(new ArrayList<Integer>());
            legMoves.get(k).add(p);
            legMoves.get(k).add(this.getY());
            k++;
            p++;
        }
        p = this.getX() - 1;
        while(p >= closestLeft){
            legMoves.add(new ArrayList<Integer>());
            legMoves.get(k).add(p);
            legMoves.get(k).add(this.getY());
            k++;
            p--;
        }

        x = this.getX() + 1;
        y = this.getY() - 1;

        topRight = 7;
        topLeft = 0;
        bottomRight = 7;
        bottomLeft = 0;

        flag = true;
        //Top right
        while(x <= 7 && y >= 0 && flag){
            for(Piece p1: handler.object){
                if(p1.getX() == x && p1.getY() == y){
                    if(!p1.getColor().equals(this.getColor())){
                       topRight = x;
                    }
                    else{
                        topRight = x - 1;
                    }
                    flag = false;
                }
            }
            x++;
            y--;
        }
        //Top left
        flag = true;
        x = this.getX() - 1;
        y = this.getY() - 1;
        while(x >= 0 && y >= 0 && flag){
            for(Piece p1: handler.object){
                if(p1.getX() == x && p1.getY() == y){
                    if(!p1.getColor().equals(this.getColor())){
                       topLeft = x;
                    }
                    else{
                        topLeft = x + 1;
                    }
                    flag = false;
                }
            }
            x--;
            y--;
        }
        //Bottom right
        flag = true;
        x = this.getX() + 1;
        y = this.getY() + 1;
        while(x <= 7 && y <= 7 && flag){
            for(Piece p1: handler.object){
                if(p1.getX() == x && p1.getY() == y){
                    if(!p1.getColor().equals(this.getColor())){
                       bottomRight = x;
                    }
                    else{
                        bottomRight = x - 1;
                    }
                    flag = false;
                }
            }
            x++;
            y++;
        }
        //Bottom left
        flag = true;
        x = this.getX() - 1;
        y = this.getY() + 1;
        while(x >= 0 && y <= 7 && flag){
            for(Piece p1: handler.object){
                if(p1.getX() == x && p1.getY() == y){
                    if(!p1.getColor().equals(this.getColor())){
                        bottomLeft = x;
                        }
                    else{
                        bottomLeft = x + 1;
                    }
                    flag = false;
                }
            }
            x--;
            y++;
        }

        //Add moves to legMoves ArrayList

        //Top right
        int pX = this.getX() + 1;
        int pY = this.getY() - 1;
        while(pX <= topRight){
            legMoves.add(new ArrayList<Integer>());
            legMoves.get(k).add(pX);
            legMoves.get(k).add(pY);
            k++;
            pX++;
            pY--;
        }
        //Top left
        pX = this.getX() - 1;
        pY = this.getY() - 1;
        while(pX >= topLeft){
            legMoves.add(new ArrayList<Integer>());
            legMoves.get(k).add(pX);
            legMoves.get(k).add(pY);
            k++;
            pX--;
            pY--;
        }
        //Bottom right
        pX = this.getX() + 1;
        pY = this.getY() + 1;
        while(pX <= bottomRight){
            legMoves.add(new ArrayList<Integer>());
            legMoves.get(k).add(pX);
            legMoves.get(k).add(pY);
            k++;
            pX++;
            pY++;
        }
        //Bottom left
        pX = this.getX() - 1;
        pY = this.getY() + 1;
        while(pX >= bottomLeft){
            legMoves.add(new ArrayList<Integer>());
            legMoves.get(k).add(pX);
            legMoves.get(k).add(pY);
            k++;
            pX--;
            pY++;
        }
        return legMoves;
    }

    @Override
    public void render(Graphics g) {
        // TODO Auto-generated method stub
        g.drawImage(image, (int) (x * ch.getBlockWidth()) + 11, (int) (y * ch.getBlockHeight()) + 2, null);
    }

    @Override
    public void tick() {
        // TODO Auto-generated method stub
        
    }
    
}
