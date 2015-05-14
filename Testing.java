package data2;
import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldimages.*;

import java.util.*;

import tester.*;  

public class Testing {
    
    public double health;
    
    public int score;
    public int WINDOWWIDTH = 1500;
    public int WINDOWHEIGHT = 800;
    public int distanceToFall = 10;
    public int objectSpeed = 50;
    public int healthOrbProbability = 100;
    public int portalProbability = 120;
    public int mobProbability = 25;
    public int orbRadius = 25;
    
    public boolean upHuh;
    public boolean gameOver;
    
    public LinkedList objectList = new LinkedList();
    
    
    Player playerUp = new Player(new Posn(WINDOWWIDTH/2,WINDOWHEIGHT/2), 
                        true, 
                        distanceToFall,
                        WINDOWHEIGHT);
    
    public boolean testPlayerMovementUp(Tester t) {
        Player testy = new Player(new Posn(WINDOWWIDTH/2, WINDOWHEIGHT/2 - distanceToFall),
                                     true,
                                     distanceToFall,
                                     WINDOWHEIGHT);
           return t.checkExpect(playerUp.fallU(),
                                testy);
        
        }
    
     Player playerDown = new Player(new Posn(WINDOWWIDTH/2,WINDOWHEIGHT/2), 
                            false, 
                            distanceToFall,
                            WINDOWHEIGHT);
    
    public boolean testPlayerMovementDown(Tester t) {
        Player testy = new Player(new Posn(WINDOWWIDTH/2, WINDOWHEIGHT/2 + distanceToFall),
                                     false,
                                     distanceToFall,
                                     WINDOWHEIGHT);
           return t.checkExpect(playerDown.fallU(),
                                testy);
        
        }
    
    public boolean testToggleSpeed(Tester t) {
        return t.checkExpect(playerUp.toggleSpeed().distanceToFall,
                             distanceToFall + 12);
            
        }
    
    Player playerPastTop = new Player(new Posn(WINDOWWIDTH/2, -10), 
                                    true, 
                                    distanceToFall,
                                    WINDOWHEIGHT);
    
    Player playerAtTop = new Player(new Posn(WINDOWWIDTH/2, 50), 
                                    true, 
                                    distanceToFall,
                                    WINDOWHEIGHT); 
    
    Player playerPastBottom = new Player(new Posn(WINDOWWIDTH/2, WINDOWHEIGHT + 10),
                                         false,
                                         distanceToFall,
                                         WINDOWHEIGHT);
    
    Player playerAtBottom =  new Player(new Posn(WINDOWWIDTH/2, WINDOWHEIGHT - 50),
                                         false,
                                         distanceToFall,
                                         WINDOWHEIGHT);

    
    public boolean testPlayerMovementEdge(Tester t) {
        return t.checkExpect(playerPastTop.fallU(),
                             playerAtTop) &&
               t.checkExpect(playerPastBottom.fallU(),
                             playerAtBottom);
    }
    
    Spike spike = new Spike(100, 100, objectSpeed, new Posn(WINDOWWIDTH/2, WINDOWHEIGHT/2));
    Spike movedSpike = new Spike(100, 100, objectSpeed, new Posn(WINDOWWIDTH/2 - objectSpeed, WINDOWHEIGHT/2));
    Spike goneSpike = new Spike(100, 100, objectSpeed, new Posn(-50, WINDOWHEIGHT/2));
    
    HealthOrb orb = new HealthOrb(orbRadius, objectSpeed, new Posn(WINDOWWIDTH/2, WINDOWHEIGHT/2));
    HealthOrb movedOrb = new HealthOrb(orbRadius, objectSpeed, new Posn(WINDOWWIDTH/2 - objectSpeed, WINDOWHEIGHT/2));
    HealthOrb goneOrb = new HealthOrb(orbRadius, objectSpeed, new Posn(-25, WINDOWHEIGHT/2));
    
    Portal portal = new Portal(100, 100, objectSpeed, new Posn(WINDOWWIDTH/2, WINDOWHEIGHT/2));
    Portal movedPortal = new Portal(100, 100, objectSpeed, new Posn(WINDOWWIDTH/2 - objectSpeed, WINDOWHEIGHT/2));
    Portal gonePortal = new Portal(100, 100, objectSpeed, new Posn(-50, WINDOWHEIGHT/2));
    
    Tree tree = new Tree(objectSpeed, new Posn(WINDOWWIDTH/2, WINDOWHEIGHT/2));
    Tree movedTree = new Tree(objectSpeed, new Posn(WINDOWWIDTH/2 - objectSpeed, WINDOWHEIGHT/2));
    Tree goneTree = new Tree(objectSpeed, new Posn(-10, WINDOWHEIGHT/2));
    
    Mountain mountain = new Mountain(objectSpeed, new Posn(WINDOWWIDTH/2, WINDOWHEIGHT/2), 100, 100, WINDOWHEIGHT);
    Mountain movedMountain = new Mountain(objectSpeed, new Posn(WINDOWWIDTH/2 - objectSpeed, WINDOWHEIGHT/2), 100, 100, WINDOWHEIGHT);
    Mountain goneMountain = new Mountain(objectSpeed, new Posn(-50, WINDOWHEIGHT/2), 100, 100, WINDOWHEIGHT);
    
    public boolean testScrollingObjectsMove(Tester t) {
        return t.checkExpect(spike.move(), movedSpike) &&
               t.checkExpect(spike.isGoneHuh(), false) &&
               t.checkExpect(goneSpike.isGoneHuh(), true) 
               &&
               t.checkExpect(orb.move(),movedOrb) &&
               t.checkExpect(orb.isGoneHuh(), false) &&
               t.checkExpect(goneOrb.isGoneHuh(), true) 
               &&
               t.checkExpect(portal.move(), movedPortal) &&
               t.checkExpect(portal.isGoneHuh(), false) &&
               t.checkExpect(gonePortal.isGoneHuh(), true)
               &&
               t.checkExpect(tree.move(), movedTree) &&
               t.checkExpect(tree.isGoneHuh(), false) &&
               t.checkExpect(goneTree.isGoneHuh(), true)
               &&
               t.checkExpect(mountain.move(), movedMountain) &&
               t.checkExpect(mountain.isGoneHuh(), false) &&
               t.checkExpect(goneMountain.isGoneHuh(), true);
               
    }
    
        
    Player player = new Player(new Posn(WINDOWWIDTH/2,WINDOWHEIGHT/2), 
                        true, 
                        distanceToFall,
                        WINDOWHEIGHT); 
    
    Mob cornerMob = new Mob(new Posn(WINDOWWIDTH, WINDOWHEIGHT), player, objectSpeed);
    Mob movedCornerMob = new Mob(new Posn(WINDOWWIDTH - objectSpeed, WINDOWHEIGHT - 40), player, objectSpeed);
    Mob goneMob = new Mob(new Posn(-25, WINDOWHEIGHT/2), player, objectSpeed);
    Mob centerMob = new Mob(new Posn(WINDOWWIDTH/2, WINDOWHEIGHT/2), player, objectSpeed);
    Mob movedCenterMob = new Mob(new Posn(WINDOWWIDTH/2 - objectSpeed, WINDOWHEIGHT/2), player, objectSpeed);
    Mob pastMob = new Mob(new Posn(WINDOWWIDTH/2 - 301, WINDOWHEIGHT/2), player, objectSpeed);
    Mob movedPastMob = new Mob(new Posn(WINDOWWIDTH/2 - 301 - objectSpeed, WINDOWHEIGHT/2 + 40), player, objectSpeed);
    
    
    public boolean testMobMove(Tester t) {
        return t.checkExpect(cornerMob.move(), movedCornerMob) &&
               t.checkExpect(cornerMob.isGoneHuh(), false) &&
               t.checkExpect(goneMob.isGoneHuh(), true) &&
               t.checkExpect(centerMob.move(), movedCenterMob) &&
               t.checkExpect(pastMob.move(), movedPastMob); 
    }
    
    public boolean testScrollingObjectsHitPlayer(Tester t) {
        return t.checkExpect(spike.hitPlayer(player), true) &&
               t.checkExpect(orb.hitPlayer(player), true) &&
               t.checkExpect(portal.hitPlayer(player), true) &&
               t.checkExpect(tree.hitPlayer(player), true) &&
               t.checkExpect(mountain.hitPlayer(player), true) &&
               t.checkExpect(centerMob.hitPlayer(player), true);
    }
    
        
    Spike playerEdgeSpike = new Spike(100, 100, objectSpeed, new Posn(WINDOWWIDTH/2 + 50, WINDOWHEIGHT/2));
    HealthOrb playerEdgeOrb = new HealthOrb(orbRadius, objectSpeed, new Posn(WINDOWWIDTH/2 + 50, WINDOWHEIGHT/2));
    Portal playerEdgePortal = new Portal(100, 100, objectSpeed, new Posn(WINDOWWIDTH/2 + 50, WINDOWHEIGHT/2));
    Tree playerEdgeTree = new Tree(objectSpeed, new Posn(WINDOWWIDTH/2, WINDOWHEIGHT/2 + 50));
    Mountain playerEdgeMountain = new Mountain(objectSpeed, new Posn(WINDOWWIDTH/2 + 50, WINDOWHEIGHT/2), 100, 100, WINDOWHEIGHT);
    Mob playerEdgeMob = new Mob(new Posn(WINDOWWIDTH/2 + 50, WINDOWHEIGHT/2), player, objectSpeed);
    
    public boolean moreHitTests(Tester t) {
        return t.checkExpect(playerEdgeSpike.hitPlayer(player), true) &&
               t.checkExpect(playerEdgeOrb.hitPlayer(player), true) &&
               t.checkExpect(playerEdgePortal.hitPlayer(player), true) &&
               t.checkExpect(playerEdgeTree.hitPlayer(player), true) &&
               t.checkExpect(playerEdgeMountain.hitPlayer(player), true) &&
               t.checkExpect(playerEdgeMob.hitPlayer(player), true);
    }
    
         
    Player overPlayer = new Player(new Posn(WINDOWWIDTH/2,WINDOWHEIGHT/2), 
                        true, 
                        distanceToFall,
                        WINDOWHEIGHT);
   
    Player playerKey = new Player(new Posn(WINDOWWIDTH/2,WINDOWHEIGHT/2), 
                        false, 
                        distanceToFall,
                        WINDOWHEIGHT); 
        
    GWorld theOver = new OverWorld(objectList, 10);
    GWorld theUnder = new UnderWorld(objectList, 10);  
    
    Data2 upWorld = new Data2(overPlayer, true, theOver, theUnder, true, objectList, 10, 1, false);
    Data2 downWorld = new Data2(playerKey, false, theOver, theUnder, true, objectList, 10, 1, false);  
    
    public boolean testOnKey(Tester t) {
        return t.checkExpect(upWorld.onKeyEvent(" "),
                             downWorld);
    }
    
    Player playerAtEdge = new Player(new Posn(WINDOWWIDTH/2,1), 
                          true, 
                          distanceToFall,
                          WINDOWHEIGHT); 
    
    Data2 edgeWorld = new Data2(playerAtEdge, true, theOver, theUnder, false, objectList, 10, 1, false);
    Data2 deadWorld = new Data2(playerAtEdge, true, theOver, theUnder, false, objectList, 30, 1, true);
    
    public boolean testOverWorldEdge(Tester t) {
        return t.checkExpect(edgeWorld.onTick(),
                             deadWorld);
    }
    
    LinkedList<ScrollingObjects> portalList = new LinkedList();
    LinkedList<ScrollingObjects> portalList() {
         portalList.addFirst(portal);
         return portalList;
     }
       
    Player portalPlayer = new Player(new Posn(WINDOWWIDTH/2,WINDOWHEIGHT/2), 
                        true, 
                        distanceToFall,
                        WINDOWHEIGHT);
    
      
    Player hitPortalPlayer = new Player(new Posn(WINDOWWIDTH/2,WINDOWHEIGHT/2-10), 
                        true, 
                        distanceToFall+12,
                        WINDOWHEIGHT);
    
    Data2 portalOne = new Data2(portalPlayer, true, theOver, theUnder, true, portalList(), 10, 1, false);
    Data2 portalTwo = new Data2(hitPortalPlayer, true, theOver, theUnder, false, new LinkedList(), 30, 1, false);
    
    public boolean testPortalCollision(Tester t) {
        return t.checkExpect(portalOne.onTick(),
                             portalTwo);
    }
    
          
    Player tickPlayer = new Player(new Posn(WINDOWWIDTH,WINDOWHEIGHT), 
                        true, 
                        distanceToFall,
                        WINDOWHEIGHT);
    
      
    Player tickedPlayer = new Player(new Posn(WINDOWWIDTH,WINDOWHEIGHT-10), 
                          true, 
                          distanceToFall,
                          WINDOWHEIGHT);
    
    LinkedList<ScrollingObjects> tickList = new LinkedList();
    LinkedList<ScrollingObjects> tickList() {
        tickList.addFirst(portal);
        tickList.addFirst(orb);
        tickList.addFirst(centerMob);
        return tickList;
    }
    LinkedList<ScrollingObjects> tickedList() {
        tickList.addFirst(movedPortal);
        tickList.addFirst(movedOrb);
        tickList.addFirst(movedCenterMob);
        return tickList;
    }
    
    Data2 tick = new Data2(tickPlayer, true, theOver, theUnder, true, tickList(), 10, 1, false);
    Data2 ticked = new Data2(tickedPlayer, true, theOver, theUnder, true, tickedList(), 20, 1, false);
    
    public boolean testBasicOnTickFunction(Tester t) {
        return t.checkExpect(tick.onTick(),
                             ticked);
    }
    
    }
    
