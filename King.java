import java.util.List;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import java.awt.Graphics;

public class King extends Piece{

    Chess ch;
    private BufferedImage image;
    private Handler handler;
    public King(Handler handler, Chess ch, String player, int x, int y) {
        super(player, x, y);
        this.ch = ch;
        this.handler = handler;
        URL resource;
        if(player.equals("B")){
            resource = getClass().getResource("/resources/b_king.png");
        }
        else{
            resource = getClass().getResource("/resources/w_king.png");
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
        legMoves.add(new ArrayList<Integer>());
        legMoves.get(0).add(this.getX());
        legMoves.get(0).add(this.getY());

        boolean flag = true;
        int x = this.getX();
        int y = this.getY();
        int k = 1;
        //Top
        if(y >= 1){
            for(Piece p: handler.object){
                if(p.getX() == x && p.getY() == y - 1){
                    if(p.getColor().equals(this.getColor())){
                        flag = false;
                    }
                }
            }
            if(flag){
                legMoves.add(new ArrayList<Integer>());
                legMoves.get(k).add(x);
                legMoves.get(k).add(y - 1);
                k++;
            }
        }
        //Top right
        flag = true;
        if(x <= 6 && y >= 1){
            for(Piece p: handler.object){
                if(p.getX() == x + 1 && p.getY() == y - 1){
                    if(p.getColor().equals(this.getColor())){
                        flag = false;
                    }
                }
            }
            if(flag){
                legMoves.add(new ArrayList<Integer>());
                legMoves.get(k).add(x + 1);
                legMoves.get(k).add(y - 1);
                k++;
            }
        }
        //Right
        flag = true;
        if(x <= 6){
            for(Piece p: handler.object){
                if(p.getX() == x + 1 && p.getY() == y){
                    if(p.getColor().equals(this.getColor())){
                        flag = false;
                    }
                }
            }
            if(flag){
                legMoves.add(new ArrayList<Integer>());
                legMoves.get(k).add(x + 1);
                legMoves.get(k).add(y);
                k++;
            }
        }
        //Bottom Right
        flag = true;
        if(x <= 6 && y <= 6){
            for(Piece p: handler.object){
                if(p.getX() == x + 1 && p.getY() == y + 1){
                    if(p.getColor().equals(this.getColor())){
                        flag = false;
                    }
                }
            }
            if(flag){
                legMoves.add(new ArrayList<Integer>());
                legMoves.get(k).add(x + 1);
                legMoves.get(k).add(y + 1);
                k++;
            }
        }
        //Bottom
        flag = true;
        if(y <= 6){
            for(Piece p: handler.object){
                if(p.getX() == x && p.getY() == y + 1){
                    if(p.getColor().equals(this.getColor())){
                        flag = false;
                    }
                }
            }
            if(flag){
                legMoves.add(new ArrayList<Integer>());
                legMoves.get(k).add(x);
                legMoves.get(k).add(y + 1);
                k++;
            }
        }
        //Bottom left
        flag = true;
        if(x >= 1 && y <= 6){
            for(Piece p: handler.object){
                if(p.getX() == x - 1 && p.getY() == y + 1){
                    if(p.getColor().equals(this.getColor())){
                        flag = false;
                    }
                }
            }
            if(flag){
                legMoves.add(new ArrayList<Integer>());
                legMoves.get(k).add(x - 1);
                legMoves.get(k).add(y + 1);
                k++;
            }
        }
        //Left
        flag = true;
        if(x >= 1){
            for(Piece p: handler.object){
                if(p.getX() == x - 1 && p.getY() == y){
                    if(p.getColor().equals(this.getColor())){
                        flag = false;
                    }
                }
            }
            if(flag){
                legMoves.add(new ArrayList<Integer>());
                legMoves.get(k).add(x - 1);
                legMoves.get(k).add(y);
                k++;
            }
        }
        //Top left
        flag = true;
        if(x >= 1 && y >= 1){
            for(Piece p: handler.object){
                if(p.getX() == x - 1 && p.getY() == y - 1){
                    if(p.getColor().equals(this.getColor())){
                        flag = false;
                    }
                }
            }
            if(flag){
                legMoves.add(new ArrayList<Integer>());
                legMoves.get(k).add(x - 1);
                legMoves.get(k).add(y - 1);
                k++;
            }
        }
        return legMoves;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, (int) (x * ch.getBlockWidth()) + 10, (int) (y * ch.getBlockHeight()) + 2, null);

    }

    @Override
    public void tick() {
        // TODO Auto-generated method stub
        
    }
    
}
