package UserCode.Minds;

import Exceptions.*;
import UserCode.Random.*;
import Framework.Interfaces.IDisplayObject;
import Framework.Interfaces.IUpdatable;
import Framework.Implementations.DisplayObject;

/**
 * Write a description of class HorizontalMind here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HorizontalMind implements IMind, IUpdatable
{
    // instance variables - replace the example below with your own
    private double _x, _y, _speed, _minPos, _maxPos;
    
    private int _facingDirection = 1;
    
    private boolean _flipped;
    
    private IRandom _rdm;
    
    private IDisplayObject _displayobj;

    /**
     * Constructor for objects of class HorizontalMind
     */
    public HorizontalMind()
    {
        //Do nothing - WEAK COUPLING
    }
    
    public void initialise(IDisplayObject pDisplayObject, IRandom pRdm, double pMinPos, double pMaxPos, double pX, double pY)
    {        
        // initialise instance variables
        _displayobj = pDisplayObject;
        _x = pX; _y = pY; _rdm = pRdm; _minPos = pMinPos; _maxPos = pMaxPos;
        
        _speed = _rdm.returnDouble(0.005, 0.05) * _facingDirection;
    }
    
    public void update()
    {       
        if(_x >= _maxPos)
        {
            _facingDirection *= -1;
            _displayobj.orientation(0,90);
        }
        else if(_x <= _minPos)
        {            
            _facingDirection *= -1;
            _displayobj.orientation(0,270);
        }        
        _x += _speed;       
        _speed *= _facingDirection; 
        
        _displayobj.position(_speed, 0);
    }    
}