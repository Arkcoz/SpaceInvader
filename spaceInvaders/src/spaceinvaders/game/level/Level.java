/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders.game.level;


import iut.Game;
import iut.Vector;
import java.awt.Color;
import java.awt.Graphics;
import spaceinvaders.game.level.alien.VagueSpawner;
import spaceinvaders.game.level.player.Player;

/**
 *
 * @author el711240
 */
public class Level extends Game{
    
    private static Player joueur;
    private Memory memory;
    
    public Level(int width, int height, String title) {
        super(width, height, title);
        
        
    }

    @Override
    protected void createItems() {
        memory = new Memory(this);
        this.addItem(new Background(this, "SpaceTest1", 0, 0, 8));
        this.addItem(new Background(this, "SpaceTest2", 0, 0, 4));
        
        
        memory.parDessus();
        Player J = new Player(memory, this, "Player0", 512, this.getHeight() - 80);
        this.addItem(J);
        Level.joueur = J;

        VagueSpawner bob = new VagueSpawner(memory, this,"transparent",0,0);
        this.addItem(bob);
        
        
        this.addItem(new Shield(memory, this,75, 770));
        this.addItem(new Shield(memory, this,450, 770));
        this.addItem(new Shield(memory, this,800, 770));
        
    }

    @Override
    protected void drawBackground(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(00, 0, getWidth(), getHeight());
    }

    @Override
    protected void lost() {
    }

    @Override
    protected void win() {
    }

    @Override
    protected boolean isPlayerWin() {
        return false;
    }

    @Override
    protected boolean isPlayerLost() {
        return false;
    }

    @Override
    public Vector getGravity() {
        return null;
    }

    public static Player getJoueur() {
        return joueur;
    }
     
    
    
}
