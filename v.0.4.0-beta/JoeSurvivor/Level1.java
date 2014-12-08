import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level1 extends World
{
    public int[] mGroundCoordinatesX = {190, 220, 250, 280,/** links unten L*/ 340, 370, 370,/** L*/ 180, 210, 240, 270,/**links oben L */ 510, 450, 480, 540, 570, 600,/** rechts vom L*/  85, 115,/**Zwischenplattform */ 190, 220, 250, 280, 310, 340, 370,/**lange Plattform */ 450, 530, 610, /**Weg zum Ziel */690, 720, 750/**Ziel */};
    public int[] mGroundCoordinatesY = {525, 525, 525, 525,/** links unten L*/ 485, 485, 455,/** L*/ 420, 420, 420, 420,/**links oben L */ 450, 450, 450, 450, 450, 450,/** rechts vom L*/ 380, 380,/**Zwischenplattform */ 330, 330, 330, 330, 330, 330, 330,/**lange Plattform */ 330, 330, 330, /**Weg zum Ziel */330, 330, 330/**Ziel */};
    public int[] mCoinCoordinatesX = {185, 245,/**links oben L */ 450, 480, 510, 540, 570, 600/** rechts vom L */};
    public int[] mCoinCoordinatesY = {400, 400,/**links oben L */ 430, 430, 430, 430, 430, 430/** rechts vom L */};
    public int[] mEnemyCoordinatesX = {500,/** rechts vom L */ 225,/** links oben L */ 360,/** lange Plattform */ 720/** Ziel*/};
    public int[] mEnemyCoordinatesY = {400,/** rechts vom L */ 390,/** links oben L */ 300,/** lange Plattform */ 300/** Ziel*/};
    
    public Player mPlayer;
    /**
     * Constructor for objects of class Level1.
     * 
     */
    public Level1()
    {    
        // Create a new world with 800x600 cells with a cell size of 1x1 pixels.
        super(800, 600, 1);
        // Protagonisten erstellen.
        mPlayer = new Player();
        
        // Unteren Boden erstellen.
        for(int i = 0; i < 5; i++)
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
        for(int i = 0; i < mEnemyCoordinatesX.length; i++)
        {
            this.addObject(new Enemy(), mEnemyCoordinatesX[i], mEnemyCoordinatesY[i]);
        }
        //this.addObject(new Enemy(), 500, 400);
        // Zielflagge erstellen.
        this.addObject(new Flag(), 750, 290);
        // Trigger-Objekte erstellen.
        this.addObject(new TriggerObject(2, 330 + 15), 530 + 15, 330 + 15 );
    }
}
