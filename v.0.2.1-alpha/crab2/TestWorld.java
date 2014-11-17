import greenfoot.*;  // imports Actor, World, Greenfoot, GreenfootImage

import java.util.Random;

public class TestWorld extends World
{
    /**
     * Create the crab world (the beach). Our world has a size 
     * of 560x460 cells, where every cell is just 1 pixel.
     */
    public TestWorld() 
    {
        super(560, 460, 1);
        // this.addObject(new Player(), Greenfoot.getRandomNumber(560), Greenfoot.getRandomNumber(460));
        
        this.addObject(new Ground(), 100, 420);
        this.addObject(new Flag(), 100, 200);
        this.addObject(new Enemy(), 110, 380);
        for(int i = 0; i < 10; i++)
        {
            // TODO: Temp
            this.addObject(new Ground(), 200 + i * 30, 420);
        }//ebene1
        this.addObject(new Ground(), 320, 390);
        this.addObject(new Player(), 250, 350);
        for(int j = 0; j < 5; j++)
        {
            // TODO: Temp
            this.addObject(new Coin(), 200+j*70, 385/*(5*(Math.random(3)-2))*/);
        }
        
          for(int i = 0; i < 4; i++)
        {
            // TODO: Temp
            this.addObject(new Ground(), 320 + i * 60, 325 - i*35);
        }//ebene2
    }
}