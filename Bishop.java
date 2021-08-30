import java.util.List;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import java.awt.Graphics;


public class Bishop extends Piece {

    private BufferedImage image;
    private Chess ch;
    private int topRight, topLeft, bottomRight, bottomLeft;
    private Handler handler;
    public Bishop(Handler handler, Chess ch, String player, int x, int y) {
        super(player, x, y);
        this.ch=ch;
        this.handler = handler;
        URL resource;
        if(player.equals("B")){
            resource = getClass().getResource("/resources/b_bishop.png");
        }
        else{
            resource = getClass().getResource("/resources/w_bishop.png");
        }
        try {
            image = ImageIO.read(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isValidMove(int x2, int y2) {
        return false;
    }

    @Override
    public List<List<Integer>> legalMoves() {
        List<List<Integer>> legMoves = new ArrayList<List<Integer>>();
        legMoves.add(new ArrayList<Integer>());
        legMoves.get(0).add(this.getX());
        legMoves.get(0).add(this.getY());

        int x = this.getX() + 1;
        int y = this.getY() - 1;
        topRight = 7;
        topLeft = 0;
        bottomRight = 7;
        bottomLeft = 0;

        boolean flag = true;
        //Top right
        while(x <= 7 && y >= 0 && flag){
            for(Piece p: handler.object){
                if(p.getX() == x && p.getY() == y){
                    if(!p.getColor().equals(this.getColor())){
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
            for(Piece p: handler.object){
                if(p.getX() == x && p.getY() == y){
                    if(!p.getColor().equals(this.getColor())){
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
            for(Piece p: handler.object){
                if(p.getX() == x && p.getY() == y){
                    if(!p.getColor().equals(this.getColor())){
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
            for(Piece p: handler.object){
                if(p.getX() == x && p.getY() == y){
                    if(!p.getColor().equals(this.getColor())){
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
        int k = 1;

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
        g.drawImage(image, (int) (x * ch.getBlockWidth()) + 12, (int) (y * ch.getBlockHeight()) + 3, null);
    }

    @Override
    public void tick() {

    }
    
}
