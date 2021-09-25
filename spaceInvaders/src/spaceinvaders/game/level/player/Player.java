/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders.game.level.player;

import iut.BoxGameItem;
import iut.Game;
import iut.GameItem;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.logging.Level;
import spaceinvaders.game.level.Bonus.Bonus_Armure.Armure;
import spaceinvaders.game.level.Bonus.Bonus_Robot.Robot;
import spaceinvaders.game.level.alien.Soucoupe;
import spaceinvaders.game.level.Memory;
import spaceinvaders.game.level.effects.effectHeal;
import spaceinvaders.game.level.missile.missileJoueur.MissileJ1;
import spaceinvaders.game.level.missile.missileJoueur.MissileJ2;

/**
 *
 * @author el711240
 */
public class Player extends BoxGameItem implements KeyListener{
    
    private Vie vie;
    private static Level level;
    
    //touches:
    private boolean right;
    private boolean left;
    private boolean down;
    private boolean up;
    
    //Sprite
    private ArrayList<String> SpritePlayer;
    private int etat; 
    
    private Vie _vie;
    private final Memory memory;
    
    //Invincibilité si touché
    private boolean invicible; 
    private long invicibleClock;
    
    //Bonus vitesse x2
    private boolean bonusFast;
    private long fastClock;
    
    //Bonus slow (pour les ennemis)
    private long slowClock;
    
    //Coordonnées actuelles
    private int X;
    private int Y;
    
    //Vitesse de base
    private int vitesseJ;
    
    //tir
    private int cooldownClock;
    private static final int COOLDOWN = 450;
    private boolean overheated;
    private int missiles;

    
    public Player(Memory m, Game g, String nom, int x, int y) {
        super(g, nom, x, y);
        this.X = x;
        this.Y = y;     
        
        //Ajoute de la barre de vie du joueur
        this.vie = new Vie(g,"transparent",10,super.getGame().getHeight()-20,this);
        g.addItem(vie);
           
        //Initialisation des variables de déplacement
        this.up = false;
        this.down = false;
        this.right = false;
        this.left = false;
        
        //Vitesse 
        this.vitesseJ = 10;
        this.bonusFast = false;
        this.fastClock = 0;
        
        //Initialisation SpritePlayer
        initialisationSprite();
        
        this.invicible = false;
        this.invicibleClock =0;
        
        this.slowClock = 0;
        
        this.memory = m;
        cooldownClock = 0;
        overheated = false;
        missiles = 1;

    }

    @Override
    public void collideEffect(GameItem gi) {
        if(!invicible){
            if ( (gi.getItemType() == "Alpha") || (gi.getItemType() == "Octopus") || (gi.getItemType() == "Mothership") || (gi.getItemType() == "Missile Alien")){
                super.getGame().remove(gi);
                if (this.vie.getNbCoeurPlein()==0){
                    //ça reste à 0
                }
                else{
                    this.vie.removeVie();
                }
                invicible = true;
                memory.getScore().losePoints(200);
            }
            
        }
        
        if ( ("BonusHeal".equals(gi.getItemType()))){
            this.vie.addVie();   
            this.getGame().addItem(new effectHeal(this.getGame(),"transparent", this.getMiddleX()-97, this.getMiddleY()-125));
            memory.getScore().earnPoints(500);
        }
        
        if ("BonusRobot".equals(gi.getItemType())){
            Robot bob = new Robot(this.getGame(),"Robot",this.getGame().getWidth(), this.getGame().getHeight() - 250);
            this.getGame().addItem(bob);
        }
    
        if ("BonusArmure".equals(gi.getItemType())){
            Armure bob = new Armure(this.getGame(),"circle-0",this.X, this.Y,this);
            this.getGame().addItem(bob);
        }
        
        if ("BonusSlow".equals(gi.getItemType())){
            memory.setSlow(true);
        }
        
        if ("BonusFast".equals(gi.getItemType())){
            if(!bonusFast){
                this.bonusFast = true;
                this.vitesseJ = this.vitesseJ*2;
            }
        }
        
        if ("BonusWeapon".equals(gi.getItemType())){
            this.missiles = 2;
        }
        
    }

    @Override
    public String getItemType() {
        return "Player";
    }

    @Override
    public void evolve(long l) {
        //tir
        cooldownClock += l;
        if(cooldownClock >= COOLDOWN){
            cooldownClock = 0;
            overheated = false;
        }
        
        //PERDRE LA PARTIE
        if (vie.getNbCoeurPlein()==0){
            super.getGame().remove(this);
            perdre();
            
        }
           
        //BONUS DE VITESSE
        if (this.bonusFast){
            this.fastClock += l;
            if(fastClock >= 10000){
                bonusFast = false;
                this.vitesseJ = this.vitesseJ/2;
                this.fastClock =0;
            }
        }
        
        //BONUS DE SLOW
        if (memory.isSlow()){
            this.slowClock += l;
            if(slowClock >= 5000){
                memory.setSlow(false);
                this.slowClock =0;
            }
        }     
        
        
        //PARTIE DEPLACEMENT DU JOUEUR
        deplacementJoueur();
        
        
        //MOUVEMENT DU SPRITE
        changementSprite();
        
        //Gestion invincible
        if(invicible){
            this.invicibleClock += l;
            if(invicibleClock >= 1500){
                invicible = false;
                this.etat = 0;
                this.invicibleClock =0;
            }
        }
        
        //Gestion alien qui passent le bas de l'écran
        if(memory.isAlienPassed()){
            memory.setAlienPassed(false);
            vie.removeVie();
            
        }
        
    }
    
    public void goLeft(){
        this.moveXY(-vitesseJ, 0);
        this.X -= vitesseJ; 
        
    }
    
    public void goRight(){
        this.moveXY(+vitesseJ, 0);
        this.X += vitesseJ; 
    }
    
    public void stopMove(){
        this.moveXY(0, 0);
    }
    
    public void goUp(){
        this.moveXY(0, -vitesseJ);
        this.Y -= vitesseJ; 
        
    }
    
    public void goUpLeft(){
        this.moveXY(-vitesseJ, -vitesseJ);
        this.X -= vitesseJ; 
        this.Y -= vitesseJ; 

    }
    
    public void goUpRight(){
        this.moveXY(vitesseJ, -vitesseJ);
        this.X = vitesseJ; 
        this.Y -= vitesseJ; 
        
    }
    
    public void goDown(){
        this.moveXY(0, +vitesseJ);
        this.Y += vitesseJ; 
        
    }
    
    public void goDownLeft(){
        this.moveXY(-vitesseJ, +vitesseJ);
        this.X -= vitesseJ;
        this.Y += vitesseJ; 
        
    }
    
    public void goDownRight(){
        this.moveXY(+vitesseJ, +vitesseJ);
        this.X += vitesseJ; 
        this.Y += vitesseJ; 
        
    }
    
    
    @Override
    public void keyTyped(KeyEvent e) {
        

    }

    @Override
    public void keyPressed(KeyEvent e) {
        try{
            switch(e.getKeyCode())
            {
                //Aller à Gauche [Q]
                case KeyEvent.VK_Q:
                    if(this.getLeft()>0)
                        goLeft();
                    break;
                
                //Aller à Droite [D]
                case KeyEvent.VK_D:
                    if(this.getRight()<this.getGame().getWidth()-300)
                        goRight();
                    break;
                
                //Aller à Gauche [Z]
                case KeyEvent.VK_Z:
                    if(this.getTop()>this.getGame().getHeight() - 150)
                        goUp();
                    break;
                
                //Aller à Droite [S]
                case KeyEvent.VK_S:
                    if(this.getBottom()<this.getGame().getHeight() - 20)
                        goDown();
                    break;
                
                //Aller à Gauche [←]
                case KeyEvent.VK_LEFT:
                        left = true;
                    break;   
                
                //Aller à Droite [→]
                case KeyEvent.VK_RIGHT:
                        right = true;
                    break;  
                
                //Aller en Haut [↑]
                case KeyEvent.VK_UP:
                        up = true;
                    break;
                
                //Aller en Bas [↓]
                case KeyEvent.VK_DOWN:
                        down = true;
                    break;
                    
                //Faire spawn une vague [N]
                case KeyEvent.VK_N:

                        //this.getGame().addItem(new VagueAlien(memory, this.getGame(),"transparent",0,0,100));

                    break;
                
                //Faire [V]
                case KeyEvent.VK_V:

                        this.getGame().addItem(new Soucoupe(memory, this.getGame(),-64,20));

                    break;
                    
                 
                
            }
        }
        catch(Exception x){}
    }

    @Override
    public void keyReleased(KeyEvent e) {
         switch(e.getKeyCode()){
            case KeyEvent.VK_SPACE:
                {
                    if(!overheated){
                        shoot(missiles);
                    }
                  
                }
                break;
            
            case KeyEvent.VK_LEFT :
                    left = false;
                break;
                    
            case KeyEvent.VK_RIGHT :
                    right = false;
                break;
                
            case KeyEvent.VK_UP :
                    up = false;
                break;
                    
            case KeyEvent.VK_DOWN :
                    down = false;
                break;
        }
    }
    
    public void shoot(int nb){
        switch(nb){
            case 1 : 
                super.getGame().addItem(new MissileJ1(this.getGame(), this.getMiddleX()-24, this.getTop()));
            break;
            
            case 2 : 
                super.getGame().addItem(new MissileJ2(this.getGame(), this.getMiddleX()-24, this.getTop()));
            break;
        }
        overheated = true;
        
    }

    public void perdre() {
        throw new UnsupportedOperationException("Vous avez perdu !.");
    }

    
    public void changementSprite(){
        this.changeSprite(SpritePlayer.get(this.etat));
        if(invicible){
            switchEtat();
        }else{
            if (this.etat == 9){
                this.etat = 0;
            }else{
                this.etat +=1;
            }
        }
    }
    
    public void initialisationSprite(){
        SpritePlayer = new ArrayList<>();
        SpritePlayer.add(0,"Player01");
        SpritePlayer.add(1,"Player02");
        SpritePlayer.add(2,"Player03");
        SpritePlayer.add(3,"Player04");
        SpritePlayer.add(4,"Player05");
        SpritePlayer.add(5,"Player06");
        SpritePlayer.add(6,"Player07");
        SpritePlayer.add(7,"Player08");
        SpritePlayer.add(8,"Player09");
        SpritePlayer.add(9,"Player010");
        SpritePlayer.add(10,"Player_invincible");
        
    }
    
    public void deplacementJoueur(){
        if ((up) && (right)){
            if ((this.getRight()<this.getGame().getWidth()-10) && (this.getTop()>this.getGame().getHeight() - 150)){
                goUpRight();
            }
            else if (this.getRight()<this.getGame().getWidth()-10){
                goRight();
            }
            else if (this.getTop()>this.getGame().getHeight() - 150){
                goUp();
            }
        }
        else if ((up) && (left)){
            if ((this.getLeft()>0) && (this.getTop()>this.getGame().getHeight() - 150)){
                goUpLeft();
            }
            else if (this.getLeft()>0){
                goLeft();
            }
            else if (this.getTop()>this.getGame().getHeight() - 150){
                goUp();
            }
        }
        else if((down)&&(right)){
            if ((this.getRight()<this.getGame().getWidth()-10) && (this.getBottom()<this.getGame().getHeight() - 20)){
                goDownRight();
            }
            else if (this.getRight()<this.getGame().getWidth()-10){
                goRight();
            }
            else if (this.getBottom()<this.getGame().getHeight() - 20){
                goDown();
            }
        }
        else if ((down)&&(left)){
            if ((this.getLeft()>0) && (this.getBottom()<this.getGame().getHeight() - 20)){
                goDownLeft();
            }
            else if(this.getLeft()>0){
                goLeft();
            }
            else if (this.getBottom()<this.getGame().getHeight() - 20){
                goDown();
            }
        }
        else if (up){
            if (this.getTop()>this.getGame().getHeight() - 150){
                goUp();
            }
        }
        else if (down){
            if (this.getBottom()<this.getGame().getHeight() - 20){
                goDown();
            }
        }
        else if(left){
            if (this.getLeft()>0){
                goLeft();
            }
        }
        else if (right){
            if(this.getRight()<this.getGame().getWidth()-10){
                goRight();
            }
        }
        else
            stopMove();
    }

   
    private void switchEtat() {
        if(this.etat == 10){
            etat = 0;
        }else{
            etat = 10;
        }
    }
    
    public void gagnerVie(){
        this.vie.addVie();
    }

    public boolean isInvicible() {
        return invicible;
    }

    public int getX() {
        return X;
    }

    public void setX(int X) {
        this.X = X;
    }

    public int getY() {
        return Y;
    }

    public void setY(int Y) {
        this.Y = Y;
    }
    
    
    
}
