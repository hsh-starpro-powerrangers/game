import greenfoot.*;  // imports Actor, World, Greenfoot, GreenfootImage

import java.util.Random;

public class Level extends World
{
    public int[] mGroundCoordinatesX = {210, 240, 300, 330, 240, 300, 330, 300, 330, 330, 140, 170, 200, 230, 420, 450, 480, 570, 570, 600, 690, 720, 750};
    public int[] mGroundCoordinatesY = {540, 540, 540, 540, 510, 510, 510, 480, 480, 450, 420, 420, 420, 420, 450, 450, 450, 420, 420, 420, 390, 390, 390};
    public int[] mCoinCoordinatesX = {135, 165, 195, 225, 450, 600};
    public int[] mCoinCoordinatesY = {370, 370, 370, 370, 390, 360};
    
    public Player mPlayer;
    
    public Level() 
    {
        // Welt erstellen?
        super(800, 600, 1);    
        // Protagonisten erstellen.
        mPlayer = new Player();
        
        // Unteren Boden erstellen.
        for(int i = 0; i < 20; i++)
        {            
            this.addObject(new Ground(), i * 30 + 15, 585);
        }
        // Restliche Böden erstellen.
        for(int i = 0; i < mGroundCoordinatesX.length; i++)
        {
            this.addObject(new Ground(), mGroundCoordinatesX[i] + 15, mGroundCoordinatesY[i] + 15);
        }
        // Münzen erstellen.
        for(int i = 0; i < mCoinCoordinatesX.length; i++)
        {
            this.addObject(new Coin(), mCoinCoordinatesX[i] + 15, mCoinCoordinatesY[i] + 30);
        }
        // Protagonisten erstellen.
        this.addObject(mPlayer, 0, 0);
        // Position des Protagonisten setzen.
        mPlayer.resetPosition();
        // Punkte des Protagonisten anzeigen.
        mPlayer.showCoins();
        // Gegner erstellen.
        this.addObject(new Enemy(), 500, 540);
        // Zielflagge erstellen.
        this.addObject(new Flag(), 750, 350);
        
    } 
}