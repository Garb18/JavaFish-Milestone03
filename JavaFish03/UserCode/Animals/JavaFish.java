package UserCode.Animals;

import UserCode.Minds.*;
import Framework.Interfaces.IDisplayObject;
import Framework.Interfaces.IWorld;
import Framework.Implementations.DisplayObject;
import Framework.Interfaces.IUpdatable;
import Exceptions.*;

/**
 * Write a description of class JavaFish here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class JavaFish implements IUpdatable, ISpawnable
{
    // instance variables
    // DECLARE an IDisplayObject to represent this JavaFish, call it _displayObject:
    private IDisplayObject _displayObject;
    
    // DECLARE a String to store the path to _displayObject's model, call it _model, and initialise it:
    String _model = "models/billboard/billboard.obj";
    
    // DECLARE a String to store the path to _displayObject texture, call it _texture, and initialise it:
    String _texture = "textures/javaFish/JavaFish.png";
    
    // DECLARE an IMovement to control the fish, call it '_mind':
    private IMind _mind;
    
    /**
     * Constructor for objects of class JavaFish
     */
    public JavaFish()
    {
        // INSTANTIATE _displayObject:
        _displayObject = new DisplayObject(_model, _texture, 0.15);
    }
    
    public void spawn(IWorld world, IMind pMind, double pX, double pY, double xOrientation, double yOrientation)throws WorldDoesNotExistException
    {
        _displayObject.position(pX, pY);
        
        _displayObject.orientation(xOrientation, yOrientation);
        
        world.addDisplayObject(_displayObject);
        
        _mind = pMind;
        
        _mind.initialise(_displayObject, pX, pY);
    }
    
    public void update()
    {
        //Update mind class:
        _mind.update();
    }
}
