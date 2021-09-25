/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders.game.level.player;

import iut.Game;
import iut.GameItem;
import java.util.ArrayList;

/**
 *
 * @author lm178867
 */
public class Vie extends GameItem {
    private int nbCoeurPlein; //CoeurPlein
    private int nbCoeurVide; //CoueurVide
    private int nbCoeurParDefaut; //Coeur par Defaut
    private int nbCoeurBonus;
    
    private Player joueur;
    private ArrayList<Coeur> listeVie; //liste des coeurs 
    
    private int xCoeur;
    private int yCoeur;
    
    public Vie(Game g, String nom, int x, int y, Player j) {
        super(g, nom, x, y);
        
        this.nbCoeurParDefaut = 3; 
        
        //Initialisation des coeurs
        this.nbCoeurPlein = 3;
        this.nbCoeurVide=0;
        this.nbCoeurBonus = 0;
        
        this.listeVie = new ArrayList<>();
        this.joueur = j;
        this.xCoeur = 20;
        this.yCoeur = super.getGame().getHeight()-40;
        
        //Affichage des coeurs
        for (int i = 0; i <this.nbCoeurPlein;i++){
            Coeur bob = new Coeur(this.getGame(),"FullHeart",xCoeur,yCoeur);
            this.xCoeur+=30; //indentation de +30 pour x 
            if (this.xCoeur>super.getGame().getWidth()-300)
            {
                this.xCoeur = 10;
                this.yCoeur += 30;
            }
            listeVie.add(bob);
            super.getGame().addItem(bob);
        }
                 
        for (int i = 0; i <this.nbCoeurVide;i++){
            Coeur bob = new Coeur(this.getGame(),"emptyHeart",xCoeur,yCoeur);
            this.xCoeur+=30; //indentation de +30 pour x 
            if (this.xCoeur>super.getGame().getWidth()-300)
            {
                this.xCoeur = 10;
                this.yCoeur += 30;
            }
            listeVie.add(bob);
            super.getGame().addItem(bob);
        }
        
        for (int i = 0; i <this.nbCoeurBonus;i++){
            Coeur bob = new Coeur(this.getGame(),"BonusHeart",xCoeur,yCoeur);
            this.xCoeur+=30; //indentation de +30 pour x 
            if (this.xCoeur>super.getGame().getWidth()-300)
            {
                this.xCoeur = 10;
                this.yCoeur += 30;
            }
            listeVie.add(bob);
            super.getGame().addItem(bob);
        }
        
    }

    @Override
    public String getItemType() {
        return "Vie";
    }

    @Override
    public void evolve(long l) {
        
    }

       
    public void addVie(){
        if (this.nbCoeurPlein == this.nbCoeurParDefaut){            
            Coeur bob = new Coeur(this.getGame(),"BonusHeart",xCoeur,yCoeur);
            listeVie.add(bob);
            super.getGame().addItem(bob);
            this.nbCoeurBonus += 1;
            this.xCoeur+=30; //indentation de +30 pour x 
            if (this.xCoeur>super.getGame().getWidth()-300)
            {
                this.xCoeur = 10;
                this.yCoeur += 30;
            }
        }
        else if (this.nbCoeurPlein<this.nbCoeurParDefaut){
              this.listeVie.get(nbCoeurPlein).changeSprite("FullHeart");  
              this.nbCoeurPlein += 1;
              this.nbCoeurVide -=1;
        }
        
        
            
        
    }
    public void removeVie(){
        if (this.nbCoeurBonus != 0){
            this.getGame().remove(this.listeVie.get(this.listeVie.size()-1));
            this.listeVie.remove(this.listeVie.get(this.listeVie.size()-1));
            this.nbCoeurBonus -=1;
        }
        else {
            this.listeVie.get(nbCoeurPlein-1).changeSprite("emptyHeart");
            
            nbCoeurPlein -= 1 ;
            nbCoeurVide += 1;
        }
        
        this.xCoeur-=30; //indentation de +30 pour x 
        if (this.xCoeur<0)
        {
            this.xCoeur = 10+(30*33);
            this.yCoeur -= 30;
        }
        
        

        
        
    }

    public int getNbCoeurPlein() {
        return nbCoeurPlein;
    }

    public Player getJoueur() {
        return joueur;
    }

    @Override
    public boolean isCollide(GameItem gi) {
        return false;
    }

    @Override
    public void collideEffect(GameItem gi) {
        
    }

    

    
    
}
