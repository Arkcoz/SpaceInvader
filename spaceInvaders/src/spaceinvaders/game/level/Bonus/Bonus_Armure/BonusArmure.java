/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders.game.level.Bonus.Bonus_Armure;

import iut.Game;
import spaceinvaders.game.level.Bonus.Bonus;

/**
 *
 * @author lucas
 */
public class BonusArmure extends Bonus {

    public BonusArmure(Game g, int x, int y) {
        super(g, "bonus_shield", x, y);
    }

    @Override
    public String getItemType() {
        return "BonusArmure";
    }
    
}
