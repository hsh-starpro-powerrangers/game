import greenfoot.*;  // imports Actor, World, Greenfoot, GreenfootImage

import java.util.Random;

public class Level extends World
{
    /**
     * Create the crab world (the beach). Our world has a size 
     * of 560x460 cells, where every cell is just 1 pixel.
     */
    
    public Level() 
    {
        super(800, 600, 1);
        // this.addObject(new Player(), Greenfoot.getRandomNumber(560), Greenfoot.getRandomNumber(460));
        
     /*   for(int i = 0; i < 10; i++)
        {
            if(i != 3 && i != 5)
            {
                this.addObject(new Ground(), 100 + i * 30,500);
            }
        }
        this.addObject(new Enemy(), 220, 450);
        this.addObject(new Player(), 100, 450);
        this.addObject(new Flag(), 380, 440);
        this.addObject(new Coin(), 130, 460);
        this.addObject(new Coin(), 280, 460);*/
    } 
}