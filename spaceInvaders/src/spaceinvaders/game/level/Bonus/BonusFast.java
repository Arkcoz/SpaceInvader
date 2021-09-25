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
public class BonusFast extends Bonus {

    public BonusFast(Game g, int x, int y) {
        super(g,"bonus_speed", x, y);
    }

    @Override
    public String getItemType() {
        return "BonusFast";
    }
    
}
