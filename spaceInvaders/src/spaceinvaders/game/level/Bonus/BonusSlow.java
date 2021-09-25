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
public class BonusSlow extends Bonus{

    public BonusSlow(Game g,  int x, int y) {
        super(g, "bonus_slow", x, y);
    }

    @Override
    public String getItemType() {
        return "BonusSlow";
    }
    
}
