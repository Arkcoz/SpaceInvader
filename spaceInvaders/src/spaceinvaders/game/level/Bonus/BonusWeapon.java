/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders.game.level.Bonus;

import iut.Game;

/**
 *
 * @author Eileen
 */
public class BonusWeapon extends Bonus {

    public BonusWeapon(Game g,  int x, int y) {
        super(g, "BonusMissile", x, y);
    }

    @Override
    public String getItemType() {
        return "BonusWeapon";
    }
    
}
