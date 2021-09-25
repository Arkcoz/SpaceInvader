/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders.game.level.alien;

import iut.Game;
import iut.GameItem;
import spaceinvaders.game.level.Memory;

/**
 *
 * @author Eileen
 */
public class VagueSpawner extends GameItem{
    private int timer;
    private int waitingTime;
    private int difficulty;
    private final Memory memo;
    
    public VagueSpawner(Memory memo, Game g, String name, double _x, double _y) {
        super(g, name, _x, _y);
        this.memo = memo;
        this.difficulty = 1;
        this.timer = 0;
        waitingTime = (int) (((1 - Math.pow((float)difficulty, 0.6)) / 10) +4) * 1000;
        super.getGame().addItem(new VagueAlien(memo, super.getGame(), "transparent", super.getMiddleX(), super.getMiddleY(), difficulty));
        
    }

    @Override
    public boolean isCollide(GameItem gi) {
        return false;
    }

    @Override
    public void collideEffect(GameItem gi) {
        
    }

    @Override
    public String getItemType() {
        return "Spawner";
    }

    @Override
    public void evolve(long l) {
        timer += l;
        if(timer >= waitingTime){
            waitingTime = (int) (((float)((1 - Math.pow(((float)difficulty)/6, 0.6))) +5) * 1000);

            super.getGame().addItem(new VagueAlien(memo, super.getGame(), "transparent", super.getMiddleX(), super.getMiddleY(), difficulty));
            timer = 0;
            if(difficulty < 100){
                difficulty++;
            }
        }
    }
    
}
