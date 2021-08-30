import java.util.List;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import java.awt.Graphics;

public class Rook extends Piece{

    Chess ch;
    private BufferedImage image;
    private Handler handler;
    private int closestUp = 0, closestDown = 7, closestLeft = 0, closestRight = 7;

    public Rook(Handler handler, Chess ch, String player, int x, int y) {
        super(player, x, y);
        this.ch = ch;
        this.handler = handler;
        URL resource;
        if(player.equals("B")){
            resource = getClass().getResource("/resources/b_rook.png");
        }
        else{
            resource = getClass().getResource("/resources/w_rook.png");
        }
        try {
            image = ImageIO.read(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isValidMove(int x2, int y2) {
        List<List<Integer>> legMoves = legalMoves();
        for(int i=0; i<legMoves.size(); i++){
            if(legMoves.get(i).get(0) == x2 && legMoves.get(i).get(1) == y2){
                return true;
            }
        }
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
        return legMoves;
    }

    @Override
    public void render(Graphics g) {
        // TODO Auto-generated method stub
        g.drawImage(image, (int) (x * ch.getBlockWidth()) + 10, (int) (y * ch.getBlockHeight()) + 2, null);
    }

    @Override
    public void tick() {
        // TODO Auto-generated method stub
        
    }
    
}
