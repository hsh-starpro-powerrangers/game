import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{    
    // Konstante für die Sprunghöhe.
    private final int JUMP_LIMIT = 40   ;
    // Konstante für die Spring- und Fallgeschwindigkeit.
    private final int GRAVITY_SPEED = 1;
    // Konstante für die Gehgeschwindigkeit.
    private final int WALKING_SPEED = 1;
    // Konstante für die Geschossgeschwindigkeit.
    private final int BULLET_SPEED = 1;
    
    // Zählt bei einem Sprung hoch, bis der Spieler wieder fällt.
    private int mJumpCounter = 0;
    // Zählt bei einem Schuss bis zum Reload-Delay.
    private int mReloadCounter = 0;
    // Nachladezeit.
    private int mReloadDelay = 180;
    // Index für die akutelle Waffe.
    private int mWeapon = 1;
    // Index für den Münzstand.
    private int mCollectedCoins = 0;
    
    // Gibt an, ob der Spieler gerade springt.
    private boolean mJumps = false;
    // Gibt an, ob der Spieler gerade fällt.
    private boolean mFalls = false;
    // Gibt an, ob der Spieler gerade geschossen hat.
    private boolean mHasShot = false;    
    // Gibt die Blickrichtung des Spielers an.
    private boolean mLooksRight = true;
    
    // Hilfsvariablen, um zu bestimmen, ob Tasten gehalten werden.
    // Gibt an, ob die Sprung-Taste gehalten wird.
    private boolean mJumpKeyDown = false;
    // Gibt an, ob die Reset-Taste gehalten wird.
    private boolean mResetKeyDown = false;
        
    // Konstruktor.
    public Player()
    {
        resetPosition();
        fall();
    }
    
    // Wird zyklisch aufgerufen.
    public void act()
    {        
        if(Greenfoot.isKeyDown("1"))
        {
            changeWeapon(0);
        }
        else if(Greenfoot.isKeyDown("2"))
        {
            changeWeapon(1);
        }
        // TEMP ENDE
        
        // Abfragen für Aktionen.
        if(Greenfoot.isKeyDown("space"))
        {
            shoot();
        }
        if(Greenfoot.isKeyDown("A"))
        {
            if(!isNearLeftWall())
            {                
                mLooksRight = false;
                // TODO: Bilder setzen.
                //setImage("Player02l.png");
                move(-WALKING_SPEED);
            }
            if(!isOnGround())
            {
                fall();
            }
        }
        if(Greenfoot.isKeyDown("D"))
        {
            if(!isNearRightWall())
            {
                mLooksRight = true;
                // TODO: Bilder setzen.
                //setImage("Player02r.png");
                move(WALKING_SPEED);
            }
            // Abfrage, ob der Protagonist, 
            if(!isOnGround())
            {
                fall();
            }
        }     
        if(Greenfoot.isKeyDown("W"))
        {
            if(!mJumpKeyDown && !mFalls && !mJumps)
            {
                jump();                
            }
            mJumpKeyDown = true;
        } 
        if(Greenfoot.isKeyDown("R"))
        {
            if(!mResetKeyDown)
            {
                resetPosition();            
            }
            mResetKeyDown = true;
        }
        // Setzen der Hilfsvariable für das Halten der W-Taste.
        if(!Greenfoot.isKeyDown("W"))
        {
            mJumpKeyDown = false;
        }
        // Setzen der Hilfsvariable für das Halten der R-Taste.
        if(!Greenfoot.isKeyDown("R"))
        {
            mResetKeyDown = false;
        }
        // Delay für die Waffe.
        if(mHasShot)
        {
            if(mReloadCounter < mReloadDelay)
            {
                mReloadCounter ++;
            }
            else
            {
                mHasShot = false;
            }
        }
        // Abfrage der Sprung-Variable.
        if(mJumps)
        {
            if(mJumpCounter < JUMP_LIMIT && !isOnCeiling())
            {
                mJumpCounter ++;
                setLocation(getX(), getY() - GRAVITY_SPEED);
            }
            else 
            {
                mJumps = false;
                fall();
            }   
        }
        // Abfrage der Fall-Variable.
        else if(mFalls)
        {
            setLocation(getX(), getY() + GRAVITY_SPEED);
            if(isOnGround())
            {
                stopFalling();
            }
        }
        // Abfrage, ob eine Münze in der Nähe ist.
        if(canSee(Coin.class))
        {
            collect(Coin.class);
            mCollectedCoins ++;
            Greenfoot.playSound("money.wav");
            showCoins();
        }
        // Abfrage, ob die Flagge in der Nähe ist.
        if(canSee(Flag.class)) 
        {
            getWorld().addObject(new Text("Gewonnen!"), 60, 20);
        }
    }
    
    // Lässt den Charakter schießen.
    private void shoot()
    {
        if(!mHasShot)
        {
            int lRotation = this.getRotation();
            mReloadCounter = 0;
            mHasShot = true;
            World lWorld = getWorld();
            if(!mLooksRight)
            {
                lRotation += 180;
            }
            switch (mWeapon)
            {
                case 0:
                    lWorld.addObject(new Bullet(lRotation, BULLET_SPEED), getX(), getY());
                    //TODO: Greenfoot.playSound(" ? ");
                break;
                case 1:
                    lWorld.addObject(new Bullet(lRotation, BULLET_SPEED), getX(), getY() - 10);
                    Greenfoot.playSound("AWPkurz.wav");
                break;
            }
        }
    }
    
    // Lässt den Charakter springen.
    private void jump()    
    {    
        mJumpCounter = 0;
        mJumps = true;
    }        
    
    // Wechselt den Waffentyp sowie das Anzeigebild.
    private void changeWeapon(int pWeapon)
    {
        mWeapon = pWeapon;
        switch(mWeapon)
        {
            case 0:
             //TODO:  setImage("Player01.png");
                break;
            case 1:
             //TODO:   setImage("Player02.png");
                break;
        }
    }  
    
    // Lässt den Charakter fallen.
    private void fall()
    {
        mFalls = true;
    }
    
    // TODO: Die Abstände müssen bei anderen Bildern angepasst werden!!!!
    
    // Liefert true, wenn der Charakter auf dem Boden steht.    
    private boolean isOnGround()
    {
      //  return getOneObjectAtOffset(0, 26, Ground.class) != null;
      return !((getOneObjectAtOffset(-(this.getImage().getWidth() / 2 - 1), this.getImage().getHeight() / 2, Ground.class) == null) 
      && (getOneObjectAtOffset(this.getImage().getWidth() / 2 - 1, this.getImage().getHeight() / 2, Ground.class) == null));
    }
    
    // Liefert true, wenn der Charakter die Decke trifft.  
    private boolean isOnCeiling()
    {
      //  return getOneObjectAtOffset(0, 26, Ground.class) != null;
      return !((getOneObjectAtOffset(-(this.getImage().getWidth() / 2 - 1), -(this.getImage().getHeight() / 2), Ground.class) == null) 
      && (getOneObjectAtOffset(this.getImage().getWidth() / 2 - 1, -(this.getImage().getHeight() / 2), Ground.class) == null));
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
    
    // Beendet das Fallen des Charakters.
    private void stopFalling()
    {
        mFalls = false;
        //setLocation(getX(),(getOneIntersectingObject(Ground.class).getY() - 42));
    }
    
    // Liefert, ob ein Objekt einer beliebigen Klasse in Reichweite ist.
    private boolean canSee(Class pClass)
    {
        Actor actor = getOneObjectAtOffset(0, 0, pClass);
        return actor != null;        
    }
    
    // Sammelt eine Münze auf.
    private void collect(Class pClass)
    {
        Actor lActor = getOneObjectAtOffset(0, 0, pClass);
        if(lActor != null) 
        {
            getWorld().removeObject(lActor);
        }        
    }
                    
    // Zeigt die gesammelten Münzen an.
    public void showCoins()
    {
        getWorld().addObject(new Text("Punkte: " + mCollectedCoins), 730,20);
    }
    
    // Setzt den Spieler auf den Startpunkt zurück.
    public void resetPosition()
    {
        this.setLocation(30, 540);
        if(mCollectedCoins >= 1)
        {
            mCollectedCoins --;
            showCoins();
        }        
    }
}