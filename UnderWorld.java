package data2;
import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldimages.*;

import java.util.*;

public class UnderWorld extends GWorld {
    
    public final static int WINDOWWIDTH = 1500;
    public final static int WINDOWHEIGHT = 800;
    
    public LinkedList<Spike> spikeList = new LinkedList();
    
    public int small = 100;
    public int medium = 190;
    public int large = 250;
    
    public int objectSpeed = 50;
    
    public double spikeProbability = 12;
    
    public int score;
    
    public boolean spikeProbability() {
        double randomNum = Math.random();
        return (randomNum < 1.0/spikeProbability);
    }
    
    public boolean topOrBottom() {
        Random rand = new Random();
        int coin = rand.nextInt(2);
        if (coin == 0) {
            return true;
        } else return false;
    }
    
    public int selectHeight() {
        int num = (int) (Math.random()*3);
        if (num == 0) {
            return small;
        } else if (num == 1) {
            return medium;
        } else return large;
    }
    
    public Spike createSpike() {
        int width = (int) (Math.random()*100+300);
        int height = selectHeight();
        boolean tOrB = topOrBottom();
        int ypos; 
        if (tOrB) {
            ypos = WINDOWHEIGHT - height/2;
        } else ypos = height/2;
            return new Spike(
                             width,
                             height,
                             objectSpeed,
                             new Posn(WINDOWWIDTH+width, ypos));
    }
    
    public UnderWorld(int score) {
        Spike newSpike = createSpike();
        spikeList.addFirst(newSpike);
        this.score = score;
    }
    
    
    public UnderWorld(LinkedList spikeList, int score) {
        this.spikeList = spikeList;
        this.score = score;
    }
    
    public World onTick() { 
        
        LinkedList<Spike> temp = spikeList;
        int length = temp.size();
        for (int i = 0; i < length; i++) {
            if (temp.get(i).isGoneHuh()) {
                temp.remove(temp.get(i));
                break; 
            } else temp.get(i).move();
        }
        if (spikeProbability()) {
            Spike newSpike = createSpike();
            temp.addFirst(newSpike);
        }
        spikeList = temp;

        return new UnderWorld(spikeList, score);
    } 
    
    public WorldImage drawSpikeList(LinkedList<Spike> list, int counter) {
        if (list.isEmpty()) {
            return screen();
        } else if (counter == -1) {
            return screen();
        } else 
            return (new OverlayImages(
                                      drawSpikeList(list, counter - 1),
                                      list.get(counter).drawObject()));  
    }
        
    public WorldImage screen() {
        return new RectangleImage(
        new Posn(WINDOWWIDTH/2,WINDOWHEIGHT/2),
        WINDOWWIDTH,
        WINDOWHEIGHT,        
        new Black());    
    } 
    
    public WorldImage makeImage() {
       return drawSpikeList(spikeList, spikeList.size()-1);
                                                                                     
    }
    
    public LinkedList returnObjectList() {
        return spikeList;
    }
    
}
