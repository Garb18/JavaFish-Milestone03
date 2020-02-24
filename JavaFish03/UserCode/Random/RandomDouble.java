package UserCode.Random;

import java.util.Random;

/**
 * Write a description of class RandomDouble here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RandomDouble implements IRandom
{
    private Random _rdm;
    /**
     * Constructor for objects of class RandomDouble
     */
    public RandomDouble()
    {
        _rdm = new Random();
    }
    
    public double returnDouble()
    {
        return _rdm.nextDouble();
    }
    
    public double returnDouble(double min, double max)
    {
        return min + (max - min) * _rdm.nextDouble();
    }
}
