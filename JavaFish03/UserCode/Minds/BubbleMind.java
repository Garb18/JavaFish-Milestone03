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
public class BubbleMind implements IMind, IUpdatable
{
    // instance variables - replace the example below with your own
    private double _x, _y, _speedX, _speedY, _minPos, _maxPos, _Oscillate;
    
    private int _facingDirectionX = 1, _facingDirectionY = -1;
    
    private boolean sink = true;
    
    private IRandom _rdm;
    
    private IDisplayObject _displayobj;

    /**
     * Constructor for objects of class HorizontalMind
     */
    public BubbleMind()
    {
        //Do nothing - WEAK COUPLING
    }
    
    public void initialise(IDisplayObject pDisplayObject, IRandom pRdm, double pMinPos, double pMaxPos, double pX, double pY)
    {        
        // initialise instance variables
        _displayobj = pDisplayObject;
        _x = pX; _y = pY; _rdm = pRdm; _minPos = pMinPos; _maxPos = pMaxPos;
        
        _speedX = _rdm.returnDouble(0.0005, 0.005) * _facingDirectionX;
        _speedY = _rdm.returnDouble(0.005, 0.05) * _facingDirectionY;
    }
    
    public void update()
    {        
        //Y axis boundry check
        if (_y <= _minPos)
        {
            _speedY = 0;
            _speedX = 0;
            sink = false;
        }    
        if(sink)
        {
            _x += _speedX = ((float)(Math.cos(_Oscillate += 0.1F)) * 0.02);      
            _y += _speedY;
        }
        
        _displayobj.position(_speedX, _speedY);
    }    
}