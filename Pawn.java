import java.util.List;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import java.awt.Graphics;

public class Pawn extends Piece {

    Chess ch;
    private BufferedImage image;
    private int closestUp, closestDown;
    private Handler handler;
    private boolean firstMove = true;
    public Pawn(Handler handler, Chess ch, String player, int x, int y) {
        super(player, x, y);
        this.ch = ch;
        this.handler = handler;
        URL resource;
        if(player.equals("B")){
            resource = getClass().getResource("/resources/b_pawn.png");
        }
        else{
            resource = getClass().getResource("/resources/w_pawn.png");
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

    public void setFirstMove(boolean x){
        this.firstMove = x;
    }

    @Override
    public List<List<Integer>> legalMoves() {
        List<List<Integer>> legMoves = new ArrayList<List<Integer>>();
        legMoves.add(new ArrayList<Integer>());
        legMoves.get(0).add(this.getX());
        legMoves.get(0).add(this.getY());

        // List<List<Integer>> illegalMoves = new ArrayList<List<Integer>>();
        int x = this.getX();
        int y = this.getY()+1;
        int k = 1;

        closestUp = this.getY();
        closestDown = this.getY();

        boolean flag = false, flag2 = false, flag3 = false, flag4 = false;
        if(this.getColor().equals("B")){
            for(Piece p: handler.object){
                if(p.getX() == x + 1 && p.getY() == y && x <= 6){
                    legMoves.add(new ArrayList<Integer>());
                    legMoves.get(k).add(x + 1);
                    legMoves.get(k).add(y);
                    k++;
                }
                if(p.getX() == x - 1 && p.getY() == y && x >= 1){
                    legMoves.add(new ArrayList<Integer>());
                    legMoves.get(k).add(x - 1);
                    legMoves.get(k).add(y);
                    k++;
                }
                if(!firstMove){
                    if(p.getX() == x && p.getY() == y){
                        closestDown = this.getY();
                        flag = true;
                    }
                }
                else{
                    if(p.getX() == x && p.getY() == y){
                        closestDown = this.getY();
                        flag2 = true;
                    }
                    if(p.getX() == x && p.getY() == y + 1){
                        closestDown = this.getY() + 1;
                        flag2 = true;
                    }
                }
            }
            if(!flag && !firstMove){
                closestDown = this.getY() + 1;
            }
            if(!flag2 && firstMove){
                closestDown = this.getY() + 2;
            }
            int p = this.getY() + 1;
            //System.out.println(closestDown);
            while(p <= closestDown){
                legMoves.add(new ArrayList<Integer>());
                legMoves.get(k).add(this.getX());
                legMoves.get(k).add(p);
                p++;
                k++;
            }
            return legMoves;
        }

        else{
            closestUp = this.getY();
            closestDown = this.getY();
            y = this.getY() - 1;
            for(Piece p: handler.object){
                if(p.getX() == x + 1 && p.getY() == y && x <= 6){
                    legMoves.add(new ArrayList<Integer>());
                    legMoves.get(k).add(x + 1);
                    legMoves.get(k).add(y);
                    k++;
                }
                if(p.getX() == x - 1 && p.getY() == y && x >= 1){
                    legMoves.add(new ArrayList<Integer>());
                    legMoves.get(k).add(x - 1);
                    legMoves.get(k).add(y);
                    k++;
                }
                if(!firstMove){
                    if(p.getX() == x && p.getY() == y){
                        closestUp = this.getY();
                        flag3 = true;
                    }
                }
                else{
                    if(p.getX() == x && p.getY() == y){
                        closestUp = this.getY();
                        flag4 = true;
                    }
                    if(p.getX() == x && p.getY() == y - 1){
                        closestUp = this.getY() - 1;
                        flag4 = true;
                    }
                }
            }
            if(!flag3 && !firstMove){
                closestUp = this.getY() - 1;
            }
            if(!flag4 && firstMove){
                closestUp = this.getY() - 2;
            }
            int p = this.getY() - 1;
            //System.out.println(closestDown);
            while(p >= closestUp){
                legMoves.add(new ArrayList<Integer>());
                legMoves.get(k).add(this.getX());
                legMoves.get(k).add(p);
                p--;
                k++;
            }
            return legMoves;
        }
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