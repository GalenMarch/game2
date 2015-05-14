package data2;
import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldimages.*;

public class HealthOrb implements ScrollingObjects {
    
    public int radius  = 25;
    
    public Posn posn;
    
    public int orbSpeed;
    
    public IColor color = new Red();
    
    public HealthOrb(int radius, int orbSpeed, Posn posn) {
        this.radius = radius;
        this.orbSpeed = orbSpeed;
        this.posn = posn;
    }
    
    public boolean isGoneHuh() {
        return (posn.x+radius <= 0);
    }
    
    public boolean hitPlayer(Player player) {
           if (Math.abs(this.posn.y - player.posn.y) <= player.size/2 + radius
            &&
           Math.abs(this.posn.x - player.posn.x) <= player.size/2 + radius) {
                return true;
              } else return false;
    }
    
    public HealthOrb move() {
        posn = new Posn((this.posn.x - orbSpeed), (this.posn.y));
        return new HealthOrb(
                         radius,
                         orbSpeed,
                         posn);
    }
    
    public WorldImage drawObject() {
        return new DiskImage(posn, radius, color);
    }
    
    public String returnType() {
        return ("HealthOrb");
    }
    
}
