/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petitjeu;

import iut.*;

/**
 * La balle du petit jeu
 * @author aguidet
 */
public class Balle extends iut.BoxGameItem{

    private double vitesse=0.2;
    private double angle = 45;
    private static int nombre=0;
    private int waiting = 0;
    private boolean touched = false;
    
    /**
     * Initialise la balle
     * @param g le jeu
     * @param x la position de départ
     * @param y 
     */
    public Balle(Game g, int x, int y) {
        super(g, "balle", x, y);
        ++nombre;
    }

    private void rebondir()
    {
        System.out.println("Angle : " + angle + " Top : "+ (super.getTop() <= 10) + "| BOttom : "+ (super.getBottom() <= super.getGame().getHeight()-10) +  " | Right :" + (super.getRight() <= super.getGame().getWidth()-1000) + " | Left : "+ (super.getLeft() <= 10) + "");
        if((angle == 45) && (super.getTop() <= 10)){
            angle += 270;
            System.out.println("1");
        }else
        if((angle == 45) && (super.getLeft() <= 10)){
            angle += 90;
            System.out.println("2");
        }else
        if((angle == 135) && (super.getTop() <= 10)){
            angle += 90;
            System.out.println("3");
        }else
        if((angle == 135) && (super.getRight() >= super.getGame().getWidth()-10)){
            angle += 270;
            System.out.println("4");
        }else
        if((angle == 225) && (super.getBottom() >= super.getGame().getHeight()-10)){
            angle += 270;
            System.out.println("5");
        }else
        if((angle == 225) && (super.getLeft() <= 10)){
            angle += 90;
            System.out.println("6");
        }else
        if((angle == 315) && (super.getRight() >= super.getGame().getWidth()-10)){
            angle += 270;
            System.out.println("7");
        }else
        if((angle == 315) && (super.getBottom() >= super.getGame().getHeight()-10)){
            angle += 270;
            System.out.println("8");
        }
        
        if(angle >= 360){
            angle -= 360;
        }
        
        vitesse += 0.1;
        double scale = 1-vitesse;
        try{
            setScale(scale);
        }
        catch(Exception x){}
        
    }
    @Override
    public void collideEffect(GameItem o) {
        if(!touched){
            if(o!=this)
            {
                rebondir();
            }
            touched = true;
        }else{
            if(this.waiting <= 0){
                this.touched = false;
            }
            
        }
    }

    @Override
    public void evolve(long dt) {
        if(this.waiting > 0){
            this.waiting -= dt;
        }
        if(this.getTop()<=0)
        {
            rebondir();
        }
        else if(this.getTop()>this.getGame().getHeight())
        {
            this.getGame().remove(this);
            --nombre;
            if(nombre==0)
                this.getGame().die();
            
        }
        else if(this.getLeft()<=0)
        {
            rebondir();
        }
        else if(this.getRight()>this.getGame().getWidth())
        {
            rebondir();
        }
        this.moveDA(dt*vitesse, angle);
        
    }

    @Override
    public String getItemType() {
        return "Balle";
    }
    
}
