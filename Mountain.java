package data2;
import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldimages.*;

public class Mountain implements ScrollingObjects{
    
    public int WINDOWHEIGHT;
    
    public int width, height;
    
    public int speed;
    public Posn posn;
    
    public Mountain(int speed, Posn posn, int width, int height, int WINDOWHEIGHT) {
        this.speed = speed;
        this.posn = posn;
        this.width = width;
        this.height = height;
        this.WINDOWHEIGHT = WINDOWHEIGHT;
    }
        
    public Mountain move() {
        posn = new Posn((this.posn.x - speed), (this.posn.y));
        return new Mountain(
                         speed,
                         posn,
                         width,
                         height,
                         WINDOWHEIGHT);
    }
    
    public boolean hitPlayer(Player player) {
       if (Math.abs(this.posn.y - player.posn.y) <= player.size/2 + this.height/2 
            &&
           Math.abs(this.posn.x - player.posn.x) <= player.size/2 + this.width/3) {
                return true;
              } else return false;
    }
    
    public boolean isGoneHuh() {
        return (posn.x + width/2 <= 0); 
    }
    
    public WorldImage drawObject() {
        return new TriangleImage(
                                 new Posn(posn.x - width, WINDOWHEIGHT),
                                 new Posn(posn.x, WINDOWHEIGHT- height),
                                 new Posn(posn.x + width, WINDOWHEIGHT),
                                 new Black());
    } 
    
    public String returnType() {
        return ("Mountain");
    }
    
}
