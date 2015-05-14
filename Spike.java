package data2;
import javalib.colors.*;
import javalib.worldimages.*;

public class Spike implements ScrollingObjects{
    
    public int width,height; 
    
    public int spikeSpeed;
    
    public Posn posn;
    
    public IColor color = new Green();
    
    public Spike(int width, int height, int spikeSpeed, Posn posn) {
        this.width = width;
        this.height = height;
        this.spikeSpeed = spikeSpeed;
        this.posn = posn;
    }
    
    public Spike move() {
        posn = new Posn((this.posn.x - spikeSpeed), (this.posn.y));
        return new Spike(
                         width,
                         height,
                         spikeSpeed,
                         posn);
    }
    
    public boolean isGoneHuh() {
        return (posn.x + width/2 <= 0); 
    }
    
    public boolean hitPlayer(Player player) {
      if (Math.abs(this.posn.y - player.posn.y) <= player.size/2 + this.height/2
            &&
           Math.abs(this.posn.x - player.posn.x) <= player.size/2 + this.width/2) {
                return true;
              } else return false;
    }
    
    public WorldImage drawObject() {
        return new RectangleImage(this.posn, this.width, this.height, this.color);
    }
    
    public String returnType() {
        return ("Spike");
    }
    
}
