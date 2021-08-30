import java.util.List;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import java.awt.Graphics;

public class Knight extends Piece {

    private BufferedImage image;
    private Chess ch;
    private Handler handler;
    public Knight(Handler handler, Chess ch, String player, int x, int y) {
        super(player, x, y);
        this.ch = ch;
        this.handler = handler;
        URL resource;
        if(player.equals("B")){
            resource = getClass().getResource("/resources/b_knight.png");
        }
        else{
            resource = getClass().getResource("/resources/w_knight.png");
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

        int x = this.getX();
        int y = this.getY();
        int k = 1;
        boolean flag = true;
        //Top right
        if(x <= 6 && y >= 2){
            for(Piece p: handler.object){
                if(p.getX() == x + 1 && p.getY() == y - 2){
                    if(p.getColor().equals(this.getColor())){
                        flag = false;
                    }
                }
            }
            if(flag){
                legMoves.add(new ArrayList<Integer>());
                legMoves.get(k).add(x + 1);
                legMoves.get(k).add(y - 2);
                k++;
            }
        }
        //Top left
        flag = true;
        if(x >= 1 && y >= 2){
            for(Piece p: handler.object){
                if(p.getX() == x - 1 && p.getY() == y - 2){
                    if(p.getColor().equals(this.getColor())){
                        flag = false;
                    }
                }
            }
            if(flag){
                legMoves.add(new ArrayList<Integer>());
                legMoves.get(k).add(x - 1);
                legMoves.get(k).add(y - 2);
                k++;
            }
        }
        //Bottom right
        flag = true;
        if(x <= 6 && y <= 5){
            for(Piece p: handler.object){
                if(p.getX() == x + 1 && p.getY() == y + 2){
                    if(p.getColor().equals(this.getColor())){
                        flag = false;
                    }
                }
            }
            if(flag){
                legMoves.add(new ArrayList<Integer>());
                legMoves.get(k).add(x + 1);
                legMoves.get(k).add(y + 2);
                k++;
            }
        }
        //Bottom left
        flag = true;
        if(x >= 1 && y <= 5){
            for(Piece p: handler.object){
                if(p.getX() == x - 1 && p.getY() == y + 2){
                    if(p.getColor().equals(this.getColor())){
                        flag = false;
                    }
                }
            }
            if(flag){
                legMoves.add(new ArrayList<Integer>());
                legMoves.get(k).add(x - 1);
                legMoves.get(k).add(y + 2);
                k++;
            }
        }
        //Bottom right right
        flag = true;
        if(x <= 5 && y <= 6){
            for(Piece p: handler.object){
                if(p.getX() == x + 2 && p.getY() == y + 1){
                    if(p.getColor().equals(this.getColor())){
                        flag = false;
                    }
                }
            }
            if(flag){
                legMoves.add(new ArrayList<Integer>());
                legMoves.get(k).add(x + 2);
                legMoves.get(k).add(y + 1);
                k++;
            }
        }
        //Bottom left left
        flag = true;
        if(x >= 2 && y <= 6){
            for(Piece p: handler.object){
                if(p.getX() == x - 2 && p.getY() == y + 1){
                    if(p.getColor().equals(this.getColor())){
                        flag = false;
                    }
                }
            }
            if(flag){
                legMoves.add(new ArrayList<Integer>());
                legMoves.get(k).add(x - 2);
                legMoves.get(k).add(y + 1);
                k++;
            }
        }
        //Top left left
        flag = true;
        if(x >= 2 && y >= 1){
            for(Piece p: handler.object){
                if(p.getX() == x - 2 && p.getY() == y - 1){
                    if(p.getColor().equals(this.getColor())){
                        flag = false;
                    }
                }
            }
            if(flag){
                legMoves.add(new ArrayList<Integer>());
                legMoves.get(k).add(x - 2);
                legMoves.get(k).add(y - 1);
                k++;
            }
        }
        //Top right right
        flag = true;
        if(x <= 5 && y >= 1){
            for(Piece p: handler.object){
                if(p.getX() == x + 2 && p.getY() == y - 1){
                    if(p.getColor().equals(this.getColor())){
                        flag = false;
                    }
                }
            }
            if(flag){
                legMoves.add(new ArrayList<Integer>());
                legMoves.get(k).add(x + 2);
                legMoves.get(k).add(y - 1);
                k++;
            }
        }
        return legMoves;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, (int) (x * ch.getBlockWidth()) + 10, (int) (y * ch.getBlockHeight()) + 3, null);        
    }

    @Override
    public void tick() {
        // TODO Auto-generated method stub
        
    }
    
}
