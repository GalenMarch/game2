package data2;
import javalib.colors.*;
import javalib.worldimages.*;

public class Tree implements ScrollingObjects { 
    
    public int width = 20;
    public int height = 80;
    
    public int speed;
   
    public Posn posn;
    
    public Tree(int speed, Posn posn) {
        this.speed = speed;
        this.posn = posn;
    }
    
        public Tree move() {
        posn = new Posn((this.posn.x - speed), (this.posn.y));
        return new Tree(speed,
                        posn);
    }
    
    public boolean isGoneHuh() {
        return (posn.x + width/2 <= 0); 
    }
    
    public boolean hitPlayer(Player player) {
      if (Math.abs((this.posn.y - height/2) - player.posn.y) <= player.size/2 + leavesRadius
            &&
           Math.abs(this.posn.x - player.posn.x) <= player.size/2 + leavesRadius) {
                return true;
              } else return false;
    }
    
    public int leavesRadius = 40;
    
    private WorldImage leaves() {
        return new DiskImage(
                             new Posn(posn.x, posn.y - height),
                             leavesRadius,
                             new Green());
    }
    
    private WorldImage trunk() {
        return new RectangleImage(
                                  new Posn(posn.x, posn.y),
                                  width,
                                  height,
                                  new Green());
    }
    
    public WorldImage drawObject() {
        return new OverlayImages(trunk(), leaves());
    }
    
    public String returnType() {
        return ("Tree");
    }
    
}
