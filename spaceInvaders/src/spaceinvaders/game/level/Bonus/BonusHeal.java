/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders.game.level.Bonus;

import iut.Game;

/**
 *
 * @author lucas
 */
public class BonusHeal extends Bonus{

    
    public BonusHeal(Game g, int x, int y) {
        super(g, "BonusHeal", x, y);
    }

    @Override
    public String getItemType() {
        String res ="BonusHeal";
        if (touche){
            res = "BonusHeal";
        }
        return res;
    }

       
}
