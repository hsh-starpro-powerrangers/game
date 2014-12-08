import greenfoot.*;  // imports Actor, World, Greenfoot, GreenfootImage

import java.util.Random;

public class Level0 extends World
{
    public int[] mGroundCoordinatesX = {210, 240, 300, 330, 240, 300, 330, 300, 330, 330, 140, 170, 200, 230, 420, 450, 480, 570, 570, 600, 690, 720, 750};
    public int[] mGroundCoordinatesY = {540, 540, 540, 540, 510, 510, 510, 480, 480, 450, 420, 420, 420, 420, 450, 450, 450, 420, 420, 420, 390, 390, 390};
    public int[] mCoinCoordinatesX = {140, 170, 200, 230, 450, 600};
    public int[] mCoinCoordinatesY = {400, 400, 400, 400, 430, 400};
    
    public Player mPlayer;
    
    public Level0() 
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
            this.addObject(new Coin(), mCoinCoordinatesX[i] + 15, mCoinCoordinatesY[i]);
        }
        // Protagonisten erstellen.
        this.addObject(mPlayer, 0, 0);
        // Position des Protagonisten setzen.
        mPlayer.resetPosition();
        // Punkte des Protagonisten anzeigen.
        mPlayer.showCoins();
        // Leben des Protagonisten anzeigen.
        mPlayer.showLifes();
        // Gegner erstellen.
        this.addObject(new Enemy(), 500, 540);
        // Zielflagge erstellen.
        this.addObject(new Flag(), 750, 350);
        // Trigger-Objekte erstellen.
        this.addObject(new TriggerObject(2, 420 + 15), 140 + 15, 420 + 15 );        
    } 
}