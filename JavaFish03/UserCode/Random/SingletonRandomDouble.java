package UserCode.Random;


import java.util.Random;

/**
 * Write a description of class SingletonRandom here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SingletonRandomDouble implements IRandom
{
   private static SingletonRandomDouble instance = null;
   private Random _rdm;
   
   private SingletonRandomDouble() {
      _rdm = new Random();
   }

   public static SingletonRandomDouble getInstance() {
      if(instance == null) {
         instance = new SingletonRandomDouble();
      }
      return instance;
   }
   
   /**
     * Method returnDouble
     *
     * @return A random double
     */
    public double returnDouble()
    {
        return _rdm.nextDouble();
    }
    
   /**
    * Method returnDouble
    * Returns a random double between a user specified range
    *
    * @param min Minimum value returned in range
    * @param max Maximum value returned in range
    * @return The return random double
    */
   public double returnDouble(double min, double max)
   {
       return min + (max - min) * _rdm.nextDouble();
   }
}
