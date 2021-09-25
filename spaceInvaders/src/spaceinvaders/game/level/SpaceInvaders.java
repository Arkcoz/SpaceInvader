/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders.game.level;

import iut.Game;
import iut.Vector;
import java.awt.Graphics;
import spaceinvaders.game.level.Level;

/**
 *
 * @author el711240
 */
public class SpaceInvaders extends Game{
        
    private static Game lvl1;
    
    
    public static void main(String[] args) {
        SpaceInvaders.lvl1 = new Level(1000, 1000, "Sp@ce"); //anciennement [1024, 576]
        lvl1.play();
        
        
    }

    public SpaceInvaders(int width, int height, String title) {
        super(width, height, title);
    }

    @Override
    protected void createItems() {
        
    }

    @Override
    protected void drawBackground(Graphics grphcs) {
        
    }

    @Override
    protected void lost() {
        
    }

    @Override
    protected void win() {
        
    }

    @Override
    protected boolean isPlayerWin() {
        return true;
    }

    @Override
    protected boolean isPlayerLost() {
        return false;
    }

    @Override
    public Vector getGravity() {
        return null;
    }

    public static Game getLvl1() {
        return lvl1;
    }
    
    
    
    
    
}
