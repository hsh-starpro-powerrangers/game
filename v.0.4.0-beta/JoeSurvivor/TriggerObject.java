import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class TriggerObject extends Actor
{
    // Variable für die Geschwindigkeit des Objektes.
    private int mMovingSpeed;    
    // Variable für die Bewegungsrichtung.
    private int mDirection = 0;
    // Variable für die originale Y-Position.
    private int mOriginalY;
    
    // Konstruktor.
    public TriggerObject(int pMovingSpeed, int pY)
    {
        // Position setzen.
        mMovingSpeed = pMovingSpeed;
        mOriginalY = pY;
    }
    
    // Wird zyklisch aufgerufen.
    public void act() 
    {       
        if(getOneIntersectingObject(Player.class) != null)
        {
            Player lPlayer = (Player)(getOneIntersectingObject(Player.class));
            lPlayer.decLives();
        }
        if(mDirection == 0)
        {
            // Prüfen, ob der Protagonist unter dem Objekt ist.
            for(int i = 0; i < getWorld().getHeight(); i += 10)
            {
                if((getOneObjectAtOffset(-(this.getImage().getWidth() / 2), i, Player.class) != null)
                    || (getOneObjectAtOffset(this.getImage().getWidth() / 2, i, Player.class) != null))
                {
                    mDirection = mMovingSpeed;
                }
            }
        }
        else if(mDirection > 0)
        {
            if(!isOnGround())
            {
                this.setLocation(this.getX(), this.getY() + mDirection);
            }
            else
            {
                mDirection = - mMovingSpeed;
            }
        }
        else if(mDirection < 0)
        {
            if(this.getY() != mOriginalY)
            {                    
                this.setLocation(this.getX(), this.getY() + mDirection);
            }
            else
            {
                mDirection = 0;
            }
        }                
    }   
    
    // Liefert true, wenn das Objekt den Boden trifft.   
    private boolean isOnGround()
    {
      //  return getOneObjectAtOffset(0, 26, Ground.class) != null;
      return !((getOneObjectAtOffset(-(this.getImage().getWidth() / 2 - 1), this.getImage().getHeight() / 2, Ground.class) == null) 
      && (getOneObjectAtOffset(this.getImage().getWidth() / 2 - 1, this.getImage().getHeight() / 2, Ground.class) == null));
    }
}
