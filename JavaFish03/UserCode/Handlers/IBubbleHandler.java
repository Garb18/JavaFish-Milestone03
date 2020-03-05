package UserCode.Handlers;

import UserCode.Factory.*;
import Framework.Interfaces.*;
import UserCode.Random.*;

/**
 * Write a description of interface IBubbleHandler here.
 * 
 * @author Benedict Gardner
 * @version 09/12/2019
 */
public interface IBubbleHandler
{
    //Initialise Handler
    public void Initialise(IWorld pWorld, IUpdatableFactory pFactory, IRandom pRdm);
    //Place a bubble in the scene
    public void placeBubble(double px, double py);
}