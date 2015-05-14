package data2;
import javalib.colors.*;
import javalib.worldimages.*;

public class Mob implements ScrollingObjects {
    
    public Player player;
    
    public Posn posn;
    
    public int mobSpeed;
    
    public int size = 50;
    
    public IColor red = new Red();
    
    public Mob(Posn posn, Player player, int mobSpeed) {
        this.posn = posn;
        this.player = player;
        this.mobSpeed = mobSpeed;
    }
    
    public boolean isGoneHuh() {
        return (posn.x + size/2 <= 0); 
    }
    
    public Mob move() {
        int xpos;
        int ypos;
        xpos = this.posn.x - mobSpeed;
        
        if (this.posn.x > player.posn.x && this.posn.y > player.posn.y) {
            ypos = this.posn.y - 40;  
        } else if (Math.abs(this.posn.x - player.posn.x) <= 300) {
            ypos = this.posn.y;
        } else
            ypos = this.posn.y + 40;
        
        posn = new Posn(xpos, ypos);
        return new Mob(posn,
                       player,
                       mobSpeed);
    }
    
    public boolean hitPlayer(Player player) {
              if (Math.abs(this.posn.y - player.posn.y) <= player.size/2 + this.size/2
            &&
           Math.abs(this.posn.x - player.posn.x) <= player.size/2 + this.size/2) {
                return true;
              } else return false;
    }
    
    public WorldImage drawObject() {
        return new RectangleImage(posn, size, size, red);
    }
    
    public String returnType() {
        return ("Mob");
    }
    
}
