package data2;
import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldimages.*;

import java.util.*;

public class OverWorld extends GWorld{
    
    public int WINDOWHEIGHT = 800;
    public int WINDOWWIDTH = 1500;
    
    public LinkedList<ScrollingObjects> objectList = new LinkedList();
    
    public int objectSpeed = 50;
    
    public int score;
    
    public int treeProbability = 6;
    public int mountainProbability = 16;
    
    public boolean probability(int probability) {
        double randomNum = Math.random();
        return (randomNum < 1.0/probability);
    }
    
    public Tree createTree() {
        int xpos = WINDOWWIDTH + 40;
        int ypos = WINDOWHEIGHT - 40;
        Posn posn = new Posn(xpos,ypos);
        return new Tree(objectSpeed,posn);
    }
    
    public Mountain createMountain() {
        int width = (int) (Math.random()*100) + 100;
        int height = (int) (Math.random()*300) + 300;
        int xpos = WINDOWWIDTH + width;
        int ypos = WINDOWHEIGHT - height/2;
        return new Mountain(objectSpeed,
                            new Posn(xpos,ypos),
                            width,
                            height,
                            WINDOWHEIGHT);
    }
       
    public WorldImage drawObjectList(LinkedList<ScrollingObjects> list, int counter) {
        if (list.isEmpty()) {
            return screen();
        } else if (counter == -1) {
            return screen();
        } else 
            return (new OverlayImages(
                                      drawObjectList(list, counter - 1),
                                      list.get(counter).drawObject()));  
    }  
       
    public OverWorld(int score) {
        Tree newTree = createTree();
        Mountain newMountain = createMountain();
        objectList.addFirst(newTree);
        objectList.addFirst(newMountain);
        this.score = score;
    }
    
    public OverWorld(LinkedList objectList, int score) {
        this.objectList = objectList;
        this.score = score;
    }
    
    public World onTick() {
        
        LinkedList<ScrollingObjects> temp = objectList;
        int lengthTree = temp.size();
        for (int i = 0; i < lengthTree; i++) {
            if (temp.get(i).isGoneHuh()) {
                temp.remove(temp.get(i));
                break;
            } else temp.get(i).move();
        }
        if (probability(treeProbability)) {
            Tree newTree = createTree();
            temp.addFirst(newTree);    
        }
        if (probability(mountainProbability)) {
            Mountain newMountain = createMountain();
            temp.addFirst(newMountain);
        }
        
        objectList = temp;
        
        return new OverWorld(objectList,score);
    }
    
    public WorldImage screen() {
        return new RectangleImage(
                                  new Posn(WINDOWWIDTH/2,WINDOWHEIGHT/2),
                                  WINDOWWIDTH,
                                  WINDOWHEIGHT,
                                  new Blue());
    }
    
    public WorldImage makeImage() {
        return drawObjectList(objectList,objectList.size()-1);
                                
    }
    
    public LinkedList returnObjectList() {
        return objectList;
    }
    
}
