/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders.game.level.alien;

import iut.Game;
import iut.GameItem;
import java.util.ArrayList;
import spaceinvaders.game.level.Memory;



/**
 *
 * @author qf393372
 */
public class VagueAlien extends  GameItem{
    
    private final ArrayList<Alien> listAlien =new ArrayList<>();
    private long timer;
    private int difficulty;
    private final Memory memory;
    private boolean eliminer;

    public VagueAlien(Memory memory, Game g, String name, double _x, double _y, int difficulty) {
        super(g, name, _x, _y);
        this.memory = memory;
        this.difficulty = difficulty;
        initialisation();
        this.eliminer = false;
        
        
        memory.addVagueAlien(this);
    }
    
    public void initialisation(){
        if(difficulty%3 == 0){
            Soucoupe s1 = new Soucoupe(memory, this.getGame(), -50,50);
            addBob(s1);
        }
        
        int nbAlien = (int) (((float) difficulty/4) + 6);
        int x;
        Alien ex = new Octopus(memory, this.getGame(),0,0);
        if(nbAlien%2==0){
            x = super.getGame().getWidth()/2-((nbAlien/2)*ex.getWidth());
        }else{
            x = super.getGame().getWidth()/2-(((nbAlien-1)/2)*ex.getWidth())+(ex.getWidth()*2);
        }
        int meanX = x;
        int y = -100;
        int meanY = y;
        
        int probaA = (int)((float)(difficulty/10)+30);
        //int probaO = (int) (-1*(1-(Math.pow(((float)(difficulty)/1.1), -5)+70)));
        int probaM = (int)((float)((difficulty)/2)+2);

        
        for(int i = 0; i < nbAlien; i++){
            Alien nouv;
            int rnd = (int) Math.round(Math.random()*100);


            if(rnd < probaA){
                nouv = new Alpha(memory, this.getGame(),x,y);
                
                
            }else if(rnd < probaA+probaM){
                nouv = new Mothership(memory, this.getGame(),x,y);
                
            }else{
                nouv = new Octopus(memory, this.getGame(),x,y);
                
                
            }
            
            addBob(nouv);
            

            switch(i%3){
                case 0 : y = meanY;
                    break;
                case 1 : y = meanY+30;
                    break;
                case 2 : y = meanY-30;
                    break;    
            }
            
            x += nouv.getWidth()+10;
            if(x >= super.getGame().getWidth()-20){
                x = meanX;
                meanY += nouv.getHeight()*2 +10;
                y = meanY;
            }
        }
        
        
        
    }
    
    @Override
    public boolean isCollide(GameItem gi) {
       return false;
    }

    @Override
    public void collideEffect(GameItem gi) {
       
    }

    @Override
    public String getItemType() {
        return "VagueAlien";
    }

    @Override
    public void evolve(long l) {
        
        estEliminer(); //teste pour savoir si la vague est éliminé
        
        DeplaceAlien(listAlien.get(0).getVitesse(),listAlien.get(0).getVitesse(), l);
        
        if (memory.isSlow()){
            for (Alien c : listAlien){
                c.setVitesse(2.5);
            }
        }else{
           for (Alien c : listAlien){
                c.setVitesse(5);
            } 
        }
        
        if (eliminer){
            this.getGame().remove(this);
        }
    }
  

    public ArrayList<Alien> getBob() {
        return listAlien;
    }
    
    public void addBob(Alien _bob){
        this.listAlien.add(_bob);
        super.getGame().addItem(_bob);
    }

    public void DeplaceAlien(double vitesseX, double vitesseY, long l){    
        timer += l;
        ArrayList passedAliens = new ArrayList();
        
        for (Alien a : listAlien){
            if(! (a instanceof Soucoupe)){
                if(a.getLastMove() == TypeMove.LEFT){
                    a.moveAlien(-vitesseY, +vitesseY/4);
                    if(timer >= 600){
                        a.setLastMove(TypeMove.RIGHT);

                    }


                }else{
                    a.moveAlien(+vitesseY, +vitesseY/4);
                    if(timer >= 600){
                        a.setLastMove(TypeMove.LEFT);

                    }
                }

                if(a.getRight() >= super.getGame().getWidth()-10){
                    a.setLastMove(TypeMove.LEFT);
                }

                if(a.getLeft() <= 10){
                    a.setLastMove(TypeMove.RIGHT);
                }

                if(a.getBottom() >= super.getGame().getHeight()+10 && !a.isTouche()){
                    memory.setAlienPassed(true);
                    passedAliens.add(a);
                    super.getGame().remove(a);
                }
            }        
            
        }
        if(timer >= 600){
            timer = 0;
        }
        
        listAlien.removeAll(passedAliens);
        
    }
   
    public void estEliminer(){
        if (this.listAlien.isEmpty()){
            this.eliminer = true;
        }
    }
 
    
    
}