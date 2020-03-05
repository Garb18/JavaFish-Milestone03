package UserCode.Handlers;

import Framework.Interfaces.*;
import UserCode.Animals.ISpawnable;
import UserCode.Animals.Bubble;
import UserCode.Factory.*;
import Framework.Implementations.Core;
import java.util.List;
import java.util.ArrayList;

/**
 * Handles the placing and removal of bubbles within the scene
 * 
 * @author Benedict Gardner
 * @version 10/12/19
 */
public class BubbleHandler implements IBubbleHandler
{
    // Instance variables
    // DECLARE a reference to ICore, call it '_core':
    private IWorld _world;
    // DECALRE a reference to IBubble, call it '_bubble':
    private ISpawnable _bubble;
    // DECALRE a List, call it '_arrayDisplayBubble':
    private List<IUpdatable> _displayBubble;
    
    private IUpdatableFactory _factory;
    /**
     * Constructor for objects of class BubbleHandler
     */
    public BubbleHandler(IWorld pWorld)
    {
       // initialise instance variables
       //STORE reference to core
       _world = pWorld;
       //INITIALISE ArrayList
       _displayBubble = new ArrayList<IUpdatable>();
       
       _factory = new UpdatableFactory();
    }
    
    public void placeBubble(double px, double py)
    {
        //INITIALISE a new bubble, position adjusted to make it seem centralised
        _bubble = new Bubble();
        //STORE bubble in ArrayList
        displayBubble.add(_bubble);
        //PLACE bubble into the scene
        IMind _mind = new BubbleMind();
        ((ISpawnable) _bubble).spawn(_world, px, py, 0, 90);
    }
    
    public void update()
    {
        //Each bubble stored in ArrayList
        for (IBubble _bubble: _arrayDisplayBubble)
        {
            // When bubble reaches boundary
            if (_bubble.popBubble() == true)
            {
                // "POP" the bubble:
                _core.removeDisplayObject((IDisplayObject)_bubble);
            }
        }
    }
}