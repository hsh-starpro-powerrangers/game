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
        if(mFalls)
        {
            setLocation(getX(), getY() + 1);
            if(isOnGround())
            {
                stopFalling();
            }
        }
        // Prüft, ob der Protagonist in Reichweite ist.
        if(canSee(Player.class))
        {
            destroy(Player.class);
        }
        move(mDirection);
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
    public boolean canSee(Class clss)
    {
        Actor actor = getOneObjectAtOffset(0, 0, clss);
        return actor != null;        
    }
    
    // Eliminiert den getroffenen Gegner sowie das Geschoss.
    public void destroy(Class clss)
    {
        Actor actor = getOneObjectAtOffset(0, 0, clss);
        if(actor != null) {
            getWorld().removeObject(actor);
        }        
        getWorld().addObject(new Text("Game Over!"), 300,300);
        // TODO: Game-Over-Sound
        // Greenfoot.playSound("au.wav");
    }
    
    // Liefert true, wenn der Charakter auf dem Boden steht.    
    private boolean isOnGround()
    {
      return !(getOneObjectAtOffset(0, 26, Ground.class) == null);//&& (getOneObjectAtOffset(0, 26, Ground.class) == null));
    }
    
     // Liefert true, wenn der Charakter rechts neben einer Wand steht.
    private boolean isNearLeftWall()
    {
        // TODO: Y-Koordinaten überprüfen.
        return ((getOneObjectAtOffset(-10, -5, Ground.class) != null) || (getOneObjectAtOffset(-10, 15, Ground.class) != null));
    }
    
    // Liefert true, wenn der Charakter links neben einer Wand steht.
    private boolean isNearRightWall()
    {   
        // TODO: Y-Koordinaten überprüfen.
        return ((getOneObjectAtOffset(10,-5,Ground.class) != null) || (getOneObjectAtOffset(10,15,Ground.class) != null));
    }
}
