import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;

/**
 * Write a description of class Text here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Text extends Actor
{
    public Text(String pText)
    {
        // Greenfoot.playSound("slurp.wav");
        setImage(new GreenfootImage(pText, 25, Color.BLACK, Color.WHITE));
    } 
}
