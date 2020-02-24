package UserCode.Minds;
import Framework.Interfaces.IDisplayObject;

/**
 * Write a description of interface IMind here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface IMind
{
    void initialise(IDisplayObject pDisplayObkject, double pX, double pY);
    
    void update();
}