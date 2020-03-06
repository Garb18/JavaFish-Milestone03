package UserCode.Animals;

import UserCode.Minds.*;
import Framework.Interfaces.IWorld;
import UserCode.Random.*;
import Exceptions.*;

/**
 * Any animal that can be spawned must implement ISpawnable.
 * 
 * @author Benedict Gardner & Marc Price 
 * @version 0.1
 */
public interface ISpawnable
{
    /**
     * Method spawn
     *
     * @param world A parameter
     * @param pMind A parameter
     * @param pX A parameter
     * @param pY A parameter
     * @param xOrientation A parameter
     * @param yOrientation A parameter
     * @param pRdm A parameter
     * @param pMinPos A parameter
     * @param pMaxPos A parameter
     */
    void spawn(IWorld world, IMind pMind, double pX, double pY, double xOrientation, double yOrientation, IRandom pRdm, double pMinPos, double pMaxPos)throws WorldDoesNotExistException;
}
