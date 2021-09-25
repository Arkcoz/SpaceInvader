/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders.game.level.Bonus.Bonus_Robot;

import iut.BoxGameItem;
import iut.Game;
import iut.GameItem;

/**
 *
 * @author lucas
 */
public class Robot extends BoxGameItem {

    private long clock;
    private boolean hitWall;
    private boolean hitRight;
    private int localisationX;
    private int localisationY;
    private int toX;
    private int toY;
    
    public Robot(Game g, String nom, int x, int y) {
        super(g, nom, x, y);
        this.clock = 0;
        this.hitWall=false;
        this.hitRight = false;
        this.localisationX = x;
        this.localisationY = y;
        
    }

    @Override
    public void collideEffect(GameItem gi) {
        if(!(gi == null)){
            if ((gi.getItemType() == "Alpha") || (gi.getItemType() == "Octopus") || (gi.getItemType() == "Mothership") || (gi.getItemType() == "Missile Alien") ) {
                //Se Passe Rien        
            }
        }
    }

    @Override
    public String getItemType() {
        return "Robot";
    }

    @Override
    public void evolve(long l) {
       /**
        //Début
        if ((this.isHitWall()== false)){
            moveRobot(-10,0);
            if (this.getLeft()<0){
                this.hitWall = true;
                this.hitRight = true;
            }
        } 
        
        //Aller a droite
        if ((hitRight == true) && (this.isHitWall()== true)){
            moveRobot(+10,0);
            if (this.getRight()>this.getGame().getWidth()-300){
                this.hitRight = false;
            }
        }
        
        //Aller a gauche
        if ((hitRight == false) && (this.isHitWall()== true)){
            moveRobot(-10,0);
            if (this.getLeft()<0){
                this.hitRight = true;
            }
        }
        **/
        
        if ((this.isHitWall()== false)){
            moveRobot(-10,0);
            if (this.getRight()<0){
                this.getGame().remove(this);
            }
        } 
        
        clock += l;
        if(clock >= 50){
            shoot();
            clock =0;
        }
        
    }
    
    public void shoot(){
        MissileRobot missile = new MissileRobot(this.getGame(),"RobotMissile", this.getMiddleX(), this.getTop());
        super.getGame().addItem(missile);
    }

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

    public void moveRobot(int x, int y){
        moveXY(x,y);
        this.localisationX += x;
        this.localisationY += y;
    }
}
