/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders.game.level.alien;

import iut.Audio;
import iut.BoxGameItem;
import iut.Game;
import spaceinvaders.game.level.Memory;

/**
 *
 * @author el711240
 */
public abstract class Alien  extends BoxGameItem{
    private final Audio sonExplosion;
    private boolean hitWall = false;
    private boolean hitRight=false;
    protected double vitesse;
    protected boolean touche;
    protected long shootClock;
    
    protected long timeToShoot;
    
    protected final Memory memory;
    protected static int POINTS;
    private TypeMove lastMove;
    
    protected boolean isInvicible;
    
    public Alien(Memory memory,Game g, String nom, int x, int y) {
        super(g, nom, x, y);
        
        //Le son
        this.sonExplosion = new Audio("explosion");
        
        //La vitesse de déplacement
        this.vitesse = 5;
        
        //Autre
        this.touche = false;
        this.memory = memory;
        
        //Le tir
        this.shootClock = 0;
        isInvicible = true;
        
        firstMove();
        
    }
    
    public void jouerSonExplosion(){
        this.sonExplosion.start(); 
    }
    

    public void moveAlien(double x,double y ){
        this.moveXY(x, y);
    }
    
    public abstract void explose();

    public boolean isHitWall() {
        return hitWall;
    }

    public void setHitWall(boolean hitWall) {
        this.hitWall = hitWall;
    }

    public boolean isHitRight() {
        return hitRight;
    }

    public void setHitRight(boolean hitRight) {
        this.hitRight = hitRight;
    }
    

    public double getVitesse() {
        return this.vitesse;
    }

    public void setVitesse(double vitesse) {
        this.vitesse = vitesse;
    }
    
    public TypeMove getLastMove(){
        return lastMove;
    }
    
    public void setLastMove(TypeMove move){
        lastMove = move;
    }
    
    public void firstMove(){
        if(Math.random()*2 < 1){
            lastMove = TypeMove.RIGHT;
        }else{
            lastMove = TypeMove.LEFT;
        }
    }

    public boolean isTouche() {
        return touche;
    }
    
    
}
