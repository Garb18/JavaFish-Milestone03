package UserCode.Minds;

import Exceptions.*;
import UserCode.Random.*;
import Framework.Interfaces.IDisplayObject;
import Framework.Implementations.DisplayObject;

/**
 * Write a description of class HorizontalMind here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HorizontalMind implements IMind
{
    // instance variables - replace the example below with your own
    private double _x, _y, _speed, _minPos, _maxPos;
    
    private int _facingDirection = 1;
    
    private IRandom _rdm;
    
    private IDisplayObject _displayobj;

    /**
     * Constructor for objects of class HorizontalMind
     */
    public HorizontalMind(IRandom pRdm, double pMinPos, double pMaxPos)
    {
        // initialise instance variables
        _rdm = pRdm;
        
        _minPos = pMinPos;
        _maxPos = pMaxPos;
        
        _speed = _rdm.returnDouble(0.0005, 0.005) * _facingDirection;
    }
    
    public void initialise(IDisplayObject pDisplayObject, double pX, double pY)
    {
        _displayobj = pDisplayObject;
        _x = pX; _y = pY;        
    }
    
    public void update()
    {         
        _displayobj.position(_speed, 0);
        
        if(_x >= _maxPos || _x <= _minPos)
        {
            _speed *= -1;
        }
        
        _x = _speed * _facingDirection;
    }    
}
