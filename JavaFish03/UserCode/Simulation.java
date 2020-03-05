package UserCode;

///////////////////////////////////////////////////////////////////////////////////////////////////
// Notes:
// * Add code to this as necessary to produce your simulation.
// * Use comments to clearly highlight your code that has been added.
// * Acknowledge/cite appropriately any added code that is not your own.
///////////////////////////////////////////////////////////////////////////////////////////////////
import java.util.List;
import java.util.ListIterator;
import java.util.ArrayList;
import env3d.Env;
import Framework.Interfaces.*;
import Framework.Implementations.*;
import Exceptions.*;
import UserCode.Animals.*;
import UserCode.Minds.*;
import UserCode.Factory.*;
import UserCode.InputHandling.*;
import UserCode.Random.*;
import UserCode.Handlers.*;

/**
 * Simulation is the top-level class for the Aquarium simulation.
 * 
 * @author (your name here!) and Marc Price
 * @version 0.6
 */
public class Simulation implements IInputListener
{
    // instance variables:
    // DECLARE a reference to the IWorld, call it '_world':
    private IWorld _world;
    
    // DECLARE a reference to the IInput, call it '_input':
    private IInput _input;    
    
    private IRandom _rdm;

    // DECLARE a reference to the IInputPublisher, call it '_inputPublisher':
    private IInputPublisher _inputPublisher;
    
    // DECLARE a list for updatables, call it '_updatables':
    private List<IUpdatable> _updatables;
       
    // DECLARE a reference to an IUpdatableFactory, call it '_factory':
    private IUpdatableFactory _factory;
    
    //DECLARE a boolean that signals when fishfood should be placed:
    private boolean _newFishFood = false;

    //DECLARE an array of ints that stores the position of the mouse:
    private int[] _mouseVal = {-1,-1};

    // DECLARE a boolean that signals when the simulation loop should be exited:
    private boolean endSim = false;    
    
    private int _javaFishAmount = 10, _orangeFishAmount = 10, _piranhaAmount = 4, _seaHorseAmount = 10, _urchinAmount = 7;
    
    private IBubbleHandler _bHandler;
    /**
     * METHOD: Static Main method used for creating standalone apps
     *
     */
    public static void main(String[] args) throws Exception
    {
        Simulation sim = new Simulation();
        sim.populate();
        sim.run();
    }    
    
    /**
     * Constructor for objects of class Simulation
     */
    public Simulation()
    {
        // INITIALISE instance variables:
        // _factory:
        _factory = new UpdatableFactory();
        
        // _updatables:
        _updatables = new ArrayList<IUpdatable>();
        
        _rdm = new RandomDouble();
        
        try
        {
            //_world
            _world = ((IWorld) _factory.create(Core.class));
            
            _bHandler = ((IBubbleHandler)_factory.create(BubbleHandler.class));
            
            _bHandler.Initialise(_world, _factory, _rdm);
            
            // _input:
            _input = (IInput) _world;

            _inputPublisher = ((IInputPublisher) _factory.create(MouseHandler.class));

            _inputPublisher.Initialise(_input);
        }
        catch(Exception e)
        {
            // do nothing
        }        
        
        // ADD _world implementation to _updatables:
        _updatables.add((IUpdatable) _world);

        _updatables.add((IUpdatable) _bHandler);
        
        // ADD _inputPublisher implementation to _updatables:
        _updatables.add((IUpdatable) _inputPublisher);
        
        //SUBSCRIBE this simulation to observer
        _inputPublisher.subscribe(this);
    }
    
    public void populate()
    {        
        // Create JavaFish objects and store them in updatable list
        for (int i=0; i<_javaFishAmount; i++)
        {
            try
            {
                //Factory creates the object
                IUpdatable javaFish = _factory.create(JavaFish.class);
                // Add object to _updatables list:
                _updatables.add(javaFish);
            }
            catch (Exception e)
            {
                System.out.println("JavaFish failed");
            }
        }
        for (int i=0; i<_piranhaAmount; i++)
        {
            try
            {
                //Factory creates the object
                IUpdatable piranha = _factory.create(Piranha.class);
                // Add object to _updatables list:
                _updatables.add(piranha);
            }
            catch (Exception e)
            {
                System.out.println("Piranha failed");
            }
        }
        for (int i=0; i<_seaHorseAmount; i++)
        {
            try
            {
                //Factory creates the object
                IUpdatable seahorse = _factory.create(SeaHorse.class);
                // Add object to _updatables list:
                _updatables.add(seahorse);
            }
            catch (Exception e)
            {
                System.out.println("Seahorse failed");
            }
        }
        for (int i=0; i<_urchinAmount; i++)
        {
            try
            {
                //Factory creates the object
                IUpdatable urchin = _factory.create(Urchin.class);
                // Add object to _updatables list:
                _updatables.add(urchin);
            }
            catch (Exception e)
            {
                System.out.println("Urchin failed");
            }
        }
    }

    public void onInput(int ...data)
    {
        //SET _newFishFood state to true:
        _newFishFood = true;

        //Assign mouseVal to data:
        _mouseVal = data;
    }

    /**
     * METHOD: Run the simulation loop.  User presses escape to exit.
     *
     */
    public void run()
    {
        // Create the 3D world:
        _world.create();
        
        // User try - catch to ensure 3D world was successfully created:
        try
        {
            // ADD Objects to 3D world?:
            // Add each updatable in updatable to the aquarium:
            for (IUpdatable updatable : _updatables)
            {
                // For each instance of JavaFish:
                if (updatable instanceof JavaFish || updatable instanceof Piranha)
                {
                    // Create a mind object
                    try
                    {
                        IUpdatable mind = _factory.create(HorizontalMind.class);
                        // Initialise updatable:
                        ((ISpawnable)updatable).spawn(_world, ((IMind)mind), _rdm.returnDouble(1,8), _rdm.returnDouble(1,7), 0, 270,_rdm, 1.0, 9.0);                
                    }
                    catch(Exception e)
                    {
                        //Do nothing
                    }
                }
                if (updatable instanceof SeaHorse)
                {
                    // Create a mind object
                    try
                    {
                        IUpdatable mind = _factory.create(DiagonalMind.class);
                        // Initialise updatable:
                        ((ISpawnable)updatable).spawn(_world, ((IMind)mind), _rdm.returnDouble(1,8), _rdm.returnDouble(1,7), 180, 90,_rdm, 1.0, 9.0);                
                    }
                    catch(Exception e)
                    {
                        //Do nothing
                    }
                }
                if (updatable instanceof Urchin)
                {
                    // Create a mind object
                    try
                    {
                        IUpdatable mind = _factory.create(HorizontalMind.class);
                        // Initialise updatable:
                        ((ISpawnable)updatable).spawn(_world, ((IMind)mind), _rdm.returnDouble(1,8), 1, 0, 270,_rdm, 1.0, 9.0);                
                    }
                    catch(Exception e)
                    {
                        //Do nothing
                    }
                }
            }
            // Start simulation loop:
            while (!endSim)
            {
                // UPDATE STAGE:
                // IF: user has requested simulation loop exit (ie escape pressed):
                if (_input.getKey() == 1)
                {
                    // SET: render loop exit condition
                    endSim = true;
                }
                
                // ADD lions when requested via mouse input...                
                // Check if new FishFood requested:
                if (_newFishFood)
                {                    
                    // COMPUTE the position/orientation for the lion from mouseVal:
                    Double[] posn = {_mouseVal[0]*0.0077, _mouseVal[1]*0.0077};
                    Double[] angle = {0.0,90.0};
                    
                    try
                    {
                        // INSTANTIATE the new FishFood as an IUpdatable, call it 'lion':
                        IUpdatable fishFood = _factory.create(FishFood.class);
                        IUpdatable mind = _factory.create(SinkingMind.class);
                        
                        // ADD FishFood to _updatables:
                        _updatables.add(fishFood);
                        
                        // SPAWN FishFood in 3D world:
                        ((ISpawnable) fishFood).spawn(_world, ((IMind)mind), posn[0], posn[1], angle[0], angle[1], _rdm, 1, 10);
                        
                        _newFishFood = false;
                    }
                    catch (Exception e)
                    {
                        // do nothing
                    }        
                }
            
                // UPDATE 3D objects and world...
                // (Iterate through _updatables in reverse)
                // CREATE an Iterator for _updatables, pointing at end of list, call it _updateIterator:
                ListIterator<IUpdatable> _updateIterator = _updatables.listIterator(_updatables.size());
                
                // UPDATE _updatables in reverse order (so that 3D world is updated last):
                while (_updateIterator.hasPrevious())
                {
                    _updateIterator.previous().update();
                }
            }
        
            // EXIT: cleanly by closing-down the environment:
            _world.destroy();
        }
        catch (WorldDoesNotExistException e)
        {
            System.out.println(e.getMessage());
        }

    }

}
