package UserCode.Minds;

import UserCode.Random.*;
import Framework.Interfaces.IDisplayObject;

/**
 * Write a description of interface IMind here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface IMind
{
    void initialise(IDisplayObject pDisplayObkject, IRandom pRdm, double pMinPos, double pMaxPos, double pX, double pY);
}