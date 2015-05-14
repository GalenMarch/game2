package data2;
import javalib.worldimages.*;

public interface ScrollingObjects {
    
    ScrollingObjects move();
    
    boolean isGoneHuh();
    
    boolean hitPlayer(Player player);
    
    WorldImage drawObject();
    
    String returnType();
    
}
