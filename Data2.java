package data2;
import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldimages.*;

import tester.*;

import java.util.*;

// Gravity Blockz - Game Manual
//
// Gravity Blockz is a survival game - points are awarded on each tick the 
// player remains alive and the goal is to acquire as many points as possible.
// 
// Different modes of play: Gravity Blockz has two levels of play - an OverWorld
// and an UnderWorld.  The player begins in the UnderWorld, where green blocks 
// are randomly generated and move across the floor and the ceiling of the 
// playing field.  By pressing the spacebar, the player can alter the direction
// of the gravitational pull of the UnderWorld and navigate their way through
// the obstacles.  Portals, medium-sized blue blocks, are also randomly 
// generated in the UnderWorld, as well as the OverWorld.  If the player hits 
// one, they are transported to the other level of play.  The OverWorld is
// designed to be harder than the UnderWorld.  There is no ceiling, meaning
// if the player goes beyond the upper boundaries of the playing field, they 
// die.  Because of the added difficulties, the player is awarded 20 points for
// each tick they remain alive in the OverWorld.
//
// Mobs: small red squares  are randomly spawned and jump at the player.  If 
// the player hits one of them, they lose health.
// 
// Player Attributes: If a player does hit one of the blocks, they lose .2
// health point for each tick that they are in contact with it.  The player
// starts out the game with 0 health points, meaning if they hit a block early
// on in the game, the game is over.  However, the game randomly generates red
// "health orbs", which, if hit by the player, give the player 1 health point.
// The player can have a maximum of ten health points and  they give the player 
// protection from collisions with obstacles in the world or hits from the
// projectiles of the mobs - instead of dying, the player simply loses  
// health points when such an event occurs.

public class Data2 extends World {
    
    public final static int WINDOWWIDTH = 1500;
    public final static int WINDOWHEIGHT = 800;
    
    public LinkedList<ScrollingObjects> objectList = new LinkedList();
    
    boolean underOrOver;    
    GWorld theOver;
    GWorld theUnder;
    
    public Player player;
    public boolean upHuh;
    public int distanceToFall = 10;
     
    public int objectSpeed = 50;
    
    public int score;
    
    public double health;
    
    public int healthOrbProbability = 100;
    public int portalProbability = 120;
    public int mobProbability = 25;
    
    public int orbRadius = 25;
    
    public boolean gameOver;

    
    public boolean probability(int probability) {
        double randomNum = Math.random();
        return (randomNum < 1.0/probability);
    }
    
    public HealthOrb createHealthOrb() {
        int ypos = (int) (Math.random() * (WINDOWHEIGHT - orbRadius*2) + orbRadius);
        Posn posn = new Posn(
                             WINDOWWIDTH + orbRadius,
                             ypos);
        return new HealthOrb(
                             orbRadius,
                             objectSpeed,
                             posn);
    }
        
    public boolean topOrBottom() {
        Random rand = new Random();
        int coin = rand.nextInt(2);
        if (coin == 0) {
            return true;
        } else return false;
    }
    
    public Portal createPortal() {
        int width = 300;
        int height = 100;
        boolean tOrB = topOrBottom();
        int ypos; 
        if (tOrB) {
            ypos = WINDOWHEIGHT - height/2;
        } else ypos = height/2;
            return new Portal(
                             width,
                             height,
                             objectSpeed,
                             new Posn(WINDOWWIDTH+width, ypos));
    }
    
    public Mob createMob() {
        int xpos = WINDOWWIDTH + 25;
        int ypos = WINDOWHEIGHT - 25;
        Posn posn = new Posn(xpos, ypos);
        return new Mob(posn,
                       player,
                       objectSpeed);
    }
    
        
    public WorldImage drawObjectList(LinkedList<ScrollingObjects> list, int counter) {
        World currentWorld = underOrOver ? theUnder : theOver;
        if (list.isEmpty()) {
            return currentWorld.makeImage();
        } else if (counter == -1) {  
            return currentWorld.makeImage();
        } else 
            return (new OverlayImages(
                                      drawObjectList(list, counter - 1),
                                      list.get(counter).drawObject()));                       
    }
    
    public Data2(GWorld theOver, GWorld theUnder) { 
        this.player = new Player(new Posn(WINDOWWIDTH/2, WINDOWHEIGHT/2),
                                 upHuh,
                                 distanceToFall,
                                 WINDOWHEIGHT);
        this.theOver = theOver;
        this.theUnder = theUnder;
        this.upHuh = false;
        this.score = 0;
        this.underOrOver = true;
        HealthOrb orb = createHealthOrb();
        this.objectList.addFirst(orb);
        this.health = 0.0;
        this.gameOver = false;
    }
 
    
    public Data2(Player player, boolean upHuh, GWorld theOver, GWorld theUnder, boolean underOrOver, LinkedList objectList, int score, double health, boolean gameOver) {
          this.player = player;
          this.upHuh = upHuh;
          this.theOver = theOver;
          this.theUnder = theUnder;
          this.underOrOver = underOrOver;
          this.objectList = objectList;
          this.score = score;
          this.health = health;
          this.gameOver = gameOver;
    }
 
    
    public World onTick() {
        
     if (underOrOver) {
         player = player.fallU().toggleSpeed();
     } else player = player.fallO().toggleSpeed();
        
     World theNewOver = underOrOver ? theOver : theOver.onTick();
     World theNewUnder = underOrOver ? theUnder.onTick() : theUnder; 
     GWorld theNewerOver = (GWorld) theNewOver;
     GWorld theNewerUnder = (GWorld) theNewUnder;
     
     
     LinkedList<ScrollingObjects> temp = objectList;
        int length = temp.size();
        for (int i = 0; i < length; i++) {
            if (temp.get(i).isGoneHuh()) {
                temp.remove(temp.get(i));
                break;
            } else if (temp.get(i).hitPlayer(player)) {
                if (temp.get(i).returnType().equals("Portal")) {
                    underOrOver = !underOrOver;
                    temp.remove(temp.get(i));
                    break;
                } else if (temp.get(i).returnType().equals("HealthOrb") && health < 11) {
                    temp.remove(temp.get(i));
                    health++;
                    break;
                }  else if (temp.get(i).returnType().equals("Mob")) {
                    health = health - 1.0;
                    temp.remove(temp.get(i));
                    break;
                }
            } else temp.get(i).move();
        }
        
        if (probability(portalProbability)) {
            Portal newPortal = createPortal();
            temp.addFirst(newPortal); 
        }
        if (probability(healthOrbProbability)) {
            HealthOrb newOrb = createHealthOrb();
            temp.addFirst(newOrb);
        }
        if (probability(mobProbability)) {
            Mob newMob = createMob();
            temp.addFirst(newMob);
        }
        
         objectList = temp;
        
        if (underOrOver) {
            score = score+10;
        } else score = score+20;
        
        LinkedList<ScrollingObjects> listy;
        if (underOrOver) {
            listy = theNewerUnder.returnObjectList();
            int listylength = listy.size();
            for (int i = 0; i < listylength; i++) {
                if (listy.get(i).hitPlayer(player)) {
                    health = health - 0.20;
                    break;
                }
            }
        } else listy = theNewerOver.returnObjectList();
          int listyLength = listy.size();
          for (int i = 0; i < listyLength; i++) {
              if (listy.get(i).hitPlayer(player)) {
                    health = health - 0.20;
                    break;
          }
          }
        
        if (health <= -0.20) {
            gameOver = true;
        } else if (!underOrOver && player.posn.y <= 0) {
            gameOver = true;
        } else gameOver = false;
        
      return new Data2(player, upHuh, theNewerOver, theNewerUnder, underOrOver, objectList, score, health, gameOver);
    }
    
    
    
    public World onKeyEvent(String toggle) {
        if (toggle.equals(" ")) {
           upHuh = !upHuh;
           player = new Player(player.posn, upHuh, distanceToFall, WINDOWHEIGHT);
           return new Data2(player, upHuh, theOver, theUnder, underOrOver, objectList, score, health, gameOver);
        } else return this;
    }
    
    // Private methods for displaying player's stats
    
    private TextImage scoreBox() {
        return new TextImage(
                             new Posn(80,40), 
                             "SCORE:            " + score,
                             new Black());
    }
    
    private TextImage healthBox() {
        return new TextImage(
                             new Posn(45,75),
                             "HEALTH: ",
                             new Black());
    }
    
    private WorldImage healthBar() {
        return new RectangleImage(
                                  new Posn(235,75),
                                  (int) health*26,
                                  15,
                                  new Red());
    }
    
    private WorldImage statsBoxBackground() {
        return new RectangleImage(
                                  new Posn(200,50),
                                  400,
                                  100,
                                  new White());
    }
    
    private WorldImage statsBox() {
        return new OverlayImages(statsBoxBackground(),
               new OverlayImages(healthBar(), 
               new OverlayImages(healthBox(), scoreBox())));
                                 
    }
    
    
    public WorldImage makeImage() {
        return new OverlayImages(drawObjectList(objectList,objectList.size()-1), 
               new OverlayImages(statsBox(), player.drawPlayer())); 
                                                            
    }
    
        
    public WorldEnd worldEnds() {
        if (gameOver) {
            return new WorldEnd(true, new OverlayImages(this.makeImage(),
                    new TextImage(new Posn(WINDOWWIDTH / 2, WINDOWHEIGHT / 2),
                            ("That's rough, buddy. Final Score: " + this.score),
                            40,
                            new White())));
        } else {
            return new WorldEnd(false, this.makeImage());
        }
    }

    
    public static void main(String[] args) {
        Tester.runReport(new Testing(), false, false);
        Data2 theWorld = new Data2(new OverWorld(0), new UnderWorld(0)); 
        theWorld.bigBang(WINDOWWIDTH, WINDOWHEIGHT,0.12);
    } 
}
