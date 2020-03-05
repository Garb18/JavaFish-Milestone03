package UserCode.Handlers;

import Framework.Interfaces.*;
import UserCode.Animals.ISpawnable;
import UserCode.Animals.Bubble;
import UserCode.Factory.*;
import UserCode.Minds.*;
import UserCode.Random.*;
import Framework.Implementations.Core;
import java.util.List;
import java.util.ArrayList;

/**
 * Handles the placing and removal of bubbles within the scene
 * 
 * @author Benedict Gardner
 * @version 10/12/19
 */
public class BubbleHandler implements IBubbleHandler, IUpdatable
{
    // Instance variables
    // DECLARE a reference to ICore, call it '_core':
    private IWorld _world;
    // DECALRE a reference to IBubble, call it '_bubble':
    private ISpawnable _bubble;
    // DECALRE a List, call it '_arrayDisplayBubble':
    private List<IUpdatable> _displayBubble;    
    // DECALRE a reference to IRandom, call it '_rdm':
    private IRandom _rdm;
    
    private IUpdatableFactory _factory;
    /**
     * Constructor for objects of class BubbleHandler
     */
    public BubbleHandler(IWorld pWorld)
    {
        //Do nothing
    }
    
    public void Initialise(IWorld pWorld, IUpdatableFactory pFactory, IRandom pRdm)
    {
       //STORE reference to core
       _world = pWorld;
       //STORE reference to factory
       _factory = pFactory;
       _rdm = pRdm;
       //INITIALISE ArrayList
       _displayBubble = new ArrayList<IUpdatable>();
    }
    
    public void placeBubble(double px, double py)
    {
        //INITIALISE a new bubble, position adjusted to make it seem centralised
        _bubble = new Bubble();
        //STORE bubble in ArrayList
        _displayBubble.add((IUpdatable)_bubble);
        //PLACE bubble into the scene
        IMind _mind = new BubbleMind();
        ((ISpawnable) _bubble).spawn(_world, _mind, px, py, 0, 90, _rdm, 0.0, 9.0);
    }
    
    public void update()
    {
        //Each bubble stored in ArrayList
        for (IUpdatable _bubble: _displayBubble)
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