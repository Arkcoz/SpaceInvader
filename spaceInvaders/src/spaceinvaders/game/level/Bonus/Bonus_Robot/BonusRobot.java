/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders.game.level.Bonus.Bonus_Robot;

import iut.BoxGameItem;
import iut.Game;
import iut.GameItem;
import spaceinvaders.game.level.Bonus.Bonus;

/**
 *
 * @author lucas
 */
public class BonusRobot extends Bonus {

    public BonusRobot(Game g, int x, int y) {
        super(g, "bonus_robot", x, y);
    }

    
    @Override
    public String getItemType() {
        return "BonusRobot";
    }
    
}
