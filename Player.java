package data2;
import java.awt.Rectangle;
import javalib.colors.*;
import javalib.worldimages.*;

public class Player {
    
    // True = up, false = down
    public boolean upHuh;
    
    public Posn posn;
    
    public int size = 100;
    
    public int distanceToFall;
    
    public int boardHeight;
    
    public IColor yellow = new Yellow();
    public IColor red = new Red();
    
    public Player(Posn posn, boolean upHuh, int distanceToFall, int boardHeight) {
        this.posn = posn;
        this.upHuh = upHuh;
        this.distanceToFall = distanceToFall;
        this.boardHeight = boardHeight;
    }
    
    public Player toggleSpeed() {
        if (!this.onCeilingHuh() && !this.onFloorHuh(boardHeight)) {
            distanceToFall = distanceToFall + 12;
            return new Player(posn, upHuh, distanceToFall, boardHeight);
        } else return this;
    }
    
    public Player fallU() {
        if (upHuh == true && !this.onCeilingHuh()) {
            posn = new Posn(posn.x, posn.y - distanceToFall);
            return new Player(posn, upHuh, distanceToFall, boardHeight);
        } else if (upHuh == true && this.onCeilingHuh()) {
            posn = new Posn(posn.x, 0 + size/2);
            return new Player(posn, upHuh, distanceToFall, boardHeight);
        } else if (upHuh == false && !this.onFloorHuh(boardHeight)) {
            posn = new Posn(posn.x, posn.y + distanceToFall);
            return new Player(posn, upHuh, distanceToFall, boardHeight);
        } else if (upHuh == false && this.onFloorHuh(boardHeight)) {
            posn = new Posn(posn.x, boardHeight - size/2);
            return new Player(posn, upHuh, distanceToFall, boardHeight);
        } return this;
    }
        
    public Player fallO() {
        if (upHuh == true) {
            posn = new Posn(posn.x, posn.y - distanceToFall); 
            return new Player(posn, upHuh, distanceToFall, boardHeight);
        } else if (upHuh == false && !this.onFloorHuh(boardHeight)) {
            posn = new Posn(posn.x, posn.y + distanceToFall);
            return new Player(posn, upHuh, distanceToFall, boardHeight);
        } else if (upHuh == false && this.onFloorHuh(boardHeight)) {
            posn = new Posn(posn.x, boardHeight - size/2);
            return new Player(posn, upHuh, distanceToFall, boardHeight);
        } return this;
    }
        
    public boolean onFloorHuh(int boardHeight) {
        return (posn.y >= boardHeight - size/2);
    }
    
    public boolean onCeilingHuh() {
        return (posn.y <= size/2);
    }
    
    private WorldImage feet() {
        int ypos;
        if (upHuh) {
            ypos = posn.y - 40;
        } else ypos = posn.y + 40;
        return new RectangleImage(
                                  new Posn(posn.x,ypos),
                                  size,
                                  20,
                                  new Red());
    }
    
    public WorldImage drawPlayer() {
        RectangleImage player = new RectangleImage(this.posn, this.size, this.size, this.yellow);
        return new OverlayImages(player, feet());
    }
}
