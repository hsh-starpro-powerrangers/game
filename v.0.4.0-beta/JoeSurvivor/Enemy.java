import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Actor
{
    private boolean mFalls = false;
    private int mDirection = -1;
    
    // Konstruktor.
    public Enemy()
    {
        fall();
    }
    
    // Wird zyklisch aufgerufen.
    public void act() 
    {
        // Bewegt den Gegner.
        move(mDirection);
        // Prüft, ob der Gegner fällt.
        if(mFalls)
        {
            setLocation(getX(), getY() + 1);
            if(isOnGround())
            {
                stopFalling();
            }
        }
        // Prüft, ob der Protagonist in Reichweite ist.
        if(canSeePlayer(Player.class))
        {            
            Player lPlayer = (Player)(getOneObjectAtOffset(0, 0, Player.class));
            if(lPlayer != null)
            {
                lPlayer.decLives();
            }
        } 
        // Prüft, ob der Gegner an einem Abgrund oder einer Mauer ist.
        if(!isOnGround() || isNearLeftWall() || isNearRightWall())
        {
            mDirection = mDirection * -1;
        }
    }    
        
    // Lässt den Charakter fallen.
    private void fall()
    {
        mFalls = true;
    }
    
    // Beendet das Fallen des Charakters.
    private void stopFalling()
    {
        mFalls = false;
        //setLocation(getX(),(getOneIntersectingObject(Ground.class).getY() - 42));
    }
    
    // Liefert, ob ein Objekt einer beliebigen Klasse in Reichweite ist.
    public boolean canSeePlayer(Class pClass)
    {
        Actor actor = getOneObjectAtOffset(0, 0, pClass);
        return actor != null;        
    }
    
    // Liefert true, wenn das Objekt den Boden trifft.     
    private boolean isOnGround()
    {
        return !((getOneObjectAtOffset(-(this.getImage().getWidth() / 2 - 1), this.getImage().getHeight() / 2, Ground.class) == null) 
            && (getOneObjectAtOffset(this.getImage().getWidth() / 2 - 1, this.getImage().getHeight() / 2, Ground.class) == null));
    }
    
    // Liefert true, wenn der Charakter rechts neben einer Wand steht.
    private boolean isNearLeftWall()
    {
        return ((getOneObjectAtOffset(-(this.getImage().getWidth() / 2), -(this.getImage().getHeight() / 2 - 1), Ground.class)) != null     // Obere Kante.
            || (getOneObjectAtOffset(-(this.getImage().getWidth() / 2), 0, Ground.class) != null)                                           // Mittlere Kante.
            || (getOneObjectAtOffset(-(this.getImage().getWidth() / 2), this.getImage().getHeight() / 2 - 1, Ground.class) != null));       // Untere Kante.
    }
    
    // Liefert true, wenn der Charakter links neben einer Wand steht.
    private boolean isNearRightWall()
    {   
        return ((getOneObjectAtOffset(this.getImage().getWidth() / 2, -(this.getImage().getHeight() / 2 - 1), Ground.class)) != null        // Obere Kante.
            || (getOneObjectAtOffset(this.getImage().getWidth() / 2, 0, Ground.class)) != null                                             // Mittlere Kante.
            || (getOneObjectAtOffset(this.getImage().getWidth() / 2, this.getImage().getHeight() / 2 - 1, Ground.class) != null));          // Untere Kante.
    }
}
