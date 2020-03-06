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
public class DiagonalMind implements IMind, IUpdatable
{
    // instance variables - replace the example below with your own
    private double _x, _y, _speedX, _speedY, _minPos, _maxPos;
    
    private int _facingDirection = 1, _facingDirectionY = 1;
    
    private boolean _flipped;
    
    private IDisplayObject _displayobj;

    /**
     * Constructor for objects of class HorizontalMind
     */
    public DiagonalMind()
    {
        //Do nothing - WEAK COUPLING
    }
    
    public void initialise(IDisplayObject pDisplayObject, IRandom pRdm, double pMinPos, double pMaxPos, double pX, double pY)
    {        
        // initialise instance variables
        _displayobj = pDisplayObject;
        _x = pX; _y = pY; _minPos = pMinPos; _maxPos = pMaxPos;
        
        _speedX = pRdm.returnDouble(0.005, 0.05) * _facingDirection;
        _speedY = pRdm.returnDouble(0.005, 0.05) * _facingDirectionY;
    }
    
    public void update()
    {       
        if(_x >= _maxPos)
        {
            _facingDirection *= -1;
            _displayobj.orientation(180,270);
        }
        else if(_x <= _minPos)
        {            
            _facingDirection *= -1;
            _displayobj.orientation(180,90);
        } 
        
        //Y axis boundry check - accomodating for smaller boundary
        if(_y >= _maxPos -2)
        {
             _facingDirectionY *= -1;
        }
        else if (_y <= _minPos)
        {
            _facingDirectionY *= -1;
        }
        _x += _speedX; 
        _y += _speedY;
        _speedX *= _facingDirection; 
        _speedY *= _facingDirectionY;
        
        _displayobj.position(_speedX, _speedY);
    }    
}