import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bullet extends Actor
{    
    int mSpeed;
    
    // Konstruktor.
    public Bullet(int pRotation, int pSpeed)
    {
        setRotation(pRotation);
        mSpeed = pSpeed;
    }
        
    // Wird zyklisch aufgerufen.
    public void act() 
    {
        move(mSpeed);
        if(isAtEdge())
        {
            getWorld().removeObject(this);
        }
        
        else if(this.hit(Enemy.class))
        {
            destroy(Enemy.class);
        }
    }    
    
    // Liefert, ob ein Objekt einer beliebigen Klasse in Reichweite ist.
    public boolean hit(Class clss)
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
        getWorld().removeObject(this);
        Greenfoot.playSound("au.wav");
    }
}
